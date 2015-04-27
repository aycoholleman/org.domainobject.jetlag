package org.domainobject.jetlag.engine.tokenizer;

import static org.domainobject.jetlag.engine.tokenizer.TokenBuilder.NIL;

/**
 * Base class of all tokens.
 * 
 * @author ayco
 * @created Apr 25, 2015
 *
 */
public abstract class Token {

	protected final String rule;
	protected final int start;

	protected TokenBuilder string;
	protected int end = -1;


	/**
	 * Create a {@code Token} that extracts a token from the specified rule
	 * starting from the specified position ({@code start})
	 * 
	 * @param rule
	 *            The rule to extract the token from
	 * @param start
	 *            The string index of the first character of the token
	 */
	public Token(String rule, int start)
	{
		if (rule == null) {
			throw new NullPointerException("First argument to Token constructor must not null");
		}
		this.rule = rule;
		this.start = start;
	}


	/**
	 * Extract the token from the rule. Subclasses are expected to do two
	 * things:
	 * <ol>
	 * <li>Create and fill a {@link TokenBuilder} and assign it to the
	 * {@link #string} field of this class
	 * <li>Set the {@link #end} field to right <i>after</i> the last character
	 * of the token.
	 * </ol>
	 * Subclasses can and must assume that the {@link start} field contains the
	 * index of the first character of the token. E.g. for a single quoted
	 * string {@code start} is the position of the opening quote. After the
	 * extract method completes, {@code cursor} must be the index <i>after</i>
	 * the closing quote.
	 * 
	 * 
	 * @throws TokenExtractionException
	 */
	public abstract void extract() throws TokenExtractionException;


	/**
	 * Get the type of the token.
	 * 
	 * @return The type of the token
	 */
	public abstract TokenType getType();


	/**
	 * Get the string index of the first character of the token.
	 * 
	 * @return The string index of the first character of the token
	 */
	public int start()
	{
		return start;
	}


	/**
	 * Get the string index right <i>after</i> the last character of the token,
	 * or the index at which token extraction encountered an error.
	 * 
	 * @return The string index right after the last character of the token
	 */
	public int end()
	{
		return end;
	}


	/**
	 * Get the {@code String} extracted from the rule by this {@code Token}.
	 * 
	 * @return The {@code String} extracted from the rule by this {@code Token}
	 */
	public String string()
	{
		if (string == null) {
			throw new IllegalStateException("Token not extracted yet");
		}
		return string.toString();
	}


	/**
	 * Have we reached the end of the rule?
	 * 
	 * @return
	 */
	protected final boolean eof()
	{
		return end == rule.length();
	}


	/**
	 * Get the character that the cursor ({@link #end}) is currently pointing
	 * to, or {@link TokenBuilder#NIL NIL} if the cursor has moved past the end
	 * of the rule.
	 * 
	 * @return The character withinin the rule that the cursor ({@link #end}) is
	 *         currently pointing to, or {@link TokenBuilder#NIL NIL} if the
	 *         cursor has moved past the end of the rule.
	 */
	protected final char curchar()
	{
		return end >= rule.length() ? NIL : rule.charAt(end);
	}


	/**
	 * Peek one character ahead in the rule, without moving the cursor forward.
	 * 
	 * @return The next character in the rule, or {@link TokenBuilder#NIL NIL}
	 *         if the cursor ({@link #end}) has has moved past the end of the
	 *         rule.
	 * 
	 */
	protected final char peek()
	{
		return end + 1 >= rule.length() ? NIL : rule.charAt(end + 1);
	}


	/**
	 * Move to the cursor ({@link #end}) forward and return the character at
	 * that position within the rule.
	 * 
	 * @return The next character in the rule, or {@link TokenBuilder#NIL NIL}
	 *         if the cursor ({@link #end}) has has moved past the end of the
	 *         rule.
	 */
	protected char advance()
	{
		return end + 1 == rule.length() ? NIL : rule.charAt(++end);
	}
}
