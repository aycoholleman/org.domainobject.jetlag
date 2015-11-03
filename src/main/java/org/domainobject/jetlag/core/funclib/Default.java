package org.domainobject.jetlag.core.funclib;

/**
 * @author Ayco Holleman
 */
public enum Default {

	/**
	 * Used to indicate that there is no default value for a parameter. In other
	 * words, it is a required parameter.
	 */
	NONE,
	/**
	 * Used to indicate that a parameter is optional. If it is omitted, the
	 * current value of the field from which the function is called ("this") is
	 * used instead.
	 */
	THIS,
	/**
	 * Used to indicate that a parameter is optional. If it is omitted, an
	 * implementation-defined default value is used instead.
	 */
	AUTO

}
