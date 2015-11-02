package org.domainobject.jetlag.core.funclib;

public @interface VarArgsDefinition {

	/**
	 * The class upon which to invoke the static factory method that produces
	 * one element of the varargs array. May be left empty if the method is
	 * invoked on the element's class itself. Must be left empty if the element
	 * is to be instantiated through the constructor of the element's class.
	 * 
	 * @return
	 */
	Class<?> factoryClass() default void.class;

	String factoryMethod() default "";

	Class<?>[] userSignature();

	Class<?>[] javaSignature() default {};

}
