package ams.gui.mainwindow.views.components;

import javax.swing.BorderFactory;

import ams.gui.mainwindow.controllers.AppController;

/**
 * Creates a GridCell to represent a CoreCourse object.
 * The CoreCell is as GridCell except with a thick border.
 * A mouselistener is attached and the name set to the paramter
 * "code".
 * 
 * @author timothyboye
 *
 */
@SuppressWarnings("serial")
public class CoreCell extends GridCell
{

   /**
    * Creates a GridCell with a thick border to represent
    * a CoreCourse. A mouselistener is attached and the name
    * is set to the parameter "code".
    * @param code       Course code
    * @param title      Course title
    * @param cp         Credit point value
    * @param prereqs    Array of course codes or null
    * @param controller Main app controller
    */
   public CoreCell(String code, String title, int cp, String[] prereqs, AppController controller)
   {
      // make a GridCell
      super(code, title, cp, prereqs, controller);
      // set it to have a mighty thick black border to denote importance
      super.setBorder(BorderFactory.createLineBorder(CELL_BORDER, 5));
   }

}
