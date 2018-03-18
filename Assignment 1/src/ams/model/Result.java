package ams.model;

/**
 * Class representation of a given result for a student
 * from a given course.
 * 
 * @author timothyboye
 *
 */
public class Result
{
   private boolean grade; // the students result for the course true = pass
   private Course course; // the course the grade is for
   
   /**
    * Constructor takes the course and matching result
    * and stores in the object.
    * 
    * @param _course    the course as a Course object
    * @param _result    the matching result as a boolean (PASS[true]/FAIL[false])
    */
   public Result(Course _course, boolean _result)
   {
      course = _course;
      grade = _result;
   }
   
   /**
    * Returns the course that the result belongs to
    * 
    * @return     the course as a Course object
    */
   public Course getCourse()
   {
      return course;
   }
   
   /**
    * Returns the course code of the course
    * 
    * @return     Course code as String
    */
   public String getCourseCode()
   {
      return course.getCode();
   }
   
   /**
    * Returns the grade the student received
    * 
    * @return     PASS[true] / FAIL[false]
    */
   public boolean getGrade()
   {
      return grade;
   }
   
   /**
    * Replaces the current grade with a new grade
    * 
    * @param _grade     PASS[true] / FAIL[false]
    */
   public void setGrade(boolean _grade)
   {
      grade = _grade;
   }
   
   /**
    * Builds a string representation of the object
    * 
    * @return     "[course_code]:[grade]"
    */
   @Override
   public String toString()
   {
      // construct the string to be returned
      StringBuilder string = new StringBuilder();
      
      // append the course code and colon
      string.append(course.getCode());
      string.append(":");
      
      // append the grade true (PASS) / flase (FAIL)
      if (this.getGrade())
         string.append("PASS");
      else
         string.append("FAIL");
      
      // Return the string
      return string.toString();
   }
   

}
