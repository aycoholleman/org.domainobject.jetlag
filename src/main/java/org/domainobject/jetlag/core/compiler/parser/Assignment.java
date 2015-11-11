package org.domainobject.jetlag.core.compiler.parser;

import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.OPERATOR;

import java.util.ArrayList;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

public class Assignment extends AbstractExpression {

	private ArrayList<Comparison> operands;

	@Override
	public void parse() throws ParseException
	{
		while (true) {
			Comparison expr = new Comparison();
			operands.add(expr);
			expr.rule = this.rule;
			expr.tokenizer = this.tokenizer;
			expr.libDefs = this.libDefs;
			expr.parse();
			if (!isAssignmentOperator(tokenizer.peek()))
				break;
			tokenizer.nextToken();
			if (!tokenizer.hasMoreTokens())
				throw new ParseException(/* TODO */);
			tokenizer.nextToken();
		}
	}

	private static boolean isAssignmentOperator(Token token)
	{
		if (token == null || token.type() != OPERATOR)
			return false;
		return (((OperatorToken) token).getOperator().isAssignmentOperator());
	}
}
