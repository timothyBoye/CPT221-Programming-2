package ams.gui.mainwindow.controllers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import ams.gui.dialogs.views.CreateProgramDialog;
import ams.model.Program;
import ams.model.exception.ProgramException;

@SuppressWarnings("serial")
public class ProgramListener extends AbstractAction
{
   // Stores the main parent window object that called the listener
   // which provides access to the model and the GUI in an OO type way
   private AppController controller;
   
   //Action Commands used in this controller
   public static final String CREATE_PROGRAM = "read";
   public static final String RESET_PROGRAM = "how";
   
   /**
    * Initialises the Listener class and stores the caller for
    * later use.
    * @param _caller    Main window the listener belongs to
    */
   public ProgramListener(AppController _controller)
   {
      controller = _controller;
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
      String action = a.getActionCommand();
      
      if (action.equals(CREATE_PROGRAM))
      {
         this.create();
      }
      else if (action.equals(RESET_PROGRAM))
      {
         this.reset();
         controller.resetSelectedCourses();
      }

      // update the gui
      controller.updateGUI();
   }

   /**
    * Helper method that resets the program when called (after
    * confirming the action with the user).
    */
   private void reset()
   {
      // Check the user is sure they understand what they're doing
      int confirm =
               JOptionPane.showConfirmDialog(controller.getFrame(),
                                             "Are you sure you want to reset the program," +
                                             " this will remove ALL courses from the program?",
                                             "Reset Program",
                                             JOptionPane.YES_NO_OPTION);
      // Chose to continue? make new identical program with no courses
      if (confirm == JOptionPane.YES_OPTION)
      {
         // Clear the courses in the program
         controller.getProgram().getAllCourses().clear();
         
         //inform the status bar
         controller.setStatusMessage("Program reset.");
      }
      // didn't want to reset? inform status bar reset aborted
      else
      {
         controller.setStatusMessage("Program reset aborted.");
      }

   }

   /**
    * When called this method asks the user for the program 
    * details then creates a new program.
    */
   private void create()
   {
      CreateProgramDialog dialog = new CreateProgramDialog();
      int option = dialog.showDialog(controller.getFrame());
      
      // User clicked ok? create the program
      if (option == JOptionPane.OK_OPTION)
      {
         try
         {
            String title = dialog.getTitle();
            String code = dialog.getCode();
            
            //check the title length
            if (title.length() < 2)
               throw new ProgramException("Titles must be AT LEAST 2 characters long..");
            // check the code length
            if (code.length() != 6)
               throw new ProgramException("Code length MUST be 6 characters long.");
            
            // create a new program and add to model
            controller.addProgram(new Program(dialog.getCode(),dialog.getTitle()));
            
            // tell the status bar
            controller.setStatusMessage("New program created.");
   
         }
         // called when the user input is invalid, tell the user they done goofed
         catch (ProgramException e)
         {
            JOptionPane.showMessageDialog(controller.getFrame(), e.getMessage(),
                                          "Course error",
                                          JOptionPane.ERROR_MESSAGE);
            controller.setStatusMessage("Program not created.");
         }
      }
      // user cancelled the creating, tell the status bar
      else
      {
         controller.setStatusMessage("Program not created.");
      }
   }

}
