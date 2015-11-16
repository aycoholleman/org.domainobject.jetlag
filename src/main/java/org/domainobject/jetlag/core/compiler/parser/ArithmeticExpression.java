package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

class ArithmeticExpression extends Operation<Terminal> {

	@Override
	Terminal child()
	{
		return new Terminal();
	}

	@Override
	boolean isOperator(Token t)
	{
		return (((OperatorToken) t).getOperator().isArithmeticOperator());
	}

}
