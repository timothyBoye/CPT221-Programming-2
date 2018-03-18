package ams.gui.mainwindow.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ams.gui.mainwindow.controllers.comparators.TitleSort;
import ams.gui.mainwindow.controllers.comparators.TypeSort;
import ams.gui.mainwindow.views.AppFrame;
import ams.model.Course;
import ams.model.Program;
import ams.model.exception.ProgramException;
import ams.model.facade.AMSModel;

/**
 * The AppController class provides all access to
 * the model and stores all model related data.
 * This includes updating the GUI when model changes
 * occur and storing course sorting and selections
 * 
 * @author timothyboye
 *
 */
public class AppController
{

   // Main Program components
   private final boolean DEBUG_MODE;
   private final AppFrame FRAME;
   private final AMSModel MODEL;

   // Sort
   /** 0 = insertion order, 1 = title sort, 2 = type sort **/
   private int sortOrder = 0;

   // user selected courses list
   private List<String> selectedCourses = new ArrayList<String>();

   // Listeners
   private ExitListener exitLis = new ExitListener(this);
   private DebugListener debugLis = new DebugListener(this);
   private HelpListener helpLis = new HelpListener(this);
   private ProgramListener programLis = new ProgramListener(this);
   private CourseListener courseLis = new CourseListener(this);
   private SortListener sortLis = new SortListener(this);

   /**
    * Initialise the AppController with a model, program mode
    * and a connection to the main view.
    * 
    * @param _appFrame     Main view window
    * @param _debug        Program mode
    * @param _model        The AMS model
    */
   public AppController(AppFrame _appFrame, Boolean _debug, AMSModel _model)
   {
      DEBUG_MODE = _debug;
      FRAME = _appFrame;
      MODEL = _model;
   }

   /**
    * When a controller makes any changes to the model this
    * method should be called to send fresh data to the views
    * to be displayed.
    */
   public void updateGUI()
   {
      // Check there is a program then send program and course data to the views
      if (this.getProgram() != null)
      {
         // Get courses and program
         Course[] courses = this.getCourses();
         Program program = this.getProgram();

         // send the status bar the course counts and program title
         FRAME.getStatusBar().setProgramDetails(MODEL.countCoreCourses(),
                                                MODEL.countElectiveCourses(),
                                                program.getTitle());
         // send the menu and toolbar the program and courses 
         //    so they know which items/buttons to enable
         FRAME.getMenu().modelUpdate(program, courses);
         FRAME.getToolbar().modelUpdate(program, courses);

         // check there are courses and then sort them if there are
         if (courses != null)
         {
            // get the sort order and sort our array
            if (this.getCourseSortOrder() == 1)
               Arrays.sort(courses, new TitleSort());
            else if (this.getCourseSortOrder() == 2)
               Arrays.sort(courses, new TypeSort());
         }

         // send the courses array to the grid view for displaying
         FRAME.getGrid().modelUpdate(courses);
      }
      // if there is no program, send nulls to the views
      else
      {
         FRAME.getStatusBar().showProgramDetails(false);
         FRAME.getMenu().modelUpdate(null, null);
         FRAME.getToolbar().modelUpdate(null, null);
         FRAME.getGrid().modelUpdate(null);
      }

      // revalidate the frame.
      FRAME.invalidate();
      FRAME.validate();
   }

   
   
   // ///
   // Application Objects
   // ///

   /**
    * Returns the current program mode, normal (false) or
    * debug mode (true). Primarily used to show the debug menu.
    * @return     debug mode (true) / normal mode (false)
    */
   public boolean isDebugMode()
   {
      return DEBUG_MODE;
   }

   /**
    * Returns the main view frame
    * @return     main window
    */
   public AppFrame getFrame()
   {
      return FRAME;
   }
   
   /**
    * Sets a user readable message in the status bar
    * @param string     message to display (don't pad)
    */
   public void setStatusMessage(String string)
   {
      System.out.println(string);
      FRAME.getStatusBar().setMsg(string);
   }

   
   
   // ///
   // Course functions
   // ///

   /**
    * Returns a copy of the model's courses list
    * @return     Copied Course array or null
    */
   public Course[] getCourses()
   {
      if (MODEL.getAllCourses() == null)
         return null;
      else
         return MODEL.getAllCourses().clone();
   }

