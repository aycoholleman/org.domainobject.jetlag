package org.domainobject.jetlag.core.parser.tokenizer;

/**
 * Base class of all tokens.
 * 
 * @author ayco
 * @created Apr 25, 2015
 *
 */
public abstract class Token {

	protected final Cursor cursor;

	private final int start;
	private final int line;

	private String token;
	private int end;


	/**
	 * Create a {@code Token} that extracts a token from the specified rule
	 * starting from the specified position ({@code start}).
	 * 
	 * @param rule
	 *            The rule to extract the token from
	 * @param start
	 *            The token index of the first character of the token
	 */
	Token(Cursor cursor)
	{
		this.cursor = cursor;
		this.start = cursor.position();
		this.line = cursor.line();
	}


	/**
	 * Get the type of the token. Could also be inferred by calling
	 * {@code getClass()} or using {@code instanceof}, but this is more
	 * friendly.
	 * 
	 * @return The type of the token
	 */
	public abstract TokenType getType();


	public final void extract() throws TokenExtractionException
	{
		token = doExtract();
		end = cursor.position();
	}


	/**
	 * Method implementing the actual extraction logic for a particular
	 * {@code Token} subclass. Subclasses are expected to move to the character
	 * just <i>after</i> the last character of the token, even if that means
	 * moving it beyond the end of the rule. Subclasses can and must assume that
	 * the cursor points at the first character of the token.
	 * 
	 * @return The token
	 * 
	 * @throws TokenExtractionException
	 */
	abstract String doExtract() throws TokenExtractionException;


	/**
	 * Get the token index of the first character of the token.
	 * 
	 * @return The token index of the first character of the token
	 */
	public int start()
	{
		return start;
	}


	/**
	 * Get the line number of the first character of the token.
	 * 
	 * @return The line number of the first character of the token
	 */
	public int line()
	{
		return line;
	}


	/**
	 * Get the token index right <i>after</i> the last character of the token,
	 * or the index at which token extraction encountered an error.
	 * 
	 * @return The token index right after the last character of the token
	 */
	public int end()
	{
		return end;
	}


	/**
	 * Get the {@code String} extracted by this {@code Token} instance
	 * 
	 * @return The {@code String} extracted by this {@code Token}
	 */
	public String string()
	{
		if (token == null) {
			throw new IllegalStateException("Token not extracted yet");
		}
		return token.toString();
	}


	/**
	 * Equivalent to calling {@link #string()}.
	 * 
	 * @return The {@code String} representation of this token
	 */
	@Override
	public String toString()
	{
		return string();
	}

}
