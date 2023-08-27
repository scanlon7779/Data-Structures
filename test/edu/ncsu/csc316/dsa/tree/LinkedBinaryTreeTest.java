package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests for the LinkedBinaryTree class
 * @author colinscanlon
 *
 */
public class LinkedBinaryTreeTest {

	/** The local tree instance for testing */
    private LinkedBinaryTree<String> tree;
    
    /** The first positional node */
    private Position<String> one;
    
    /** The second positional node */
    private Position<String> two;
    
    /** The third positional node */
    private Position<String> three;
    
    /** the fourth positional node */
    private Position<String> four;
    
    /** the fifth positional node */
    private Position<String> five;
    
    /** The sixth positional node */
    private Position<String> six;
    
    /** The seventh positional node */
    private Position<String> seven;
    
    /** The eighth positional node */
    private Position<String> eight;
    
    /** The ninth positional node */
    private Position<String> nine;
    
    /** The tenth positional node */
    private Position<String> ten;
    
    /**
     * Helper class to create an invalid position to help test validate(p)
     * @param <E> The generic type of the tree
     */
    private class InvalidPosition<E> implements Position<E> {

        @Override
        public E getElement() {
            return null;
        }
        
    }
    
    /**
     * Resets the tree before each test;
     */
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Tests for the set method
     */
    @Test
    public void testSet() {
        createTree();
        assertEquals("one", tree.set(one, "ONE"));
        InvalidPosition<String> hold = new InvalidPosition<String>();
        hold.getElement();
        
        try {
            tree.set(new InvalidPosition<String>(), "invalid");
            fail();
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
        
        assertEquals(10, tree.size());
        assertEquals("two", tree.set(two, "2"));
        
        assertEquals("seven", tree.set(seven, "77777"));
        assertEquals("77777", tree.remove(seven));
        assertEquals(9, tree.size());
        
    }
    
    /**
     * Tests for the size method
     */
    @Test
    public void testSize() {
    	assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
        assertFalse(tree.isEmpty());
        
        assertEquals("seven", tree.remove(seven));
        assertEquals(9, tree.size());
        
        assertEquals("ten", tree.remove(ten));
        assertEquals(8, tree.size());
        tree.addRight(six, "seven");
        assertEquals(9, tree.size());
    }
    
    /**
     * Tests for the numChildren method
     */
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(one));
        assertEquals(2, tree.numChildren(two));
        assertEquals(1, tree.numChildren(three));
        assertEquals(2, tree.numChildren(four));
        assertEquals(0, tree.numChildren(five));
        assertEquals(0, tree.numChildren(six));
        assertEquals(0, tree.numChildren(seven));
        assertEquals(0, tree.numChildren(eight));
        assertEquals(0, tree.numChildren(nine));
        assertEquals(2, tree.numChildren(ten));
    }

    /**
     * Tests for the parent method
     */
    @Test
    public void testParent() {
        createTree();
        assertNull(tree.parent(one));
        assertEquals(one, tree.parent(two));
        assertEquals(one, tree.parent(three));
        assertEquals(three, tree.parent(four));
        assertEquals(four, tree.parent(eight));
        assertEquals(four, tree.parent(nine));
        assertEquals(two, tree.parent(six));
        assertEquals(two, tree.parent(ten));
        assertEquals(ten, tree.parent(seven));
        assertEquals(ten, tree.parent(five));

    }
    
    /**
     * Tests the default iterator for the tree
     */
    @Test
    public void testIterator() {
        createTree();
        Iterator<String> in = tree.iterator();
        assertTrue(in.hasNext());
        assertEquals("six", in.next());
        assertEquals("two", in.next());
        assertEquals("seven", in.next());
        assertEquals("ten", in.next());
        assertEquals("five", in.next());
        assertEquals("one", in.next());
        assertEquals("eight", in.next());
        assertEquals("four", in.next());
        assertEquals("nine", in.next());
        assertEquals("three", in.next());
        assertFalse(in.hasNext());
        try {
        	in.remove();
        	fail();
        } catch (Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
    }
    
    /**
     * tests for the sibling method
     */
    @Test
    public void testSibling() {
        createTree();
        assertNull(tree.sibling(one));
        assertNull(tree.sibling(four));
        assertEquals(three, tree.sibling(two));
        assertEquals(two, tree.sibling(three));
        assertEquals(six, tree.sibling(ten));
        assertEquals(seven, tree.sibling(five));
        assertEquals(eight, tree.sibling(nine));
    }
    
    /**
     * Tests for the is internal method
     */
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(three));
        assertTrue(tree.isInternal(four));
        assertTrue(tree.isInternal(ten));
        assertFalse(tree.isInternal(six));
        assertFalse(tree.isInternal(five));
        assertFalse(tree.isInternal(seven));
        assertFalse(tree.isInternal(eight));
        assertFalse(tree.isInternal(nine));

        
    }
    
    /**
     * tests the isLeaf method
     */
    @Test
    public void isLeaf() {
        createTree();
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(two));
        assertFalse(tree.isLeaf(four));
        assertFalse(tree.isLeaf(three));
        assertFalse(tree.isLeaf(ten));
        assertTrue(tree.isLeaf(six));
        assertTrue(tree.isLeaf(five));
        assertTrue(tree.isLeaf(seven));
        assertTrue(tree.isLeaf(eight));
        assertTrue(tree.isLeaf(nine));
    }
    
    /**
     * Tests the isRoot method
     */
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
        assertFalse(tree.isRoot(three));
        assertFalse(tree.isRoot(six));
        assertFalse(tree.isRoot(seven));
    }
    
    /**
     * Tests the pre-order iterator
     */
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> pre = tree.preOrder().iterator();
        assertTrue(pre.hasNext());
        assertEquals(one, pre.next());
        assertEquals(two, pre.next());
        assertEquals(six, pre.next());
        assertEquals(ten, pre.next());
        assertEquals(seven, pre.next());
        assertEquals(five, pre.next());
        assertEquals(three, pre.next());
        assertEquals(four, pre.next());
        assertEquals(eight, pre.next());
        assertEquals(nine, pre.next());
        assertFalse(pre.hasNext());
        
    }
    
    /**
     * Tests the post order iteration of the tree
     */
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> post = tree.postOrder().iterator();
        assertTrue(post.hasNext());
        assertEquals(six, post.next());
        assertEquals(seven, post.next());
        assertEquals(five, post.next());
        assertEquals(ten, post.next());
        assertEquals(two, post.next());
        assertEquals(eight, post.next());
        assertEquals(nine, post.next());
        assertEquals(four, post.next());
        assertEquals(three, post.next());
        assertEquals(one, post.next());
        assertFalse(post.hasNext());

    }
    
    /**
     * Tests the in order iterator
     */
    @Test
    public void testInOrder() {
        createTree();
        Iterator<Position<String>> in = tree.inOrder().iterator();
        assertTrue(in.hasNext());
        assertEquals(six, in.next());
        assertEquals(two, in.next());
        assertEquals(seven, in.next());
        assertEquals(ten, in.next());
        assertEquals(five, in.next());
        assertEquals(one, in.next());
        assertEquals(eight, in.next());
        assertEquals(four, in.next());
        assertEquals(nine, in.next());
        assertEquals(three, in.next());
        assertFalse(in.hasNext());
    }
    
    /**
     * Tests on an empty tree
     */
    @Test
    public void testEmptyTree() {
    	LinkedBinaryTree<String> bTree = new LinkedBinaryTree<String>();
        assertTrue(bTree.isEmpty());
        assertEquals(0, bTree.size());
        Position<String> one1 = bTree.addRoot("one");
        bTree.remove(one1);
        assertTrue(bTree.isEmpty());
        
    }
    
    /**
     * Tests the level order iterator
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> level = tree.levelOrder().iterator();
        assertTrue(level.hasNext());
        assertEquals(one, level.next());
        assertEquals(two, level.next());
        assertEquals(three, level.next());
        assertEquals(six, level.next());
        assertEquals(ten, level.next());
        assertEquals(four, level.next());
        assertEquals(seven, level.next());
        assertEquals(five, level.next());
        assertEquals(eight, level.next());
        assertEquals(nine, level.next());
        assertFalse(level.hasNext());
        
    }
    
    /**
     * Tests the addChildren methods
     */
    @Test
    public void testAddChildren() {
    	LinkedBinaryTree<String> tree2 = new LinkedBinaryTree<String>();
    	assertTrue(tree2.isEmpty());
        Position<String> one1 = tree2.addRoot("One");
        assertEquals(1, tree2.size());
        assertNull(tree2.parent(one1));
        Position<String> two2 = tree2.addLeft(one1, "Two");
        assertEquals(2, tree2.size());
        assertEquals(1, tree2.numChildren(one1));
        Position<String> three3 = tree2.addRight(one1, "Three");
        assertEquals(3, tree2.size());
        assertEquals(2, tree2.numChildren(one1));
        assertEquals(0, tree2.numChildren(two2));
        assertEquals(0, tree2.numChildren(three3));
        
    }
    
    /**
     * Tests for the remove method
     */
    @Test
    public void testRemove() {
        createTree();
        assertEquals("six", tree.remove(six));
        assertEquals(1, tree.numChildren(two));
        assertEquals("five", tree.remove(five));
        assertEquals(1, tree.numChildren(ten));
        assertEquals("seven", tree.remove(seven));
        assertEquals("ten", tree.remove(ten));
        assertEquals(0, tree.numChildren(two));
        
        
        LinkedBinaryTree<String> tree2 = new LinkedBinaryTree<String>();
        Position<String> one1 = tree2.addRoot("one");
        Position<String> two2 = tree2.addRight(one1, "two");
        tree2.remove(one1);
        assertEquals(1, tree2.size());
        tree2.remove(two2);
        assertEquals(0, tree2.size());
    }
}