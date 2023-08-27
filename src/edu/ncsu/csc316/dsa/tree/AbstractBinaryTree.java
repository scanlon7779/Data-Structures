package edu.ncsu.csc316.dsa.tree;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * The abstract binary tree that implements BinaryTreeCollection
 * @author colinscanlon
 *
 * @param <E> The general type of the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTreeCollection<E> {

    @Override
    public Iterable<Position<E>> inOrder() {
        List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();

        if (!isEmpty()) {
            inOrderHelper(root(), traversal);
        }

        return traversal;
    }

    private void inOrderHelper(Position<E> node, List<Position<E>> traversal) {
        if (left(node) != null) {
        	inOrderHelper(left(node), traversal);
        }
        traversal.addLast(node);
        if (right(node) != null) {
        	inOrderHelper(right(node), traversal);
        }
    }
    
    @Override
    public Position<E> sibling(Position<E> p) {
    	Position<E> parent = parent(p);
    	if (parent == null) { 
    		return null;
    	}
        if (p == left(parent)) {
        	return right(parent); 
        } else {
        	return left(parent);
        }
    }
    
    private AbstractNode<E> validate(Position<E> p) {
        if(!(p instanceof AbstractNode)) {
            throw new IllegalArgumentException("Position is not a valid binary tree node");
        }
        return (AbstractNode<E>)(p);
    }
    
    @Override
    public int numChildren(Position<E> p) {
    	int count = 0;
    	if (left(p) != null) {
    		count++;
    	}
    	if (right(p) != null) {
    		count++;
    	}
    	return count;
    }
    
    @Override
    public E set(Position<E> p, E value) {
        AbstractNode<E> node = validate(p);
        E original = node.getElement();
        node.setElement(value);
        return original;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator(inOrder().iterator());
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractNode<E> node = validate(p);
        List<Position<E>> ret = new SinglyLinkedList<Position<E>>();
        if(left(node) != null) {
            ret.addLast(left(node));
        }
        if(right(node) != null) {
            ret.addLast(right(node));
        }
        return ret;
    }
    
    @Override
	public Iterable<Position<E>> preOrder()
    {
        // You can use any list data structure here that supports
        // O(1) addLast
        List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();
        if (!isEmpty()) {
            preOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
	/**
	 * The helper method for pre-order traversal
	 * @param p The position
	 * @param traversal The list to add to
	 */
    private void preOrderHelper(Position<E> p, List<Position<E>> traversal) {
        traversal.addLast(p);
        for(Position<E> c : children(p)) {
            preOrderHelper(c, traversal);
        }
    }    

    @Override
    public Iterable<Position<E>> postOrder() {
        // You can use any list data structure here that supports
        // O(1) addLast
        List<Position<E>> list = new SinglyLinkedList<Position<E>>();
        if(!isEmpty()) {
            postOrderHelper(root(), list);
        }
        return list;
    }
    
    /**
	 * The helper method for post-order traversal
	 * @param p The position
	 * @param list The list to add to
	 */
    private void postOrderHelper(Position<E> p, List<Position<E>> list) {
        for(Position<E> c : children(p)) {
            postOrderHelper(c, list);
        }
        list.addLast(p);
    }

	@Override
    public Iterable<Position<E>> levelOrder()
    {
    	List<Position<E>> list = new SinglyLinkedList<Position<E>>();
    	List<Position<E>> list2 = new SinglyLinkedList<Position<E>>();
    	if(!isEmpty()) {
            levelOrderHelper(root(), list, list2);
        }
    	return list2;
    }
    
    /**
	 * The helper method for level-order traversal
	 * @param p The position
	 * @param list The list to add to
	 * @param list2 the list to add in order to 
	 */
    private void levelOrderHelper(Position<E> p, List<Position<E>> list, List<Position<E>> list2) {
    	if (p == null) {
    		return;
    	}
    	list.addLast(p);
    	while (!list.isEmpty()) {
    		Position<E> q = list.removeFirst();
    		list2.addLast(q);
    		for(Position<E> c : children(q)) {
        		list.addLast(c);
            }
    	}
    }
    
    /**
     * The element iterator for the tree
     * @author colinscanlon
     *
     */
	protected class ElementIterator implements Iterator<E> {
		
		/** The local position iterator */
        private Iterator<Position<E>> it;
        
        /**
         * Constructor for the element iterator with a given iterator
         * @param iterator The iterator to use
         */
        public ElementIterator(Iterator<Position<E>> iterator) {
            it = iterator;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next().getElement();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
    }  
}