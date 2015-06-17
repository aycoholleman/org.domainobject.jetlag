package org.domainobject.jetlag.core.parser.tokenizer;

/**
 * Base class of all tokens.
 * 
 * @author Ayco Holleman
 * @created Apr 25, 2015
 *
 */
public abstract class Token {

	protected final Cursor cursor;

	private final int start;
	private final int line;

	private String data;
	private int end;


	/**
	 * Create a {@code Token} that extracts a data from the specified rule
	 * starting from the specified position ({@code start}).
	 * 
	 * @param rule
	 *            The rule to extract the token from
	 * @param start
	 *            The data index of the first character of the data
	 */
	Token(Cursor cursor)
	{
		this.cursor = cursor;
		this.start = cursor.position();
		this.line = cursor.line();
	}


	/**
	 * Get the type of the token. Since subclasses of {@code Token} only have
	 * package visibility, calling this method is the only way to figure out the
	 * actual type of the token.
	 * 
	 * @return The type of the data
	 */
	public abstract TokenType getType();


	public final void extract() throws TokenExtractionException
	{
		data = doExtract();
		end = cursor.position();
	}


	/*
	 * Method implementing the actual extraction logic. Subclasses are expected
	 * to move the cursor to the character just after the last character of the
	 * token, even if that means moving it beyond the end of the rule.
	 * Subclasses can and must assume that the cursor points at the first
	 * character of the token.
	 */
	abstract String doExtract() throws TokenExtractionException;


	/**
	 * Get the data index of the first character of the data.
	 * 
	 * @return The data index of the first character of the token
	 */
	public int start()
	{
		return start;
	}


	/**
	 * Get the line number of the first character of the data.
	 * 
	 * @return The line number of the first character of the token
	 */
	public int line()
	{
		return line;
	}


	/**
	 * Get the data index right <i>after</i> the last character of the data, or
	 * the index at which data extraction encountered an error.
	 * 
	 * @return The data index right after the last character of the token
	 */
	public int end()
	{
		return end;
	}


	/**
	 * Get the {@code String} extracted by this {@code Token} instance. If the
	 * {@link #extract()} method has not been called yet on this instance, an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return The {@code String} extracted by this {@code Token}
	 */
	public String data()
	{
		if (data == null) {
			throw new IllegalStateException("Token not extracted yet");
		}
		return data;
	}


	/**
	 * Returns a {@code String} representation of this {@code Token}.
	 * 
	 * @return The {@code String} representation of this token
	 */
	@Override
	public String toString()
	{
		return data;
	}

}
