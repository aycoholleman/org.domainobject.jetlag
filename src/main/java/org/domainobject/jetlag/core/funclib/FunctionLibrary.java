package org.domainobject.jetlag.core.funclib;

import org.domainobject.jetlag.core.model.Rule;
import org.domainobject.jetlag.core.model.Template;
import org.domainobject.jetlag.core.runtime.TemplateRunner;

/**
 * @author ayco
 * @created May 6, 2015
 *
 */
public abstract class FunctionLibrary {
	
	protected TemplateRunner templateRunner;
	protected Template template;
	protected Rule[] rules;

}
