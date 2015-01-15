package com.code.saurabh.algorithms.DP;

import com.code.saurabh.array.ArrayUtils;
import com.code.saurabh.matrix.Matrix;
import com.code.saurabh.util.IntPair;


public class MaxAppleInMatrix {

	static Matrix LA = null;

	static int maxAppleWrapper (int[][] input) {
		LA = new Matrix(input.length, input[0].length, -1);

		Matrix inputMat = new Matrix (input);
		return maxApple (new IntPair(0,0), inputMat);
	}
	
	static int maxApple (IntPair point, Matrix input) {
		if (LA.getValue(point) != -1) {
			return LA.getValue(point);
		}
		
		int right = 0;
		int down = 0;
		if (input.rightPointExist(point)) {
			right = maxApple (input.getRightPoint(point), input);
		}
		if (input.downPointExist(point)) {
			down = maxApple (input.getDownPoint(point), input);
		}
		int max = Math.max(right, down);
		LA.setValue(point, input.getValue(point) + max);
		return LA.getValue(point);
	}

	static int maxAppleTopDown (int[][] input) {
		int[][] la = new int [input.length][input[0].length];
		ArrayUtils.initArray(la);
		
		for (int i = 0; i < input.length; i++) {
			int leftToRight = Integer.MIN_VALUE;
			int topToDown = Integer.MIN_VALUE;
			for (int j = 0; j < input[0].length; j++) {
				if (i > 0) {
					leftToRight = la[i-1][j];
				}
				if (j > 0) {
					topToDown = la[i][j-1];
				}
				int max = Math.max(topToDown, leftToRight);

				/*this is consider the negative numbers in the array 
				 * for test case:
				    {-1,-2},
					{0,4} 
			     */
				if (max == Integer.MIN_VALUE)
					la[i][j] = input[i][j];
				else
					la[i][j] = max + input[i][j];
			}
		}
		return la[input.length-1][input[0].length-1];
	}

	public static void main(String[] args) {		
		int [][][] input = {
				{
					{4,2,9},
					{8,5,11},
					{1,2,3},
				},
				{
					{1, 5, 6},
					{2, 4, 7},
					{11, 8, 9},	
				},
				{
					{1, 5, 6},
					{2, 4, 7},
					{1, 8, 9},
				},
				{
					{30, 1, 6, 100},
					{1, 4, 7, 2},
					{31, 90, 5, 11},
					{11, 9, 5, 11},
					{1, 10, 25, 21},
					{3, 20, 15, 31},
				},
				{
					{1,0},
					{2,4}
				},
				{
					{-1,-2},
					{0,4}
				},
				{
					{-1}
				}
		};

		for (int i = 0; i < input.length; i++) {
			System.out.println(" : " + maxAppleWrapper(input[i]));
			System.out.println(" : " + maxAppleTopDown(input[i]));
			System.out.println();
		}
	}

}
