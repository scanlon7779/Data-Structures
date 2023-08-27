package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * Class for RedBlackTreeMaps that implements BinarySearchTreeMap
 * @author colinscanlon
 *
 * @param <K> The type for keys in the tree map
 * @param <V> The type for value in the tree map
 */
public class RedBlackTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * Constructor for Red Black Tree Maps with no given comparator
	 */
	public RedBlackTreeMap() {
		super(null);
	}

	/**
	 * Constructor for Red Black Tree Maps with a given comparator
	 * @param compare The comparator to use for sorting
	 */
	public RedBlackTreeMap(Comparator<K> compare) {
		super(compare);
	}

    // Helper method to abstract the idea of black
	private boolean isBlack(Position<Entry<K, V>> p) {
		return getProperty(p) == 0;
	}

    // Helper method to abstract the idea of red
	private boolean isRed(Position<Entry<K, V>> p) {
		return getProperty(p) == 1;
	}

    // Set the color for a node to be black
	private void makeBlack(Position<Entry<K, V>> p) {
		setProperty(p, 0);
	}

    // Set the color for a node to be red
	private void makeRed(Position<Entry<K, V>> p) {
		setProperty(p, 1);
	}

	private void resolveRed(Position<Entry<K, V>> node) {
		Position<Entry<K, V>> parent, uncle, middle, grand;
		parent = parent(node);
		if (isRed(parent)) {
			uncle = sibling(parent);
			if (isBlack(uncle)) {
				middle = restructure(node);
				makeBlack(middle);
				makeRed(left(middle));
				makeRed(right(middle));
			} else {
				makeBlack(parent);
				makeBlack(uncle);
				grand = parent(parent);
				if (!isRoot(grand)) {
					makeRed(grand);
					resolveRed(grand);
				}
			}
		}
		
	}

	private void remedyDoubleBlack(Position<Entry<K, V>> p) {
		Position<Entry<K, V>> z = parent(p);
		Position<Entry<K, V>> y = sibling(p);
		if (isBlack(y)) {
			if (isRed(left(y)) || isRed(right(y))) {
				Position<Entry<K, V>> x = (isRed(left(y)) ? left(y) : right(y));
				Position<Entry<K, V>> middle = restructure(x);
				if(isRed(z)) {
					makeRed(middle);
				} else {
					makeBlack(middle);
				}
				makeBlack(left(middle));
				makeBlack(right(middle));
			} else {
				makeRed(y);
				if (isRed(z)) {
					makeBlack(z);
				} else if (!isRoot(z)) {
					remedyDoubleBlack(z);
				}
			}
		} else {
			rotate(y);
			makeBlack(y);
			makeRed(z);
			remedyDoubleBlack(p);
		}
	}

	@Override
	protected void actionOnInsert(Position<Entry<K, V>> p) {
		if (!isRoot(p)) {
			makeRed(p);
			resolveRed(p);
		}
	}

	@Override
	protected void actionOnDelete(Position<Entry<K, V>> p) {
		if (isRed(p)) {
			makeBlack(p);
		} else if (!isRoot(p)) {
			Position<Entry<K, V>> sib = sibling(p);
			if (isInternal(sib) && (isBlack(sib) || isInternal(left(sib)))) {
				remedyDoubleBlack(p);
			}
		}
	}
}