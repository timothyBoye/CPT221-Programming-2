package ams.model;

import ams.model.exception.EnrollmentException;

/**
 * @author Mikhail Perepletchikov
 */
public class UGStudent extends AbstractStudent {

	public static final int MAX_LOAD = 60;

	public UGStudent(int studentId, String fullName) {
		super(studentId, fullName, MAX_LOAD);
	}

	// overloading parent method - performing checks specific to UG students
	public void enrollIntoCourse(Course course) throws EnrollmentException {
		int counter = 0;

		// perform overload check
		if (calculateCurrentLoad() + course.getCreditPoints() > maxLoad)
			throw new EnrollmentException("OVERLOAD EXCEPTION  ...");

		if (course.getPreReqs() != null) {
			// loop through the list of prereqs
			for (int i = 0; i < course.getPreReqs().length; i++) {
				// check if all prereqs were completed
				for (int j = 0; j < results.size(); j++) {
					if ((results.get(j).getCourse().getCode() == course
							.getPreReqs()[i])
							&& (results.get(j).getGrade() == true))
						counter++;
				}
			}
			if (counter == course.getPreReqs().length)
				super.enrollIntoCourse(course);
			else
				throw new EnrollmentException("PRE-REQS EXCEPTION  ...");
		} else
			super.enrollIntoCourse(course);
	}
}
