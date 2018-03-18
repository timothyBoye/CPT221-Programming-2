package ams.model.visitor;

import ams.model.CoreCourse;
import ams.model.ElectiveCourse;
/**
 * AMS interface for the visitor pattern
 * allowing counting of course types.
 * 
 * @author timothyboye
 *
 */
public interface Visitor
{
   /**
    * Called by CoreCourse via its accept method and keeps
    * a tally of how many objects have called the method.
    * 
    * @param course     reference to the calling object
    */
   public void visit(CoreCourse course);
   
   /**
    * Called by ElectiveCourse via its accept method and keeps
    * a tally of how many objects have called the method.
    * 
    * @param course     reference to the calling object
    */
   public void visit(ElectiveCourse course);
}
