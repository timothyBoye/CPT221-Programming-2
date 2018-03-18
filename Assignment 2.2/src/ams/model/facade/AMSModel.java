package ams.model.facade;

import ams.model.*;
import ams.model.exception.*;

/**
 * @author Mikhail Perepletchikov  
 * IMPORTANT: your AMSFacade class must implement this interface
*/

public interface AMSModel {

	// adds new student to university
	public void addStudent(Student newStudent);
	
	// returns the (current) student
	public Student getStudent();

	// adds new program to university
	public void addProgram(Program program);
	
	// returns the (current) program
	public Program getProgram();	

	/*- add/remove course to/from the (current) program -*/
	/** throws exception if courses specified as prereqs for newCourse do not exist */
	public void addCourse(Course course) throws ProgramException;
	/** throws exception if the course to be removed is a prereq for the other course/s*/
	public void removeCourse(String courseId) throws ProgramException;

	// returns a course according to the provided courseCode
	public Course getCourse(String courseCode);

	// returns a collection of all courses in the (current) program
	public Course[] getAllCourses();

	/*- enrol/withdraw student to/from a given course (defined by courseID) -*/
	/** throws exceptions when the enrolment constraints are not maintained
	 * (refer to Ass1 specs document)*/
	public void enrollIntoCourse(String courseID) throws EnrollmentException;
	/** throws exceptions if the student is not currently enrolled into the
	 * course (defined by the courseId) */
	public void withdrawFromCourse(String courseID) throws EnrollmentException;
	
	// adds a new result. returns false if the course associated with this
    // result does not exist in the collection of all currently enrolled
    // courses. Note: you need to remove the associated course from the
    // collection of currently enrolled courses upon entering the result to 
    // indicate that this course is no longer current.
    public boolean addResult(Result result);

    // returns a collection of all results for the (current) student
    public Result[] getResults();

    // returns a collection of all currently enrolled courses for the student
    public Course[] getCurrentEnrollment();

	// returns the total number of credit points for all currently enrolled courses
	public int calculateCurrentLoad();

	// returns the total number of credit points for all completed (passed) courses
	public int calculateCareerPoints();
	
	// NOTE: you are allowed to use the instanceof operator to achieve this
	public int countCoreCourses();  
	
	// NOTE: you are allowed to use the instanceof operator to achieve this
	public int countElectiveCourses(); 	
}
