package com.code.saurabh.datastructure.graph.adjacencyList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.code.saurabh.datastructure.graph.elements.Vertex;
import com.code.saurabh.datastructure.graph.elements.WeightedEdge;

//http://en.wikipedia.org/wiki/Dijkstra's_algorithm
/**
 * For a given source vertex (node) in the graph, the algorithm finds the path with lowest cost (i.e. the shortest path) between that vertex and every other vertex. 
 * It can also be used for finding costs of shortest paths from a single vertex to a single destination vertex by stopping the algorithm once the shortest path to the destination
 * vertex has been determined.
 * <Shortest path From single source to all other vertices in graph>
 * @author saurabh.gupta
 *
 */
public class Dijkshtra {
	
	WeightedGraph weightedGraph = null;
	Map<Vertex, Integer> distanceMatrix = new HashMap <Vertex, Integer> ();

	Dijkshtra (WeightedGraph graph) {
		this.weightedGraph = graph;
	}

	void shortestPathAlgorithm (Vertex sV) {
		Set<Vertex> uV = new HashSet<Vertex> ();
		uV.addAll(weightedGraph.graph.keySet());
		
		Set<Vertex> V = new HashSet<Vertex> ();
		initialiseDistanceMatrix (sV, weightedGraph.graph.keySet());
		
		while (!uV.isEmpty()) {
			Vertex node = getNearestVertex (distanceMatrix, uV);
			if (V.contains(node)) {
				continue;
			}
			/*In case some nodes of graph are unreachable*/
			if (node == null || distanceMatrix.get(node) == Integer.MAX_VALUE) {
				break;
			}
			Set<WeightedEdge> neighbours = weightedGraph.graph.get(node);
			updateNeighbour (distanceMatrix, neighbours, node);
			uV.remove(node);
			V.add(node);
		}
	}

	private void updateNeighbour(Map<Vertex, Integer> distanceMatrix,
			Set<WeightedEdge> neighbours, Vertex node) {

		Iterator<WeightedEdge> iter = neighbours.iterator();
		Integer d = distanceMatrix.get(node);
		while (iter.hasNext()) {
			WeightedEdge e = iter.next();
			Integer curD = distanceMatrix.get(e.getNextVertex ());
			if ( (e.getWeight() + d ) < curD) {
				distanceMatrix.put(e.getNextVertex (), (e.getWeight() + d) );
			}
		}
	}

	/*Return node which has smallest distance from source to that node*/
	private Vertex getNearestVertex(Map<Vertex, Integer> distanceMatrix,
			Set<Vertex> uV) {
		Iterator<Vertex> iter = distanceMatrix.keySet().iterator();
		Integer min = Integer.MAX_VALUE;
		Vertex minV = null;
		while (iter.hasNext()) {
			Vertex v = iter.next();
			if (distanceMatrix.get(v) < min && uV.contains(v)) {
				min = distanceMatrix.get(v);
				minV = v;
			}
		}
		return minV;
	}

	private void initialiseDistanceMatrix(Vertex sV, Set<Vertex> allVertex) {
		Iterator<Vertex> iter = allVertex.iterator();
		
		while (iter.hasNext()) {
			Vertex v = iter.next();
			distanceMatrix.put(v, Integer.MAX_VALUE);
		}
		/*Initialize first vertex with 0*/
		distanceMatrix.put(sV, 0);
	}

	public void printDistanceMatrix (Vertex source) {
		Iterator<Vertex> iter = distanceMatrix.keySet().iterator();
		System.out.println("Source Vertex: " + source);
		while (iter.hasNext()) {
			Vertex v = iter.next();
			System.out.println("Vertex: " + v + ", distance From source: " + distanceMatrix.get(v));
		}
	}

	public static void main(String[] args) {
		WeightedGraph wG = new WeightedGraph ();
		WeightedGraph.prepareWeightedGraph (wG);

		Vertex sourceVertex = wG.getVertex(0);

		Dijkshtra algo = new Dijkshtra (wG);

		algo.initialiseDistanceMatrix(sourceVertex, wG.graph.keySet());
		
		System.out.println("distance matrix before algo");
		algo.printDistanceMatrix(sourceVertex);
		algo.shortestPathAlgorithm(sourceVertex);
		System.out.println();
		System.out.println("distance matrix after algo");
		algo.printDistanceMatrix(sourceVertex);
	}

}
