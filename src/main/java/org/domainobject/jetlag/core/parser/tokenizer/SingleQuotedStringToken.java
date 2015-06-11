package org.domainobject.jetlag.core.parser.tokenizer;

import static org.domainobject.jetlag.core.parser.tokenizer.Cursor.APOSTROPHE;
import static org.domainobject.jetlag.core.parser.tokenizer.Cursor.BACKSLASH;
import static org.domainobject.jetlag.core.parser.tokenizer.Cursor.NIL;

/**
 * {@link Token} subclass for extracting single quoted strings.
 * 
 * @author ayco
 * @created Apr 25, 2015
 *
 */
public final class SingleQuotedStringToken extends Token {

	SingleQuotedStringToken(Cursor cursor)
	{
		super(cursor);
	}


	@Override
	public TokenType getType()
	{
		return TokenType.SINGLE_QUOTED_STRING;
	}


	@Override
	String doExtract() throws StringNotTerminatedException
	{		
		TokenBuilder token = new TokenBuilder(16);
		// The cursor now points at the opening quote
		while (true) {
			// Move past opening quote
			cursor.forward();
			if (cursor.at(NIL)) {
				throw new StringNotTerminatedException(this);
			}
			else if (cursor.at(APOSTROPHE)) {
				// Done. Move cursor past closing quote
				cursor.forward();
				break;
			}
			else if (cursor.at(BACKSLASH)) {
				cursor.forward();
				if (cursor.at(NIL)) {
					throw new StringNotTerminatedException(this);
				}
				token.add(BACKSLASH, cursor.at());
			}
			else {
				token.add(cursor.at());
			}
		}
		return token.toString();
	}

}
