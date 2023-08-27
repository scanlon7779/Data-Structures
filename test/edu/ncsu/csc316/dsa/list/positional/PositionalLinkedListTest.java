package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests for PositionalLinkedList
 * @author colinscanlon
 *
 */
public class PositionalLinkedListTest {

	/** The list used for testing */
	private PositionalList<String> list;
	
	/**
	 * Initialized the list before each test
	 */
	@Before
	public void setUp() {
		list = new PositionalLinkedList<String>();
	}
	
	/**
	 * Tests the first method
	 */
	@Test
	public void testFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		assertNull(list.first());
		
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals(first, list.first());
		
		Position<String> f2 = list.addFirst("two");
		assertEquals(2, list.size());
		assertEquals(f2, list.first());
		
		Position<String> f3 = list.addFirst("three");
		assertEquals(3, list.size());
		assertEquals(f3, list.first());
	}
	
	/**
	 * Tests the last method
	 */
	@Test
	public void testLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		assertNull(list.first());
		
		Position<String> last = list.addLast("one");
		assertEquals(1, list.size());
		assertEquals(last, list.last());
		
		Position<String> l2 = list.addLast("two");
		assertEquals(2, list.size());
		assertEquals(l2, list.last());
		
		Position<String> l3 = list.addLast("three");
		assertEquals(3, list.size());
		assertEquals(l3, list.last());
		
	}
	
	/**
	 * Tests the addFirst method
	 */
	@Test
	public void testAddFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals(first, list.first());
		Position<String> f2 = list.addFirst("two");
		assertEquals(2, list.size());
		assertEquals(f2, list.first());
		Position<String> f3 = list.addFirst("three");
		assertEquals(3, list.size());
		assertEquals(f3, list.first());
		
	}
	
	/**
	 * Tests the add last method
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());
        assertEquals(first, list.last());
		Position<String> l2 = list.addLast("two");
		assertEquals(2, list.size());
		assertEquals(l2, list.last());
		Position<String> l3 = list.addLast("three");
		assertEquals(3, list.size());
		assertEquals(l3, list.last());
	}
	
	/**
	 * Tests the before method
	 */
	@Test
	public void testBefore() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());
		Position<String> l2 = list.addLast("two");
		Position<String> l3 = list.addLast("three");
		assertNull(list.before(first));
		assertEquals(first, list.before(l2));
		assertEquals(l2, list.before(l3));
		
	}
	
	/**
	 * Tests the after method
	 */
	@Test
	public void testAfter() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());
		Position<String> l2 = list.addLast("two");
		Position<String> l3 = list.addLast("three");
		assertNull(list.after(l3));
		assertEquals(l3, list.after(l2));
		assertEquals(l2, list.after(first));
	}
	
	/**
	 * Tests the addBefore method
	 */
	@Test
	public void testAddBefore() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		Position<String> second = list.addBefore(first, "two");
		Position<String> third = list.addBefore(second, "three");
		Position<String> fourth = list.addBefore(third, "four");
		
		assertEquals(second, list.before(first));
		assertEquals(third, list.before(second));
		assertEquals(fourth, list.before(third));

		
	}
	
	/**
	 * Tests the addAfter method
	 */
	@Test
	public void testAddAfter() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		Position<String> second = list.addAfter(first, "two");
		Position<String> third = list.addAfter(second, "three");
		Position<String> fourth = list.addAfter(third, "four");
		
		assertEquals(second, list.after(first));
		assertEquals(third, list.after(second));
		assertEquals(fourth, list.after(third));
	}
	
	/**
	 * Tests the set method
	 */
	@Test
	public void testSet() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		Position<String> second = list.addAfter(first, "two");
		
		String f = list.set(first, "new one");
		assertEquals("one", f);
		assertEquals("new one", list.first().getElement());
		
		String s = list.set(second, "new two");
		assertEquals("two", s);
		assertEquals("new two", list.after(first).getElement());
		
	}
	
	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		Position<String> second = list.addAfter(first, "two");
		Position<String> third = list.addAfter(second, "three");
		Position<String> fourth = list.addAfter(third, "four");
		
		assertEquals(4, list.size());
		String f = list.remove(first);
		assertEquals("one", f);
		assertEquals(3, list.size());
		assertEquals(second, list.first());
		
		String four = list.remove(fourth);
		assertEquals("four", four);
		assertEquals(2, list.size());
		assertEquals(third, list.last());
	}
	
	/**
	 * Tests the iterator method of the list
	 */
	@Test
	public void testIterator() {
		Position<String> first = list.addLast("one");
		Position<String> second = list.addAfter(first, "2");
		Position<String> third = list.addAfter(second, "4");
		Position<String> fourth = list.addAfter(third, "6");
		list.addAfter(fourth, "8");

		Iterator<String> it = list.iterator();
		
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		
		assertTrue(it.hasNext());
		assertEquals("2", it.next());
		
		assertTrue(it.hasNext());
		assertEquals("4", it.next());
		
		it.remove();
		assertTrue(it.hasNext());
		assertEquals("6", it.next());
		
		
	}
	
	/**
	 * Tests the position method of the list
	 */
	@Test
	public void testPositions() {
		assertEquals(0, list.size());
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addLast("two");
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());
		
		Iterator<Position<String>> it = list.positions().iterator();
		assertTrue(it.hasNext());
		assertEquals(first, it.next());
		assertEquals(second, it.next());
		assertEquals(third, it.next());
		
	}

}