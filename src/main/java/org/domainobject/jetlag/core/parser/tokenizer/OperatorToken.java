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


	OperatorToken(String rule, int start)
	{
		super(rule, start);
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
	void extract() throws TokenExtractionException
	{
		end = start;
		char c0 = curchar();
		char c1 = advance();
		if (c1 == NIL) {
			string = new TokenBuilder(1);
			string.add(c0);
			operator = Operator.forSymbol(String.valueOf(c0));
		}
		else {
			operator = Operator.forSymbol(String.valueOf(new char[] { c0, c1 }));
			if (operator == null) {
				// We have a one-character operator
				string = new TokenBuilder(1);
				string.add(c0);
				operator = Operator.forSymbol(String.valueOf(c0));
				// We have already advanced past the one and only
				// character (no need to increment cursor)
			}
			else {
				// We have a two-character operator
				string = new TokenBuilder(2);
				string.add(c0, c1);
				// Move past 2nd character
				++end;
			}
		}
	}

}
