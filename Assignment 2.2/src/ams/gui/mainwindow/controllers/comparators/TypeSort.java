package ams.gui.mainwindow.controllers.comparators;

import java.util.Comparator;

import ams.gui.mainwindow.controllers.visitor.CourseTypeVisitor;
import ams.model.Course;

/**
 * TypeSort is a comparator for comparing
 * Courses by type, the primary purpose of
 * which is to be used in conjunction with
 * Arrays.sort to sort an array of courses
 * by type
 * 
 * @author timothyboye
 *
 */
public class TypeSort implements Comparator<Course>
{

   /**
    * Compares course o1 with course o2 and returns
    * a result indicating which is most significant
    * by course type (where CoreCourses are considered 
    * most significant).
    * 
    * @param o1   Course 1 to compare
    * @param o2   Course 2 to compare
    * @return     -1 o1 < o2, 0 o1 = o2, 1 o1 > o2
    */
   @Override
   public int compare(Course o1, Course o2)
   {
      // score the courses
      int o1Score = scoreCourseType(o1);
      int o2Score = scoreCourseType(o2);
      
      // compare the scores for o1 and o2, declare a winner and return
      // greater than
      if (o1Score < o2Score)
         return -1;
      // equal
      else if (o1Score == o2Score)
         return 0;
      // less than
      else
         return 1;
   }
   
   /**
    * Private class for scoreing a course based on the subclass it uses
    * 
    * @param course     course to be scored
    * @return     the score:
    *             -1 unknown class
    *              0 CoreCourse
    *              1 ElectiveCourse
    */
   private int scoreCourseType(Course course)
   {
      // create a visitor to check which subclasses are being compared
      CourseTypeVisitor visitor = new CourseTypeVisitor();
      // make course visit the visitor
      course.accept(visitor);
      
      // assign a score for the course based on subclass
      if (visitor.isCore())
         return 0;
      else if (visitor.isElective())
         return 1;
      else
         return -1;
   }

}
