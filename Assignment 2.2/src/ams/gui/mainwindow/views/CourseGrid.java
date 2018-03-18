package ams.gui.mainwindow.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import ams.gui.mainwindow.controllers.AppController;
import ams.gui.mainwindow.controllers.visitor.CourseTypeVisitor;
import ams.gui.mainwindow.views.components.CoreCell;
import ams.gui.mainwindow.views.components.ElectiveCell;
import ams.gui.mainwindow.views.components.EmptyCell;
import ams.gui.mainwindow.views.components.GridCell;
import ams.model.Course;

/**
 * This panel displays a visual grid of grey squares
 * which represent courses and contain vital information
 * about each one.
 * 
 * @author timothyboye
 *
 */
@SuppressWarnings("serial")
public class CourseGrid extends JPanel
{
   // main program contoller
   private AppController controller;
   // the actual panel that is the course grid
   private JPanel courseGrid = new JPanel();
   // a map of the cells for user selections etc.
   private Map<String, GridCell> gridCells = new HashMap<String, GridCell>();

   /**
    * Init the grid
    * @param appController    main application controller
    */
   public CourseGrid(AppController appController)
   {
      // Set up the panel
      super(new BorderLayout());
      controller = appController;
      this.add(courseGrid);
   }

   /**
    * Takes in a list of courses then produces
    * a grid to represent them
    * @param courses    array of Course objects or null
    */
   public void modelUpdate(Course[] courses)
   {
      // remove the current grid so we can make a new one without any errors
      this.remove(courseGrid);
      // create the new one
      courseGrid = new JPanel();
      // add it back in
      this.add(courseGrid, BorderLayout.CENTER);
      
      // set the right layout based on number of courses
      //
      // no courses
      if (courses == null || courses.length == 0)
      {
         //display nothing
         return;
      }
      // less than 4 courses?
      else if (courses.length <= 4)
      {
         courseGrid.setLayout(new GridLayout(1, courses.length));
      }
      // the number of courses is a multiple of 4 ?
      else if (courses.length % 4 == 0)
      {
         courseGrid.setLayout(new GridLayout((courses.length / 4), 4));
      }
      // if not a multiple of 4 or less than 4? we'll need padding
      else
      {
         courseGrid.setLayout(new GridLayout((courses.length / 4) + 1, 4));
      }
      
      /////
      // OK prework done, lets make a grid of all courses!
      ////
      for (int i = 0; i < courses.length; i++)
      {
         String code = courses[i].getCode();
         String title = courses[i].getTitle();
         int cp = courses[i].getCreditPoints();
         String[] prereqs = courses[i].getPreReqs();
         
         GridCell cell;
         
         // Create a grid cell
         //
         // create a visitor to figure out what sort of subclass we have
         CourseTypeVisitor visitor = new CourseTypeVisitor();
         courses[i].accept(visitor);
         
         // is the course a CoreCourse?
         if (visitor.isCore())
         {
            cell = new CoreCell(code, title, cp, prereqs, controller);
         }
         else
         {
            cell = new ElectiveCell(code, title, cp, prereqs, controller);
         }

         // store the cell in a map
         gridCells.put(code, cell);
         
         // set the cell to cyan if selected
         if (controller.selectedCoursesContains(code))
            cell.getJTextArea().setBackground(GridCell.SELECTED_CELL_BG);
         
         // add the cell to the panel
         courseGrid.add(cell);
      }
      
      
      /////
      // If the grid has blank spaces, make them pretty by adding blank fields
      ////
      if (courses.length % 4 != 0 && courses.length > 4)
      {
         // cycle through the white space and make them pretty
         for (int i = 0; i < 4 - (courses.length % 4); i++)
         {
            courseGrid.add(new EmptyCell());
         }
      }
   }
   
   /**
    * Change a grid cell to selected
    * @param code    course code of cell
    */
   public void toggleSelection(String code, boolean selected)
   {
      if (selected)
         gridCells.get(code).getJTextArea().setBackground(GridCell.SELECTED_CELL_BG);
      else
         gridCells.get(code).getJTextArea().setBackground(GridCell.COURSE_CELL_BG);
   }
}
