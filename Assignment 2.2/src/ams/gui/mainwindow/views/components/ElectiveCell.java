package ams.gui.mainwindow.views.components;

import ams.gui.mainwindow.controllers.AppController;

/**
 * Creates a cell for the course grid that
 * represents an ElectiveCourse object. an
 * elective is a default look and feel GridCell
 * with a mouselistener and the name set to the
 * parametre "code".
 * 
 * @author timothyboye
 *
 */
@SuppressWarnings("serial")
public class ElectiveCell extends GridCell
{

   /**
    * Creates a GridCell to represent an Elective.
    * The look and feel is as GridCell a mouselistener
    * is attached and the name is set to paramatre "code".
    * 
    * @param code       course code
    * @param title      course title
    * @param cp         credit point value
    * @param prereqs    array of course codes or null
    * @param controller main app controller
    */
   public ElectiveCell(String code, String title, int cp, String[] prereqs, AppController controller)
   {
      // just call super.
      super(code, title, cp, prereqs, controller);
   }

}
