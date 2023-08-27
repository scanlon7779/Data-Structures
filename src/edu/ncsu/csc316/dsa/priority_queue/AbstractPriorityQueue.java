package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * Abstract class for priority queues
 * @author colinscanlon
 *
 * @param <K> The keys of the queue
 * @param <V> The values of the queue
 */
public abstract class AbstractPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {

	/** the comparator for sorting */
	private Comparator<K> comparator;

	/**
	 * Constructor for an abstrcat queue with a given comparator
	 * @param c The comparator to use for sorting
	 */
	public AbstractPriorityQueue(Comparator<K> c) {
		setComparator(c);
	}
	
	/**
	 * Sets the comparator of the queue
	 * @param c The comparator to use
	 */
	private void setComparator(Comparator<K> c) {
		if(c == null) {
			c = new NaturalOrder();
		}
		comparator = c;
	}

	/**
	 * The natural order comparator class
	 * @author colinscanlon
	 *
	 */
	public class NaturalOrder implements Comparator<K> {
		
		/**
		 * The compare method that compares two objects
		 * @param first the first object to compare
		 * @param second The second object to compare
		 * 
		 * @return The comparison value of the two objects
		 */
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}

	/**
	 * Compares two key values
	 * @param data1 The first key to compare
	 * @param data2 The second key to compare
	 * @return The comparison values of the two keys
	 */
	public int compare(K data1, K data2) {
		return comparator.compare(data1, data2);
	}
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

    /**
     * class for PQ entries
     * @author colinscanlon
     *
     * @param <K> The keys of the entry
     * @param <V> The value of the entry
     */
	public static class PQEntry<K, V> implements Entry<K, V> {

		/** The key of the entry */
		private K key;
		
		/** The value of the entry */
		private V value;

		/**
		 * Constructor for a new PQEntry
		 * @param key The key of the entry
		 * @param value The value of the entry
		 */
		public PQEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		/**
		 * Sets the key to the given key
		 * @param key The new key of the entry
		 */
		public void setKey(K key) {
			this.key = key;
		}

		/**
		 * Sets the value to the given value
		 * @param value The new value of the entry
		 */
		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
	}
	
    /**
     * Protected class for creating new entries
     * @param key The key of the new entry
     * @param value The value of the new entry
     * @return The new entry
     */
	protected Entry<K, V> createEntry(K key, V value) {
		return new PQEntry<K, V>(key, value);
	}
}