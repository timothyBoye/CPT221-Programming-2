package ams.model.visitor;

/**
 * @author Mikhail Perepletchikov
 */
public interface Visitable {
	public void accept(Visitor visitor);
}
