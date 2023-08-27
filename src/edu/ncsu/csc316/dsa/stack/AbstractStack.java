package edu.ncsu.csc316.dsa.stack;

/**
 * The abstract class for stacks containing the is empty method
 * @author colinscanlon
 *
 * @param <E> The general type of the stacks
 */
public abstract class AbstractStack<E> implements Stack<E> {
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}