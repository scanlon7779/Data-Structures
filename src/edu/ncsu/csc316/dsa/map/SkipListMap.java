package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * The Skip List map that extends the AbstractSortedMap class
 * @author colinscanlon
 *
 * @param <K> The keys in the map
 * @param <V> The values in the map
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {
	/** Randomness for sorting the data */
	private Random coinToss;
	
	/** The initial entry */
	private SkipListEntry<K, V> start;
	
	/** The size of the list */
	private int size;
	
	/** The height of the list */
	private int height;

	/**
	 * The constructor with no comparator given
	 */
	public SkipListMap() {
		this(null);
	}

	/**
	 * The constructor with a given comparator
	 * @param compare The given comparator to use
	 */
	public SkipListMap(Comparator<K> compare) {
		super(compare);
		coinToss = new Random();
		// Create a dummy head node for the left "-INFINITY" sentinel tower
		start = new SkipListEntry<K, V>(null, null);
		// Create a dummy tail node for the right "+INFINITY" sentinel tower		
		start.setNext(new SkipListEntry<K, V>(null, null));
		// Set the +INFINITY tower's previous to be the "start" node
		start.getNext().setPrevious(start);
		size = 0;
		height = 0;
	}

    /**
     * Checks if the given entry is a sentinel node or not
     * @param entry The entry to be checked
     * @return if the entry is a sentinel or not
     */
	private boolean isSentinel(SkipListEntry<K, V> entry) {
		return entry.getKey() == null;
	}	

	/**
	 * Looks up the entry with the given key
	 * @param key The key to look up
	 * @return The entry with the given key
	 */
	private SkipListEntry<K, V> lookUp(K key) {
		SkipListEntry<K, V> current = start;
        while (current.below != null) {
            current = current.below;
            while (!isSentinel(current.next) && compare(key, current.next.getKey()) >= 0) {
                current = current.next;
            }
        }
        return current;
	}

	@Override
	public V get(K key) {
		SkipListEntry<K, V> temp = lookUp(key);
		if (temp.getKey() != null && temp.getKey().equals(key)) {
			V org = temp.getValue();
			return org;
		}
		return null;
	}

	/**}
	 * Inserts the given entry after the pervious one
	 * @param prev The previous entry
	 * @param down The entry below 
	 * @param key The key of the new entry
	 * @param value The value of the new entry
	 * @return The previous entry in that spot
	 */
	private SkipListEntry<K, V> insertAfterAbove(SkipListEntry<K, V> prev, SkipListEntry<K, V> down, K key, V value) {
		SkipListEntry<K, V> newEntry = new SkipListEntry<K, V>(key, value);
		newEntry.setBelow(down);
		newEntry.setPrevious(prev);
		if (prev != null) {
			newEntry.setNext(prev.next);
			newEntry.prev.setNext(newEntry);
		}
		if (newEntry.next != null) {
			newEntry.next.setPrevious(newEntry);
		}
		if (down != null) {
			down.setAbove(newEntry);
		}
		return newEntry;
		
	}

	@Override
	public V put(K key, V value) {
		SkipListEntry<K, V> temp = lookUp(key);
		if (temp.getKey() != null && temp.getKey().equals(key)) {
			V org = temp.getValue();
			while (temp != null) {
				temp.setValue(value);
				temp = temp.above;
			}
			return org;
		}
		
		SkipListEntry<K, V> q = null;
		int currentLevel = -1;
		do {
			currentLevel++;
			if (currentLevel >= height) {
				height = height + 1;
				SkipListEntry<K, V> tail = start.next;
				
				start = insertAfterAbove(null, start, null, null);
				insertAfterAbove(start, tail, null, null);
			}
			q = insertAfterAbove(temp, q, key, value);
			while(temp.getAbove() == null) {
				temp = temp.getPrevious();
			}
			temp = temp.above;
		} while (coinToss.nextInt(2) == 1);
		size++;
		return null;
		
	}
	
    @Override
    public V remove(K key) {
        SkipListEntry<K, V> temp = lookUp(key);
        if (temp.getKey() != null && temp.getKey().equals(key)) {
        	V org = temp.getValue();
        	do {
        		SkipListEntry<K, V> prev = temp.getPrevious();
            	SkipListEntry<K, V> next = temp.getNext();
            	prev.setNext(next);
            	next.setPrevious(prev);
            	temp = temp.getAbove();
        	} while (temp != null);
        	size--;
        	return org;
		}
        return null;
    }	

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		List<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
        SkipListEntry<K, V> current = start;
        while(current.below != null){
            current = current.below;
        }
        current = current.next;
        while(!isSentinel(current)) {
            set.addLast(current);
            current = current.next;
        }
        return set;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		SkipListEntry<K, V> cursor = start;
		while( cursor.below != null) {
			cursor = cursor.below;
		}
		cursor = cursor.next;
		while(cursor != null && cursor.getKey() != null) {
			sb.append(cursor.getKey());
			if(!isSentinel(cursor.next)) {
				sb.append(", ");
			}
			cursor = cursor.next;
		}
		sb.append("]");
		
		return sb.toString();
	}

    /**
     * Gets the full string of the skip list
     * @return The full string representation of the list
     */
	public String toFullString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
		SkipListEntry<K, V> cursor = start;
		SkipListEntry<K, V> firstInList = start;
		while( cursor != null) {
			firstInList = cursor;
			sb.append("-INF -> ");
			cursor = cursor.next;
			while(cursor != null && !isSentinel(cursor)) {
				sb.append(cursor.getKey() + " -> ");
				cursor = cursor.next;
			}
			sb.append("+INF\n");
			cursor = firstInList.getBelow();
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * The class for SkipListEntries
	 * @author colinscanlon
	 *
	 * @param <K> The generic type for keys
	 * @param <V> The generic type for values
	 */
	private static class SkipListEntry<K, V> extends MapEntry<K, V> {

		/** The entry above the current one */
        private SkipListEntry<K, V> above;
        
        /** The entry below current */
        private SkipListEntry<K, V> below;
        
        /** The entry before current */
        private SkipListEntry<K, V> prev;
        
        /** The entry after current */
        private SkipListEntry<K, V> next;

        public SkipListEntry(K key, V value) {
            super(key, value);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }
        
        public SkipListEntry<K, V> getBelow() {
            return below;
        }
        
        public SkipListEntry<K, V> getNext() {
            return next;
        }
        
        public SkipListEntry<K, V> getPrevious() {
            return prev;
        }
        
        public SkipListEntry<K, V> getAbove() {
            return above;
        }
        
        public void setBelow(SkipListEntry<K, V> down) {
            this.below = down;
        }
        
        public void setNext(SkipListEntry<K, V> next) {
            this.next = next;
        }
        
        public void setPrevious(SkipListEntry<K, V> prev) {
            this.prev = prev;
        }
        public void setAbove(SkipListEntry<K, V> up) {
            this.above = up;
        }
    }
	
}