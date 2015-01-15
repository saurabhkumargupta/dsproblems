/**
 * 
 */
package com.code.saurabh.array;

import com.code.saurabh.util.IntPair;

/**
 * <pre>
 * Given a unsorted array
 * 3, 5, 1, 9 ,13, 7, 14, 2 ,4, 6, 8, 11
 * 
 * print subsequence which are increasing in order and have maximum length
 * for above:
 * increasing subsequece are:
 * 3, 5, 9, 13, 14
 * 1, 2, 4, 6, 8, 11
 * 
 * maximum lengh subsequence is : 1, 2, 4, 6, 8, 11
 * </pre>
 * <pre>
 * 
 * Algo
 * we can create a 2-D array from this array temp.

	1. initialise 2-D array with all 0
	2. for each j > i && a[j] > a[i], temp[i][j] = 1 
	O(n^2)
	
	3. now we can run DFS algo on this 2-D array to figure our max size array 
		i) this DFS can be run either by creating a graph and run it on it
		ii)or we can take another O( n ) size array which stores max for each node of graph
	its like finding out max path from root-to-leaf in a tree
	overall complexity is O( n ^ 2 )
	
	3rd point again
	After creating 2D array, we are creating an array (DPArray) of Integer Pair of having <count,Index> of size N
	i.e. DPArray[i] = {5, 3}, means starting from ith index, total array size is 5 and next element in sequence is at index 3
	
	For printing, we can following this DPArray and print sequence.
	
</pre>
 * @author saurabh.gupta
 *
 */
public class MaxIncreadingSubsequence {

	static void maxIncreasingSubsequence (int [] input) {
		int [][] temp = new int[input.length][input.length];
		IntPair[] DPArray = new IntPair[input.length];

		ArrayUtils.initArray(temp);
		for (int i = 0; i < DPArray.length; i++) {
			DPArray[i] = new IntPair (0, -1);
		}
		
		//prepare 2D temp array
		//
		for (int j = 1; j < input.length; j++) {
			for (int i = 0; i < j; i++) {
				if (input[i] < input[j]) {
					temp[i][j] = 1;
				}
			}
		}
		
		for (int i = 0; i < temp.length; i++) {
			if (DPArray[i].getFirst() == 0) {
				RF (i, temp, DPArray);
			}
		}
		
		int maxCountIndex = -1;
		int maxCount = 0;
		for (int i = 0; i < DPArray.length; i++) {
//			System.out.println("Index: "+ i + ", Count: " + DPArray[i].getFirst() + ", Index: " + DPArray[i].getSecond());
			 
			if (maxCount < DPArray[i].getFirst()) {
				maxCount = DPArray[i].getFirst();
				maxCountIndex = i;
			}
		}
		
		ArrayUtils.printArray(temp);
		
		System.out.println("output: Size: " + maxCount + ", Start Index Of Input Array: " + maxCountIndex);
		int index = maxCountIndex;
		while (true) {
			System.out.print (input[index] + ", ");
			index = DPArray[index].getSecond();
			if (index == -1) {
				break;
			}
		}
	}
	/**
	 * <pre>
	 * </pre>
	 * @param i
	 * @param temp
	 * @param dPArray
	 */
	private static int RF(int index, int[][] temp, IntPair[] DPArray) {
		if (DPArray[index].getFirst() > 0) {
			return DPArray[index].getFirst();
		}
		int count = 0;
		int maxCount = 0;
		int maxIndex = -1;
		
		for (int j = 0; j < temp[0].length; j++) {//Number of columns only from temp array
			if (temp[index][j] == 1) {
				count = RF(j, temp, DPArray);
				if (count > maxCount) {
					maxCount = count;
					maxIndex = j;
				}
			}
		}

		//TODO: need to check if first condition is valid / and required  ?
		if (DPArray[index].getFirst() > maxCount
				|| DPArray[index].getFirst() == 0) {
			DPArray[index].setFirst(maxCount + 1);
			DPArray[index].setSecond(maxIndex);
		}
		return DPArray[index].getFirst();
	}
	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		int [] [] input = {
				{3, 5, 1, 9 ,13, 7, 14, 2 ,4, 6, 8, 11},
				{6,5,4,3,2,1},
				{1,2,3,4,5,6,7},
				{10,2,3,11,5,6,7,13},
		};
		for (int i = 0; i < input.length; i++) {
			System.out.println("Input");
			ArrayUtils.printArray(input[i]);
			maxIncreasingSubsequence (input[i]);
			System.out.println();
			System.out.println();
		}
	}
}
