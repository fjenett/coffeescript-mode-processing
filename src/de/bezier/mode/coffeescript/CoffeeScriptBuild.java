package de.bezier.mode.coffeescript;

import de.bezier.mode.coffeescript.compiler.*;
import de.bezier.mode.javascript.JavaScriptBuild;

import processing.core.PApplet;

import processing.app.Base;
import processing.app.Mode;
import processing.app.Sketch;
import processing.app.SketchCode;
import processing.app.Library;
import processing.app.SketchException;
import processing.app.Preferences;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.*;
import java.io.FileInputStream;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import javax.script.*;

/**
 *	This clearly needs a cleanup run.
 */
public class CoffeeScriptBuild extends JavaScriptBuild
{	
	public static String TEMPLATE_FOLDER_NAME = "template-coffee";
	public static String EXPORTED_FOLDER_NAME = "web-export-coffee";
	
	public static final String SIZE_REGEX = 
    	"(?:^|\\s|;)[@]?size\\s*[(]?\\s*([^\\s,]+)\\s*,\\s*([^\\s,\\)]+),?\\s*([^\\)]*)\\s*[)]?\\s*[;]?";
	
	public final static String IMPORT_REGEX =
		"^[\\s]*#[\\s]*import[\\s]+([^\\s]+)[\\s]*";
	
	private final static String SETUP_REGEX = 
		"^[\\s]*setup[\\s]*[:][\\s]*->[\\s]*?";

	private File binFolder;
	private Sketch sketch;
	private Mode mode;
	
	public CoffeeScriptBuild ( Sketch sketch ) 
	{
		super( sketch );
		
		this.sketch = sketch;
		this.mode = sketch.getMode();
	}
	
	/** 
	 * Export the sketch to the default web-export-coffee folder.  
	 * @return success of the operation 
	 */
	public boolean export() throws IOException, SketchException
	{
		File exportFolder = new File(sketch.getFolder(), EXPORTED_FOLDER_NAME);
		return build( exportFolder );
	}
	
