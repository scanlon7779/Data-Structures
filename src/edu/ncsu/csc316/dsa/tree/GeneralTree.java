package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * The general tree class that extends AbstractTree and implements the generalTreeCollection
 * @author colinscanlon
 *
 * @param <E> The generat type of the tree
 */
public class GeneralTree<E> extends AbstractTree<E> implements GeneralTreeCollection<E> {

	/** The root node of the tree */
    private Node<E> root;
    
    /** The size of the tree */
    private int size;
    
    /**
     * The constructor for the general tree
     */
    public GeneralTree() {
        root = null;
        size = 0;
    }
    
    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) {
        return validate(p).getParent();
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        Node<E> node = validate(p);
        List<Position<E>> ret = new SinglyLinkedList<Position<E>>();
        for(Position<Node<E>> n : node.getChildren().positions())
        {
            ret.addLast(n.getElement());
        }
        return ret;
    }

    @Override
    public int numChildren(Position<E> p) {
        Node<E> node = validate(p);
        return node.getChildren().size();
    }
    
    @Override
    public Position<E> addRoot(E value) {
        if(root != null) {
            throw new IllegalArgumentException("Tree already has a root");
        }
        this.root = createNode(value);
        size = 1;
        return root;
    }   

    @Override
    public Position<E> addChild(Position<E> p, E value) {
        Node<E> node = validate(p);
        Node<E> newNode = createNode(value);
        node.getChildren().addLast(newNode);
        newNode.setParent(node);
        size++;
        return newNode;
    }

    @Override
    public E set(Position<E> p, E value) {
        Node<E> node = validate(p);
        E original = node.getElement();
        node.setElement(value);
        return original;
    }
    
    /**
     * Creates a node with the given element
     * @param element The element of the new node
     * @return The node that was created
     */
    public Node<E> createNode(E element) {
        return new Node<E>(element);
    }
	
	@Override
	public int size() {
		return size;
	}
	
	private Node<E> validate(Position<E> p) {
        if(!(p instanceof Node)) {
            throw new IllegalArgumentException("Position is not a legal general tree node");
        }
        return (Node<E>)p;
    }
	
	@Override
	public E remove(Position<E> p) {
	    // We will only support removal of a node that only has 1 child
	    if(numChildren(p) > 1) {
	        throw new IllegalArgumentException("The node has more than 1 child.");
	    }
	    // Handle special case if the node being removed is the root
	    if(isRoot(p)) {
	        E original = p.getElement();
	        if(numChildren(p) == 0) {
	            this.root = null;
	        } else {
	            Node<E> replacement = validate(p).getChildren().first().getElement();
	            replacement.setParent(null);
	            this.root = replacement;
	        }
	        size--;
	        return original;
	    }
	    // Handle the case where the node being removed is NOT the root
	    Node<E> node = validate(p);
	    Node<E> parent = validate(parent(p));
	    // Create an iterator over the parent node's children positions
	    Iterator<Position<Node<E>>> it = parent.getChildren().positions().iterator();
	    while(it.hasNext()) {
	        // Find the position of the node to be removed
	        Position<Node<E>> current = it.next();
	        if(current.getElement() == node) {
	            if(numChildren(p) == 1) {
	                // If the node being removed has 1 child, replace it
	                // in the parent's list of children
	                Node<E> replacement = node.getChildren().first().getElement();
	                replacement.setParent(parent);
	                parent.getChildren().set(current, replacement);
	            } else {
	                // If the node being removed has 0 children, remove it
	                parent.getChildren().remove(current);
	            }
	        }
	    }
	    size--;
	    return node.getElement();
	}
	
	
	/**
	 * The Class for nodes held by the tree
	 * @author colinscanlon
	 *
	 * @param <E> The general type of the nodes
	 */
	private static class Node<E> extends AbstractNode<E> {

		/** The parent node of the node */
        private Node<E> parent;
        
        /** The list of the nodes children */
        private PositionalList<Node<E>> children;
        
        /**
         * Constructor for the node
         * @param element The element of the node
         */
        public Node(E element) {
            this(element, null);
        }
        
        /**
         * The constructor for a node with a parent
         * @param element The element of the new node
         * @param parent The parent of the node
         */
        public Node(E element, Node<E> parent) {
            super(element);
            setParent(parent);
            children = new PositionalLinkedList<Node<E>>();
        }
        
        /**
         * Sets the parent of the node
         * @param p The parent of the node
         */
        public void setParent(Node<E> p) {
            parent = p;
        }
        
        /**
         * Gets the parent of the node
         * @return The node's parent
         */
        public Node<E> getParent() {
            return parent;
        }
        
        /**
         * The list of the nodes children 
         * @return The list of the nodes children 
         */
        public PositionalList<Node<E>> getChildren() {
            return children;
        }
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


    @Override
    public Iterator<E> iterator() {
        return new ElementIterator(preOrder().iterator());
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