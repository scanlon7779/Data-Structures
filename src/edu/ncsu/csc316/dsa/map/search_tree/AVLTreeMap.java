package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * The AVL tree map implementation of the BinarySearchTreeMap
 * @author colinscanlon
 *
 * @param <K> The key value of the tree map
 * @param <V> The value of the tree map
 */
public class AVLTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * Constructor for AVLTreeMap with no given comparator
	 */
	public AVLTreeMap() {
		super(null);
	}

	/**
	 * Constructor for AVLTreeMap with a given comparator
	 * @param compare the comparator to use in the tree map
	 */
	public AVLTreeMap(Comparator<K> compare) {
		super(compare);
	}

    /**
     * Helper rebalance method for the AVLTreeMap
     * @param p The position to rebalance around
     */
	private void rebalance(Position<Entry<K, V>> p) {
		int oldHeight, newHeight;
		do {
			oldHeight = getProperty(p);
			if(!isBalanced(p)) {
				p = restructure(tallerChild(tallerChild(p)));
				recomputeHeight(left(p));
				recomputeHeight(right(p));
			}
			recomputeHeight(p);
		    newHeight = getProperty(p);
		    p = parent(p);
		} while (oldHeight != newHeight && p != null);
	}
	
    /**
     * Returns the child of p that has the greater height
     * @param p The parent position to look from
     * @return The child of p with the greater height
     */
	private Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
		if (getProperty(left(p)) > getProperty(right(p))) {
			return left(p);
		}
		if (getProperty(left(p)) < getProperty(right(p))) {
			return right(p);
		}
		if(isRoot(p)) {
			return left(p);
		}
		if(p == left(parent(p))) {
			return left(p);
		} else {
			return right(p);
		}
	}	
	/**
	 * Determines if the tree is balanced
	 * @param p The position to evaluate
	 * @return if the tree is balanced or not
	 */
	private boolean isBalanced(Position<Entry<K, V>> p) {
		return Math.abs(getProperty(left(p)) - getProperty(right(p))) <= 1;
	}
	
	/**
	 * Recomputes the height of the tree
	 * @param p The position to compute the height of
	 */
	private void recomputeHeight(Position<Entry<K, V>> p) {
		int h = 1 + Math.max(getProperty(left(p)), getProperty(right(p)));
		setProperty(p, h);
	}

	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		rebalance(node);
	}

	@Override
	protected void actionOnDelete(Position<Entry<K, V>> node) {
		if(!isRoot(node))
		{
			rebalance(parent(node));
		}
	}
}
