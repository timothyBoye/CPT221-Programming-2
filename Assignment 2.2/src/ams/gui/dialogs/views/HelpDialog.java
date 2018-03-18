package ams.gui.dialogs.views;

import javax.swing.JOptionPane;

import ams.gui.mainwindow.views.AppFrame;

/**
 * Help dialogs
 * 
 * @author timothyboye
 *
 */
public class HelpDialog
{   
   //Dialog titles
   private static final String HOW_TO_TITLE  = "How to use the system";
   private static final String READ_ME_TITLE = "Read Me File";
   private static final String VISITOR_TITLE  = "Visitor Pattern";
   
   // create the text to be displayed
   private static final String[] HOW_TO_TEXT = 
         {"**************************",
         "How to use this system",
         "**************************",
         " ",
         "1. Initialise the program by clicking \"Create New Program\"",
         "2. The program can now be reset using \"Reset Program\" which",
         "completely resets the program leaving only the name and code",
         "3. Courses can be added using the \"Add Course\" button and dialog",
         "4. Courses can be removed with the \"Remove Course\" button",
         "4a. Left click each course to be removed (they will turn cyan",
         "indicating they're selected, then press \"Remove Course\"",
         "4b. If no courses are selected you can press \"Remove Course\" and",
         "choose the courses to remove from a list (ctrl+click to select multiple",
         "5. The order of the courses display can be changed via the sort drop down",
         " ",
         " ",
         "Note: 4a approved by Mikhail as Direct manipulation requirement here:",
         "https://lms.rmit.edu.au/webapps/discussionboard/do/message?action=",
         "list_messages&forum_id=_347917_1&nav=discussion_board_entry&conf_id=",
         "_288726_1&course_id=_312042_1&message_id=_3082858_1#msg__3082858_1Id"};
   private static final String[] READ_ME_TEXT = 
       { "**************************",
         "Academic Management System",
         "By Timothy Boye",
         "**************************",
         " ",
         "Main method class: ams.gui.GUIMain",
         " ",
         "If there are any graphics issues try setting",
         "NIMBUS_LOOK_AND_FEEL in GUIMain to false, this",
         "will revert the program to swing's default look",
         "and feel.",
         " ",
         "For ease of testing some debug code has been included",
         "to enter debug mode set DEBUG_MODE in GUIMain to true,",
         "this has a few small effects on the system including",
         "adding a debug menu that allows importing some test data.",
         "",
         "The model in use is entirely Mikhail's Ass1 solution and",
         "is therefore not my own work."};
   private static final String[] VISITOR_TEXT = 
        {"**************************",
         "The Visitor Design Pattern",
         "**************************",
         " ",
         "This system implements the visitor design pattern",
         "to ascertain the difference between sub classes of",
         "Course objects.",
         " ",
         "The Subclasses implement a method called accept",
         "that takes a Visitor object and calls its visit",
         "method. The visit method is overridden in our ",
         "ams.visitor.CourseTypeVisitor class such that each",
         "sub class has its own method. Once the class has",
         "visited the visitor then is[class]() can be called",
         "to determine if the class is of that type.",
         " ",
         "The classes involved are:",
         "ams.model.visitor.Visitable,",
         "ams.model.visitor.Visitor,",
         "ams.gui.mainwindow.controllers.comparators.visitor.CourseTypeVisitor",
         "the Course related classes in ams.model",
         " ",
         "Please note whilst my model did include the visitor",
         "pattern I have swapped my model for Mikhail's to be",
         "certain the model runs as he expects and thus the",
         "classes Visitable and Visitor are Mikhail's work"
         };
   
   public HelpDialog()
   {
   }
   
   /**
    * Displays information about how to use the system to the user
    * 
    * @param window     window to centre the dialog in
    */
   public static void howToDialog(AppFrame window)
   {
      displayDialog(window, HOW_TO_TITLE, HOW_TO_TEXT);
   }
   
   /**
    * Displays information about the system to the user
    * 
    * @param window     window to centre the dialog in
    */
   public static void readMeDialog(AppFrame window)
   {
      displayDialog(window, READ_ME_TITLE, READ_ME_TEXT);
   }
   
   /**
    * Displays information about the visitor pattern to the user
    * 
    * @param window     window to centre the dialog in
    */
   public static void visitorDialog(AppFrame window)
   {
      displayDialog(window, VISITOR_TITLE, VISITOR_TEXT);
   }
   
   /**
    * Displays the dialog
    * 
    * @param window     window to centre the dialog in
    * @param title      dialog title
    * @param contents   dialog contents
    */
   private static void displayDialog(AppFrame window, String title, String[] contents)
   {
      // show the text to the user in a prompt
      JOptionPane.showMessageDialog(window, 
                                    contents,
                                    title,
                                    JOptionPane.INFORMATION_MESSAGE);
   }

}
