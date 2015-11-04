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
@Repeatable(Params.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface Param {

	/**
	 * The Jetlage user interface name of the parameter.
	 * 
	 * @return
	 */
	String uiName();

	/**
	 * Whether or not the parameter has a default value (i&#46;e&#46; whether or
	 * not it is a required parameter).
	 * 
	 * @return
	 */
	Default dfault() default Default.NONE;

	/**
	 * The default value, or a description of the default value for an optional
	 * paramater. This attribute should only be provided if {@link #dfault()} is
	 * set to {@link Default#AUTO AUTO}). It is ignored otherwise.
	 * 
	 * @return
	 */
	String autoValue() default "";

	/**
	 * The description of the parameter.
	 * 
	 * @return
	 */
	String descr();

}

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@interface Params {

	Param[] value();
}
