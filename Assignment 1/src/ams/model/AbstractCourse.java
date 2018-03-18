package ams.model;

import ams.model.visitor.Visitor;

/**
 * Implementation of the Course interface
 * providing all common functionality for all
 * subclasses of AbstractCourse.
 * 
 * @author timothyboye
 *
 */
public abstract class AbstractCourse implements Course
{
   private String code;      // courses unique code
   private String title;     // courses full title
   private int cp;           // courses credit point value
   private String[] prereqs; // array of prerequiste course codes
   
   /**
    * AbstractCourse constructor
    * @param _code      course code as String
    * @param _title     course title as String
    * @param _cp        credit point value of course as integer
    * @param _prereqs   course codes of required prerequisite units as array of Strings
    */
   public AbstractCourse(String _code, String _title, int _cp, String[] _prereqs)
   {
      code = _code;
      title = _title;
      cp = _cp;
      prereqs = _prereqs;
   }
   
   /**
    * Returns the courses unique code
    * @return     course code as String
    */
   @Override
   public String getCode()
   {
      return code;
   }
   
   /**
    * Returns the courses full title
    * @return     course title as String
    */
   @Override
   public String getTitle()
   {
      return title;
   }
   
   /**
    * returns the credit point value of the course
    * @return     credit point value as int
    */
   @Override
   public int getCreditPoints()
   {
      return cp;
   }
   
   /**
    * Returns an array of course codes of all the 
    * prerequite courses required before enrolling
    * in this course
    * @return     prereq course codes as array of Strings
    */
   @Override
   public String[] getPrereqs()
   {
      return prereqs;
   }
   
   /**
    * Checks if the course requires the passed course be 
    * completed by the student before enrolling in this
    * one
    * @param _prereq    course code as String
    * @return     true(requires this unit) / false(this unit is not required)
    */
   @Override
   public boolean hasPrereq(String _prereq)
   {
      // check if there are any prereqs
      if (prereqs != null)
      {
         // loop through prereqs and check if any match
         // the prereq provided, if any do return true
         for (String prereq : prereqs)
         {
            if (prereq.equals(_prereq))
               return true;
         }
         // there were no matching prereqs return false
         return false;
      }
      // there are no prereqs so return false
      else
      {
         return false;
      }
   }
   
   /**
    * Builds a string representation of the object
    * 
    * @return     "[course_code]:[course_name]:[credit_points]:[prereqs_courseCodes]:[course_type]"
    */
   @Override
   public String toString()
   {
      // build the string to be output
      StringBuilder string = new StringBuilder();
      
      // get code, title, CP and add to output
      string.append(this.getCode());
      string.append(":");
      string.append(this.getTitle());
      string.append(":");
      string.append(this.getCreditPoints());
      string.append(":");
      
      // get prereq course codes and add to output
      String[] prereqs = this.getPrereqs();
      // are there any prereqs?
      if (prereqs != null)
      {
         // append first prereq then loop through temainder
         // appending each seperated by a comma, finally
         // append a colon.
         string.append(prereqs[0]);
         for(int i= 1; i < prereqs.length; i++)
         {
            string.append(",");
            string.append(prereqs[i]);
         }
         string.append(":");
      }
      
      // return completed string
      return string.toString();
   }
   
   /**
    * accept(visitor) method is part of the visitor design pattern
    * implementation. It "visits" a Visitor object that is passed
    * to it.
    * 
    * @param visitor    passed Visitor class for the method to visit
    */
   @Override
   public abstract void accept(Visitor visitor);
}
