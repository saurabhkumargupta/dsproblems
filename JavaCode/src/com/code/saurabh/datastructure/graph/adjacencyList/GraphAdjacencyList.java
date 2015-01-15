package com.code.saurabh.datastructure.graph.adjacencyList;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.code.saurabh.datastructure.graph.elements.Edge;
import com.code.saurabh.datastructure.graph.elements.Vertex;

public class GraphAdjacencyList {

	Map<Vertex, Set<Vertex>> graph = new LinkedHashMap<Vertex, Set<Vertex>> ();
	boolean directedGraph = false;
	Set<Vertex> visitedVertex = new LinkedHashSet<Vertex> ();

	public GraphAdjacencyList () {
	}
	
	boolean directedGraph () {
		return directedGraph;
	}

	public void createGraph(Set<Vertex> V, Set<Edge> E, boolean directed) {
		addVertexToGraph (V);
		directedGraph = directed;
		for (Edge e: E) {
			Vertex first = e.getEdge().getFirst();
			Vertex second = e.getEdge().getSecond();

			graph.get(first).add(second);
			if (!directedGraph){
				graph.get(second).add(first);
			}
		}
	}

	public void createGraph(Set<Vertex> V, Set<Edge> E) {
		createGraph (V, E, directedGraph);
	}
	
	private void addVertexToGraph(Set<Vertex> V) {

		for (Vertex v: V) {
			graph.put(v, new LinkedHashSet<Vertex> ());
		}
	}

	public void printGraph() {
		for (Vertex vertex : graph.keySet()) {
			System.out.print(vertex + ": ");
			for (Vertex values: graph.get(vertex)) {
				System.out.print(values.getValue() + ", ");
			}
			System.out.println();
		}
	}

	public void dfs (Vertex startNode) {
		if (visitedVertex.contains(startNode)) {
			return;
		}
		visitedVertex.add(startNode);
		System.out.println(startNode.getValue());
		
		Set<Vertex> neighbours = graph.get(startNode);
		Iterator<Vertex> iter = neighbours.iterator();
		while (iter.hasNext()) {
			Vertex v = iter.next();
			dfs (v);
		}
	}

	public static void main (String[] args) {
		Set<Vertex> V = new LinkedHashSet <Vertex> ();
		Vertex vA = new Vertex ("A");
		Vertex vB = new Vertex ("B");
		Vertex vC = new Vertex ("C");
		Vertex vD = new Vertex ("D");
		Vertex vE = new Vertex ("E");
		Vertex vF = new Vertex ("F");
		Vertex vG = new Vertex ("G");
		/*LocalVertex vA = new LocalVertex (1);
		LocalVertex vB = new LocalVertex (2);
		LocalVertex vC = new LocalVertex (3);
		LocalVertex vD = new LocalVertex (4);
		LocalVertex vE = new LocalVertex (5);
		LocalVertex vF = new LocalVertex (6);
		LocalVertex vG = new LocalVertex (7);*/
		V.add(vA);
		V.add(vB);
		V.add(vC);
		V.add(vD);
		V.add(vE);
		V.add(vF);
		V.add(vG);
		
		Set<Edge> Edges = new LinkedHashSet <Edge> ();
		Edges.add(new Edge(vA, vB));
		Edges.add(new Edge(vA, vE));
		Edges.add(new Edge(vB, vC));
		Edges.add(new Edge(vB, vD));
		Edges.add(new Edge(vC, vD));
		Edges.add(new Edge(vD, vE));
		Edges.add(new Edge(vE, vF));
		
		GraphAdjacencyList localGraph = new GraphAdjacencyList ();
		localGraph.createGraph(V, Edges);
		
		localGraph.printGraph ();
		DFSTestRun (localGraph);
	}
	
	static GraphAdjacencyList getGraph () {
		GraphAdjacencyList G = new GraphAdjacencyList ();
		Set<Vertex> V = new LinkedHashSet <Vertex> ();
		Vertex vA = new Vertex ("A");
		Vertex vB = new Vertex ("B");
		Vertex vC = new Vertex ("C");
		Vertex vD = new Vertex ("D");
		Vertex vE = new Vertex ("E");
		Vertex vF = new Vertex ("F");
		Vertex vG = new Vertex ("G");
		/*LocalVertex vA = new LocalVertex (1);
		LocalVertex vB = new LocalVertex (2);
		LocalVertex vC = new LocalVertex (3);
		LocalVertex vD = new LocalVertex (4);
		LocalVertex vE = new LocalVertex (5);
		LocalVertex vF = new LocalVertex (6);
		LocalVertex vG = new LocalVertex (7);*/
		V.add(vA);
		V.add(vB);
		V.add(vC);
		V.add(vD);
		V.add(vE);
		V.add(vF);
		V.add(vG);
		
		Set<Edge> Edges = new LinkedHashSet <Edge> ();
		Edges.add(new Edge(vA, vB));
		Edges.add(new Edge(vA, vE));
		Edges.add(new Edge(vB, vC));
		Edges.add(new Edge(vB, vD));
		Edges.add(new Edge(vC, vD));
		Edges.add(new Edge(vD, vE));
		Edges.add(new Edge(vE, vF));
		G.createGraph(V, Edges);
		return G;
	}

	private static void DFSTestRun(GraphAdjacencyList adjacencyListGraph) {
		Set<Vertex> V = adjacencyListGraph.graph.keySet();
		Iterator<Vertex> iter = V.iterator();

		while (iter.hasNext()) {
			Vertex v = iter.next();
			System.out.println("DFS Traversal: Start: " + v.getValue());
			adjacencyListGraph.dfs(v);
			adjacencyListGraph.visitedVertex.clear();
		}
	}
}
