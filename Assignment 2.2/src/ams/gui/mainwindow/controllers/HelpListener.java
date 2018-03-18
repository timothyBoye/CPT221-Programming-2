package ams.gui.mainwindow.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ams.gui.dialogs.views.HelpDialog;

/**
 * Help menu functionality implementation
 * 
 * @author timothyboye
 *
 */
public class HelpListener implements ActionListener
{
   // Stores the main parent window object that called the listener
   // which provides access to the model and the GUI in an OO type way
   private AppController controller;
   
   //Action Commands used in this controller
   public static final String READ_ME = "read";
   public static final String HOW_TO = "how";
   public static final String VISITOR = "visitor";

   /**
    * Initialises the Listener class and stores the caller for
    * later use.
    * @param _caller    Main window the listener belongs to
    */
   public HelpListener(AppController _controller)
   {
      controller = _controller;
   }
   
   /**
    * Gets the actionCommand attached to the event
    * and calls the relavent helper method to implement
    * the correct functionality for the call.
    * 
    * @param a    ActionEvent containing source action command
    *             "read" shows a readme file like dialog box
    *             "how"  shows a dialog with interface usage
    *                    instructions
    */
   @Override
   public void actionPerformed(ActionEvent a)
   {
      // get the action command from the event source
      String action = a.getActionCommand();

      // pass call to read me method if action is read
      if (action.equals(READ_ME))
      {
         HelpDialog.readMeDialog(controller.getFrame());
      }
      // pass call to how to method if action is how
      else if (action.equals(HOW_TO))
      {
         HelpDialog.howToDialog(controller.getFrame());
      }
      // pass call to visitor method if action is how
      else if (action.equals(VISITOR))
      {
         HelpDialog.visitorDialog(controller.getFrame());
      }
   }

}
