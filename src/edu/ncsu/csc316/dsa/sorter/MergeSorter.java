package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Class for sorting using merge sort
 * @author colinscanlon
 *
 * @param <E> The generic type of the 
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * The constructor with a given comparator
	 * @param comparator The comparator to use in sorting
	 */
	public MergeSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * The constructor with no given comparator
	 */
	public MergeSorter() {
		this(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sort(E[] data) {
		if (data.length < 2) {
			return;
		}
		int mid = data.length / 2;
		E[] left = (E[]) new Comparable[mid];
		for (int i = 0; i < mid; i++) {
			left[i] = data[i];
		}
		int j = 0;
		E[] right = (E[]) new Comparable[data.length - mid];
		for (int i = mid; i < data.length; i++) {
			right[j] = data[i];
			j++;
		}
		sort(left);
		sort(right);
		
		merge(left, right, data);
		
	}
	
	/**
	 * The private helper method for merge sorting
	 * @param left The left half of the array to merge
	 * @param right The right half of the array to merge
	 * @param data The array to place the merged sections of the data into
	 */
	private void merge(E[] left, E[] right, E[] data) {
		int leftIndex = 0;
		int rightIndex = 0;
		
		while (leftIndex + rightIndex < data.length) {
			if (rightIndex == right.length || leftIndex < left.length && 
					compare(left[leftIndex], right[rightIndex]) < 0) {
				data[leftIndex + rightIndex] = left[leftIndex];
				leftIndex += 1;
			} else {
				data[leftIndex + rightIndex] = right[rightIndex];
				rightIndex += 1;
			}
		}
	}
	


}
