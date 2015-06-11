package org.domainobject.jetlag.core.parser.tokenizer;

/**
 * Class that scrolls along the characters of a {@code String} while keeping
 * track of line numbers and columns.
 * 
 * @author ayco
 * @created 11 jun. 2015
 *
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
	private int pos;
	private int line;
	private int col;
	private char curr;


	Cursor(String rule)
	{
		this.rule = rule;
		this.curr = rule.length() == 0 ? NIL : rule.charAt(0);
	}


	/*
	 * Get the character the cursor is currently pointing at
	 */
	char at()
	{
		return curr;
	}


	/*
	 * Check whether cursor is currently pointing at the specified character
	 */
	boolean at(char c)
	{
		return c == curr;
	}


	/*
	 * Get previous character
	 */
	char prev()
	{
		return pos == 0 ? NIL : rule.charAt(pos - 1);
	}


	/*
	 * Get next character (without moving the cursor forward)
	 */
	char peek()
	{
		return (curr == NIL || pos + 1 == rule.length()) ? NIL : rule.charAt(pos + 1);
	}


	/*
	 * Move the cursor forward and return the character at the new position
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