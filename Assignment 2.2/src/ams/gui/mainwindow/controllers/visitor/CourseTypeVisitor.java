package ams.gui.mainwindow.controllers.visitor;

import ams.model.CoreCourse;
import ams.model.ElectiveCourse;
import ams.model.visitor.Visitor;

/**
 * Implements the visitor interface to implment a basic subclass check
 * 1. Pass the visitor object to the object in questions accept method.
 * 2. the object will visit the correct visit() method below based on 
 * polymorphism, the visit method will then set the correct variables to
 * true or false.
 * 3. the class can be asked is[subclass]() to see what the result was.
 * 
 * @author timothyboye
 *
 */
public class CourseTypeVisitor implements Visitor
{
   // subclass flags
   private boolean coreType = false;
   private boolean electiveType = false;
   
   /**
    * Setup CourseTypeVisitor
    */
   public CourseTypeVisitor()
   {
   }

   /**
    * Sets the elective flag to true and all others to false.
    * 
    * @param elective
    */
   @Override
   public void visit(ElectiveCourse elective)
   {
      electiveType = true;
      coreType = false;
   }

   /**
    * Sets the core fla to true all others to false
    * 
    * @param core
    */
   @Override
   public void visit(CoreCourse core)
   {
      coreType = true;
      electiveType = false;
   }
   
   /**
    * Returns the answer to the question:
    * Was the most recent visitor a CoreCourse?
    * @return     yes (true) / no (false)
    */
   public boolean isCore()
   {
      return coreType;
   }
   
   /**
    * Returns the answer to the question:
    * Was the most recent visitor a ElectiveCourse?
    * @return     yes (true) / no (false)
    */
   public boolean isElective()
   {
      return electiveType;
   }

}
