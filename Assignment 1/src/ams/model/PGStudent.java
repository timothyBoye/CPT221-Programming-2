package ams.model;

import java.util.Iterator;

import ams.model.exception.EnrollmentException;
import ams.model.exception.ProgramException;

/**
 * A type of Student representing Postgraduate Students, 
 * most functionality is provided by AbstractStudent.
 * 
 * @author timothyboye
 *
 */
public class PGStudent extends AbstractStudent
{
   // maximum number of cp the student can enrol in at any one time
   private static final int DEFAULT_MAX_LOAD = 48;
   // maximum number student can go over MAX_LOAD if they have no fails
   private static final int DEFAULT_MAX_OVERLOAD = 6;
   
   /**
    * Postgrad constructor that passes the ID, name and max load
    * of the student to AbstractStudent.
    * 
    * @param _id     Studnet ID as integer
    * @param _name   Student name as String
    */
   public PGStudent(int _id, String _name)
   {
      super(_id, _name, DEFAULT_MAX_LOAD);
   }

   /**
    * Enrolls the student into a given course if it wont
    * overload the student and ANY prereqs have been met
    * satisfactorily.
    * 
    * @param   The Course object of the course to enroll into
    * @throws ProgramException      if the requirments for enrollment aren't met
    */
   @Override
   public void enrollIntoCourse(Course course) throws EnrollmentException
   {      
      //Is the student overenrolled?
      int studyLoadAfterEnrollment = super.calculateCurrentLoad() + course.getCreditPoints();
      if (studyLoadAfterEnrollment > super.getMaxLoad())
      {
         //How about by more than MAX_OVERLOAD?
         if ( (studyLoadAfterEnrollment) > super.getMaxLoad() + DEFAULT_MAX_OVERLOAD )
         {
            throw new EnrollmentException("This enrollment would overload the student ");
         }
         
         //has failed grades?
         Iterator<Result> results = super.getAllResults().values().iterator();
         while(results.hasNext())
         {
            if ( ! results.next().getGrade() )
            {
               throw new EnrollmentException("This student has failed grades and is thus ineligible for overloading.");
            }
         }
      }
      
      //Has ANY prereqs been met?
      if (course.getPrereqs() != null)
      {
         String[] prereqs = course.getPrereqs();
         boolean noPrereqsMet = true;
         for(String prereq : prereqs)
         {
            // check the student passed the prereq
            if (super.getResult(prereq).getGrade())
            {
               noPrereqsMet = false;
               break;
            }
         }
         if (noPrereqsMet)
         {
            throw new EnrollmentException("Student has not passed any of the required units.");
         }
      }
      
      // OK looks good, go ahead and enroll.
      super.enrollIntoCourse(course);
   }
}
