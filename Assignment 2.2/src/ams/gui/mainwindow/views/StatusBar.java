package ams.gui.mainwindow.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Creates a status bar panel for the main 
 * view/window
 *
 * @author timothyboye
 *
 */
@SuppressWarnings("serial")
public class StatusBar extends JPanel
{
   // sub panel for the program info
   private JPanel mainPanel = new JPanel();
   
   // Labels that make up the status bar structure
   private JLabel msgLabel = new JLabel();
   private JLabel coreCourses = new JLabel("");;
   private JLabel electiveCourses = new JLabel("");;
   private JLabel programTitle = new JLabel("");

   // initial status bar message
   private static final String INITIAL_MSG = "System loaded.";

   // Program info strings
   private static final String CORE_STRING = "   Core: ";
   private static final String ELECTIVE_STRING = "   Elective: ";
   private static final String PROGRAM_STRING = "   Program: ";
   
   /**
    * Init the status bar
    */
   public StatusBar()
   {
      // set the layout manager for the panel and subpanel
      super(new BorderLayout());
      mainPanel.setLayout(new FlowLayout());
      
      // setup a fancy border for the status bar
      this.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY), 
                        BorderFactory.createMatteBorder(1, 0, 0, 0, Color.WHITE)
                        ));
      msgLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
      mainPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

      // add the program labels to the sub panel
      mainPanel.add(programTitle);
      mainPanel.add(coreCourses);
      mainPanel.add(electiveCourses);

      // add the msg label and program sub panel to the status bar
      this.add(msgLabel, BorderLayout.WEST);
      this.add(mainPanel, BorderLayout.CENTER);
      
      // set initial status message
      this.setMsg(INITIAL_MSG);
   }
   
   
   /**
    * Allows outside classes to set a message on the status bar
    * 
    * @param string  Message to be displayed (don't add padding
    *                it's done automatically)
    */
   public void setMsg(String string)
   {
      // set the label to the message with some padding for aestetics
      msgLabel.setText("   " + string + "   ");
   }
   
   /**
    * Called when the status bar needs to check the models data and
    * display any changes
    * 
    * @param title 
    * @param elective 
    * @param core
    */
   public void setProgramDetails(int core, int elective, String title)
   { 
         // set the programs info to display in the status bar
         coreCourses.setText(CORE_STRING + core);
         electiveCourses.setText(ELECTIVE_STRING + elective);
         programTitle.setText(PROGRAM_STRING + title);
         this.showProgramDetails(true);
   }
   
   /**
    * Tells the status bar whether to show or hide the program pane
    * @param show    true show/flase hide
    */
   public void showProgramDetails(boolean show)
   {
      mainPanel.setVisible(show);
   }
}
