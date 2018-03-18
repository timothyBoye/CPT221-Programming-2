package ams.gui.mainwindow.controllers.comparators;

import java.util.Comparator;

import ams.model.Course;

/**
 * TitleSort is a comparator for comparing
 * Courses by title, the primary purpose of
 * which is to be used in conjunction with
 * Arrays.sort to sort an array of courses
 * by title
 * 
 * @author timothyboye
 *
 */
public class TitleSort implements Comparator<Course>
{

   /**
    * Compares course o1 with course o2 and returns
    * a result indicating which is lexogrpahically
    * significant by course title.
    * 
    * @param o1   Course 1 to compare
    * @param o2   Course 2 to compare
    * @return     -1 o1 < o2, 0 o1 = o2, 1 o1 > o2
    */
   @Override
   public int compare(Course o1, Course o2)
   {
      // Compare o1's title to o2's title alphabetically
      //    using java's built in lexicographical compare 
      //    methods, no need to make our own for that.
      //
      //    Ignore case because it's really not important for
      //    our purposes.
      return o1.getTitle().compareToIgnoreCase(o2.getTitle());
   }

}
