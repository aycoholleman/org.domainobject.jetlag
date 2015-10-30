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
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface Param {

	/**
	 * The name of the parameter as it must appear in the user interface.
	 * Defaults to the name of the field to which this annotation was added.
	 * 
	 * @return
	 */
	String uiName() default "";

	/**
	 * Whether or not this is a required parameter. Alternatively, the parameter
	 * is optional and the current value of the field from which the function is
	 * called is used instead ({@link ParamPresence#THIS_IF_ABSENT
	 * THIS_IF_ABSENT}). Or the parameter is optional and an
	 * implementation-defined default is used (
	 * {@link ParamPresence#DEFAULT_IF_ABSENT DEFAULT_IF_ABSENT}). Only one
	 * parameter is allowed to be ({@link ParamPresence#THIS_IF_ABSENT
	 * THIS_IF_ABSENT}).
	 * 
	 * @return
	 */
	ParamPresence presence() default ParamPresence.REQUIRED;

	/**
	 * The default value, or a description of the default value for an optional
	 * pamater. This attribute should only be provided if {@link #presence()} is
	 * {@link ParamPresence#DEFAULT_IF_ABSENT DEFAULT_IF_ABSENT}).
	 * 
	 * @return
	 */
	String defaultValue() default "";

	/**
	 * The description of the parameter.
	 * 
	 * @return
	 */
	String descr();

}

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
@interface Params {

	Param[] value();
}
