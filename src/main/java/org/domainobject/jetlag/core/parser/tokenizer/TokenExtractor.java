package org.domainobject.jetlag.core.parser.tokenizer;

import static org.domainobject.jetlag.core.parser.tokenizer.TokenBuilder.APOSTROPHE;
import static org.domainobject.jetlag.core.parser.tokenizer.TokenBuilder.CR;
import static org.domainobject.jetlag.core.parser.tokenizer.TokenBuilder.DOUBLE_QUOTE;
import static org.domainobject.jetlag.core.parser.tokenizer.TokenBuilder.LF;
import static org.domainobject.jetlag.core.parser.tokenizer.TokenBuilder.NIL;

import org.domainobject.jetlag.core.parser.Operator;

/**
 * Identifies and extracts tokens from a rule.
 * 
 * @author ayco
 * @created Apr 26, 2015
 *
 */
class TokenExtractor {

	private final String rule;
	private int cursor;

	private int line;
	private int column;


	TokenExtractor(String rule)
	{
		this.rule = rule;
	}


	Token nextToken() throws IllegalCharacterException, TokenExtractionException
	{
		skipWhitespace();
		char c = curchar();
		if (c == NIL) {
			return null;
		}
		Token token = null;
		if (c == DOUBLE_QUOTE)
			token = new DoubleQuotedStringToken(rule, cursor);
		else if (c == APOSTROPHE)
			token = new SingleQuotedStringToken(rule, cursor);
		else if (Character.isDigit(c) || c == '.')
			token = new NumberToken(rule, cursor);
		else if (c == '(')
			token = new LeftParenthesisToken(rule, cursor);
		else if (c == ')')
			token = new RightParenthesisToken(rule, cursor);
		else if (c == ',')
			token = new CommaToken(rule, cursor);
		else if (Operator.isOperatorStart(c))
			token = new OperatorToken(rule, cursor);
		else if (Character.isJavaIdentifierStart(c) && c != '$')
			token = new WordToken(rule, cursor);
		else
			throw new IllegalCharacterException(c, cursor);
		token.extract();
		cursor = token.end();
		return token;
	}


	boolean hasMoreTokens()
	{
		skipWhitespace();
		return curchar() != NIL;
	}


	private void skipWhitespace()
	{
		char c = curchar();
		while (true) {
			if (Character.isWhitespace(c)) {
				c = advance();
			}
			else if (Character.isISOControl(c)) {
				c = advance();
			}
			else {
				break;
			}
		}
		//		char c = curchar();
		//		while (c != NIL) {
		//			// Skip comments
		//			if (c == '#' && (cursor == 0 || prevchar() == LF || prevchar() == CR)) {
		//				do {
		//					c = advance();
		//				} while (c != NIL && c != LF && c != CR);
		//				skipWhitespace();
		//			}
		//			else if (Character.isWhitespace(c)) {
		//				c = advance();
		//			}
		//			else if (Character.isISOControl(c)) {
		//				c = advance();
		//			}
		//			else {
		//				break;
		//			}
		//		}
	}


	private char curchar()
	{
		return cursor == rule.length() ? NIL : rule.charAt(cursor);
	}


	private char prevchar()
	{
		return rule.charAt(cursor - 1);
	}


	private char advance()
	{
		if (++cursor == rule.length()) {
			return NIL;
		}
		char c = rule.charAt(cursor);
		if (c == CR) {
			++line;
			column = 0;
			// Handle <CR><LF> sequence
			if (cursor + 1 != rule.length() && rule.charAt(cursor + 1) == LF) {
				++cursor;
			}
		}
		else if (c == LF) {
			++line;
			column = 0;
		}
		else {
			++column;
		}
		return c;
	}

}
