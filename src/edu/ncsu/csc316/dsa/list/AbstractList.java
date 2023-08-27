package edu.ncsu.csc316.dsa.list;

/**
 * The abstract class for lists
 * @author colinscanlon
 *
 * @param <E> The element type of the list
 */
public abstract class AbstractList<E> implements List<E> {
	
	@Override
	public void addFirst(E value) {
		add(0, value);
	}
	
	@Override
	public void addLast(E value) {
		add(size(), value);
	}

	/**
	 * Checks the given index to see if it falls in the bounds of the list
	 * @param index the index to check
	 */
	protected void checkIndex(int index)
	{
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}
	
	/**
	 * Checks the index for if its appropriate for adding 
	 * @param index the index to check for add
	 */
	protected void checkIndexForAdd(int index)
	{
		if(index < 0 || index > size())
		{
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}
	
	@Override
	public E first() {
		return get(0);
	}
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public E last() {
		return get(size() - 1);
	}
	
	@Override
	public E removeFirst() {
		return remove(0);
	}
	
	@Override
	public E removeLast() {
		return remove(size() - 1);
	}
}