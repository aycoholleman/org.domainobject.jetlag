package org.domainobject.jetlag.core.compiler.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.domainobject.jetlag.core.compiler.tokenizer.Cursor;
import org.domainobject.jetlag.core.compiler.tokenizer.OperatorToken;
import org.domainobject.jetlag.core.compiler.tokenizer.TokenExtractionException;
import org.junit.Test;

public class OperatorTokenTest {

	@Test
	public void testExtract()
	{
		String rule = "7+8";
		Cursor cursor = new Cursor(rule);
		cursor.forward();
		OperatorToken tok = new OperatorToken(cursor);
		try {
			tok.extract();
			assertEquals("+", tok.toString());
		}
		catch (TokenExtractionException e) {
			fail("No TokenExtractionException expected");
		}
	}

}
