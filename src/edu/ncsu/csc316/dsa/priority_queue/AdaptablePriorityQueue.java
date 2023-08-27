package edu.ncsu.csc316.dsa.priority_queue;

/**
 * Interface for AdaptablePriorityQueues
 * @author colinscanlon
 *
 * @param <K> The type of keys in the queue
 * @param <V> The type of values in the queue
 */
public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {

	/**
	 * Removes the given entry from the queue
	 * @param entry The entry to remove
	 */
	void remove(Entry<K, V> entry);
	
	/**
	 * Replaces a given entry with the given key
	 * @param entry The entry to replace
	 * @param key The new key of the entry
	 */
	void replaceKey(Entry<K, V> entry, K key);
	
	/**
	 * Replaces the entry with the given values
	 * @param entry The entry to change
	 * @param value The new value of the entry
	 */
	void replaceValue(Entry<K, V> entry, V value);	
}