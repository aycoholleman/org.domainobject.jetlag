package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

public class Assignment extends AbstractExpression<Comparison> {

	@Override
	protected Comparison createChild()
	{
		return new Comparison();
	}

	@Override
	protected boolean isExpressionOperator(Token token)
	{
		return (((OperatorToken) token).getOperator().isAssignmentOperator());
	}

}
