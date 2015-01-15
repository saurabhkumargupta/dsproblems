package com.code.saurabh.datastructure.graph.adjacencyList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.code.saurabh.datastructure.graph.elements.Vertex;
import com.code.saurabh.datastructure.queue.Queue;

public class CloneGraph {

	static Graph clone (Graph g) {
		Graph NG = new Graph ();
		Set<Vertex> UV = new HashSet<Vertex> ();
		UV.addAll(g.graph.keySet());
		
		//In case graph is not strongly connected
		while (!UV.isEmpty()) {
			Vertex v = null;
			Iterator<Vertex> iter = UV.iterator();
			if (iter.hasNext()) {
				v = iter.next();
			}
			if (v != null) {
				clone (g, v, UV, NG);
			}
		}
		return NG;
	}
	
	/*
	 * This is BFS based approach
	 * Given a single vertex, create cloned graph. With Strongly connected component rooted with v
	 */
	private static void clone(Graph g, Vertex v, Set<Vertex> uV, Graph nG) {
		Queue q = new Queue ();
		
		//Enqueue root
		q.enqueue(v);
		
		//Until all elements of this strongly connected component is traversed
		while (!q.isEmpty()) {
			v = (Vertex) q.dequeue();
			//check if this is unvisited
			if (!uV.contains(v)) {
				continue;
			}
			
			//remove this from unvisited list
			uV.remove(v);
			
			//Add this node to new Graph
			addVertexToGraph (nG, v);
			
			//get all childs of new node
			List<Vertex> childs = g.graph.get(v);
			for (Vertex child: childs) {
				//add each child of this node to new Graph
				//Add edge in new graph from this node to its child
				//Add this child in queue for next iteration
				addVertexToGraph (nG, child);
				addEdgeToGraph (nG, v, child);
				//Enqueue child if its not been visited
				if (uV.contains(child)) {
					q.enqueue(child);
				}
			}
		}
	}

	private static void addEdgeToGraph(Graph nG, Vertex v1, Vertex v2) {
		if (nG.graph.containsKey(v1)) {
			nG.graph.get(v1).add(v2);
		}
	}

	private static void addVertexToGraph(Graph nG, Vertex v) {
		if (!nG.graph.containsKey(v)) {
			nG.graph.put(v, new ArrayList<Vertex> ());
		}
	}

	public static void main(String[] args) {
		Graph graph = new Graph ();
		graph = Graph.getGraph(false);
		
		Graph clonedgraph = clone (graph);
		System.out.println("Original Undirected Graph");
		graph.printGraph();
		System.out.println("Cloned Undirected Graph");
		clonedgraph.printGraph();
		
		//DIRECTED GRAPH
		graph = new Graph ();
		graph = Graph.getGraph(true);
		
		clonedgraph = clone (graph);
		System.out.println("Original directed Graph");
		graph.printGraph();
		System.out.println("Cloned directed Graph");
		clonedgraph.printGraph();
	}

}
