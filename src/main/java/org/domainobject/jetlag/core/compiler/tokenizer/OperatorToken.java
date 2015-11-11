package org.domainobject.jetlag.core.compiler.tokenizer;

import static org.domainobject.jetlag.core.compiler.tokenizer.Cursor.NIL;

import org.domainobject.jetlag.core.compiler.Operator;

/**
 * @author Ayco Holleman
 *
 */
public class OperatorToken extends Token {

	private Operator operator;

	OperatorToken(Cursor cursor)
	{
		super(cursor);
	}

	@Override
	public TokenType type()
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
		// Cursor now points at the first character of an operator
		char c0 = cursor.at();
		char c1 = cursor.forward().at();
		String token = String.valueOf(c0);
		operator = Operator.forSymbol(token);
		if (c1 == NIL) {
			return token;
		}
		String tmpTok = String.valueOf(new char[] { c0, c1 });
		Operator tmpOp = Operator.forSymbol(tmpTok);
		tmpOp = Operator.forSymbol(tmpTok);
		if (operator != null) {
			token = tmpTok;
			operator = tmpOp;
			cursor.forward();
		}
		return token;
	}

}
