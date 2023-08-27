package edu.ncsu.csc316.dsa.priority_queue;

/**
 * The interface for priority queues
 * @author colinscanlon
 *
 * @param <K> The generic key type
 * @param <V> The generic value type
 */
public interface PriorityQueue<K, V> {

	/**
	 * Inner interface for queue entries
	 * @author colinscanlon
	 *
	 * @param <K> The type of the keys
	 * @param <V> The type of the values
	 */
	interface Entry<K, V> {
		/**
		 * Gets the key of the entry
		 * @return The entry's key
		 */
		K getKey();
		
		/**
		 * Gets the value of the entry
		 * @return The entry's value
		 */
		V getValue();
	}
	
	/**
	 * Inserts the given key and value to the queue
	 * @param key The key of the entry
	 * @param value The value of the entry
	 * @return The new entry
	 */
	Entry<K, V> insert(K key, V value);
	
	/**
	 * Gets the minimum entry in the queue
	 * @return The minimum entry in the queue
	 */
	Entry<K, V> min();
	
	/**
	 * Deletes the minimum entry in the queue
	 * @return The entry deleted
	 */
	Entry<K, V> deleteMin();
	
	/**
	 * Gets the size of the queue
	 * @return The size of the queue
	 */
	int size();
	
	/**
	 * Tells if the queue is empty
	 * @return If the queue is empty or not
	 */
	boolean isEmpty();
}
