package edu.ncsu.csc316.dsa.tree;
import edu.ncsu.csc316.dsa.Position;

/**
 * The ADT Tree interface 
 * @author colinscanlon
 *
 * @param <E> The general type of data in the tree
 */
public interface Tree<E> {

	/**
	 * Returns the root of the tree
	 * @return The position of the root of the tree
	 */
    Position<E> root();
    
    /**
     * returns the parent position of the given position
     * @param p The position to get the parent of 
     * @return The parent's position
     */
    Position<E> parent(Position<E> p);
    
    /**
     * Returns the children iteration of the given parent
     * @param p The parent to get the children of 
     * @return The children of the given parent
     */
    Iterable<Position<E>> children(Position<E> p);
    
    /**
     * Gets the number of children for the given position
     * @param p The position to get the children of
     * @return The number of children of the given children
     */
    int numChildren(Position<E> p);
    
    /**
     * Checks if the given position is an internal node or not
     * @param p The position to check 
     * @return If the given position if internal or not
     */
    boolean isInternal(Position<E> p);
    
    /**
     * Checks if the given position is a leaf or not
     * @param p the position to check 
     * @return If the position is a leaf or not
     */
    boolean isLeaf(Position<E> p);
    
    /**
     * Checks if the given position is the root or not
     * @param p The position to check
     * @return If the position is a root or not
     */
    boolean isRoot(Position<E> p);
    
    /**
     * Gets the size of the tree
     * @return The size of the tree
     */
    int size();
    
    /**
     * Checks if the tree is empty or not
     * @return If the tree is empty or not
     */
    boolean isEmpty();

    /**
     * The preorder iteration of the tree
     * @return The iteration of the tree in pre order
     */
    Iterable<Position<E>> preOrder();
    
    /**
     * The post order iteration of the tree
     * @return The post order iteration
     */
    Iterable<Position<E>> postOrder();
    
    /**
     * The level order iteration of the tree
     * @return The level order iteration of the tree
     */
    Iterable<Position<E>> levelOrder();
}
