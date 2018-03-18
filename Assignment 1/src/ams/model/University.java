package ams.model;

import java.util.Map;

import ams.model.exception.EnrollmentException;
import ams.model.exception.ProgramException;

/**
 * The University class is the main routing class of
 * the AMS system, it instantiates the Program class and
 * Student class and has all methods required to interact
 * with them.
 * 
 * Calls to university are routed to its program and 
 * student objects very little is performed directly here.
 * 
 * @author timothyboye
 *
 */
public class University
{
   // Store the program and student
   private Program program;
   private Student student;
   
   /**
    * Default constructor, as the program and student
    * are explicitly instantiated by the caller with 
    * the addStudent and addProgram methods the 
    * constructor DOES NOT instantiate program and student
    * and thus only needs to call super.
    */
   public University()
   {
      // Calls the Object default constructor explicitly for completeness
      // Would be called anyway but YOLO
      super();
   }

   /**
    * Takes a pre constructed Student object and stores it 
    * as THE university Student. CAUTION this method overwrites 
    * any previous students WITHOUT WARNING.
    * 
    * @param newStudent    New pre constructed Student object
    */
   public void addStudent(Student newStudent)
   {
      student = newStudent;
   }

   /**
    * Returns the current Student object to the caller
    * for modification or reading. CAUTION this is not
    * a copy but a passed reference to the actual Student
    * all modifications to the returned Student will affect
    * the University's Student.
    * 
    * @return     University's Student object
    */
   public Student getStudent()
   {
      return student;
   }

   /**
    * Takes a pre constructed Program object and stores it 
    * as THE university Program. CAUTION this method overwrites 
    * any previous prorgams WITHOUT WARNING.
    * 
    * @param _program    New pre constructed Program object
    */
   public void addProgram(Program _program)
   {
      program = _program;
   }

   /**
    * Returns the current Program object to the caller
    * for modification or reading. CAUTION this is not
    * a copy but a passed reference to the actual Program
    * all modifications to the returned Program will affect
    * the University's Program.
    * 
    * @return     University's Program object
    */
   public Program getProgram()
   {
      return program;
   }

   /**
    * Adds a new course to the Program
    * 
    * @param course     new pre constructed Course object (CoreCourse or ElectiveCourse)
    * @throws ProgramException      if course not added for any reason
    */
   public void addCourse(Course course) throws ProgramException
   {
      program.addCourse(course);
   }

   /**
    * Removes a given course from the program if it exists
    * 
    * @param courseId      The course ID as a String of the course to be removed 
    * @throws ProgramException     If the course ID could not be found.
    */
   public void removeCourse(String courseId) throws ProgramException
   {
      program.removeCourse(courseId);
   }

   /**
    * Returns the Course object matching the provided course ID
    * 
    * @param courseCode    The ID as a String of the course to return 
    * @return     The Course object matching the ID provided or NULL if no match.
    */
   public Course getCourse(String courseCode)
   {
      return program.getCourse(courseCode);
   }

   /**
    * Returns a Map of all Course objects currently added to the program
    * and their matching course IDs as the key.
    * 
    * @return     Map<ID as String, matching Course object>
    */
   public Map<String, Course> getAllCourses()
   {
      return program.getAllCourses();
   }

   /**
    * Enrolls the student into a given Course, provided
    * they meet all relavent enrollment criteria and
    * the course exists.
    * 
    * @param courseID      Id as a String of the course to be enrolled into
    * @throws EnrollmentException      If the student was not enrolled.
    */
   public void enrollIntoCourse(String courseID) throws EnrollmentException
   {
      student.enrollIntoCourse(program.getCourse(courseID));
   }

   /**
    * Withdraws the Student from the provided Course,
    * provided the student meets the withdrawl criteria
    * and they were enrolled in the course.
    * 
    * @param courseID   Id as a String of the course to be withdrawn from
    * @throws EnrollmentException   If the student was not withdrawn.
    */
   public void withdrawFromCourse(String courseID) throws EnrollmentException
   {
      student.withdrawFromCourse(courseID);      
   }

   /**
    * Adds a result for a given course the student is 
    * enrolled in and removes the student from the course 
    * as they have completed it.
    * 
    * @param result     Result object for a given course.
    * @return           True if Result added, false if there was a problem
    */
   public boolean addResult(Result result)
   {
      return student.addResult(result);
   }

   /**
    * Gets a Map of all Results for the current Student
    * 
    * @return     Map<Course ID as String, matching Result object>
    */
   public Map<String, Result> getResults()
   {
      return student.getAllResults();
   }

   /**
    * Gets a Map of all enrolled courses for the current Student
    * 
    * @return     Map<Course ID as String, matching Course object>
    */
   public Map<String, Course> getCurrentEnrollment()
   {
      return student.getCurrentEnrollment();
   }

   /**
    * Calculates the credit point study load of the student
    * 
    * @return     integer value equal to the sum of CP values for 
    *             all courses the student is currently enrolled in
    */
   public int calculateCurrentLoad()
   {
      return student.calculateCurrentLoad();
   }

   /**
    * Calculates how many credit points worth of Courses
    * the student has completed.
    * 
    * @return     integer value equal to the sum of CP values for
    *             all courses the student has completed satisfactorily
    */
   public int calculateCareerPoints()
   {
      return student.calculateCareerPoints();
   }

   /**
    * Counts how many of the Courses in the Program are
    * CoreCourses
    * 
    * @return     Integer number of CoreCourses in the Program
    */
   public int countCoreCourses()
   {
      return program.countCoreCourses();
   }

   /**
    * Counts how many of the Courses in the Program are
    * ElectiveCourses
    * 
    * @return     Integer number of ElectiveCourses in the Program
    */
   public int countElectiveCourses()
   {
      return program.countElectiveCourses();
   }

}
