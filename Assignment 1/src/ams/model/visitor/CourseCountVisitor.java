package ams.model.visitor;

import ams.model.CoreCourse;
import ams.model.ElectiveCourse;

/**
 * AMS implementation of the visitor pattern
 * for counting number and types of courses
 * 
 * @author timothyboye
 *
 */
public class CourseCountVisitor implements Visitor
{
   private int coreCount;     // CoreCourse counter to tally CoreCourses
   private int electiveCount; // ElectiveCourse counter to tally ElectiveCourses
   
   /**
    * CourseCountVisitor Constructor
    * Initailses the counting variables
    */
   public CourseCountVisitor()
   {
      coreCount = 0;
      electiveCount = 0;
   }

   /**
    * Called by CoreCourse via its accept method and adds
    * to the tally of how many objects have called the method.
    * 
    * @param course     reference to the calling object
    */
   @Override
   public void visit(CoreCourse course)
   {
      coreCount++;
   }

   /**
    * Called by ElectiveCourse via its accept method and adds
    * to the tally of how many objects have called the method.
    * 
    * @param course     reference to the calling object
    */
   @Override
   public void visit(ElectiveCourse course)
   {
      electiveCount++;
   }
   
   /**
    * Returns the current count of how many CoreCoures
    * have visited the CourseCountVisitor object
    * 
    * @return     number of CoreCourses as int
    */
   public int getCoreCount()
   {
      return coreCount;
   }
   
   /**
    * Returns the current count of how many ElectiveCoures
    * have visited the CourseCountVisitor object
    * 
    * @return     number of ElectiveCourses as int
    */
   public int getElectiveCount()
   {
      return electiveCount;
   }

}
