package org.domainobject.jetlag.core.parser.tokenizer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.domainobject.jetlag.core.parser.tokenizer.DoubleQuotedStringToken;
import org.domainobject.jetlag.core.parser.tokenizer.StringNotTerminatedException;
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
		 * Rule starts with double quoted string.
		 */
		String rule = String.format("\"%s\"FOO", token);
		DoubleQuotedStringToken dqs = new DoubleQuotedStringToken(rule, 0);
		try {
			dqs.extract();
			assertTrue(dqs.get() != null);
			assertTrue(dqs.get().equals(token));
		}
		catch (StringNotTerminatedException e) {
			fail("StringNotTerminatedException not expected");
		}

		/*
		 * Rule starts with double quoted string.
		 */
		rule = String.format("\"%s\" FOO", token);
		dqs = new DoubleQuotedStringToken(rule, 0);
		try {
			dqs.extract();
			assertTrue(dqs.get() != null);
			assertTrue(dqs.get().equals(token));
		}
		catch (StringNotTerminatedException e) {
			fail("StringNotTerminatedException not expected");
		}

		rule = String.format("\"%s\"\" FOO", token);
		dqs = new DoubleQuotedStringToken(rule, 0);
		try {
			dqs.extract();
			assertTrue(dqs.get() != null);
			assertTrue(dqs.get().equals(token));
		}
		catch (StringNotTerminatedException e) {
			fail("StringNotTerminatedException not expected");
		}

		/*
		 * Rule ends with double quoted string.
		 */
		rule = String.format("FOO\"%s\"", token);
		dqs = new DoubleQuotedStringToken(rule, 3);
		try {
			dqs.extract();
			assertTrue(dqs.get() != null);
			assertTrue(dqs.get().equals(token));
		}
		catch (StringNotTerminatedException e) {
			fail("StringNotTerminatedException not expected");
		}

		/*
		 * Empty string
		 */
		token = "";

		rule = String.format("FOO\"%s\"BAR", token);
		dqs = new DoubleQuotedStringToken(rule, 3);
		try {
			dqs.extract();
			assertTrue(dqs.get() != null);
			assertTrue(dqs.get().equals(token));
		}
		catch (StringNotTerminatedException e) {
			fail("StringNotTerminatedException not expected");
		}
	}

}