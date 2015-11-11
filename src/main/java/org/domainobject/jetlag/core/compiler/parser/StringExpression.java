package org.domainobject.jetlag.core.compiler.parser;

import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.OPERATOR;

import java.util.ArrayList;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

public class StringExpression extends AbstractExpression {

	private ArrayList<ArithmeticExpression> operands;
	private ArrayList<OperatorToken> operators;

	@Override
	public void parse() throws ParseException
	{
		while (true) {
			ArithmeticExpression expr = new ArithmeticExpression();
			operands.add(expr);
			expr.rule = this.rule;
			expr.tokenizer = this.tokenizer;
			expr.libDefs = this.libDefs;
			expr.parse();
			if (!isStringOperator(tokenizer.peek()))
				break;
			operators.add((OperatorToken) tokenizer.nextToken());
			if (tokenizer.nextToken() == null)
				throw new ParseException(/* TODO */);
		}
	}

	private static boolean isStringOperator(Token token)
	{
		if (token == null || token.type() != OPERATOR)
			return false;
		return (((OperatorToken) token).getOperator().isStringOperator());
	}
}