	/**
	 * Builds the sketch
	 * 
	 * @param bin the output folder for the built sketch
	 * @return boolean whether the build was successful
	 */
	public boolean build ( File bin ) throws IOException, SketchException
	{
		// make sure the user isn't playing "hide-the-sketch-folder" again
		sketch.ensureExistence();

		this.binFolder = bin;

		if ( bin.exists() ) 
		{    
			Base.removeDescendants( bin );
		}

		// generate an ID for the sketch to use with <canvas id="XXXX"></canvas>
		String sketchID = sketch.getName().replaceAll("[^a-zA-Z0-9]+", "").replaceAll("^[^a-zA-Z]+","");

		String coffeeSketchName = "Sketch" + 
								  sketchID.substring(0,1).toUpperCase() + 
								  sketchID.substring(1).toLowerCase();

		// use as many spaces as defined in preferences
		String tabs = String.format( "%" + Preferences.get("editor.tabs.size") + "s", "" );
		
		StringBuffer bigCode = new StringBuffer();
		String modeExt = mode.getDefaultExtension(); // only loads the .pde files
		boolean setupFound = false;
		for (SketchCode sc : sketch.getCode())
		{
			if (sc.isExtension(modeExt)) 
			{
				String prog = sc.getProgram();
				String[] progLns = prog.split("\n");
				// TODO:
				// as we are adding a class around the code right shift every line by one tab
				// this is potantially problematic with multiline strings ..
				for ( String l : progLns ) 
				{
					if ( !setupFound ) 
					{
						if ( l.replaceAll("[\\s]", "").equals("setup:->") ) 
						{
							String indent = "";
							int indentSetup = l.indexOf("setup");
							if ( indentSetup > 0 ) {
								indent = String.format( "%" + indentSetup + "s", "" );
							}
							bigCode.append( tabs + indent + l + "\n" );
							l = tabs + "injectProcessingApi(@)";
							setupFound = true;
						}
					}
					bigCode.append( tabs + l + "\n" );
				}
			}
		}

		String coffeeCode = "\n" + "class " + coffeeSketchName + 
							"\n" + bigCode.toString() +
							"\n";
		
		// ------------------------------------------
		// 	PRE-COMPILE
		// ------------------------------------------
		
		File csCompilerScript = sketch.getMode().getContentFile( 
									TEMPLATE_FOLDER_NAME + File.separator + "coffee-script.js" );
		File compiledSketchFile = new File( bin, sketch.getName()+"-compiled.js" );
		String compiledSketch = CSCompiler.compile( 
			csCompilerScript, 
			coffeeCode, 
			compiledSketchFile
		);
		if ( compiledSketch == null ) {
			System.out.println( coffeeCode );
			return false;
		}

		// ------------------------------------------
		// 	INJECT Processing API
		// ------------------------------------------

		// inject Processing API to remove need for "@" ("this") everywhere ..

		File api = sketch.getMode().getContentFile( "processing-api-min.js" );
		BufferedReader reader = PApplet.createReader(api);
		StringBuilder builder = new StringBuilder();
		String oneLine;
		while ((oneLine = reader.readLine()) != null) builder.append( tabs + oneLine.replaceAll("\r","\n") + "\n" );
		String apiStr = builder.toString();

		String sketchHead = coffeeSketchName + " = (function() {";
		String compiledInjectedSketch = compiledSketch.replace( sketchHead, sketchHead + "\n" + apiStr + "\n" );

		PrintWriter writer = PApplet.createWriter( compiledSketchFile );
		String[] csLines = compiledInjectedSketch.split( "\n" );
		for ( String l : csLines )
		{
			writer.println( l );
		}
		writer.close();

		// ------------------------------------------
		// 	ADD FILES
		// ------------------------------------------
		
		// move the data files, copies contents of sketch/data/ to web-export-coffee/
		if ( sketch.hasDataFolder() ) 
		{
			try {
				Base.copyDir( sketch.getDataFolder(), bin );

			} catch ( IOException e ) {
				final String msg = "An exception occured while trying to copy the data folder. " + 
								   "You may have to manually move the contents of sketch/data to " +
								   "the web-export-coffee/ folder. Processing.js doesn't look for a data " +
								   "folder, so lump them together.";
				Base.showWarning("Problem building the sketch", msg, e);
			}
		}

		// as .js and .coffee files are allowed now include these into the mix,
		// first find and compile them ..
		String[] sketchFolderFilesRaw = sketch.getFolder().list();
		String[] sketchFolderFiles = new String[0];
		ArrayList sffList = new ArrayList();
		if ( sketchFolderFilesRaw != null )
		{
			for ( String s : sketchFolderFilesRaw )
			{
				if ( s.toLowerCase().startsWith(".") ) continue;
				if ( s.toLowerCase().endsWith(".pde") )
				{	
					sffList.add(s);
				} 
				else if ( s.toLowerCase().endsWith(".js") )
				{	
					sffList.add(s);
				}
				else if ( s.toLowerCase().endsWith(".coffee") )
				{
					String t = s.replace(".coffee","-compiled.js");
					String res2 = CSCompiler.compile( 
						csCompilerScript, 
						new File( sketch.getFolder(), s ),
						new File( bin, t )
					);
					if ( res2 == null ) {
						System.err.println( "Something went wrong compiling: " + s );
						return false;
					}
					sffList.add(s);
				}
				else 
					continue;
			}
			if ( sffList.size() > 0 )
				sketchFolderFiles = (String[])sffList.toArray(new String[0]);
		}
		
		// ... now copy them over.
		for ( String s : sketchFolderFiles )
		{
			try {
				Base.copyFile( new File(sketch.getFolder(), s), new File(bin, s) );
			} catch ( IOException ioe ) {
				String msg = "Unable to copy file: "+s;
				Base.showWarning("Problem building the sketch", msg, ioe);
				return false;
			}
		}
		
		// ------------------------------------------
		// 	IMPORT LIBRARIES
		// ------------------------------------------
		
		String[] matches;
		
		ArrayList<String> importPackages = new ArrayList<String>();
		ArrayList<String> csImports = new ArrayList<String>();
		String[] lines = bigCode.toString().split( "\n" );
		
		for ( String l : lines )
		{
			int iIndex = l.indexOf( "import" );
			if ( iIndex != -1 )
			{
				String[] iStatements = l.split(";");
				for ( String iExpression : iStatements )
				{
					matches = PApplet.match( iExpression, IMPORT_REGEX );
					if ( matches != null && matches.length >= 2 && matches[1] != null )
					{
						String iPackage = matches[1];
						iPackage = iPackage.trim();

						if ( iPackage.indexOf(".*") != -1 ) {
							// de.bezier.tutti.*
							iPackage = iPackage.replace( ".*", "" );
						} else {
							// de.bezier.uno.SingleClass
							iPackage = iPackage.replaceAll( "\\.[^.]+$", "" );
						}
						if ( !importPackages.contains(iPackage) ) // is this a "==" or ".equals()" ?
							importPackages.add( iPackage );
					}
				}
			}
		}
		
		if ( importPackages.size() > 0 )
		{
			File libsExport = new File( bin, "libs" );
			if ( !libsExport.mkdir() )
			{
				Base.showWarning( "Error",
				 				  "Unable to create 'libs' in export folder.",
								  null );
				return false;
			}
		}
		
		for ( String pack : importPackages )
		{
			Library lib = mode.getLibrary( pack );
			if ( lib != null )
			{
				String libPath = lib.getJarPath();
				File libJar = new File( libPath );
				if ( libJar.exists() )
				{
					File libCS = new File( libJar.getParent(), libJar.getName().replace(".jar",".js") );
					//System.out.println( libCS.getPath() );
					if ( libCS.exists() )
					{
						String libCSDest = "libs" + File.separator + libCS.getName();
						File libCSDestFile = new File( bin, libCSDest );
						if ( libCSDestFile.exists() )
						{
							System.out.println( "Duplicate import!" );
						}
						try
						{
							Base.copyFile( libCS,
										   libCSDestFile );
							csImports.add( libCSDest );

						} catch ( Exception se ) {
							se.printStackTrace();
						}
					}
				}
			}
		}
		
		// ------------------------------------------
		// 	GRAB WIDTH, HEIGHT FOR HTML
		// ------------------------------------------

		// get width and height
		int wide = PApplet.DEFAULT_WIDTH;
		int high = PApplet.DEFAULT_HEIGHT;

		// TODO
		// Really scrub comments from code? 
		// Con: larger files, PJS/coffee needs to do it later
		// Pro: being literate as we are in a script language.
		String scrubbed = scrubComments( sketch.getCode(0).getProgram() );
		matches = PApplet.match( scrubbed, SIZE_REGEX );
		
		if ( matches != null ) 
		{
			try 
	 		{
				wide = Integer.parseInt(matches[1]);
				high = Integer.parseInt(matches[2]);
				// renderer

			} catch ( NumberFormatException e ) {
				// if ( ((JavaScriptMode)mode).showSizeWarning ) {
				// 		 				// found a reference to size, but it didn't seem to contain numbers
				//  final String message =
				//    "The size of this applet could not automatically be\n" +
				//    "determined from your code. You'll have to edit the\n" +
				//    "HTML file to set the size of the applet.\n" +
				//    "Use only numeric values (not variables) for the size()\n" +
				//    "command. See the size() reference for an explanation.";
				//  Base.showWarning("Could not find applet size", message, null);
				// // warn only once ..
				// ((JavaScriptMode)mode).showSizeWarning = false;
				//}
			}
			
		}  // else no size() command found, defaults will be used
		
		// ------------------------------------------
		// 	PREP TEMPLATE
		// ------------------------------------------
		
		// final prep and write to template.
		// getTemplateFile() is very important as it looks up and preps
		// any custom templates present in the sketch folder.
		
		File templateFile = getTemplateFile();
		File htmlOutputFile = new File(bin, "index.html");

		Map<String, String> templateFields = new HashMap<String, String>();
		templateFields.put( "width", 		String.valueOf(wide) );
		templateFields.put( "height", 		String.valueOf(high) );
		templateFields.put( "sketch", 		sketch.getName() );
		templateFields.put( "description", 	getSketchDescription() );
		
		// add a handy method to read the generated sketchID
		String scriptFiles = "<script type=\"text/javascript\">" +
						     "function getProcessingSketchID () { return '"+sketchID+"'; }" +
						     "</script>\n";
		
		// add imports if any ...
		for ( String importScript : csImports )
		{
			scriptFiles += "<script type=\"text/javascript\" src=\""+importScript+"\"></script>";
		}

		// main .pde file first
		String sourceFiles = "<a href=\"" + sketch.getName() + ".pde\">" + sketch.getName() + "</a> ";

		// add all other files (both types: .pde and .js)
		if ( sketchFolderFiles != null )
		{
			for ( String s : sketchFolderFiles )
			{
				if ( s.toLowerCase().endsWith(".pde") && !s.equals(sketch.getName()+".pde") )
				{
					sourceFiles += "<a href=\"" + s + "\">" + s.replace(".pde","") + "</a> ";
				}
				else if ( s.toLowerCase().endsWith(".js") )
				{
					sourceFiles += "<a href=\"" + s + "\">" + s + "</a> ";
					scriptFiles += "<script src=\""+ s +"\" type=\"text/javascript\"></script>\n";
				}
				else if ( s.toLowerCase().endsWith(".coffee") )
				{
					sourceFiles += "<a href=\"" + s + "\">" + s + "</a> ";
					scriptFiles += "<script src=\"" + 
										s.replace(".coffee","-compiled.js") +
										"\" type=\"text/javascript\"></script>\n";
				}
			}
		}
		templateFields.put( "source", sourceFiles );
		templateFields.put( "scripts", scriptFiles );
		templateFields.put( "id", sketchID );
		
		templateFields.put( "coffee", coffeeCode );
		templateFields.put( "coffeescripts", "" );
		templateFields.put( "coffeesketch", coffeeSketchName );
		
		// ------------------------------------------
		// 	WRITE / MOVE FILES
		// ------------------------------------------
		
		// need these ..
		String[] needed = new String[]{
			"processing.js"
		};
		
		// process template replace tokens with content
		try
		{
			writeTemplate(templateFile, htmlOutputFile, templateFields);

		} catch (IOException ioe) {
			final String msg = "There was a problem writing the html template " +
							   "to the build folder.";
			Base.showWarning( "A problem occured during the build", msg, ioe );
			return false;
		}

		try {
			for ( String n : needed ) 
			{
				File nf = new File( bin, n );
				if ( !nf.exists() )
				{
					Base.copyFile( 
						sketch.getMode().getContentFile( TEMPLATE_FOLDER_NAME + File.separator + n ), 
						nf );
				}
			}
		} catch (IOException ioe) {
			final String msg = "There was a problem copying one or more files to the " +
								 "build folder. You will have to manually add " + 
								 "processing.js to the build folder before the sketch will run.";
			Base.showWarning( "There was a problem writing to the build folder", msg, ioe );
			//return false; 
		}

		return true;
	}
	
