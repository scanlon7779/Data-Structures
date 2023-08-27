package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Contains the test for HashSet
 * @author colinscanlon
 *
 */
public class HashSetTest {

	/** The local set for testing */
    private Set<Integer> set;
    
    /** The set for testing */
    private Set<Integer> testSet;
    
    /**
     * Resets both sets before each test
     */
    @Before
    public void setUp() {
        // Will use a hash map with random alpha, beta values
        set = new HashSet<Integer>();
        
        // Will use our hash map for testing, which uses alpha=1, beta=1, prime=7
        testSet = new HashSet<Integer>(true);
    }
    
    /**
     * Tests the add method
     */
    @Test
    public void testAdd() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
        set.add(5);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
        
        set.add(10);
        assertEquals(2, set.size());
        set.add(15);
        assertEquals(3, set.size());
        set.add(20);
        assertEquals(4, set.size());
        set.add(25);
        assertEquals(5, set.size());
        
    }

    /**
     * Tests for the contains method
     */
    @Test
    public void testContains() {
    	assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());

        assertTrue(set.contains(5));
        assertTrue(set.contains(10));
        assertTrue(set.contains(15));
        assertTrue(set.contains(20));
        assertTrue(set.contains(25));
    }

    /**
     * Tests for the remove method
     */
    @Test
    public void testRemove() {
    	assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        assertEquals(5, (int)set.remove(5));
        assertEquals(4, set.size());
        assertEquals(15, (int)set.remove(15));
        assertEquals(3, set.size());
        assertEquals(25, (int)set.remove(25));
        assertEquals(2, set.size());
        assertEquals(10, (int)set.remove(10));
        assertEquals(1, set.size());
        assertEquals(20, (int)set.remove(20));
        assertEquals(0, set.size());
        assertTrue(set.isEmpty());
    }
    
    /**
     * Tests for the retain all method
     */
    @Test
    public void testRetainAll() {       
    	 assertTrue(set.isEmpty());
         assertEquals(0, set.size());
         set.add(5);
         set.add(10);
         set.add(15);
         set.add(20);
         set.add(25);
         assertEquals(5, set.size());
         
         Set<Integer> other = new TreeSet<Integer>();
         other.add(10);
         other.add(20);
         other.add(30);
         
         set.retainAll(other);
         assertEquals(2, set.size());
         assertTrue(set.contains(10));
         assertTrue(set.contains(20));
    }
    
    /**
     * Tests for the remove all method
     */
    @Test
    public void testRemoveAll() {
    	assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        set.removeAll(other);
        assertEquals(3, set.size());
        assertFalse(set.contains(10));
        assertFalse(set.contains(20));
        assertTrue(set.contains(5));
        assertTrue(set.contains(15));
        assertTrue(set.contains(25));
    }
    
    /**
     * Tests for the addAll method
     */
    @Test
    public void testAddAll() {
    	assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(30);
        other.add(40);
        other.add(50);
        
        set.addAll(other);
        assertEquals(8, set.size());
        assertTrue(set.contains(5));
        assertTrue(set.contains(15));
        assertTrue(set.contains(25));
        assertTrue(set.contains(30));
        assertTrue(set.contains(40));
        assertTrue(set.contains(50));
    }

    // Since our hash map uses random numbers, it can
    // be difficult to test that our hash set iterator returns
    // values in a certain order.
    // We could approach this problem with a few different options:
    // (1) instead of checking the specific order of entries
    //      visited by the iterator, we could save each
    //      iterator.next() into an array, then check that the 
    //      array *contains* the entry, regardless of its exact location
    //
    // (2) implement an isTesting flag for HashSet, similar to HashtestSet. 
    //     This is the more appropriate option since we can control the
    //     entire execution and know exactly which values should appear
    //     in the set and in the correct expected order from using the iterator 
    //
    // Revisit your test cases for HashMap iterator -- those tests can be adapted
    //     to work here, too, since you have already worked out the details
    //     of where entries with certain keys will be stored and in what order
    //     they will be stored
    /**
     * Tests the iterator
     */
    @Test
    public void testIterator() {
        assertTrue(testSet.isEmpty());
        testSet.add(3);
        testSet.add(5);
        testSet.add(2);
        testSet.add(4);
        testSet.add(1);
        testSet.add(6);
        assertEquals(6, testSet.size());
        assertFalse(testSet.isEmpty());
        
        Iterator<Integer> it = testSet.iterator();
        assertTrue(it.hasNext());
        assertEquals(6, (int)it.next()); // should be index 0
        assertEquals(1, (int)it.next()); // should be index 2
        assertEquals(2, (int)it.next()); // should be index 3
        assertEquals(3, (int)it.next()); // should be index 4
        assertEquals(4, (int)it.next()); // should be index 5
        assertEquals(5, (int)it.next()); // should be index 6   
        assertFalse(it.hasNext());
    }
}