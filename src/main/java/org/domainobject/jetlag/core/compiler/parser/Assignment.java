package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;

class Assignment extends Operation<Comparison> {

	@Override
	Comparison child()
	{
		return new Comparison();
	}

	@Override
	boolean isOperator(Token token)
	{
		return (((OperatorToken) token).getOperator().isAssignmentOperator());
	}

}
