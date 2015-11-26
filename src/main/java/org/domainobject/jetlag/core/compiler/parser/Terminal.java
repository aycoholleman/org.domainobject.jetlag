package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.Token;
import static org.domainobject.jetlag.core.compiler.parser.TerminalType.*;

class Terminal extends AbstractExpression {

	protected TerminalType type;
	private Token terminal;
	private SimpleExpression expression;

	@Override
	public void parse()
	{
		Token token = tokenizer.at();
		String data = token.data();
		switch (token.type()) {
			case COMMA:
				break;
			case SINGLE_QUOTED_STRING:
			case DOUBLE_QUOTED_STRING:
				type = LITERAL_STRING;
				break;
			case NUMBER:
				type = LITERAL_NUMBER;
				break;
			case LPAREN:
				expression = new SimpleExpression();
				break;
			case OPERATOR:
				break;
			case RPAREN:
				break;
			case WORD:
				if (data.equals("false") || data.equals("true")) {
					this.type = CONSTANT_BOOLEAN;
				}
				else if (data.equals("null")) {
					this.type = CONSTANT_NULL;
				}
				else if (data.equals("not")) {
					this.type = TerminalType.CONSTANT_BOOLEAN;
				}
				break;
			default:
				break;
		}
	}

}
