package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Interface for binary trees
 * @author colinscanlon
 *
 * @param <E> the general type of the tree
 */
public interface BinaryTree<E> extends Tree<E> {
	/**
	 * Gets the left child of the given position
	 * @param p The position to get the child of
	 * @return The left child of the position
	 */
    Position<E> left(Position<E> p);
    
    /**
     * Gets the right child of the position
     * @param p The position to get the right child of
     * @return The right child of the given position
     */
    Position<E> right(Position<E> p);
    
    /**
     * Gets the sibling of the current position
     * @param p The position to get the sibling of
     * @return the sibling of the position
     */
    Position<E> sibling(Position<E> p);
    
    /**
     * Gets the iterable of the tree in order
     * @return The iterable of the tree in order
     */
    Iterable<Position<E>> inOrder();
}
