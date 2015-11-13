package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

public class ArithmeticExpression extends AbstractExpression<Terminal> {

	@Override
	protected Terminal createChild()
	{
		return new Terminal();
	}

	@Override
	protected boolean isExpressionOperator(Token t)
	{
		return (((OperatorToken) t).getOperator().isArithmeticOperator());
	}

}
