package ams.gui.mainwindow.views;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ams.gui.mainwindow.controllers.AppController;
import ams.gui.mainwindow.controllers.CourseListener;
import ams.gui.mainwindow.controllers.ProgramListener;
import ams.gui.mainwindow.controllers.SortListener;
import ams.gui.mainwindow.views.components.TwoLineButton;
import ams.model.Course;
import ams.model.Program;

/**
 * Creates the toolbar for the main view
 * 
 * @author timothyboye
 *
 */
@SuppressWarnings("serial")
public class Toolbar extends JPanel
{
   // main app controller
   private AppController controller;
   
   // program related functionality components / buttons
   private JButton createProgram = new TwoLineButton("Create\nProgram");  
   private JButton resetProgram = new TwoLineButton("Reset\nProgram");
   private JLabel seperator = new JLabel("|");
   private JButton addCourse = new TwoLineButton("Add\nCourse");
   private JButton removeCourses = new TwoLineButton("Remove\nCourses");
   private JButton clearSelection = new TwoLineButton("Clear\nSelection");
   private JComboBox<String> sortOrder = new JComboBox<String>(new String[]{"Sort: none", "Sort: by title", "Sort: by type"});
   
   /**
    * Init the toolbar
    * @param _controller      main app controller
    */
   public Toolbar(AppController _controller)
   {
      // set the layout and save the controller
      super(new GridLayout(1,7));
      controller = _controller;
      //centre the text in my seperator label
      seperator.setHorizontalAlignment(SwingConstants.CENTER);
      // add all the buttons to the panel
      this.add(createProgram);
      this.add(resetProgram);
      this.add(seperator);
      this.add(addCourse);
      this.add(removeCourses);
      this.add(clearSelection);
      this.add(sortOrder);
      
      // make the buttons panel pretty with a border
      this.setBorder(BorderFactory.createCompoundBorder(BorderFactory
               .createMatteBorder(1, 0, 1, 0, Color.GRAY), BorderFactory
               .createMatteBorder(1, 0, 1, 0, Color.WHITE)));
      
      // set the action commands for the buttons
      createProgram.setActionCommand(ProgramListener.CREATE_PROGRAM);
      resetProgram.setActionCommand(ProgramListener.RESET_PROGRAM);
      addCourse.setActionCommand(CourseListener.ADD_COURSE);
      removeCourses.setActionCommand(CourseListener.REMOVE_COURSE);
      clearSelection.setActionCommand(CourseListener.CLEAR_SELECTION);
      sortOrder.setActionCommand(SortListener.SORT_COMBO_COMPONENT);
      
      // set the listeners for the buttons
      createProgram.addActionListener(controller.getProgramListener());
      resetProgram.addActionListener(controller.getProgramListener());
      addCourse.addActionListener(controller.getCourseListener());
      removeCourses.addActionListener(controller.getCourseListener());
      clearSelection.addActionListener(controller.getCourseListener());
      sortOrder.addActionListener(controller.getSortListener());
   }

   /**
    * Receives a fresh copy of the model data and updates
    * the buttons so they are enabled or disabled as necesary
    * @param program    program object or null
    * @param courses    copy of the courses array or null
    */
   public void modelUpdate(Program program, Course[] courses)
   {
      // Check there is a program and set buttons accordingly
      if (program != null)
      {
         createProgram.setEnabled(false);
         resetProgram.setEnabled(true);
         addCourse.setEnabled(true);

         // check there are courses and set buttons accordingly
         if (courses != null)
         {
            removeCourses.setEnabled(true);
            sortOrder.setEnabled(true);
            // check if there are any selected courses and set select button accordingly
            if (controller.getSelectedCourses().isEmpty())
               clearSelection.setEnabled(false);
            else
               clearSelection.setEnabled(true);

            // set sort order
            if (controller.getCourseSortOrder() != sortOrder.getSelectedIndex())
               sortOrder.setSelectedIndex(controller.getCourseSortOrder());
         }
         // no course? set buttons accordingly
         else
         {
            removeCourses.setEnabled(false);
            clearSelection.setEnabled(false);
            sortOrder.setEnabled(false);
         }
      }
      // no porgram? set buttons accordingly
      else
      {
         createProgram.setEnabled(true);
         resetProgram.setEnabled(false);
         addCourse.setEnabled(false);
         removeCourses.setEnabled(false);
         clearSelection.setEnabled(false);
         sortOrder.setEnabled(false);
      }
   }

   /**
    * Used to turn the clear selection button on and off.
    * @param b    true enabled / false disabled
    */
   public void setSelectionClearable(boolean b)
   {
      clearSelection.setEnabled(b);
   }
}
