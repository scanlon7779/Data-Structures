package edu.ncsu.csc316.dsa.graph;

/**
 * Interface for the graph adt
 * @author colinscanlon
 *
 * @param <V> The general type for vertices
 * @param <E> The general type for edges
 */
public interface Graph<V, E> {
	
	/**
	 * Returns if the graph is directed or not
	 * @return if then graph is directed
	 */
    boolean isDirected();
    
    /**
     * Gets the number of vertices
     * @return The number of vertices
     */
    int numVertices();
    
    /**
     * Iteration of the vertices
     * @return the iteration of vertices
     */
    Iterable<Vertex<V>> vertices();
    
    /**
     * Gets the number of edges
     * @return The number of edges
     */
    int numEdges();
    
    /**
     * Gets the edge iteration
     * @return The edge iteration of the graph
     */
    Iterable<Edge<E>> edges();
    
    /**
     * Gets the edge between two vertices
     * @param vertex1 The first vertex
     * @param vertex2 The second vertex
     * @return The edge between the two
     */
    Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2);
    
    /**
     * Gets the end vertices of an edge
     * @param edge The edge to get vertices from 
     * @return The vertices if the edge
     */
    Vertex<V>[] endVertices(Edge<E> edge);
    
    /**
     * Gets the opposite vertex of the edge
     * @param vertex One vertex of the edge
     * @param edge The edge to look at
     * @return The opposite vertex
     */
    Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge);
    
    /**
     * The out degree of a given vertex
     * @param vertex The vertex to check
     * @return The out degree of the vertex
     */
    int outDegree(Vertex<V> vertex);
    
    /**
     * Gets the in degree of the given vertex
     * @param vertex The vertex to check
     * @return the in degree of the vertex
     */
    int inDegree(Vertex<V> vertex);
    
    /**
     * Gets the number of edges going into the given vertex
     * @param vertex The vertex to check
     * @return The number of outgoing edges
     */
    Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex);
    
    /**
     * The number of in edged
     * @param vertex The vertex to check
     * @return The number of in edges
     */
    Iterable<Edge<E>> incomingEdges(Vertex<V> vertex);
    
    /**
     * Inserts a vertex with the given data
     * @param vertexData The data of the new vertex
     * @return The new vertex
     */
    Vertex<V> insertVertex(V vertexData);
    
    /**
     * creates a new edge between two vertices
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @param edgeData The data of the edge
     * @return The new edge
     */
    Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData);
    
    /**
     * Removes a given vertex
     * @param vertex The vertex to remove
     * @return the vertex removed
     */
    Vertex<V> removeVertex(Vertex<V> vertex);
    
    /**
     * Removes a given edge
     * @param edge The edge to remove
     * @return The the removed edge
     */
    Edge<E> removeEdge(Edge<E> edge);
    
    /**
     * The interface for an edge
     * @author colinscanlon
     *
     * @param <E> The type of the edge
     */
    interface Edge<E> {
    	/**
    	 * Gets the element of the edge
    	 * @return The element of the edge
    	 */
        E getElement();
    }
    
    /**
     * The interface for vertices
     * @author colinscanlon
     *
     * @param <V> The general type for the vertex
     */
    interface Vertex<V> {
    	
    	/**
    	 * Gets the element from the vertex
    	 * @return The element of the vertex
    	 */
        V getElement();
    }
}