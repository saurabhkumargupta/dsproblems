package com.code.saurabh.datastructure.graph.adjacencyList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/*
 *  Rajat's interview in infoWorks, in Mail
 *  
 * In a social network (viewed as a graph G=(V,E)) vertices in V are added over time, and edges
in E are formed as a vertex u in E send request to another vertex v in E, and v accepts it. Such a
network can be viewed as a directed graph with arc (u,v) in E when u sends request to v and we
denote it as u < v. A order of a directed graph (connected components) is a total order of the
vertices such that for every arc (v, w), v < w. A directed graph has such order (and generally
more than one) if and only if it (connected components) is acyclic.
The (incremental) aggressive networker ordering problem is that of maintaining a order in a
social network directed graph as arcs are added over time (i.e. when a vertex u in E send
request to another vertex v in E, and we assume v always accepts request) , stopping when
addition of an arc creates a cycle (in a connected component), and breaking cycle by removing
the last added arc.

More specifically, the goal is to maintain a data structure that supports following two operations:
1. Edge insertions, in which a new edge is added to the graph G, and
2. Queries of the form: "Does u come before v in the ordering?"

At an early stage, a social network may have many disconnected components. To form a
connected graph faster it helps suggesting possible connections to a vertex. The incremental
aggressive networker ordering problem helps in detecting nodes that "come before" many
nodes, in their connected component. Then one can suggest two aggressive networkers from
different component to form bigger graph.

Develop:
1. An algorithm for The (incremental) aggressive networker ordering problem, and associated data structure. 
Document the pseudocode of the algorithm.

2. Code the data structure that supports : incremental edge addition, answers question on ordering. 
(C++, Python, Java) include some Unit test to show correctness.

Duration: 3 hours. 
 *
 */
public class TopologicalOrderingInfoWorksProblem<V> {

	/**
	 * The graph is implemented as an adjacency list, but instead of an array of lists a map is used to hold adjacency list for each vertex.
	 * If the vertices are fixed in start and are continuous increasing than array of lists is good but taking a map to avoid interview tweaks.
	 */
	
    private Map<V,List<V>> dag = new HashMap<V,List<V>>(); //Directed acyclic graph
    private List<V> vertexOrder;
    
    /**
     * String representation of graph.
     */
    public String toString () {
        StringBuffer s = new StringBuffer();
        for (V v: dag.keySet()) s.append("\n   " + v + " -> " + dag.get(v));
        return s.toString();                
    }
    
    /**
     * Add a new vertex to the graph. Ignore if already added.
     */
    public void add (V vertex) {
        if (dag.containsKey(vertex)) return;
        dag.put(vertex, new ArrayList<V>());
    }
    
    public boolean contains (V vertex) {
        return dag.containsKey(vertex);
    }
    
    /**
     * Add an edge to the graph.
     * If either vertex does not exists in graph add that too.
     * If addition of this edge makes the graph acyclic then do not add.
     */
    public void add (V u, V v) {
        this.add(u); this.add(v);
        dag.get(u).add(v);
        
        List<V> newVertexOrder = getNewVertexOrderIfAcyclic();
        if(newVertexOrder == null){
        	/**
        	 * This will be null only when the graph is cyclic. Remove the current added edge to remove the cycle.
        	 */
        	remove(u,v);
        }else{
        	vertexOrder = newVertexOrder;
        }
    }
    
    private void remove (V u, V v) {
        dag.get(u).remove(v);
    }
    
    /**
     * Calculate in-degree (Number of incoming edge to this vertex) of each vertex.
     */
    public Map<V,Integer> inDegree () {
        Map<V,Integer> result = new HashMap<V,Integer>();
        for (V v: dag.keySet()) result.put(v, 0);       // All in-degrees are 0. Will be updated later
        for (V from: dag.keySet()) {
            for (V to: dag.get(from)) {
                result.put(to, result.get(to) + 1);           // Increment in-degree
            }
        }
        return result;
    }
    
    /**
     * calculate the ordering of the vertex, return as list.
     * Find incoming count for every vertex.
     * Start with zero incoming vertices and move to every connected vertex.
     * For every connected vertex, decrement the incoming count and if it becomes zero then add it to vertexOrder list. 
     * If the graph is acyclic then the vertex order list should include every vertex from graph in the end.
     */
    private List<V> getNewVertexOrderIfAcyclic () {
        Map<V, Integer> indegree = inDegree();
        // Determine all vertices with zero in-degree
        Stack<V> zeroIncomingVertex = new Stack<V>();        
        for (V v: indegree.keySet()) {
            if (indegree.get(v) == 0) zeroIncomingVertex.push(v);
        }
        
        // Determine the vertex order
        List<V> result = new ArrayList<V>();
        while (!zeroIncomingVertex.isEmpty()) {
            V v = zeroIncomingVertex.pop();                  // Choose a vertex with zero in-degree
            result.add(v);                          // Vertex v is next in vertex ordering
            // "Remove" vertex v by updating its neighbors
            for (V neighbor: dag.get(v)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                // Remember any vertices that now have zero in-degree
                if (indegree.get(neighbor) == 0) zeroIncomingVertex.push(neighbor);
            }
        }
        // Check that we have used the entire graph. If not then there was a cycle.
        if (result.size() != dag.size()) return null;
        return result;
    }
    
    public boolean checkIfInOrder(V u, V v){
    	int uIndx = vertexOrder.indexOf(u);
    	int vIndx = vertexOrder.indexOf(v);
    	if(uIndx != -1 && vIndx != -1){
    		return uIndx<vIndx;
    	}
    	System.out.println("graph do not have either of given two vertices");
    	return false;
    }
    
    public void printVertexOrder(){
    	for(V v: vertexOrder)
    		System.out.print(v + " -> ");
    	System.out.println();
    }
    
    public static void main (String[] args) {
        // Create a Graph with Integer nodes
    	TopologicalOrderingInfoWorksProblem<Integer> graph = new TopologicalOrderingInfoWorksProblem<Integer>();
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(0, 3);
        graph.add(1, 2);
        graph.add(1, 3);
        graph.add(2, 3);
        graph.add(2, 4);
//        System.out.println("current graph: " + graph);
        graph.printVertexOrder();
        System.out.println("check order 1,2 " + graph.checkIfInOrder(1, 2));
        graph.add(4, 1);                                     // Create a cycle
//        System.out.println("current graph: " + graph);
        System.out.println("check order 4,1 " + graph.checkIfInOrder(4, 1));
        graph.add(4, 5);         
        graph.printVertexOrder();
        System.out.println("check order 1,5 " + graph.checkIfInOrder(1, 5));
    }

}
