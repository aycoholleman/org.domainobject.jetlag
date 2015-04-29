package org.domainobject.jetlag.engine.tokenizer;

/**
 * A {@code Token} capable of extracting numerical strings (either integer or
 * decimal).
 * 
 * @author ayco
 * @created Apr 27, 2015
 *
 */
public class Number extends Token {

	private boolean decimal;


	public Number(String rule, int start)
	{
		super(rule, start);
	}


	@Override
	public void extract() throws TokenExtractionException
	{
		string = new TokenBuilder(8);
		end = start;
		// The cursor (end) now points to either a '.' or a digit
		decimal = false;
		for (char c = curchar();; c = advance()) {
			if (Character.isDigit(c)) {
				string.add(c);
			}
			else if (c == '.') {
				// Whoops, second time we hit a dot
				if (decimal) {
					break;
				}
				decimal = true;
				string.add('.');
			}
			else {
				// Done. Move cursor past closing quote
				++end;
				break;
			}
		}
	}


	@Override
	public TokenType getType()
	{
		return TokenType.NUMBER;
	}


	/**
	 * Was the extracted number found to be a decimal number (i.e. did it
	 * contain a dot)?
	 * 
	 * @return Whether or not the extracted number was found to be a decimal
	 *         number
	 */
	public boolean isDecimal()
	{
		return decimal;
	}

}
