package org.domainobject.jetlag.core.compiler.parser;

import org.domainobject.jetlag.core.compiler.tokenizer.Tokenizer;
import org.domainobject.jetlag.core.funclib.def.LibDef;
import org.domainobject.jetlag.core.model.Rule;
import org.domainobject.jetlag.core.model.Template;

abstract class AbstractExpression {

	Template template;
	Rule rule;
	Tokenizer tokenizer;
	LibDef<?>[] libDefs;

	abstract void parse() throws ParseException;

	<T extends AbstractExpression> T parse(T child) throws ParseException
	{
		child.template = this.template;
		child.rule = this.rule;
		child.tokenizer = this.tokenizer;
		child.libDefs = this.libDefs;
		child.parse();
		return child;
	}

}
