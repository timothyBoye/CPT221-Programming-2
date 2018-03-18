package ams.model.visitor;

/**
 * AMS interface for the visitor pattern
 * which is used with the visiting objects
 * to provide their accept method.
 * 
 * @author timothyboye
 *
 */
public interface Visitable
{
   /**
    * accept(visitor) method is part of the visitor design pattern
    * implementation. It "visits" a Visitor object that is passed
    * to it.
    * 
    * @param visitor    passed Visitor class for the method to visit
    */
   public void accept(Visitor visitor);
}
