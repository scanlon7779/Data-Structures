package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * Class for SplayTree implementation of BinarySearchTrees
 * @author colinscanlon
 *
 * @param <K> The key value of the map tree
 * @param <V> The values of the map tree
 */
public class SplayTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * Constructor for a splay tree with no given comparator
	 */
	public SplayTreeMap() {
		super(null);
	}

	/**
	 * Constructor for a splay tree with a given comparator
	 * @param compare The comparator to use
	 */
	public SplayTreeMap(Comparator<K> compare) {
		super(compare);
	}

	/**
	 * Private method for splaying 
	 * @param p The position to splay
	 */
	private void splay(Position<Entry<K, V>> p) {
		while (!isRoot(p)) {
			Position<Entry<K, V>> parent = parent(p);
			Position<Entry<K, V>> grand = parent(parent);
			if (grand == null) {
				rotate(p);
			} else if ((parent == left(grand)) == (p == left(parent))) {
				rotate(parent);
				rotate(p);
				
			} else {
				rotate(p);
				rotate(p);
			}
		}
	}

	@Override
	protected void actionOnAccess(Position<Entry<K, V>> p) {
		// If p is a dummy/sentinel node, move to the parent
		if(isLeaf(p)) {
			p = parent(p);
		}
		if(p != null) {
			splay(p);
		}
	}

	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		splay(node);
	}

	@Override
	protected void actionOnDelete(Position<Entry<K, V>> p) {
		if(!isRoot(p)) {
			splay(parent(p));
		}
	}
}