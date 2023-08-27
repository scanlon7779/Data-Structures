package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Tests for the HeapAdaptablePriorityQueue class
 * @author colinscanlon
 *
 */
public class HeapAdaptablePriorityQueueTest {

	/** Local heap for testing */
	private HeapAdaptablePriorityQueue<Integer, String> heap;
	
	/**
	 * Resets the heap before each test
	 */
	@Before
	public void setUp() {
		heap = new HeapAdaptablePriorityQueue<Integer, String>();
	}
	
	/**
	 * Tests the replaceKey method
	 */
	@Test
	public void testReplaceKey() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		
		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		Entry<Integer, String> e3 = heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());
		
		heap.replaceKey(e8,  -5);
		assertEquals(-5, (int)heap.min().getKey());
		assertEquals("eight", heap.min().getValue());
		assertEquals(9, heap.size());
		
		heap.replaceKey(e8,  9);
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
		assertEquals(9, heap.size());
		
		heap.replaceKey(e0,  100);
		assertEquals(1, (int)heap.min().getKey());
		assertEquals("one", heap.min().getValue());
		assertEquals(9, heap.size());
		
		heap.replaceKey(e1,  101);
		assertEquals(2, (int)heap.min().getKey());
		assertEquals("two", heap.min().getValue());
		assertEquals(9, heap.size());
		
		heap.replaceKey(e2,  102);
		assertEquals(3, (int)heap.min().getKey());
		assertEquals("three", heap.min().getValue());
		assertEquals(9, heap.size());
		
		heap.replaceKey(e3,  103);
		assertEquals(4, (int)heap.min().getKey());
		assertEquals("four", heap.min().getValue());
		assertEquals(9, heap.size());
		
		heap.replaceKey(e4,  104);
		assertEquals(5, (int)heap.min().getKey());
		assertEquals("five", heap.min().getValue());
		assertEquals(9, heap.size());
		
		heap.replaceKey(e5,  105);
		assertEquals(6, (int)heap.min().getKey());
		assertEquals("six", heap.min().getValue());
		assertEquals(9, heap.size());
		
		heap.replaceKey(e6,  106);
		assertEquals(7, (int)heap.min().getKey());
		assertEquals("seven", heap.min().getValue());
		assertEquals(9, heap.size());
		
		heap.replaceKey(e7,  107);
		assertEquals(9, (int)heap.min().getKey());
		assertEquals("eight", heap.min().getValue());
		assertEquals(9, heap.size());
		
	}
	
	/**
	 * Tests for the replaceValue method
	 */
	@Test
	public void testReplaceValue() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		
		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		Entry<Integer, String> e3 = heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());
		
		heap.replaceValue(e8,  "EIGHT");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("EIGHT",  e8.getValue());
		
		heap.replaceValue(e0,  "ZERO");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("ZERO", heap.min().getValue());
		
		heap.replaceValue(e1,  "ONE");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("ZERO", heap.min().getValue());
		assertEquals("ONE",  e1.getValue());
		
		heap.replaceValue(e2,  "TWO");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("ZERO", heap.min().getValue());
		assertEquals("TWO",  e2.getValue());
		
		heap.replaceValue(e3,  "THREE");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("ZERO", heap.min().getValue());
		assertEquals("THREE",  e3.getValue());
		
		heap.replaceValue(e4,  "FOUR");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("ZERO", heap.min().getValue());
		assertEquals("FOUR",  e4.getValue());
		
		heap.replaceValue(e5,  "FIVE");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("ZERO", heap.min().getValue());
		assertEquals("FIVE",  e5.getValue());
		
		heap.replaceValue(e6,  "SIX");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("ZERO", heap.min().getValue());
		assertEquals("SIX",  e6.getValue());
		
		heap.replaceValue(e7,  "SEVEN");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("ZERO", heap.min().getValue());
		assertEquals("SEVEN",  e7.getValue());
		
		
	}
	
	/**
	 * Tests for the remove method
	 */
	@Test
	public void testRemove() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		
		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		Entry<Integer, String> e3 = heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());
		
		heap.remove(e0);
		assertEquals(1, (int)heap.min().getKey());
		assertEquals("one", heap.min().getValue());
		assertEquals(8, heap.size());
		
		heap.remove(e1);
		assertEquals(2, (int)heap.min().getKey());
		assertEquals("two", heap.min().getValue());
		assertEquals(7, heap.size());
		
		heap.remove(e8);
		assertEquals(2, (int)heap.min().getKey());
		assertEquals("two", heap.min().getValue());
		assertEquals(6, heap.size());
		
		heap.remove(e7);
		assertEquals(2, (int)heap.min().getKey());
		assertEquals("two", heap.min().getValue());
		assertEquals(5, heap.size());
		
		heap.remove(e4);
		assertEquals(2, (int)heap.min().getKey());
		assertEquals("two", heap.min().getValue());
		assertEquals(4, heap.size());
		
		heap.remove(e2);
		assertEquals(3, (int)heap.min().getKey());
		assertEquals("three", heap.min().getValue());
		assertEquals(3, heap.size());
		
		heap.remove(e3);
		assertEquals(5, (int)heap.min().getKey());
		assertEquals("five", heap.min().getValue());
		assertEquals(2, heap.size());
		
		heap.remove(e6);
		assertEquals(5, (int)heap.min().getKey());
		assertEquals("five", heap.min().getValue());
		assertEquals(1, heap.size());
		
		heap.remove(e5);
		assertEquals(0, heap.size());
	}
	
	/**
	 * Tests for a heap sorting students
	 */
	@Test
	public void testStudentHeap() {
		AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
		Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
		Student s2 = new Student("J", "S", 2, 1, 2, "js2");
		Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
		Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
		Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
		
		assertTrue(sHeap.isEmpty());
		assertEquals(0, sHeap.size());
		
		Entry<Student, String> e1 = sHeap.insert(s1, "s1");
		Entry<Student, String> e2 = sHeap.insert(s2, "s2");
		Entry<Student, String> e3 = sHeap.insert(s3, "s3");
		Entry<Student, String> e4 = sHeap.insert(s4, "s4");
		Entry<Student, String> e5 = sHeap.insert(s5, "s5");
		
		assertEquals(s1, sHeap.min().getKey());
		assertEquals(5, sHeap.size());
		sHeap.replaceValue(e1, "sONE");
		assertEquals("sONE", e1.getValue());
		
		sHeap.replaceValue(e2, "sTWO");
		assertEquals("sTWO", e2.getValue());
		
		sHeap.replaceValue(e3, "sTHREE");
		assertEquals("sTHREE", e3.getValue());
		
		sHeap.replaceValue(e4, "sFOUR");
		assertEquals("sFOUR", e4.getValue());
		
		sHeap.replaceValue(e5, "sFIVE");
		assertEquals("sFIVE", e5.getValue());
		sHeap.remove(e2);
		assertEquals(s1, sHeap.min().getKey());
		assertEquals(4, sHeap.size());
		
	}
}