package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.Token;

class Terminal extends AbstractExpression {

	final TerminalType type;
	final Token token;

	Terminal(TerminalType type, Token token)
	{
		this.type = type;
		this.token = token;
	}

	@Override
	public void parse()
	{
	}

}
