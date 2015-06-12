package org.domainobject.jetlag.core.parser.tokenizer;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

import org.domainobject.util.FileUtil;
import org.junit.BeforeClass;
import org.junit.Test;

public class CursorTest {


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
	public void testCursor()
	{
		Cursor cursor = new Cursor(rules.get("rule-012"));
		assertEquals('i', cursor.at());
	}


	@Test
	public void testAt()
	{
		Cursor cursor = new Cursor(rules.get("rule-012"));
		assertEquals('i', cursor.at());
		assertEquals('f', cursor.forward().at());
		assertEquals(' ', cursor.forward().at());
		assertEquals('(', cursor.forward().at());
		assertEquals(Cursor.LF, cursor.forward().at());
		assertEquals(Cursor.TAB, cursor.forward().at());
	}


	@Test
	public void testAt__char()
	{
	}


	@Test
	public void testPrev()
	{
	}


	@Test
	public void testPeek()
	{
	}


	@Test
	public void testForward()
	{
	}


	@Test
	public void testPosition()
	{
	}


	@Test
	public void testLine()
	{
	}


	@Test
	public void testColumn()
	{
	}


	@Test
	public void testCopy()
	{
	}

}