	/** 
	 * Collects the sketch code.
	 *
	 * @param bin the output folder
	 */
	public void preprocess ( File bin ) throws IOException
	{
		// COLLECT .pde FILES INTO ONE,
		// essentially... cat sketchFolder/*.pde > bin/sketchname.pde

		// StringBuffer bigCode = new StringBuffer();
		// String modeExt = mode.getDefaultExtension();
		// for (SketchCode sc : sketch.getCode())
		// {
		// 	if (sc.isExtension(modeExt)) 
		// 	{
		// 		bigCode.append(sc.getProgram());
		// 		bigCode.append("\n");
		// 	}
		// }
		// 
		// if (!bin.exists()) {
		// 	bin.mkdirs();
		// }
		// 
		// File bigFile = new File( bin, sketch.getName() + "." + modeExt );
		// String bigCodeContents = bigCode.toString();
		// Base.saveFile( bigCodeContents, bigFile );
	}
	
	/**
	 *  Find and return the template HTML file to use. This also checks for custom
	 *  templates that might be living in the sketch folder. If such a "template_js"
	 *  folder exists then it's contents will be copied over to "web-export-coffee" and
	 *  it's template.html will be used as template.
	 *
	 *	@return the main template file renamed to index.html
	 */
	private File getTemplateFile ()
	{
		File sketchFolder = sketch.getFolder();
		File customTemplateFolder = new File( sketchFolder, TEMPLATE_FOLDER_NAME );
		if ( customTemplateFolder.exists() && 
			 customTemplateFolder.isDirectory() && 
			 customTemplateFolder.canRead() )
		{
			File exportFolder = new File( sketchFolder, EXPORTED_FOLDER_NAME );

			try {
				//TODO: this is potentially dangerous as it might override files in web-export-coffee 
				Base.copyDir( customTemplateFolder, exportFolder );
				if ( !(new File( exportFolder, TEMPLATE_FILE_NAME )).delete() )
				{
					// ignore?
				}
				return new File( customTemplateFolder, TEMPLATE_FILE_NAME );
			} catch ( Exception e ) {	
				String msg = "";
				Base.showWarning( "There was a problem copying your custom template folder", msg, e );
				return sketch.getMode().getContentFile(
					TEMPLATE_FOLDER_NAME + File.separator + TEMPLATE_FILE_NAME
				);
			}
		}
		else
		{
			return sketch.getMode().getContentFile(
				TEMPLATE_FOLDER_NAME + File.separator + TEMPLATE_FILE_NAME
			);
		}
	 }
	
