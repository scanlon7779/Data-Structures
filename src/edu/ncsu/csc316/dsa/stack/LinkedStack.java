package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * A singly linked list that implements stack methods
 * @author colinscanlon
 *
 * @param <E> The general type of the stack
 */
public class LinkedStack<E> extends AbstractStack<E> {
	
	/** The singly linked list held locally */
	private SinglyLinkedList<E> list;
	
	/**
	 * Constructor for linked stack
	 */
	public LinkedStack()
	{
		list = new SinglyLinkedList<E>();
	}

	@Override
	public void push(E value) {
		list.addFirst(value);
		
	}

	@Override
	public E pop() {
		if (list.isEmpty()) {
			throw new EmptyStackException();
		}
		return list.removeFirst();
	}

	@Override
	public E top() {
		if (list.isEmpty()) {
			throw new EmptyStackException();
		}
		return list.first();
	}

	@Override
	public int size() {
		return list.size();
	}
	
}