package org.domainobject.jetlag.core.parser.tokenizer;

import static org.domainobject.jetlag.core.parser.tokenizer.TokenBuilder.APOSTROPHE;
import static org.domainobject.jetlag.core.parser.tokenizer.TokenBuilder.BACKSLASH;
import static org.domainobject.jetlag.core.parser.tokenizer.TokenBuilder.NIL;

/**
 * {@link Token} subclass for extracting single quoted strings.
 * 
 * @author ayco
 * @created Apr 25, 2015
 *
 */
public final class SingleQuotedStringToken extends Token {

	SingleQuotedStringToken(String rule, int start)
	{
		super(rule, start);
	}


	@Override
	public TokenType getType()
	{
		return TokenType.SINGLE_QUOTED_STRING;
	}


	@Override
	void extract() throws StringNotTerminatedException
	{
		string = new TokenBuilder(16);
		end = start;
		// The cursor (end) now points to the opening quote.
		LOOP: while (true) {
			// Move past opening quote
			char c = advance();
			switch (c) {
				case NIL:
					throw new StringNotTerminatedException(this);
				case APOSTROPHE:
					// Done. Move cursor past closing quote
					++end;
					break LOOP;
				case BACKSLASH:
					c = advance();
					if (c == NIL) {
						throw new StringNotTerminatedException(this);
					}
					string.add(BACKSLASH, c);
					break;
				default:
					string.add(c);

			}
		}
	}

}
