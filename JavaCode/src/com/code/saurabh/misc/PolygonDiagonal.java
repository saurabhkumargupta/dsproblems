/*http://www.careercup.com/question?id=5653937818435584 */
package com.code.saurabh.misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.code.saurabh.util.UtilClass;

public class PolygonDiagonal {

	public static void main(String[] args) {
		try {
			readInput ("/Users/saurabh.gupta/Documents/Personal/Interview/Round_2_preparation_material/JavaCode/Inputs/polygon.txt");
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void readInput(String filename) throws NumberFormatException, IOException {
		File file = new File (filename);
		if (!file.exists()) {
			return;
		}
		
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader (filename);
			br = new BufferedReader (fr);
			int totalTest = Integer.parseInt(br.readLine());
			for (int i = 0; i < totalTest; i++) {
				String[] NK = br.readLine().split("\\s+");
				int N = Integer.parseInt(NK[0]);
				int K = Integer.parseInt(NK[1]);
				System.out.println("N: " + N + ", K: " + K + " -> " + calculateDiag (N, K));
			}
		}
		finally {
			br.close();
		}
	}

	static int calculateDiag (int N, int K) {
		/*It can create a diagonal with all other vertexes than immediate neighbors and its own vertex (count 3) */
		int maxDiagonal = N-3; //Maximum number of diagonals from a vertex
		int totalDiagonal = 0;
		if (maxDiagonal <= 0) {
			return 0;
		}
		
		if (K > maxDiagonal) {
			return 0;
		}
		
		/*This method calculate how many diagonal can be created from a single vertex given constraint*/
		int pointDiagonal = singleVertexDiagonal (K, maxDiagonal);
		
		/*this is trimmed version*/
		totalDiagonal = pointDiagonal * N;
		if (K == 1) {
			totalDiagonal = totalDiagonal/2;
		}
		/* This is extended version. which is trimmed above
		 * if (pointDiagonal == 1) {
			if (K != 1) {
				totalDiagonal = N;
			}
			else {
				totalDiagonal = N/2;
			}
		}
		else {
			if (K != 1) {
				totalDiagonal = N * pointDiagonal;
			}
			else {
				totalDiagonal = (N * pointDiagonal )/2;
			}
		}*/
		return totalDiagonal;
	}

	private static int singleVertexDiagonal(int k, int maxDiagonal) {
		return UtilClass.factorial (maxDiagonal) / (UtilClass.factorial (maxDiagonal-k) * UtilClass.factorial (k));
	}
}
