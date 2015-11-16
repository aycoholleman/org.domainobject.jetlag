package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

class Comparison extends Operation<StringExpression> {

	@Override
	StringExpression child()
	{
		return new StringExpression();
	}

	@Override
	boolean isOperator(Token token)
	{
		return (((OperatorToken) token).getOperator().isComparisonOperator());
	}
}
