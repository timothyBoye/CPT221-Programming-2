package ams.model.exception;

/**
 * @author Mikhail Perepletchikov
 */
@SuppressWarnings("serial")
public class ProgramException extends AMSException
{
	public ProgramException()
	{
		super();
	}
	public ProgramException(String message)
	{
		super(message);
	}
}
