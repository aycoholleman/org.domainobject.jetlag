package org.domainobject.jetlag.core.compiler.tokenizer;

public final class InvalidNumberException extends TokenExtractionException {

	private static final long serialVersionUID = 3888343090717150075L;

	public InvalidNumberException(Token token)
	{
		super(token);
	}

	public InvalidNumberException(Token token, String message)
	{
		super(token, message);
	}


}
