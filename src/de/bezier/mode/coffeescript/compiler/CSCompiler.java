package de.bezier.mode.coffeescript.compiler;

import java.io.*;
import org.mozilla.javascript.*;

/**
 *	This is a Rhino based compiler for .coffee to .js conversion.
 *
 *	These provided help:
 *	ScriptEngine in Java 1.6:
 *	https://github.com/guilleiguaran/coffeescript-java/blob/master/CoffeeScript.java
 *	Rhino based:
 *	https://github.com/yeungda/jcoffeescript/blob/master/src/main/java/org/jcoffeescript/JCoffeeScriptCompiler.java
 *	https://github.com/talios/coffee-maven-plugin/blob/develop/src/main/java/com/theoryinpractise/coffeescript/CoffeeScriptCompiler.java
 *
 */
public class CSCompiler extends Thread
{
	private Scriptable globalScope;
	private File coffeeCompiler, sourceFile, targetFile;
	private String source, result;
	
	/**
	 *	Takes coffee-script.js, a sourceFile and a target file.
	 *
	 *	@return the compiled js code
	 */
	public static String compile ( File coffeeCompiler, File sourceFile, File targetFile )
	{
		CSCompiler compiler = new CSCompiler();
		compiler.setCompiler( coffeeCompiler );
		compiler.setSource( sourceFile );
		compiler.setTarget( targetFile );
		compiler.init();
		compiler.run();
		return compiler.getResult();
	}
	
	/**
	 *	Takes coffee-script.js, source code and a target file.
	 *
	 *	@return the compiled js code
	 */
	public static String compile ( File coffeeCompiler, String source, File targetFile )
	{
		CSCompiler compiler = new CSCompiler();
		compiler.setCompiler( coffeeCompiler );
		compiler.setSource( source );
		compiler.setTarget( targetFile );
		compiler.init();
		compiler.run();
		return compiler.getResult();
	}
	
	CSCompiler ()
	{
	}
	
	public void setSource ( String s )
	{
		source = s;
	}

	public void setSource ( File s )
	{	
		FileInputStream is = null;
		Reader reader = null;
		
		sourceFile = s;
		source = "";
		
		try {
			is = new FileInputStream( sourceFile );
			reader = new InputStreamReader( is, "UTF-8" );
			BufferedReader br = new BufferedReader( reader );
			String line = "";
			while ( (line = br.readLine()) != null ) 
			{
				source = source + line + "\n";
			}
			source += "\n";
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
		//System.out.println( source );
	}
	
	public void setTarget ( File t )
	{
		targetFile = t;
	}

	public void setCompiler ( File c )
	{
		coffeeCompiler = c;
	}
	
	public String getResult ()
	{
		return result;
	}
	
	private void init ()
	{
		Context ctx = Context.enter();
		FileInputStream is = null;
		Reader reader = null;
		
		ctx.setGeneratingDebug( true );
		ctx.setOptimizationLevel( -1 );
		ctx.setLanguageVersion( Context.VERSION_1_5 );
		
		globalScope = ctx.initStandardObjects();
		
		try {
			is = new FileInputStream( coffeeCompiler );
			reader = new InputStreamReader( is, "UTF-8" );
			ctx.evaluateReader( globalScope, reader, "coffee-script.js", 0, null );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			Context.exit();
			try {
				if ( reader != null ) reader.close();
				if ( is != null ) is.close();
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
	}
	
	public void run ()
	{
		Context context = Context.enter();
		String compiledSource = null;
		
		result = null;
		
		try {
			Scriptable compileScope = context.newObject( globalScope );
		    compileScope.setParentScope( globalScope );
		    compileScope.put( "csSource", compileScope, source );
		    try {
				compiledSource = (String)context.evaluateString(
					compileScope, 
					"try { CoffeeScript.compile( csSource, {bare: true} ); } catch ( e ) { throw e; }",
					"<CoffeeScriptModeProcessing>", 0, null);
					
				result = compiledSource;
			} 
			catch ( JavaScriptException e ) 
			{
				e.printStackTrace();
			}
		} 
		finally 
		{
			Context.exit();
		}
		
		if ( compiledSource != null && targetFile != null )
		{
			try {
				PrintWriter writer = new PrintWriter( targetFile, "UTF-8" );
				writer.print( compiledSource );
				writer.flush();
				writer.close();
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
	}
}