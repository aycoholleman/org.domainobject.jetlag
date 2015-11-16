package org.domainobject.jetlag.core.compiler.parser;

import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.OPERATOR;

import java.util.ArrayList;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

abstract class Operation<T extends AbstractExpression> extends AbstractExpression {

	ArrayList<T> operands = new ArrayList<>(8);
	ArrayList<OperatorToken> operators = new ArrayList<>(8);

	@Override
	void parse() throws ParseException
	{
		while (true) {
			operands.add(parse(child()));
			if (done(tokenizer.peek()))
				break;
			operators.add((OperatorToken) tokenizer.nextToken());
			if (tokenizer.nextToken() == null)
				throw new ParseException(/* TODO */);
		}
	}

	abstract T child();

	abstract boolean isOperator(Token t);

	private boolean done(Token t)
	{
		if (t == null || t.type() != OPERATOR)
			return false;
		return isOperator(t);
	}

}
