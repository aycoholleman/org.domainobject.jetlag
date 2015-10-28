package org.domainobject.jetlag.core.funclib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Parameter} annotation is used to describe a parameter of a Jetlag
 * function.
 * 
 * @author Ayco Holleman
 */
@Repeatable(Parameters.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Parameter {

	/**
	 * The name of the parameter as it must appear in the user interface.
	 * 
	 * @return
	 */
	String uiName() default "";

	/**
	 * The description of the parameter.
	 * 
	 * @return
	 */
	String value();

}

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@interface Parameters {

	Parameter[] value();
}
