package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A list that holds elements in an array based format
 * @author colinscanlon
 *
 * @param <E> The generic type of the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

	/** The default capacity of the list */
	private final static int DEFAULT_CAPACITY = 10;
	
	/** The array contained and manipulated by this class */
	private E[] data;

	/** the size of the array */
	private int size;

	/**
	 * Constructor for the array list with no specified capacity
	 */
	public ArrayBasedList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * The constructor for an array list with a given capacity
	 * @param capacity The initial capacity of the list
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList(int capacity) {
		data = (E[]) (new Object[capacity]);
		size = 0;
	}

	@Override
	public void add(int index, E value) {
		checkIndexForAdd(index);
		ensureCapacity(size + 1);
		if (index == size) {
			data[index] = value;
			size++;
		} else {
			for(int i = size - 1; i >= index; i--) {
				data[i + 1] = data[i];
			}
			data[index] = value;
			size++;
		}
		
		
	}

	@Override
	public E get(int index) {
		checkIndex(index);
		return data[index];
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		E r = data[index];
		if (index == size - 1) {
			data[size - 1] = null;
			size--;
			return r;
		}
		for (int i = index; i < size - 1; i++) {
			data[i] = data [i + 1];
		}
		data[size - 1] = null;
		size--;
		return r;
	}

	@Override
	public E set(int index, E value) {
		checkIndex(index);
		E r = data[index];
		data[index] = value;
		return r;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
	/**
	 * A private method to ensure there is enough capacity in the list
	 * @param minCapacity The smallest new capacity for the next list
	 */
	private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity * 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }
	
	/**
	 * The iterator class for array based list that traverses through the list 
	 * @author colinscanlon
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		/** the current position of the iterator */
	    private int position;
	    
	    /** The boolean for if it is ok to remove an element */
	    private boolean removeOK;

	    public ElementIterator() {
	        position = -1;
	    }

	    public boolean hasNext() {
	        return position < size - 1;
	    }

	    public E next() {
	    	if (position == size - 1) {
	    		throw new  NoSuchElementException();
	    	}
	    	removeOK = true;
	        position++;
	        return data[position];
	    }
	        
	    public void remove() {
	    	if (removeOK) {
	    		ArrayBasedList.this.remove(position);
	    		removeOK = false;
	    		position--;
	    	} else {
	    		throw new IllegalStateException();
	    	}
	    }
	}

	
}
