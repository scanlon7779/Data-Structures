package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Super class for comparison sorter classes
 * @author colinscanlon
 *
 * @param <E> the generic type to be passed in
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/** the local comparator */
    private Comparator<E> comparator;
    
    /**
     * Constructor for the abstract comparator
     * @param comparator the comparator to be passed in
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /**
     * Sets the comparator for the class
     * @param comparator the comparator to be set
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
           comparator = new NaturalOrder();
        }
        this.comparator = comparator;
    }     
    
    /**
     * inner-class of neutral comparator
     * @author colinscanlon
     *
     */
    private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /**
     * compares two elements and dewtermines their ordering
     * @param data1 the first element to be compared
     * @param data2 the second element to be compared
     * @return the int representation of their intended ordering
     */
    public int compare(E data1, E data2) {
        return comparator.compare(data1,  data2);
    }
    
    
}