/**
 * 
 */
package com.code.saurabh.array;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

/**
 * 
 * @author saurabh.gupta
 * 1. Two sorted arrays are given. Find kth smallest element in log(k)
 * 	http://www.geeksforgeeks.org/forums/topic/kth-smallest-element-in-the-union-of-the-arrays-in-a-logarithmic-time-algorithm-2/
 *  (code)
 * 2. Find median of two given sorted array
 * 	http://www.geeksforgeeks.org/median-of-two-sorted-arrays/
 * 	(Not written code for this.)
 */


//SOMETHING WRONG WITH some cases
public class KthSmallest_Median {
	
	static int kthsmallest (int [] a, int [] b, int k) {
		if (a.length == 0 && k <= b.length) {
			return b[k-1];
		}
		if (b.length == 0 && k <= a.length) {
			return a[k-1];
		}

		//if both array of size 1 and k is either 0 or 1
		if (a.length == 1 && b.length == 1) {
			if (k > 2) {
				return -1;
			}
			else {
				if (k == 1) {
					return Math.min(a[0], b[0]);//(a[0] < b[0]) ? a[0] : b[0];
				}
				else {
					return Math.max(a[0], b[0]);//(a[0] > b[0]) ? a[0] : b[0];
				}
			}
		}

		if (a.length == 1) {
			int index = getProperIndexOfNumber (b, a[0]);
			if (index == -1) {
				System.out.println("ERROR:");
				return -1;
			}
			if (k-1 < index) {
				return b[k-1];
			}
			else if (k-1 > index) {
				k = k - (index-1);
				return b[k-1];
			}
			else
				return a[0];
		}

		if (b.length == 1) {
			int index = getProperIndexOfNumber (a, b[0]);
			if (index == -1) {
				System.out.println("ERROR:");
				return -1;
			}
			if (k-1 < index) {
				return a[k-1];
			}
			else if (k-1 > index) {
				k = k - (index-1);
				return a[k-1];
			}
			else
				return b[0];
		}


		int mid_a = a.length /2;
		int mid_b = b.length /2;
		
		if (a[mid_a] >= b[mid_b]) {
			if (k < (mid_a + mid_b+2) ) {
				return kthsmallest (Arrays.copyOfRange (a, 0, mid_a), b, k);
			}
			else if (k > (mid_a + mid_b +2) ) {
				return kthsmallest (a, Arrays.copyOfRange (b, mid_b+1, b.length), k-(mid_b+1));
			}
			else {
				return kthsmallest (Arrays.copyOfRange (a, 0, mid_a+1), Arrays.copyOfRange (b, mid_b, b.length), k-mid_b);
			}
		}
		else {// if (a[mid_a] < b[mid_b]) {
			if (k < (mid_a + mid_b+2) ) {
				return kthsmallest (a, Arrays.copyOfRange (b, 0, mid_b), k);
			}
			else if (k > (mid_a + mid_b +2) ) {
				return kthsmallest (Arrays.copyOfRange (a, mid_a+1, a.length), b, k-(mid_a+1));
			}
			else {
				return kthsmallest (Arrays.copyOfRange (a, mid_a, a.length), Arrays.copyOfRange (b, 0, mid_b+1), k-mid_a);
			}
		}
	}
	
