package ams.gui.mainwindow.views.components;


/**
 * Creates a blank cell for use in the course grid.
 * This has no mouse listener and is white.
 * 
 * @author timothyboye
 *
 */
@SuppressWarnings("serial")
public class EmptyCell extends GridCell
{
   /**
    * Create the blank cell
    */
   public EmptyCell()
   {
      // set the background to white
      super.getJTextArea().setBackground(EMPTY_CELL_BG);
   }
}
