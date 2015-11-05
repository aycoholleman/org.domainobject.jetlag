package org.domainobject.jetlag.core.parser.expression;

import org.domainobject.jetlag.core.parser.tokenizer.Tokenizer;

public abstract class AbstractParser {

	protected String rule;
	protected Tokenizer tokenizer;

	// protected LibraryDefinition[] lib

	public AbstractParser()
	{
	}

	public abstract void parse(AbstractParser firstOperand);

}
