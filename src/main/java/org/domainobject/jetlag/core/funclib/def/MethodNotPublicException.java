package org.domainobject.jetlag.core.funclib.def;

import java.lang.reflect.Method;

public final class MethodNotPublicException extends IllegalMethodModifierException {

	private static final long serialVersionUID = -518019799866806685L;


	public MethodNotPublicException(Method method)
	{
		super("Method annotated with @Function but not public: " + method.getName());
	}

}
