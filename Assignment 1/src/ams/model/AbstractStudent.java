package ams.model;

import java.util.HashMap;
import java.util.Map;

import ams.model.exception.EnrollmentException;

/**
 * Implementation of the Student and enrollable
 * interfaces providing all common functionality
 * for all subclasses of AbstractStudent.
 * 
 * @author timothyboye
 *
 */
public abstract class AbstractStudent implements Student
{
   private int studentId;        // Unique student number
   private String studentName;   // full name of student
   private int maxStudyLoad;     // maximum number of CP student can enrol in at once
   
   // map of all courses the student is currently enrolled in
   private Map<String, Course> enrolledCourses = new HashMap<String,Course>();
   // map of all results the student has from completed units
   private Map<String, Result> results = new HashMap<String,Result>();
   
   /**
    * AbstractStudent Constructor
    * @param _id     Students identification number as int
    * @param _name   Students full name as String
    * @param _load   Students maximum allowable credit point load as int
    */
   public AbstractStudent(int _id, String _name, int _load)
   {
      studentId = _id;
      studentName = _name;
      maxStudyLoad = _load;
   }

   /**
    * Returns the full name of the student
    * @return     student name as String
    */
   @Override
   public String getFullName()
   {
      return studentName;
   }
   
   /**
    * Returns the ID number of the student
    * @return     ID as int
    */
   @Override
   public int getStudentID()
   {
      return studentId;
   }

   /**
    * Sums the total number of credit points for each
    * unit the student is currently enrolled in.
    * @return     credit point total as int
    */
   @Override
   public int calculateCurrentLoad()
   {
      // loop through all enrolled courses and 
      // add their values to the load counter
      int load = 0;
      for(Course course : enrolledCourses.values())
      {
         load += course.getCreditPoints();
      }
      
      // return the count
      return load;
   }
   
   /**
    * Returns the maximum number of credit points the 
    * student is allowed to enrol in at any one time
    * @return     credit point value as int
    */
   @Override
   public int getMaxLoad()
   {
      return maxStudyLoad;
   }
   
   /**
    * Returns a sum of the total number of credit points for each
    * unit the student has successfully completed.
    * @return     credit point value as int
    */
   @Override
   public int calculateCareerPoints()
   {
      // check there are results (if there aren't return 0)
      if (results.isEmpty())
      {
         return 0;
      }
      // there are results so count their CP
      else
      {
         // loop through all results, if the result is a pass
         // add the related courses cp to the total
         int careerPoints = 0;
         for (Result tempResult : results.values())
         {
            if (tempResult.getGrade())
            {
               careerPoints += tempResult.getCourse().getCreditPoints();
            }
         }
         
         // return the total
         return careerPoints;
      }
   }
   
   /**
    * Accepts a Result object and if that objects related course
    * is in the students enrolled courses list adds the result to
    * the students results and removes the unit from the current
    * enrolments
    * @param result     pre constructed Result object
    * @return     true(result added and unit removed from current enrolments) / 
    *             false(course wasn't in current enrollments, no changes occured)
    */
   @Override
   public boolean addResult(Result _result)
   {
      // check the student is enrolled in the Result's course
      if (enrolledCourses.containsKey(_result.getCourseCode()))
      {
         // add the result to the map
         results.put(_result.getCourseCode(), _result);
         // remove the course from enrollments
         enrolledCourses.remove(_result.getCourseCode());
         
         // All A OK, return true.
         return true;
      }
      // course not enrolled, do nothing, return false
      else
         return false;
   }
   
   /**
    * Returns the result for the unit specified if one exists
    * @param _code      course code of the unit whos result is to be returned
    * @return     Result object related to the course provided
    */
   @Override
   public Result getResult(String _code)
   {
      // check the result exists and return it or null
      if (results.containsKey(_code))
         return results.get(_code);
      else
         return null;
   }
   
   /**
    * Returns a map of ALL Result objects attached to this student
    * @return     Map<Course_code, Matching Result object>
    */
   @Override
   public Map<String, Result> getAllResults()
   {
      return results;
   }
   
   /**
    * Returns a map of ALL Courses the student is currently enrolled in
    * @return     Map<Course_code, Matching Course Object>
    */
   @Override
   public Map<String, Course> getCurrentEnrollment()
   {
      return enrolledCourses;
   }
   
   /**
    * Withdraws the Student from the provided Course,
    * provided the student meets the withdrawl criteria
    * and they were enrolled in the course.
    * 
    * @param courseID   Id as a String of the course to be withdrawn from
    * @throws EnrollmentException   If the student was not withdrawn.
    */
   @Override
   public void withdrawFromCourse(String course) throws EnrollmentException
   {
      // check the course is actually enrolled in then remove it
      if(enrolledCourses.containsKey(course))
      {
         enrolledCourses.remove(course);
      }
      // course not enrolled before being withdrawn? throw error 
      else
         throw new EnrollmentException("Not enrolled in this course.");
   }
   
   /**
    * Enrolls the student into a given Course, provided
    * they meet all relavent enrollment criteria and
    * the course exists.
    * 
    * @param courseID      Id as a String of the course to be enrolled into
    * @throws EnrollmentException      If the student was not enrolled.
    */
   @Override
   public void enrollIntoCourse(Course course) throws EnrollmentException
   {
      // Does the enrollment already exist?
      if(enrolledCourses.containsKey(course.getCode()))
      {
         throw new EnrollmentException("Already enrolled in this course.");
      }
      
      // Has the course been completed previously?
      else if (results.containsKey(course.getCode()))
      {
         // IF so was it completed satisfactorily?
         if ( results.get(course.getCode()).getGrade() )
         {
            throw new EnrollmentException("Already completed this course.");
         }
      }
      
      // OK looks good, go ahead and enroll.
      enrolledCourses.put(course.getCode(), course);
   }
   
   /**
    * Builds a string representation of the object
    * 
    * @return     "[student_id]:[full_name]:[maximum_load]"
    */
   @Override
   public String toString()
   {
      // build a string to be returned
      StringBuilder string = new StringBuilder();
      
      // append studentId : studentName : maxload
      string.append(studentId);
      string.append(":");
      string.append(studentName);
      string.append(":");
      string.append(maxStudyLoad);
      
      // return the constructed string
      return string.toString();
   }
}
