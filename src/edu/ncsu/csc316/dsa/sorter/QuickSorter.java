package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
import java.util.Random;

/**
 * The class for sorting using the quicksort method
 * @author colinscanlon
 *
 * @param <E> The general type of the elements
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/** The creation of a local pivot selector beginning with the first element **/
	public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
	
	/** The creation of a local selector beginning with the last element */
    public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
    
    /** The creation of a local selector with the middle element */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    
    /** The creation of a local selector with a random number */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();
    
    /** The selector to use */
    private PivotSelector selector;
    
    /**
     * The Constructor for quicksort with a given comparator and selector
     * @param comparator The given comparator 
     * @param selector The given selector for sorting
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }
    
    /**
     * The constructor for quicksort with no given selector
     * @param comparator the comparator to use in sorting
     */
	public QuickSorter(Comparator<E> comparator) {
		super(comparator);
		setSelector(null);
	}    
    
	/**
	 * The constructor with a given selector
	 * @param selector The selector to be used in sorting
	 */
    public QuickSorter(PivotSelector selector) {
        super(null);
        setSelector(selector);
    }
    /**
     * The undefined constructor for Quicksort
     */
    public QuickSorter() {
        super(null);
        setSelector(null);
    }
    
    /** 
     * Sets the selector to use in the sorting
     * @param selector The selector to be used
     */
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            selector = new RandomElementSelector();
        }
        this.selector = selector;
    }
    
    @Override
	public void sort(E[] data) {
		quickSort(data, 0, data.length - 1);
		
	}
    
    /**
     * The private recursive method for sorting with quicksort
     * @param data The array to be sorted
     * @param low The lowest index of the array
     * @param high The highest index to compare
     */
    private void quickSort(E[] data, int low, int high) {
    	if (low < high) {
    		int pivotLocation = partition(data, low, high);
    		quickSort(data, low, pivotLocation - 1);
    		quickSort(data, pivotLocation + 1, high);
    	}
    }
    
    /**
     * The private partition method that determines a partition based on the selector
     * @param data The array being sorted
     * @param low The lowest index to sort
     * @param high The highest index to sort
     * @return The integer of the pivot
     */
    private int partition(E[] data, int low, int high) {
    	int pivotIndex = selector.selectPivot(low, high);
    	swap(data, pivotIndex, high);
    	return partitionHelper(data, low, high);
    	
    }
    
    /**
     * The partition helper to swap the data around the pivot
     * @param data The array being sorted
     * @param low The lowest index of the array
     * @param high The highest index of the array
     * @return The last index being sorted
     */
    private int partitionHelper(E[] data, int low, int high) {
    	E pivot = data[high];
    	int index = low;
    	for (int j = low; j < high; j++) {
    		if (compare(data[j], pivot) < 0) {
    			swap(data, index, j);
    			index++;
    		}
    	}
    	swap(data, index, high);
    	return index;
    }
    
    /**
     * Swaps the data at the index and high in the given array
     * @param data The array to swap data
     * @param index The index of one element to switch
     * @param high The higher index to swap with
     */
    private void swap(E[] data, int index, int high) {
    	E hold = data[index];
    	data[index] = data[high];
    	data[high] = hold;
    }
    
    /**
     * The private interface for PivotSelector that holds the method for setPivot
     * @author colinscanlon
     *
     */
    private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * @param low - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
    
    /**
     * The innerclass that selects with the first element
     * @author colinscanlon
     *
     */
    public static class FirstElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return low;
		}

	}
    
    /**
     * The innerclass that selects with the last element
     * @author colinscanlon
     *
     */
    public static class LastElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return high;
		}

	}
    
    /**
     * The selector that selects with the middle element
     * @author colinscanlon
     *
     */
    public static class MiddleElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return (low + high) / 2;
		}

	}
    
    /**
     * The selector that selects with a random element
     * @author colinscanlon
     *
     */
    public static class RandomElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			Random rand = new Random();
			return rand.nextInt(high - low + 1) + low;
		}

	}

    
}