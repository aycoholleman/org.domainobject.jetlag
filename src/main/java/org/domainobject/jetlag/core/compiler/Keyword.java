package org.domainobject.jetlag.core.compiler;

import java.util.HashMap;

/**
 * @author ayco
 * @created May 1, 2015
 *
 */
public enum Keyword
{
	NULL, NOT, AND, OR, TRUE, FALSE, EITHER, NEITHER;

	private static final HashMap<String, Keyword> table = new HashMap<>(10, 1F);

	static {
		for (Keyword kw : values()) {
			table.put(kw.word, kw);
		}
	}


	/**
	 * Get the enum value corresponding to the specified keyword.
	 * 
	 * @param keyword
	 *            The {@code String} to lookup
	 * @return The corresponding {@code KeyWord}, or {@code null} if the
	 *         argument pased to this method is not a keyword.
	 */
	public static Keyword lookup(String keyword)
	{
		return table.get(keyword);
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
