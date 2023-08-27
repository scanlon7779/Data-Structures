package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * The class for AdjacencyMapGraph
 * @author colinscanlon
 *
 * @param <V> The vertex type
 * @param <E> The element type
 */
public class AdjacencyMapGraph<V, E> extends AbstractGraph<V, E> {

	/** The list of vertices */
    private PositionalList<Vertex<V>> vertexList;
    
    /** the list of edges */
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * The map of graphs without direction
     */
    public AdjacencyMapGraph() {
        this(false);
    }
    
    /**
     * Constructor for AdjacencyMapGraph with direction
     * @param directed if the map is directed or not
     */
    public AdjacencyMapGraph(boolean directed) {
        super(directed);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
    }
    
    @Override
    public int numVertices() {
        return vertexList.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }

    @Override
    public int numEdges() {
        return edgeList.size();
    }

    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        return validate(vertex1).getOutgoing().get(vertex2);
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().size();
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return validate(vertex).getIncoming().size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().values();
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming().values();
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        AMVertex vertex = new AMVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        AMVertex origin = validate(v1);
        AMVertex destination = validate(v2);
        GraphEdge edge = new GraphEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(edge);
        edge.setPosition(pos);
        origin.getOutgoing().put(v2, edge);
        destination.getIncoming().put(v1, edge);
        return edge;
        
        // Remember to add the edge to the maps for each endpoint vertex
    }

    @Override
    public Vertex<V> removeVertex(Vertex<V> vertex) {
        AMVertex v = validate(vertex);
        for (Edge<E> e : v.getOutgoing().values()) {
        	removeEdge(e);
        }
        if (isDirected()) {
        	for (Edge<E> e : v.getIncoming().values()) {
            	removeEdge(e);
            }
        }
        
        return vertexList.remove(v.getPosition( ));
    }

    @Override
    public Edge<E> removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        Vertex<V>[] ends = e.getEndpoints();
        AMVertex origin = validate(ends[0]);
        AMVertex dest = validate(ends[1]);
        origin.getOutgoing().remove(ends[1]);
        dest.getIncoming().remove(ends[0]);
        edgeList.remove(e.getPosition());
        e.setPosition(null);
        return edge;
    }
    
    /**
     * Inner class for vertices
     * @author colinscanlon
     *
     */
    private class AMVertex extends GraphVertex {
        /** Map of outgoing edges */
        private Map<Vertex<V>, Edge<E>> outgoing;
        
        /** The map of incoming edges */
        private Map<Vertex<V>, Edge<E>> incoming;
        
        /**
         * Constructor
         * @param data The vertex data
         * @param isDirected if its directed or not
         */
        public AMVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
            if(isDirected) {
                incoming = new LinearProbingHashMap<>();
            } else {
                incoming = outgoing;
            }
        }
        
        /**
         * Gets the outgoing edges
         * @return the outgoing edge map
         */
        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }
        
        /**
         * Gets the incoming edges
         * @return the incoming edge map
         */
        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }
    }
    
    /**
     * Validates vertices
     * @param v the vertex to validate
     * @return The AMVertex type of the vertex
     */
    private AMVertex validate(Vertex<V> v) {
        if(!(v instanceof AdjacencyMapGraph.AMVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency map vertex.");
        }
        return (AMVertex)v;
    }
}