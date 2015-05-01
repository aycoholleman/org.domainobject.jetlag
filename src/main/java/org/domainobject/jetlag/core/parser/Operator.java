package org.domainobject.jetlag.core.parser;

import java.util.Arrays;
import java.util.EnumSet;

public enum Operator
{

	// Arithmetic operators
	ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/"), MODULO("%"),
	// Boolean operators
	AND("&&"), OR("||"), NOT("!"),
	// Comparison operators
	EQUALS("="), NOTEQUALS("!="), LT("<"), GT(">"), LTE("<="), GTE(">="),
	// String operators
	STRCONCAT("&"),
	// Assignment operator
	ASSIGN(":="),
	// Library operator
	LIB("->");

	private static final String[] symbols = new String[Operator.values().length];

	private static final EnumSet<Operator> multicharOps = EnumSet.of(AND, OR, NOTEQUALS, LTE, GTE, LIB);
	private static final EnumSet<Operator> arithmeticOps = EnumSet.of(ADD, SUBTRACT, MULTIPLY, DIVIDE, MODULO);
	private static final EnumSet<Operator> booleanOps = EnumSet.of(AND, OR, NOT);
	private static final EnumSet<Operator> comparisonOps = EnumSet.of(EQUALS, NOTEQUALS, LT, GT, LTE, GTE);

	private static final char[] startChars = new char[] { '+', '-', '*', '%', '/', '&', '|', '!', '=', '<', '>', ':' };

	static {
		Arrays.sort(startChars);
		
		Operator[] ops = values();
		for (int i = 0; i < ops.length; ++i) {
			symbols[i] = ops[i].symbol;
		}
		
	}


	/**
	 * Is the specified character the first character of at least one operator?
	 * 
	 * @param c
	 *            The character to test
	 * @return Whether or not the character is the first character of at least
	 *         one operator
	 */
	public static boolean isOperatorCharacter(char c)
	{
		return Arrays.binarySearch(startChars, c) >= 0;
	}

	private final String symbol;


	private Operator(String symbol)
	{
		this.symbol = symbol;
	}


	public String getSymbol()
	{
		return symbol;
	}

}
