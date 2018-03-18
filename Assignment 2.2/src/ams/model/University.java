package ams.model;

import java.util.List;
import java.util.Map;
import ams.model.exception.EnrollmentException;
import ams.model.exception.ProgramException;

/**
 * @author Mikhail Perepletchikov
 */
public class University {

	private Student student;
	private Program program;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public void addCourse(Course course) throws ProgramException {
		program.addCourse(course);
	}

	public void removeCourse(String courseCode) throws ProgramException {
		program.removeCourse(courseCode);
	}

	public Course getCourse(String courseCode) {
		return program.getCourse(courseCode);
	}

	public Map<String, Course> getProgramCourses() {
		return program.getAllCourses();
	}

	public void enrollStudentIntoCourse(String courseCode)
			throws EnrollmentException {
		student.enrollIntoCourse(program.getCourse(courseCode));
	}

	public void withdrawStudentFromCourse(String courseCode) throws EnrollmentException{

		student.withdrawFromCourse(program.getCourse(courseCode));
	}

	public boolean addStudentResult(Result result) {
		return student.addResult(result);
	}

	public List <Result> getStudentResults() {
		return student.getResults();
	}

	public List <Course> getCurrentStudentEnrollment() {
		return (student.getCurrentEnrollment());
	}

	public int calculateCurrentStudentLoad() {
		return student.calculateCurrentLoad();
	}

	public int calculateTotalCareerPoints() {
		return student.calculateCareerPoints();
	}

	public int countCoreCourses() {
		return program.countCoreCourses();
	}

	public int countElectiveCourses() {
		return program.countElectiveCourses();
	}
}
