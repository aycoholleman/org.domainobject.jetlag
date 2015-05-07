package org.domainobject.jetlag.core.funclib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Function {

	/**
	 * The name of the corresponding function in the user interface.
	 * @return
	 */
	String uiName();
}
