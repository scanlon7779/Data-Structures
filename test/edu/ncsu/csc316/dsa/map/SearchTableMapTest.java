package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests for the SearchTableMap class
 * @author colinscanlon
 *
 */
public class SearchTableMapTest {

	/** the map of integers and strings to test */
	private Map<Integer, String> map;
	
	/** The map of students and strings to test */
	private Map<Student, Integer> studentMap;
	
	/**
	 * Resets the maps before each test
	 */
	@Before
	public void setUp() {
		map = new SearchTableMap<Integer, String>();
		studentMap = new SearchTableMap<Student, Integer>();
	}
	
	/**
	 * Tests for the put method
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("SearchTableMap[3]", map.toString());
		assertEquals(1, map.size());
		
		assertEquals(map.put(3, "UpdatedString"), "string3");
		assertEquals(1, map.size());
		
		assertNull(map.put(2, "string2"));
		assertNull(map.put(1, "string1"));
		assertEquals(3, map.size());
		assertEquals(map.put(2, "UpdatedString2"), "string2");
		assertEquals("SearchTableMap[1, 2, 3]", map.toString());
		
	}
	
	/**
	 * Tests for the get method
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
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertEquals("string1", map.get(1));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertEquals("string1", map.get(1));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertEquals("string2", map.get(2));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertEquals("string3", map.get(3));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertEquals("string3", map.get(3));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
	}
	
	/**
	 * Tests for the remove method
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
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		
		assertEquals(map.remove(1), "string1");
		assertEquals(4, map.size());
		assertEquals("SearchTableMap[2, 3, 4, 5]", map.toString());
		
		assertEquals(map.remove(5), "string5");
		assertEquals(3, map.size());
		assertEquals("SearchTableMap[2, 3, 4]", map.toString());
		
		assertEquals(map.remove(3), "string3");
		assertEquals(2, map.size());
		assertEquals("SearchTableMap[2, 4]", map.toString());
	}
	
	/**
	 * Tests for a map of comparable student objects
	 */
	@Test
	public void testStudentMap() {
		Student s1 = new Student("J", "K", 1, 0, 0, "jk");
		Student s2 = new Student("J", "S", 2, 0, 0, "js");
		Student s3 = new Student("S", "H", 3, 0, 0, "sh");
		Student s4 = new Student("J", "J", 4, 0, 0, "jj");
		Student s5 = new Student("L", "B", 5, 0, 0, "lb");
		
		studentMap.put(s1,  100);
		assertEquals(1, studentMap.size());
		assertTrue(studentMap.get(s1).equals(100));
		studentMap.put(s2,  200);
		assertTrue(studentMap.get(s2).equals(200));
		assertEquals("SearchTableMap[J,K,1,0,0.0,jk, J,S,2,0,0.0,js]", studentMap.toString());
		studentMap.put(s3,  3);
		assertTrue(studentMap.get(s3).equals(3));
		assertEquals("SearchTableMap[S,H,3,0,0.0,sh, J,K,1,0,0.0,jk, J,S,2,0,0.0,js]", studentMap.toString());
		assertTrue(studentMap.remove(s1).equals(100));
		assertTrue(studentMap.remove(s2).equals(200));
		
		studentMap.put(s4,  4);
		assertTrue(studentMap.get(s4).equals(4));
		studentMap.put(s5,  5);
		assertTrue(studentMap.get(s5).equals(5));
		assertEquals("SearchTableMap[L,B,5,0,0.0,lb, S,H,3,0,0.0,sh, J,J,4,0,0.0,jj]", studentMap.toString());
		
		
		
		
	}
	
	/**
	 * test for the integer method
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
		assertTrue(it.next().compareTo(2) == 0);
		assertTrue(it.hasNext());
		assertTrue(it.next().compareTo(3) == 0);
		assertTrue(it.hasNext());
		assertTrue(it.next().compareTo(4) == 0);
		assertTrue(it.hasNext());
		assertTrue(it.next().compareTo(5) == 0);
		assertFalse(it.hasNext());
	}
	
	/**
	 * Tests for entries
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
		assertEquals(2, (int)(entry.getKey()));
		assertEquals("string2", (String)(entry.getValue()));
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(3, (int)(entry.getKey()));
		assertEquals("string3", (String)(entry.getValue()));
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(4, (int)(entry.getKey()));
		assertEquals("string4", (String)(entry.getValue()));
		
		assertTrue(it.hasNext());
		entry = it.next();
		assertEquals(5, (int)(entry.getKey()));
		assertEquals("string5", (String)(entry.getValue()));
	}
	
	/**
	 * Tets for values
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
		assertTrue(it.next().equals("string2"));
		assertTrue(it.hasNext());
		assertTrue(it.next().equals("string3"));
		assertTrue(it.hasNext());
		assertTrue(it.next().equals("string4"));
		assertTrue(it.hasNext());
		assertTrue(it.next().equals("string5"));
		assertFalse(it.hasNext());
	}
}
