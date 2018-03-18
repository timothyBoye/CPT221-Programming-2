package ams.model;

import ams.model.exception.EnrollmentException;

/**
 * Provides the skeleton of an enrollable object
 * including methods for enrolling into and withdrawing
 * from courses.
 * 
 * @author timothyboye
 *
 */
public interface Enrollable
{
   /**
    * Enrolls the student into a given Course, provided
    * they meet all relavent enrollment criteria and
    * the course exists.
    * 
    * @param courseID      Id as a String of the course to be enrolled into
    * @throws EnrollmentException      If the student was not enrolled.
    */
   public void enrollIntoCourse(Course course) throws EnrollmentException;
   
   /**
    * Withdraws the Student from the provided Course,
    * provided the student meets the withdrawl criteria
    * and they were enrolled in the course.
    * 
    * @param courseID   Id as a String of the course to be withdrawn from
    * @throws EnrollmentException   If the student was not withdrawn.
    */
   public void withdrawFromCourse(String course) throws EnrollmentException;
   
}