   /**
    * Checks if a course exists with the provided course code
    * and returns it if it does
    * @param code    course code to search for
    * @return        Course object that is represented by the provided code
    */
   public Course getCourse(String code)
   {
      return MODEL.getCourse(code);
   }

   /**
    * Adds a provided program to the model if the model 
    * doesn't throw any exceptions.
    * @param course                 A course object
    * @throws ProgramException      thrown by model when course object is invalid
    */
   public void addCourse(Course course) throws ProgramException
   {
      MODEL.addCourse(course);
   }

   /**
    * Removes an object from the model represented by the course code
    * provided.
    * @param course              the course code of the course to be removed
    * @throws ProgramException   thrown by the model when the course can't be removed
    */
   public void removeCourse(String course) throws ProgramException
   {
      MODEL.removeCourse(course);
   }

   
   
   // ///
   // Program functions
   // ///

   /**
    * Gets the program from the model
    * @return     Program object
    */
   public Program getProgram()
   {
      return MODEL.getProgram();
   }

   /**
    * Adds or overwrites the program in the model
    * @param program    Program object
    */
   public void addProgram(Program program)
   {
      MODEL.addProgram(program);
   }
   
   
   
   // ///
   // GUI Listeners
   // ///

   /**
    * Single instance of an ExitListener for exit functions
    * @return     ExitListener
    */
   public ExitListener getExitListener()
   {
      return exitLis;
   }

   /**
    * Single instance of an ProgramListener for program functions
    * @return     ProgramListener
    */
   public ProgramListener getProgramListener()
   {
      return programLis;
   }

   /**
    * Single instance of an HelpListener for help functions
    * @return     HelpListener
    */
   public HelpListener getHelpListener()
   {
      return helpLis;
   }

   /**
    * Single instance of an DebugListener for debug functions
    * @return     DebugListener
    */
   public DebugListener getDebugListener()
   {
      return debugLis;
   }

   /**
    * Single instance of an CourseListener for course functions
    * @return     CourseListener
    */
   public CourseListener getCourseListener()
   {
      return courseLis;
   }

   /**
    * Single instance of an SortListener for sorting functions
    * @return     SortListener
    */
   public SortListener getSortListener()
   {
      return sortLis;
   }

   
   
   // ///
   // Sort Courses in the grid functions
   // ///

   /**
    * Allows the changing of the sort order
    * @param sort    0 = insertion sort order
    *                1 = title sort order
    *                2 = type sort order
    */
   public void setCourseSortOrder(int sort)
   {
      sortOrder = sort;
   }

   /**
    * provides the current sort order
    * @return    0 = insertion sort order
    *            1 = title sort order
    *            2 = type sort order
    */
   public int getCourseSortOrder()
   {
      return sortOrder;
   }

   
   
   // ///
   // Select Courses in the grid functions
   // ///

   /**
    * Toggles a course cell as selected or not selected
    * @param code    course code of cell that was clicked
    */
   public void toggleSelectedCourse(String code)
   {
      // check if the course is already selected
      //    then set it selected or not selected.
      if (selectedCourses.contains(code))
      {
         selectedCourses.remove(code);
         FRAME.getGrid().toggleSelection(code, false);
      }
      else
      {
         selectedCourses.add(code);
         FRAME.getGrid().toggleSelection(code, true);
      }
      
      // Check if the list still contains courses
      //    Then set the clear selection button
      if (selectedCourses.isEmpty())
      {
         FRAME.getToolbar().setSelectionClearable(false);
         FRAME.getMenu().setSelectionClearable(false);
      }
      else
      {
         FRAME.getToolbar().setSelectionClearable(true);
         FRAME.getMenu().setSelectionClearable(true);         
      }
   }

   /**
    * Checks if the course represented by the provided code has
    * been selected by the user and rturns true if it has.
    * @param code      course code
    * @return          returns true if the code is in the selections
    */
   public boolean selectedCoursesContains(String code)
   {
      return selectedCourses.contains(code);
   }

   /**
    * Returns a list of all the courses currently selected
    * @return     list of course codes
    */
   public List<String> getSelectedCourses()
   {
      return selectedCourses;
   }

   /**
    * removes all courses from the selection
    */
   public void resetSelectedCourses()
   {
      selectedCourses.removeAll(selectedCourses);
      this.setStatusMessage("Selection(s) cleared");
   }

}
