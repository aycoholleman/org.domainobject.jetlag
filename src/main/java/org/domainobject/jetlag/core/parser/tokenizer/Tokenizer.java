package org.domainobject.jetlag.core.parser.tokenizer;

import java.util.ArrayList;

/**
 * @author ayco
 * @created 3 jun. 2015
 *
 */
public final class Tokenizer {

	private final TokenProducer tokenProducer;

	private ArrayList<Token> tokens;
	private int current;
	private int last;


	public Tokenizer(String rule)
	{
		this.tokenProducer = new TokenProducer(rule);
	}


	public boolean hasMoreTokens()
	{
		return tokenProducer.hasMoreTokens();
	}


	public Token currentToken()
	{
		return tokens == null ? null : tokens.get(current);
	}


	public Token nextToken() throws IllegalCharacterException, TokenExtractionException
	{
		if (current < last) {
			return tokens.get(++current);
		}
		Token token = tokenProducer.nextToken();
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
		Token token = tokenProducer.nextToken();
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
			token = tokenProducer.nextToken();
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
