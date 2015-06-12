package org.domainobject.jetlag.core.parser.tokenizer;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

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
	public void testTokenizer()
	{

	}


	@Test
	public void testHasMoreTokens()
	{

	}


	@Test
	public void testCurrentToken()
	{

	}


	@Test
	public void testNextToken()
	{
		String rule12 = rules.get("rule-012");
		Tokenizer tokenizer = new Tokenizer(new Cursor(rule12));
		try {
			assertTrue(tokenizer.nextToken().string().equals("if"));
			assertTrue(tokenizer.nextToken().string().equals("("));
			assertTrue(tokenizer.nextToken().string().equals("name"));
			assertTrue(tokenizer.nextToken().string().equals("="));
			assertTrue(tokenizer.nextToken().string().equals("John Smith"));
			assertTrue(tokenizer.nextToken().string().equals(","));
			assertTrue(tokenizer.nextToken().string().equals("Manager"));
			assertTrue(tokenizer.nextToken().string().equals(","));
			assertTrue(tokenizer.nextToken().string().equals("Employee"));
			assertTrue(tokenizer.nextToken().string().equals(")"));
			assertTrue(tokenizer.nextToken() == null);
		}
		catch (IllegalCharacterException | TokenExtractionException e) {
			fail("Exception not expected: " + e);
		}
	}


	@Test
	public void testPeek()
	{
		String rule12 = rules.get("rule-012");
		Tokenizer t = new Tokenizer(new Cursor(rule12));
		try {
			assertTrue(t.peek().string().equals("if"));
			t.nextToken();
			assertTrue(t.peek().string().equals("("));
			t.nextToken();
			assertTrue(t.peek().string().equals("name"));
			t.nextToken();
			assertTrue(t.peek().string().equals("="));
			t.nextToken();
			assertTrue(t.peek().string().equals("John Smith"));
			t.nextToken();
			assertTrue(t.peek().string().equals(","));
			t.nextToken();
			assertTrue(t.peek().string().equals("Manager"));
			t.nextToken();
			assertTrue(t.peek().string().equals(","));
			t.nextToken();
			assertTrue(t.peek().string().equals("Employee"));
			t.nextToken();
			assertTrue(t.peek().string().equals(")"));
			t.nextToken();
			assertTrue(t.peek() == null);
		}
		catch (IllegalCharacterException | TokenExtractionException e) {
			fail("Exception not expected: " + e);
		}
	}


	@Test
	public void testPeek__int()
	{
		String rule12 = rules.get("rule-012");
		Tokenizer t = new Tokenizer(new Cursor(rule12));
		try {
			assertTrue(t.peek(1).string().equals("if"));
			assertTrue(t.peek(2).string().equals("("));
			assertTrue(t.peek(3).string().equals("name"));
			assertTrue(t.peek(4).string().equals("="));
			assertTrue(t.peek(5).string().equals("John Smith"));
			assertTrue(t.peek(6).string().equals(","));
			assertTrue(t.peek(7).string().equals("Manager"));
			assertTrue(t.peek(8).string().equals(","));
			assertTrue(t.peek(9).string().equals("Employee"));
			assertTrue(t.peek(10).string().equals(")"));
			assertTrue(t.peek(11) == null);
		}
		catch (IllegalCharacterException | TokenExtractionException e) {
			fail("Exception not expected: " + e);
		}
	}

}
