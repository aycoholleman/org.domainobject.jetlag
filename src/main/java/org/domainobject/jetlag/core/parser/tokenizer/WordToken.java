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

	WordToken(String rule, int start)
	{
		super(rule, start);
	}


	@Override
	public TokenType getType()
	{
		return TokenType.WORD;
	}


	@Override
	void extract() throws TokenExtractionException
	{
		token = new TokenBuilder(16);
		end = start;
		// The cursor (end) now points to either a '_' or a letter
		char c = curchar();
		do {
			token.add(c);
			c = advance();
		} while (Character.isLetterOrDigit(c) || c == '_');
	}

}
