package ams.model;

import ams.model.visitor.Visitor;

/**
 * @author Mikhail Perepletchikov
 */
public class CoreCourse extends AbstractCourse {
	public static final int DEFAULT_CREDIT_POINTS = 12;
	public static final String TYPE_STRING = "CORE";

	public CoreCourse(String code, String title, String [] preReqs) {
		super(code, title, DEFAULT_CREDIT_POINTS, preReqs);
	}

	public String toString() {
		return (super.toString() + ":" + TYPE_STRING);
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
