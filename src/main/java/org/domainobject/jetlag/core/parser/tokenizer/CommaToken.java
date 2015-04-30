package org.domainobject.jetlag.core.parser.tokenizer;

final class CommaToken extends Token {

	public CommaToken(String rule, int start)
	{
		super(rule, start);
	}


	@Override
	public TokenType getType()
	{
		return TokenType.COMMA;
	}


	@Override
	public void extract() throws TokenExtractionException
	{
		string = new TokenBuilder(1);
		// The cursor (end) now points to the comma
		string.add('(');
		// Move cursor past token
		end = start + 1;
	}

}
