package org.domainobject.jetlag.core.parser.tokenizer;

import java.util.ArrayList;

/**
 * @author ayco
 * @created 3 jun. 2015
 *
 */
public final class Tokenizer {

	private final TokenExtractor tokenExtractor;

	private ArrayList<Token> tokens;
	private int current;
	private int last;


	public Tokenizer(String rule)
	{
		this.tokenExtractor = new TokenExtractor(rule);
	}


	/**
	 * Are there more tokens to be extracted from the rule?
	 * 
	 * @return {@code false} if there is nothing left but whitespace in the
	 *         rule; {@code true} otherwise
	 */
	public boolean hasMoreTokens()
	{
		return tokenExtractor.hasMoreTokens();
	}


	/**
	 * Get the current token in the rule.
	 * 
	 * @return
	 */
	public Token currentToken()
	{
		return tokens == null ? null : tokens.get(current);
	}


	public Token nextToken() throws IllegalCharacterException, TokenExtractionException
	{
		if (current < last) {
			return tokens.get(++current);
		}
		Token token = tokenExtractor.nextToken();
		if (token == null) {
			return null;
		}
		if (tokens == null) {
			tokens = new ArrayList<>();
		}
		tokens.add(token);
		++current;
		++last;
		return token;
	}


	public Token peek() throws IllegalCharacterException, TokenExtractionException
	{
		if (current + 1 < last) {
			return tokens.get(current + 1);
		}
		Token token = tokenExtractor.nextToken();
		if (token == null) {
			return null;
		}
		if (tokens == null) {
			tokens = new ArrayList<>();
		}
		tokens.add(token);
		++last;
		return token;
	}


	public Token peek(int ahead) throws IllegalCharacterException, TokenExtractionException
	{
		if (ahead == 0) {
			return currentToken();
		}
		if (ahead < 0) {
			if (tokens == null || current + ahead < 0) {
				return null;
			}
			return tokens.get(current + ahead);
		}
		if (current + ahead < last) {
			return tokens.get(current + ahead);
		}
		Token token = null;
		for (int i = 0; i < (last - (current + ahead)); ++i) {
			token = tokenExtractor.nextToken();
			if (token == null) {
				return null;
			}
			if (tokens == null) {
				tokens = new ArrayList<>();
			}
			tokens.add(token);
			++last;
		}
		return token;
	}

}
