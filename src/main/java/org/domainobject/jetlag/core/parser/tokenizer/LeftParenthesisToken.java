package org.domainobject.jetlag.core.parser.tokenizer;

/**
 * A {@code Token} capable of extracting a left parenthesis from the rule.
 * 
 * @author ayco
 * @created Apr 29, 2015
 *
 */
public final class LeftParenthesisToken extends Token {

	LeftParenthesisToken(String rule, int start)
	{
		super(rule, start);
	}


	@Override
	public TokenType getType()
	{
		return TokenType.LPAREN;
	}


	@Override
	void extract() throws TokenExtractionException
	{
		string = new TokenBuilder(1);
		// The cursor (end) now points to the left parenthesis.
		string.add('(');
		end = start + 1;
		// Move past it
	}

}
