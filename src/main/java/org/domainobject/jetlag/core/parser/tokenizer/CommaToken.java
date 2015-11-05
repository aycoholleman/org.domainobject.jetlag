package org.domainobject.jetlag.core.parser.tokenizer;

class CommaToken extends Token {

	CommaToken(Cursor cursor)
	{
		super(cursor);
	}

	@Override
	public TokenType getType()
	{
		return TokenType.COMMA;
	}

	@Override
	String doExtract() throws TokenExtractionException
	{
		// Move cursor past comma
		cursor.forward();
		return ",";
	}

}
