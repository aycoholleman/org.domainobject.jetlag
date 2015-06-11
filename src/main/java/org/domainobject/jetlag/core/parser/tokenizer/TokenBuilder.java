package org.domainobject.jetlag.core.parser.tokenizer;

import java.util.Arrays;

/**
 * A dressed-down {@link StringBuilder}, capable only of appending characters,
 * and with some extra knowledge of what tokens are like (e.g. average length).
 * 
 * @author ayco
 * @created Apr 25, 2015
 *
 */
class TokenBuilder {

	static final char NIL = 0x00;
	static final char BACKSLASH = 0x5C;
	static final char DOUBLE_QUOTE = 0x22;
	static final char APOSTROPHE = 0x27;
	static final char LF = 0x0A;
	static final char CR = 0x0D;
	static final char TAB = 0x09;

	private char[] value;
	private int count;


	/**
	 * Create a {@code TokenBuilder} with the specified initial capacity.
	 * 
	 * @param capacity
	 *            The initial
	 */
	TokenBuilder(int capacity)
	{
		value = new char[capacity];
	}


	/**
	 * Returns the length (character count).
	 *
	 * @return The length of the sequence of characters currently represented by
	 *         this object
	 */
	int length()
	{
		return count;
	}


	/**
	 * Append the specified character.
	 * 
	 * @param c
	 *            The caracter to append
	 */
	void add(char c)
	{
		if (count == value.length) {
			value = Arrays.copyOf(value, value.length * 2);
		}
		value[count++] = c;
	}


	/**
	 * Append the specified characters.
	 * 
	 * @param c1
	 *            First character to append
	 * @param c2
	 *            Second character top append
	 */
	void add(char c1, char c2)
	{
		if (count + 1 == value.length) {
			value = Arrays.copyOf(value, value.length * 2);
		}
		value[count++] = c1;
		value[count++] = c2;
	}


	@Override
	public String toString()
	{
		return new String(value, 0, count);
	}

}
