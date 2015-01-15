package com.code.saurabh.datastructure.graph.adjacencyList;

/*
 * http://www.careercup.com/question?id=5725353829990400
 * */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.code.saurabh.datastructure.graph.elements.Vertex;


public class PathFinder {
	Set<Vertex> vertex = new HashSet< Vertex  > ();
	Map<Vertex, List<Vertex> > graph = new HashMap <Vertex, List<Vertex>> ();

	PathFinder () {
	}
	
	void createGraph (int[][] matrix) {
		List<Vertex> neighbour = null;
		for (int i =0; i < matrix.length; i++) {
			for (int j =0; j < matrix[i].length; j++) {
				if (matrix[i][j] != 0) {
					Vertex v = null;
					if ((v = getVertex (i,j)) == null){
						v = new Vertex (i,j);
						vertex.add(v);
					}
					neighbour = getNeighbour (matrix, v);
					graph.put(v, neighbour);
				}
			}
		}
	}
	
	private List<Vertex> getNeighbour(int[][] matrix, Vertex v) {
		List<Vertex> result = new ArrayList<Vertex> ();
		Vertex left = nodeExist (matrix, v.getX(), v.getY()-1);
		Vertex up = nodeExist (matrix, v.getX()-1, v.getY());
		Vertex right = nodeExist (matrix, v.getX(), v.getY()+1);
		Vertex down = nodeExist (matrix, v.getX()+1, v.getY());
		
		if (left != null)
			result.add(left);
		if (up != null)
			result.add(up);
		if (right != null)
			result.add(right);
		if (down != null)
			result.add(down);
		return result;
	}

	private Vertex nodeExist(int[][] matrix, int x, int y) {
		if (x < 0 || x >= matrix.length
				|| y < 0 || y >= matrix[0].length
				||matrix[x][y] == 0) 
		{
			return null;
		}
		Vertex v = getVertex(x,y);
		if (v == null)
		{
			v = new Vertex (x, y);
			vertex.add(v);
		}
		return v;
	}

	Vertex getVertex (int i, int j) {
		Iterator<Vertex> iter = vertex.iterator();
		while (iter.hasNext()) {
			Vertex v = iter.next();
			if (v.equal(i, j))
				return v;
		}
		return null;
	}
	
	void printGraph () {		
		System.out.println ("Given Graph");
		System.out.println ("vertex Size: " + vertex.size());
		Iterator<Vertex> ver = vertex.iterator();
		while (ver.hasNext()) {
			Vertex v = ver.next();
			System.out.print("Keys: (" + v.getX() + ", " + v.getY() + ") ---> ");
			printNeighbour (v);
			System.out.println ();
		}
	}
	
	private void printNeighbour(Vertex v) {
		List<Vertex> n = graph.get(v);
		for (Vertex local: n) {
			System.out.print ("(" + local.getX() + ", " + local.getY() + "), ");
		}
	}

	void getShortestPath (Vertex v1, Vertex v2, 
			List<Vertex> current, List<Vertex> result) {
		current.add(v1);
		if (v1.equals(v2)) {
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

		List<Vertex> N = graph.get(v1);
		for (Vertex v: N) {
			if (!current.contains(v)) {
				getShortestPath (v, v2, current, result);
				current.remove(v);
			}
		}
		return;
	}

	public static void main(String[] args) {
		int [][] matrix = {
				{1,1,1,1},
				{1,1,0,1},
				{1,0,1,1},
				{1,1,1,1}
		};
		
		PathFinder graph = new PathFinder ();
		graph.createGraph(matrix);

		List<Vertex> current = new ArrayList<Vertex> ();
		List<Vertex> result = new ArrayList<Vertex> ();

		graph.printGraph ();
		
		graph.getShortestPath (graph.getVertex(1,0), graph.getVertex(3,2), current, result);
		graph.printResult (result);

		Vertex v = graph.getVertex(1, 1);
		System.out.println (v.toString());
	}

	public void printResult(List<Vertex> resultList) {
		System.out.println ("Result :");
		for (Vertex v : resultList) {
			System.out.print ("(" +v.getX() + ", " + v.getY() + ")");
		}
		System.out.println ();
	}
}
