package ams.model.visitor;

import ams.model.CoreCourse;
import ams.model.ElectiveCourse;

/**
 * @author Mikhail Perepletchikov
 */
public interface Visitor {
	public void visit(ElectiveCourse elective);
	public void visit(CoreCourse core);
}
