package org.domainobject.jetlag.core.compiler.parser;

import static org.domainobject.jetlag.core.compiler.parser.TerminalType.LITERAL_NUMBER;
import static org.domainobject.jetlag.core.compiler.parser.TerminalType.LITERAL_STRING;
import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.DOUBLE_QUOTED_STRING;
import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.LPAREN;
import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.NUMBER;
import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.OPERATOR;
import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.SINGLE_QUOTED_STRING;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;
import org.domainobject.jetlag.core.compiler.tokenizer.TokenType;

class ArithmeticExpression extends Operation<AbstractExpression> {

	@Override
	AbstractExpression child()
	{
		Token token = tokenizer.at();
		TokenType tt = token.type();
		if (tt == SINGLE_QUOTED_STRING || tt == DOUBLE_QUOTED_STRING) {
			LiteralExpression terminal = new LiteralExpression();
			terminal.type = LITERAL_STRING;
			terminal.literal = token;
			return terminal;
		}
		else if (tt == NUMBER) {
			LiteralExpression terminal = new LiteralExpression();
			terminal.type = LITERAL_NUMBER;
			terminal.literal = token;
			return terminal;
		}
		else if (tt == LPAREN) {
			return new SimpleExpression();
		}
		else if (tt == OPERATOR) {
			OperatorToken ot = (OperatorToken) token;
			if (ot.getOperator().isUnaryOperator()) {
				return new UnaryOperation();
			}
		}
		return null;
	}

	@Override
	boolean isOperator(Token t)
	{
		return (((OperatorToken) t).getOperator().isArithmeticOperator());
	}

}
