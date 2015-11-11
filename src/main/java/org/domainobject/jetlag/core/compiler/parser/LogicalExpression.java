package org.domainobject.jetlag.core.compiler.parser;

import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.OPERATOR;

import java.util.ArrayList;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

public class LogicalExpression extends AbstractExpression {

	private ArrayList<Assignment> operands;
	private ArrayList<OperatorToken> operators;

	@Override
	public void parse() throws ParseException
	{
		while (true) {
			Assignment expr = new Assignment();
			operands.add(expr);
			expr.rule = this.rule;
			expr.tokenizer = this.tokenizer;
			expr.libDefs = this.libDefs;
			expr.parse();
			if (!isLogicalOperator(tokenizer.peek()))
				break;
			operators.add((OperatorToken) tokenizer.nextToken());
			if (!tokenizer.hasMoreTokens())
				throw new ParseException(/* TODO */);
			tokenizer.nextToken();
		}
	}

	private static boolean isLogicalOperator(Token token)
	{
		if (token == null || token.type() != OPERATOR)
			return false;
		return (((OperatorToken) token).getOperator().isLogicalOperator());
	}
}
