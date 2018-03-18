package ams.model;

import ams.model.exception.EnrollmentException;

/**
 * @author Mikhail Perepletchikov
 */
public class PGStudent extends AbstractStudent {
	public static final int MAX_LOAD = 48;
	public static final int OVERLOAD_LIMIT = 6;

	public PGStudent(int studentId, String fullName) {
		super(studentId, fullName, MAX_LOAD);
	}

	// overloading parent method - performing checks specific to PG students
	public void enrollIntoCourse(Course course) throws EnrollmentException {

		boolean flag = false;
		
		// perform overload check
		int crPoints = calculateCurrentLoad() + course.getCreditPoints();
		if ((crPoints > maxLoad)
				&& ((crPoints > (maxLoad + OVERLOAD_LIMIT)) || !checkForFailures()))
			throw new EnrollmentException("OVERLOAD EXCEPTION ...");

		// check that AT LEAST ONE prereq is passed
		if (course.getPreReqs() != null) {
			// loop through the list of prereqs
			for (int i = 0; i < course.getPreReqs().length; i++) {
				// check if prereqs were completed
				for (int j = 0; j < results.size(); j++) {
					if ((results.get(j).getCourse().getCode() == course
							.getPreReqs()[i])
							&& (results.get(j).getGrade() == true))
						flag = true;
				}
			}
			if (!flag)
				throw new EnrollmentException("PRE-REQS EXCEPTION  ...");
			super.enrollIntoCourse(course);
		} else
			super.enrollIntoCourse(course);
	}

	// check if the student had failed any courses previously
	private boolean checkForFailures() {
		Result temp;
		for (int i = 0; i < results.size(); i++) {
			temp = (Result) results.get(i);
			if (temp.getGrade() == false)
				return false;
		}
		return true;
	}
}

