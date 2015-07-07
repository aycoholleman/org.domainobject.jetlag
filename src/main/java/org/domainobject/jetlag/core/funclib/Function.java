package org.domainobject.jetlag.core.funclib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation, when applied to a method, makes that method available in
 * jetlag's user interface. Methods without this annotation are considered
 * internal to the library. Methods with this annotation must be public, but not
 * all public methods must have this annotation.
 * 
 * @author Ayco Holleman
 * @created Jul 7, 2015
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Function {

	/**
	 * Provides the name of the function within jetlag's user interface.
	 * Function names used by the user need not map one-to-one to method names
	 * in a {@link AbstractFunctionLibrary}.
	 * 
	 * @return The name of the function within jetlag's user interface
	 */
	String uiName() default "";


	/**
	 * Provides the {@code uiName} of another method that implements the same
	 * user interface function, just with a larger method signature. In other
	 * words, the first parameters of the referenced method have the same type
	 * as the parameters of the method decorated using the {@code ref}
	 * attribute, but the referenced method has more parameters. Only the
	 * referenced method then needs to be described using annotation like
	 * {@link Description}. You must not specify the {@code uiName} and the
	 * {@code ref} attribute at the same time.
	 * 
	 * @return
	 */
	String ref() default "";

}
