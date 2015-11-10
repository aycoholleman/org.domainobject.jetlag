package org.domainobject.jetlag.core.parser.expression;

import org.domainobject.jetlag.core.parser.tokenizer.TokenType;

public class ExpressionParser extends AbstractParser {

	public ExpressionParser()
	{
	}

	@Override
	public void parse(AbstractParser firstOperand)
	{
		if (tokenizer.at().getType() == TokenType.LPAREN) {

		}
	}

}
