package org.domainobject.jetlag.core.compiler.tokenizer;

import java.util.ArrayList;

/**
 * Tokenizes a rule and enables you to scroll through the tokens.
 * 
 * @author Ayco Holleman
 */
public class Tokenizer {

	private TokenExtractor extractor;
	private ArrayList<Token> tokens;
	private int ptr = -1;

	/**
	 * Creates a tokenizer for the rule encapsulated by the specified
	 * {@link Cursor}. The constructor will immediately extract and cache all
	 * tokens from the rule and may therefore throw a
	 * {@link IllegalCharacterException} or a {@link TokenExtractionException}.
	 * 
	 * @param cursor
	 *            The {@code Cursor} through which to access the rule
	 * @throws IllegalCharacterException
	 * @throws TokenExtractionException
	 */
	public Tokenizer(Cursor cursor) throws IllegalCharacterException, TokenExtractionException
	{
		extractor = new TokenExtractor(cursor);
		tokens = new ArrayList<>();
		while (extractor.hasMoreTokens())
			tokens.add(extractor.nextToken());
	}

	/**
	 * Returns the total number of tokens in the rule.
	 * 
	 * @return The total number of tokens in the rule
	 */
	public int numTokens()
	{
		return tokens.size();
	}

	/**
	 * Whether or not there are more tokens to be extracted.
	 * 
	 * @return Whether or not there are more tokens to be extracted
	 */
	public boolean hasMoreTokens()
	{
		return (ptr + 1) < tokens.size();
	}

	/**
	 * Returns the token currently pointed at by the tokenizer.
	 * 
	 * @return The token currently pointed at by the tokenizer
	 */
	public Token at()
	{
		if (tokens.size() > 0 && ptr != -1)
			return tokens.get(ptr);
		return null;
	}

	/**
	 * Retrieves the next token from the rule.
	 * 
	 * @return The next token from the rule or {@code null} in case of an empty
	 *         rule or if there are no more tokens to be extracted from the rule
	 */
	public Token nextToken()
	{
		if (hasMoreTokens())
			return tokens.get(++ptr);
		return null;
	}

	/**
	 * Peeks one token ahead in the token stream without moving the token
	 * pointer.
	 * 
	 * @return
	 */
	public Token peek()
	{
		return peek(1);
	}

	/**
	 * Peeks the specified number of tokens ahead in the token stream without
	 * moving the token pointer. You can specify negative numbers to look back
	 * into the token stream.
	 * 
	 * @param ahead
	 * @return
	 */
	public Token peek(int ahead)
	{
		int i = ptr + ahead;
		if (i >= 0 && i < tokens.size())
			return tokens.get(i);
		return null;
	}

}
