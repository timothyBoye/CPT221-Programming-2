package ams.gui.mainwindow.views;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import ams.gui.mainwindow.controllers.AppController;
import ams.model.facade.AMSModel;

/**
 * Main application window / view
 * 
 * @author timothyboye
 *
 */
@SuppressWarnings("serial")
public class AppFrame extends JFrame
{
   // Application Controller
   private AppController controller;
   
   // Window frame titles
   private static final String APPLICATION_TITLE = "Academic Management System";
   private static final String DEBUG_APPLICATION_TITLE = "Academic Management System - DEBUG MODE";
   
   // default window dimensions
   private static final int WINDOW_WIDTH = 800;
   private static final int WINDOW_HEIGHT = 600;
   
   // Components
   private MenuBar menu;
   private Toolbar toolbar;
   private CourseGrid grid;
   private StatusBar status;
   private Container pane = this.getContentPane();
   
   /**
    * Init the main window
    * @param _debug     program mode
    * @param _model     program model
    */
   public AppFrame(Boolean _debug, AMSModel _model)
   {
      // create a new main controller
      controller = new AppController(this, _debug, _model);
      
      // Set title and window size
      this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      // Set close operation
      this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      this.addWindowListener(controller.getExitListener());
      
      // check the program mode and set the relavent title
      if (controller.isDebugMode())
      {
         this.setTitle(DEBUG_APPLICATION_TITLE);
      }
      else
      {
         this.setTitle(APPLICATION_TITLE);
      }
      
      // create the sub views
      menu = new MenuBar(controller);
      toolbar = new Toolbar(controller);
      grid = new CourseGrid(controller);
      status = new StatusBar();

      // Setup the content pane layout
      pane.setLayout(new BorderLayout());
      
      // add the sub views to the pane
      this.setJMenuBar(menu);
      pane.add(toolbar, BorderLayout.NORTH);
      pane.add(grid, BorderLayout.CENTER);
      pane.add(status, BorderLayout.SOUTH);
      
      // call update for the first time to get inital program etc
      controller.updateGUI();
   }

   /**
    * Returns the status bar
    * @return
    */
   public StatusBar getStatusBar()
   {
      return status;
   }

   /**
    * returns the main menu
    * @return
    */
   public MenuBar getMenu()
   {
      return menu;
   }

   /**
    * returns the course grid panel
    * @return
    */
   public CourseGrid getGrid()
   {
      return grid;
   }
   
   /**
    * returns the toolbar
    * @return
    */
   public Toolbar getToolbar()
   {
      return toolbar;
   }
}
