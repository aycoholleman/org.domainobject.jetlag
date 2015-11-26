package org.domainobject.jetlag.core.compiler;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;

public enum Operator {

	/* Arithmetic operators */
	ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/"), MODULO("%"),
	/* Boolean operators */
	AND("&&"), OR("||"), NOT("!"),
	/* Comparison operators */
	EQUALS("="), NOTEQUALS("!="), LT("<"), GT(">"), LTE("<="), GTE(">="),
	/* String operators */
	STRCONCAT("&", "+"),
	/* Assignment operator */
	ASSIGN(":="),
	/* Library namespace operator */
	NAMESPACE("->", null);

	//@formatter:off
	private static final char[] startChars = new char[] {
		'+',
		'-',
		'*',
		'%',
		'/',
		'&',
		'|',
		'!',
		'=',
		'<',
		'>',
		':'
	};
	//@formatter:on

	private static final HashMap<String, Operator> table;
	private static final EnumSet<Operator> arithOps;
	private static final EnumSet<Operator> compOps;

	static {
		Arrays.sort(startChars);
		arithOps = EnumSet.range(ADD, MODULO);
		compOps = EnumSet.range(EQUALS, GTE);
		table = new HashMap<String, Operator>(values().length + 1, 1.0f);
		for (Operator op : values())
			table.put(op.symbol, op);
	}

	/**
	 * Look up the enum constant corresponding to the specified symbol.
	 * 
	 * @param symbol
	 *            The symbol to look up
	 * @return The {@code Operator} or {@code null} if the argument to this method is not
	 *         a valid operator
	 */
	public static Operator parse(String symbol)
	{
		return table.get(symbol);
	}

	/**
	 * Establishes whether the specified character the first character of at least one
	 * operator?
	 * 
	 * @param c
	 *            The character to test
	 * 
	 * @return Whether or not the character is the first character of at least one
	 *         operator
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
	 * Returns the symbol (i.e. character sequence) for this operator.
	 * 
	 * @return The symbol (i.e. character sequence) for this operator
	 */
	public String getSymbol()
	{
		return symbol;
	}

	/**
	 * Returns the Java translation for this operator.
	 * 
	 * @return The Java translation for this operator, or {@code null} if this operator
	 *         has no equivalent in Java.
	 */
	public String getJavaSymbol()
	{
		return javaSymbol;
	}

	/**
	 * Whether or not this is a logical operator.
	 * 
	 * @return
	 */
	public boolean isLogicalOperator()
	{
		return this == AND || this == OR;
	}

	/**
	 * Whether or not this is an assignment operator.
	 * 
	 * @return
	 */
	public boolean isAssignmentOperator()
	{
		return this == ASSIGN;
	}

	/**
	 * Whether or not this is a comparison operator.
	 * 
	 * @return
	 */
	public boolean isComparisonOperator()
	{
		return compOps.contains(this);
	}

	/**
	 * Whether or not this is a string operator.
	 * 
	 * @return
	 */
	public boolean isStringOperator()
	{
		return this == STRCONCAT;
	}

	/**
	 * Whether or not this is an arithmetic operator.
	 * 
	 * @return
	 */
	public boolean isArithmeticOperator()
	{
		return arithOps.contains(this);
	}

	public boolean isUnaryOperator()
	{
		return this == ADD || this == SUBTRACT || this == NOT;
	}
}
