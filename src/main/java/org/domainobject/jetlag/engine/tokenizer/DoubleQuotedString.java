package org.domainobject.jetlag.engine.tokenizer;

import static org.domainobject.jetlag.engine.tokenizer.TokenBuilder.NIL;
import static org.domainobject.jetlag.engine.tokenizer.TokenBuilder.BACKSLASH;
import static org.domainobject.jetlag.engine.tokenizer.TokenBuilder.CR;
import static org.domainobject.jetlag.engine.tokenizer.TokenBuilder.DOUBLE_QUOTE;
import static org.domainobject.jetlag.engine.tokenizer.TokenBuilder.LF;
import static org.domainobject.jetlag.engine.tokenizer.TokenBuilder.TAB;

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
		while(true) {
			// Move past opening quote
			char c = advance();
			if (c == NIL) {
				throw new StringNotTerminatedException(this);
			}
			else if (c == DOUBLE_QUOTE) {
				// Done. Move cursor past closing quote
				++end;
				break;
			}
			else if (c == BACKSLASH) {
				c = advance();
				switch (c) {
					case NIL:
						throw new StringNotTerminatedException(this);
					case DOUBLE_QUOTE:
						string.add(DOUBLE_QUOTE);
						break;
					case BACKSLASH:
						string.add(BACKSLASH);
						break;
					case 'n':
						string.add(LF);
						break;
					case 'r':
						string.add(CR);
						break;
					case 't':
						string.add(TAB);
						break;
					default:
						string.add(BACKSLASH, c);
				}
			}
			else {
				string.add(c);
			}
		}
	}

}
