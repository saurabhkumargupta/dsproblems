package com.code.saurabh.matrix;

import com.code.saurabh.util.IntPair;

/*
 * http://www.careercup.com/question?id=5649927996702720
 * */

public class MaxMatrixPath {

	int[][] tempMatrix = null;
	int maxSum_total_iteration = 0;
	int maxSum_less_expensive_total_iteration = 0;
	
	MaxMatrixPath () {
		tempMatrix = null;
	}

	MaxMatrixPath (int sizeX, int sizeY) {
		tempMatrix = new int [sizeX][sizeY];
		initialise ();
	}

	private void initialise() {
		for (int i = 0; i < tempMatrix.length; i++) {
			for (int j = 0; j < tempMatrix[0].length; j++) {
				tempMatrix[i][j]=Integer.MIN_VALUE;
			}
		}
	}

	int maxSum_less_expensive (int[][] input, IntPair current) {
		int right_sum = 0;
		int down_sum = 0;
		int x = current.getFirst();
		int y = current.getSecond();

		if (tempMatrix[x][y] != Integer.MIN_VALUE) {
			return tempMatrix[x][y];
		}

		maxSum_less_expensive_total_iteration++;
		if (isLastNode (input, current)) {
			return input[input.length][input[0].length];
		}
		if (rightPathValid (input, current)) {
			right_sum = maxSum_less_expensive (input, rightNode(input, current));
		}
		if (downPathValid (input, current)) {
			down_sum = maxSum_less_expensive (input, downNode(input, current));
		}
		int max_sum = Math.max(right_sum, down_sum);
		int sumTillNode = max_sum + input[x][y];
		tempMatrix [x][y] = sumTillNode;
		return sumTillNode;
	}

	int maxSum (int[][] input, IntPair current) {
		int right_sum = 0;
		int down_sum = 0;

		maxSum_total_iteration++;
		if (isLastNode (input, current)) {
			return input[input.length][input[0].length];
		}
		if (rightPathValid (input, current)) {
			right_sum = maxSum (input, rightNode(input, current));
		}
		if (downPathValid (input, current)) {
			down_sum = maxSum (input, downNode(input, current));
		}
		int max_sum = Math.max(right_sum, down_sum);
		return max_sum + input[current.getFirst()][current.getSecond()];
	}

	private IntPair downNode(int[][] input, IntPair current) {
		return new IntPair ((current.getFirst()+1), current.getSecond());
	}

	private IntPair rightNode(int[][] input, IntPair current) {
		return new IntPair (current.getFirst(), (current.getSecond() + 1));
	}

	private boolean downPathValid(int[][] input, IntPair current) {
		if (input.length > (current.getFirst() + 1)){
			return true;
		}
		return false;
	}

	private boolean rightPathValid(int[][] input, IntPair current) {
		if (input[0].length > (current.getSecond() + 1)){
			return true;
		}
		return false;
	}

	private boolean isLastNode(int[][] input, IntPair current) {
		if (current.getFirst() == input.length
				&& current.getSecond() == input[0].length) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		runMultipleRun ();
	}

	private static void runMultipleRun() {
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
			MaxMatrixPath test = new MaxMatrixPath ();
			MaxMatrixPath test_2 = new MaxMatrixPath(input[i].length, input[i][0].length);
			System.out.println("test :->" + test.maxSum(input[i], new IntPair (0,0)) + ", " + test.maxSum_total_iteration);
			System.out.println("test_2:->" + test_2.maxSum_less_expensive(input[i], new IntPair (0,0)) + ", " + test_2.maxSum_less_expensive_total_iteration);
		}

	}

}
