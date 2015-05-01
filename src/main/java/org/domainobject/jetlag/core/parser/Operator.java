package org.domainobject.jetlag.core.parser;

import java.util.Arrays;

public enum Operator
{

	/* Arithmetic operators */
	ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/"), MODULO("%"),
	/* Boolean operators */
	AND("&&"), OR("||"), NOT("!"),	
	/* Comparison operators */
	EQUALS("=", "=="), NOTEQUALS("!="), LT("<"), GT(">"), LTE("<="), GTE(">="),	
	/* String operators */
	STRCONCAT("&", "+"),	
	/* Assignment operator */
	ASSIGN(":=", "="),	
	/* Library namespace operator */
	NAMESPACE("->", null);

	private static final char[] startChars = new char[] { '+', '-', '*', '%', '/', '&', '|', '!', '=', '<', '>', ':' };
	private static final String[] symbols = new String[Operator.values().length];
	private static final Operator[] opsAlphabetical = new Operator[Operator.values().length];

	static {
		Arrays.sort(startChars);
		Operator[] ops = values();
		for (int i = 0; i < ops.length; ++i) {
			symbols[i] = ops[i].symbol;
			opsAlphabetical[i] = ops[i];
		}
	}


	/**
	 * Look up the {@code Operator} enum value corresponding to the specified
	 * symbol.
	 * 
	 * @param symbol
	 *            The symbol to look up
	 * @return The {@code Operator} or {@code null} if the argument to this
	 *         method is not a valid operator
	 */
	public static Operator forSymbol(String symbol)
	{
		int i = Arrays.binarySearch(symbols, symbol);
		return i < 0 ? null : opsAlphabetical[i];
	}


	/**
	 * Is the specified character the first character of at least one operator?
	 * 
	 * @param c
	 *            The character to test
	 * @return Whether or not the character is the first character of at least
	 *         one operator
	 */
	public static boolean isOperatorStart(char c)
	{
		return Arrays.binarySearch(startChars, c) >= 0;
	}

	private final String symbol;
	private final String javaSymbol;


	private Operator(String symbol)
	{
		this.symbol = symbol;
		this.javaSymbol = symbol;
	}


	private Operator(String symbol, String javaSymbol)
	{
		this.symbol = symbol;
		this.javaSymbol = javaSymbol;
	}


	/**
	 * Get the symbol (i.e. character sequence) for this operator
	 * 
	 * @return The symbol (i.e. character sequence) for this operator
	 */
	public String getSymbol()
	{
		return symbol;
	}


	/**
	 * Get the Java translation for this operator
	 * 
	 * @return The Java translation for this operator, or {@code null} if this
	 *         operator has no equivalent in Java.
	 */
	public String getJavaSymbol()
	{
		return javaSymbol;
	}


	/**
	 * Whether or not this {@code Operator} is an arithmetic operator.
	 * 
	 * @return Whether or not this {@code Operator} is an arithmetic operator
	 */
	public boolean isArithmetic()
	{
		return this == ADD || this == SUBTRACT || this == MULTIPLY || this == DIVIDE || this == MODULO;
	}


	/**
	 * Whether or not this {@code Operator} is a boolean operator.
	 * 
	 * @return Whether or not this {@code Operator} is an boolean operator
	 */
	public boolean isBoolean()
	{
		return this == AND || this == OR || this == NOT;
	}


	/**
	 * Whether or not this {@code Operator} is a comparison operator.
	 * 
	 * @return Whether or not this {@code Operator} is an arithmetic operator
	 */
	public boolean isComparison()
	{
		return this == EQUALS || this == NOTEQUALS || this == LT || this == GT || this == LTE || this == GTE;
	}
}
