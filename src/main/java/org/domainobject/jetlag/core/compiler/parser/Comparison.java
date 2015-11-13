package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

public class Comparison extends AbstractExpression<StringExpression> {

	@Override
	protected StringExpression createChild()
	{
		return new StringExpression();
	}

	@Override
	protected boolean isExpressionOperator(Token token)
	{
		return (((OperatorToken) token).getOperator().isComparisonOperator());
	}
}
