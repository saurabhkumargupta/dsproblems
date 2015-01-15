package com.code.saurabh.datastructure.tree.misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.code.saurabh.util.Pair;

/**
 * (Copied Smaller solution) http://www.geeksforgeeks.org/check-for-identical-bsts-without-building-the-trees/
 * 
 * Given two arrays which represent a sequence of keys. Imagine we make a Binary Search Tree (BST) from each array. 
 * We need to tell whether two BSTs will be identical or not without actually constructing the tree.
 * 
 * 
 * This problem can also be solved using this approach itself (min, max)
 * isSameBSTUtil ()
 * Given an array representing insertion order in a BST, find the root-to-node path for any given node.
 * 
 * 
 * @author saurabh.gupta
 *
 */
public class SimilarBSTArray {
	
	
	//COPIED  (http://www.geeksforgeeks.org/check-for-identical-bsts-without-building-the-trees/)
	/** <pre>
	 * 
	 *	1. The main function that checks if two arrays a[] and b[] of size n construct same BST. 
	 *	2. The two values 'min' and 'max' decide whether the call is made for left subtree or right subtree of a parent element. 
	 *	3. The indexes i1 and i2 are the indexes in (a[] and b[]) after which we search the left or right child.
	 *	4. Initially, the call is made for INT_MIN and INT_MAX as 'min' and 'max' respectively, because root has no parent.
	 *	5. i1 and i2 are just after the indexes of the parent element in a[] and b[]. 
	  </pre>
	  */
	static boolean isSameBSTUtil(int a[], int b[], int n, int i1, int i2, int min, int max)
	{
	   int j, k;
	 
	   /* Search for a value satisfying the constraints of min and max in a[] and 
	      b[]. If the parent element is a leaf node then there must be some 
	      elements in a[] and b[] satisfying constraint. */
	   for (j=i1; j<n; j++)
	       if (a[j]>min && a[j]<max)
	           break;
	   for (k=i2; k<n; k++)
	       if (b[k]>min && b[k]<max)
	           break;
	 
	   /* If the parent element is leaf in both arrays */
	   if (j==n && k==n)
	       return true;
	 
	   /* Return false if any of the following is true
	      a) If the parent element is leaf in one array, but non-leaf in other.
	      b) The elements satisfying constraints are not same. We either search
	         for left child or right child of the parent element (decinded by min
	         and max values). The child found must be same in both arrays */
	   if (((j==n)^(k==n)) || a[j]!=b[k])
	       return false;
	 
	   /* Make the current child as parent and recursively check for left and right
	      subtrees of it. Note that we can also pass a[k] in place of a[j] as they
	      are both are same */
	   return isSameBSTUtil(a, b, n, j+1, k+1, a[j], max) &&  // Right Subtree
	          isSameBSTUtil(a, b, n, j+1, k+1, min, a[j]);    // Left Subtree
	}
	
	
	Map<Integer, Integer> arr2elements = new HashMap<Integer, Integer> ();
	Set<Integer> usedArr1 = new HashSet<Integer> ();

