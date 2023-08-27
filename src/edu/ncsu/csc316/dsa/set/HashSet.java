package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

// Since our hash map uses linear probing, the entries are not ordered.
// As a result, we do not restrict our hash set to use Comparable elements.
// This also gives you an option if you need a set to manage elements
//     that are *NOT* Comparable (versus a TreeSet)
/**
 * The class for hash sets
 * @author colinscanlon
 *
 * @param <E> The generic type of elements
 */
public class HashSet<E> extends AbstractSet<E> {

	/** The local map for the set */
    private Map<E, E> map;
    
    // This constructor will use our "production version" of our hash map
    // meaning random values for alpha and beta will be used
    /**
     * Basic constructor when not testing
     */
    public HashSet() {
        this(false);
    }
    
    // If isTesting is true, this constructor will use our "development version" of our hash map
    // meaning alpha=1, beta=1, and prime=7 
    /**
     * Constructor for testing the HashSet
     * @param isTesting Boolean for if were testing or not
     */
    public HashSet(boolean isTesting) {
        map = new LinearProbingHashMap<E, E>(isTesting);
    }   
    
    @Override
    public Iterator<E> iterator() {
        return map.iterator();
    }

    @Override
    public void add(E value) {
        map.put(value, value);
    }

    @Override
    public boolean contains(E value) {
        return map.get(value) != null;
    }

    @Override
    public E remove(E value) {
        return map.remove(value);
    }
    
    @Override
    public int size() {
        return map.size();
    }
}