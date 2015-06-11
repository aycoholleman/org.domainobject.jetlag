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

	private final Cursor cursor;


	TokenExtractor(String rule)
	{
		this.cursor = new Cursor(rule);
	}


	Token nextToken() throws IllegalCharacterException, TokenExtractionException
	{
		skipWhitespace();
		Token token = null;
		if (cursor.at(NIL))
			return null;
		if (cursor.at(DOUBLE_QUOTE))
			token = new DoubleQuotedStringToken(cursor);
		else if (cursor.at(APOSTROPHE))
			token = new SingleQuotedStringToken(cursor);
		else if (NumberToken.isNumberStart(cursor.at()))
			token = new NumberToken(cursor);
		else if (cursor.at('('))
			token = new LeftParenthesisToken(cursor);
		else if (cursor.at(')'))
			token = new RightParenthesisToken(cursor);
		else if (cursor.at(','))
			token = new CommaToken(cursor);
		else if (Operator.isOperatorStart(cursor.at()))
			token = new OperatorToken(cursor);
		else if (Character.isJavaIdentifierStart(cursor.at()) && !cursor.at('$'))
			token = new WordToken(cursor);
		else
			throw new IllegalCharacterException(cursor.copy());
		token.extract();
		return token;
	}


	boolean hasMoreTokens()
	{
		skipWhitespace();
		return !cursor.at(NIL);
	}


	private void skipWhitespace()
	{
		while (true) {
			if (Character.isWhitespace(cursor.at())) {
				cursor.forward();
			}
			else if (Character.isISOControl(cursor.at())) {
				cursor.forward();
			}
			else if (cursor.at('#') && (cursor.position() == 0 || cursor.prev() == LF || cursor.prev() == CR)) {
				// Skip comments
				do {
					cursor.forward();
				} while (!cursor.at(NIL) && !cursor.at(LF) && !cursor.at(CR));
			}
			else {
				break;
			}
		}
	}

}
