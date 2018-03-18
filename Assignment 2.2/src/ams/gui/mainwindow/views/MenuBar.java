package ams.gui.mainwindow.views;

import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ams.gui.mainwindow.controllers.AppController;
import ams.gui.mainwindow.controllers.CourseListener;
import ams.gui.mainwindow.controllers.DebugListener;
import ams.gui.mainwindow.controllers.HelpListener;
import ams.gui.mainwindow.controllers.ProgramListener;
import ams.gui.mainwindow.controllers.SortListener;
import ams.model.Course;
import ams.model.Program;

/**
 * Sub component that implements a menu
 * for the GUI
 * 
 * @author timothyboye
 *
 */
@SuppressWarnings("serial")
public class MenuBar extends JMenuBar
{

   // top level menus
   private JMenu fileMenu = new JMenu("File");
   private JMenu programMenu = new JMenu("Program");
   private JMenu courseMenu = new JMenu("Course Functions");
   private JMenu sortMenu = new JMenu("Sort Courses");
   private JMenu helpMenu = new JMenu("Help");
   
   // help menu items
   private JMenuItem readMeMenuItem = new JMenuItem("Read Me file...");
   private JMenuItem instructionsMenuItem = new JMenuItem("How To...");
   private JMenuItem visitorMenuItem = new JMenuItem("Visitor Pattern...");
   // sort menu items
   private JCheckBoxMenuItem noSortMenuItem = new JCheckBoxMenuItem("No Sort");
   private JCheckBoxMenuItem titleSortMenuItem = new JCheckBoxMenuItem("Sort by Title");
   private JCheckBoxMenuItem typeSortMenuItem = new JCheckBoxMenuItem("Sort by Type");
   // file menu items
   private JMenuItem exitMenuItem = new JMenuItem("Exit");
   // program menu items
   private JMenuItem createProgramMenuItem = new JMenuItem("Create New Program...");
   private JMenuItem resetMenuItem = new JMenuItem("Reset Program");
   // course menu items
   private JMenuItem addCourseMenuItem = new JMenuItem("Add new course...");
   private JMenuItem removeCourseMenuItem = new JMenuItem("Remove Courses...");
   private JMenuItem clearSelectedCourseMenuItem = new JMenuItem("Clear selection");
   // main controller
   private AppController controller;
   
   /**
    * Init the menubar
    * @param appController     main controller
    */
   public MenuBar(AppController appController)
   {
      // save the controller
      controller = appController;
      
      // set the top level menu mnemonics
      fileMenu.setMnemonic(KeyEvent.VK_F);
      programMenu.setMnemonic(KeyEvent.VK_P);
      courseMenu.setMnemonic(KeyEvent.VK_C);
      sortMenu.setMnemonic(KeyEvent.VK_S);
      helpMenu.setMnemonic(KeyEvent.VK_H);
      // set the help menu items mnemonics
      readMeMenuItem.setMnemonic(KeyEvent.VK_M);
      instructionsMenuItem.setMnemonic(KeyEvent.VK_W);
      visitorMenuItem.setMnemonic(KeyEvent.VK_V);
      // set the sort menu items mnemonics
      noSortMenuItem.setMnemonic(KeyEvent.VK_O);
      titleSortMenuItem.setMnemonic(KeyEvent.VK_I);
      typeSortMenuItem.setMnemonic(KeyEvent.VK_Y);
      // set the file menu items mnemonics
      exitMenuItem.setMnemonic(KeyEvent.VK_X);
      // set the program menu items mnemonics
      createProgramMenuItem.setMnemonic(KeyEvent.VK_N);
      resetMenuItem.setMnemonic(KeyEvent.VK_T);
      // set the course menu items mnemonics
      addCourseMenuItem.setMnemonic(KeyEvent.VK_A);
      removeCourseMenuItem.setMnemonic(KeyEvent.VK_R);
      clearSelectedCourseMenuItem.setMnemonic(KeyEvent.VK_L);

      // add the top level menus to the menu
      this.add(fileMenu);
      this.add(programMenu);
      this.add(courseMenu);
      this.add(sortMenu);
      this.add(helpMenu);
      
      // add the file menu items to the file menu
      fileMenu.add(exitMenuItem);
      // add the program menu items to the program menu
      programMenu.add(createProgramMenuItem);
      programMenu.add(resetMenuItem);
      // add the course menu items to the course menu
      courseMenu.add(addCourseMenuItem);
      courseMenu.add(removeCourseMenuItem);
      courseMenu.add(clearSelectedCourseMenuItem);
      // add the sort menu items to the sort menu
      sortMenu.add(noSortMenuItem);
      sortMenu.add(titleSortMenuItem);
      sortMenu.add(typeSortMenuItem);
      // add the help menu items to the help menu
      helpMenu.add(readMeMenuItem);
      helpMenu.add(instructionsMenuItem);
      helpMenu.add(visitorMenuItem);
      
      // set the action commands for the program menu items
      createProgramMenuItem.setActionCommand(ProgramListener.CREATE_PROGRAM);
      resetMenuItem.setActionCommand(ProgramListener.RESET_PROGRAM);
      // set the action commands for the course menu items
      addCourseMenuItem.setActionCommand(CourseListener.ADD_COURSE);
      removeCourseMenuItem.setActionCommand(CourseListener.REMOVE_COURSE);
      clearSelectedCourseMenuItem.setActionCommand(CourseListener.CLEAR_SELECTION);
      // set the action commands for the sort menu items
      noSortMenuItem.setActionCommand(SortListener.SORT_MENU_NONE);
      titleSortMenuItem.setActionCommand(SortListener.SORT_MENU_TITLE);
      typeSortMenuItem.setActionCommand(SortListener.SORT_MENU_TYPE);
      // set the action commands for the help menu items
      readMeMenuItem.setActionCommand(HelpListener.READ_ME);
      instructionsMenuItem.setActionCommand(HelpListener.HOW_TO);
      visitorMenuItem.setActionCommand(HelpListener.VISITOR);
      
      // set the action listener for file menu items
      exitMenuItem.addActionListener(controller.getExitListener());
      // set the action listener for program menu items
      createProgramMenuItem.addActionListener(controller.getProgramListener());
      resetMenuItem.addActionListener(controller.getProgramListener());
      // set the action listener for course menu items
      addCourseMenuItem.addActionListener(controller.getCourseListener());
      removeCourseMenuItem.addActionListener(controller.getCourseListener());
      clearSelectedCourseMenuItem.addActionListener(controller.getCourseListener());
      // set the action listener for sort menu items
      noSortMenuItem.addActionListener(controller.getSortListener());
      titleSortMenuItem.addActionListener(controller.getSortListener());
      typeSortMenuItem.addActionListener(controller.getSortListener());
      // set the action listener for help menu items
      readMeMenuItem.addActionListener(controller.getHelpListener());
      instructionsMenuItem.addActionListener(controller.getHelpListener());
      visitorMenuItem.addActionListener(controller.getHelpListener());
      
      
      //////
      // DEBUG FUNCTIONALITY
      //////
      if (controller.isDebugMode())
      {
         this.add(makeDebugMenu());
      }
   }

