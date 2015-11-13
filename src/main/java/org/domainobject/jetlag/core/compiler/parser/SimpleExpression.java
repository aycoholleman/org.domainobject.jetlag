package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.Token;
import org.domainobject.jetlag.core.compiler.tokenizer.TokenType;

public class SimpleExpression extends AbstractExpression<LogicalExpression> {

	private SimpleExpression nested;

	@Override
	public void parse() throws ParseException
	{
		if (tokenizer.at().type() == TokenType.LPAREN) {
			if (tokenizer.hasMoreTokens())
				throw new EOFException(/* TODO */);
			tokenizer.nextToken();
			nested = new SimpleExpression();
			descend(nested);
			if (tokenizer.nextToken() == null)
				throw new EOFException(/* TODO */);
			if (tokenizer.at().type() != TokenType.RPAREN)
				throw new ParseException(/* TODO */);
		}
		else {
			super.parse();
		}
	}

	@Override
	protected LogicalExpression createChild()
	{
		return new LogicalExpression();
	}

	@Override
	protected boolean isExpressionOperator(Token t)
	{
		return false;
	}

}
