package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
 * The array based queue that implements queue methods
 * @author colinscanlon
 *
 * @param <E> The general type of the queue
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

	/** The local array of data */
	private E[] data;
	
	/** The index of the front of the queue */
	private int front;
	
	/** The index of the end of the queue */
	private int rear;
	
	/** The size of the queue */
	private int size;
	
	/** The default capacity of the queue */
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * The Constructor for an array based queue with a given capacity
	 * @param initialCapacity The initial capacity of the queue
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedQueue(int initialCapacity)
	{
		data = (E[])(new Object[initialCapacity]);
		size = 0;
	}
	
	/**
	 * The constructor for an array based queue with no given capacity
	 */
	public ArrayBasedQueue()
	{
		this(DEFAULT_CAPACITY);
	}

	@Override
	public void enqueue(E value) {
		ensureCapacity(size);
		data[rear] = value;
		rear = (rear + 1) % data.length;
		size++;
		
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		E x = data[front];
		data[front] = null;
		front = (front + 1) % data.length;
		size--;
		return x;
	}

	@Override
	public E front() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return data[front];
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * A private method to ensure the list has the capacity to add to the list
	 * @param n the current size of the list
	 */
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int n) {		
		if (n == data.length) {
			E[] second = (E[])(new Object[data.length * 2]);
			for (int i = 0; i < n; i++) {
				second[i] = data[(front + i) % data.length];
			}
			front = 0;
			rear = data.length;
			data = second;
		}
		
	}
}
