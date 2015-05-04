package org.domainobject.jetlag.core.parser.tokenizer;

import static org.junit.Assert.*;

import org.domainobject.jetlag.core.parser.Operator;
import org.junit.Test;

public class OperatorTokenTest {

	@Test
	public void testExtract()
	{
		String rule = "7+8";
		OperatorToken tok = new OperatorToken(rule, 1);
		try {
			tok.extract();
			assertNotNull(tok.getOperator());
			assertEquals(Operator.ADD, tok.getOperator());
			assertEquals("+", tok.toString());
		}
		catch (TokenExtractionException e) {
			fail("No TokenExtractionException expected");
		}
	}

}
