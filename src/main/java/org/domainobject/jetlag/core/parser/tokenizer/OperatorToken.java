package org.domainobject.jetlag.core.parser.tokenizer;

/**
 * @author ayco
 * @created May 1, 2015
 *
 */
public final class OperatorToken extends Token {

	OperatorToken(String rule, int start)
	{
		super(rule, start);
	}


	@Override
	public TokenType getType()
	{
		return null;
	}


	@Override
	void extract() throws TokenExtractionException
	{
	}

}
