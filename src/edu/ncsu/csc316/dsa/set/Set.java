package edu.ncsu.csc316.dsa.set;

/**
 * The interface for the Set ADT
 * @author colinscanlon
 *
 * @param <E> The generic type of the set
 */
public interface Set<E> extends Iterable<E> {
	
	/**
	 * Adds the given value to the set
	 * @param value The value to add
	 */
    void add(E value);
    
    /**
     * Determines if the given value is in the set
     * @param value The value to look for
     * @return If the value is in the set or not
     */
    boolean contains(E value);
    
    /**
     * Removes the given value from the set
     * @param value The value to remove
     * @return The value removed
     */
    E remove(E value);
    
    /**
     * Determines if the set is empty
     * @return If the set is empty or not
     */
    boolean isEmpty();
    
    /**
     * Returns the size of the set
     * @return the size of the set
     */
    int size();
    
    /**
     * Adds a given set to the current set
     * @param other The set to add
     */
    void addAll(Set<E> other);
    
    /**
     * Retains the entire given set
     * @param other The set to retain
     */
    void retainAll(Set<E> other);
    
    /**
     * Removes the given set from the set
     * @param other The set to remove
     */
    void removeAll(Set<E> other);
}