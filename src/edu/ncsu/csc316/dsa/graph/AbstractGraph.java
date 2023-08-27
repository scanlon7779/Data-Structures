package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;

/**
 * The abstract class for graphs
 * @author colinscanlon
 *
 * @param <V> The vertex type
 * @param <E> Thne edge type
 */
public abstract class AbstractGraph<V, E> implements Graph<V, E> {
    
	/** if the graph is directed or not */
    private boolean isDirected;
    
    /**
     * Constructor for a graph
     * @param directed if the graph is directed or not
     */
    public AbstractGraph(boolean directed) {
        setDirected(directed);
    }
    
    /**
     * Sets the directed field
     * @param directed if the graph is directed or not
     */
    private void setDirected(boolean directed) {
        isDirected = directed;
    }
    
    /**
     * Gets if the graph is directed or not
     * @return if the graph is directed or not
     */
    public boolean isDirected() {
        return isDirected;
    }
    
    @Override
    public Vertex<V>[] endVertices(Edge<E> edge) {
        return validate(edge).getEndpoints();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge) {
        GraphEdge temp = validate(edge);
        Vertex<V>[] ends = temp.getEndpoints();
        if(ends[0] == vertex) {
            return ends[1];
        }
        if(ends[1] == vertex) {
            return ends[0];
        }
        throw new IllegalArgumentException("Vertex is not incident on this edge.");
    }
    
    /**
     * The protected class for a graph vertex
     * @author colinscanlon
     *
     */
    protected class GraphVertex implements Vertex<V> {
    	
    	/** The element of the vertex */
        private V element;
        
        /** The position of the vertex */
        private Position<Vertex<V>> position;
        
        /**
         * Creates a new graph vertex with the given element
         * @param element The element of the new vertex 
         */
        public GraphVertex(V element) {
            setElement(element);
        }
        
        /**
         * Sets the element of the vertex 
         * @param element The new element of the vertex
         */
        public void setElement(V element) {
            this.element = element;
        }
        
        /**
         * Gets the element of the vertex
         * @return the element of the vertex
         */
        public V getElement() {
            return element;
        }
        
        /**
         * Gets the position of the vertex
         * @return The position of the vertex
         */
        public Position<Vertex<V>> getPosition(){
            return position;
        }
        
        /**
         * Sets the position of the vertex
         * @param pos The new position 
         */
        public void setPosition(Position<Vertex<V>> pos) {
            position = pos;
        }
    }
    
    /**
     * The class for graph edges
     * @author colinscanlon
     *
     */
    protected class GraphEdge implements Edge<E> {
    	/** the element of the edge */
        private E element;
        
        /** the end point vertices of the edge */
        private Vertex<V>[] endpoints;
        
        /** the position of the edge */
        private Position<Edge<E>> position;
        
        /**
         * The constructor for the graph edge
         * @param element The element of the edge
         * @param v1 The first vertex of the edge
         * @param v2 The second vertex of the edge
         */
        @SuppressWarnings("unchecked")
        public GraphEdge(E element, Vertex<V> v1, Vertex<V> v2) {
            setElement(element);
            endpoints = (Vertex<V>[])new Vertex[]{v1, v2};
        }
        
        /**
         * Sets the element of the edge
         * @param element The new element of the edge
         */
        public void setElement(E element) {
            this.element = element;
        }
        
        /**
         * Gets the element of the edge
         * @return The edge element
         */
        public E getElement() {
            return element;
        }
        
        /**
         * Gets the end points of the edge
         * @return the array of end points of the edge
         */
        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }
        
        /**
         * Gets the position of the edge
         * @return The position of the edge
         */
        public Position<Edge<E>> getPosition(){
            return position;
        }
        
        /**
         * Sets the position of the edge
         * @param pos The new position of the edge
         */
        public void setPosition(Position<Edge<E>> pos) {
            position = pos;
        }
        
        @Override
        public String toString() {
            return "Edge[element=" + element + "]";
        }
    }
    
    /**
     * Validates the edge of the graph
     * @param e the edge to validate
     * @return The graph edge equivalent
     */
    protected GraphEdge validate(Edge<E> e) {
        if (!(e instanceof AbstractGraph.GraphEdge)) {
            throw new IllegalArgumentException("Edge is not a valid graph edge.");
        }
        return (GraphEdge) e;
    }
}