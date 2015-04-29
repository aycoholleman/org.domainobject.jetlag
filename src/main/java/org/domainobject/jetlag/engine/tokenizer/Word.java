package org.domainobject.jetlag.engine.tokenizer;

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
public class Word extends Token {

	/**
	 * @param rule
	 * @param start
	 */
	public Word(String rule, int start)
	{
		super(rule, start);
	}


	@Override
	public TokenType getType()
	{
		return TokenType.WORD;
	}


	@Override
	public void extract() throws TokenExtractionException
	{
		string = new TokenBuilder(16);
		end = start;
		// The cursor (end) now points to either a '_' or a letter
		char c = curchar();
		do {
			string.add(c);
			c = advance();
		} while (Character.isLetterOrDigit(c) || c == '_');
	}

}
