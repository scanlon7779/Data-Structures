package edu.ncsu.csc316.dsa.list;

/**
 * The interface for lists
 * @author colinscanlon
 *
 * @param <E> the generic type for the elements in the list
 */
public interface List<E> extends Iterable<E> {
	
	/**
	 * Adds an element at the given index
	 * @param index The index to add at
	 * @param value the value to add
	 */
    void add(int index, E value);
    
    /**
     * Adds an element at the beginning of the list
     * @param value the value to add
     */
    void addFirst(E value);
    
    /**
     * Adds an element to the end of the list
     * @param value the value to add
     */
    void addLast(E value);
    
    /**
     * A returns the first element in the list
     * @return The first element
     */
    E first();
    
    /**
     * Gets the element at that index
     * @param index the index of the element to retrieve
     * @return the element at the given index
     */
    E get(int index);
    
    /**
     * Returns if the list is empty or not
     * @return if the list is empty
     */
    boolean isEmpty();
    
    /**
     * Returns the last element in the list
     * @return the last element in the list
     */
    E last();
    
    /**
     * Removes the element at the given index
     * @param index The index of the element to remove
     * @return the element removed
     */
    E remove(int index);
    
    /**
     * Removes the first element in the list
     * @return the first element in the list
     */
    E removeFirst();
    
    /**
     * Removes the last element in the list
     * @return the last element in the list
     */
    E removeLast();
    
    /**
     * Sets the element at the given index to the given value
     * @param index The index to set the new value at
     * @param value The new value for the given index
     * @return The element replaced
     */
    E set(int index, E value);
    
    /**
     * Returns the size of the list
     * @return the size of the list
     */
    int size();
}