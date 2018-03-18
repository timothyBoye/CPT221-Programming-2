package ams.model;

import java.util.LinkedHashMap;
import java.util.Map;

import ams.model.exception.ProgramException;
import ams.model.visitor.CourseVisitor;

/**
 * @author Mikhail Perepletchikov
 */
public class Program {

	private String programCode;
	private String title;
	private Map<String, Course> courses = new LinkedHashMap<String, Course>();

	public Program(String programCode, String title) {
		this.programCode = programCode;
		this.title = title;
	}

	public void addCourse(Course course) throws ProgramException {
		if (course.getPreReqs() != null) {
			// loop through the list of prereqs
			for (int i = 0; i < course.getPreReqs().length; i++) {
				// check if prereq exists
				if (!courses.containsKey(course.getPreReqs()[i]))
					throw new ProgramException(
							"** ERROR ADDING A COURSE: PRE-REQS DO NOT EXIST **");
			}
		}
		courses.put(course.getCode(), course);
	}

	public void removeCourse(String courseCode) throws ProgramException {

		if (!courses.containsKey(courseCode))
			throw new ProgramException(
					"** ERROR REMOVING A COURSE: COURSE NOT FOUND **");

		for (Course course : courses.values()) {
			String[] preReqs = course.getPreReqs();
			// check if this course is a prereq for the other existing course/s
			if (preReqs != null) {
				for (int i = 0; i < preReqs.length; i++) {
					if (preReqs[i].equalsIgnoreCase(courseCode))
						throw new ProgramException(
								"** ERROR REMOVING A COURSE: CANNOT REMOVE PREREQUSITE COURSE**");
				}
			}
		}
		courses.remove(courseCode);
	}

	public Course getCourse(String courseCode) {
		return courses.get(courseCode);
	}

	public Map<String, Course> getAllCourses() {
		return courses;
	}

	public int countCoreCourses() {
		CourseVisitor coreVisitor = new CourseVisitor();
		for (Course course : courses.values())
			course.accept(coreVisitor);
		return coreVisitor.getCoreCount();
	}

	public int countElectiveCourses() {
		CourseVisitor electiveVisitor = new CourseVisitor();
		for (Course course : courses.values())
			course.accept(electiveVisitor);
		return electiveVisitor.getElectiveCount();
	}

	public String toString() {
		String programString = programCode + ":" + title;

		// append course codes
		/*if (courses.size() != 0) 
			for (Map.Entry<String, Course> entry : courses.entrySet()) 				
				programString += (":" + entry.getValue().getCode());		*/	
		return programString;
	}

   public String getCode()
   {
      return programCode;
   }

   public String getTitle()
   {
      return title;
   }
}
