package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;

/**
 * Interface for Disjointed Sets
 * @author colinscanlon
 *
 * @param <E> The generic type of the set
 */
public interface DisjointSetForest<E> {

	/**
	 * Makes a set with the given value
	 * @param value The value to add to the set
	 * @return The position of the set
	 */
    Position<E> makeSet(E value);
    
    /**
     * Finds the position of the given value
     * @param value The value to find the position of
     * @return The position of the value
     */
    Position<E> find(E value);
    
    /**
     * Creates a union between the two positions
     * @param s The first position to connect
     * @param t The second position to connect
     */
    void union(Position<E> s, Position<E> t);

}