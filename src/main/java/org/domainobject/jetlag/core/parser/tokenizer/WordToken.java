package org.domainobject.jetlag.core.parser.tokenizer;

/**
 * A {@code Token} capable of extracting:
 * <ol>
 * <li>keywords
 * <li>rule references ("variables")
 * <li>function identifiers
 * </ol>
 * The tokenizer has no way of distinguishing between these subtypes. Keywords
 * in practice never start with an underscore, but we won't count on it.
 * 
 * @author ayco
 * @created Apr 29, 2015
 *
 */
public class WordToken extends Token {

	WordToken(Cursor cursor)
	{
		super(cursor);
	}


	@Override
	public TokenType getType()
	{
		return TokenType.WORD;
	}


	@Override
	String doExtract() throws TokenExtractionException
	{
		TokenBuilder token = new TokenBuilder(16);
		// The cursor now points either to an underscore ('_') or to a letter
		do {
			token.add(cursor.at());
			cursor.forward();
		} while (Character.isLetterOrDigit(cursor.at()) || cursor.at('_'));
		return token.toString();
	}

}
