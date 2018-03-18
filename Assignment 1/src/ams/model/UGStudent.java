package ams.model;

import ams.model.exception.EnrollmentException;
import ams.model.exception.ProgramException;

/**
 * A type of Student representing Undergraduate Students, 
 * most functionality is provided by AbstractStudent.
 * 
 * @author timothyboye
 *
 */
public class UGStudent extends AbstractStudent
{
   // maximum number of cp the student can enrol in at any one time
   private static final int DEFAULT_MAX_LOAD = 60; 
   
   /**
    * Undergrad Constructor that passes the ID, name and max load
    * of the student to AbstractStudent.
    * 
    * @param _id     Studnet ID as integer
    * @param _name   Student name as String
    */
   public UGStudent(int _id, String _name)
   {
      super(_id, _name, DEFAULT_MAX_LOAD);
   }

   /**
    * Enrolls the student into a given course if it wont
    * overload the student and ALL prereqs have been met
    * satisfactorily.
    * 
    * @param   The Course object of the course to enroll into
    * @throws ProgramException      if the requirments for enrollment aren't met
    */
   @Override
   public void enrollIntoCourse(Course course) throws EnrollmentException
   {
      //Is the student overenrolled?
      if ( (super.calculateCurrentLoad() + course.getCreditPoints()) > super.getMaxLoad() )
      {
         throw new EnrollmentException("This enrollment would overrload the student");
      }
      
      // Has the student completed ALL prereqs?
      if (course.getPrereqs() != null)
      {
         String[] prereqs = course.getPrereqs();
         for(String prereq : prereqs)
         {
            // check result for prereq exists
            if (super.getResult(prereq) == null)
            {
               throw new EnrollmentException("Student has not completed: " + prereq);
            }
            
            // check the student passed the prereq
            else if ( ! super.getResult(prereq).getGrade())
            {
               throw new EnrollmentException("Student only has a failed attempt at: " + prereq);
            }
         }
      }
      
      // OK looks good, go ahead and enroll.
      super.enrollIntoCourse(course);
   }
}