	/**
	 * Reads in a simple template file, with fields of the form '@@somekey@@'
	 * and replaces each field with the value in the map for 'somekey', writing
	 * the output to the output file.
	 * 
	 * Keys not in the map will be replaced with empty strings.
	 * 
	 * @param template File object mapping to the template
	 * @param output File object handle to the output
	 * @param args template keys, data values to replace them
	 * @throws IOException when there are problems writing to or from the files
	 */
	public static void writeTemplate ( File template, File output, Map<String, String> fields )
	throws IOException 
	{
		BufferedReader reader = PApplet.createReader(template);
		PrintWriter writer = PApplet.createWriter(output);

		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line.indexOf("@@") != -1) {
				StringBuffer sb = new StringBuffer(line);
				int start = 0, end = 0;
				while ((start = sb.indexOf("@@")) != -1) {
					if ((end = sb.indexOf("@@", start+1)) != -1) {
						String value = fields.get(sb.substring(start+2, end));
						sb.replace(start, end+2, value == null ? "" : value );
					} else {
						Base.showWarning( "Problem writing file from template",
										  "The template appears to have an unterminated " +
										  "field. The output may look a little funny.",
										  null );
					}
				}
				line = sb.toString();
			}
			writer.println(line);
		}
		writer.close();
	}
	
	/**
	 * Read the first / head comment from tab one.
	 */
	public String getSketchDescription() {
		// TODO!
		return "";
	}
	
	/**
	 * Remove all comments from source.
	 */
	private String scrubComments ( String commentedSource ) {
		// TODO!
		// single line comments start with '#'
		// multiline comments are wrapped in '###'
		return commentedSource;
	}
}