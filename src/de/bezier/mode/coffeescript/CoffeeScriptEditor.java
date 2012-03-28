package de.bezier.mode.coffeescript;

import processing.mode.javascript.ServingEditor;

import processing.app.Base;
import processing.app.EditorState;
import processing.app.Editor;
import processing.app.Mode;
import processing.app.EditorToolbar;
import processing.app.Formatter;
import processing.app.Sketch;

import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoffeeScriptEditor extends ServingEditor
{	
	CoffeeScriptMode csMode;
	
	protected CoffeeScriptEditor ( Base base, String path, EditorState state, Mode mode ) 
	{	
		super( base, path, state, mode );
		
		processing.mode.java.PdeKeyListener listener = new processing.mode.java.PdeKeyListener( this, textarea );

		csMode = (CoffeeScriptMode) mode;
	}
	
	// -------- extending Editor ----------
	
	public EditorToolbar createToolbar () 
	{
		return new CoffeeScriptToolbar( this, base );
	}

	public Formatter createFormatter () 
	{ 
		return new CoffeeScriptFormatter();
	}
	
	public JMenu buildFileMenu () 
	{
	  JMenuItem exportItem = Base.newJMenuItem("Export", 'E');
	  exportItem.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	      handleExport( true );
	    }
	  });
	  return buildFileMenu(new JMenuItem[] { exportItem });
	}

	public JMenu buildSketchMenu () 
	{
		JMenuItem startServerItem = Base.newJMenuItem("Start Server", 'R');
		startServerItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		      handleStartServer();
		    }
		  });

		JMenuItem openInBrowserItem = Base.newJMenuItem("Reopen in Browser", 'B');
		openInBrowserItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		      handleOpenInBrowser();
		    }
		  });

		JMenuItem stopServerItem = new JMenuItem("Stop Server");
		stopServerItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		      handleStopServer();
		    }
		  });

		JMenuItem copyServerAddressItem = new JMenuItem("Copy Server Address");
		copyServerAddressItem.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			handleCopyServerAddress();
		}
		});
		// copyServerAddressItem.getInputMap().put(
		// 	javax.swing.KeyStroke.getKeyStroke('C', java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.META_MASK ),
		// 	new AbstractAction () {
		// 		public void actionPerformed ( ActionEvent e ) {
		// 			handleCopyServerAddress();
		// 		}
		// 	}
		// );

		JMenuItem setServerPortItem = new JMenuItem("Set Server Port");
		setServerPortItem.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e) {
			handleSetServerPort();
		}
		});

		return buildSketchMenu(new JMenuItem[] {
		startServerItem, openInBrowserItem, stopServerItem, 
		copyServerAddressItem, setServerPortItem
		});
	}
	
	public JMenu buildModeMenu() 
	{
	    JMenu menu = new JMenu("CoffeeScript");    
	    JMenuItem item;

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
		// 
		// item = Base.newJMenuItemShift("Find in Reference", 'F');
		// item.addActionListener(new ActionListener() {
		//   public void actionPerformed(ActionEvent e) {
		//     handleFindReferenceHACK();
		//   }
		// });
		// menu.add(item);

	    // OSX has its own about menu
	    if (!Base.isMacOS()) {
	      menu.addSeparator();
	      item = new JMenuItem("About Processing");
	      item.addActionListener( new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          base.handleAbout();
	        }
	      });
	      menu.add(item);
	    }

	    return menu;
	}
	
	public String getCommentPrefix () 
	{
		return "#";
	}
	
	public void internalCloseRunner ()
	{
	}
	
	public void deactivateRun ()
	{
	}
	
	// -------- handlers ----------
	
	/**
	 * Call the export method of the sketch and handle the gui stuff
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
	
	public void handleStopServer () 
	{
		stopServer();

		toolbar.deactivate(CoffeeScriptToolbar.RUN);	
	}
	
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
	
	private boolean handleExportCheckModified () 
	{
	  if (sketch.isModified()) {
	    Object[] options = { "OK", "Cancel" };
	    int result = JOptionPane.showOptionDialog(this,
	                                              "Save changes before export?",
	                                              "Save",
	                                              JOptionPane.OK_CANCEL_OPTION,
	                                              JOptionPane.QUESTION_MESSAGE,
	                                              null,
	                                              options,
	                                              options[0]);

	    if (result == JOptionPane.OK_OPTION) {
	      handleSaveRequest(true);

	    } else {
	      statusNotice("Export canceled, changes must first be saved.");
	      return false;
	    }
	  }
	  return true;
	}
	
	/**
	 *  Changed from Editor.java to automaticaly export and
	 *  handle the server when it's running. Normal save ops otherwise.
	 */
	public boolean handleSaveRequest ( boolean immediately )
	{
		if (untitled) {
			return handleSaveAs();
			// need to get the name, user might also cancel here

		} else if (immediately) {
			handleSave();
	 		statusEmpty();
	 		handleStartServer();
		} else {
			SwingUtilities.invokeLater(new Runnable() {
			    public void run() {
			    	handleSave();
					statusEmpty();
			 		handleStartServer();
			    }
			  });
		}
		return true;
	}
	
	
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

   // -------- server callbacks -------

  public void serverStarted ()
  {
  		super.serverStarted();

		if ( !handleExport( false ) ) return;
		toolbar.activate(CoffeeScriptToolbar.RUN);
  }

	// -------- other stuff ----------
	
	protected File getExportFolder ()
	{
	  	return new File( getSketch().getFolder(),
		 				 CoffeeScriptBuild.EXPORTED_FOLDER_NAME );
	}
	
	protected File getCustomTemplateFolder ()
	{
	  	return new File( getSketch().getFolder(),
		 				 CoffeeScriptBuild.TEMPLATE_FOLDER_NAME );
	}
}