package org.domainobject.jetlag.core.compiler.expression;

import org.domainobject.jetlag.core.compiler.tokenizer.Tokenizer;
import org.domainobject.jetlag.core.funclib.def.LibDef;

public abstract class AbstractParser {

	protected String rule;
	protected Tokenizer tokenizer;
	protected ExpressionType type;

	protected LibDef<?>[] libDefs;

	public AbstractParser()
	{
	}

	public abstract void parse(AbstractParser firstOperand);

	protected ExpressionParser startExpressionParser(AbstractParser firstOperand)
	{
		return (ExpressionParser) startOtherParser(new ExpressionParser(), firstOperand);
	}

	protected AbstractParser startOtherParser(AbstractParser other, AbstractParser firstOperand)
	{
		other.rule = rule;
		other.tokenizer = tokenizer;
		other.libDefs = libDefs;
		other.parse(firstOperand);
		return other;
	}

}
