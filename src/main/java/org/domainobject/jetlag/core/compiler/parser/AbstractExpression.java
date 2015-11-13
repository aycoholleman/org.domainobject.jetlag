package org.domainobject.jetlag.core.compiler.parser;

import static org.domainobject.jetlag.core.compiler.tokenizer.TokenType.OPERATOR;

import java.util.ArrayList;

import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.Token;
import org.domainobject.jetlag.core.compiler.tokenizer.Tokenizer;
import org.domainobject.jetlag.core.funclib.def.LibDef;

public abstract class AbstractExpression<T extends AbstractExpression<?>> {

	protected String rule;
	protected Tokenizer tokenizer;
	protected LibDef<?>[] libDefs;

	protected ArrayList<T> operands = new ArrayList<>(8);
	protected ArrayList<OperatorToken> operators = new ArrayList<>(8);

	public AbstractExpression()
	{
	}

	public void parse() throws ParseException
	{
		while (true) {
			T expr = createChild();
			operands.add(expr);
			descend(this, expr);
			if (done(tokenizer.peek()))
				break;
			operators.add((OperatorToken) tokenizer.nextToken());
			if (!tokenizer.hasMoreTokens())
				throw new ParseException(/* TODO */);
			tokenizer.nextToken();
		}
	}

	protected void descend(AbstractExpression<?> child) throws ParseException
	{
		child.rule = this.rule;
		child.tokenizer = this.tokenizer;
		child.libDefs = this.libDefs;
		child.parse();
	}

	protected abstract T createChild();

	protected abstract boolean isExpressionOperator(Token t);

	private boolean done(Token t)
	{
		if (t == null || t.type() != OPERATOR)
			return false;
		return isExpressionOperator(t);
	}

}
