package org.domainobject.jetlag.core.compiler.tokenizer;

/**
 * @author Ayco Holleman
 *
 */
public class NumberToken extends Token {

	static boolean isNumberStart(char c)
	{
		return Character.isDigit(c) || c == '.';
	}

	private boolean decimal;

	NumberToken(Cursor cursor)
	{
		super(cursor);
	}

	@Override
	public TokenType type()
	{
		return TokenType.NUMBER;
	}

	@Override
	String doExtract() throws TokenExtractionException
	{
		TokenBuilder token = new TokenBuilder(8);
		// The cursor now points at either a dot ('.') or a digit
		decimal = false;
		while (true) {
			if (Character.isDigit(cursor.at())) {
				token.add(cursor.at());
			}
			else if (cursor.at('.')) {
				if (decimal) {
					// Whoops, second time we hit a dot
					break;
				}
				decimal = true;
				token.add('.');
			}
			else {
				break;
			}
			cursor.forward();
		}
		return token.toString();
	}

	/**
	 * Was the extracted number a decimal number (in stead of an integer)?
	 * 
	 * @return Whether or not the extracted number was a decimal number
	 */
	public boolean isDecimal()
	{
		return decimal;
	}

}
