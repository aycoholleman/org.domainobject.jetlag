package org.domainobject.jetlag.core.compiler.parser;

class ExpressionTerminal extends Terminal {

	SimpleExpression expression;

	ExpressionTerminal(SimpleExpression expression)
	{
		this.expression = expression;
		this.type = TerminalType.EXPRESSION;
	}

}
