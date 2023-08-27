package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * The AdjacencyListGraph class
 * @author colinscanlon
 *
 * @param <V> The vertex type
 * @param <E> The edge type
 */
public class AdjacencyListGraph<V, E> extends AbstractGraph<V, E> {

	/** The local list for vertices */
    private PositionalList<Vertex<V>> vertexList;
    
    /** The local list of edges */
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * The constructor for edges with no direction
     */
    public AdjacencyListGraph() {
        this(false);
    }
    
    /**
     * The constructor for AdjacencyListGraph
     * @param directed if the graph is directed or not
     */
    public AdjacencyListGraph(boolean directed) {
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
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing();
    }
    
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming();
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        ALVertex v1 = validate(vertex1);
        ALVertex v2 = validate(vertex2);
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge current = validate(it.next());
            Vertex<V>[] ends = current.getEndpoints();
            if(!isDirected() && ends[1] == v1 && ends[0] == v2) {
                return current;
            }
            if (ends[0] == v1 && ends[1] == v2) {
                return current;
            }
        }
        return null;
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
    public Vertex<V> insertVertex(V vertexData) {
        ALVertex vertex = new ALVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;
        
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        ALVertex origin = validate(v1);
        ALVertex destination = validate(v2);       
        ALEdge edge = new ALEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(edge);
        edge.setPosition(pos);
        edge.setIncomingListPosition(destination.getIncoming().addLast(edge));
        edge.setOutgoingListPosition(origin.getOutgoing().addLast(edge));
        return edge;
    }

    @Override
    public Vertex<V> removeVertex(Vertex<V> vertex) {
        ALVertex v = validate(vertex);
        Iterator<Edge<E>> it1 = v.getOutgoing().iterator();
        while (it1.hasNext()) {
        	Edge<E> e = it1.next();
        	removeEdge(e);
        }
        
        if (isDirected()) {
        	Iterator<Edge<E>> it2 = v.getIncoming().iterator();
          while ( it2.hasNext() ) {
        	    Edge<E> e = it2.next();
        	    removeEdge(e);
          }
        } else {
        	Iterator<Edge<E>> it2 = v.getIncoming().iterator();
            while ( it2.hasNext() ) {
          	    Edge<E> e = it2.next();
          	  ALEdge e2 = validate(e);
              Vertex<V>[] ends = e2.getEndpoints();
              ALVertex origin = validate(ends[0]);
              ALVertex dest = validate(ends[1]);
              origin.getOutgoing().remove(e2.getPosition());
              dest.getIncoming().remove(e2.getPosition());
            }
        }
        
        
        return vertexList.remove(v.getPosition());
    }

    @Override
    public Edge<E> removeEdge(Edge<E> edge) {
        ALEdge e = validate(edge);
        Vertex<V>[] ends = e.getEndpoints();
        ALVertex origin = validate(ends[0]);
        ALVertex dest = validate(ends[1]);
        origin.outgoing.remove(e.getIncomingListPosition());
        dest.incoming.remove(e.getOutgoingListPosition());
        return edgeList.remove(e.getPosition());
    }
    
    /**
     * Class for AVLvertices
     * @author colinscanlon
     *
     */
    private class ALVertex extends GraphVertex {
        
    	/** The list of outgoing edges */
        private PositionalList<Edge<E>> outgoing;
        
        /** The list of incoming edges */
        private PositionalList<Edge<E>> incoming;
        
        /**
         * Constructor for AVLVertex
         * @param data The data of the vertex
         * @param isDirected If the vertex is directed or not
         */
        public ALVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new PositionalLinkedList<Edge<E>>();
            if(isDirected) {
                incoming = new PositionalLinkedList<Edge<E>>();
            } else {
                incoming = outgoing;
            }
        }
        
        /**
         * Gets the outgoing edges
         * @return The outgoing edges of the vertex
         */
        public PositionalList<Edge<E>> getOutgoing() {
            return outgoing;
        }
        
        /**
         * Gets the incoming edges
         * @return The list of incoming edges
         */
        public PositionalList<Edge<E>> getIncoming() {
            return incoming;
        }
    }
    
    /**
     * Class of ALEdges
     * @author colinscanlon
     *
     */
    private class ALEdge extends GraphEdge {    
    	/** List of outgoing edges */
        private Position<Edge<E>> outgoingListPosition;
        
        /** list of incoming edges */
        private Position<Edge<E>> incomingListPosition;
        
        /** 
         * Constructor for ALEdges
         * @param element The element of the edge
         * @param v1 The first vertex of the edge
         * @param v2 The second vertex of the edge
         */
        public ALEdge(E element, Vertex<V> v1,
                Vertex<V> v2) {
            super(element, v1, v2);
        }
        
        /**
         * Gets the outgoing position 
         * @return The outgoing position
         */
        public Position<Edge<E>> getOutgoingListPosition() {
            return outgoingListPosition;
        }
        
        /**
         * Sets the outgoing position
         * @param outgoingListPosition The outgoing position
         */
        public void setOutgoingListPosition(Position<Edge<E>> outgoingListPosition) {
            this.outgoingListPosition = outgoingListPosition;
        }
        
        /**
         * Gets the incoming position
         * @return The incoming position
         */
        public Position<Edge<E>> getIncomingListPosition() {
            return incomingListPosition;
        }
        
        /**
         * Sets the incoming position
         * @param incomingListPosition The incoming position
         */
        public void setIncomingListPosition(Position<Edge<E>> incomingListPosition) {
            this.incomingListPosition = incomingListPosition;
        }
    }

    /**
     * Validates the ALVertex
     * @param v The vertex to validate
     * @return The ALVertex version of the vertex
     */
    private ALVertex validate(Vertex<V> v) {
        if(!(v instanceof AdjacencyListGraph.ALVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency list vertex.");
        }
        return (ALVertex)v;
    }
    
    /**
     * Validates the given edge
     * @param e the edge to validate 
     * @return The ALEdge version of the edge
     */
    protected ALEdge validate(Edge<E> e) {
        if(!(e instanceof AdjacencyListGraph.ALEdge)) {
            throw new IllegalArgumentException("Edge is not a valid adjacency list edge.");
        }
        return (ALEdge)e;
    }
}