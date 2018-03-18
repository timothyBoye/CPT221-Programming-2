package ams.model;

import java.util.Map;
/**
 * Student interface providing the skeleton
 * for the common methods of the various 
 * student types.
 * 
 * @author timothyboye
 *
 */
public interface Student extends Enrollable
{
   /**
    * Returns the full name of the student
    * @return     student name as String
    */
   public String getFullName();
   
   /**
    * Returns the ID number of the student
    * @return     ID as int
    */
   public int getStudentID();

   /**
    * Sums the total number of credit points for each
    * unit the student is currently enrolled in.
    * @return     credit point total as int
    */
   public int calculateCurrentLoad();
   
   /**
    * Returns the maximum number of credit points the 
    * student is allowed to enrol in at any one time
    * @return     credit point value as int
    */
   public int getMaxLoad();
   
   /**
    * Returns a sum of the total number of credit points for each
    * unit the student has successfully completed.
    * @return     credit point value as int
    */
   public int calculateCareerPoints();
   
   /**
    * Accepts a Result object and if that objects related course
    * is in the students enrolled courses list adds the result to
    * the students results and removes the unit from the current
    * enrolments
    * @param result     pre constructed Result object
    * @return     true(result added and unit removed from current enrolments) / 
    *             false(course wasn't in current enrollments, no changes occured)
    */
   public boolean addResult(Result result);
   
   /**
    * Returns the result for the unit specified if one exists
    * @param _code      course code of the unit whos result is to be returned
    * @return     Result object related to the course provided
    */
   public Result getResult(String _code);
   
   /**
    * Returns a map of ALL Result objects attached to this student
    * @return     Map<Course_code, Matching Result object>
    */
   public Map<String, Result> getAllResults();
   
   /**
    * Returns a map of ALL Courses the student is currently enrolled in
    * @return     Map<Course_code, Matching Course Object>
    */
   public Map<String, Course> getCurrentEnrollment();
   
   /**
    * Builds a string representation of the object
    * 
    * @return     "[student_id]:[full_name]:[maximum_load]"
    */
   public String toString();
   
}
