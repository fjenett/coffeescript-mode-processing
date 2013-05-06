package de.bezier.mode.coffeescript;

import de.bezier.mode.javascript.ServingEditor;

import processing.app.Base;
import processing.app.Toolkit;
import processing.app.EditorState;
import processing.app.Editor;
import processing.app.Mode;
import processing.app.EditorToolbar;
import processing.app.Formatter;
import processing.app.Sketch;
import processing.app.SketchCode;
import processing.app.Settings;
import processing.app.Preferences;
import processing.app.syntax.*;

import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.*;
import javax.swing.text.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

/**
 *	This is the actual editor implementation, one editor is opened per sketch.
 *
 *	@see <a href="http://code.google.com/p/processing/source/browse/trunk/processing/app/src/processing/mode/javascript/ServingEditor.java">processing.mode.javascript.ServingEditor.java</a>
 *	@see <a href="http://code.google.com/p/processing/source/browse/trunk/processing/app/src/processing/app/Editor.java">processing.app.Editor.java</a>
 */
public class CoffeeScriptEditor extends ServingEditor
{	
	CoffeeScriptMode csMode;
	
	protected CoffeeScriptEditor ( Base base, String path, EditorState state, Mode mode ) 
	{	
		super( base, path, state, mode );
		
		processing.mode.java.PdeKeyListener listener = new processing.mode.java.PdeKeyListener( this, textarea );

		csMode = (CoffeeScriptMode)mode;

		checkTabSettings();
	}
	
	// ------------------------------------------
	//  OVERRIDING / EXTENDING EDITOR
	// ------------------------------------------
	
	/**
	 *	Overriding Editor.setCode()
	 *
	 *	Called when editor switches between tabs. Handles different syntax coloring per code types.
	 *
	 *	@see processing.app.Editor#setCode(SketchCode code)
	 */
	protected void setCode ( SketchCode code ) 
	{
		SyntaxDocument document = (SyntaxDocument)code.getDocument();
		super.setCode( code );
		if ( document == null ) 
		{	
			if ( code.getExtension().equals("pde") || 
				 code.getExtension().equals("coffee") )
			{
				document = (SyntaxDocument)code.getDocument();
				document.setTokenMarker( ((CoffeeScriptMode)mode).getCSTokenMarker() );
			}
		}
	}
	
	/**
	 *	Overriding Editor.createToolbar()
	 *
	 *	Called to set the toolbar above the main textarea.
	 *
	 *	@see processing.app.Editor#setCode()
	 */
	public EditorToolbar createToolbar () 
	{
		return new CoffeeScriptToolbar( this, base );
	}

	/**
	 *	Overriding Editor.createFormatter()
	 *
	 *  Called to get the default formatter.
	 *
	 *	@see processing.app.Editor#createFormatter() 
	 */
	public Formatter createFormatter () 
	{ 
		return new CoffeeScriptFormatter();
	}
	
