package org.domainobject.jetlag.core.funclib.def;

public class FunctionDefinitionException extends LibraryDefinitionException {

	private static final long serialVersionUID = 3833002098195940893L;


	public FunctionDefinitionException()
	{
	}


	public FunctionDefinitionException(String message)
	{
		super(message);
	}


	public FunctionDefinitionException(Throwable cause)
	{
		super(cause);
	}


	public FunctionDefinitionException(String message, Throwable cause)
	{
		super(message, cause);
	}


	public FunctionDefinitionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
