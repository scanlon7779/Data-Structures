package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author colinscanlon
 * 
 * @param <E> the generic type of element to bve sorted
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
	/**
	 * Empty constructor for insertion sorter
	 */
    public InsertionSorter() {
        this(null);
    }
    
    /**
     * The comparable constructor for insertion sorter
     * @param comparator the comparator to be passed in
     */
    public InsertionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
    
    /**
     * Method for sorting the data in a given array using the insertion sorter technique
     * @param data the array to be sorted
     */
    public void sort(E[] data) {
		for (int i = 1; i < data.length; i++) {
			E x = data[i];
			int j = i - 1;
			while (j >= 0 && compare(data[j], x) > 0) {
				data[j + 1]  = data[j];
				j = j - 1;
			}
			data[j + 1] = x;
		}
		
	}
    
}

