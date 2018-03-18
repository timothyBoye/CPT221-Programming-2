package ams.model;

import ams.model.visitor.Visitor;

/**
 * Represents courses in a program that are an elective
 * with most functionality being inherited from AbstractCourse
 * 
 * @author timothyboye
 *
 */
public class ElectiveCourse extends AbstractCourse
{   
   // Course type label
   private static final String TYPE = "ELECTIVE";
   
   /**
    * Elective constructor passes the courses code, title, 
    * credit point value, and prerequisite courses to the 
    * AbstractCourse constructor.
    * 
    * @param _code      Course code as String
    * @param _title     Course title as String
    * @param _cp        Course credit point value as int
    * @param _prereqs   List of the courses Prerequisites as a String array
    */
   public ElectiveCourse(String _code, String _title, int _cp, String[] _prereqs)
   {
      super(_code, _title, _cp, _prereqs);
   }
   
   /**
    * Builds a string representation of the object
    * 
    * @return     "[course_code]:[course_name]:[credit_points]:[prereqs_courseCodes]:[course_type]"
    */
   @Override
   public String toString()
   {
      // call super method for all universal attributes then append course type
      return super.toString()+TYPE;
   }
   
   /**
    * accept(visitor) method is part of the visitor design pattern
    * implementation. It "visits" a Visitor object that is passed
    * to it.
    * 
    * @param visitor    passed Visitor class for the method to visit
    */
   @Override
   public void accept(Visitor visitor)
   {
      visitor.visit(this);
   }

}
