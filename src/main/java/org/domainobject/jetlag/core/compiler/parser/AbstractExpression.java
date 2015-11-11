package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.Tokenizer;
import org.domainobject.jetlag.core.funclib.def.LibDef;

public abstract class AbstractExpression<T extends AbstractExpression<?>> {

	protected String rule;
	protected Tokenizer tokenizer;
	protected ExpressionType type;
	protected LibDef<?>[] libDefs;

	// protected

	public AbstractExpression()
	{
	}

	public abstract void parse() throws ParseException;

	protected SimpleExpression getExpression() throws ParseException
	{
		return (SimpleExpression) startOtherParser(new SimpleExpression());
	}

	protected AbstractExpression startOtherParser(AbstractExpression other) throws ParseException
	{
		other.rule = rule;
		other.tokenizer = tokenizer;
		other.libDefs = libDefs;
		other.parse();
		return other;
	}

}
