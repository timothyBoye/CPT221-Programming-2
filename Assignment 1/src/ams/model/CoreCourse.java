package ams.model;

import ams.model.visitor.Visitor;

/**
 * CoreCourse is used to represent all university courses
 * that are core/mandatory in a program.
 * 
 * A Core course has a set credit point value of 12 units
 * and will append the type CORE to all toString calls to
 * indicate the units type.
 * 
 * @author timothyboye
 *
 */
public class CoreCourse extends AbstractCourse
{
   // all core courses have the same number of credit points set here
   // and passed to the abstract course class in the constructor.
   private static final int CORE_UNIT_CREDIT_POINTS_VALUE = 12;
   
   // Course type label
   private static final String TYPE = "CORE";
   
   /**
    * Core constructor passes the courses code, title, 
    * credit point value, and prerequisite courses to the 
    * AbstractCourse constructor.
    * 
    * @param _code      Course code as String
    * @param _title     Course title as String
    * @param _cp        Course credit point value as int
    * @param _prereqs   List of the courses Prerequisites as a String array
    */
   public CoreCourse(String _code, String _title, String[] _prereqs)
   {
      super(_code, _title, CORE_UNIT_CREDIT_POINTS_VALUE, _prereqs);
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
