package ams.model;

import java.util.HashMap;
import java.util.Map;

import ams.model.exception.ProgramException;
import ams.model.visitor.CourseCountVisitor;

/**
 * The Program class provides all functionality
 * related to creating and maintaining a university
 * program.
 * 
 * @author timothyboye
 *
 */
public class Program
{
   private String code;    // program's unique code
   private String title;   // programs full title
   
   // list of all courses in the program
   private Map<String, Course> courses = new HashMap<String, Course>(); 
   
   /**
    * Program Constuctor
    * 
    * @param _code   unique code for program as String
    * @param _title  full program title as String
    */
   public Program(String _code, String _title)
   {
      code = _code;
      title = _title;
   }
   
   /**
    * Adds a new pre constructed course to the program
    * if it doesn't exist and its prerequisite courses
    * have already been added.
    * 
    * @param course              pre constructed Course object
    * @throws ProgramException   if course not added
    */
   public void addCourse(Course course) throws ProgramException
   {
      // get course prereqs
      if (course.getPrereqs() != null)
      {
         String[] prereqs = course.getPrereqs();
         // check prereqs ALL exist in program
         for(String prereq : prereqs)
         {
            // if ANY prereqs don't already exist throw exception
            if (getCourse(prereq) == null)
            {
               throw new ProgramException("Prerequisite missing.");
            }
         }
      }
      
      // if ALL prereqs exist then add the course
      courses.put(course.getCode(), course);
   }
   
   /**
    * removes the course matching the provided
    * course ID if the course exists and isn't
    * required by other courses.
    * 
    * @param courseID            a courses unique code as String
    * @throws ProgramException   if course not removed
    */
   public void removeCourse(String _course) throws ProgramException
   {
      // Loop through all courses and check if this unit is required still
      for (Course course : courses.values())
      {
         if (course.hasPrereq(_course))
         {
            throw new ProgramException("This course is still a required unit.");
         }
      }
      // mustn't be required so remove course
      courses.remove(_course);
   }

   /**
    * Returns the course object corresponding to
    * the course code provided.
    * 
    * @param courseCode    Courses unique code as String
    * @return Course       Reference to the Course object 
    *                      matching the courseCode
    */
   public Course getCourse(String courseCode)
   {
      // check course exists and return course or null.
      if (courses.containsKey(courseCode))
         return courses.get(courseCode);
      else
         return null;
   }
   
   /**
    * Returns all courses in the program
    * 
    * @return   Map<Course_id, matching_course_object>
    *           returns a map of all course objects
    */
   public Map<String, Course> getAllCourses()
   {
         return courses;
   }
   
   /**
    * Counts the number of ElectiveCourses in the program
    * using the visitor design pattern
    * 
    * @return  number of ElectiveCourses as int
    */
   public int countElectiveCourses()
   {
      // create a visitor to count the courses
      CourseCountVisitor visitor = new CourseCountVisitor();
      // loop through all courses and call on them to visit the visitor
      for (Course course : courses.values())
      {
         course.accept(visitor);
      }
      // get the final count of electives and return it
      return visitor.getElectiveCount();
   }
   
   /**
    * Counts the number of CoreCourses in the program
    * using the visitor design pattern
    * 
    * @return  number of CoreCourses as int
    */
   public int countCoreCourses()
   {
      // create a visitor to count the courses
      CourseCountVisitor visitor = new CourseCountVisitor();
      // loop through all courses and call on them to visit the visitor
      for (Course course : courses.values())
      {
         course.accept(visitor);
      }
      // get the final count of cores and return it
      return visitor.getCoreCount();
   }
   
   /**
    * Builds a string representation of the object
    * 
    * @return     "[program_code]:[program_title]"
    */
   public String toString()
   {
      // build the string to be output
      StringBuilder string = new StringBuilder();
      
      // append the code + : + title
      string.append(code);
      string.append(":");
      string.append(title);
      
      // string complete, return it
      return string.toString();
   }
}
