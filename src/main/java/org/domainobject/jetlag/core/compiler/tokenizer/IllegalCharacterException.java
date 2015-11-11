package org.domainobject.jetlag.core.compiler.tokenizer;

/**
 * @author ayco
 * @created Apr 26, 2015
 *
 */
public final class IllegalCharacterException extends TokenizerException {

	private static final long serialVersionUID = 7232148208222647685L;
	private static final String MESSAGE_TEMPLATE = "Illegal character at position %s (line %s, column %s): '%s' (%s)";


	private static String createMessage(Cursor cursor)
	{
		int pos = cursor.position();
		int line = cursor.line();
		int col = cursor.column();
		char c = cursor.at();
		String unicode = "\\u" + Integer.toHexString(c | 0x10000).substring(1);
		return String.format(MESSAGE_TEMPLATE, pos, line, col, c, unicode);
	}

	private final Cursor cursor;


	public IllegalCharacterException(Cursor cursor)
	{
		super(createMessage(cursor));
		this.cursor = cursor;
	}


	public Cursor getCursor()
	{
		return cursor;
	}

}
