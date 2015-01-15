package com.code.saurabh.matrix;

import com.code.saurabh.array.ArrayUtils;

/*
 * http://www.geeksforgeeks.org/print-elements-sorted-order-row-column-wise-sorted-matrix/
 * 
 */
public class PrintSortedRowColumnAray {

	/*
	 * This gives kind of O(N^3), but not strictly by the nature that columns are also sorted.
	 * 
	 * Instead of using index array, we can use MinHeap to store next M elements (first from each row) and extract min each time and 
	 * insert next element into minHeap from row from which last element was extracted
	 * (exlplained there)
	 */
	static void printArray (int [][] input) {
		int[] indexArray = new int[input.length];
		ArrayUtils.initArray(indexArray);

		System.out.println();
		for (int i = 0; i < input.length * input[0].length; i++) {
			findMin (indexArray, input);
		}
		System.out.println();
	}

	static private void findMin(int[] indexArray, int[][] input) {
		int min_i = -1;
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < indexArray.length; i++) {
			int j = indexArray[i];
			if (j >= input[0].length) {
				continue;
			}
			if (min > input[i][j]) {
				min = input[i][j];
				min_i = i;
			}
		}
		indexArray[min_i]++;
		System.out.print(min + ", ");
	}
	public static void main(String[] args) {
		int [][] [] input = {
				{	
					{10, 20, 30, 40},
					{15, 25, 35, 45},
					{27, 29, 37, 48},
					{32, 33, 39, 50}
				},
				{	
					{10, 20, 30, 40},
					{15, 25, 35, 45},
					{27, 29, 37, 48},
					{32, 33, 39, 50},
					{51, 55, 57, 60},
				},
				{
					{10, 20, 30, 40},
					{15, 25, 35, 45},
					{27, 29, 37, 48},
					{32, 33, 39, 50},
					{51, 55, 57, 60},
				},
				{
					{10, 11, 12, 13},
					{14, 15, 16, 17},
					{18, 19, 20, 21},
				},
				{
					{10, 11, 12, 13},
					{14, 16, 18, 20},
					{15, 17, 19, 21},
				},
		};
		for (int i = 0; i < input.length; i++) {
			printArray (input[i]);			
		}

	}

}