	/*
	 * <pre>
	 * idea is :
	 * 1. get next left and right child from arr1 with all constraint
	 * 	i.  these elements should not have been used
	 * 	ii. its min and max should satisfy min/max constraint of given subtree
	 * 2. then chech this left and right should come after the current root node in both arrays
	 * </pre>
	 */
	boolean similarBSTArray (int[] arr1, int []arr2) {

		int root = arr1[0];
		Pair<Integer, Integer> smallbig = smallbig (arr1, root, 1);
		usedArr1.clear();
		arr2elements.clear();
		usedArr1.add(root);
		
		for (int i = 0; i < arr2.length; i++) {
			arr2elements.put(arr2[i], i);
		}

		//left
		int leftindex = smallbig.getFirst();
		int rightindex = smallbig.getSecond();
		if (leftindex != -1) {
			usedArr1.add(arr1[leftindex]);
			if (!testArr2(root, arr1[leftindex])) {
				return false;
			}	
		}
		//right
		if (rightindex != -1) {
			usedArr1.add(arr1[rightindex]);
			if (!testArr2(arr1[0], arr1[rightindex])) {
				return false;
			}
		}

		if (leftindex != -1) { 
			if (!isSame (root, "left", arr1[leftindex], leftindex, arr1, Integer.MIN_VALUE, root)) {
				return false;
			}
		}
		if (rightindex != -1) {
			if (!isSame (arr1[0], "right", arr1[rightindex], rightindex, arr1, root, Integer.MAX_VALUE)) {
				return false;
			}
		}
		return true;
	}

	
	private boolean isSame(int parent, String side, int element, int index, int[] arr, int min, int max) {
		if (side.equals("left")) {
			int L = SS (parent, element, index+1, arr, min, max);
			if (L != -1) {
				usedArr1.add(arr[L]);
				if (!testArr2(element, arr[L])) {
					return false;
				}
			}
			int R = SB (parent, element, index+1, arr, min, max);
			if (R != -1) {
				usedArr1.add(arr[R]);
				if (!testArr2(element, arr[R])) {
					return false;
				}
			}

			if (L != -1) {
				if (!isSame (element, "left", arr[L], L, arr, min, element)) {
					return false;
				}
			}
			if (R != -1) {
				if (!isSame (element, "right", arr[R], R, arr, element, max)) {
					return false;
				}
			}
		}
		else {
			int L = BS (parent, element, index+1, arr, min, max);
			if (L != -1) {
				usedArr1.add(arr[L]);
				if (!testArr2(element, arr[L])) {
					return false;
				}
			}
			int R = BB (parent, element, index+1, arr, min, max);
			if (R != -1) {
				usedArr1.add(arr[R]);
				if (!testArr2(element, arr[R])) {
					return false;
				}
			}
			
			if (L != -1) {
				if (!isSame (element, "left", arr[L], L, arr, min, element)) {
					return false;
				}
			}
			if (R != -1) {
				if (!isSame (element, "right", arr[R], R, arr, element, max)) {
					return false;
				}
			}
		}
		return true;
	}

	private int SS(int first, int second, int i, int[] arr, int min, int max) {
		while (i < arr.length) {
			if (arr[i] < first && arr[i] < second && arr[i] > min && arr[i] < max && !usedArr1.contains(arr[i])) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private int SB(int first, int second, int i, int[] arr, int min, int max) {
		while (i < arr.length) {
			if (arr[i] < first && arr[i] > second && arr[i] > min && arr[i] < max && !usedArr1.contains(arr[i])) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private int BS (int first, int second, int i, int[] arr, int min, int max) {
		while (i < arr.length) {
			if (arr[i] > first && arr[i] < second && arr[i] > min && arr[i] < max && !usedArr1.contains(arr[i])) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private int BB(int first, int second, int i, int[] arr, int min, int max) {
		while (i < arr.length) {
			if (arr[i] > first && arr[i] > second && arr[i] > min && arr[i] < max && !usedArr1.contains(arr[i])) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	//index of f should be less than s in Map
	private boolean testArr2(int f, int s) {
		int indexOff = arr2elements.get(f);
		int indexOfs = arr2elements.get(s);
		if (indexOff < indexOfs) {
			return true;
		}
		return false;
	}

	private int getMinMax (int min, int max, int[] arr1, int index) {
		int s = -1;
		int b = -1;
		while (index < arr1.length) {
			if (arr1[index] > min && arr1[index] < max) {
				return index;
			}
			index++;
		}
		return -1;
	}
	private static Pair<Integer, Integer> smallbig(int[] arr1, int element, int index) {
		int s = -1;
		int b = -1;
		while (index < arr1.length) {
			if (s == -1 && arr1[index] < element) {
				s = index;
			}
			if (b == -1 && arr1[index] > element) {
				b = index;
			}
			index++;
		}
		return new Pair<Integer, Integer> (s, b);
	}

	public static void main(String[] args) {
		
		int[][][] input = {
				{
					{8, 3, 6, 1, 4, 7, 10, 14, 13},
					{8, 10, 14, 3, 6, 4, 1, 7, 13},
				},
				{
					{2, 4, 3, 1},
					{2, 1, 4, 3}
				},
				{
					{10, 5, 20, 15, 30},
					{10, 20, 15, 30, 5},
				},
				{
					{10, 5, 20, 15, 30},
					{10, 15, 20, 30, 5},
				},
		};
//		int arr1[] = {8, 3, 6, 1, 4, 7, 10, 14, 13};
//		int arr2[] = {8, 10, 14, 3, 6, 4, 1, 7, 13};
//		
		SimilarBSTArray sba = new SimilarBSTArray ();
//		sba.similarBSTArray(arr1, arr2);
		
		for (int i =0; i < input.length; i++) {
			System.out.println("lengthy mine  :  " + sba.similarBSTArray(input[i][0], input[i][1]));
			System.out.println("geeks   best  :  " + isSameBSTUtil (input[i][0], input[i][1], input[i][1].length, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE));
			System.out.println();
		}
	}

}