	private static int getProperIndexOfNumber(int[] b, int num) {
		int i = 0;
		int j = b.length-1;
		while (i < j) {
			if (num < b[i] && (i == 0 || num > b[i-1])) {
				return i;
			}
			if (num > b[j] && (j == b.length-1 || num < b[j+1])) {
				return j+1;
			}
			int mid = i + (j-i)/2;
			if (num > b[mid] && num < b[mid+1]) {
				return mid+1;
			}
			if (num > b[mid]) {
				i = mid+1;
			}
			else {
				j = mid-1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int [] input = {10,20,30,40,50,60,70};
		
		System.out.println(Arrays.toString(input));
		System.out.println(Arrays.toString(Arrays.copyOfRange(input, 2, 5)));
		
//		int a [] = {4, 8, 11, 15, 17, 19, 21};
//		int b [] = {3, 5, 6, 10};
		
		int a [] = {4, 8, 11, 15, 17, 19, 21};
		int b [] = {3, 5, 6, 10, 12};
		
		
		int [] mix = new int [a.length + b.length];
		mix = ArrayUtils.addAll(a, b);
		Arrays.sort(mix);
		int k = 0;
		System.out.println("\nArray: \n" + Arrays.toString(mix));
		for (int i = 1; i <=a.length + b.length ; i++) {
			System.out.println("\n" + i + "th element: " + mix[i-1]);
//			System.out.println("Third: " + kthsmallest (a, b, i));
			System.out.println("Third: " + findKthSmallest(a, a.length, b, b.length, i-1));
		}
		
//		k = 5;
//		System.out.println("\n" + k + "th element: " + mix[k-1]);
//		System.out.println(k + "th element: " + kthsmallest (a, b, k));
//
//		k = 6;
//		System.out.println("\nArray: \n" + Arrays.toString(mix));
//		System.out.println("\n" + k + "th element: " + mix[k-1]);
//		System.out.println("\nFirst: " + findKthSmallest (a, a.length, b, b.length, k));
//		System.out.println("Second: " + findKthSmallest_1 (a, a.length, b, b.length, k));
//		System.out.println("Third: " + kthsmallest (a, b, k));
		
//		k = 7;
//		System.out.println("\n" + k + "th element: " + mix[k-1]);
//		System.out.println("\nFirst: " + findKthSmallest (a, a.length, b, b.length, k));
//		System.out.println("Second: " + findKthSmallest_1 (a, a.length, b, b.length, k));
//		System.out.println("Third: " + kthsmallest (a, b, k));
		
		printMedian(a, b);
	}

	static void printMedian (int a[], int b[]) {
		int totalLength = a.length + b.length;
		System.out.println("\nMedian calculation");
		System.out.println("First Method");
		if (totalLength % 2 == 0) {
			int first = findKthSmallest (a, a.length, b, b.length, (totalLength / 2));
			int second = findKthSmallest (a, a.length, b, b.length, (totalLength / 2) - 1 );
			System.out.println("" + ((double) first + (double) second)/2);
		}
		else {
			System.out.println("" + findKthSmallest (a, a.length, b, b.length, (totalLength / 2)));
		}

		System.out.println("Second Method");
		if (totalLength % 2 == 0) {
			int first = findKthSmallest (a, a.length, b, b.length, (totalLength / 2));
			int second = findKthSmallest (a, a.length, b, b.length, (totalLength / 2) - 1 );
			System.out.println("" + ((double) first + (double) second)/2);
		}
		else {
			System.out.println("" + findKthSmallest (a, a.length, b, b.length, (totalLength / 2)));
		}
	}

	//giving wrong result in some cases
	//copied
	static int findKthSmallest_1(int A[], int m, int B[], int n, int k) {
		assert(m >= 0); assert(n >= 0); assert(k > 0); assert(k <= m+n);

		int i = (int)((double)m / (m+n) * (k-1));
		int j = (k-1) - i;

		assert(i >= 0); assert(j >= 0); assert(i <= m); assert(j <= n);
		// invariant: i + j = k-1
		// Note: A[-1] = -INF and A[m] = +INF to maintain invariant
		int Ai_1 = ((i == 0) ? Integer.MIN_VALUE : A[i-1]);
		int Bj_1 = ((j == 0) ? Integer.MIN_VALUE : B[j-1]);
		int Ai   = ((i == m) ? Integer.MAX_VALUE : A[i]);
		int Bj   = ((j == n) ? Integer.MAX_VALUE : B[j]);

		if (Bj_1 < Ai && Ai < Bj)
			return Ai;
		else if (Ai_1 < Bj && Bj < Ai)
			return Bj;

		assert((Ai > Bj && Ai_1 > Bj) || 
				(Ai < Bj && Ai < Bj_1));

		// if none of the cases above, then it is either:
		if (Ai < Bj)
			// exclude Ai and below portion
			// exclude Bj and above portion
			return findKthSmallest(Arrays.copyOfRange (A, i+1, m), m-i-1, B, j, k-i-1);
//			return findKthSmallest(A+i+1, m-i-1, B, j, k-i-1);
		else /* Bj < Ai */
			// exclude Ai and above portion
			// exclude Bj and below portion
			return findKthSmallest(A, i, Arrays.copyOfRange(B, j+1, n), n-j-1, k-j-1);
//			return findKthSmallest(A, i, B+j+1, n-j-1, k-j-1);
	}
	
	//copied
	/*
	 * 
	 */
	static int findKthSmallest(int [] a, int n, int [] b, int m, int k)
	{
		if (k > n + m) {
			System.out.println("K is too large");
			return -1;
		}
		//If either of array has single element
		if(n == 1 || m == 1)
		{
			if(n == 1)
			{
				/* if b has more than k elements, then compare kth element of b with 0th element of a
				 * case 1: if kth element of b is smaller tha 0th of a, then return b[k]
				 * case 2: if b[k] is greater than a[0]. then bigger between a[0] and b[k-1] will be returned;
				 * case 3: else a[0] 
				 */
				if(m > k)
				{
					if (b[k] < a[0]) //case 1:
						return b[k];
					else if (k != 0 && b[k-1] > a[0])//case 2:
						return b[k-1];
					else //case 3:
						return a[0];
				}
				else if(b[m-1] > a[0])
					return b[m-1];
				else
					return a[0];
			}
			else
			{
				if(n > k)
				{
					if (a[k] < b[0])
						return a[k];
					else if (k != 0 && a[k-1] > b[0])
						return a[k-1];
					else
						return b[0];
				}
				else if(a[n-1] > b[0])
					return a[n-1];
				else
					return b[0];
			}
		}
		
		//if all elements of array b is greater than a
		if(a[n-1] < b[0])
		{
			if(k < n)
				return a[k];
			else
				return b[k - n];
		}
		//if all elements of array a is greater than b
		else if(b[m-1] < a[0])
		{
			if(k < m)
				return b[k];
			else
				return a[k - m];
		}
		
		//get middle of both array
		int mid_a = (n / 2);
		int mid_b = (m / 2);
		if(a[mid_a] < b[mid_b])
		{
			if(k < mid_a + mid_b + 1)
				return findKthSmallest(a, n, b, m - mid_b, k);

			return findKthSmallest(
					Arrays.copyOfRange(a, mid_a, n), n - mid_a, //subarray a, and size of a
					b, m , // subarray b and size of b
					k - mid_a); // new size of k
		}
		else
		{
			if(k < mid_a + mid_b + 1)
				return findKthSmallest(b, m, a, n - mid_a, k);

			return findKthSmallest(
					Arrays.copyOfRange(b, mid_b, m), m - mid_b, 
					a, n ,
					k - mid_b);
		}
	}
}
