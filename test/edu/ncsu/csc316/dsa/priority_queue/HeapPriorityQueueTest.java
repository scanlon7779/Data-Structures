package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Tests for the HeapPriorityQueue class
 * @author colinscanlon
 *
 */
public class HeapPriorityQueueTest {

	/** local heap for testing */
	private PriorityQueue<Integer, String> heap;
	
	/**
	 * Resets the heap before each test
	 */
	@Before
	public void setUp() {
		heap = new HeapPriorityQueue<Integer, String>();
	}
	
	/**
	 * Tests the insert method
	 */
	@Test
	public void testInsert() {
		assertTrue(heap.isEmpty());
		assertTrue(heap.size() == 0);
		
		heap.insert(8, "eight");
		assertEquals(1, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(8, (int)heap.min().getKey());
		heap.insert(7, "seven");
		assertEquals(7, (int)heap.min().getKey());
		
		heap.insert(15, "fifteen");
		assertEquals(7, (int)heap.min().getKey());
		heap.insert(1, "one");
		assertEquals(1, (int)heap.min().getKey());
		assertEquals(4, heap.size());
		
	}
	
	/**
	 * Tests the min method
	 */
	@Test
	public void testMin() {
		assertTrue(heap.isEmpty());
		assertTrue(heap.size() == 0);
		
		assertNull(heap.min());
		heap.insert(8, "eight");
		assertEquals(8, (int)heap.min().getKey());
		heap.insert(7, "seven");
		assertEquals(7, (int)heap.min().getKey());
		heap.insert(6, "six");
		assertEquals(6, (int)heap.min().getKey());
		heap.insert(5, "five");
		assertEquals(5, (int)heap.min().getKey());
		heap.insert(10, "ten");
		assertEquals(5, (int)heap.min().getKey());
		
	}
	
	/**
	 * Removes the minimum value from the queue
	 */
	@Test 
	public void deleteMin() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		
		assertNull(heap.deleteMin());
		heap.insert(8, "eight");
		heap.insert(7, "seven");
		heap.insert(6, "six");
		heap.insert(5, "five");
		heap.insert(4, "four");
		heap.insert(3, "three");
		assertEquals(6, heap.size());
		assertEquals(3, (int)heap.deleteMin().getKey());
		assertEquals(4, (int)heap.min().getKey());
		assertEquals(4, (int)heap.deleteMin().getKey());
		assertEquals(5, (int)heap.deleteMin().getKey());
		assertEquals(6, (int)heap.deleteMin().getKey());
		assertEquals(7, (int)heap.deleteMin().getKey());
		assertEquals(8, (int)heap.deleteMin().getKey());
		
	}
	
	/**
	 * Tests for a heap of students
	 */
	@Test
	public void testStudentHeap() {
		PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
		Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
		Student s2 = new Student("J", "S", 2, 1, 2, "js2");
		Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
		Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
		Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
		
		assertTrue(sHeap.isEmpty());
		assertEquals(0, sHeap.size());
		
		sHeap.insert(s1, "Student1");
		assertEquals(1, sHeap.size());
		assertEquals(s1, sHeap.min().getKey());
		sHeap.insert(s2, "Student2");
		sHeap.insert(s3, "Student3");
		sHeap.insert(s4, "Student4");
		sHeap.insert(s5, "Student5");
		assertEquals(s1, sHeap.min().getKey());
		assertEquals(s1, sHeap.deleteMin().getKey());
		assertEquals(s2, sHeap.min().getKey());
		
	}
}