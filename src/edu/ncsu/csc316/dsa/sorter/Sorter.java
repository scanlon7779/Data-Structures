package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * 
 * @param <E> the generic type of the interface
 */
public interface Sorter<E> {
	
	/**
	 * The method to be used to sort a given array of data
	 * @param data the array to be sorted
	 */
    void sort(E[] data);
}
