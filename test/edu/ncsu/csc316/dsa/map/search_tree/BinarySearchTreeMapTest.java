package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Tests for the BinarySearchTreeMap class
 * @author colinscanlon
 *
 */
public class BinarySearchTreeMapTest {

	/** The local tree for testing */
	BinarySearchTreeMap<Integer, String> tree;
	
	/**
	 * Resets the tree before each test 
	 */
	@Before
	public void setUp() {
		tree = new BinarySearchTreeMap<Integer, String>();
	}
	
	/**
	 * Tests the put method
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		tree.put(1, "one");
		assertEquals(1, tree.size());
		assertFalse(tree.isEmpty());
		assertEquals(1, (int)tree.root().getElement().getKey());
		
		tree.put(2, "two");
		tree.put(3, "three");
		tree.put(4, "four");
		tree.put(7, "seven");
		tree.put(5, "five");
		
		assertEquals(6, tree.size());
		assertEquals(2, tree.numChildren(tree.root()));
		assertEquals(1, (int)tree.root().getElement().getKey());
		assertEquals("two", tree.get(2));
		assertEquals("three", tree.get(3));
		assertEquals("four", tree.get(4));
		tree.put(3, "three2");
		tree.inOrder();
		tree.levelOrder();
		assertEquals("three2", tree.get(3));
		
		
	}
	
	/**
	 * Tests for the get method
	 */
	@Test
	public void testGet() {
		tree.put(1,  "one");
		assertEquals(1, tree.size());
		
		assertEquals("one", tree.get(1));
		tree.put(2, "two");
		tree.put(3, "three");
		tree.put(4, "four");
		tree.put(7, "seven");
		tree.put(5, "five");
		assertEquals("two", tree.get(2));
		assertEquals("three", tree.get(3));
		assertEquals("four", tree.get(4));
		tree.entrySet();
		tree.remove(2);
		assertNull(tree.get(2));
		
	}
	
	/**
	 * Tests for the get method
	 */
	@Test
	public void testRemove() {
		tree.put(1,  "one");
		assertEquals(1, tree.size());
		
		assertNull(tree.remove(10));
		assertEquals(1, tree.size());
		
		assertEquals("one", tree.remove(1));
		assertEquals(0, tree.size());
		tree.put(1,  "one");
		tree.put(2, "two");
		Iterable<Position<Entry<Integer, String>>> it = tree.preOrder();
		tree.toString();
		tree.children(it.iterator().next());
		assertEquals("one", tree.remove(1));
		assertEquals(1, tree.size());
		tree.put(1,  "one");
		assertEquals("two", tree.remove(2));
		assertEquals(1, tree.size());
		tree.put(0,  "zero");
		tree.put(2,  "two");
		assertEquals("one", tree.remove(1));
		tree.postOrder();
		assertEquals(2, (int)tree.root().getElement().getKey());
	}
}
