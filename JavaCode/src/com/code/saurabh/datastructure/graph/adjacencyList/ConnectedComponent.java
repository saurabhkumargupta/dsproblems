package com.code.saurabh.datastructure.graph.adjacencyList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.code.saurabh.util.Pair;

/*
http://www.careercup.com/question?id=5136759969021952
*/

public class ConnectedComponent {

	public static void main(String[] args) {
		runTest ("/Users/saurabh.gupta/Documents/Personal/Interview/Round_2_preparation_material/JavaCode/Inputs/Matrix.txt", ",");
		runTest ("/Users/saurabh.gupta/Documents/Personal/Interview/Round_2_preparation_material/JavaCode/Inputs/Matrix_2.txt"," ");
		runTest ("/Users/saurabh.gupta/Documents/Personal/Interview/Round_2_preparation_material/JavaCode/Inputs/Matrix_3.txt"," ");
	}
	
	static void runTest (String filename, String separator) {
		int [][] matrix = null;
		try {
			matrix = readMatrix (filename, separator);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Before:->");
		printInput (matrix);
//		convertToConnectedComponent (matrix);
		convertToConnectedComponent_1 (matrix);
		System.out.println("After:->");
		printInput (matrix);
	}
	
	/**
	 * In this modified and simple version of solution, we not using any localMap.
	 * Here, we simply updating each connected component by 1 value and after processing complete matrix, I am reducing value by 1
	 * and thus maintaining required constraint
	 * 
	 * Steps:
	 * Counter is initialised with 2 because there 1 available in matrix
	 * 	1. When encounter with 1, update all its neighbour with counter.
	 * 	2. When all neighbors of current 1 is updated with counter, then increment counter.
	 * 		While searching for neighbors, search for 1 only
	 * 	3. repeat step 1 and 2
	 * 	4. after Matrix is processed, then decrement non-zero value by 1. and also get max value of Matrix which denotes total
	 * connected components
	 */
	private static void convertToConnectedComponent_1(int[][] matrix) {
		
		Pair<Integer, Integer> newPair = null;
		int counter = 2;
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++){
				if (matrix[i][j] == 1) {
					newPair = new Pair<Integer, Integer> (i,j);
					playNeighbour (newPair, counter, matrix);
					counter++;
				}
			}
		}

		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++){
				if (matrix[i][j] != 0) {
					matrix[i][j]--;
					if (count < matrix[i][j])
						count = matrix[i][j];	
				}
			}
		}
		System.out.println("Total Connected component are: " + count);
	}

	private static void playNeighbour(Pair<Integer, Integer> pos, int counter, int[][] matrix) {
		matrix[pos.getFirst()][pos.getSecond()] = counter;

		Pair<Integer, Integer> left = isValid (pos.getFirst(), pos.getSecond()-1, matrix);
		if (left != null && matrix[left.getFirst()][left.getSecond()] == 1){
			playNeighbour (left, counter, matrix);
		}

		Pair<Integer, Integer> up = isValid (pos.getFirst()-1, pos.getSecond(), matrix);
		if (up != null && matrix[up.getFirst()][up.getSecond()] == 1){
			playNeighbour (up, counter, matrix);
		}

		Pair<Integer, Integer> right = isValid (pos.getFirst(), pos.getSecond()+1, matrix);
		if (right != null && matrix[right.getFirst()][right.getSecond()] == 1){
			playNeighbour (right, counter, matrix);
		}

		Pair<Integer, Integer> down = isValid (pos.getFirst()+1, pos.getSecond(), matrix);
		if (down != null && matrix[down.getFirst()][down.getSecond()] == 1){
			playNeighbour (down, counter, matrix);
		}
	}

	/**
	 * <pre>
	 *  Process 2-D array.
	 *  we are using a temporary map which holds future result. we are not storing result directly into matrix as it will lead to 
	 *  update same index more than once.
	 *  
	 *  So whenever there is non-zero value in a matrix position.
	 *  We use current counter, gets all its neighbors recursively and update them with current counter.
	 *  All these neighbors are stored in a temporary map. Which keeps Map<postion, counter>
	 *  
	 *  After all current neighbors are processed we, increment counter and start looking other non-zero elements.
	 *  There many non-zero elements which has already been processed, they are skipped why checking into tempMap which are 
	 *  keeping. So that we dont process them more than once.
	 *  
	 *  After parsing all matrix, we update matrix with tempMap values.
	 * </pre>
	 * @param matrix
	 */
	private static void convertToConnectedComponent(int[][] matrix) {
		
		Pair<Integer, Integer> newPair = null;
		Map<Pair<Integer, Integer>, Integer> localMap = new HashMap<Pair<Integer, Integer>, Integer> ();
		int counter = 1;
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++){

				if (matrix[i][j] == 1) {
					newPair = new Pair<Integer, Integer> (i,j);
					if (mapContainsData(localMap,newPair) == false){
						updateNeighbour (newPair, counter, localMap, matrix);
						counter++;
					}
				}

			}
		}
		for (Pair<Integer, Integer> pair: localMap.keySet()){
			matrix[pair.getFirst()][pair.getSecond()] = localMap.get(pair);
		}
	}

	private static void updateNeighbour(Pair<Integer, Integer> pos,
			int counter, Map<Pair<Integer, Integer>, Integer> localMap, int[][] matrix) {

		if (mapContainsData(localMap, pos) == true)
			return;
		
		localMap.put(pos, counter);

		Pair<Integer, Integer> left = isValid (pos.getFirst(), pos.getSecond()-1, matrix);
		if (left != null){
			updateNeighbour (left, counter, localMap, matrix);
		}

		Pair<Integer, Integer> up = isValid (pos.getFirst()-1, pos.getSecond(), matrix);
		if (up != null){
			updateNeighbour (up, counter, localMap, matrix);
		}

		Pair<Integer, Integer> right = isValid (pos.getFirst(), pos.getSecond()+1, matrix);
		if (right != null){
			updateNeighbour (right, counter, localMap, matrix);
		}

		Pair<Integer, Integer> down = isValid (pos.getFirst()+1, pos.getSecond(), matrix);
		if (down != null){
			updateNeighbour (down, counter, localMap, matrix);
		}
	}

	private static boolean mapContainsData(
			Map<Pair<Integer, Integer>, Integer> localMap,
			Pair<Integer, Integer> pos) {

		for (Pair<Integer, Integer> pair: localMap.keySet()) {
			if (pair.getFirst() == pos.getFirst()
					&& pair.getSecond() == pos.getSecond()) {
				return true;
			}
		}
		return false;
	}

	private static Pair<Integer, Integer> isValid(int x, int y,
			int[][] matrix) {
			if ( x < 0 || x >= matrix.length
					|| y < 0 || y >= matrix[0].length
					|| matrix[x][y] == 0) {
				return null;
			}
		return new Pair<Integer, Integer> (x,y);
	}

	private static void printInput(int[][] matrix) {

		for (int i = 0; i < matrix.length;i++){
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + ", ");
			}
			System.out.println();
		}
	}

	/**
	 * <pre>
	 * this function reads matrix input from standard input and creates 2-D matrix from this.
	 * </pre>
	 * @param filepath
	 * @param separator
	 * @return
	 * @throws IOException
	 */
	private static int[][] readMatrix(String filepath, String separator) throws IOException {
		FileReader fr = new FileReader (filepath);
		BufferedReader br = new BufferedReader (fr);

		String line = br.readLine();
		if (line != null)
			line = line.trim();
		int rowCount = Integer.parseInt(line);
		int [][] matrix = new int [rowCount][];
		int counter = 0;
		line = br.readLine();
		if (line != null)
			line = line.trim();
		while (line != null) {
				String[] input = line.split(separator);
				int[] local = new int[input.length];
				for (int i = 0; i < input.length; i++) {
					local[i] = Integer.parseInt(input[i].trim());
				}
				matrix[counter] = new int [input.length];
				matrix[counter] = (local);
				line = br.readLine();
				if (line != null)
					line = line.trim();
				counter++;
		}
		br.close();
		return matrix;
	}

}
