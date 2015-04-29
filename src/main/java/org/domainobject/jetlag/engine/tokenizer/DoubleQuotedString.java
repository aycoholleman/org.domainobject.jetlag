package org.domainobject.jetlag.engine.tokenizer;

import static org.domainobject.jetlag.engine.tokenizer.TokenBuilder.BACKSLASH;
import static org.domainobject.jetlag.engine.tokenizer.TokenBuilder.DOUBLE_QUOTE;
import static org.domainobject.jetlag.engine.tokenizer.TokenBuilder.NIL;

/**
 * {@link Token} subclass for extracting double quoted strings.
 * 
 * @author ayco
 * @created Apr 25, 2015
 *
 */
public final class DoubleQuotedString extends Token {

	public DoubleQuotedString(String rule, int start)
	{
		super(rule, start);
	}


	@Override
	public TokenType getType()
	{
		return TokenType.DOUBLE_QUOTED_STRING;
	}


	@Override
	public void extract() throws StringNotTerminatedException
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
				case DOUBLE_QUOTE:
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
