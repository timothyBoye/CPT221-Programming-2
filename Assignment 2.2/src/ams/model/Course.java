package ams.model;
import ams.model.visitor.Visitable;

/**
 * @author Mikhail Perepletchikov
 */
public interface Course extends Visitable {
    public String getCode();
	public String getTitle();
	public int getCreditPoints();		
	public String [] getPreReqs();	
}