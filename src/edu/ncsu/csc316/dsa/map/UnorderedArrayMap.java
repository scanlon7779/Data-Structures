package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * An unordered array implementation of map 
 * @author colinscanlon
 *
 * @param <K> The key types of the map
 * @param <V> The Value types of the map
 */
public class UnorderedArrayMap<K, V> extends AbstractMap<K, V> {

	/** array-based list of entries */
	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * Constructor for the UnorderedArrayMap
	 */
	public UnorderedArrayMap() {
		this.list = new ArrayBasedList<Entry<K, V>>();
	}
	
	// LookUp is a core behavior of all maps
    // This lookup should perform a sequential search
    // and return the index where the entry
    // is located. If the entry is not in the map, return -1
	private int lookUp(K key)
	{
		KeyIterator k = new KeyIterator(list.iterator());
		int i = 0;
		while (k.hasNext()) {
			if (k.next().equals(key)) {
				return i;
			}
			i++;
			
		}
		return -1;
	}
	
	@Override
	public V get(K key) {
		int index = lookUp(key);
		if (index == -1) {
			return null;
		}
		return transpose(index);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public V put(K key, V value) {
		int index = lookUp(key);
		if (index == -1) {
			list.addFirst(new MapEntry(key, value));
			return null;
		}
		V val = transpose(index);
		if (index > 0) {
			list.set(index - 1, new MapEntry(key, value));
			return val;
		}
		list.set(index, new MapEntry(key, value));
		return val;
		
	}
	
	@Override
	public V remove(K key) {
        int index = lookUp(key);
        if (index == -1) {
        	return null;
        }
        return list.remove(index).getValue();
	}

	@Override
	public int size() {
		return list.size();
	}

	
	private V transpose(int index)
	{
		V val = list.get(index).getValue();
		if (index > 0) {
			Entry<K, V> hold = list.get(index);
			list.set(index, list.get(index - 1));
			list.set(index - 1, hold);
		}
		
		return val;
		
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return list;
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