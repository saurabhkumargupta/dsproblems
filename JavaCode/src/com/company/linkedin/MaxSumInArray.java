package com.company.linkedin;

import com.code.saurabh.array.ArrayUtils;

/*
 * Write a function that, given a list of integers (both positive and negative) returns the sum of the contiguous subsequence 
 * with maximum sum. Thus, given the sequence (1, 2, -4, 1, 3, -2, 3, -1) it should return 5.
 * 
 */
public class MaxSumInArray {

	static int maxSum (int [] input) {
		int max = Integer.MIN_VALUE;
		int curSum = 0;
		for (int key: input) {
			curSum += key;
			if (max < curSum) {
				max = curSum;
			}
			if (curSum < 0) {
				curSum = 0;
			}
		}
		return max;
	}
	
	static class Result {
		int start;
		int end;
		int sum;
		
		Result () {
			start = 0;
			end = 0;
			sum = Integer.MIN_VALUE;
		}
	};
	static void maxSumSubarray (int [] input) {
		Result max = new Result ();
		Result curSum = new Result ();
		curSum.sum = 0;
		
		for (int i = 0; i < input.length; i++) {

			curSum.sum += input[i];
			curSum.end = i;
			if (max.sum < curSum.sum) {
				max.start = curSum.start;
				max.end = curSum.end;
				max.sum = curSum.sum;
			}

			//this = is required, otherwise in this case {4,16,-9,-10,-1, 21},, it will print un-necessary subarray
			if (curSum.sum <= 0) {
				curSum.sum = 0;
				curSum.start = i+1; // next 
			}
		}
		System.out.println("Sum: " + max.sum);
		System.out.println("SubArray: ");
		ArrayUtils.printArray(input, max.start, max.end);
	}

	public static void main(String[] args) {
		int [][] inputs = {
				{1, 2, -4, 1, 3, -2, 3, -1},
				{-1, -2},
				{-1, 0, -2},
				{-1, -2, -3},
				{4,6,-9,10,-10,1,-30,2,3,45},
				{4,6,-9,10,-10,1},
				{4,16,-9,-10, 1},
				{4,16,-9,-10, 20},
				{4,16,-9,-10,-1, 21},
				{14,16,-9,-10,-1, 10}
		};
		
		for (int[] input: inputs) {
			System.out.println("max Sum :" + maxSum (input));
			maxSumSubarray (input);
			System.out.println();
		}
	}

}
