package org.domainobject.jetlag.core.funclib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ayco Holleman
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface Declare {

	/**
	 * The name of the function being declared. N.B. this is not the name of the
	 * Java method that implements the function! It is the Jetlag user interface
	 * name of the function. Jetlag function names need not map one-to-one to
	 * Java method names. This attribute defaults to the name of the field
	 * decorated with the {@code Declare} annotation.
	 */
	String uiName() default "";

}
