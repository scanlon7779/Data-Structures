package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A linked list data structure that extends abstract list
 * @author colinscanlon
 *
 * @param <E> the generic type of the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

	/** The front node of the linked list */
	private LinkedListNode<E> front;
	
	/** The end node of the linked list */
	private LinkedListNode<E> tail;
	
	/** The size of the list */
	private int size;
	
	/**
	 * The inner-class for list node that holds data and the surrounding nodes in the list
	 * @author colinscanlon
	 *
	 * @param <E> The generic type of the data in the list
	 */
    private static class LinkedListNode<E> {
        
    	/** The data from the list node */
        private E data;
        
        /** The next linked list node in the list */
        private LinkedListNode<E> next;
        
        public LinkedListNode(E data) {
        	this.data = data;
        	next = null;
        }
        
        public LinkedListNode(E data, LinkedListNode<E> next) {
        	setElement(data);
        	this.next = next;
        }
        
        public LinkedListNode<E> getNext() {
        	return next;
        }
        
        public E getElement() {
        	return data;
        }
        
        public void setNext(LinkedListNode<E> next) {
        	this.next = next;
        }
        
        public void setElement(E data) {
        	this.data = data;
        }
    }
	
	/**
	 * The constructor for the linked list
	 */
	public SinglyLinkedList() {
		front = new LinkedListNode<E>(null);
		tail = null;
		size = 0;
	}

	@Override
	public void add(int index, E value) {
		checkIndexForAdd(index);
		if (index == 0) {
			LinkedListNode<E> n = new LinkedListNode<E>(value, front.next);
			front.setNext(n);
			if(index == size) {
				tail = n;
			}
			size++;
		} else {
			LinkedListNode<E> current = front;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			LinkedListNode<E> n = new LinkedListNode<E>(value, current.next);
			current.next = n;
			if (index == size) {
				tail = n;
			}
			size++;
		}
		
		
		
	}

	@Override
	public E get(int index) {
		checkIndex(index);
		LinkedListNode<E> current = front.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		
		return current.data;
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		if(index == 0) {
			E x = front.next.data;
			front.next = front.next.next;
			if (index == size - 1) {
				tail = null;
			}
			size--;
			return x;
		}
		
		LinkedListNode<E> current = front.next;
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		if (index == size - 1) {
			tail = current;
			E x = current.next.data;
			current.next = null;
			size--;
			return x;
		} else {
			E x = current.next.data;
			current.next = current.next.next;
			size--;
			return x;
		}
		
	}

	@Override
	public E set(int index, E value) {
		checkIndex(index);
		LinkedListNode<E> current = front.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		
		E x = current.data;
		current.data = value;
		return x;
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
    public E last() {
        return tail.getElement();
    }
    
    @Override
    public void addLast(E value) {
    	if (tail != null) {
    	    tail.next = new LinkedListNode<E>(value);
            tail = tail.next;
            size++;
    	} else {
    		add(size, value);
    	}
    }

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator(front.getNext());
	}
	
	/**
	 * The iterator for singly linked list that allows traversal of the linked list
	 * @author colinscanlon
	 *
	 */
	private class ElementIterator implements Iterator<E> {
	    /** Keep track of the next node that will be processed */
	    private LinkedListNode<E> current;
	    
	    /** Keep track of the node that was processed on the last call to 'next' */
	    private LinkedListNode<E> previous;
	    /** Keep track of the previous-previous node that was processed */
	    private LinkedListNode<E> previousPrevious;
	    
	    /** boolean for if we can remove an element of the list */
	    private boolean removeOK;

	    public ElementIterator(LinkedListNode<E> start) {
	        this.current = start;
	        this.previous = front;
	        this.previousPrevious = front;
	    }

	    public boolean hasNext() {
	        return previous.next != null;
	    }

	    public E next() {
	    	if (current == null) {
	    		throw new NoSuchElementException();
	    	}
	    	E x = current.data;
	    	previousPrevious = previous;
	    	previous = current;
	    	current = current.next;
	    	removeOK = true;
	        return x;
	    }
	        
	    public void remove() {
	        if (removeOK) {
	        	previousPrevious.next = current;
	        	previous = previousPrevious;
	        	removeOK = false;
	        	size--;
	        } else {
	        	throw new IllegalStateException();
	        }
	    }
	}
	
	
}