	/**
	 *	Overriding Editor.buildMenu
	 *
	 *	@see processing.app.Editor#buildFileMenu()
	 */
	public JMenu buildFileMenu () 
	{
		JMenuItem exportItem = Toolkit.newJMenuItem("Export", 'E');
		exportItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleExport( true );
			}
		});
		return buildFileMenu(new JMenuItem[] { exportItem });
	}

	/**
	 *	Overriding Editor.buildSketchMenu()
	 *
	 *	@see processing.app.Editor#buildSketchMenu()
	 */
	public JMenu buildSketchMenu () 
	{
		JMenuItem startServerItem = Toolkit.newJMenuItem("Run in Browser", 'R');
		startServerItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					handleStartServer();
				}
			});

		JMenuItem openInBrowserItem = Toolkit.newJMenuItem("Reopen in Browser", 'B');
		openInBrowserItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					handleOpenInBrowser();
				}
			});

		JMenuItem stopServerItem = new JMenuItem("Stop");
		stopServerItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					handleStopServer();
				}
			});

		return buildSketchMenu(new JMenuItem[] {
			startServerItem, 
			openInBrowserItem, 
			stopServerItem
		});
	}
	
	/**
	 *	Overriding Editor.buildModeMenu()
	 *
	 *	@see processing.app.Editor#buildModeMenu()
	 */
	public JMenu buildModeMenu() 
	{
		JMenu menu = new JMenu("CoffeeScript");    
		JMenuItem item;
		
		JMenuItem copyServerAddressItem = new JMenuItem("Copy Server Address");
		copyServerAddressItem.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			handleCopyServerAddress();
		}
		});
		menu.add( copyServerAddressItem );

		JMenuItem setServerPortItem = new JMenuItem("Set Server Port");
		setServerPortItem.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e) {
			handleSetServerPort();
		}
		});
		menu.add( copyServerAddressItem );
		
		menu.addSeparator();

		item = new JMenuItem("Start Custom Template");
		item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				handleCreateCustomTemplate();
			}
		});
		menu.add(item);

		item = new JMenuItem("Show Custom Template");
		item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				handleOpenCustomTemplateFolder();
			}
		});
		menu.add(item);

		return menu;
	}

	/**
	 *	Overriding Editor.buildHelpMenu()
	 *
	 *	@see processing.app.Editor#buildHelpMenu()
	 */
	public JMenu buildHelpMenu () 
	{
		JMenu menu = new JMenu("Help ");
		JMenuItem item;

		// TODO switch to "http://js.processing.org/"?

		item = new JMenuItem("CoffeeScript Language Overview");
		item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Base.openURL("http://coffeescript.org/");
				}
		});
		menu.add(item);
		
		
		item = new JMenuItem("CoffeeScript Mode Home");
		item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Base.openURL("https://github.com/fjenett/coffeescript-mode-processing");
				}
		});
		menu.add(item);

		// item = new JMenuItem("Reference");
		// item.addActionListener(new ActionListener() {
		//   public void actionPerformed(ActionEvent e) {
		//     //TODO get offline reference archive corresponding to the release 
		//     // packaged with this mode see: P.js ticket 1146 "Offline Reference"
		//     Base.openURL("http://processingjs.org/reference");
		//   }
		// });
		// menu.add(item);
		
		// item = Base.newJMenuItemShift("Find in Reference", 'F');
		// item.addActionListener(new ActionListener() {
		//   public void actionPerformed(ActionEvent e) {
		//     handleFindReferenceHACK();
		//   }
		// });
		// menu.add(item);

			// // OSX has its own about menu
			// if (!Base.isMacOS()) {
			// 	menu.addSeparator();
			// 	item = new JMenuItem("About Processing");
			// 	item.addActionListener( new ActionListener() {
			// 		public void actionPerformed(ActionEvent e) {
			// 			base.handleAbout();
			// 		}
			// 	});
			// 	menu.add(item);
			// }

		return menu;
	}

	/**
	 *	Overriding Editor.getCommentPrefix()
	 *
	 *	@see processing.app.Editor#getCommentPrefix()
	 */
	public String getCommentPrefix () 
	{
		return "#";
	}

	/**
	 *	Overriding Editor.internalCloseRunner()
	 *
	 *	@see processing.app.Editor#internalCloseRunner()
	 */
	public void internalCloseRunner ()
	{
	}

	/**
	 *	Overriding Editor.deactivateRun()
	 *
	 *	@see processing.app.Editor#deactivateRun()
	 */
	public void deactivateRun ()
	{
	}
	
	// ---------------------------
	//  HANDLERS
	// ---------------------------
	
	/**
	 *	Call the export method of the sketch and handle the gui stuff
	 */
	private boolean handleExport ( boolean openFolder ) 
	{		
		if ( !handleExportCheckModified() )
		{
			return false;
		}
		else
		{
			toolbar.activate(CoffeeScriptToolbar.EXPORT);
			try 
			{
				boolean success = csMode.handleExport(sketch);
				if ( success && openFolder ) 
				{
					File exportFolder = getExportFolder();
					Base.openFolder( exportFolder );

					statusNotice("Finished exporting.");
				} else if ( !success ) { 
					// error message already displayed by handleExport
					return false;
				}
			} catch (Exception e) {
				statusError(e);
				toolbar.deactivate(CoffeeScriptToolbar.EXPORT);
				return false;
			}
			toolbar.deactivate(CoffeeScriptToolbar.EXPORT);
		}
		return true;
	}
	
	/**
	 *	
	 */
	public void handleStartServer () 
	{
		statusEmpty();
		
		if ( !startServer(getExportFolder()) )
		{
			if ( !handleExport( false ) ) return;
			
			toolbar.activate(CoffeeScriptToolbar.RUN);
		}
		
		// wait for callback from server ..
	}
	
	/**
	 *	
	 */
	public void handleStopServer () 
	{
		stopServer();

		toolbar.deactivate(CoffeeScriptToolbar.RUN);	
	}
	
	/**
	 *	
	 */
	private void handleSetServerPort () 
	{	
		statusEmpty();

		boolean wasRunning = serverRunning();
		if ( wasRunning ) {
			statusNotice("Server was running, changing the port requires a restart.");
			stopServer();
		}

		setServerPort();
		//saveSketchSettings();

		if ( wasRunning ) {
			startServer( getExportFolder() );
		}
	}
	
	/**
	 *	
	 */
	private void handleCopyServerAddress () 
	{
		String address = getServerAddress();

		if ( address != null )
		{
			java.awt.datatransfer.StringSelection stringSelection = 
				new java.awt.datatransfer.StringSelection( address );
				java.awt.datatransfer.Clipboard clipboard = 
				java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents( stringSelection, null );
		}	
	}
	
	/**
	 *	
	 */
	private void handleOpenInBrowser ()
	{
		openBrowserForServer();
	}
	
	/**
	 *	Comes from Editor.
	 */
	public void handleImportLibrary (String item) 
	{
		Base.showWarning("CoffeeScript doesn't support libraries",
									 "Libraries are not supported. Import statements are " +
									 "ignored, and code relying on them will break.",
									 null);
	}
	
	/**
	 *	
	 */
	private boolean handleExportCheckModified () 
	{
		if (sketch.isModified()) {
			Object[] options = { "OK", "Cancel" };
			int result = JOptionPane.showOptionDialog( this,
													"Save changes before export?",
													"Save",
													JOptionPane.OK_CANCEL_OPTION,
													JOptionPane.QUESTION_MESSAGE,
													null,
													options,
													options[0] );

			if (result == JOptionPane.OK_OPTION) 
			{
				handleSave(true);
			} 
			else 
			{
				statusNotice("Export canceled, changes must first be saved.");
				return false;
			}
		}
		return true;
	}

	/**
	 *	
	 */
	public void handleSave ()
	{
		toolbar.activate(CoffeeScriptToolbar.SAVE);
		super.handleSave(true);
		toolbar.deactivate(CoffeeScriptToolbar.SAVE);

		checkTabSettings();
	}

	/**
	 *	
	 */
	public boolean handleSave ( boolean immediately )
	{
		if (sketch.isUntitled()) 
		{
			return handleSaveAs();
			// need to get the name, user might also cancel here

		} 
		else if (immediately) 
		{
			handleSave();
	 		statusEmpty();
			if ( serverRunning() ) handleStartServer();
		} 
		else 
		{
			SwingUtilities.invokeLater(new Runnable() 
			{
				public void run() 
				{
					handleSave();
					statusEmpty();
					if ( serverRunning() ) handleStartServer();
				}
			});
		}
		return true;
	}

	/**
	 *
	 */
	private void checkTabSettings ()
	{
		File sketchProps = getSketchPropertiesFile();
		Settings settings;

		try {
			settings = new Settings(sketchProps);
		} catch ( IOException ioe ) {
			ioe.printStackTrace();
			return;
		}
		if ( settings == null )
		{
			statusError( "Unable to create sketch properties file!" );
			return;
		}

		String sketchTabs = settings.get( "coffee.editor.tabs.size" );
		String userTabs = Preferences.get( "editor.tabs.size" );

		if ( sketchTabs != null ) 
		{
			if ( !sketchTabs.equals( userTabs ) )
			{
				// TODO: make this an alert?
				// how can it be fixed?
				// System.err.println( "The current sketch has not the same tab setting as your editor" );
			}
		}
		else
		{
			settings.set( "mode", "de.bezier.mode.coffeescript.CoffeeScriptMode" );
			settings.set( "coffee.editor.tabs.size", userTabs );
			settings.save();
		}
	}

	/**
	 *	
	 */
	private void handleCreateCustomTemplate ()
	{
		Sketch sketch = getSketch();

		File ajs = sketch.getMode().getContentFile( CoffeeScriptBuild.TEMPLATE_FOLDER_NAME );

		File tjs = getCustomTemplateFolder();

		if ( !tjs.exists() )
		{
			try {
				Base.copyDir( ajs, tjs );
				statusNotice( "Default template copied." );
				Base.openFolder( tjs );
			} catch ( java.io.IOException ioe ) {
				Base.showWarning("Copy default template folder", 
					"Something went wrong when copying the template folder.", ioe);
			}
		}
		else
			statusError(String.format(
				"You need to remove the current \"%s\" folder from the sketch.",
				CoffeeScriptBuild.TEMPLATE_FOLDER_NAME
			));
	}

	/**
	 *	
	 */
	private void handleOpenCustomTemplateFolder ()
	{
		File tjs = getCustomTemplateFolder();
		if ( tjs.exists() )
		{
			Base.openFolder( tjs );
		}
		else
		{
			// TODO: promt to create one?
			statusNotice( "You have no custom template with this sketch. Create one from the menu!" );
		}
	}

	// ------------------------------------------
	//  SERVER CALLBACKS
	// ------------------------------------------

	/**
	 *	
	 */
	public void serverStarted ()
	{
		super.serverStarted();

		if ( !handleExport( false ) ) return;
		
		toolbar.activate(CoffeeScriptToolbar.RUN);
	}

	// ------------------------------------------
	//  OTHER STUFF
	// ------------------------------------------

	/**
	 *	
	 */
	protected File getExportFolder ()
	{
		return new File( getSketch().getFolder(),
						 CoffeeScriptBuild.EXPORTED_FOLDER_NAME );
	}

	/**
	 *	
	 */
	protected File getCustomTemplateFolder ()
	{
		return new File( getSketch().getFolder(),
		 				 CoffeeScriptBuild.TEMPLATE_FOLDER_NAME );
	}
}