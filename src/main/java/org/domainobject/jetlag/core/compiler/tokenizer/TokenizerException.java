package org.domainobject.jetlag.core.compiler.tokenizer;

/**
 * The base class of all exceptions that may occur while retrieving tokens from
 * a rule.
 * 
 * @author ayco
 * @created Apr 26, 2015
 *
 */
public class TokenizerException extends Exception {

	private static final long serialVersionUID = 2256943402497520631L;


	public TokenizerException()
	{
		super();
	}


	public TokenizerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}


	public TokenizerException(String message, Throwable cause)
	{
		super(message, cause);
	}


	public TokenizerException(String message)
	{
		super(message);
	}


	public TokenizerException(Throwable cause)
	{
		super(cause);
	}

}
