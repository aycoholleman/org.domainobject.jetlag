package org.domainobject.jetlag.core.funclib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code &amp;Library} annotation marks a class as a jetlag function
 * library.
 * 
 * @author Ayco Holleman
 * @created Jul 10, 2015
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Library {

	/**
	 * The namespace of the library (e.g. "sql" in sql->select).
	 * 
	 * @return
	 */
	String namespace();

}
