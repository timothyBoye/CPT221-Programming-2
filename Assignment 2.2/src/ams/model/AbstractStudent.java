package ams.model;

import java.util.*;

import ams.model.exception.EnrollmentException;

/**
 * @author Mikhail Perepletchikov
 */
public abstract class AbstractStudent implements Student {
	protected int studentId;
	protected String fullName;
	protected int maxLoad;
	protected List<Result> results = new ArrayList<Result>();
	protected List<Course> currentEnrollment = new ArrayList<Course>();

	public AbstractStudent(int studentId, String fullName, int maxLoad) {
		this.fullName = fullName;
		this.studentId = studentId;
		this.maxLoad = maxLoad;
	}

	public String getFullName() {
		return fullName;
	}

	public int getStudentId() {
		return studentId;
	}

	public boolean addResult(Result result) {				
		if (currentEnrollment.contains(result.getCourse())) {
			// if the result already exists, replace it with the new one
			if (results.contains(result))
				results.remove(result);
			results.add(result);
			currentEnrollment.remove(result.getCourse());
			return true;
		}		
		return false;
	}

	public boolean getResult(Course course) {
		Result temp;
		for (int i = 0; i < results.size(); i++) {
			temp = (Result) results.get(i);
			if (temp.getCourse().equals(course))
				return temp.getGrade();
		}
		return false;
	}
	
	public List <Result> getResults() {		
		return results;
	}

	public List <Course> getCurrentEnrollment() {		
		return currentEnrollment;
	}

	public int calculateCurrentLoad() {
		int load = 0;
		for (Course course : currentEnrollment)
			load += course.getCreditPoints();
		return load;
	}

	public int calculateCareerPoints() {
		int points = 0;
		Result temp;
		for (int i = 0; i < results.size(); i++) {
			temp = (Result) results.get(i);
			if (temp.getGrade() == true)
				points += temp.getCourse().getCreditPoints();
		}
		return points;
	}

	public void enrollIntoCourse(Course course) throws EnrollmentException {
		// duplicate enrollment is not allowed
		if (currentEnrollment.contains(course) || getResult(course))
			throw new EnrollmentException("DUPLICATE ENROLLMENT ...");
		currentEnrollment.add(course);		
	}

	public void withdrawFromCourse(Course course) throws EnrollmentException {
		if (!currentEnrollment.contains(course))
			throw new EnrollmentException("COURSE NOT FOUND ...");
		else
			currentEnrollment.remove(course);
	}

	public String toString() {
		return (studentId + ":" + fullName + ":" + maxLoad);		
	}
}
