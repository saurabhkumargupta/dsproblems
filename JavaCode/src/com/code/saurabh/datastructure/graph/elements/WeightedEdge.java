package com.code.saurabh.datastructure.graph.elements;

public class WeightedEdge extends Edge {

	Integer weight;
	public WeightedEdge(Vertex start, Vertex end, Integer w) {
		super(start, end);
		this.weight = w;
	}

	void setWeight (Integer w) {
		this.weight = w;
	}
	
	public Integer getWeight () {
		return this.weight;
	}

	public Vertex getFirstVertex () {
		return this.edge.getFirst();
	}

	public Vertex getNextVertex () {
		return this.edge.getSecond();
	}

	public String toString () {
		StringBuilder sb = new StringBuilder ();
		sb.append("{<");
		sb.append(edge.getFirst());
		sb.append(",");
		sb.append(edge.getSecond());
		sb.append(">");
		sb.append(":");
		sb.append(this.weight);
		sb.append("}");
		return sb.toString();
	}
}
