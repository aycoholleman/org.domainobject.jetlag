package org.domainobject.jetlag.core.funclib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.domainobject.jetlag.core.runtime.CallInfo;

/**
 * The {@code Parameter} annotation is used to describe a parameter of a
 * function. In order to prevent cluttering up your code, you add this
 * annotation to the method, not to the parameter described by the
 * &amp;Parameter annotation.
 * 
 * 
 * @author Ayco Holleman
 * @created Jul 7, 2015
 *
 */
@Repeatable(Parameters.class)
public @interface Parameter {

	/**
	 * The index of parameter within the parameter list. Note that all methods
	 * implementing a user interface function must always have a
	 * {@link CallInfo} object as their first parameter. This parameter must
	 * never be documented, because it is not provided by the user but inserted
	 * quietly by the compiler. Thus parameter counting starts with 1 (not with
	 * 0). You only need to provide this attribute if you use this annotation
	 * repeatedly to describe the same argument. If there are as many
	 * {@code &amp;Parameter} annotations as there are parameters (not counting
	 * the {@code CallInfo} parameter), each {@code &amp;Parameter} annotation
	 * is assumed to describe one parameter in the paramter list.
	 * 
	 * @return
	 */
	int position() default -1;


	/**
	 * The name of the argument as it must appear in the user interface.
	 * 
	 * @return
	 */
	String uiName() default "";


	/**
	 * Description of the argument
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
