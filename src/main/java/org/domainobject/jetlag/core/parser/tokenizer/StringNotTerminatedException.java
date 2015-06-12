package org.domainobject.jetlag.core.parser.tokenizer;

/**
 * 
 * @author Ayco Holleman
 * @created 12 jun. 2015
 *
 */
public class StringNotTerminatedException extends TokenExtractionException {

	private static final long serialVersionUID = 3642920208392056186L;


	public StringNotTerminatedException(Token token)
	{
		super(token);
	}


	@Override
	public String getMessage()
	{
		return String.format("String starting at position %s not terminated: %s", token.start(), token);
	}

}
