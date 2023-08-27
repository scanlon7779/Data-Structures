package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * The Class for a search table map that implements the AbstractSortedMap class
 * @author colinscanlon
 *
 * @param <K> The keys of the map
 * @param <V> The values of the map
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {

	/** The local list of map entries */
	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * Search table constructor with no given comparator
	 */
	public SearchTableMap() {
		this(null);
	}
	
	/**
	 * The Search table constructor with a given comparator
	 * @param compare The comparator to use in sorting
	 */
	public SearchTableMap(Comparator<K> compare) {
		super(compare);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * Gets the index for the given key
	 * @param key The key to look for
	 * @return The index for the given key
	 */
	private int lookUp(K key) {
        if (list.size() > 0) {
        	return binarySearchHelper(0, list.size() - 1, key);
        }
        return -1;
	}

	/**
	 * The helper method for binary search
	 * @param min The minimum value 
	 * @param max The maximum value
	 * @param key The key value for searching
	 * @return The index for the associated key
	 */
	private int binarySearchHelper(int min, int max, K key) {
		if (min > max) {
			return -1 * (min + 1);
		}
		int mid = (max + min) / 2;
		if (compare(list.get(mid).getKey(), key) == 0) {
			return mid;
		} else if (compare(list.get(mid).getKey(), key) > 0) {
			return binarySearchHelper(min, mid - 1, key);
		} else {
			return binarySearchHelper(mid + 1, max, key);
		}
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public V get(K key) {
		int index = lookUp(key);
		if (index < 0 ) {
			return null;
		}
		return list.get(index).getValue();
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayBasedList<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
		for(Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public V put(K key, V value) {
		int index = lookUp(key);
		if (index < 0) {
			list.add(-(index + 1), new MapEntry(key, value));
			return null;
		}
		V val = list.get(index).getValue();
		list.set(index, new MapEntry(key, value));
		return val;
	}

	@Override
	public V remove(K key) {
		int index = lookUp(key);
		if (index < 0) {
			return null;
		}
		return list.remove(index).getValue();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		Iterator<Entry<K, V>> it = list.iterator();
		while(it.hasNext()) {
			sb.append(it.next().getKey());
			if(it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}