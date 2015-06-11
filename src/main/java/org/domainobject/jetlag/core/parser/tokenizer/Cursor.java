package org.domainobject.jetlag.core.parser.tokenizer;

import static org.domainobject.jetlag.core.parser.tokenizer.TokenBuilder.CR;
import static org.domainobject.jetlag.core.parser.tokenizer.TokenBuilder.LF;
import static org.domainobject.jetlag.core.parser.tokenizer.TokenBuilder.NIL;

/**
 * @author ayco
 * @created 11 jun. 2015
 *
 */
class Cursor {

	private String rule;
	private int pos;
	private int line;
	private int col;
	private char curr;


	Cursor(String rule)
	{
		this.rule = rule;
		this.curr = rule.length() == 0 ? NIL : rule.charAt(0);
	}


	/**
	 * Get the character the cursor is currently pointing at.
	 * 
	 * @return The character the cursor is currently pointing at or or
	 *         {@link TokenBuilder#NIL NIL} if the cursor has moved past the
	 *         last character of the rule
	 */
	char at()
	{
		return curr;
	}


	/**
	 * Is the character the cursor is currently pointing at equal to the
	 * specified character?
	 * 
	 * @param c
	 *            The character to compare
	 * 
	 * @return Whether or not the character the cursor is currently pointing at
	 *         equal to the specified character
	 */
	boolean at(char c)
	{
		return c == curr;
	}


	/**
	 * Get previous character.
	 * 
	 * @return The previous character or {@link TokenBuilder#NIL NIL} if the
	 *         cursor is at the first character of the rule
	 */
	char prev()
	{
		return pos == 0 ? NIL : rule.charAt(pos - 1);
	}


	/**
	 * Get the next character (without moving the cursor forward).
	 * 
	 * @return The next character or {@link TokenBuilder#NIL NIL} if the cursor
	 *         is at the last character of the rule
	 */
	char peek()
	{
		return (curr == NIL || pos + 1 == rule.length()) ? NIL : rule.charAt(pos + 1);
	}


	/**
	 * Move the cursor forward and return the character at that position.
	 * 
	 * @return The next character or {@link TokenBuilder#NIL NIL} if the end of
	 *         the rule has been reached.
	 */
	char forward()
	{
		if (curr == NIL) {
			return NIL;
		}
		if (++pos == rule.length()) {
			return (curr = NIL);
		}
		curr = rule.charAt(pos);
		if (curr == CR) {
			++line;
			col = 0;
		}
		else if (curr == LF) {
			if (prev() != CR) {
				++line;
				col = 0;
			}
		}
		else {
			++col;
		}
		return curr;
	}


	int position()
	{
		return pos;
	}


	int line()
	{
		return line;
	}


	int column()
	{
		return col;
	}


	Cursor copy()
	{
		Cursor cursor = new Cursor(this.rule);
		cursor.pos = this.pos;
		cursor.line = this.line;
		cursor.col = this.col;
		cursor.curr = this.curr;
		return cursor;
	}

}
