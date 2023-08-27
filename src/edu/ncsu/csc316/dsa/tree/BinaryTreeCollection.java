package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Interface for the BainaryTreeCollection
 * @author colinscanlon
 *
 * @param <E> The general type of the tree
 */
public interface BinaryTreeCollection<E> extends BinaryTree<E>, Iterable<E> {
	
	/**
	 * Adds a root to the tree
	 * @param value The value of the root
	 * @return The position or the root
	 */
    Position<E> addRoot(E value);
    
    /**
     * Adds a left child to the given position
     * @param p The position to add the left child to
     * @param value The value of the left child
     * @return The position of the left child
     */
    Position<E> addLeft(Position<E> p, E value);
    
    /**
     * Adds a right child with a given value to the given position
     * @param p The position to add the right child to 
     * @param value the value of the right child
     * @return The position of the right child
     */
    Position<E> addRight(Position<E> p, E value);
    
    /**
     * Removes a given position
     * @param p The position to remove from the tree
     * @return The value of the removed position
     */
    E remove(Position<E> p);
    
    /**
     * Sets the given position to the given value
     * @param p The position to set the value of
     * @param value The new value of the position
     * @return The old value of the position
     */
    E set(Position<E> p, E value);
}