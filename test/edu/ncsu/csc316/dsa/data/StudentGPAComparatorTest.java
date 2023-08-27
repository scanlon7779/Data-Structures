package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the comparison of students gpa
 * @author colinscanlon
 *
 */
public class StudentGPAComparatorTest {

	/** first student to test **/
	private Student sOne;
	
	/** second student to test */
	private Student sTwo;
	
	/** third student to test */
	private Student sThree;
	/** fourth student to test */
	private Student sFour;
	
	/** fifth student to test */
	private Student sFive;

	/** private gpa comparator **/
	private StudentGPAComparator comparator;

	/** Initializing the students and their values */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentGPAComparator();
	}

	/**
	 * tests that students are given the correct number when compared
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);
		assertTrue(comparator.compare(sThree, sOne) < 0);
		assertTrue(comparator.compare(sFour, sOne) < 0);
		assertTrue(comparator.compare(sOne, sTwo) > 0);
		assertTrue(comparator.compare(sFive, sOne) < 0);
	}

}
