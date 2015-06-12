package org.domainobject.jetlag.core.parser.tokenizer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author ayco
 * @created Apr 27, 2015
 *
 */
public class DoubleQuotedStringTest {

	/**
	 * Test method for
	 * {@link org.domainobject.jetlag.core.parser.tokenizer.DoubleQuotedStringToken#extract()}
	 * .
	 */
	@Test
	public final void testExtract()
	{
		String token = "The Double Quoted String";

		/*
		 * Rule starts with double quoted token.
		 */
		String rule = String.format("\"%s\"FOO", token);
		Cursor cursor = new Cursor(rule);
		DoubleQuotedStringToken stringToken = new DoubleQuotedStringToken(cursor);
		try {
			stringToken.extract();
			assertTrue(stringToken.string() != null);
			assertTrue(stringToken.string().equals(token));
		}
		catch (TokenExtractionException e) {
			fail("Not expected");
		}

		/*
		 * Rule starts with double quoted token.
		 */
		rule = String.format("\"%s\" FOO", token);
		stringToken = new DoubleQuotedStringToken(new Cursor(rule));
		try {
			stringToken.extract();
			assertTrue(stringToken.string() != null);
			assertTrue(stringToken.string().equals(token));
		}
		catch (TokenExtractionException e) {
			fail("Not expected");
		}

		rule = String.format("\"%s\"\" FOO", token);
		stringToken = new DoubleQuotedStringToken(new Cursor(rule));
		try {
			stringToken.extract();
			assertTrue(stringToken.string() != null);
			assertTrue(stringToken.string().equals(token));
		}
		catch (TokenExtractionException e) {
			fail("Not expected");
		}

		/*
		 * Rule ends with double quoted token.
		 */
		rule = String.format("FOO\"%s\"", token);
		cursor = new Cursor(rule);
		cursor.forward().forward().forward();
		stringToken = new DoubleQuotedStringToken(cursor);
		try {
			stringToken.extract();
			assertTrue(stringToken.string() != null);
			assertTrue(stringToken.string().equals(token));
		}
		catch (TokenExtractionException e) {
			fail("Not expected");
		}

		/*
		 * Empty token
		 */
		token = "";

		rule = String.format("FOO\"%s\"BAR", token);
		cursor = new Cursor(rule);
		cursor.forward().forward().forward();
		stringToken = new DoubleQuotedStringToken(cursor);
		try {
			stringToken.extract();
			assertTrue(stringToken.string() != null);
			assertTrue(stringToken.string().equals(token));
		}
		catch (TokenExtractionException e) {
			fail("Not expected");
		}
	}

}
