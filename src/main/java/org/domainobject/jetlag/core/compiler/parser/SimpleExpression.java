package org.domainobject.jetlag.core.compiler.parser;

import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.*;

class SimpleExpression extends AbstractExpression {

	/* Will be either a SimpleExpression or a LogicalExpression */
	AbstractExpression child;

	@Override
	void parse() throws ParseException
	{
		if (tokenizer.at().type() == LPAREN) {
			if (tokenizer.hasMoreTokens())
				throw new EOFException(/* TODO */);
			tokenizer.nextToken();
			child = parse(new SimpleExpression());
			if (tokenizer.nextToken() == null)
				throw new EOFException(/* TODO */);
			if (tokenizer.at().type() != RPAREN)
				throw new ParseException(/* TODO */);
		}
		else {
			child = parse(new LogicalExpression());
		}
	}

}
