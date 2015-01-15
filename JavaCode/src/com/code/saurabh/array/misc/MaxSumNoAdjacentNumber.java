package com.code.saurabh.array.misc;

/*
 * Question: Given an array of positive numbers, find the maximum sum of a subsequence with the constraint that no 2 numbers in the 
 * sequence should be adjacent in the array. So 3 2 7 10 should return 13 (sum of 3 and 10) or 3 2 5 10 7 should return 15 
 * (sum of 3, 5 and 7).Answer the question in most efficient way.
 * 
 * http://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
 * 
 */
public class MaxSumNoAdjacentNumber {

	/* copied from above link's comment section */
	static int foo(int[] arr, int size)
	{
		if(size == 0) return arr[0];
		if(size < 0 ) return 0;
		return Math.max(arr[size]+foo(arr,size-2), foo(arr,size-1));
	}
	
	/* copied from above link */
	static int FindMaxSum(int arr[], int n)
	{
	  int incl = arr[0];
	  int excl = 0;
	  int excl_new;
	  int i;
	 
	  for (i = 1; i < n; i++)
	  {
	     /* current max excluding i */
	     excl_new = (incl > excl)? incl: excl;
	 
	     /* current max including i */
	     incl = excl + arr[i];
	     excl = excl_new;
	  }
	 
	   /* return max of incl and excl */
	   return ((incl > excl)? incl : excl);
	}

	public static void main(String[] args) {
		int [][] input = {
//				{-3,-2,4,5,-3,6}, //Doesnt work with foo (recusrive solution)
//				{-3,-2,-4,-7,-1},// doesnt work with both solution
//				{-1, -3, -5},// doesnt work with both solution
				{5, 5, 10, 100, 10, 5},
				{5,  5, 10, 40, 50, 35},
				{3, 2, 7, 10},
				{3, 2, 5, 10, 7},
				{5, 4, 7, 10, 2},
				{4, 2, 3, 6, 1, 5, 7, 8, 3, 9},
				{4, 5, 17, 3, 12, 29, 0, -25, 25, 28, 16, 16, 16, 11, -10, -4, 3, -25, 10, -20, 1, -9, 23, 13, -25, 9, -24, 15, 5, -20},
		};
		
		for (int i = 0; i < input.length; i++) {
			System.out.println("sum: " + FindMaxSum(input[i], input[i].length));
			System.out.println("foo: " + foo(input[i], input[i].length-1));
			System.out.println();
		}
	}
}
