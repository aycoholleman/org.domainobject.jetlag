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
		Tokenizer t = new Tokenizer(rule12);
		try {
			assertTrue(t.nextToken().string().equals("if"));
			assertTrue(t.nextToken().string().equals("("));
			assertTrue(t.nextToken().string().equals("name"));
			assertTrue(t.nextToken().string().equals("="));
			assertTrue(t.nextToken().string().equals("John Smith"));
			assertTrue(t.nextToken().string().equals(","));
			assertTrue(t.nextToken().string().equals("Manager"));
			assertTrue(t.nextToken().string().equals(","));
			assertTrue(t.nextToken().string().equals("Employee"));
			assertTrue(t.nextToken().string().equals(")"));
			assertTrue(t.nextToken() == null);
		}
		catch (IllegalCharacterException | TokenExtractionException e) {
			fail("Exception not expected: " + e);
		}
	}


	@Test
	public void testPeek()
	{

	}


	@Test
	public void testPeekInt()
	{

	}

}
