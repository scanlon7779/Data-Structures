package edu.ncsu.csc316.dsa.map;

/**
 * The interface for the map ADT
 * @author colinscanlon
 *
 * @param <K> The key values in the map
 * @param <V> The Value associated with each key
 */
public interface Map<K, V> extends Iterable<K> {
	/**
	 * The iterable for each entry
	 * @return The entry
	 */
	Iterable<Entry<K, V>> entrySet();
	
	/**
	 * Gets the value of the given key
	 * @param key The key to get the value for 
	 * @return The value of the key
	 */
	V get(K key);
	
	/**
	 * boolean for is the map is empty or not
	 * @return If the map is empty or not
	 */
	boolean isEmpty();
	
	/**
	 * Adds a key and a given value to the map
	 * @param key The key associated with the entry
	 * @param value The value of the entry
	 * @return The value of the original
	 */
	V put(K key, V value);
	
	/**
	 * Removes the entry with the given key from the map and gives its value
	 * @param key The entry to remove
	 * @return The value of the removed entry
	 */
	V remove(K key);
	
	/**
	 * Gats the size of the map
	 * @return The size of the map
	 */
	int size();
	
	/**
	 * The iterable for the values in the map
	 * @return The value
	 */
	Iterable<V> values();
	
	/**
	 * Inner interface for entries
	 * @author colinscanlon
	 *
	 * @param <K> The key of each entry
	 * @param <V> The value of each entry
	 */
	interface Entry<K, V> {
		/**
		 * Gets the key value of the entry
		 * @return The key of the entry
		 */
		K getKey();
		
		/**
		 * Gets the value of the entry
		 * @return The value of the entry
		 */
		V getValue();
		
		/**
		 * Sets the entry's value to the given value 
		 * @param value The value to update to
		 * @return The origional value of the entry
		 */
		V setValue(V value);
	}
}