package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * The Unordered linked map that implements AbstractMap
 * @author colinscanlon
 *
 * @param <K> The general type of keys
 * @param <V> The general type of values 
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/** The list of map entries */
	private PositionalList<Entry<K, V>> list;
	
	/**
	 * The constructor for the map
	 */
	public UnorderedLinkedMap() {
		this.list = new PositionalLinkedList<Entry<K, V>>();
	}
	
	/**
	 * The private lookUp method
	 * @param key The key value to look for
	 * @return The position of the key
	 */
	private Position<Entry<K, V>> lookUp(K key)
	{
		Position<Entry<K, V>> name = list.first();
		for (int i = 0; i < list.size(); i++) {
			if (name.getElement().getKey().equals(key)) {
				return name;
			}
			name = list.after(name);
		}
		
		
		return null;
		
	}

	@Override
	public V get(K key) {
		Position<Entry<K, V>> p = lookUp(key);
		if (p == null) {
			return null;
		}
		return moveToFront(p);
	}
	
	private V moveToFront(Position<Entry<K, V>> position) {
		Entry<K, V> val = list.remove(position);
		list.addFirst(val);
		return val.getValue();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public V put(K key, V value) {
		Position<Entry<K, V>> p = lookUp(key);
		if (p == null) {
			list.addFirst(new MapEntry(key, value));
			return null;
		}
		V val = list.remove(p).getValue();
		list.addFirst(new MapEntry(key, value));
		return val;
		
	}
	
	@Override
	public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       if (p == null) {
    	   return null;
       }
       return list.remove(p).getValue();
	}
	
	@Override
	public int size() {
		return list.size();
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		PositionalList<Entry<K, V>> set = new PositionalLinkedList<Entry<K, V>>();
		for(Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
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