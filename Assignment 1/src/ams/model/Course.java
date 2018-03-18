package ams.model;

import ams.model.visitor.Visitable;

/**
 * Course interface providing the skeleton
 * for the common methods of the various 
 * course types.
 * 
 * @author timothyboye
 *
 */
public interface Course extends Visitable
{
   /**
    * Returns the courses unique code
    * @return     course code as String
    */
   public String getCode();
   
   /**
    * Returns the courses full title
    * @return     course title as String
    */
   public String getTitle();
   
   /**
    * returns the credit point value of the course
    * @return     credit point value as int
    */
   public int getCreditPoints();
   
   /**
    * Returns an array of course codes of all the 
    * prerequite courses required before enrolling
    * in this course
    * @return     prereq course codes as array of Strings
    */
   public String[] getPrereqs();
   
   /**
    * Checks if the course requires the passed course be 
    * completed by the student before enrolling in this
    * one
    * @param _prereq    course code as String
    * @return     true(requires this unit) / false(this unit is not required)
    */
   public boolean hasPrereq(String _prereq);

   /**
    * Builds a string representation of the object
    * 
    * @return     "[course_code]:[course_name]:[credit_points]:[prereqs_courseCodes]:[course_type]"
    */
   @Override
   public String toString();
}
