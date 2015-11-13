package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

public class LogicalExpression extends AbstractExpression<Assignment> {

	@Override
	protected boolean isExpressionOperator(Token token)
	{
		return (((OperatorToken) token).getOperator().isLogicalOperator());
	}

	@Override
	protected Assignment createChild()
	{
		return new Assignment();
	}
}
