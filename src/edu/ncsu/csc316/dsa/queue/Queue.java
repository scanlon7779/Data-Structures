package edu.ncsu.csc316.dsa.queue;

/**
 * The interface for queues
 * @author colinscanlon
 *
 * @param <E> The general type of the queue
 */
public interface Queue<E> {

	/**
	 * Adds and element to the end of the queue
	 * @param value The value to add to the queue
	 */
    void enqueue(E value);
    
    /**
     * Removes and returns the value from the front of the queue
     * @return The element at the front of the queue
     */
    E dequeue();
    
    /**
     * The element at the front of the queue
     * @return The element at the front of the queue
     */
    E front();
    
    /**
     * Gets the size of the queue
     * @return the size of the queue
     */
    int size();
    
    /**
     * Determines if the list is empty or not
     * @return a boolean for if the list is empty or not
     */
    boolean isEmpty();
}