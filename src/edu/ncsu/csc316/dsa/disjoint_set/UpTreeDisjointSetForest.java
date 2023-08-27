package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * The class for Up Tree Disjoint Set Forests
 * @author colinscanlon
 *
 * @param <E> The generic type of the set
 */
public class UpTreeDisjointSetForest<E> implements DisjointSetForest<E> {

    // We need a secondary map to quickly locate an entry within the forest of up-trees
    // NOTE: The textbook implementation does not include this
    //       functionality; instead, the textbook implementation leaves
    //       the responsibility of tracking positions to the client in a
    //       separate map structure
	/** The local map of tree nodes */
    private Map<E, UpTreeNode<E>> map;
    
    /**
     * The constructor for UpTreeDisjointSetForests
     */
    public UpTreeDisjointSetForest() {
        // Use an efficient map!
        map = new LinearProbingHashMap<E, UpTreeNode<E>>();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Position<E> makeSet(E value) {
    	UpTreeNode p = new UpTreeNode(value);
    	map.put(value, p);
        return p;
    }

    @Override
    public Position<E> find(E value) {
    	return findHelper(validate(map.get(value)));
        
    }
    
    private UpTreeNode<E> findHelper(UpTreeNode<E> current) {
        if (current != current.getParent() ) {
        	return findHelper(current.getParent());
        }
        return current;
    }

    @Override
    public void union(Position<E> s, Position<E> t) {
        // Instead of trusting the client to give us the roots
        // of two up-trees, we will verify by finding the root 
        // of the up-tree that contains the element in positions s and t
        UpTreeNode<E> a = validate(find(s.getElement()));
        UpTreeNode<E> b = validate(find(t.getElement()));
        if ( a != b ) {
        	if (a.getCount() > b.getCount()) {
        		b.setParent(a);
        		a.setCount(a.getCount() + b.getCount());
        	} else {
        		a.setParent(b);
        		b.setCount(a.getCount() + b.getCount());
        		
        	}
        }
    }
    
    /**
     * Validates the given position
     * @param p The position to validate
     * @return The tree node
     */
    private UpTreeNode<E> validate(Position<E> p) {
        if(!(p instanceof UpTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid up tree node.");
        }
        return (UpTreeNode<E>)p;
    }
    
    /**
     * The private class for UpTreeNodes
     * @author colinscanlon
     *
     * @param <E> The generic type of the node
     */
    private static class UpTreeNode<E> implements Position<E> {
        /** The element of the node */
        private E element;
        
        /** The parent of the node */
        private UpTreeNode<E> parent;
        
        /** The count of the node */
        private int count;
        
        public UpTreeNode(E element) {
            setElement(element);
            setParent(this);
            setCount(1);
        }
        
        public void setElement(E element) {
            this.element = element;
        }
        
        @Override
        public E getElement() {
            return element;
        }
        
        public void setParent(UpTreeNode<E> parent) {
            this.parent = parent;
        }
        
        public UpTreeNode<E> getParent() {
            return parent;
        }
        
        public void setCount(int count) {
            this.count = count;
        }
        
        public int getCount() {
            return count;
        }
    }
}