package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;

/**
 * The abstract class for sorted maps
 * @author colinscanlon
 *
 * @param <K> The generic value of the keys
 * @param <V> The generic value of the values
 */
public abstract class AbstractSortedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {

    /** The comparator to be used in the class */
	private Comparator<K> compare;

	/**
	 * The constructor for AbstractSortedMap
	 * @param compare the comparator to be used
	 */
	public AbstractSortedMap(Comparator<K> compare) {
		if (compare == null) {
			this.compare = new NaturalOrder();
		} else {
			this.compare = compare;
		}
	}

	/**
	 * The compare method for comparing two keys
	 * @param key1 The first key to compare
	 * @param key2 The second key to compare
	 * @return The int for how they should be ordered
	 */
	public int compare(K key1, K key2) {
		return compare.compare(key1, key2);
	}

	/**
	 * The natural order comparator that uses the keys natural compare to method
	 * @author colinscanlon
	 *
	 */
	private class NaturalOrder implements Comparator<K> {
		
		/**
		 * Compare method using the inherent compareTo method
		 * @param first The first key to compare
		 * @param second The second key to compare
		 * @return The int for how they should be ordered
		 */
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}
}
