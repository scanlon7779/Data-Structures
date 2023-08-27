package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Sorts a given list of comparable elements using the bubble sorting technique
 * @author colinscanlon
 *
 * @param <E> The objects to be sorted
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Empty constructor for BubbleSorter
	 */
	public BubbleSorter() {
        this(null);
    }
	
	/**
	 * Comparator constructor for BubbleSorter
	 * @param comparator the comparator to be passed in
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Sorts a given array of data using the bubble sort method
	 * @param data the array to sort
	 */
	public void sort(E[] data) {
		boolean r = true;
		while (r) {
			r = false;
			for (int i = 1; i < data.length; i++) {
				if (compare(data[i], data[i - 1]) < 0) {
					E x = data[i - 1];
					data[i - 1] = data[i];
					data[i] = x;
					r = true;
				}
			}
		}
		
	}

}
