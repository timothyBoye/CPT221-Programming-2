package ams.model.exception;

/**
 * The EnrollmentException class is called when managing student
 * enrollments and an error occurs such as a student attempting 
 * to enroll into too many units.
 * 
 * @author timothyboye
 *
 */
@SuppressWarnings("serial")
public class EnrollmentException extends AMSException
{
   /**
    * Used to pass an error message describing the
    * enrollment error to the caller. This message
    * should be human readable so the caller can print
    * it to the screen.
    * 
    * @param message    Human readable String describing error
    */
   public EnrollmentException(String message)
   {
      super(message);
   }

}
