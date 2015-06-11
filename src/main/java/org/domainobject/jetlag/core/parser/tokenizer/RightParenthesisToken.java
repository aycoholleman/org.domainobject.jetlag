package org.domainobject.jetlag.core.parser.tokenizer;

/**
 * A {@code Token} capable of extracting a right parenthesis from the rule.
 * 
 * @author ayco
 * @created Apr 29, 2015
 *
 */
public final class RightParenthesisToken extends Token {

	RightParenthesisToken(Cursor cursor)
	{
		super(cursor);
	}


	@Override
	public TokenType getType()
	{
		return TokenType.RPAREN;
	}


	@Override
	String doExtract() throws TokenExtractionException
	{
		// Move cursor past token
		cursor.forward();
		return ")";
	}

}
