package org.domainobject.jetlag.core.compiler.parser;

/**
 * @author Ayco Holleman
 */
public enum TerminalType {
	/**
	 * 
	 */
	LITERAL_STRING,
	LITERAL_NUMBER,
	CONSTANT_BOOLEAN,
	CONSTANT_NULL,
	TERMINAL_VARIABLE,
	TERMINAL_FUNCTION,
	ARITHMETIC,
	STRING,
	COMPARISON,
	LOGICAL,
	ASSIGNMENT,
	EXPRESSION
}
