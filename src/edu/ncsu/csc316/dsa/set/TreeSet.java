package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.RedBlackTreeMap;

/**
 * The Set implementation using trees
 * @author colinscanlon
 *
 * @param <E> The generic type of the set
 */
public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> {

	/** The local tree */
    private Map<E, E> tree;
    
    /**
     * Constructor for the TreeSet
     */
    public TreeSet() {
        tree = new RedBlackTreeMap<E, E>();
    }
    
    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }

    @Override
    public void add(E value) {
        tree.put(value, value);
    }

    @Override
    public boolean contains(E value) {
        return tree.get(value) != null;
    }

    @Override
    public E remove(E value) {
        return tree.remove(value);
    }

    @Override
    public int size() {
        return tree.size();
    }
}