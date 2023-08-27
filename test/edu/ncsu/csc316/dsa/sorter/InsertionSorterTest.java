package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests for the InsertionSort method
 * @author colinscanlon
 *
 */
public class InsertionSorterTest {

	/** Ascending data */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	
	/** descending data */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	
	/** random data */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	/**
	 * the sorter for insertion sorter
	 */
	private InsertionSorter<Integer> integerSorter;

	/** 
	 * creates a new InsertionSorter object
	 */
	@Before
	public void setUp() {
		integerSorter = new InsertionSorter<Integer>();
	}

	/**
	 * Tests the ascending, descending, and random arrays
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertTrue(dataAscending[0].equals(1));
		assertTrue(dataAscending[1].equals(2));
		assertTrue(dataAscending[2].equals(3));
		assertTrue(dataAscending[3].equals(4));
		assertTrue(dataAscending[4].equals(5));

		integerSorter.sort(dataDescending);
		assertTrue(dataDescending[0].equals(1));
		assertTrue(dataDescending[1].equals(2));
		assertTrue(dataDescending[2].equals(3));
		assertTrue(dataDescending[3].equals(4));
		assertTrue(dataDescending[4].equals(5));

		integerSorter.sort(dataRandom);
		assertTrue(dataRandom[0].equals(1));
		assertTrue(dataRandom[1].equals(2));
		assertTrue(dataRandom[2].equals(3));
		assertTrue(dataRandom[3].equals(4));
		assertTrue(dataRandom[4].equals(5));
	}

	/**
	 * Tests sorting an array of students
	 */
	@Test
	public void testSortStudent() {
		InsertionSorter<Student> integerSorter2 = new InsertionSorter<Student>(null);
		integerSorter2 = new InsertionSorter<Student>();
		Student sOne = new Student("Alex", "Andrew", 1, 1, 1.0, "oneUnityID");
		Student sTwo = new Student("Bill", "Bob", 2, 2, 2.0, "bbb");
		Student sThree = new Student("Colin", "Bob", 3, 3, 3.0, "threeUnityID");
		Student sFour = new Student("Doublas", "Colin", 4, 4, 4.0, "fourUnityID");
		Student sFive = new Student("Bill", "Bob", 5, 5, 5.0, "bcd");
		Student[] array = {sOne, sTwo, sThree, sFour, sFive};
		integerSorter2.sort(array);
		assertTrue(array[0].getFirst().equals("Alex"));
		assertTrue(array[1].getUnityID().equals("bbb"));
		assertTrue(array[2].getUnityID().equals("bcd"));
		assertTrue(array[3].getFirst().equals("Colin"));
		assertTrue(array[4].getFirst().equals("Doublas"));


		
	}
}
