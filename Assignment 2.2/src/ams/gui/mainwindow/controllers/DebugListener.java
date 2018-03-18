package ams.gui.mainwindow.controllers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

import ams.model.CoreCourse;
import ams.model.Course;
import ams.model.ElectiveCourse;
import ams.model.Program;


@SuppressWarnings("serial")
public class DebugListener extends AbstractAction
{

   private AppController controller;

   //Action Commands used in this controller
   public static final String CREATE = "create";
   public static final String CREATE_POPULATED = "createpopulated";
   
   public DebugListener (AppController _controller)
   {
      controller = _controller;
   }
   
   
   @Override
   public void actionPerformed(ActionEvent a)
   {
   // get the action command
      String action = a.getActionCommand();

      // redirect to the relavent helper method
      if (action.equals(CREATE))
      {
         this.createProgram();
      }
      else if (action.equals(CREATE_POPULATED))
      {
         this.createProgram();
         this.populate();
      }

      // tell the gui to check the model and update
      controller.updateGUI();
   }


   /**
    * Creates a program populated with courses
    */
   private void populate()
   {
      JLabel noOfCoursesLabel = new JLabel("How many courses to add?");
      JSlider noOfCoursesSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 10);
      noOfCoursesSlider.setMinorTickSpacing(5);
      noOfCoursesSlider.setMajorTickSpacing(10);
      noOfCoursesSlider.setPaintTicks(true);
      noOfCoursesSlider.setPaintLabels(true);
      
      // Ask how many courses to make and then check if the user clicked ok
      Object[] message = {noOfCoursesLabel,noOfCoursesSlider};
      int option = JOptionPane.showConfirmDialog(controller.getFrame(), 
                                                 message,
                                                 "Populate the program",
                                                 JOptionPane.OK_CANCEL_OPTION);
      if (option == JOptionPane.OK_OPTION)
      {
         int numberOfCoursesToAdd = noOfCoursesSlider.getValue();
         // Populate the program with the courses
         try
         {
            // print the initialising message for debugging
            System.out.println("Initialising the courses...\n");
            
            // create a model full of courses
            for (int i = 1; i <= numberOfCoursesToAdd; i++)
            {
               // pad a string with zeros so we can make a unique
               // course code that is 8 characters long later.
               String code = String.format("%07d", i);
               // make the array for prereqs
               String[] prereqs = null;
   
               // every 7th course lets add some prereqs
               if (i % 7 == 0)
               {
                  // create an array of prereqs with a uniqur length
                  prereqs = new String[controller.getCourses().length/2];
                  for (int j = 0; j < controller.getCourses().length/2; j++)
                  {
                     prereqs[j] = controller.getCourses()[j].getCode().toString();
                  }
               }
               
               // create a core or elective course depending on odd or even number
               // this slightly randomises the array to make sort testing easier.
               if (i % 2 == 0)
                  controller.addCourse(new CoreCourse(("C"+code), 
                                                      "DEBUG core course"+i, 
                                                      prereqs));
               else
                  controller.addCourse(new ElectiveCourse(("E"+code), 
                                                          "DEBUG elective course"+i, 
                                                          i, 
                                                          prereqs));
            }
            
            // print the array for debuging
            for (Course course : controller.getCourses())
            {
               System.out.println(course.toString());
            }
            //print the model complete message to show no errors
            controller.setStatusMessage("New program created.");
            System.out.println("\nModel complete, updateing the GUI...\n");
         }
         catch (Exception e)
         {
            System.out.println(e.getMessage());
         }
      }
   }


   /**
    * Creates an empty program
    */
   private void createProgram()
   {

      // print welcome message
      System.out.println("=====\n" +
                         "Academic Management System - DEBUG MODE\n" +
                         "=====\n"
                        );
      // print program message for debugging
      System.out.println("Initialising the program...\n");
      controller.addProgram(new Program("DEBUG1","DEBUG program"));
      // print the models program for debugging
      controller.setStatusMessage("New program created.");
      System.out.println(controller.getProgram().toString()+"\n");
      
   }

}
