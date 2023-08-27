package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * Tests for the AVLTreeMap class
 * @author colinscanlon
 *
 */
public class AVLTreeMapTest {

	/** The local instance of the tree for testing */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Resets the tree before each test
     */
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Tests for the put method
     */
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(5, "string5"));
        assertEquals(1, tree.size());
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertNull(tree.left(tree.root()).getElement());
        
        assertNull(tree.put(10, "string10"));
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertNull(tree.left(tree.root()).getElement());
        assertEquals(10, (int)tree.right(tree.root()).getElement().getKey());
        assertNull(tree.left(tree.right(tree.root())).getElement());
        assertNull(tree.right(tree.right(tree.root())).getElement());
        assertEquals(2, tree.size());
        
        assertNull(tree.put(15, "string15"));
        assertNull(tree.put(20, "string7"));
        assertNull(tree.put(25, "string15"));
        assertEquals(10, (int)tree.root().getElement().getKey());
        assertEquals(20, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(5, tree.size());
        
        assertNull(tree.put(11, "string11"));
        assertEquals(15, (int)tree.root().getElement().getKey());
        assertEquals(20, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(10, (int)tree.left(tree.root()).getElement().getKey());
        
        assertNull(tree.put(30, "string11"));
        assertEquals(15, (int)tree.root().getElement().getKey());
        assertEquals(25, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(10, (int)tree.left(tree.root()).getElement().getKey());
        
        assertNull(tree.put(12, "string11"));
        assertNull(tree.put(13, "string11"));
        assertEquals(15, (int)tree.root().getElement().getKey());
        assertEquals(25, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(10, (int)tree.left(tree.root()).getElement().getKey());
        
        assertNull(tree.put(1, "string11"));
        assertNull(tree.put(7, "string11"));
        assertNull(tree.put(8, "string11"));
        assertEquals(10, (int)tree.root().getElement().getKey());
        assertEquals(15, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
        
    }
    
    /**
     * Tests for the get method
     */
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        assertNull(tree.put(3, "string3"));
        assertFalse(tree.isEmpty());
        
        assertEquals("string3", tree.get(3));
        assertEquals(null, tree.get(6));
        assertEquals(null, tree.get(0));
        
        assertNull(tree.put(9, "string9"));
        assertNull(tree.put(2, "string2"));
        assertEquals("string9", tree.get(9));
        assertEquals("string2", tree.get(2));
        
        assertNull(tree.put(10, "string10"));
        assertNull(tree.put(15, "string15"));
        assertEquals("string10", tree.get(10));
        assertEquals("string2", tree.get(2));
        assertEquals("string9", tree.get(9));
        assertEquals("string15", tree.get(15));
        assertEquals("string3", tree.get(3));
        StudentGPAComparator comparator = new StudentGPAComparator();
        BinarySearchTreeMap<Student, String> tree2 = new AVLTreeMap<Student, String>(comparator);
        Student sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
        tree2.put(sOne, "Student one");
        
    }
    
    /**
     * Tests for the remove method
     */
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertNull(tree.put(1, "one"));
        assertNull(tree.put(2, "two"));
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(4, "four"));
        assertNull(tree.put(5, "five"));
        assertNull(tree.put(6, "six"));
        assertNull(tree.put(7, "seven"));
        assertEquals(7, tree.size());
        assertFalse(tree.isEmpty());
        
        assertNull(tree.remove(0));
        assertEquals(7, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(7, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        assertEquals("three", tree.remove(3));
        assertEquals("four", tree.remove(4));
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        
        assertEquals("one", tree.remove(1));
        assertEquals("two", tree.remove(2));
        assertEquals(6, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals("seven", tree.remove(7));
        assertEquals(6, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals("six", tree.remove(6));
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertEquals("five", tree.remove(5));
        assertEquals(0, tree.size());
        
        assertNull(tree.put(44, "one"));
        assertNull(tree.put(17, "two"));
        assertNull(tree.put(62, "three"));
        assertNull(tree.put(32, "four"));
        assertNull(tree.put(50, "five"));
        assertNull(tree.put(78, "six"));
        assertNull(tree.put(48, "seven"));
        assertNull(tree.put(54, "one"));
        assertNull(tree.put(88, "two"));
        assertEquals("four", tree.remove(32));
        assertEquals(62, (int)tree.root().getElement().getKey());
        
        
    }
}