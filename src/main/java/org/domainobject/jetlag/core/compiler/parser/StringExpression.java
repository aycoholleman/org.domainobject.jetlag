package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

public class StringExpression extends AbstractExpression<ArithmeticExpression> {

	@Override
	protected ArithmeticExpression createChild()
	{
		return new ArithmeticExpression();
	}

	@Override
	protected boolean isExpressionOperator(Token token)
	{
		return (((OperatorToken) token).getOperator().isStringOperator());
	}
}
