/**
 * 
 */
package com.code.saurabh.matrix;

import com.code.saurabh.array.ArrayUtils;
import com.code.saurabh.util.UtilClass;

/**
 * @author saurabh.gupta
 * given a matrix you have to find out all the ways to reach (n,n) from (0,0) . You can go either right or down
 * 
 */
public class AllPaths {

	//Returning total number of path only
	static void allPaths (int n, int m) {
		int[][] arr = new int[n][m];
		ArrayUtils.initArray(arr);
		arr[0][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (j-1 >= 0) { // if left of current pos exist
					arr[i][j] += arr[i][j-1];
				}
				if (i-1 >= 0) {  // if up  of current pos exist
					arr[i][j] += arr[i-1][j];
				}
			}
		}
		System.out.println("Count: " + arr[n-1][m-1]);
	}

	/*
		http://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
		The time complexity of this recursive solution is exponential. 
	*/
	static int numberOfPaths(int m, int n)
	{
	   // If either given row number is first or given column number is first
	   if (m == 1 || n == 1)
	        return 1;
	 
	   // If diagonal movements are allowed then the last addition
	   // is required.
	   return  numberOfPaths(m-1, n) + numberOfPaths(m, n-1); 
	           // + numberOfPaths(m-1,n-1);
	}

	static void count (int m, int n) {
		int val = UtilClass.factorial((m-1 + n-1)) / (UtilClass.factorial (m-1) * UtilClass.factorial (n-1));
		System.out.println("count: " + val);
//		(m-1 + n-1)!/(m-1)!(n-1)!
	}
	
	static void printPaths (String tempString, int i, int j, int m, int n, char [][] arr) {
		String newString = tempString + arr[i][j];
		if (i == m -1 && j == n-1) {
			System.out.println(newString);
			return;
		}
		//right
		if (j+1 < n) {
			printPaths (newString, i, j+1, m, n, arr);
		}
		//down
		if (i+1 < m) {
			printPaths (newString, i+1, j, m, n, arr);			
		}
	}
	public static void main(String[] args) {
		int [] [] input = {
				{3, 3},
				{4, 4},
				{3, 4},
		};
		
		for (int i = 0; i < input.length; i++) {
			System.out.println();
			System.out.print("allPaths->");
			allPaths (input[i][0], input[i][1]);
			System.out.print("numberOfPaths:");
			System.out.println(numberOfPaths (input[i][0], input[i][1]));
			count (input[i][0], input[i][1]);
		}

		char [] [] pathInput = {
				{'a', 'b', 'c', 'z'},
				{'d', 'e', 'f', 'y'},
				{'g', 'h', 'i', 'x'},
		};
		printPaths ("", 0, 0, pathInput.length, pathInput[0].length, pathInput);
	}
}
