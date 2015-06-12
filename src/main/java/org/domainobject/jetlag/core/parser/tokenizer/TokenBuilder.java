package org.domainobject.jetlag.core.parser.tokenizer;

import java.util.Arrays;

/**
 * A dressed-down {@link StringBuilder}, capable only of appending characters,
 * and with some extra knowledge of what tokens are like (e.g. average length).
 * 
 * @author Ayco Holleman
 * @created Apr 25, 2015
 *
 */
class TokenBuilder {

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
