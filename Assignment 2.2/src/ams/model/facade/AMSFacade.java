package ams.model.facade;

import java.util.List;
import java.util.Map;
import ams.model.*;
import ams.model.exception.*;

/**
 * @author Mikhail Perepletchikov
 */
public class AMSFacade implements AMSModel {
	private University university;

	public AMSFacade() {
		// initialised new University
		university = new University();
	}

	public AMSFacade(Student student, Program program) {
		university = new University();
		university.setStudent(student);
		university.setProgram(program);
	}
	
	@Override
	public void addStudent(Student student) { 
		university.setStudent(student);
	}

	@Override
	public Student getStudent() {
		return university.getStudent();
	}

	@Override
	public void addProgram(Program program) { 
		university.setProgram(program);
	}

	@Override
	public Program getProgram() {
		return university.getProgram();
	}
	
	@Override
	public void addCourse(Course course) throws ProgramException {
		university.addCourse(course);
	}

	@Override
	public void removeCourse(String courseCode) throws ProgramException {
		university.removeCourse(courseCode);
	}
	
	@Override
	public Course getCourse(String courseCode)
	{
		return university.getCourse(courseCode);
	}
	
	@Override
	public Course[] getAllCourses() {	
		// convert to array
		Map <String, Course> courses = university.getProgramCourses();
		if (courses.isEmpty())
			return null;	
		return courses.values().toArray(new Course[courses.size()]);
	}

	@Override
	public void enrollIntoCourse(String courseCode) throws EnrollmentException {		
		university.enrollStudentIntoCourse(courseCode);		
	}

	@Override
	public void withdrawFromCourse(String courseCode) throws EnrollmentException{	
		university.withdrawStudentFromCourse(courseCode);		
	}

	@Override
	public boolean addResult(Result result) {
		return university.addStudentResult(result);
	}

	@Override
	public Result[] getResults() {
		// convert to array
		List <Result> results = university.getStudentResults();
		if (results.isEmpty())
			return null;	
		return results.toArray(new Result[results.size()]);		
	}

	@Override
	public Course[] getCurrentEnrollment() {
		// convert to array
		List <Course> enrollment= university.getCurrentStudentEnrollment();
		if (enrollment.isEmpty())
			return null;	
		return enrollment.toArray(new Course [enrollment.size()]);
	}
	
	@Override
	public int calculateCurrentLoad() {
		return university.calculateCurrentStudentLoad();
	}

	@Override
	public int calculateCareerPoints() {
		return university.calculateTotalCareerPoints();
	}

	@Override
	public int countCoreCourses() {		
		return university.countCoreCourses();
	}

	@Override
	public int countElectiveCourses() {		
		return university.countElectiveCourses();
	}    
}