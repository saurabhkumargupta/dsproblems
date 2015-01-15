package com.code.saurabh.datastructure.graph.adjacencyList;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.code.saurabh.datastructure.graph.elements.Vertex;
import com.code.saurabh.datastructure.graph.elements.WeightedEdge;

public class WeightedGraph {
	Map<Vertex, Set<WeightedEdge>> graph = new LinkedHashMap<Vertex, Set<WeightedEdge>> ();
	boolean directedGraph = false;
	Set<Vertex> visitedVertex = new LinkedHashSet<Vertex> ();

	public WeightedGraph () {
	}
	
	boolean directedGraph () {
		return directedGraph;
	}
/*
	public void createGraph(Set<Vertex> V, Set<WeightedEdge> E, boolean directed) {
		addVertexToGraph (V);
		directedGraph = directed;
		graph.get(V).add(E);
	}

	public void createGraph(Set<Vertex> V, Set<WeightedEdge> E) {
		createGraph (V, E, directedGraph);
	}
*/
	@SuppressWarnings("unused")
	private void addVertexToGraph(Set<Vertex> V) {

		for (Vertex v: V) {
			addVertexToGraph (v);
		}
	}

	private void addVertexToGraph(Vertex V) {
		if (!graph.containsKey(V))
			graph.put(V, new LinkedHashSet<WeightedEdge> ());
	}

	void addToGraph (Vertex V, WeightedEdge E) {
		graph.get(V).add(E);
	}
	
	Vertex getVertex (Integer key) {
		Iterator<Vertex> iter =  graph.keySet().iterator();
		while (iter.hasNext()) {
			Vertex v = iter.next();
			if (v.equal(key))
				return v;
		}
		return new Vertex (key);
	}

	public void printGraph() {
		for (Vertex vertex : graph.keySet()) {
			System.out.print(vertex + ": ");
			for (WeightedEdge edge: graph.get(vertex)) {
				System.out.print(edge + ", ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		WeightedGraph wG = new WeightedGraph ();

		prepareWeightedGraph (wG);
		wG.printGraph();
	}

	public static void prepareWeightedGraph(WeightedGraph wG) {
		addToWeightedGraph (wG, 0, 1, 4);
		addToWeightedGraph (wG, 0, 7, 8);

		addToWeightedGraph (wG, 1, 2, 8);
		addToWeightedGraph (wG, 1, 7, 11);
		addToWeightedGraph (wG, 1, 0, 4);
		
		addToWeightedGraph (wG, 2, 1, 8);
		addToWeightedGraph (wG, 2, 3, 7);
		addToWeightedGraph (wG, 2, 8, 2);
		addToWeightedGraph (wG, 2, 5, 4);
		
		addToWeightedGraph (wG, 3, 2, 7);
		addToWeightedGraph (wG, 3, 4, 9);
		addToWeightedGraph (wG, 3, 5, 14);
		
		addToWeightedGraph (wG, 4, 3, 9);
		addToWeightedGraph (wG, 4, 5, 10);
		
		addToWeightedGraph (wG, 5, 4, 10);
		addToWeightedGraph (wG, 5, 3, 14);
		addToWeightedGraph (wG, 5, 2, 4);
		addToWeightedGraph (wG, 5, 6, 2);
		
		addToWeightedGraph (wG, 6, 5, 2);
		addToWeightedGraph (wG, 6, 8, 6);
		addToWeightedGraph (wG, 6, 7, 1);
		
		addToWeightedGraph (wG, 7, 6, 1);
		addToWeightedGraph (wG, 7, 8, 7);
		addToWeightedGraph (wG, 7, 1, 11);
		addToWeightedGraph (wG, 7, 0, 8);
		
		addToWeightedGraph (wG, 8, 2, 2);
		addToWeightedGraph (wG, 8, 6, 6);
		addToWeightedGraph (wG, 8, 7, 7);
		
		/*Disconnected Node added*/
		wG.addVertexToGraph(wG.getVertex(9));
	}

	private static void addToWeightedGraph(WeightedGraph wG, Integer s, Integer e, Integer w) {
		Vertex start = null;
		Vertex end = null;

		start = wG.getVertex(s);
		end = wG.getVertex(e);
		wG.addVertexToGraph(start);
		wG.addVertexToGraph(end);
		wG.addToGraph(start, new WeightedEdge (start, end, w));	
	}

}
