package org.domainobject.jetlag.core.parser.tokenizer;

/**
 * @author ayco
 * @created Apr 26, 2015
 *
 */
public final class IllegalCharacterException extends TokenizerException {

	private static final long serialVersionUID = 7232148208222647685L;


	private static String unicode(char c)
	{
		return "\\u" + Integer.toHexString(c | 0x10000).substring(1);
	}

	private final char character;
	private final int position;


	public IllegalCharacterException(char c, int position)
	{
		super(String.format("Illegal character at position %s: \"%s\" (%s)", c, position, unicode(c)));
		this.character = c;
		this.position = position;
	}


	public char getCharacter()
	{
		return character;
	}


	public int getPosition()
	{
		return position;
	}

}
