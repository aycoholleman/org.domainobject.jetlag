package org.domainobject.jetlag.core.parser.tokenizer;

import java.util.ArrayList;

/**
 * @author Ayco Holleman
 */
public class Tokenizer {

	private final TokenExtractor extractor;
	private final ArrayList<Token> tokens;

	private int current = -1;

	/**
	 * Creates a new {@code Tokenizer} for the rule encapsulated by the
	 * specified {@link Cursor}. The constructor will attempt to extract the
	 * first token from the rule (if any) and may therefore throw an
	 * {@link IllegalCharacterException} or an {@link TokenExtractionException}.
	 * 
	 * @param cursor
	 *            The {@code Cursor} through which to access the rule
	 * @throws IllegalCharacterException
	 * @throws TokenExtractionException
	 */
	public Tokenizer(Cursor cursor) throws IllegalCharacterException, TokenExtractionException
	{
		extractor = new TokenExtractor(cursor);
		Token token = extractor.nextToken();
		if (token == null) {
			tokens = null;
		}
		else {
			tokens = new ArrayList<>();
			tokens.add(token);
		}
	}

	public Token at()
	{
		if (tokens == null || current == tokens.size())
			return null;
		if (current == -1)
			return tokens.get(0);
		return tokens.get(current);
	}

	/**
	 * Retrieve the next token from the rule.
	 * 
	 * @return The next token from the rule or {@code null} in case of an empty
	 *         rule or if there are no more tokens to be extracted from the rule
	 * @throws IllegalCharacterException
	 * @throws TokenExtractionException
	 */
	public Token nextToken() throws IllegalCharacterException, TokenExtractionException
	{
		if (tokens == null || current == tokens.size())
			return null;
		++current;
		if (current == 0)
			return tokens.get(0);
		if (current < tokens.size())
			// Tokens already retrieved through peek()
			return tokens.get(current);
		Token token = extractor.nextToken();
		if (token != null)
			tokens.add(token);
		return token;
	}

	public Token peek() throws IllegalCharacterException, TokenExtractionException
	{
		if (tokens == null || current == tokens.size())
			return null;
		if (current == -1)
			return tokens.get(0);
		if (current + 1 < tokens.size())
			return tokens.get(current + 1);
		Token token = extractor.nextToken();
		if (token == null)
			return null;
		tokens.add(token);
		return token;
	}

	public Token peek(int ahead) throws IllegalCharacterException, TokenExtractionException
	{
		if (ahead < 1)
			throw new IllegalArgumentException("Parameter must be greater than 0");
		if (tokens == null || current == tokens.size())
			return null;
		int curr = current == -1 ? 0 : current;
		if (curr + ahead < tokens.size())
			return tokens.get(curr + ahead);
		int newSize = curr + ahead + 1;
		Token token = null;
		while (tokens.size() < newSize) {
			token = extractor.nextToken();
			if (token == null)
				break;
			tokens.add(token);
		}
		return token;
	}

}
