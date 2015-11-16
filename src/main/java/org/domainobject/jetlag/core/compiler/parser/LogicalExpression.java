package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

class LogicalExpression extends Operation<Assignment> {

	@Override
	boolean isOperator(Token token)
	{
		return (((OperatorToken) token).getOperator().isLogicalOperator());
	}

	@Override
	Assignment child()
	{
		return new Assignment();
	}
}
