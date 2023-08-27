package edu.ncsu.csc316.dsa.list.positional;

import edu.ncsu.csc316.dsa.Position;

/**
 * The interface for positional lists
 * @author colinscanlon
 *
 * @param <E> The general type of the list
 */
public interface PositionalList<E> extends Iterable<E> {
	
	/**
	 * Adds after the given position
	 * @param p The position to add after
	 * @param value The value to add to the list
	 * @return the position of the new element
	 */
	Position<E> addAfter(Position<E> p, E value);
	
	/**
	 * Adds the given element before the position
	 * @param p The position to add before
	 * @param value The value to add
	 * @return The position of the new element
	 */
	Position<E> addBefore(Position<E> p, E value);
	
	/**
	 * Adds and element at the beginning of the list
	 * @param value The value to add
	 * @return The position of the new element
	 */
	Position<E> addFirst(E value);
	
	/**
	 * Adds an element at the end of the list
	 * @param value The element to add
	 * @return The position of the new element
	 */
	Position<E> addLast(E value);
	
	/**
	 * Gets the position after the current p
	 * @param p The current position
	 * @return The position after the given p
	 */
	Position<E> after(Position<E> p);
	
	/**
	 * The position before the current position
	 * @param p The position in the list
	 * @return The position before the given position
	 */
	Position<E> before(Position<E> p);
	
	/**
	 * Gets the first position in the list
	 * @return The first position in the list
	 */
	Position<E> first();
	
	/**
	 * Returns if the list is empty or not
	 * @return if the list is empty
	 */
	boolean isEmpty();
	
	/**
	 * Gets the last position in the list
	 * @return The last position of the list
	 */
	Position<E> last();
	
	/**
	 * gets an interable of the list to navigate
	 * @return an interable of the positions
	 */
	Iterable<Position<E>> positions();
	
	/**
	 * Removes the element at the given position
	 * @param p the position to remove at
	 * @return The element removed
	 */
	E remove(Position<E> p);
	
	/**
	 * Sets the element at a given position to the given value
	 * @param p The position to set at
	 * @param value The value to set the element to
	 * @return The element previously in that position
	 */
	E set(Position<E> p, E value);
	
	/**
	 * Gets the size of the list
	 * @return the size of the list
	 */
	int size();
}