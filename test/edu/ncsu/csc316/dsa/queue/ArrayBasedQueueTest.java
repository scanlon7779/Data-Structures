package edu.ncsu.csc316.dsa.queue;


import static org.junit.Assert.*;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the ArrayBasedQueue class
 * @author colinscanlon
 *
 */
public class ArrayBasedQueueTest {

	/** The local queue for testing */
    private Queue<String> queue;
    
    /**
     * Resets the queue before each test
     */
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }
    
    /**
     * Tests for the enqueue method
     */
    @Test
    public void testEnqueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("two");
        assertEquals(2, queue.size());
        
        queue.enqueue("three");
        assertEquals(3, queue.size());
        
        queue.enqueue("four");
        assertEquals(4, queue.size());
        
        queue.enqueue("five");
        assertEquals(5, queue.size());
        
        queue.enqueue("six");
        assertEquals(6, queue.size());
        
        queue.enqueue("seven");
        assertEquals(7, queue.size());
        
        queue.enqueue("eight");
        assertEquals(8, queue.size());
        
        queue.enqueue("nine");
        assertEquals(9, queue.size());
        
        queue.enqueue("ten");
        assertEquals(10, queue.size());
        
        assertEquals("one",  queue.dequeue());
        assertEquals("two",  queue.dequeue());
        assertEquals(8, queue.size());
        
        queue.enqueue("one");
        queue.enqueue("two");
        assertEquals(10, queue.size());
        
        
        
        // Tests the ensure capacity private method
        queue.enqueue("eleven");
        assertEquals(11, queue.size());
        
        assertEquals("three",  queue.dequeue());
        assertEquals("four",  queue.dequeue());
        assertEquals(9, queue.size());
    }
    
    /**
     * Tests for the dequeue method
     */
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        queue.enqueue("five");
        queue.enqueue("six");
        assertEquals(6, queue.size());
        
        assertEquals("one",  queue.dequeue());
        assertEquals(5, queue.size());
        
        assertEquals("two",  queue.dequeue());
        assertEquals(4, queue.size());
        
        assertEquals("three",  queue.dequeue());
        assertEquals(3, queue.size());
        
        assertEquals("four",  queue.dequeue());
        assertEquals(2, queue.size());
        
        assertEquals("five",  queue.dequeue());
        assertEquals(1, queue.size());
        
        assertEquals("six",  queue.dequeue());
        assertEquals(0, queue.size());
                
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }
    
    /**
     * Tests for the front method
     */
    @Test
    public void testFront() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        try {
            queue.front();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        
        queue.enqueue("one");
        assertEquals("one", queue.front());
        
        queue.enqueue("two");
        assertEquals("one", queue.front());
        assertEquals("one",  queue.dequeue());
        assertEquals("two", queue.front());


        
        queue.enqueue("three");
        assertEquals("two",  queue.dequeue());
        assertEquals("three", queue.front());
        
        queue.enqueue("four");
        assertEquals("three",  queue.dequeue());
        assertEquals("four", queue.front());
        
        queue.enqueue("five");
        assertEquals("four",  queue.dequeue());
        assertEquals("five", queue.front());
        
        queue.enqueue("six");
        assertEquals("five",  queue.dequeue());
        assertEquals("six", queue.front());
        

    }

}

