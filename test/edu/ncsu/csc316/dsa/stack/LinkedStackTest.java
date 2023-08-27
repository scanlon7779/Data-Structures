package edu.ncsu.csc316.dsa.stack;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the LinkedStack class
 * @author colinscanlon
 *
 */
public class LinkedStackTest {

	/** The local stack to be used in testing */
	private Stack<String> stack;
	
	/**
	 * Resetting the stack for each test
	 */
	@Before
	public void setUp() {
		stack = new LinkedStack<String>();
	}
	
	/**
	 * Tests for the push method
	 */
	@Test
	public void testPush() {
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
		
		stack.push("one");
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());
		
		stack.push("two");
		assertEquals(2, stack.size());
		
		stack.push("three");
		assertEquals(3, stack.size());
		
		stack.push("four");
		assertEquals(4, stack.size());
	}

	/**
	 * Test for the pop method
	 */
	@Test
	public void testPop() {
		assertEquals(0, stack.size());
		stack.push("one");
		stack.push("two");
		stack.push("three");
		stack.push("four");
		stack.push("five");
		stack.push("six");
		assertEquals(6, stack.size());
		
		assertEquals("six",  stack.pop());
		assertEquals(5, stack.size());
		
		stack.push("six");
        assertEquals(6, stack.size());
		assertEquals("six",  stack.pop());
		assertEquals(5, stack.size());
		
		assertEquals("five",  stack.pop());
		assertEquals(4, stack.size());
		
		assertEquals("four",  stack.pop());
		assertEquals(3, stack.size());
		
		assertEquals("three",  stack.pop());
		assertEquals(2, stack.size());
		
		assertEquals("two",  stack.pop());
		assertEquals(1, stack.size());
		
		assertEquals("one",  stack.pop());
		assertEquals(0, stack.size());
		
		try {
			stack.pop();
			fail("EmptyStackException should have been thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
	}

	/**
	 * Tests for the top method
	 */
	@Test
	public void testTop() {	
		assertEquals(0, stack.size());
		try {
			stack.top();
			fail("EmptyStackException should have been thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
		
		stack.push("one");
		assertEquals(1, stack.size());
		assertEquals("one", stack.top());
		
		stack.push("two");
		assertEquals(2, stack.size());
		assertEquals("two", stack.top());

		
		stack.push("three");
		assertEquals(3, stack.size());
		assertEquals("three", stack.top());

		
		stack.push("four");
		assertEquals(4, stack.size());
		assertEquals("four", stack.top());

		
		
	}

}