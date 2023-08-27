package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the merge sort class
 * @author colinscanlon
 *
 */
public class MergeSorterTest {

	/** Ascending data */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	
	/** descending data */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	
	/** random data */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	/**
	 * the sorter for insertion sorter
	 */
	private MergeSorter<Integer> mergeSorter;

	/** 
	 * creates a new InsertionSorter object
	 */
	@Before
	public void setUp() {
		mergeSorter = new MergeSorter<Integer>(); 
	}

	/**
	 * Tests the ascending, descending, and random arrays
	 */
	@Test
	public void testSortIntegers() {
		mergeSorter.sort(dataAscending);
		assertTrue(dataAscending[0].equals(1));
		assertTrue(dataAscending[1].equals(2));
		assertTrue(dataAscending[2].equals(3));
		assertTrue(dataAscending[3].equals(4));
		assertTrue(dataAscending[4].equals(5));

		mergeSorter.sort(dataDescending);
		assertTrue(dataDescending[0].equals(1));
		assertTrue(dataDescending[1].equals(2));
		assertTrue(dataDescending[2].equals(3));
		assertTrue(dataDescending[3].equals(4));
		assertTrue(dataDescending[4].equals(5));

		mergeSorter.sort(dataRandom);
		assertTrue(dataRandom[0].equals(1));
		assertTrue(dataRandom[1].equals(2));
		assertTrue(dataRandom[2].equals(3));
		assertTrue(dataRandom[3].equals(4));
		assertTrue(dataRandom[4].equals(5));
	}
}
