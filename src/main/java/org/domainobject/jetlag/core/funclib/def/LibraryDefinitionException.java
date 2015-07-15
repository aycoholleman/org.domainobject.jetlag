package org.domainobject.jetlag.core.funclib.def;

/**
 * @author Ayco Holleman
 * @created Jul 15, 2015
 *
 */
public class LibraryDefinitionException extends Exception {

	private static final long serialVersionUID = -5708641117775716418L;


	public LibraryDefinitionException()
	{
	}


	public LibraryDefinitionException(String message)
	{
		super(message);
	}


	public LibraryDefinitionException(Throwable cause)
	{
		super(cause);
	}


	public LibraryDefinitionException(String message, Throwable cause)
	{
		super(message, cause);
	}


	public LibraryDefinitionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
