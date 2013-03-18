package de.bezier.mode.coffeescript;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.HashMap;

import de.bezier.mode.javascript.JavaScriptMode;

import processing.app.Base;
import processing.app.Editor;
import processing.app.EditorState;
import processing.app.Sketch;
import processing.app.SketchException;

import processing.app.syntax.PdeKeywords;
import processing.app.syntax.TokenMarker;

import processing.core.PApplet;

import de.bezier.mode.coffeescript.syntax.CSTokenMarker;

/**
 *	CoffeeScript mode for Processing IDE 2.0alpha5 or later.
 *
 *	This heavily builds upon JavaScript mode, you might want to 
 *	check that out as well: <a href="http://code.google.com/p/processing/source/browse/trunk/processing/app/src/processing/mode/javascript/JavaScriptMode.java">JavaScriptMode</a>
 *
 *	Coffeescript language is at <a href="http://coffeescript.org/">coffeescript.org</a>
 */
public class CoffeeScriptMode extends JavaScriptMode
{
	private CoffeeScriptEditor csEditor;
	private CSTokenMarker coffeeTokenMarker;
	
	/**
	 *	Constructor called by Processing IDE to start this mode.
	 */
	public CoffeeScriptMode ( Base base, File folder )
	{
		super( base, folder );
		
		coffeeTokenMarker = new CSTokenMarker();
		
		// try {
		// 	loadAdditionalKeywords( 
		// 		new File( base.getContentFile("modes/java"), "keywords.txt" ),
		// 		coffeeTokenMarker
		// 	);
		// 	loadAdditionalKeywords( 
		// 		new File( base.getContentFile("modes/javascript"), "keywords.txt" ),
		// 		coffeeTokenMarker
		// 	);
		// 	loadAdditionalKeywords( 
		// 		new File( folder, "keywords.txt" ),
		// 		coffeeTokenMarker
		// 	);
		// } catch (IOException e) {
	 //    	Base.showError( "Problem loading keywords",
	 //                   		"Could not load keywords.txt, please re-install CoffeeScript mode.", e);
		// }
	}

	/**
	 *	Called to get the keyword files to be loaded
	 */
	public File[] getKeywordFiles ()
	{
		return new File[]{
			Base.getContentFile("modes/java/keywords.txt"),
			Base.getContentFile("modes/javascript/keywords.txt"),
			new File( folder, "keywords.txt" )
		};
	}
	
	/**
	 *	Called from editor for CS code documents
	 */
	public TokenMarker getCSTokenMarker () 
	{
		if ( coffeeTokenMarker == null )
			coffeeTokenMarker = new CSTokenMarker();
		return coffeeTokenMarker;
	}
	
	/**
	 *	Called by PDE
	 */
	public Editor createEditor ( Base base, String path, EditorState state )
	{
		// if ( path != null && !path.endsWith(".coffee") ) 
		// {
		// 	String cPath = path.replace(".pde", ".coffee");
		// 	File cFile = new File( cPath );
		// 	if ( !cFile.exists() ) 
		// 	{
		// 		try {
		// 			if ( !cFile.createNewFile() ) 
		// 			{
		// 				System.err.println( "CoffeeScriptMode: " + 
		// 									"unable to create .coffee file for .pde file at:\n" + 
		// 									cPath );
		// 			}
		// 		} catch ( java.io.IOException ioe ) {
		// 			ioe.printStackTrace();
		// 		}
		// 	}
		// 	path = cPath;
		// }
	 	csEditor = new CoffeeScriptEditor( base, path, state, this );
		return csEditor;
	}
	
	/**
	 *	Called by PDE
	 */
	public String getTitle ()
	{
		return "CoffeeScript";
	}
	
	/**
	 *	Called by PDE. Return list of folders at root of examples dir
	 */
	public File[] getExampleCategoryFolders ()
	{
		File[] csExamples = examplesFolder.listFiles( 
			new java.io.FileFilter()
			{
				public boolean accept (File f) 
				{
					return f.isDirectory();
				}
			}
		);
		java.util.Arrays.sort(csExamples);
		
		return csExamples;
	}
	
	/**
	 *	Called by PDE. Return default extension
	 */
	public String getDefaultExtension () 
	{
		return "pde";
	}

	/**
	 *	Called by PDE. Return allowed file extensions
	 */
	public String[] getExtensions () 
	{
		return new String[] { "pde", "coffee", "js" };
	}
	
	/**
	 *	Called by PDE. Return list of files to ignore when saving as
	 */
	public String[] getIgnorable () 
	{
		return new String[] {
			CoffeeScriptBuild.EXPORTED_FOLDER_NAME
		};
	}
	
	/**
	 *	Called from JS mode.
	 */
	public boolean handleExport( Sketch sketch ) throws IOException, SketchException
	{
		CoffeeScriptBuild build = new CoffeeScriptBuild( sketch );
		return build.export();
	}
}