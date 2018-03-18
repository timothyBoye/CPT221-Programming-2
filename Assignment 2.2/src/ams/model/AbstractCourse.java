package ams.model;

import ams.model.visitor.Visitor;

/**
 * @author Mikhail Perepletchikov
 */
public abstract class AbstractCourse implements Course {
	protected String code;
	protected String title;
	protected int creditPoints;
	protected String[] preReqs;

	public AbstractCourse(String code, String title, int creditPoints,
			String[] preReqs) {
		this.code = code;
		this.title = title;
		this.creditPoints = creditPoints;
		this.preReqs = preReqs;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public int getCreditPoints() {
		return creditPoints;
	}

	public String[] getPreReqs() {
		return preReqs;
	}

	public String toString() {
		String returnString = code + ":" + title + ":" + creditPoints;
		if (preReqs != null) {
			returnString += ":";
			for (int i = 0; i < preReqs.length; i++)
				returnString += (preReqs[i] + ",");
			returnString = returnString.substring(0, returnString.length() - 1);
		}
		return returnString;
	}

	// basic equals method comparing course codes
	public boolean equals(Object in) {
		Course obj = (Course) in;
		return (code == obj.getCode());
	}

	// VISITOR PATTERN //
	public abstract void accept(Visitor visitor);
}
