package ams.model.visitor;

import ams.model.CoreCourse;
import ams.model.ElectiveCourse;

/**
 * @author Mikhail Perepletchikov
 */
public class CourseVisitor implements Visitor {
	private int coreCount =0;
	private int electivesCount =0;
	
	public CourseVisitor() {
	}	
	
	public void visit(CoreCourse course) {
		coreCount++;		
	}
	public void visit(ElectiveCourse course) {		
		electivesCount++;		
	}
	/**
	 * @return the coreCount
	 */
	public int getCoreCount() {
		return coreCount;
	}
	/**
	 * @return the electivesCount
	 */
	public int getElectiveCount() {
		return electivesCount;
	}	
}