package ams.gui.mainwindow.views.components;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ams.gui.mainwindow.controllers.AppController;
import ams.gui.mainwindow.controllers.SelectCourseListener;

/**
 * Abstract GridCell is the foundation for the course grid
 * where this cell represents one course or an empty cell.
 * there are two constructors one for a cell with text and
 * one without text.
 * 
 * @author timothyboye
 *
 */
@SuppressWarnings("serial")
public abstract class GridCell extends JScrollPane
{
   // Cell colours
   public static final Color COURSE_CELL_BG = Color.LIGHT_GRAY;
   public static final Color EMPTY_CELL_BG = Color.WHITE;
   public static final Color SELECTED_CELL_BG = Color.CYAN;
   public static final Color CELL_BORDER = Color.BLACK;
   
   // The text area EVERYTHING in this cell is based around
   private JTextArea textArea = new JTextArea();
            
   /**
    * Used to create a blank GridCell without a mouseListener
    */
   public GridCell()
   {
      // customise the textarea's look and behaviour
      textArea.setEditable(false);
      this.setBorder(BorderFactory.createLineBorder(CELL_BORDER, 1));

      // adds the textarea to the scroll pane (this class)
      this.setViewportView(textArea);
   }
   
   /**
    * Creates a GridCell containing formatted text and sets a
    * MouseListener on the textarea (the name of the textarea
    * will be set to the parametre "code" for use with the 
    * MouseListener).
    * @param code       course code
    * @param title      course title
    * @param cp         number of credit points
    * @param prereqs    array of course codes or null
    * @param controller the main appcontroller
    */
   public GridCell(String code, String title, int cp, String[] prereqs, AppController controller)
   {
      // set the DEFAULT border
      this.setBorder(BorderFactory.createLineBorder(CELL_BORDER, 1));
      // build a formatted string with all the course data
      StringBuilder tempString = new StringBuilder();
      tempString.append("Code: ");
      tempString.append(code);
      tempString.append("\n");
      tempString.append("Title: ");
      tempString.append(title);
      tempString.append("\n");
      tempString.append("CP: ");
      tempString.append(cp);
      // Are there prereqs?
      if (prereqs != null)
      {  // append the first one
         tempString.append("\n");
         tempString.append("Prerequisites: ");
         // cycle through and append any further prereqs
         for (String string : prereqs)
         {
            tempString.append(string);
            tempString.append("   ");
         }
         
      }
      
      // set the textareas behaviour and look
      textArea.setEditable(false);
      textArea.setText(tempString.toString());
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true);
      textArea.setName(code);
      textArea.setBackground(COURSE_CELL_BG);
      textArea.addMouseListener(new SelectCourseListener(controller));
      
      // adds the textarea to the scroll pane (this class)
      this.setViewportView(textArea);
      
   }
   
   /**
    * Returns a reference to the main textarea for editing
    * @return     reference to main JTextArea
    */
   public JTextArea getJTextArea()
   {
      return textArea;
   }
}
