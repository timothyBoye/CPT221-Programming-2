package ams.gui.dialogs.views;

import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import ams.gui.mainwindow.views.AppFrame;

/**
 * Remove course dialog box that shows
 * the user a list of courses in the system
 * they can then select to remove.
 * 
 * @author timothyboye
 *
 */
public class RemoveCourseDialog
{   
   // the list element to store the course codes in
   // that the user can then select
   private JList<String> courseList;
   
   /**
    * create the object
    */
   public RemoveCourseDialog()
   {
   }
   
   /**
    * Show the remove course dialog box with a list
    * of course codes to select which course(s) to 
    * remove.
    *    
    * @param caller     Window to centre the dialog on
    * @param courseCodes   List of courses to show user
    * @return           JOptionPane.OK_OPTION = user clicked ok
    *                   JOptionPane.CANCEL_OPTION = user doesn't 
    *                                           want to continue
    */
   public int showDialog(AppFrame caller, String[] courseCodes)
   {
      // create the dialog box elements
      courseList = new JList<String>(courseCodes);
      JScrollPane courseListPane = new JScrollPane(courseList);
      Object[] removeCoursesDialog =
               { "Please select the courses to remove:", courseListPane };

      // display the dialog
      return JOptionPane.showConfirmDialog(caller, removeCoursesDialog,
                                                 "Remove courses",
                                                 JOptionPane.OK_CANCEL_OPTION);
      
   }
   /**
    * Returns a list of course codes the user selected
    * if any.
    * 
    * @return     List<String> of course codes
    */
   public List<String> getSelectedCourses()
   {
      return courseList.getSelectedValuesList();
   }

}
