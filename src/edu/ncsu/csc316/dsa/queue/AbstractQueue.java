package edu.ncsu.csc316.dsa.queue;

/**
 * The abstract class for queues that has the common method isEmpty
 * @author colinscanlon
 *
 * @param <E> The generic type of elements in the queue
 */
public abstract class AbstractQueue<E> implements Queue<E> {

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}