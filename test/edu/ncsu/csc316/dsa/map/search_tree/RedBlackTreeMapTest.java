package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * Tests for the RedBlackTreeMapTest class
 * @author colinscanlon
 *
 */
public class RedBlackTreeMapTest {

	/** Local tree instance for testing */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Resets the tree before each test
     */
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Tests the put method
     */
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(4,  "four"));
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(4, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(7,  "seven"));
        assertEquals(2, tree.size());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(12,  "twelve"));
        assertEquals(3, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        
        tree.put(12,  "twelve");
        assertEquals(3, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(14,  "fourteen"));
        assertEquals(4, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(8,  "eight"));
        assertEquals(5, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(9,  "nine"));
        assertEquals(6, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(10,  "ten"));
        assertEquals(7, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(11,  "eleven"));
        assertEquals(8, tree.size());
        assertEquals(9, (int)tree.root().getElement().getKey());
        assertEquals(7, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        
        StudentGPAComparator comparator = new StudentGPAComparator();
        BinarySearchTreeMap<Student, String> tree2 = new RedBlackTreeMap<Student, String>(comparator);
        Student sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
        tree2.put(sOne, "Student one");
        
    }
    
    /**
     * Tests for the get method
     */
    @Test
    public void testGet() {
    	assertNull(tree.put(4,  "four"));
    	assertNull(tree.put(7,  "seven"));
    	assertNull(tree.put(12,  "twelve"));
    	assertNull(tree.put(14,  "fourteen"));
    	assertNull(tree.put(8,  "eight"));
    	assertNull(tree.put(9,  "nine"));
    	assertNull(tree.put(10,  "ten"));
    	assertNull(tree.put(11,  "eleven"));
    	assertEquals(9, (int)tree.root().getElement().getKey());
        assertEquals(7, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
    	
    	assertEquals("eight", tree.get(8));
    	assertEquals("four", tree.get(4));
    	assertEquals("nine", tree.get(9));
    	assertEquals("eleven", tree.get(11));
    	assertEquals("ten", tree.get(10));
    	assertEquals("fourteen", tree.get(14));
    	assertEquals(9, (int)tree.root().getElement().getKey());
        assertEquals(7, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
    	
    }
    
    /**
     * Tests for the remove method
     */
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(4,  "four"));
    	assertNull(tree.put(7,  "seven"));
    	assertNull(tree.put(12,  "twelve"));
    	assertNull(tree.put(14,  "fourteen"));
    	assertNull(tree.put(8,  "eight"));
    	assertNull(tree.put(9,  "nine"));
    	assertNull(tree.put(10,  "ten"));
    	assertNull(tree.put(11,  "eleven"));
    	
    	assertEquals("eight", tree.remove(8));
    	assertEquals(9, (int)tree.root().getElement().getKey());
        assertEquals(7, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        
        assertEquals("nine", tree.remove(9));
        assertEquals(10, (int)tree.root().getElement().getKey());
        assertEquals(7, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        
        assertEquals("twelve", tree.remove(12));
        assertEquals(10, (int)tree.root().getElement().getKey());
        assertEquals(7, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(14, (int)tree.right(tree.root()).getElement().getKey());
        
        assertEquals("seven", tree.remove(7));
        assertEquals(10, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(14, (int)tree.right(tree.root()).getElement().getKey());
        
        assertEquals("ten", tree.remove(10));
        assertEquals(11, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(14, (int)tree.right(tree.root()).getElement().getKey());
        
        assertEquals("fourteen", tree.remove(14));
        assertEquals(11, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        
        assertEquals("eleven", tree.remove(11));
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals("four", tree.remove(4));
        
        assertNull(tree.put(14,  "fourteen"));
    	assertNull(tree.put(7,  "seven"));
    	assertNull(tree.put(16,  "sixteen"));
    	assertNull(tree.put(4,  "four"));
    	assertNull(tree.put(12,  "twelve"));
    	assertNull(tree.put(15,  "fifteen"));
    	assertNull(tree.put(18,  "eighteen"));
    	assertNull(tree.put(3,  "three"));
    	assertNull(tree.put(5,  "five"));
    	assertNull(tree.put(17,  "seventeen"));
    	
    	tree.remove(3);
    	tree.remove(12);
    	tree.remove(17);
    	tree.remove(18);
    	tree.remove(15);
    	tree.remove(16);
    	assertEquals(5, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(14, (int)tree.right(tree.root()).getElement().getKey());
    	
        
    }
}