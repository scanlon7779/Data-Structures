package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the SinglyLinkedList class
 * @author colinscanlon
 *
 */
public class SinglyLinkedListTest {

	/** the instance of the list for testing */
	private List<String> list;

	/**
	 * Resets the list before each test
	 */
	@Before
	public void setUp() {
		list = new SinglyLinkedList<String>();
	}

	/**
	 * Tests the add at an index method for the list
	 */
	@Test
	public void testAddIndex() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());
		
		// Use the statements above to help guide your test cases
		// for data structures: Start with an empty data structure, then
		// add an element and check the accessor method return values.
		// Then add another element and check again. Continue to keep checking
		// for special cases. For example, for an array-based list, you might
		// continue adding until you trigger a resize operation to make sure
		// the resize operation worked as expected.
		
		try{
			list.add(15,  "fifteen");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
	}

	/**
	 * Tests the addLast method
	 */
	@Test
	public void testAddLast() {
		list.addLast("One");
		list.addLast("Two");
		list.addLast("Three");
		list.addLast("Four");
		
		assertEquals("One", list.get(0));
		assertEquals("Two", list.get(1));
		assertEquals("Three", list.get(2));
		assertEquals("Four", list.get(3));
	}

	/**
	 * Tests the last method
	 */
	@Test
	public void testLast() {
		list.addLast("One");
		assertEquals("One", list.last());
		
		list.addLast("Two");
		assertEquals("Two", list.last());
		
		list.addLast("Three");
		assertEquals("Three", list.last());
		
		list.addLast("Four");
		assertEquals("Four", list.last());
	}

	/**
	 * Tests the addFirst method
	 */
	@Test
	public void testAddFirst() {
		list.addFirst("One");
		list.addFirst("Two");
		list.addFirst("Three");
		list.addFirst("Four");
		
		assertEquals("Four", list.get(0));
		assertEquals("Three", list.get(1));
		assertEquals("Two", list.get(2));
		assertEquals("One", list.get(3));
	}
	
	/**
	 * Tests the first method
	 */
	@Test
	public void testFirst() {
		list.addFirst("One");
		assertEquals("One", list.get(0));
		
		list.addFirst("Two");
		assertEquals("Two", list.get(0));

		list.addFirst("Three");
		assertEquals("Three", list.get(0));

		list.addFirst("Four");
		assertEquals("Four", list.get(0));

	}


	/**
	 * Tests the iterator inner class
	 */
	@Test
	public void testIterator() {
		// Start with an empty list
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		// Create an iterator for the empty list
		Iterator<String> it = list.iterator();
		
		// Try different operations to make sure they work
		// as expected for an empty list (at this point)
		try{
			it.remove();
		} catch(Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		assertFalse(it.hasNext());

		// Now add an element
		list.addLast("one");
		
		// Use accessor methods to check that the list is correct
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals("one", list.get(0));
		
		// Create an iterator for the list that has 1 element
		it = list.iterator();
		
		// Try different iterator operations to make sure they work
		// as expected for a list that contains 1 element (at this point)
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertFalse(it.hasNext());
		try{
			it.next();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
		
		list.remove(0);
		list.addLast("2");
		list.addLast("4");
		list.addLast("6");
		list.addLast("8");
		
		it = list.iterator();
		assertTrue(it.hasNext());
		assertEquals("2", it.next());
		
		assertTrue(it.hasNext());
		assertEquals("4", it.next());
		
		it.remove();
		assertTrue(it.hasNext());
		assertEquals("6", it.next());
		
		assertTrue(it.hasNext());
		assertEquals("8", it.next());
		

	}

	/**
	 * Tests the removeIndex
	 */
	@Test
	public void testRemoveIndex() {
		list.addLast("One");
		list.addLast("Two");
		list.addLast("Three");
		list.addLast("Four");
		
		list.remove(1);
		assertEquals("One", list.get(0));
		assertEquals("Three", list.get(1));
		assertEquals("Four", list.get(2));
		
		list.remove(0);
		assertEquals("Three", list.get(0));
		assertEquals("Four", list.get(1));
		
		list.remove(1);
		assertEquals("Three", list.get(0));
		
	}

	/**
	 * Tests the removeFirst method
	 */
	@Test
	public void testRemoveFirst() {
		list.addLast("One");
		list.addLast("Two");
		list.addLast("Three");
		list.addLast("Four");
		
		list.removeFirst();
		assertEquals("Two", list.get(0));
		assertEquals("Three", list.get(1));
		assertEquals("Four", list.get(2));
		
		list.removeFirst();
		assertEquals("Three", list.get(0));
		assertEquals("Four", list.get(1));
	}

	/**
	 * Tests the removeLast method
	 */
	@Test
	public void testRemoveLast() {
		list.addLast("One");
		list.addLast("Two");
		list.addLast("Three");
		list.addLast("Four");
		
		list.removeLast();
		assertEquals("One", list.get(0));
		assertEquals("Two", list.get(1));
		assertEquals("Three", list.get(2));
		
		list.removeLast();
		assertEquals("One", list.get(0));
		assertEquals("Two", list.get(1));
		
		list.removeLast();
		assertEquals("One", list.get(0));
		list.removeLast();
		assertTrue(list.isEmpty());
		
		
	}

	/**
	 * Tests the set method
	 */
	@Test
	public void testSet() {
		list.addLast("One");
		list.addLast("Two");
		list.addLast("Three");
		list.addLast("Four");
		
		list.set(1, "New");
		assertEquals("One", list.get(0));
		assertEquals("New", list.get(1));
		assertEquals("Three", list.get(2));
		assertEquals("Four", list.get(3));
		
		list.set(3, "New2");
		assertEquals("One", list.get(0));
		assertEquals("New", list.get(1));
		assertEquals("Three", list.get(2));
		assertEquals("New2", list.get(3));
		
		
	}
}
