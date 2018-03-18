package ams.model;
import ams.model.exception.EnrollmentException;

/**
 * @author Mikhail Perepletchikov
 */
public interface Enrollable {
	public void enrollIntoCourse (Course course) throws EnrollmentException;
    public void withdrawFromCourse (Course course) throws EnrollmentException;
}
