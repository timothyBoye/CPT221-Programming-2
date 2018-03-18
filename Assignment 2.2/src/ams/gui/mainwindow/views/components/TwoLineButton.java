package ams.gui.mainwindow.views.components;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Creates a GUI button based on swing.JButton that can
 * have up to two lines of text on the label of the button.
 * 
 * @author timothyboye
 *
 */
@SuppressWarnings("serial")
public class TwoLineButton extends JButton
{
   // array of strings the button should represent
   private String[] text;
   // the buttons labels
   private JLabel line1 = new JLabel();
   private JLabel line2 = new JLabel();
   
   /**
    * Creates a new TwoLineButton that splits the _text
    * String where it find a new line character \n and 
    * displays that text across a two line button.
    * 
    * @param _text    button label with \n where the second
    *                 line should begin (any further \n's 
    *                 will trim the remaining substrings and
    *                 won't be displayed).
    */
   public TwoLineButton(String _text)
   {
      // get the buttons lines of text
      text = _text.split("\n");
      
      // set the first two lines onto the two labels
      line1.setText(text[0]);
      line2.setText(text[1]);

      // need a border layout to display properly
      this.setLayout(new BorderLayout());
      
      // centre the text
      line1.setHorizontalAlignment(SwingConstants.CENTER);
      line2.setHorizontalAlignment(SwingConstants.CENTER);

      // add the labels to the button
      this.add(line1, BorderLayout.NORTH);
      this.add(line2, BorderLayout.SOUTH);
   }
   
   /**
    * Splits the _text String where it finds a new line 
    * character \n and displays that text across a two 
    * line button.
    * 
    * @param _text    button label with \n where the second
    *                 line should begin (any further \n's 
    *                 will trim the remaining substrings and
    *                 won't be displayed).
    */
   @Override
   public void setText(String _text)
   {
      // get the buttons lines of text
      text = _text.split("\n");
      
      // set the first two lines to the text for the two labels
      line1.setText(text[0]);
      line2.setText(text[1]);
   }
}
