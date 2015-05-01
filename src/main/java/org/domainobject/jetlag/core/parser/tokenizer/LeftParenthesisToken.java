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
		// At this point cursor points to left parenthesis
		token = new TokenBuilder(1);
		token.add('(');
		end = start + 1;
	}

}
