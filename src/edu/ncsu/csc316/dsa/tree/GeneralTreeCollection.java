package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * The interface for the general tree collection that extends tree
 * @author colinscanlon
 *
 * @param <E> The general type of the tree
 */
public interface GeneralTreeCollection<E> extends Tree<E>, Iterable<E> {
	
	/**
	 * Adds a root to the tree and returns a reference to the new root
	 * @param value The value to add to the tree
	 * @return The new reference to the tree
	 */
    Position<E> addRoot(E value);
    
    /**
     * Adds a child to the given position in the tree
     * @param p The parent to add the child to 
     * @param value The value of the child
     * @return The reference to the new child
     */
    Position<E> addChild(Position<E> p, E value);
    
    /**
     * Removes the given position
     * @param p The position to remove
     * @return The value of the removed position
     */
    E remove(Position<E> p);
    
    /**
     * Sets a given position to a new given value
     * @param p The position to change the value of 
     * @param value The new value of the position
     * @return The old value of the position
     */
    E set(Position<E> p, E value);
}