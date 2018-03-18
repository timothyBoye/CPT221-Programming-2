package ams.gui.mainwindow.controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
/**
 * Provides a listener implementation for selecting
 * courses when the user clicks on a course in the
 * course grid.
 * 
 * @author timothyboye
 *
 */
public class SelectCourseListener implements MouseListener
{
   // Stores the main parent window object that called the listener
   // which provides access to the model and the GUI in an OO type way
   private AppController controller;
   
   /**
    * Initialises the Listener class and stores the caller for
    * later use.
    * @param _caller    Main window the listener belongs to
    */
   public SelectCourseListener(AppController _controller)
   {
      controller = _controller;
   }

   /**
    * Gets the name of the clicked item from the MouseEvent,
    * tells the status bar which course has been selected and
    * finally tells the caller to toggle that course selection.
    * 
    * @param e
    */
   @Override
   public void mouseClicked(MouseEvent e)
   {
      String selection = ((JComponent)e.getSource()).getName();
      controller.setStatusMessage("Course "+ selection + ", selection toggled.");
      controller.toggleSelectedCourse(selection);
      }

   @Override
   public void mousePressed(MouseEvent e)
   {
      //do nothing  
   }

   @Override
   public void mouseReleased(MouseEvent e)
   {
      //do nothing  
   }

   @Override
   public void mouseEntered(MouseEvent e)
   {
      //do nothing  
   }

   @Override
   public void mouseExited(MouseEvent e)
   {
      //do nothing      
   }

}
