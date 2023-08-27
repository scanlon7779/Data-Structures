package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
/**
 * Tests for the SplayTreeMap class
 * @author colinscanlon
 *
 */
public class SplayTreeMapTest {

	/** the local instance of a BinarySearctTreeMap for testing */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Resets the tree before each test
     */
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Tests for the put method
     */
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(3, "string3"));
        assertEquals(1, tree.size());
        assertEquals(3, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(5, "string5"));
        assertEquals(2, tree.size());
        assertEquals(5, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(2, "string2"));
        assertEquals(3, tree.size());
        assertEquals(2, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(4, "string4"));
        assertEquals(4, tree.size());
        assertEquals(4, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(1, "string1"));
        assertEquals(5, tree.size());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(7, "string1"));
        assertEquals(6, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        
        StudentGPAComparator comparator = new StudentGPAComparator();
        BinarySearchTreeMap<Student, String> tree2 = new SplayTreeMap<Student, String>(comparator);
        Student sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
        tree2.put(sOne, "Student one");
    }
    
    /**
     * Tests for the get method
     */
    @Test
    public void testGet() {
    	assertNull(tree.put(3, "string3"));
        assertEquals(3, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(5, "string5"));
        assertEquals(5, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(2, "string2"));
        assertEquals(2, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(4, "string4"));
        assertEquals(4, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(1, "string1"));
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(7, "string7"));
        assertEquals(7, (int)tree.root().getElement().getKey());
        
        assertEquals("string7", tree.get(7));
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals("string3", tree.get(3));
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals("string5", tree.get(5));
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertEquals("string2", tree.get(2));
        assertEquals(2, (int)tree.root().getElement().getKey());
        
        
        
        
    }
    
    /**
     * Tests for the remove method
     */
    @Test
    public void testRemove() {
    	assertNull(tree.put(3, "string3"));
    	assertNull(tree.put(5, "string5"));
    	assertNull(tree.put(2, "string2"));
    	assertNull(tree.put(4, "string4"));
    	assertNull(tree.put(1, "string1"));
    	assertNull(tree.put(7, "string7"));
    	
    	assertEquals("string7", tree.remove(7));
    	assertEquals(2, (int)tree.root().getElement().getKey());
    	assertEquals("string1", tree.remove(1));
    	assertEquals(2, (int)tree.root().getElement().getKey());
    	
    	assertEquals("string4", tree.remove(4));
    	assertEquals(5, (int)tree.root().getElement().getKey());
    	
    	assertEquals("string3", tree.remove(3));
    	assertEquals(2, (int)tree.root().getElement().getKey());
    	assertEquals("string5", tree.remove(5));
    	assertEquals(2, (int)tree.root().getElement().getKey());
    	
    	assertEquals("string2", tree.remove(2));
    	assertEquals(0, tree.size());
    	
    	
    }
}

