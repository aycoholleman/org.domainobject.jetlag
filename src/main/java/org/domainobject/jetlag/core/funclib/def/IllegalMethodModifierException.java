package org.domainobject.jetlag.core.funclib.def;

public class IllegalMethodModifierException extends FunctionDefinitionException {

	private static final long serialVersionUID = 7069298912463686955L;


	public IllegalMethodModifierException()
	{
	}


	public IllegalMethodModifierException(String message)
	{
		super(message);
	}


	public IllegalMethodModifierException(Throwable cause)
	{
		super(cause);
	}


	public IllegalMethodModifierException(String message, Throwable cause)
	{
		super(message, cause);
	}


	public IllegalMethodModifierException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
