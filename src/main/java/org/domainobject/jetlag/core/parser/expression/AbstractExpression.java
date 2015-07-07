package org.domainobject.jetlag.core.parser.expression;

import org.domainobject.jetlag.core.parser.tokenizer.Tokenizer;

public abstract class AbstractExpression {

	protected String rule;
	protected Tokenizer tokenizer;
	//protected LibraryDefinition[] lib

	public AbstractExpression() {
	}

}
