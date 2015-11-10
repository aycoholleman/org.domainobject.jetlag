package org.domainobject.jetlag.core.parser.expression;

import org.domainobject.jetlag.core.parser.tokenizer.Token;

public class TerminalParser extends AbstractParser {

	public TerminalParser()
	{
	}

	@Override
	public void parse(AbstractParser firstOperand)
	{
		Token token = tokenizer.at();
		String data = token.data();
		switch (token.getType()) {
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
				else if (data.equals("")) {
					this.type = ExpressionType.TERMINAL_NULL;
				}
				break;
			default:
				break;

		}
	}
}
