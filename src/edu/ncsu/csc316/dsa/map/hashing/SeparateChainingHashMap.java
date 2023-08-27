package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.SearchTableMap;

/**
 * The class for Separate Chaining Hash Maps
 * @author colinscanlon
 *
 * @param <K> The key types of the map
 * @param <V> The value types of the map
 */
public class SeparateChainingHashMap<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {

	/** The local held table of maps */
    private Map<K, V>[] table;
    
    /** The size of the map */
    private int size;
    
    /**
     * The default constructor for a hash map
     */
    public SeparateChainingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }
    
    /**
     * The constructor for a hash map used in testing
     * @param isTesting If the map is being tested or not 
     */
    public SeparateChainingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }  	
	
    /**
     * The constructor with a given capacity of the map
     * @param capacity The capacity of the map
     */
    public SeparateChainingHashMap(int capacity) {
        this(capacity, false);
    }    
    
    /**
     * Constructor for a map with a given constructor and is testing
     * @param capacity The capacity of the map
     * @param isTesting if the map is being tested or not
     */
    public SeparateChainingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	 List<Entry<K, V>> list = new SinglyLinkedList<Entry<K, V>>();
         for(int i = 0; i < table.length; i++) {
             if(table[i] != null) {
                 // Each bucket contains a map, so include
                 // all entries in the entrySet for the map
                 // at the current bucket
                 for(Entry<K, V> entry : table[i].entrySet()) {
                     list.addLast(entry);
                 }
             }
         }
         return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        // Example -- change this to whatever map you'd like        
        table = new SearchTableMap[capacity];
        size = 0;
    }

    @Override
    public V bucketGet(int hash, K key) {
        // Get the bucket at the specified index in the hash table      
        Map<K, V> bucket = table[hash];
        // If there is no map in the bucket, then the entry does not exist      
        if(bucket == null) {
            return null;
        }
        // Otherwise, delegate to the existing map's get method to return the value     
        return bucket.get(key);
    }

	@Override
    public V bucketPut(int hash, K key, V value) {
        Map<K, V> bucket = table[hash]; 
        if (bucket == null) {
        	bucket = new SearchTableMap<>();
        	table[hash] = bucket;
        }
        
        int oldSize = bucket.size();
        V answer = bucket.put(key, value);
        int newSize = bucket.size();
        this.size += newSize - oldSize;
        return answer;
    }

    @Override
    public V bucketRemove(int hash, K key) {
        Map<K, V> bucket = table[hash];
        if (bucket == null) return null;
        int oldSize = bucket.size();
        V answer = bucket.remove(key);
        int newSize = bucket.size();
        this.size += newSize - oldSize;
        return answer;
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }
}

