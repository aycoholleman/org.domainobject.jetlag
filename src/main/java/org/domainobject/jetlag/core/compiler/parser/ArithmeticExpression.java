package org.domainobject.jetlag.core.compiler.parser;

import static org.domainobject.jetlag.core.compiler.parser.TerminalType.NUMBER_LITERAL;
import static org.domainobject.jetlag.core.compiler.parser.TerminalType.*;
import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.DOUBLE_QUOTED_STRING;
import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.LPAREN;
import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.NUMBER;
import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.OPERATOR;
import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.SINGLE_QUOTED_STRING;
import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.WORD;

import org.domainobject.jetlag.core.compiler.Keyword;
import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;
import org.domainobject.jetlag.core.compiler.tokenizer.TokenType;
import org.domainobject.jetlag.core.model.Rule;

class ArithmeticExpression extends Operation<AbstractExpression> {

	@Override
	AbstractExpression child()
	{
		Token token = tokenizer.at();
		TokenType tt = token.type();
		if (tt == SINGLE_QUOTED_STRING || tt == DOUBLE_QUOTED_STRING)
			return new Terminal(STRING_LITERAL, token);
		if (tt == NUMBER)
			return new Terminal(NUMBER_LITERAL, token);
		if (tt == LPAREN)
			return new SimpleExpression();
		if (tt == OPERATOR) {
			OperatorToken ot = (OperatorToken) token;
			if (ot.getOperator().isUnaryOperator())
				return new UnaryOperation();
		}
		if (tt == WORD) {
			if (Keyword.parse(token.data()) == Keyword.NOT)
				return new UnaryOperation();
			Rule rule = inspector.findRule(token.data());
			if (rule != null)
				return new Terminal(REFERENCE, token);
		}
		return null;
	}

	@Override
	boolean isOperator(Token t)
	{
		return (((OperatorToken) t).getOperator().isArithmeticOperator());
	}

}
