package org.domainobject.jetlag.core.compiler.tokenizer;

import static org.junit.Assert.*;
import static org.domainobject.jetlag.core.compiler.tokenizer.Cursor.*;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

import org.domainobject.jetlag.core.compiler.tokenizer.Cursor;
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
		assertEquals('i', cursor.forward().at());
		assertEquals('f', cursor.forward().at());
		assertEquals(' ', cursor.forward().at());
		assertEquals('(', cursor.forward().at());
		assertEquals(LF, cursor.forward().at());
		assertEquals(TAB, cursor.forward().at());
		
		cursor = new Cursor(rules.get("rule-005"));
		assertEquals('1', cursor.at());
		assertEquals('1', cursor.forward().at());
		assertEquals('3', cursor.forward().at());
		assertEquals('4', cursor.forward().at());
		assertEquals(NIL, cursor.forward().at());	
		assertEquals(NIL, cursor.forward().at());	
		assertEquals(NIL, cursor.forward().at());	
		
		cursor = new Cursor(rules.get("empty-rule"));
		assertEquals(NIL, cursor.forward().at());	
		assertEquals(NIL, cursor.forward().at());	
		assertEquals(NIL, cursor.forward().at());	
	}


	@Test
	public void testAt__char()
	{
		Cursor cursor = new Cursor(rules.get("rule-012"));
		assertTrue(cursor.at('i'));
		assertTrue(cursor.forward().at('i'));
		assertTrue(cursor.forward().at('f'));
		assertTrue(cursor.forward().at(' '));
		assertTrue(cursor.forward().at('('));
		assertTrue(cursor.forward().at(LF));
		assertTrue(cursor.forward().at(TAB));
		
		cursor = new Cursor(rules.get("empty-rule"));
		assertTrue(cursor.forward().at(NIL));
	}


	@Test
	public void testPrev()
	{
		Cursor cursor = new Cursor(rules.get("rule-012"));
		assertEquals(NIL, cursor.prev());
		assertEquals(NIL, cursor.forward().prev());
		assertEquals('i', cursor.forward().prev());
		assertEquals('f', cursor.forward().prev());
		assertEquals(' ', cursor.forward().prev());
		assertEquals('(', cursor.forward().prev());
		assertEquals(LF, cursor.forward().prev());
		assertEquals(TAB, cursor.forward().prev());
		assertEquals(TAB, cursor.prev());
		
		cursor = new Cursor(rules.get("empty-rule"));
		assertEquals(NIL, cursor.prev());
		assertEquals(NIL, cursor.prev());
	}


	@Test
	public void testPeek()
	{
		Cursor cursor = new Cursor(rules.get("rule-012"));
		assertEquals('i', cursor.peek());
		assertEquals('f', cursor.forward().peek());
		assertEquals(' ', cursor.forward().peek());
		
		cursor = new Cursor(rules.get("empty-rule"));
		assertEquals(NIL, cursor.peek());
		assertEquals(NIL, cursor.peek());
		assertEquals(NIL, cursor.forward().peek());
		assertEquals(NIL, cursor.forward().peek());
	}


	@Test
	public void testForward()
	{
		Cursor cursor = new Cursor(rules.get("rule-005"));
		assertEquals('1', cursor.at());
		assertEquals(cursor, cursor.forward());
		assertEquals('1', cursor.at());
		assertEquals('3', cursor.forward().at());
		assertEquals('4', cursor.forward().at());
		assertEquals(NIL, cursor.forward().at());
		assertEquals(NIL, cursor.forward().at());
		assertEquals(NIL, cursor.forward().at());
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
