package edu.ncsu.csc316.dsa.stack;

/**
 * The interface for stack based lists
 * @author colinscanlon
 *
 * @param <E> The generic type of the stack
 */
public interface Stack<E> {

	/**
	 * Adds the given value to the top of the stack
	 * @param value The element to add to the top of the stack
	 */
    void push(E value);
    
    /**
     * Removes and returns the top element of the list 
     * @return the top element of the list
     */
    E pop();
    
    /**
     * The element at the top of the stack
     * @return The element at the top of the stack
     */
    E top();
    
    /**
     * Gets the size of the stack
     * @return the size of the stack
     */
    int size();
    
    /**
     * Returns a boolean for is the stack is empty or not
     * @return if the stack is empty or not
     */
    boolean isEmpty();
}