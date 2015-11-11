package org.domainobject.jetlag.core.compiler.parser;

import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.OPERATOR;

import java.util.ArrayList;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

public class Comparison extends AbstractExpression {

	private ArrayList<StringExpression> operands;
	private ArrayList<OperatorToken> operators;

	@Override
	public void parse() throws ParseException
	{
		while (true) {
			StringExpression expr = new StringExpression();
			operands.add(expr);
			expr.rule = this.rule;
			expr.tokenizer = this.tokenizer;
			expr.libDefs = this.libDefs;
			expr.parse();
			if (!isComparisonOperator(tokenizer.peek()))
				break;
			operators.add((OperatorToken) tokenizer.nextToken());
			if (!tokenizer.hasMoreTokens())
				throw new ParseException(/* TODO */);
			tokenizer.nextToken();
		}
	}

	private static boolean isComparisonOperator(Token token)
	{
		if (token == null || token.type() != OPERATOR)
			return false;
		return (((OperatorToken) token).getOperator().isComparisonOperator());
	}
}
