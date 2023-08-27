package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Class for HeapPriorityQueues
 * @author colinscanlon
 *
 * @param <K> The type of the keys 
 * @param <V> The type of the values
 */
public class HeapPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {

    /** Array based list for the queue */
	protected ArrayBasedList<Entry<K, V>> list;

	/**
	 * Constructor for HeapPriorityQueue with a given comparator
	 * @param comparator the comparator to use in sorting
	 */
	public HeapPriorityQueue(Comparator<K> comparator) {
		super(comparator);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * The constructor for HeapPriorityQueue with no given comparator
	 */
	public HeapPriorityQueue() {
		this(null);
	}

    //////////////////////////////////////////
    // ADT Operations
    //////////////////////////////////////////

	@Override
	public Entry<K, V> insert(K key, V value) {
		Entry<K, V> temp = createEntry(key, value);
		list.addLast(temp);
		upHeap(list.size( ) - 1);
		return temp;
	}

	@Override
	public Entry<K, V> min() {
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public Entry<K, V> deleteMin() {
		if (list.isEmpty()) {
			return null;
		}
		Entry<K, V> answer = list.get(0);
		swap(0, list.size( ) - 1);
		list.remove(list.size( ) - 1);
		downHeap(0);
		return answer;
	}

	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Up Heaps from the given position
	 * @param index The index to up heap from
	 */
	protected void upHeap(int index) {
		while (index > 0) {
			int p = parent(index);
			if (compare(list.get(index).getKey(), list.get(p).getKey()) >= 0) break;
			swap(index, p);
			index = p;
		}
	}

	/**
	 * Swaps two indexes
	 * @param index1 The first index
	 * @param index2 The second index
	 */
	protected void swap(int index1, int index2) {
		Entry<K, V> temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}

	/**
	 * Down heaps from the given index
	 * @param index The index to down heap from
	 */
	protected void downHeap(int index) {
		 while (hasLeft(index)) {
			 int leftIndex = left(index);
			 int smallChildIndex = leftIndex;
			 if (hasRight(index)) {
				 int rightIndex = right(index);
				 if (compare(list.get(leftIndex).getKey(), list.get(rightIndex).getKey()) > 0)
	                 smallChildIndex = rightIndex;
			 }
			 if (compare(list.get(smallChildIndex).getKey(), list.get(index).getKey()) >= 0)
		         break;
			 swap(index, smallChildIndex);
			 index = smallChildIndex;
		 }
        // The textbook has a non-recursive version of
        //    the recursive algorithms presented in the lecture slides
	}
	
    //////////////////////////////////////////////////
    // Convenience methods to help abstract the math
    // involved in jumping to parent or children
    //////////////////////////////////////////////////	

	/**
	 * Gets the index of the parent node
	 * @param index The index of the current node
	 * @return The index of the parent node
	 */
	protected int parent(int index) {
		return (index - 1) / 2;
	}

	/**
	 * Returns the index of the left node
	 * @param index The index of the current node
	 * @return The index of the left child node 
	 */
	protected int left(int index) {
		return 2 * index + 1;
	}

	/**
	 * returns the index of the right node
	 * @param index The index of the current node
	 * @return The index of the right child node
	 */
	protected int right(int index) {
		return 2 * index + 2;
	}

	/**
	 * Tells if the current node has a left child or not
	 * @param index The current node
	 * @return If the left child exists or not
	 */
	protected boolean hasLeft(int index) {
		return left(index) < list.size();
	}

	/**
	 * Tells if the current node has a right child or not
	 * @param index The current index
	 * @return If the right child exists
	 */
	protected boolean hasRight(int index) {
		return right(index) < list.size();
	}
}
