package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the UnorderedLinkedMap class
 * @author colinscanlon
 *
 */
public class UnorderedLinkedMapTest {

	/** The map used for testing */
	private Map<Integer, String> map;
	
	/**
	 * Resets the map before each test
	 */
	@Before
	public void setUp() {
		map = new UnorderedLinkedMap<Integer, String>();
	}
	
	/**
	 * Tests the put method
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("UnorderedLinkedMap[3]", map.toString());
		assertEquals(1, map.size());
		assertNull(map.put(5, "string5"));
		assertEquals("UnorderedLinkedMap[5, 3]", map.toString());
		assertEquals(2, map.size());
		
		assertEquals(map.put(3, "UpdatedString"), "string3");
		assertEquals(2, map.size());
		assertEquals("UnorderedLinkedMap[3, 5]", map.toString());
		
		assertNull(map.put(2, "string2"));
		assertNull(map.put(1, "string1"));
		assertEquals(4, map.size());
		assertEquals("UnorderedLinkedMap[1, 2, 3, 5]", map.toString());
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
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals("string1", map.get(1));
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals("string2", map.get(2));
		assertEquals("UnorderedLinkedMap[2, 1, 4, 5, 3]", map.toString());
		
		assertEquals("string3", map.get(3));
		assertEquals("UnorderedLinkedMap[3, 2, 1, 4, 5]", map.toString());
		
		assertEquals("string3", map.get(3));
		assertEquals("UnorderedLinkedMap[3, 2, 1, 4, 5]", map.toString());
		
		assertNull(map.get(6));
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
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals(map.remove(1), "string1");
		assertEquals(4, map.size());
		assertEquals("UnorderedLinkedMap[4, 2, 5, 3]", map.toString());
		
		assertEquals(map.remove(5), "string5");
		assertEquals(3, map.size());
		assertEquals("UnorderedLinkedMap[4, 2, 3]", map.toString());
		
		assertEquals(map.remove(3), "string3");
		assertEquals(2, map.size());
		assertEquals("UnorderedLinkedMap[4, 2]", map.toString());
	}
	
	/**
	 * Tests the map iterator
	 */
	@Test
	public void testIterator() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<Integer> it = map.iterator();
		assertTrue(it.hasNext());
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
	 * Tests the entries
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
		assertTrue(it.hasNext());
		
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