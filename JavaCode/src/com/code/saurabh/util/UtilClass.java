package com.code.saurabh.util;

import com.code.saurabh.array.ArrayUtils;

public class UtilClass {

	public static int factorial (int N) {
		if (N == 0 || N == 1) {
			return 1;
		}
		return N * factorial (N-1);
	}
	
	public static int binarySearch (int[] input, int value, int left, int right) {
		int mid = left + (right-left)/2;
		
		if (left < 0 || right >= input.length || (left > right)) {
			return -1;
		}

		if (value == input[mid]) {
			return mid;
		}
		else if (value < input[mid]) {
			return binarySearch (input, value, left, mid-1);
		}
		else if (value > input[mid]) {
			return binarySearch (input, value, mid+1, right);
		}
		return -1;
	}

	public static int binarySearch (int[] input, int value) {
		int mid = input.length/2;
	
		int left = 0;
		int right = input.length-1;
		while (mid >= 0 && mid < input.length) {
			mid = left + (right-left)/2;
			if (left > right) {
				return -1;
			}
			if (value == input[mid]) {
				return mid;
			}
			if (value < input[mid]) {
				right = mid-1;
			}
			else if (value > input[mid]) {
				left = mid+1;
			}
		}
		return -1;
	}
	
	static public void swap (int [] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	public static int b_search (int[] input, int value) {
		int left = 0;
		int right = input.length-1;
		while (left <= right) {
			if (left == right) {
				if (input[left] == value) {
					return left;
				}
				else {
					return -1;
				}
			}
			int mid = left + (right-left)/2;
			if (input[mid] == value) {
				return mid;
			}
			else if (value < input[mid]) {
				right = mid-1;
			}
			else {
				left = mid+1;
			}
		}
		return -1;
	}
	
	public static void XorSwap (int[] input, int f, int s) {
		input[f] = input[f] ^ input[s];
		input[s] = input[f] ^ input[s];
		input[f] = input[f] ^ input[s];
	}

	public static boolean isEven (int num) {
		return num % 2 == 0;
	}

	public static boolean isOdd (int num) {
		return num % 2 != 0;
	}

	public static void main(String[] args) {

		//factorialTest ();
		binarySearchTest ();
		//XorTest ();
	}

	@SuppressWarnings("unused")
	private static void XorTest() {
		int[][] inputs = {
				{5, 8, 11, 25, 35, 45, 70, 110},
				{-10, -5, 0, 1, 11, 12, 20, 24, 25},
		};

		for (int[] input: inputs) {
			ArrayUtils.printArray(input);
			XorSwap (input, 2, 4);
			ArrayUtils.printArray(input);
		}
	}

	private static void binarySearchTest() {
		int[][] inputs = {
				{5, 8, 11, 25, 35, 45, 70, 110},
//				{-10, -5, 0, 1, 11, 12, 20, 24, 25},
//				{-10, -5, 0, 1, 11, 12, 20, 24},
				{7,8,9,10,11,12},
		};

		for (int[] input: inputs) {
			for (int i = 0; i < input.length; i++) {
				int key = input[i];
				System.out.println ("Key: " + key + ", Index -> " + binarySearch (input, key, 0, input.length-1));
				System.out.println ("Key: " + key + ", Index -> " + binarySearch (input, key));
				System.out.println ("Key: " + key + ", Index -> " + b_search (input, key));
			}
		}
		int key = 10;
		System.out.println ("Key: " + key + ", Index -> " + binarySearch (inputs[0], key, 0, inputs[0].length-1));
		System.out.println ("Key: " + key + ", Index -> " + binarySearch (inputs[0], key));
		System.out.println ("Key: " + key + ", Index -> " + b_search (inputs[0], key));
	}

	@SuppressWarnings("unused")
	private static void factorialTest() {
		int input[] = {4,1,0,2,5,7};
		for (int val: input) {
			System.out.println ("factorial of: " + val + " -> " + factorial (val));
		}
	}

}
