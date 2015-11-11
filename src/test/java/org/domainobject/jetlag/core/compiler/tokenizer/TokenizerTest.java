package org.domainobject.jetlag.core.compiler.tokenizer;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

import org.domainobject.jetlag.core.compiler.tokenizer.Cursor;
import org.domainobject.jetlag.core.compiler.tokenizer.IllegalCharacterException;
import org.domainobject.jetlag.core.compiler.tokenizer.TokenExtractionException;
import org.domainobject.jetlag.core.compiler.tokenizer.Tokenizer;
import org.domainobject.util.FileUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TokenizerTest {

	private static HashMap<String, String> rules = new HashMap<>();

	@BeforeClass
	public static void init() throws URISyntaxException
	{
		rules.clear();
		URL url = TokenizerTest.class.getResource("/rules");
		File dir = new File(url.toURI());
		for (File f : dir.listFiles()) {
			String fn = f.getName().substring(0, f.getName().length() - 4);
			rules.put(fn, FileUtil.getContents(f));
		}
	}

	@Test
	@SuppressWarnings("static-method")
	public void testNextToken()
	{

		String rule = rules.get("empty-rule");
		try {
			Tokenizer tokenizer = new Tokenizer(new Cursor(rule));
			assertTrue(tokenizer.nextToken() == null);
		}
		catch (IllegalCharacterException | TokenExtractionException e) {
			fail("Exception not expected: " + e);
		}

		rule = rules.get("rule-012");
		try {
			Tokenizer tokenizer = new Tokenizer(new Cursor(rule));
			assertTrue(tokenizer.nextToken().data().equals("if"));
			assertTrue(tokenizer.nextToken().data().equals("("));
			assertTrue(tokenizer.nextToken().data().equals("name"));
			assertTrue(tokenizer.nextToken().data().equals("="));
			assertTrue(tokenizer.nextToken().data().equals("John Smith"));
			assertTrue(tokenizer.nextToken().data().equals(","));
			assertTrue(tokenizer.nextToken().data().equals("Manager"));
			assertTrue(tokenizer.nextToken().data().equals(","));
			assertTrue(tokenizer.nextToken().data().equals("Employee"));
			assertTrue(tokenizer.nextToken().data().equals(")"));
			assertTrue(tokenizer.nextToken() == null);
		}
		catch (IllegalCharacterException | TokenExtractionException e) {
			fail("Exception not expected: " + e);
		}

	}

	@Test
	@SuppressWarnings("static-method")
	public void testPeek()
	{
		String rule12 = rules.get("rule-012");
		try {
			Tokenizer tokenizer = new Tokenizer(new Cursor(rule12));
			assertTrue(tokenizer.peek().data().equals("if"));
			tokenizer.nextToken();
			assertTrue(tokenizer.peek().data().equals("("));
			tokenizer.nextToken();
			assertTrue(tokenizer.peek().data().equals("name"));
			tokenizer.nextToken();
			assertTrue(tokenizer.peek().data().equals("="));
			tokenizer.nextToken();
			assertTrue(tokenizer.peek().data().equals("John Smith"));
			tokenizer.nextToken();
			assertTrue(tokenizer.peek().data().equals(","));
			tokenizer.nextToken();
			assertTrue(tokenizer.peek().data().equals("Manager"));
			tokenizer.nextToken();
			assertTrue(tokenizer.peek().data().equals(","));
			tokenizer.nextToken();
			assertTrue(tokenizer.peek().data().equals("Employee"));
			tokenizer.nextToken();
			assertTrue(tokenizer.peek().data().equals(")"));
			tokenizer.nextToken();
			assertTrue(tokenizer.peek() == null);
		}
		catch (IllegalCharacterException | TokenExtractionException e) {
			fail("Exception not expected: " + e);
		}
	}

	@Test
	@SuppressWarnings("static-method")
	public void testPeek__int()
	{
		String rule12 = rules.get("rule-012");
		try {
			Tokenizer tokenizer = new Tokenizer(new Cursor(rule12));
			assertTrue(tokenizer.peek(-1) == null);
			assertTrue(tokenizer.peek(0) == null);
			assertTrue(tokenizer.peek(1).data().equals("if"));
			assertTrue(tokenizer.peek(2).data().equals("("));
			assertTrue(tokenizer.peek(3).data().equals("name"));
			assertTrue(tokenizer.peek(4).data().equals("="));
			assertTrue(tokenizer.peek(5).data().equals("John Smith"));
			assertTrue(tokenizer.peek(6).data().equals(","));
			assertTrue(tokenizer.peek(7).data().equals("Manager"));
			assertTrue(tokenizer.peek(8).data().equals(","));
			assertTrue(tokenizer.peek(9).data().equals("Employee"));
			assertTrue(tokenizer.peek(10).data().equals(")"));
			assertTrue(tokenizer.peek(11) == null);
		}
		catch (IllegalCharacterException | TokenExtractionException e) {
			fail("Exception not expected: " + e);
		}
	}

}
