package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

/**
 * The abstract map that implements Map
 * @author colinscanlon
 *
 * @param <K> The keys of the map
 * @param <V> The value of the map
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

	/**
	 * Inner class of map entries that implements entry
	 * @author colinscanlon
	 *
	 * @param <K> The keys of the entry
	 * @param <V> The values if the entry
	 */
	protected static class MapEntry<K, V> implements Entry<K, V> {

		/** The key of the entry */
		private K key;
		
		/** The value of the entry */
		private V value;

		/**
		 * Constructor for a map entries
		 * @param key The key of the entry
		 * @param value The value of the entry
		 */
		public MapEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		private void setKey(K key) {
			this.key = key;
		}

		@Override
		public V setValue(V value) {
			V original = this.value;
			this.value = value;
			return original;
		}
	}

	/**
	 * The iterator for key values
	 * @author colinscanlon
	 *
	 */
	protected class KeyIterator implements Iterator<K> {
		/** Local iterator for the KeyIterator */
        private Iterator<Entry<K, V>> it;
        
        /**
         * Constructor for the key iterator
         * @param iterator The iterator of the map 
         */
        public KeyIterator(Iterator<Entry<K, V>> iterator) {
            it = iterator;
        }
        
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public K next() {
            return it.next().getKey();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
	}
	
	/**
	 * The iterator for values
	 * @author colinscanlon
	 *
	 */
	protected class ValueIterator implements Iterator<V> {
		/** Local iterator for the KeyIterator */
		private Iterator<Entry<K, V>> it;
	        
		/**
		 * The value iterator constructor
		 * @param iterator The iterator of the map
		 */
	    public ValueIterator(Iterator<Entry<K, V>> iterator) {
	            it = iterator;
	    }
	        
	    @Override
	    public boolean hasNext() {
	        return it.hasNext();
	    }

	    @Override
	    public V next() {
	        return it.next().getValue();
	    }
	        
	    @Override
	    public void remove() {
	        throw new UnsupportedOperationException("The remove operation is not supported yet.");
	    }	
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	@Override
	public Iterator<K> iterator() {
		return new KeyIterator(entrySet().iterator());
	}
	
	@Override
	public Iterable<V> values() {
		return new ValueIterable();
	}
	
	/**
	 * The Value iterable class
	 * @author colinscanlon
	 *
	 */
	private class ValueIterable implements Iterable<V> {
		@Override
        public Iterator<V> iterator() {
            return new ValueIterator(entrySet().iterator());
        }
	}
	
}