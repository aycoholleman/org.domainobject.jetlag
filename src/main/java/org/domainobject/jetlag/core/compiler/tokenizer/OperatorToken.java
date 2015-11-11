package org.domainobject.jetlag.core.compiler.tokenizer;

import static org.domainobject.jetlag.core.compiler.tokenizer.Cursor.NIL;

import org.domainobject.jetlag.core.compiler.Operator;

/**
 * @author ayco
 * @created May 1, 2015
 *
 */
class OperatorToken extends Token {

	OperatorToken(Cursor cursor)
	{
		super(cursor);
	}


	@Override
	public TokenType getType()
	{
		return TokenType.OPERATOR;
	}


	@Override
	String doExtract() throws TokenExtractionException
	{
		// Cursor now points at the first character of an operator
		char c0 = cursor.at();
		char c1 = cursor.forward().at();
		if (c1 == NIL) {
			return String.valueOf(c0);
		}
		String token = String.valueOf(new char[] { c0, c1 });
		if (Operator.forSymbol(token) == null) {
			// We have a one-character operator
			token = String.valueOf(c0);
		}
		else {
			// Move past 2nd character
			cursor.forward();
		}
		return token;
	}

}
