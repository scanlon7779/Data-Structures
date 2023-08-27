package edu.ncsu.csc316.dsa.map.hashing;

import java.util.Random;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.AbstractMap;
/**
 * The abstract class for an AbstractHashMap that implements AbstractMap
 * @author colinscanlon
 *
 * @param <K> The keys of the map
 * @param <V> The values of the map
 */
public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {

    /** An initial capacity for the hash table */
    protected static final int DEFAULT_CAPACITY = 17;
    
    /** The max load factor of the map */
    private static final double MAX_LOAD_FACTOR = 0.5;
    
    /** The default prime number */
    protected static final int DEFAULT_PRIME = 109345121;
    
    // Alpha and Beta values for MAD compression
    // This implementation uses a variation of the MAD method
    // where h(k) = ( (alpha * f(k) + beta) % prime) % capacity
    /** The alpha value for MAD compression */
    private long alpha;
    
    /** The bets value for MAD compression */
    private long beta;
    
    /** The prime number to use for compression strategy */
    private int prime;
    
    /**
     * The constructor with an istesting flag to avoid randomness in testing
     * @param capacity The capacity of the list
     * @param isTesting The is testing flag
     */
    public AbstractHashMap(int capacity, boolean isTesting) {
        if(isTesting) {
            alpha = 1;
            beta = 1;
            prime = 7;
        } else {
            Random rand = new Random();
            alpha = rand.nextInt(DEFAULT_PRIME - 1) + 1;
            beta = rand.nextInt(DEFAULT_PRIME);
            prime = DEFAULT_PRIME;
        }
        createTable(capacity);
    }
    
    /**
     * The compression method to resize the list
     * @param key The key to compress around
     * @return the new placement of the key
     */
    private int compress(K key) {
        return (int)((Math.abs(key.hashCode() * alpha + beta) % prime) % capacity());
    }

    @Override
    public V put(K key, V value) {
        V ret = bucketPut(compress(key), key, value);
        if( (double)size() / capacity() > MAX_LOAD_FACTOR){
            resize(2 * capacity() + 1);
        }
        return ret;
    }
    
    @Override
    public V get(K key) {
        return bucketGet(compress(key), key);
    }

    @Override
    public V remove(K key) {
        return bucketRemove(compress(key), key);
    }
    
    /**
     * The resize of the list with a new capacity
     * @param newCapacity the new capacity of the map
     */
    private void resize(int newCapacity) {
        List<Entry<K, V>> list = new ArrayBasedList<Entry<K, V>>();
        for(Entry<K, V> entry : entrySet()) {
            list.addLast(entry);
        }
        createTable(newCapacity);
        for(Entry<K, V> entry : list) {
            put(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * Returns the capacity of the map
     * @return The capacity of the map
     */
    protected abstract int capacity();
    
    /**
     * Creates a table with the given capacity
     * @param capacity The capacity of the table
     */
    protected abstract void createTable(int capacity);
    
    /**
     * Gets the value of a bucket with the given key and hash
     * @param hash The hash value to look for
     * @param key The key value to look for
     * @return The value of the given bucket
     */
    protected abstract V bucketGet(int hash, K key);
    
    /**
     * puts an entry in the bucket that corresponds to the hash an given key
     * @param hash The hash value to look for
     * @param key The key to look for
     * @param value The value to put
     * @return The value that was placed
     */
    protected abstract V bucketPut(int hash, K key, V value);
    
    /**
     * Removes a bucket with the given key and has value
     * @param hash The hash value to remove
     * @param key The key to remove
     * @return The value removed
     */
    protected abstract V bucketRemove(int hash, K key);
}