   /**
    * Creates a debug menu
    * @return     the debug menu
    */
   private JMenu makeDebugMenu()
   {
      //DEBUG MENU ITEMS
      JMenu debugMenu = new JMenu("Debug");
      JMenuItem createDebugMenuItem = new JMenuItem("Create a program");
      JMenuItem createPopulatedDebugMenuItem = new JMenuItem("Create a populated program...");
      
      // make the debug menu
      debugMenu.add(createDebugMenuItem);
      debugMenu.add(createPopulatedDebugMenuItem);
      
      // set the action commands
      createDebugMenuItem.setActionCommand(DebugListener.CREATE);
      createPopulatedDebugMenuItem.setActionCommand(DebugListener.CREATE_POPULATED);
      
      // set the listeners
      createDebugMenuItem.addActionListener(controller.getDebugListener());
      createPopulatedDebugMenuItem.addActionListener(controller.getDebugListener());
      
      return debugMenu;
   }
   
   /**
    * Accepts a program and courses to determine which menu items
    * should be enabled and which should be disabled
    * @param program    the main program object or null
    * @param courses    copy of the courses array or null
    */
   public void modelUpdate(Program program, Course[] courses)
   {
      // Is there a program?
      if (program != null)
      {
         // a program can have courses written, can be overwritten
         // but a new one can't be created
         createProgramMenuItem.setEnabled(false);
         resetMenuItem.setEnabled(true);
         addCourseMenuItem.setEnabled(true);

         
         // are there courses?
         if (courses != null)
         {
            // can remove and sort courses if they exist
            removeCourseMenuItem.setEnabled(true);
            noSortMenuItem.setEnabled(true);
            titleSortMenuItem.setEnabled(true);
            typeSortMenuItem.setEnabled(true);
            
            if (controller.getSelectedCourses().isEmpty())
               clearSelectedCourseMenuItem.setEnabled(false);
            else
               clearSelectedCourseMenuItem.setEnabled(true);
            
            // what sort order is set?
            //    insertion order
            if (controller.getCourseSortOrder() == 0)
            {
               noSortMenuItem.setSelected(true);
               titleSortMenuItem.setSelected(false);
               typeSortMenuItem.setSelected(false);
            }
            //    title order
            else if (controller.getCourseSortOrder() == 1)
            {
               noSortMenuItem.setSelected(false);
               titleSortMenuItem.setSelected(true);
               typeSortMenuItem.setSelected(false);
            }
            //    type order
            else if (controller.getCourseSortOrder() == 2)
            {
               noSortMenuItem.setSelected(false);
               titleSortMenuItem.setSelected(false);
               typeSortMenuItem.setSelected(true);
            }
            
            
         }
         // there are no courses
         else
         {
            // can't remove or sort somthing that doesn't exist
            removeCourseMenuItem.setEnabled(false);
            noSortMenuItem.setEnabled(false);
            titleSortMenuItem.setEnabled(false);
            typeSortMenuItem.setEnabled(false);
            clearSelectedCourseMenuItem.setEnabled(false);
         }
         
         
      }
      // There is no program
      else
      {
         createProgramMenuItem.setEnabled(true);
         resetMenuItem.setEnabled(false);
         addCourseMenuItem.setEnabled(false);
         removeCourseMenuItem.setEnabled(false);
         noSortMenuItem.setEnabled(false);
         titleSortMenuItem.setEnabled(false);
         typeSortMenuItem.setEnabled(false);
         clearSelectedCourseMenuItem.setEnabled(false);
      }
   }
   
   /**
    * Toggles the clear selection menu item
    * @param b
    */
   public void setSelectionClearable(boolean b)
   {
      clearSelectedCourseMenuItem.setEnabled(b);
   }
}
