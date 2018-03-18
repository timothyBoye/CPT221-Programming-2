package ams.gui.mainwindow.controllers;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import ams.gui.dialogs.views.AddCourseDialog;
import ams.gui.dialogs.views.RemoveCourseDialog;
import ams.model.CoreCourse;
import ams.model.Course;
import ams.model.ElectiveCourse;
import ams.model.exception.AMSException;
import ams.model.exception.ProgramException;

/**
 * Provides all functionality related to adding and removing
 * courses and extends AbstractAction so the
 * GUI can call the functions when needed.
 * 
 * @author timothyboye
 *
 */
@SuppressWarnings("serial")
public class CourseListener extends AbstractAction
{
   // Stores the main parent window object that called the listener
   // which provides access to the model and the GUI in an OO type way
   private AppController controller;

   //Action Commands used in this controller
   public static final String ADD_COURSE = "add";
   public static final String REMOVE_COURSE = "remove";
   public static final String CLEAR_SELECTION = "clear";
   
   /**
    * Initialises the Listener class and stores the caller for
    * later use.
    * @param _caller    Main window the listener belongs to
    */
   public CourseListener(AppController _caller)
   {
      controller = _caller;
   }

   /**
    * Invoked when an action occurs (button pressed etc)
    * and checks the Action Command passed to it to redirect
    * the call to a helper method for the right functionality.
    * 
    * @param a    Information on the event that occured 
    *             in particular we need the action command
    *             attached to the calling object.
    */
   @Override
   public void actionPerformed(ActionEvent a)
   {
      // get the action command
      String action = a.getActionCommand();

      // redirect to the relavent helper method
      if (action.equals(ADD_COURSE))
      {
         controller.resetSelectedCourses();
         this.addCourse();
      }
      else if (action.equals(REMOVE_COURSE))
      {
         this.removeCourses();
         controller.resetSelectedCourses();
      }
      else if (action.equals(CLEAR_SELECTION))
      {
         controller.resetSelectedCourses();
      }

      // tell the gui to check the model and update
      controller.updateGUI();
   }


   /**
    * Removes selected courses or provides 
    * a dialog to pick courses to remove 
    * if none are selected already.
    */
   private void removeCourses()
   {
      try
      {
         // List of courses to remove from the model
         //    initialised to the list of all currently selected 
         //    courses in the main course grid.
         List<String> selected = controller.getSelectedCourses();

         // if there aren't any courses selected in the grid provide
         // a dialog to select the courses to remove from a list.
         if (selected.isEmpty())
         {
            // create the remove dialog
            RemoveCourseDialog dialog = new RemoveCourseDialog();
            // get a list of all courses
            Course[] courses = controller.getCourses();
            String[] courseCodes = new String[courses.length];
            for (int i = 0; i < courses.length; i++)
            {
               courseCodes[i] = courses[i].getCode();
            }
            // pass the courses to the dialog and show it
            int remove = dialog.showDialog(controller.getFrame(), courseCodes);
            
            // check if ok was pressed then pull all selected courses from the list
            if (remove == JOptionPane.OK_OPTION)
            {
               selected = dialog.getSelectedCourses();
            }
            // if ok wasn't pressed no courses are to be removed, exit.
            else
            {
               controller.setStatusMessage("No changes occured.");
               return;
            }
         }

         // OK lets now check to see if the user is sure they want to remove
         // the courses they either selected in the grid or dialog list.
         int confirm = JOptionPane.showConfirmDialog(controller.getFrame(),
                                   "Are you sure you want to remove the selected course(s)?",
                                   "Permanently remove courses?",
                                   JOptionPane.OK_CANCEL_OPTION);
         
         // Check they said yes then procceed to remove the courses
         // one at a time.
         if (confirm == JOptionPane.YES_OPTION)
         {
            for (String course : selected)
            {
               controller.removeCourse(course);
            }
            controller.setStatusMessage("Selected course(s) removed");
         }
      }
      // Thrown if the model has an issue (such as removing units that
      // are preres for other units), displays a dialog with a user friendly
      // error message
      catch (AMSException e)
      {
         JOptionPane.showMessageDialog(controller.getFrame(), 
                                       e.getMessage(), 
                                       "Course error",
                                       JOptionPane.ERROR_MESSAGE);
         controller.resetSelectedCourses();
         controller.setStatusMessage("An error occured.");
      }
   }
   
