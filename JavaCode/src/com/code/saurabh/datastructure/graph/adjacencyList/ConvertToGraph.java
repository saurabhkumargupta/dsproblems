package com.code.saurabh.datastructure.graph.adjacencyList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.code.saurabh.datastructure.graph.adjacencyMatrix.GraphAdjacencyMatrix;


public class ConvertToGraph {

	public static void main(String[] args) {
		runMultipleRun ();
	}

	private static void runMultipleRun () {
		Integer [][] distance = { 
				{2, 1, 2},
				{1, 1, 2, 2, 3, 2, 2},
				{2, 1, 4},
				{1, 2, 2},
				{1, 1, 4},
				{2, 2, 1, 3, 2, 2, 2, 2}
		};
		Integer [][] jumpAvailable = { 
				{1, 3},
				{1, 3, 7},
				{3, 1, 5},
				{1, 3, 4},
				{1, 2, 5},
				{1, 2, 4}
		};

		for (int i = 0; i < distance.length; i++) {
			int[][] matrix = toAdjacencyMatrix (distance[i], jumpAvailable[i]);
			//printMatrix (matrix);

			GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix ();
			graph.createGraph(matrix);
			graph.runAndPrintShortestPath(0, distance[i].length);
		}
	}

	@SuppressWarnings("unused")
	private static void printMatrix(int[][] matrix) {

		for (int i = 0; i < matrix.length; i++) {
			System.out.println();
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(" " + matrix[i][j]);
			}
		}
	}

	private static int[][] toAdjacencyMatrix(Integer[] distance,
			Integer[] jumpAvailable) {
		List<Integer> dist = new ArrayList<Integer> (Arrays.asList(distance));
		dist.add(0, new Integer(0));
		int[][] matrix = new int [dist.size()][dist.size()];
		for (int i = 0; i < dist.size(); i++) {
			for (int j = i+1; j < dist.size(); j++) {
				boolean pathExist = isPathExist (i, j, jumpAvailable, dist);
				if (pathExist) {
					matrix[i][j] = 1;
					matrix[j][i] = 1;
				}
			}
		}
		return matrix;
	}

	private static boolean isPathExist(int i, int j, Integer[] jumpAvailable,
			List<Integer> dist) {
		int sum = 0;
		i++;
		for (; i <= j; i++) {
			sum += dist.get(i);
		}
		
		for (int k = 0; k < jumpAvailable.length; k++)
		{
			if (jumpAvailable[k] == sum) {
				return true;
			}
		}
		return false;
	}

}
