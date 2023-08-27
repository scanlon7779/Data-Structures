package edu.ncsu.csc316.dsa;

/**
 * The position interface for position lists
 * @author colinscanlon
 *
 * @param <E> The general type of elements
 */
public interface Position<E> {
	
	/**
	 * Gets the element of a position
	 * @return The element of the position
	 */
	E getElement();
}
