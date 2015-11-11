package org.domainobject.jetlag.core.compiler.tokenizer;

/**
 * Class that scrolls along the characters of a rule while keeping track of line
 * numbers and column numbers. {@link Token} instances and
 * {@link TokenExtractor} instances can only access the contents of a rule
 * through a {@code Cursor}.
 * 
 * @author Ayco Holleman
 */
class Cursor {

	static final char NIL = 0x00;
	static final char TAB = 0x09;
	static final char LF = 0x0A;
	static final char CR = 0x0D;
	static final char DOUBLE_QUOTE = 0x22;
	static final char APOSTROPHE = 0x27;
	static final char BACKSLASH = 0x5C;

	private String rule;
	private int ptr = 0;
	private int line;
	private int col;
	private char curr;

	Cursor(String rule)
	{
		this.rule = rule;
		this.curr = rule.length() == 0 ? NIL : rule.charAt(0);
	}

	/**
	 * Returns the character the cursor is currently pointing at.
	 */
	char at()
	{
		return curr;
	}

	/**
	 * Checks whether cursor is currently pointing at the specified character.
	 */
	boolean at(char c)
	{
		return c == curr;
	}

	/**
	 * Returns previous character.
	 */
	char prev()
	{
		if (ptr == 0)
			return NIL;
		return rule.charAt(ptr - 1);
	}

	/**
	 * Returns next character (without moving the cursor forward).
	 */
	char peek()
	{
		return (curr == NIL || ptr + 1 == rule.length()) ? NIL : rule.charAt(ptr + 1);
	}

	/**
	 * Moves the cursor to the next character.
	 * 
	 * @return
	 */
	Cursor forward()
	{
		if (curr == NIL)
			return this;
		curr = ++ptr == rule.length() ? NIL : rule.charAt(ptr);
		checkPosition();
		return this;
	}

	/**
	 * Returns the current position of the cursor.
	 * 
	 * @return
	 */
	int position()
	{
		return ptr;
	}

	/**
	 * Returns the line number of the character currently pointed at by the
	 * cursor.
	 * 
	 * @return
	 */
	int line()
	{
		return line;
	}

	/**
	 * Returns the column number of the character currently pointed at by the
	 * cursor.
	 * 
	 * @return
	 */
	int column()
	{
		return col;
	}

	/**
	 * Returns a clone of this {@code Cursor} object.
	 * 
	 * @return
	 */
	Cursor copy()
	{
		Cursor cursor = new Cursor(this.rule);
		cursor.ptr = this.ptr;
		cursor.line = this.line;
		cursor.col = this.col;
		cursor.curr = this.curr;
		return cursor;
	}

	// Keeps track of lines and columns
	private void checkPosition()
	{
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
	}

}
