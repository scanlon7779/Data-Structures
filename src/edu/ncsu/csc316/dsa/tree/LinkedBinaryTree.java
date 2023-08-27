package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * The linked binary tree that uses linked nodes to hold data as a binary tree
 * @author colinscanlon
 *
 * @param <E> The generic type of the tree
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	/** The root of the tree */
    private Node<E> root;
    
    /** The size of the tree */
    private int size;

    /**
     * The constructor for the LinkedBinaryTree
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * The validation method that checks if the given position is a valid linked binary node
     * @param p The position to check
     * @return If it is a valid node or not
     */
    protected Node<E> validate(Position<E> p) {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Position is not a valid linked binary node");
        }
        return (Node<E>) p;
    }

    /**
     * The inner node class that represents each part of the tree
     * @author colinscanlon
     *
     * @param <E> The generic type of the node
     */
    public static class Node<E> extends AbstractNode<E> {
    	
    	/** The parent node of the node */
        private Node<E> parent;
        
        /** The left node of the node */
        private Node<E> left;
        
        /** The right node of the node */
        private Node<E> right;

        /**
         * The constructor for a node
         * @param element The element of the node
         */
        public Node(E element) {
            this(element, null);
        }

        /**
         * The constrcutor for a node with a given parent
         * @param element The element of the node
         * @param parent The parent of the node
         */
        public Node(E element, Node<E> parent) {
            super(element);
            setParent(parent);
        }

        /**
         * Gets the left child of the node
         * @return The left child of the node
         */
        public Node<E> getLeft() {
            return left;
        }

        /**
         * Gets the right child of the node
         * @return The right child of the node
         */
        public Node<E> getRight() {
            return right;
        }

        /**
         * Sets the left child of the node
         * @param left The node for the new left child
         */
        public void setLeft(Node<E> left) {
            this.left = left;
        }

        /**
         * Sets the right child of the node 
         * @param right The node to set the right child to 
         */
        public void setRight(Node<E> right) {
            this.right = right;
        }

        /**
         * Gets the parent of the node
         * @return The parent of the node
         */
        public Node<E> getParent() {
            return parent;
        }

        /**
         * Sets the parent of the node 
         * @param parent The node of the parent to set to
         */
        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
    }

    @Override
    public Position<E> left(Position<E> p) {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) {
        Node<E> node = validate(p);
        return node.getRight();
    }

	@Override
    public Position<E> addLeft(Position<E> p, E value) {
        Node<E> node = validate(p);
        if (left(node) != null) {
            throw new IllegalArgumentException("Node already has a left child.");
        }
        Node<E> left = createNode(value, node, null, null);
        node.setLeft(left);
        size++;
        return left;
    }

	@Override
    public Position<E> addRight(Position<E> p, E value) {
        Node<E> node = validate(p);
        if (right(node) != null) {
            throw new IllegalArgumentException("Node already has a right child.");
        }
        Node<E> right = createNode(value, node, null, null);
        node.setRight(right);
        size++;
        return right;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) {
        Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Position<E> addRoot(E value) {
        if (root() != null) {
            throw new IllegalArgumentException("The tree already has a root.");
        }
        this.root = createNode(value, null, null, null);
        size++;
        return root;
    }

    @Override
    public E remove(Position<E> p) {
    	Node<E> node = validate(p);
        if (numChildren(p) == 2){
            throw new IllegalArgumentException("The node has two children");
        }
        Node<E> child;
        if (left(node) != null) {
        	child = node.getLeft();
        } else {
        	child = node.getRight();
        }
        if (child != null) {
        	child.setParent(node.getParent());
        }
        if (node == root) {
        	root = child;
        } else {
        	Node<E> parent = node.getParent();
        	if (node == parent.getLeft()) {
        		parent.setLeft(child);
        	} else {
        		parent.setRight(child);
        	}
        }
        
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return temp;
        
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Method that creates a new node
     * @param e The element of the new node
     * @param parent The parent of the node
     * @param left The left child of the node
     * @param right The right child of the node
     * @return The new node
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        Node<E> newNode = new Node<E>(e);
        newNode.setParent(parent);
        newNode.setLeft(left);
        newNode.setRight(right);
        return newNode;
    }

    /**
     * Sets the root of the tree
     * @param p The position of the root to set
     * @return The position of the root
     */
    protected Position<E> setRoot(Position<E> p) {
        root = validate(p);
        return root;
    }
}