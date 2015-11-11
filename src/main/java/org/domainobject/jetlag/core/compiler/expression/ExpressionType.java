package org.domainobject.jetlag.core.compiler.expression;

/**
 * @author Ayco Holleman
 */
public enum ExpressionType
{
	/**
	 * 
	 */
	UNKNOWN,
	TERMINAL_STRING,
	TERMINAL_NUMBER,
	TERMINAL_BOOLEAN,
	TERMINAL_NULL,
	TERMINAL_VARIABLE,
	TERMINAL_FUNCTION,
	ARITHMETIC,
	STRING,
	COMPARISON,
	LOGICAL,
	ASSIGNMENT
}
