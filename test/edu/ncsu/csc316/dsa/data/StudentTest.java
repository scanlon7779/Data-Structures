package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests for the student object
 * @author colinscanlon
 *
 */
public class StudentTest {


	/** first student to test **/
	private Student sOne;
	
	/** second student to test */
	private Student sTwo;
	
	
	
	/** copy of the first student */
	private Student s1;


	/** Initializing the students and their values */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		s1 = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
	}

	/**
	 * Tests set first name
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Tests set last name
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Tests setId
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Tests setGpa
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * tests setUnityId
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Tests compareTo
	 */
	@Test
	public void testCompareTo() {
		// assertTrue(sOne.compareTo(sTwo) < 0);
		// assertTrue(sTwo.compareTo(sOne) > 0);
		assertSame(sOne.compareTo(sOne), 0);
		assertSame(sTwo.compareTo(sTwo), 0);
	}
	
	/**
	 * Tests equals
	 */
	@Test
	public void testEquals() {
		assertFalse(sOne.equals(sTwo));
		assertTrue(s1.equals(sOne));
	}
	
	/**
	 * Tests toString
	 */
	@Test
	public void testToString() {
		assertEquals("OneFirst,OneLast,1,1,1.0,oneUnityID", sOne.toString());
	}
	
	/**
	 * Tests the HashCode method
	 */
	@Test
	public void testHashCode() {
		assertEquals(s1.hashCode(), sOne.hashCode());
	}
	
	/**
	 * Test the setCreditHours method
	 */
	@Test
	public void testSetCreditHours() {
		assertEquals(1, s1.getCreditHours());
		s1.setCreditHours(2);
		assertEquals(2, s1.getCreditHours());

		
	}
}
