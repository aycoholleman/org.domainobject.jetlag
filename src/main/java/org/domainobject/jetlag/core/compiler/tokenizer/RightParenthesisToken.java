package org.domainobject.jetlag.core.compiler.tokenizer;

/**
 * @author Ayco Holleman
 * @created Apr 29, 2015
 *
 */
class RightParenthesisToken extends Token {

	RightParenthesisToken(Cursor cursor)
	{
		super(cursor);
	}


	@Override
	public TokenType type()
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
