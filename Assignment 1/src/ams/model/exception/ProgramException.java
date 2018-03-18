package ams.model.exception;

/**
 * The ProgramException class is called when managing
 * the university programs contents such as adding and
 * removing courses from the program, the error would be
 * called when adding a unit that has unmet prerequisites
 * or removing a course that is a prerequisite for another.
 * 
 * @author timothyboye
 *
 */
@SuppressWarnings("serial")
public class ProgramException extends AMSException
{
   /**
    * Used to pass an error message describing the
    * enrollment error to the caller. This message
    * should be human readable so the caller can print
    * it to the screen.
    * 
    * @param message    Human readable String describing error
    */
   public ProgramException(String message)
   {
      super(message);
   }

}
