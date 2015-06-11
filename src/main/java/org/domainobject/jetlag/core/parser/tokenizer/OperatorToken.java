package org.domainobject.jetlag.core.parser.tokenizer;

import static org.domainobject.jetlag.core.parser.tokenizer.TokenBuilder.NIL;

import org.domainobject.jetlag.core.parser.Operator;

/**
 * @author ayco
 * @created May 1, 2015
 *
 */
public final class OperatorToken extends Token {

	private Operator operator;


	OperatorToken(Cursor cursor)
	{
		super(cursor);
	}


	@Override
	public TokenType getType()
	{
		return TokenType.OPERATOR;
	}


	public Operator getOperator()
	{
		return operator;
	}


	@Override
	String doExtract() throws TokenExtractionException
	{
		char c0 = cursor.at();
		char c1 = cursor.forward();
		String token;
		if (c1 == NIL) {
			token = String.valueOf(c0);
			operator = Operator.forSymbol(token);
		}
		else {
			token = String.valueOf(new char[] { c0, c1 });
			operator = Operator.forSymbol(token);
			if (operator == null) {
				// We have a one-character operator
				token = String.valueOf(c0);
				operator = Operator.forSymbol(token);
			}
			else {
				// We have a two-character operator; move past 2nd character
				cursor.forward();
			}
		}
		return token.toString();
	}

}
