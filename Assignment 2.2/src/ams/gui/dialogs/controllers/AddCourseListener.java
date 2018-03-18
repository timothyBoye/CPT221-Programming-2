package ams.gui.dialogs.controllers;

import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ams.model.CoreCourse;

/**
 * Basic listener class to listener for selection changes
 * in the course type list when createing a new course.
 * When Elective is chosen CP value field becomes editable.
 * 
 * @author timothyboye
 *
 */
public class AddCourseListener implements ListSelectionListener
{
   // reference to cp field of dialog
   private JSlider cpSlider;
   
   /**
    * Stores the reference to the cp field
    * @param _cp     reference to the cp field in the dialog box
    */
   public AddCourseListener(JSlider _cp)
   {
      cpSlider = _cp;
   }

   /**
    * Called when the selection changes, checks what the selection is
    * then either makes CP editable or uneditable.
    * 
    * @param e
    */
   @SuppressWarnings("unchecked")
   @Override
   public void valueChanged(ListSelectionEvent e)
   {
      JList<String> typeList = (JList<String>)e.getSource();
      // Core is selected?
      if (typeList.getSelectedIndex() == 0)
      {
         // set NOT editable and string = default
         cpSlider.setEnabled(false);
         cpSlider.setValue(CoreCourse.DEFAULT_CREDIT_POINTS);
      }
      // Elective is selected?
      else if (typeList.getSelectedIndex() == 1)
      {
         // set editable
         cpSlider.setEnabled(true);
      }
      // Something unexpected?
      else
      {
         // set uneditable and string = default, just in case.
         cpSlider.setEnabled(false);
         cpSlider.setValue(CoreCourse.DEFAULT_CREDIT_POINTS);
      }

   }

}
