package com.code.saurabh.datastructure.graph.adjacencyMatrix;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.code.saurabh.datastructure.graph.elements.Edge;
import com.code.saurabh.datastructure.graph.elements.Vertex;

public class GraphAdjacencyMatrix {

	int[][] graph = null;
	boolean directedGraph = false;
	boolean[] visitedVertex = null;

	public void createGraph() {
	}

	public void createGraph(Set<Vertex> V, Set<Edge> E) {
		createGraph (V, E, directedGraph);
	}
	
	public void createGraph (int[][] input) {
		graph = input;
	}
	
	public void createGraph(Set<Vertex> V, Set<Edge> E, boolean directed) {
		graph = new int [V.size()][V.size()];
		visitedVertex = new boolean [V.size()];

		for (Edge e: E) {
			int fIndex = (int) e.getEdge().getFirst().getValue();
			int sIndex = (int) e.getEdge().getSecond().getValue();
			graph[fIndex] [sIndex] = 1;
			if (!directed)
				graph[sIndex] [fIndex] = 1;
		}
	}
	
	public void printGraph() {
		for (int i = 0; i < graph.length; i++) {
			System.out.print(i + ": ");
			for (int j = 0; j < graph[i].length; j++) {
				if (graph[i][j] == 1)
					System.out.print(j + ", ");
			}
			System.out.println();
		}
	}

	void dfs (int v) {
		if (visitedVertex[v]) {
			return;
		}
		visitedVertex[v] = true;
		System.out.println(v);
		for (int j = 0; j < graph[v].length; j++) {
			if (graph[v][j] == 1 && visitedVertex[j] == false) {
				dfs (j);
			}
		}
	}
	
	/**
	 * <pre>
	 * get shortest path from Vertex vA to Vertex vB
	 * 
	 * We keep two sets of vertex, one is current set and another is result set
	 * 
	 * we start from vA and add it to current list.
	 * We iterate all neighbors of vA and start searching for vB from that neighbor
	 * Whenever we reach vB, we check both list current and result list.
	 * if result list is bigger than current then we drop result set and make current set as result set
	 * 
	 * or while processing vertex, we check that our current set is bigger than earlier result set then we dont move ahead.
	 * As it is guaranteed not to be smallest. 
	 * </pre>
	 * @param vA
	 * @param vB
	 * @param result
	 * @param current
	 */
	private void shortestPath(int vA, int vB,
			List<Integer> result, List<Integer> current) {

		current.add(vA);
		if (vA == vB) {
			if ( (result.size() != 0 && result.size() > current.size())
					|| result.size() == 0)
			{
				result.clear();
				result.addAll(current);
			}
			return;
		}
		
		if (result.size() != 0 && current.size() > result.size()) {
			return;
		}

		for (int j = 0; j < graph[vA].length; j++) {
			if (!current.contains(j) && graph[vA][j] == 1) {
				shortestPath (j, vB, result, current);
				current.remove(Integer.valueOf(j));
			}
		}
	}

	private void printMatrix () {
		for (int i = 0; i < graph.length; i++) {
			System.out.println();
			for (int j = 0; j < graph[i].length; j++) {
				System.out.print(graph[i][j] + ", ");
			}
		}
		System.out.println();
	}

	private static void clearVisitedList(boolean[] visitedVertex2) {
		for (int i = 0; i < visitedVertex2.length; i++) {
			visitedVertex2[i] = false;
		}
	}

	public static void main(String[] args) {
		Set<Vertex> V = new LinkedHashSet <Vertex> ();
		/*Vertex vA = new Vertex ("A");
		Vertex vB = new Vertex ("B");
		Vertex vC = new Vertex ("C");
		Vertex vD = new Vertex ("D");
		Vertex vE = new Vertex ("E");
		Vertex vF = new Vertex ("F");
		Vertex vG = new Vertex ("G");*/

		Vertex vA = new Vertex (1);
		Vertex vB = new Vertex (2);
		Vertex vC = new Vertex (3);
		Vertex vD = new Vertex (4);
		Vertex vE = new Vertex (5);
		Vertex vF = new Vertex (6);
		Vertex vG = new Vertex (0);
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
		
		GraphAdjacencyMatrix localGraph = new GraphAdjacencyMatrix ();
		localGraph.createGraph(V, Edges);
		
		localGraph.printGraph ();
		localGraph.printMatrix();
		DFSTestRun (localGraph);

		shortestPathTest (localGraph);
	}

	private static void shortestPathTest(GraphAdjacencyMatrix localGraph) {
		localGraph.runAndPrintShortestPath (1, 4);
		localGraph.runAndPrintShortestPath (5, 6);
		localGraph.runAndPrintShortestPath (1, 6);
		localGraph.runAndPrintShortestPath (3, 6);
	}
	
	public void runAndPrintShortestPath (int s, int t) {
		List<Integer> result = new ArrayList<Integer> ();
		List<Integer> current = new ArrayList<Integer> ();

		shortestPath ( s, t, result, current);
		System.out.println ();
		System.out.println ("Shortest Path from: " + s + " -> " + t);
		for (Integer i:result) {
			System.out.print(i + ", ");
		}
		result.clear();
		current.clear();
		System.out.println ();		
	}

	private static void DFSTestRun(GraphAdjacencyMatrix localGraph) {
		System.out.println();
		for (int i = 0; i < localGraph.graph.length; i++) {
			System.out.println("DFS Traversal: Start: " + i);
			localGraph.dfs(i);
			clearVisitedList (localGraph.visitedVertex);
		}
	}

}
