package ams.gui.mainwindow.controllers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;

/**
 * Implements the change sort order functionality of the gui
 * 
 * @author timothyboye
 *
 */
@SuppressWarnings("serial")
public class SortListener extends AbstractAction
{

   // Stores the main parent window object that called the listener
   // which provides access to the model and the GUI in an OO type way
   private AppController controller;

   //Names used in this controller
   public static final String SORT_COMBO_COMPONENT = "combo";
   public static final String SORT_MENU_NONE = "none";
   public static final String SORT_MENU_TITLE = "title";
   public static final String SORT_MENU_TYPE = "type";
   
   /**
    * Initialises the Listener class and stores the caller for
    * later use.
    * @param _caller    Main window the listener belongs to
    */
   public SortListener(AppController _controller)
   {
      controller = _controller;
   }
   
   /**
    * Sets the course grids sorting type to the one 
    * designated by the users action: none, title or
    * by type.
    * 
    * @param a    Information on the event that occured 
    *             in particular we need the name of the 
    *             calling object to figure out what type
    *             of sort to use.
    */
   @Override
   public void actionPerformed(ActionEvent a)
   {
      // set the sort to default (no sort)
      int sort = 0;
      
      // Check the source
      //    If it's the combo box get the index of the selected item
      //    and save that as the sort method
      if (a.getActionCommand().equals(SORT_COMBO_COMPONENT))
      {
         @SuppressWarnings("unchecked")
         JComboBox<String> comboBox = ((JComboBox<String>) a.getSource());
         sort = comboBox.getSelectedIndex();
      }
      // source name is "none" set sort to none (0).
      else if (a.getActionCommand().equals(SORT_MENU_NONE))
      {
         sort = 0;
      }
      // source name is "title" set sort to title (1).
      else if (a.getActionCommand().equals(SORT_MENU_TITLE))
      {
         sort = 1;
      }
      // source name is "type" set sort to type (2).
      else if (a.getActionCommand().equals(SORT_MENU_TYPE))
      {
         sort = 2;
      }

      // set the callers new course sort order and tell the status bar
      controller.setCourseSortOrder(sort);
      controller.setStatusMessage("Sort order changed.");
      
      controller.updateGUI();
   }
   

}
