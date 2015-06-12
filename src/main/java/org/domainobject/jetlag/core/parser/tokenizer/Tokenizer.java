package org.domainobject.jetlag.core.parser.tokenizer;

import java.util.ArrayList;

/**
 * @author ayco
 * @created 3 jun. 2015
 *
 */
public class Tokenizer {

	private final TokenExtractor tokenExtractor;

	private ArrayList<Token> tokens;
	private int current = -1;


	public Tokenizer(Cursor cursor)
	{
		this.tokenExtractor = new TokenExtractor(cursor);
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


	/**
	 * Retrieve the next token from the token stream.
	 * 
	 * @return
	 * @throws IllegalCharacterException
	 * @throws TokenExtractionException
	 */
	public Token nextToken() throws IllegalCharacterException, TokenExtractionException
	{
		Token token;
		if (tokens == null) {
			token = tokenExtractor.nextToken();
			if (token != null) {
				++current;
				tokens = new ArrayList<>();
				tokens.add(token);
			}
		}
		else if (current + 1 < tokens.size()) {
			token = tokens.get(++current);
		}
		else {
			token = tokenExtractor.nextToken();
			if (token != null) {
				++current;
				tokens.add(token);
			}
		}
		return token;
	}


	public Token peek() throws IllegalCharacterException, TokenExtractionException
	{
		Token token;
		if (tokens == null) {
			token = tokenExtractor.nextToken();
			if (token != null) {
				tokens = new ArrayList<>();
				tokens.add(token);
			}
		}
		else if (current + 1 < tokens.size()) {
			token = tokens.get(current + 1);
		}
		else {
			token = tokenExtractor.nextToken();
			if (token != null) {
				tokens.add(token);
			}
		}
		return token;
	}


	public Token peek(int ahead) throws IllegalCharacterException, TokenExtractionException
	{
		if (ahead < 1) {
			throw new IllegalArgumentException("Argument must be greater than 0");
		}
		Token token = null;
		if (tokens == null) {
			token = tokenExtractor.nextToken();
			if (token != null) {
				tokens = new ArrayList<>();
				tokens.add(token);
				while (--ahead != 0) {
					token = tokenExtractor.nextToken();
					if (token == null) {
						break;
					}
					tokens.add(token);
				}
			}
		}
		else if (current + ahead < tokens.size()) {
			token = tokens.get(current + ahead);
		}
		else {
			while (tokens.size() < (current + ahead)) {
				token = tokenExtractor.nextToken();
				if (token == null) {
					break;
				}
				tokens.add(token);
			}
		}
		return token;
	}

}
