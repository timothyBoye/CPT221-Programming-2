package ams.gui.dialogs.views;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import ams.gui.mainwindow.views.AppFrame;

/**
 * Dialog box for creating a program
 * 
 * @author timothyboye
 *
 */
public class CreateProgramDialog
{   
   // Program details dialog objects
   private JTextField title;
   private JTextField code;
   private Object[] message;
   
   /**
    * Create a dialog object with user input limitations
    */
   public CreateProgramDialog()
   {
      // Set the length limit of the code to 6
      try
      {
         MaskFormatter formatter = new MaskFormatter("******");
         code = new JFormattedTextField(formatter);
      } catch (ParseException e) {
         code = new JFormattedTextField();
      }
      
      // create the title field
      title = new JTextField();
      
      // create the JOptionPane Objects Array
      message = new Object[]
            { "Program code (6 alphanumeric characters):", code, 
              "Program title (Min 2 alphanumeric characters):", title };
   }
   
   /**
    * Show the create program dialog and save the results
    * 
    * @param caller     The window frame to centre the dialog on
    * @return           JOptionPane.OK_OPTION = user clicked ok
    *                   JOptionPane.CANCEL_OPTION = user doesn't 
    *                                           want to continue
    */
   public int showDialog(AppFrame caller)
   {
      // Call the dialog and save the user input
      return JOptionPane.showConfirmDialog(caller, message,
                                           "New Program",
                                           JOptionPane.OK_CANCEL_OPTION);
   }
   
   /**
    * Return the title the user entered, if the
    * user doesn't enter anything or the dialog
    * hasn't been called yet will return an empty
    * string
    * 
    * @return     title string entered by user
    */
   public String getTitle()
   {
      return title.getText().trim();
   }
   
   /**
    * Return the code the user entered, if the
    * user doesn't enter anything or the dialog
    * hasn't been called yet will return an empty
    * string
    * 
    * @return     code string entered by user
    */
   public String getCode()
   {
      return code.getText().toUpperCase().trim();
   }

}
