package com.code.saurabh.array.misc;

/*
 * Given a sorted array, (may include negative, 0, positives), we need to return a index on array where a[i] == i where i is index of array.
 * Amazon interview question
 */
public class A_I_Equal_to_I {
	static int f (int[] a) {
		int s = 0;
		int e = a.length -1;

		while (s <= e) {
			if (s == e) { //in case single element
				if (a[s] == s) {
					return s;
				}
				else {
					return -1;
				}
			}

			if (s+1 == e) { // in case 2 elements
				if (a[s] == s) {
					return s;
				}
				if (a[e] == e) {
					return e;
				}
				else {
					return -1;
				}
			}
			int mid = s + (e -s)/2;
			if (a[mid] == mid) {
				return mid;
			}
			else if (a[mid] > mid) {
				e = mid-1;
			}
			else {
				s = mid+1;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		int[][] input = {
				{-3, -2, -1, 4, 5},
				{-2, 0, 1, 3, 7, 8},
				{-2, 1, 2, 3, 7, 8},
				{0, 1, 2, 3, 4, 5, 6, 7},
		};
		
		for (int i = 0; i < input.length; i++) {
			System.out.println("Result index: " + f (input[i]));
		}
	}

}
