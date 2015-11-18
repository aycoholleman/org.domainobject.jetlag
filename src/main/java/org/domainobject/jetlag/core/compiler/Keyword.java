package org.domainobject.jetlag.core.compiler;

import java.util.HashMap;

/**
 * @author Ayco Holleman
 *
 */
public enum Keyword {

	NULL, NOT, AND, OR, TRUE, FALSE;

	private static final HashMap<String, Keyword> table = new HashMap<>(10, 1F);

	static {
		for (Keyword kw : values()) {
			table.put(kw.word, kw);
		}
	}

	/**
	 * Returns the enum constant corresponding to the specified string.
	 * 
	 * @param word
	 *            The string to lookup
	 * 
	 * @return The corresponding {@code KeyWord}, or {@code null} if the argument passed
	 *         to this method is not a keyword.
	 */
	public static Keyword parse(String word)
	{
		return table.get(word);
	}

	private final String word;

	public String toString()
	{
		return word;
	}

	private Keyword()
	{
		this.word = name().toLowerCase();
	}

	private Keyword(String word)
	{
		this.word = word;
	}

}
