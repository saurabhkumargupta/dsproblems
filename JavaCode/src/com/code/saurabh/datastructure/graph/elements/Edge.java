package com.code.saurabh.datastructure.graph.elements;

import com.code.saurabh.util.Pair;

public class Edge {
	Pair<Vertex, Vertex> edge = new Pair<Vertex, Vertex> ();
	
	public Edge (Vertex start, Vertex end) {
		edge.setFirst(start);
		edge.setSecond(end);
	}
	
	public Pair<Vertex, Vertex> getEdge () {
		return edge;
	}
	
	public String toString () {
		StringBuilder sb = new StringBuilder ();
		sb.append(edge.getFirst());
		sb.append("<--->");
		sb.append(edge.getSecond());
		return sb.toString();
	}
}
