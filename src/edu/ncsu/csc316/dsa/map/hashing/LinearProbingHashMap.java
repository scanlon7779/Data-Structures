package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * The class for Linear Probing HashMaps
 * @author colinscanlon
 *
 * @param <K> The keys of the map
 * @param <V> The value of the map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

    /** The table of the maps */
    private TableEntry<K, V>[] table;
    
    /** The size of the map */
    private int size;

    /**
     * Constructor for no given values
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }
    
    /**
     * Constructor with a testing boolean
     * @param isTesting The testing boolean
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }
    
    /**
     * Constructor with a given  capacity
     * @param capacity The capacity of the map
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }
    
    /**
     * Constructor with a given capacity and testing boolean
     * @param capacity The capacity of the map
     * @param isTesting If the map is for testing or not
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	List<Entry<K, V>> list = new ArrayBasedList<Entry<K, V>>();
        for(int i = 0; i < table.length; i++) {
            if(table[i] != null && !table[i].isDeleted()) {
            	 list.addLast(table[i]);
                
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }
    
    // Helper method to determine whether a bucket has an entry or not  
    private boolean isAvailable(int index) {
        return (table[index] == null || table[index].isDeleted());
    }

    // Helper method to find the bucket for an entry;
    // If the entry *is* in the map, returns the index of the bucket
    // If the entry is *not* in the map, returns -(a + 1) to indicate 
    //     that the entry should be added at index a
    private int findBucket(int index, K key) {
    	int avail = -1;
    	int j = index;
    	
    	do {
    		if (isAvailable(j)) {
    			if (avail == -1) {
    				avail = j;
    			}
    			if (table[j] == null) {
    				return -(avail + 1);
    			}
    		} else if (table[j].getKey().equals(key)) {
    			return j;
    		}
    		j = (j + 1) % table.length;
    		
    	} while (j != index);
    	return -(avail + 1);
    }
    
    @Override
    public V bucketGet(int hash, K key) {
        int index = findBucket(hash, key);
        if(index < 0) {
        	return null;
        }
        if(table[index].isDeleted()) {
        	return null;
        }
        return table[index].getValue();
        
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public V bucketPut(int hash, K key, V value) {
        int index = findBucket(hash, key);
        if (index < 0) {
        	table[-index - 1] = new TableEntry(key, value);
        	size++;
        	return null;
        }
        if(table[index].isDeleted()) {
        	table[index] = new TableEntry(key, value);
        	size++;
        	return null;
        }
        V oldValue = table[index].getValue();
        table[index] =  new TableEntry(key, value);
        return oldValue;
    }   

    @Override
    public V bucketRemove(int hash, K key) {
        int index = findBucket(hash, key);
        if(index < 0) {
        	return null;
        }
        if(table[index].isDeleted()) {
        	return null;
        }
        table[index].setDeleted(true);
        size--;
        return table[index].getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }
    
    /**
     * Private class for table entries of the map
     * @author colinscanlon
     *
     * @param <K> The key value of the table entry
     * @param <V> The value of the table entry
     */
    private static class TableEntry<K, V> extends MapEntry<K, V> {

    	/** The boolean for if the entry is deleted or not */
        private boolean isDeleted;

        /**
         * Constructor for table entries
         * @param key The key of the entry
         * @param value The value of the entry
         */
        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        /**
         * If the entry is deleted or not
         * @return if the entry is deleted or not
         */
        public boolean isDeleted() {
            return isDeleted;
        }

        /**
         * Set the deleted status of the entry
         * @param deleted If the entry is deleted or not
         */
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}