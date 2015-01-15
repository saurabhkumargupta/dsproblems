package com.code.saurabh.datastructure.graph.adjacencyList;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.code.saurabh.datastructure.graph.elements.Edge;
import com.code.saurabh.datastructure.graph.elements.Vertex;
import com.code.saurabh.datastructure.queue.Queue;

public class GraphTraversal extends GraphAdjacencyList {

	void bfs (Vertex root) {
		Queue queue = new Queue ();
		if (root == null) {
			return;
		}

		queue.enqueue(root);
		while (!queue.isEmpty()) {
			Vertex node = (Vertex) queue.dequeue();
			if (visitedVertex.contains(node)) {
				continue;
			}
			visitedVertex.add(node);
			System.out.println( node + ", ");
			enqueuAllchilds (node, queue);
		}
	}

	private void enqueuAllchilds(Vertex node, Queue queue) {
		Iterator<Vertex> iter = graph.get(node).iterator();
		while (iter.hasNext()) {
			Vertex v = iter.next();
			if (!visitedVertex.contains(v))
				queue.enqueue(v);
		}
	}

	public static void main(String[] args) {
		Set<Vertex> V = new LinkedHashSet <Vertex> ();
		Vertex vA = new Vertex ("A");
		Vertex vB = new Vertex ("B");
		Vertex vC = new Vertex ("C");
		Vertex vD = new Vertex ("D");
		Vertex vE = new Vertex ("E");
		Vertex vF = new Vertex ("F");
		Vertex vG = new Vertex ("G");

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
		
		GraphTraversal localGraph = new GraphTraversal ();
		localGraph.createGraph(V, Edges);
		
		localGraph.printGraph ();
		BFSTestRun (localGraph);
	}

	private static void BFSTestRun(GraphTraversal adjacencyListGraph) {
		Set<Vertex> V = adjacencyListGraph.graph.keySet();
		Iterator<Vertex> iter = V.iterator();

		while (iter.hasNext()) {
			Vertex v = iter.next();
			System.out.println("BFS Traversal: Start: " + v.getValue());
			adjacencyListGraph.bfs(v);
			adjacencyListGraph.visitedVertex.clear();
		}
	}
}
