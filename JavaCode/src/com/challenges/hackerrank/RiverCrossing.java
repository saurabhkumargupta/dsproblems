package com.challenges.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Two test cases timed-out
 */
public class RiverCrossing {

	static public class GraphAdjacencyMatrix {

		int[][] graph = null;

		public void createGraph (int[][] input) {
			graph = input;
		}
		
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

		public void runShortestPath (int s, int t) {
			List<Integer> result = new ArrayList<Integer> ();
			List<Integer> current = new ArrayList<Integer> ();

			shortestPath ( s, t, result, current);
			if (result.size() == 0) {
				System.out.println("-1");
			}
			else {
				System.out.println (result.size()-1);
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
	
	public static void main(String[] args) {
		readInput ();
	}

	static void readInput () {
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
		try {
			String[] line = br.readLine().trim().split(" ");
			int M = Integer.valueOf(line[0]);
			int N = Integer.valueOf(line[1]);
			Integer[] distance = inputArrayFromStdin(br, " ");
			Integer[] jumps = inputArrayFromStdin(br, " ");
			
			int[][] matrix = toAdjacencyMatrix (distance, jumps);

			GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix ();
			graph.createGraph(matrix);
			graph.runShortestPath(0, distance.length);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static public Integer[] inputArrayFromStdin (BufferedReader br, String separator) throws IOException {
		String[] input = br.readLine().trim().split(separator);
		Integer[] arr = new Integer[input.length];
		int index = 0;
		for (String s : input) {
			if (!s.equals(" ") && !s.isEmpty()) {
				arr[index] = Integer.valueOf(s.trim());
				index++;
			}
		}
		Integer[] finalArray = new Integer[index];
		for (int i = 0; i < index; i++) {
			finalArray[i] = arr[i];
		}
		return finalArray;
	}
}
