package ams.gui.mainwindow.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

/**
 * Implements correct exit functionality
 * 
 * @author timothyboye
 *
 */
public class ExitListener extends WindowAdapter implements ActionListener
{
   // Stores the main parent window object that called the listener
   // which provides access to the model and the GUI in an OO type way
   private AppController controller;
   
   /**
    * Initialises the Listener class and stores the caller for
    * later use.
    * @param _caller    Main window the listener belongs to
    */
   public ExitListener(AppController _controller)
   {
      controller = _controller;
   }
   
   /**
    * Prompts user to confirm exit and then calls exit(0)
    */
   private void confirmExit()
   {
      // Prompt user, Exit?
      int confirm = JOptionPane.showConfirmDialog
                                (controller.getFrame(),
                                 "Are you sure you want to exit?",
                                 "Exit Program",
                                 JOptionPane.YES_NO_OPTION);
      // Check user said OK then call exit(0)
      if (confirm == JOptionPane.YES_OPTION)
      {
         System.exit(0);
      }
   }
   
   /**
    * Called when exit button is pressed and passes
    * the call to confirmExit()
    * 
    * @param a
    */
   @Override
   public void actionPerformed(ActionEvent a)
   {
      this.confirmExit();
   }
   
   /**
    * Called when window close button is pressed 
    * and passes the call to confirmExit()
    * 
    * @param w
    */
   @Override
   public void windowClosing(WindowEvent w)
   {
      this.confirmExit();
   }


}
