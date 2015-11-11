package org.domainobject.jetlag.core.compiler.parser;

import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.OPERATOR;

import java.util.ArrayList;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

public class ArithmeticExpression extends AbstractExpression {

	private ArrayList<Terminal> operands;
	private ArrayList<OperatorToken> operators;

	@Override
	public void parse() throws ParseException
	{
		while (true) {
			Terminal expr = new Terminal();
			operands.add(expr);
			expr.rule = this.rule;
			expr.tokenizer = this.tokenizer;
			expr.libDefs = this.libDefs;
			expr.parse();
			if (!isArithmeticOperator(tokenizer.peek()))
				break;
			operators.add((OperatorToken) tokenizer.nextToken());
			if (tokenizer.nextToken() == null)
				throw new ParseException(/* TODO */);
		}
	}

	private static boolean isArithmeticOperator(Token token)
	{
		if (token == null || token.type() != OPERATOR)
			return false;
		return (((OperatorToken) token).getOperator().isArithmeticOperator());
	}

}
