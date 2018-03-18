package ams.model.facade;

import java.util.Map;

import ams.model.Course;
import ams.model.Program;
import ams.model.Result;
import ams.model.Student;
import ams.model.University;
import ams.model.exception.EnrollmentException;
import ams.model.exception.ProgramException;
/**
 * Implements the AMSModel interface to provide access
 * to the system from the client classes. AMSFacade
 * contains a University object and all AMSModel methods
 * implement a call to relavent methods in the University 
 * object and any type conversions required by the client.
 * 
 * @author Timothy Boye
 * 
 */
public class AMSFacade implements AMSModel {
   
   private University uni;
   
   /**
    * AMSFacade constructor
    */
   public AMSFacade()
   {
      super();
      uni = new University();
   }
   
   /**
    * Adds/Replaces the student object
    * 
    * @param _Student    pre constructed student object which 
    *                    will replace the current student object
    */
   @Override
   public void addStudent(Student _Student)
   {
      uni.addStudent(_Student);
   }

   /**
    * Returns a reference to the current Student object
    * 
    * @return     reference to the current student object
    */
   @Override
   public Student getStudent()
   {
      return uni.getStudent();
   }

   /**
    * Adds/Replaces the program object
    * 
    * @param _program      pre constructed program object which 
    *                      will replace the current program object
    */
   @Override
   public void addProgram(Program _program)
   {
      uni.addProgram(_program);
   }

   /**
    * Returns a reference to the current program object
    * 
    * @return     reference to the current program object
    */
   @Override
   public Program getProgram()
   {
      return uni.getProgram();
   }

   /**
    * Adds a new pre constructed course to the program
    * if it doesn't exist and its prerequisite courses
    * have already been added.
    * 
    * @param course              pre constructed Course object
    * @throws ProgramException   if course not added
    */
   @Override
   public void addCourse(Course course) throws ProgramException
   {
      uni.addCourse(course);
   }

   /**
    * removes the course matching the provided
    * course ID if the course exists and isn't
    * required by other courses.
    * 
    * @param courseID            a courses unique code as String
    * @throws ProgramException   if course not removed
    */
   @Override
   public void removeCourse(String courseId) throws ProgramException
   {
      uni.removeCourse(courseId);
   }

   /**
    * Returns the course object corresponding to
    * the course code provided.
    * 
    * @param courseCode    Courses unique code as String
    * @return Course       Reference to the Course object 
    *                      matching the courseCode
    */
   @Override
   public Course getCourse(String courseCode)
   {
      return uni.getCourse(courseCode);
   }

   /**
    * Returns all courses in the program
    * 
    * @return Course[]  returns an array of all course objects
    */
   @Override
   public Course[] getAllCourses()
   {
      // get all courses from uni
      Map<String, Course> courses = uni.getAllCourses();
      
      // check if courses empty?
      if (courses.size() < 1)
      {
         return null;
      }
      // not empty? convert courses map to courses array and return array
      else
      {
         Course[] coursesArray = courses.values().toArray(new Course[courses.size()]);
         return coursesArray;
      }
   }

   /**
    * Enrols the student into a given course provided
    * the course is a valid selection for that student
    * 
    * @param courseID               The Courses unique ID as a String
    * @throws EnrollmentException   if the student was prevented from enrolling
    */
   @Override
   public void enrollIntoCourse(String courseID) throws EnrollmentException
   {
      uni.enrollIntoCourse(courseID);
   }

   /**
    * Withdraws the student from a given course provided
    * the withdrawl is a valid selection for that student
    * 
    * @param courseID               The Courses unique ID as a String
    * @throws EnrollmentException   If the student was prevented from withdrawing
    */
   @Override
   public void withdrawFromCourse(String courseID) throws EnrollmentException
   {
      uni.withdrawFromCourse(courseID);
   }

   /**
    * Adds a result for a student povided the student
    * was enrolled in the given course and then removes
    * the course from the students list of enrolled courses
    * 
    * @param result     Preconstructed Result object
    * @return           true (result recorded) / false (result not recorded)
    */
   @Override
   public boolean addResult(Result result)
   {
      return uni.addResult(result);
   }

   /**
    * Returns all results for the student
    * 
    * @return     Array of Result objects
    */
   @Override
   public Result[] getResults()
   {
      // get all results from uni
      Map<String, Result> results = uni.getResults();
      
      // check if there are no results
      if (results.size() < 1)
      {
         return null;
      }
      // there are results? convert them from map to array and return the array
      else
      {
         Result[] resultsArray = results.values().toArray(new Result[results.size()]);
         return resultsArray;
      }
   }

   /**
    * Returns all courses the student is enrolled in
    * 
    * @return  Array of Course objects
    */
   @Override
   public Course[] getCurrentEnrollment()
   {
      // get enrolled courses from uni
      Map<String, Course> courses = uni.getCurrentEnrollment();
      
      // check there are any enrolled courses
      if (courses.size() < 1)
      {
         return null;
      }
      // there are? convert from map to array and return array
      else
      {
         Course[] courseArray = courses.values().toArray(new Course[courses.size()]);
         return courseArray;
      }
   }

   /**
    * Calculates the credit point total of the students
    * currently enrolled units
    * 
    * @return     CP total as int
    */
   @Override
   public int calculateCurrentLoad()
   {
      return uni.calculateCurrentLoad();
   }

   /**
    * Calculates the credit point total of the students
    * completed units
    * 
    * @return  cp total as int
    */
   @Override
   public int calculateCareerPoints()
   {
      return uni.calculateCareerPoints();
   }

   /**
    * Counts the number of CoreCourses in the program
    * 
    * @return  number of CoreCourses as int
    */
   @Override
   public int countCoreCourses()
   {
      return uni.countCoreCourses();
   }

   /**
    * Counts the number of ElectiveCourses in the program
    * 
    * @return  number of ElectiveCourses as int
    */
   @Override
   public int countElectiveCourses()
   {
      return uni.countElectiveCourses();
   }	
}