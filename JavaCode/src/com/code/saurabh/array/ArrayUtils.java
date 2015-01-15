package com.code.saurabh.array;

import com.code.saurabh.util.Pair;

public class ArrayUtils {

	public static Pair<Integer, Integer> getNodePairsOfSumFromSortedArray (Integer[] input, int sum) {
		int start = 0;
		int end = input.length - 1;

		boolean singleNodeExist = false;

		for (int i = 0; i < input.length; i++) {
			int CornerSum = input[start] + input[end];
			if (sum == input[start] || input[end] == sum) {
				singleNodeExist = true;
			}
			if (sum == CornerSum) {
				break;
			}
			if (CornerSum < sum ) {
				start++;
			}
			else if (CornerSum > sum) {
				end--;
			}
		}
		
		if (start <= end)
		{
			return new Pair<Integer, Integer> (input[start], input[end]);
		}

		/*Comment this if condition if sum as single node is not allowed*/
		if (singleNodeExist) {
			return new Pair<Integer, Integer> (sum, sum);
		}
		return null;
	}

	public static void initArray (int[][] input, int initvalue) {
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length; j++) {
			input[i][j] = initvalue;
			}
		}
	}

	public static void initArray (int[] input, int initvalue) {
		for (int i = 0; i < input.length; i++) {
			input[i] = initvalue;
		}
	}

	public static void initArray (int[][] input) {
		initArray (input, 0);
	}

	public static void initArray (int[] input) {
		initArray (input, 0);
	}

	public static void printArray (int[] input) {
		printArray (input, 0, input.length-1);
	}

	public static void printArray (int[] input, int startIndex) {
		printArray (input, startIndex, input.length -1);
	}

	public static void printArray (int[] input, int startIndex, int endIndex) {
		for (int i = startIndex; i <= endIndex; i++) {
			System.out.print(input[i] + ", ");
		}
		System.out.println();
	}

	public static void printArray (String input, int startIndex, int endIndex) {
		System.out.println(input.substring(startIndex, endIndex+1));
		System.out.println();
	}

	public static void printArray (int[][] input) {
		for (int i = 0; i < input.length; i++) {
			printArray (input[i]);
		}
	}

	public static void printArray (Integer[][] input) {
		for (int i = 0; i < input.length; i++) {
			printArray (input[i]);
		}
	}

	/**
	 * <pre>
	 * </pre>
	 * @param integers
	 */
	public static void printArray(Integer[] integers) {
		for (int i = 0; i < integers.length; i++) {
			System.out.print(integers[i] + ", ");
		}
		System.out.println();		
	}

	public static void main(String[] args) {
		Integer [][] testData = {
				{1,2,3,4,5},
				{2,4,6,10},
				{1,4,6,10},
		};
		
		for (int i = 0; i < testData.length; i++) {
			Pair<Integer, Integer> result = ArrayUtils.getNodePairsOfSumFromSortedArray (testData[i], 6);
			if (result == null)
				System.out.println("No Result");
			else
				System.out.println(result.getFirst() + ": " + result.getSecond());
		}
	}

}
