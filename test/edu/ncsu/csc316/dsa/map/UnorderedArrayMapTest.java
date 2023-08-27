package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the UnorderedArrayMap class
 * @author colinscanlon
 *
 */
public class UnorderedArrayMapTest {

	/** The map used for testing */
	private Map<Integer, String> map;
	
	/**
	 * Resets the map for the next test
	 */
	@Before
	public void setUp() {
		map = new UnorderedArrayMap<Integer, String>();
	}
	
	/**
	 * Tests the put method
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("UnorderedArrayMap[3]", map.toString());
		assertEquals(1, map.size());
		
		assertEquals(map.put(3, "UpdatedString"), "string3");
		assertEquals(1, map.size());
		
		assertNull(map.put(2, "string2"));
		assertNull(map.put(1, "string1"));
		assertEquals(3, map.size());
		assertEquals(map.put(2, "UpdatedString2"), "string2");
		
	}
	
	/**
	 * Tests the get method
	 */
	@Test
	public void testGet() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals("string1", map.get(1));
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals("string2", map.get(2));
		assertEquals("UnorderedArrayMap[1, 2, 4, 5, 3]", map.toString());
		
		assertEquals("string3", map.get(3));
		assertEquals("UnorderedArrayMap[1, 2, 4, 3, 5]", map.toString());
		
		assertEquals("string3", map.get(3));
		assertEquals("UnorderedArrayMap[1, 2, 3, 4, 5]", map.toString());
		
		
	}
	
	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());
		
		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals(map.remove(1), "string1");
		assertEquals(4, map.size());
		assertEquals("UnorderedArrayMap[4, 2, 5, 3]", map.toString());
		
		assertEquals(map.remove(5), "string5");
		assertEquals(3, map.size());
		assertEquals("UnorderedArrayMap[4, 2, 3]", map.toString());
		
		assertEquals(map.remove(3), "string3");
		assertEquals(2, map.size());
		assertEquals("UnorderedArrayMap[4, 2]", map.toString());
		
		
	}
	
	/**
	 * Tests the iterator of the map
	 */
	@Test
	public void testIterator() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<Integer> it = map.iterator();
		assertTrue(it.next().compareTo(1) == 0);
		assertTrue(it.hasNext());
		assertTrue(it.next().compareTo(4) == 0);
		assertTrue(it.hasNext());
		assertTrue(it.next().compareTo(2) == 0);
		assertTrue(it.hasNext());
		assertTrue(it.next().compareTo(5) == 0);
		assertTrue(it.hasNext());
		assertTrue(it.next().compareTo(3) == 0);
		assertFalse(it.hasNext());
	}
	
	/**
	 * Tests the entry set
	 */
	@Test
	public void testEntrySet() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry = it.next();
		assertEquals(1, (int)(entry.getKey()));
		assertEquals("string1", (String)(entry.getValue()));
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(4, (int)(entry.getKey()));
		assertEquals("string4", (String)(entry.getValue()));
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(2, (int)(entry.getKey()));
		assertEquals("string2", (String)(entry.getValue()));
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(5, (int)(entry.getKey()));
		assertEquals("string5", (String)(entry.getValue()));
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(3, (int)(entry.getKey()));
		assertEquals("string3", (String)(entry.getValue()));
		
	}
	
	/**
	 * Tests the value iterator
	 */
	@Test
	public void testValues() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<String> it = map.values().iterator();
		assertTrue(it.next().equals("string1"));
		assertTrue(it.hasNext());
		assertTrue(it.next().equals("string4"));
		assertTrue(it.hasNext());
		assertTrue(it.next().equals("string2"));
		assertTrue(it.hasNext());
		assertTrue(it.next().equals("string5"));
		assertTrue(it.hasNext());
		assertTrue(it.next().equals("string3"));
		assertFalse(it.hasNext());
	}
}