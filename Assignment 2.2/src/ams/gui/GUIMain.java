package ams.gui;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ams.gui.mainwindow.views.AppFrame;
import ams.model.facade.AMSFacade;
import ams.model.facade.AMSModel;

public class GUIMain
{
   // makes a few minor changes and imports testing data
   public static final boolean DEBUG_MODE = true;
   
   // Changes the appearance of the app frame between default and nimbus
   private static final boolean NIMBUS_LOOK_AND_FEEL = true;
   
   // Reference to the model that is passed around the GUI
   public static AMSModel model = new AMSFacade();
   
   // Main frame, all panels etc are composition classes of AppFrame
   public static AppFrame mainWindow;
  
   /**
    * MAIN CLASS
    * 
    * @param args    command line args [not used]
    */
   public static void main(String[] args)
   {
      // check look and feel then set Nimbus or default
      if (NIMBUS_LOOK_AND_FEEL)
      {
         //
         // (ref:
         // https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html )
         //
         try
         {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
               if ("Nimbus".equals(info.getName()))
               {
                  UIManager.setLookAndFeel(info.getClassName());
                  javax.swing.SwingUtilities.updateComponentTreeUI(mainWindow);
                  break;
               }
            }
         }
         catch (Exception e)
         {
            // If Nimbus is not available use default
         }
         //
         // (end ref:
         // https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html )
         //
      }
      
      // call and create the main window
      mainWindow = new AppFrame(DEBUG_MODE, model);
     
      // display window
      mainWindow.setVisible(true);

   }
   
}
