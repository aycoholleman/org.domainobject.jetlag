package org.domainobject.jetlag.engine.tokenizer;

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
	 * {@link org.domainobject.jetlag.engine.tokenizer.DoubleQuotedString#extract()}
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
		DoubleQuotedString dqs = new DoubleQuotedString(rule, 0);
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
		dqs = new DoubleQuotedString(rule, 0);
		try {
			dqs.extract();
			assertTrue(dqs.get() != null);
			assertTrue(dqs.get().equals(token));
		}
		catch (StringNotTerminatedException e) {
			fail("StringNotTerminatedException not expected");
		}

		rule = String.format("\"%s\"\" FOO", token);
		dqs = new DoubleQuotedString(rule, 0);
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
		dqs = new DoubleQuotedString(rule, 3);
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
		dqs = new DoubleQuotedString(rule, 3);
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
