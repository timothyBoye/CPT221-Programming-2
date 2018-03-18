package ams.gui.dialogs.views;

import java.awt.Color;
import java.text.ParseException;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import ams.gui.dialogs.controllers.AddCourseListener;
import ams.gui.mainwindow.views.AppFrame;

/**
 * Prompts the user to enter course data
 * for a new course.
 * 
 * @author timothyboye
 *
 */
public class AddCourseDialog
{
   // public codes for the course types
   public static String CORE_COURSE = "Core";
   public static String ELECTIVE_COURSE = "Elective";
   
   // components of the prereqs entry
   private JScrollPane prereqs;
   private JList<String> prereqsList;
   
   // CP components (NumberFormat prevents letters being entered)
   private JSlider cpSlider = new JSlider(JSlider.HORIZONTAL, 6, 12, 12);
   
   // other user data entry components
   private JFormattedTextField code;
   private JFormattedTextField title;

   // course type list
   private JList<String> type =
            new JList<String>(new String[] { CORE_COURSE, ELECTIVE_COURSE });
   
   // create the dialog box contents array
   Object[] newCourseDialogQuestions;
  
   /**
    * Initialise the dialog box setup with user input restrictions
    */
   public AddCourseDialog()
   {
      // set up the type list to only allow one selection
      //    and have a listener for making cp editable when appropriate
      ListSelectionModel typeModel = new DefaultListSelectionModel();
      typeModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      type.setSelectionModel(typeModel);
      type.addListSelectionListener(new AddCourseListener(cpSlider));

      // Set the length limit of the code to 8 
      //    and the title to 25 with a capital letter
      try
      {
         MaskFormatter codeFormatter = new MaskFormatter("********");
         code = new JFormattedTextField(codeFormatter);
         MaskFormatter titleFormatter = new MaskFormatter("U*********************" +
                                                          "***********************");
         title = new JFormattedTextField(titleFormatter);
      } 
      catch (ParseException e) 
      {
         code = new JFormattedTextField();
         title = new JFormattedTextField();
      }
      
      // setup the cp field and block it out from being editable
      cpSlider.setEnabled(false);
      cpSlider.setMajorTickSpacing(6);
      cpSlider.setSnapToTicks(true);
      cpSlider.setPaintTicks(true);
      cpSlider.setPaintLabels(true);
      
   }
   
   /**
    * Open the add course dialog box and save the input
    * 
    * @return     JOptionPane.OK_OPTION or JOptionPane.CANCEL_OPTION 
    */
   public int openPrompt(AppFrame caller, String[] courseList)
   {
      // setup the prereqs list
      // it is empty?
      if (courseList == null)
      {
         // blank out the user selection list
         prereqsList = new JList<String>();
         prereqsList.setEnabled(false);
         prereqsList.setBackground(Color.LIGHT_GRAY);
      }
      else
      {
         prereqsList = new JList<String>(courseList);
      }
      // add the list to a scroll pane
      prereqs = new JScrollPane(prereqsList);
      
      // create the dialog box contents from the fields above
      newCourseDialogQuestions = new Object[]
            { "Course code (8 alphanumeric characters):", code, 
            "Course title (Should start with a capital and be at least 2 characters long):", title, 
            "Course type (Select 1):", type, 
            "Credit Points (Only electives have a variable CP):", cpSlider,
            "Prereqistites (ctrl+click for multiple):", prereqs };
      
      
      // open the dialog and return the users selection of OK or Cancel
      int option = JOptionPane.showConfirmDialog(caller, newCourseDialogQuestions,
                                            "Enter course details",
                                            JOptionPane.OK_CANCEL_OPTION);
      return option;
   }

   /**
    * Provides an array of course ids representing the
    * prerequisities the user selected for the course
    * 
    * @return     array of course codes
    */
   public String[] getPrereqsArray()
   {
      // check if the user selected any prereqs
      // and if they did store the selection
      if (prereqsList.isSelectionEmpty())
         return null;
      else
      {
         return prereqsList.getSelectedValuesList()
                                   .toArray(new String[0]);
      }
   }

   /**
    * Returns the users entered course code.
    * 
    * @return     course code as string
    */
   public String getCode()
   {
      // set the string to uppercase so all codes are uppercase
      String codeString = code.getText().toUpperCase().trim();
      
      // return
      return codeString;
   }

   /**
    * Returns the string the user entered to represent the 
    * courses title.
    * 
    * @return     title string
    */
   public String getTitle()
   {
      String titleString = title.getText().trim();
      
      // valid input pass the title along
      return titleString;
   }

   /**
    * Returns the CP value entered by the user or the default
    * depending on course type
    * 
    * @return     integer value entered by the user or the default balue
    * @throws NumberFormatException    When the user doesn't enter a valid number
    */
   public int getCp()
   {
      // convert the cp value (throws NumberFormatException if not a number)
      int cpInt = cpSlider.getValue();
      return cpInt;
   }

   /**
    * Returns the type that was selected by the user
    * 
    * @return     String representing the users input that 
    *             can be compared to the public static strings
    *             defined in this class
    */
   public String getType()
   {
      return type.getSelectedValue();
   }
   
   

}