   /**
    * Provides the dialog box(es) for adding new courses,
    * provides basic verification of input and adds the 
    * new courses to the model.
    * 
    * NOTE: The model does not prevent overwriting courses
    * so course codes are checked against the model here
    * and the user is warned their actions will overwrite
    * a pre existing course.
    */
   private void addCourse()
   {
      // display the main course entry dialog box
      AddCourseDialog dialog = new AddCourseDialog();
      // Get the list of pre existing courses
      // and create the prereq dialog stuff
      Course[] courseArray = controller.getCourses();
      String[] courseList = null;
      // the array isn't empty?
      if (courseArray != null)
      {
         // create the list from the array for user selection
         courseList = new String[courseArray.length];
         for (int i = 0; i < courseList.length; i++)
         {
            courseList[i] = courseArray[i].getCode();
         }
      }
      int option = dialog.openPrompt(controller.getFrame(), courseList);
      
      // Check the user clicked OK and wants to procceed
      if (option == JOptionPane.OK_OPTION)
      {
         try
         {
            // get the input
            String code = dialog.getCode();
            String title = dialog.getTitle();
            String type = dialog.getType();
            int cp = dialog.getCp();
            String[] prereqs = dialog.getPrereqsArray();

            //
            // INPUT VALIDATION
            //
            // CODE
            // check code is 8 characters long
            if (code.length() != 8)
               throw new ProgramException
                         ("Code length MUST be 8 characters long.");
            // Check to see if the course code entered is already taken
            //    by another unit, if so warn user!
            if (controller.getCourse(code) != null)
            {
               // warn user the course code already exists
               option = JOptionPane.showConfirmDialog(controller.getFrame(),
                                                    "This course already exists, overwrite course?",
                                                    "Course Exits",
                                                    JOptionPane.OK_CANCEL_OPTION);
               // check if user doesn't want to procceed
               if (option != JOptionPane.OK_OPTION)
                  throw new ProgramException("The course will not be overwritten.");
            }
            //
            // TITLE
            // check title is 2 or more characters
            if (title.length() < 2)
               throw new ProgramException
                         ("Titles must be AT LEAST 2 characters long..");
            // Make sure the title starts with a capital LETTER
            if (title.charAt(0) < 65 ||
                title.charAt(0) > 90)
               throw new ProgramException
                         ("Titles must start with a capital LETTER.");
            //
            // COURSE CREATION
            //
            // check which course type to create
            // CoreCourse?
            if (type.equals(AddCourseDialog.CORE_COURSE))
            {
               // Create new CoreCourse and inform the status bar
               controller.addCourse(
                            new CoreCourse(code, 
                                           title, 
                                           prereqs));
               controller.setStatusMessage("Course was added.");
            }
            // ElectiveCourse?
            else if (type.equals(AddCourseDialog.ELECTIVE_COURSE))
            {
               // create new ElectiveCourse and inform the status bar
               controller.addCourse(
                            new ElectiveCourse(code, 
                                               title, 
                                               cp,
                                               prereqs));
               controller.setStatusMessage("Course was added.");

            }
         }
         // thrown when invalid user input is detected
         catch (AMSException e)
         {
            JOptionPane.showMessageDialog(controller.getFrame(), 
                                          e.getMessage(),
                                          "Course error",
                                          JOptionPane.ERROR_MESSAGE);
            controller.setStatusMessage("No course was added.");
         }
         // thrown when user doesn't fill a mandatory field
         catch (NullPointerException e)
         {
            JOptionPane.showMessageDialog(controller.getFrame(), 
                                          "Required field left blank",
                                          "Course error",
                                          JOptionPane.ERROR_MESSAGE);
            controller.setStatusMessage("No course was added.");
         }
         // thrown when the CP value isn't a number
         catch (NumberFormatException e)
         {
            JOptionPane.showMessageDialog(controller.getFrame(),
                                        "Only a numeric value can be entered.",
                                        "Course error",
                                        JOptionPane.ERROR_MESSAGE);
            controller.setStatusMessage("No course was added.");
         }
         // Hopefully never thrown, covers unforseen edge cases.
         catch (Exception e)
         {
            JOptionPane.showMessageDialog(controller.getFrame(), 
                                          e, 
                                          "Course error",
                                          JOptionPane.ERROR_MESSAGE);
            controller.setStatusMessage("No course was added.");
         }
      }
      // User clicked cancel, tell the status bar nothing happened
      else
         controller.setStatusMessage("No course was added.");
   }

}
