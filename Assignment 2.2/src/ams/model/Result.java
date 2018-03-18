package ams.model;

/**
 * @author Mikhail Perepletchikov
 */
public class Result {

	public static final String PASS_GRADE = "pass";
	public static final String FAIL_GRADE = "fail";
	private Course course;
	private boolean grade;

	public Result(Course course, boolean grade) {
		this.course = course;
		this.grade = grade;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public boolean getGrade() {
		return grade;
	}

	public void setGrade(boolean grade) {
		this.grade = grade;
	}

	public String toString() {
		String resultString = course.getCode() + ":";
		if (grade)
			resultString += PASS_GRADE;
		else
			resultString += FAIL_GRADE;
		return resultString;
	}
	
	// basic implementation of the equals() based on course equality
	public boolean equals(Object in) {
		Result obj = (Result) in;
		return (course == obj.getCourse());			
	}	
}