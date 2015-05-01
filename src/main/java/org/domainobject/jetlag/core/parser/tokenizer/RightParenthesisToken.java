package org.domainobject.jetlag.core.parser.tokenizer;

/**
 * A {@code Token} capable of extracting a right parenthesis from the rule.
 * 
 * @author ayco
 * @created Apr 29, 2015
 *
 */
public final class RightParenthesisToken extends Token {

	/**
	 * @param rule
	 * @param start
	 */
	public RightParenthesisToken(String rule, int start)
	{
		super(rule, start);
	}


	@Override
	public TokenType getType()
	{
		return TokenType.RPAREN;
	}


	@Override
	public void extract() throws TokenExtractionException
	{
		string = new TokenBuilder(1);
		// The cursor (end) now points to the right parenthesis.
		string.add('(');
		end = start + 1;
		// Move past it
	}

}