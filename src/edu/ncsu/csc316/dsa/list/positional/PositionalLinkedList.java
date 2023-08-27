package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The class for PositionalLinkedList that implements PositionalList
 * @author colinscanlon
 *
 * @param <E> the generic type of the list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

	/** The front position of the list */
	private PositionalNode<E> front;
	
	/** The end position of the list */
	private PositionalNode<E> tail;
	
	/** the size of the list */
	private int size;

	/**
	 * The Constructor for PositionalLinkedList that initializes the front and tail positions
	 */
	public PositionalLinkedList() {
		front = new PositionalNode<E>(null);
		tail = new PositionalNode<E>(null, null, front);
		front.setNext(tail);
		size = 0;
	}
	
	/**
	 * The private class for position nods that hold data and order information 
	 * @author colinscanlon
	 *
	 * @param <E> The general element type
	 */
	private static class PositionalNode<E> implements Position<E> {

		/** The elements contained in the position */
        private E element;
        
        /** the next position node in the list */
        private PositionalNode<E> next;
        
        /** The previous node in the list */
        private PositionalNode<E> previous;

        public PositionalNode(E value) {
            this(value, null);
        }

        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        public PositionalNode<E> getNext() {
            return next;
        }

        @Override
        public E getElement() {
            return element;
        }
        
        public void setElement(E element) {
            this.element = element;
        }
    }

	@Override
	public Iterator<E> iterator() {
        // we start at front.getNext() because front is a dummy/sentinel node
        return new ElementIterator(front.getNext());
    }

	@Override
	public Position<E> addAfter(Position<E> p, E value) {
		PositionalNode<E> n = validate(p);
		return addBetween(value, n.getNext(), n);
		
	}

	@Override
	public Position<E> addBefore(Position<E> p, E value) {
		PositionalNode<E> n = validate(p);
		return addBetween(value, n, n.getPrevious());
	}

	@Override
	public Position<E> addFirst(E value) {
		return addBetween(value, front.next, front);
	}

	@Override
	public Position<E> addLast(E value) {
		return addBetween(value, tail, tail.previous);
	}

	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> n = validate(p);
		if (n.next.element == null) {
			return null;
		}
		return n.next;
	}

	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> n = validate(p);
		if(n.previous.element == null) {
			return null;
		}
		return n.previous;
	}

	@Override
	public Position<E> first() {
		if(size == 0) {
			return null;
		}
		return front.next;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> last() {
		if(size == 0) {
			return null;
		}
		return tail.previous;
	}

	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> n = validate(p);
		E x = n.element;
		n.previous.setNext(n.next);
		n.next.setPrevious(n.previous);
		size--;
		return x;
	}

	@Override
	public E set(Position<E> p, E value) {
		PositionalNode<E> n = validate(p);
		E x = n.element;
		n.element = value;
		return x;
	}

	@Override
	public int size() {
		return size;
	}

	
	private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
	
	private Position<E> addBetween(E value, PositionalNode<E> next, PositionalNode<E> prev) {
       PositionalNode<E> n = new PositionalNode<E>(value, next, prev);
       next.previous = n;
       prev.next = n;
       size++;
       return n;
    }
	
	/**
	 * The Position iterator inner class to traverse through the positions of the list
	 * @author colinscanlon
	 *
	 */
	private class PositionIterator implements Iterator<Position<E>> {

		/** The current position of the iterator */
        private Position<E> current;
        
        /** a boolean for if you can remove or not */
        private boolean removeOK;

        public PositionIterator(PositionalNode<E> start) {
            this.current = start.previous;
        }

        @Override
        public boolean hasNext() {
        	PositionalNode<E> n = validate(current);
        	return n.next.element != null;
        }

        @Override
        public Position<E> next() {
        	PositionalNode<E> n = validate(current);
        	if (n.next.element == null) {
	    		throw new NoSuchElementException();
	    	}
        	removeOK = true;
        	current = n.next;
        	return current;
        }

        @Override
        public void remove() {
            if (removeOK) {
            	PositionalLinkedList.this.remove(current);
            	removeOK = false;
            } else {
            	throw new IllegalStateException();
            }
            
        }
    }
	
	/**
	 * The inner-class of element iterator that allows for the traversal of specific elements 
	 * in the list rather than positions
	 * @author colinscanlon
	 *
	 */
	private class ElementIterator implements Iterator<E> {

		/** the iterator of element iterator */
        private Iterator<Position<E>> it;

        public ElementIterator(PositionalNode<E> start) {
            it = new PositionIterator(start);
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next().getElement();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }
	
	/**
	 * The shell class PositionIterable to be used by position
	 * @author colinscanlon
	 *
	 */
    private class PositionIterable implements Iterable<Position<E>> {
        
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator(front.getNext());
        }
    }

}
