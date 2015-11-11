package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.Token;

public class Terminal extends AbstractExpression {

	private Token terminal;

	@Override
	public void parse()
	{
		Token token = tokenizer.at();
		String data = token.data();
		switch (token.type()) {
			case COMMA:
				break;
			case DOUBLE_QUOTED_STRING:
				break;
			case LPAREN:
				break;
			case NUMBER:
				break;
			case OPERATOR:
				break;
			case RPAREN:
				break;
			case SINGLE_QUOTED_STRING:
				break;
			case WORD:
				if (data.equals("false") || data.equals("true")) {
					this.type = ExpressionType.TERMINAL_BOOLEAN;
				}
				else if (data.equals("null")) {
					this.type = ExpressionType.TERMINAL_NULL;
				}
				else if (data.equals("not")) {
					this.type = ExpressionType.TERMINAL_BOOLEAN;
				}
				break;
			default:
				break;
		}
	}

}
