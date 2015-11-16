package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

class StringExpression extends Operation<ArithmeticExpression> {

	@Override
	ArithmeticExpression child()
	{
		return new ArithmeticExpression();
	}

	@Override
	boolean isOperator(Token token)
	{
		return (((OperatorToken) token).getOperator().isStringOperator());
	}
}
