package ams.model;

import ams.model.visitor.Visitor;

/**
 * @author Mikhail Perepletchikov
 */
public class ElectiveCourse extends AbstractCourse {
	public static final int DEFAULT_CREDIT_POINTS = 6;
	public static final String TYPE_STRING = "ELECTIVE";

	public ElectiveCourse(String code, String title, String [] preReqs) {
		super(code, title, DEFAULT_CREDIT_POINTS, preReqs);
	}

	public ElectiveCourse(String code, String title, int creditPoints,
			String[] preReqs) {
		super(code, title, creditPoints, preReqs);
	}
	
	public String toString() {
		return (super.toString() + ":" + TYPE_STRING);
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
