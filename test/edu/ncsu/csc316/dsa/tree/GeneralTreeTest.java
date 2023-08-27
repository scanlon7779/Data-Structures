package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests for the general tree class
 * @author colinscanlon
 *
 */
public class GeneralTreeTest {

	/** The filled tree to be used for testing */
    private GeneralTree<String> tree;
    
    /** The empty tree to be used in testing */
    private GeneralTree<String> emptyTree;
    
    /** The second tree to be used in tests */
    private GeneralTree<String> tree2;
    
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
     * An invalid position to be used in testing
     * @author colinscanlon
     *
     * @param <E> The generic type of the position
     */
    private class InvalidPosition<E> implements Position<E> {

        @Override
        public E getElement() {
            return null;
        }
        
    }
    
    /** 
     * Sets up trees for testing 
     */
    @Before
    public void setUp() {
        tree = new GeneralTree<String>();
        emptyTree = new GeneralTree<String>();
        tree2 = new GeneralTree<String>();
    }
    
    /**
     * Helper method to construct a sample tree
     *
     * One
     * -> Two
     *   -> Six
     *   -> Five
     *   -> Ten
     *     -> Seven
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     *
     * Or, visually:
     *                 one
     *            /            \
     *         two                three
     *      /   |     \             |   
     *   six   five   ten          four
     *                  |         /    \
     *                seven     eight  nine              
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addChild(one, "two");
        three = tree.addChild(one,  "three");
        six = tree.addChild(two, "six");
        five = tree.addChild(two, "five");
        ten = tree.addChild(two,  "ten");
        seven = tree.addChild(ten,  "seven");
        four = tree.addChild(three,  "four");
        eight = tree.addChild(four,  "eight");
        nine = tree.addChild(four,  "nine");
    }
    
    /**
     * Tests the set method
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
     * Tests the size method
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
        tree.addChild(two, "New node");
        assertEquals(9, tree.size());
        
    }
    
    /**
     * Tests the numChildren method
     */
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(one));
        assertEquals(3, tree.numChildren(two));
        assertEquals(0, tree.numChildren(six));
        assertEquals(0, tree.numChildren(five));
        assertEquals(1, tree.numChildren(ten));
        assertEquals(1, tree.numChildren(three));
        assertEquals(0, tree.numChildren(seven));
        assertEquals(2, tree.numChildren(four));
        assertEquals(0, tree.numChildren(eight));
        assertEquals(0, tree.numChildren(nine));
        
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
        assertEquals(two, tree.parent(six));
        assertEquals(two, tree.parent(five));
        assertEquals(two, tree.parent(ten));
        assertEquals(four, tree.parent(eight));
        assertEquals(four, tree.parent(nine));
    }
    
    /**
     * Tests for the isInternal method
     */
    @Test
    public void testIsInternal() {
        createTree();

        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(ten));
        assertTrue(tree.isInternal(four));
        assertTrue(tree.isInternal(three));
        assertFalse(tree.isInternal(six));
        assertFalse(tree.isInternal(five));
        assertFalse(tree.isInternal(eight));
        assertFalse(tree.isInternal(nine));
        assertFalse(tree.isInternal(seven));
    }
    
    /**
     * Tests for the isLeaf method
     */
    @Test
    public void isLeaf() {
        createTree();
        
       assertFalse(tree.isLeaf(two));
       assertFalse(tree.isLeaf(ten));
       assertFalse(tree.isLeaf(four));
       assertFalse(tree.isLeaf(three));
       assertFalse(tree.isLeaf(one));
       assertTrue(tree.isLeaf(six));
       assertTrue(tree.isLeaf(five));
       assertTrue(tree.isLeaf(seven));
       assertTrue(tree.isLeaf(eight));
       assertTrue(tree.isLeaf(nine));
    }
    
    /**
     * Tests for the isRoot method
     */
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
        assertFalse(tree.isRoot(three));
        assertFalse(tree.isRoot(four));
        assertFalse(tree.isRoot(five));
        assertFalse(tree.isRoot(six));
        assertFalse(tree.isRoot(seven));
        assertFalse(tree.isRoot(eight));
        assertFalse(tree.isRoot(nine));
        assertFalse(tree.isRoot(ten));
        
    }
    
    /**
     * Tests the isEmpty method
     */
    @Test
    public void testIsEmpty() {
        assertTrue(emptyTree.isEmpty());
        
        createTree();
        assertFalse(tree.isEmpty());
        assertTrue(emptyTree.isEmpty());
        Position<String> one1 = tree2.addRoot("one");
        assertFalse(tree2.isEmpty());
        tree2.remove(one1);
        assertTrue(tree2.isEmpty());
      
    }
    
    /**
     * Tests for the preOrder iteration
     */
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> pre = tree.preOrder().iterator();
        assertEquals(one, pre.next());
        assertTrue(pre.hasNext());
        assertEquals(two, pre.next());
        assertEquals(six, pre.next());
        assertEquals(five, pre.next());
        assertEquals(ten, pre.next());
        assertEquals(seven, pre.next());
        assertEquals(three, pre.next());
        assertEquals(four, pre.next());
        assertEquals(eight, pre.next());
        assertEquals(nine, pre.next());
        assertFalse(pre.hasNext());
        
    }
    
    /**
     * Tests the iterator method
     */
    @Test
    public void testIterator() {
        createTree();
        Iterator<String> pre = tree.iterator();
        assertEquals("one", pre.next());
        assertTrue(pre.hasNext());
        assertEquals("two", pre.next());
        assertEquals("six", pre.next());
        assertEquals("five", pre.next());
        assertEquals("ten", pre.next());
        assertEquals("seven", pre.next());
        assertEquals("three", pre.next());
        assertEquals("four", pre.next());
        assertEquals("eight", pre.next());
        assertEquals("nine", pre.next());
        assertFalse(pre.hasNext());
        try {
        	pre.remove();
        	fail();
        } catch (Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
    }
    
    /**
     * Tests for the postOrder iteration
     */
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> post = tree.postOrder().iterator();
        assertTrue(post.hasNext());
        assertEquals(six, post.next());
        assertEquals(five, post.next());
        assertEquals(seven, post.next());
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
     * Tests the level-order iterator
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
        assertEquals(five, level.next());
        assertEquals(ten, level.next());
        assertEquals(four, level.next());
        assertEquals(seven, level.next());
        assertEquals(eight, level.next());
        assertEquals(nine, level.next());
        
    }
    
    /**
     * Tests the addChild method
     */
    @Test
    public void testAddChild() {
        assertTrue(tree2.isEmpty());
        Position<String> one1 = tree2.addRoot("one");
        assertEquals(1, tree2.size());
        assertNull(tree2.parent(one1));
        assertEquals("GeneralTree[\none\n]", tree2.toString());
        
        two = tree2.addChild(one1, "two");
        assertEquals(2, tree2.size());
        three = tree2.addChild(two, "three");
        assertEquals(3, tree2.size());
        four = tree2.addChild(three, "four");
        assertEquals(4, tree2.size());
        five = tree2.addChild(three, "five");
        assertEquals(5, tree2.size());
        six = tree2.addChild(three, "six");
        assertEquals(6, tree2.size());
        assertEquals(3, tree2.numChildren(three));
                
    }
    
    /**
     * Tests the remove method
     */
    @Test
    public void testRemove() {
        createTree();
        assertEquals(10, tree.size());
        assertEquals(2, tree.numChildren(four));
        tree.remove(nine);
        assertEquals("GeneralTree[\none\n two\n  six\n  five\n  ten\n   seven\n three\n  four\n   eight\n]", tree.toString());
        assertEquals(9, tree.size());
        assertEquals(1, tree.numChildren(four));
        tree.remove(eight);
        assertEquals(0, tree.numChildren(four));
        tree.remove(ten);
        assertEquals(3, tree.numChildren(two));
        
        Position<String> one1 = tree2.addRoot("one");
        Position<String> two2 = tree2.addChild(one1, "two");
        tree2.remove(one1);
        assertEquals(1, tree2.size());
        tree2.remove(two2);
        assertEquals(0, tree2.size());
        
    }
    
    /**
     * Tests an empty tree
     */
    @Test
    public void testEmptyTree() {
        Tree<String> bTree = new GeneralTree<String>();
        assertTrue(bTree.isEmpty());
        assertEquals(0, bTree.size());
        
    }
    
}