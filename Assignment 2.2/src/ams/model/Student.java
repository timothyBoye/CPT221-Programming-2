package ams.model;

import java.util.List;

/**
 * @author Mikhail Perepletchikov
 */
public interface Student extends Enrollable
{     
	public String getFullName ();
	public int getStudentId ();
    public List <Result> getResults ();
    public boolean addResult(Result result);
    public List <Course> getCurrentEnrollment ();
    public int calculateCurrentLoad();
	public int calculateCareerPoints();        
}

