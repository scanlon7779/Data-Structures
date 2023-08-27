package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests for the UpTreeDisjointSetForest class
 * @author colinscanlon
 *
 */
public class UpTreeDisjointSetForestTest {

	/** The local set for testing */
    private DisjointSetForest<String> set;
    
    /**
     * Resets the set before each test
     */
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Tests the makeSet method
     */
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
        
        Position<String> three = set.makeSet("three");
        assertEquals("three", three.getElement());
    }
    
    /**
     * Tests teh union and find methods
     */
    @Test
    public void testUnionFind() {
        Position<String> one = set.makeSet("one");
        Position<String> two = set.makeSet("two");
        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
        Position<String> six = set.makeSet("six");
        Position<String> seven = set.makeSet("seven");
        Position<String> eight = set.makeSet("eight");
        Position<String> nine = set.makeSet("nine");
        Position<String> ten = set.makeSet("ten");
        
        assertEquals(one, set.find("one"));
        
        set.union(one, two);
        assertEquals(two, set.find("one"));
        
        set.union(two, three);
        assertEquals(two, set.find("one"));
        assertEquals(two, set.find("three"));
        assertEquals(two, set.find("two"));
        
        set.union(four, five);
        assertEquals(five, set.find("four"));
        
        set.union(four, two);
        assertEquals(two, set.find("four"));
        assertEquals(two, set.find("five"));
        assertEquals(two, set.find("one"));
        assertEquals(two, set.find("three"));
        assertEquals(two, set.find("two"));
        
        set.union(six, seven);
        assertEquals(seven, set.find("six"));
        
        set.union(eight, nine);
        assertEquals(nine, set.find("eight"));
        
        set.union(eight, ten);
        assertEquals(nine, set.find("eight"));
        assertEquals(nine, set.find("nine"));
        assertEquals(nine, set.find("ten"));
        
        set.union(five, eight);
        assertEquals(two, set.find("four"));
        assertEquals(two, set.find("five"));
        assertEquals(two, set.find("one"));
        assertEquals(two, set.find("three"));
        assertEquals(two, set.find("two"));
        assertEquals(two, set.find("eight"));
        assertEquals(two, set.find("nine"));
        assertEquals(two, set.find("ten"));
        
        set.union(two, seven);
        assertEquals(two, set.find("four"));
        assertEquals(two, set.find("five"));
        assertEquals(two, set.find("one"));
        assertEquals(two, set.find("three"));
        assertEquals(two, set.find("two"));
        assertEquals(two, set.find("eight"));
        assertEquals(two, set.find("nine"));
        assertEquals(two, set.find("ten"));
        assertEquals(two, set.find("six"));
        assertEquals(two, set.find("seven"));
        
    }
}