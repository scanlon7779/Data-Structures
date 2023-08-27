package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 * @author colinscanlon
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
    /**
     * The comparable constructor for SelectionSorter
     * @param comparator the comparator to be passed in
     */
    public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
    /**
     * The empty constructor for selection sorter
     */
    public SelectionSorter() {
    	this(null);
    }


    /**
     * The sort method for sorting a given array in the selection sort technique
     * @param data the array to be sorted
     */
    public void sort(E[] data) {
        for (int i = 0; i < data.length; i++) {
        	int min = i;
        	for (int j = i + 1; j < data.length; j++) {
        		if (compare(data[j], data[min]) < 0) {
        			min = j;
        		}
        	}
        	if (i != min) {
        		E x = data[i];
        		data[i] = data[min];
        		data[min] = x;
        	}
        }
    }
    
}
