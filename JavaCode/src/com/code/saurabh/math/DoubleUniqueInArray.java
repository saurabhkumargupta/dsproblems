package com.code.saurabh.math;

/* 
 * Problem-1
 * This is another problem which can be done by XOR
 * You have an array of size n-1 and elements of this array ranges from 1-to-n. find missing number.
 * (XOR of number from 1-n and then XOR result with array. This result will have missing number)
 * 
 * Problem-2
 * In an Array
 * 		a) if one element is unique and all other are duplicate (2 times), how will you identify the unique element. (Code)
		b) what if the array has 2 unique elements. (Code)
 * 
 * 
 * 	Finding single unique (all others are there for even number of times):
 *  1. Do Xor of all element, result of XOR will be unique. 
 *  2. As XOR of same number result to 0.
 *  3. So all number appearing even number of times will be zored, except the one which occur odd number of times.
 *  
 *  Finding two unique element (all other elements in input occur for even number of times)
 *  1. Do  XOR of all elements. This result will be XOR of two unique elements as all others will be ZEROed. Now, think if two unique
 *     elements are XORed then there will be at least one bit which will be 1. Otherwise all result will have been 0.
 *  2. Find out 1st set bit from XOR result (say : kth bit).(You can take any but 1st it guaranteed as there may be only single bit set)
 *  3. Now, find out all elements in input array whose that bit is set. This gives a temporary array of elements. Now this temp array
 *     will have all elements whose kth bit is set. All elements which are duplicate and have kth bit set, will be part of this
 *     temp array. Also, there will be one unique element whose kth bit set (As other unique element does not have kth bit set.
 *     This kth bit is 1st set bit of XOR result of these two unique elements). So after doing XOR of this temp array, we will get
 *     first unique element.
 *  4. Similarily, find out all elements in input array whose kth bit is not set. This will give another temp array. Doing XOR of this
 *     temp array will give another unique element.
 *      
 *      
 *  Problem-3
 *  http://www.geeksforgeeks.org/find-the-two-numbers-with-odd-occurences-in-an-unsorted-array/
 *  	Same solution as Problem-2 (b)
 *  
 * */
public class DoubleUniqueInArray {

	public static void main(String[] args) {
		int[][] inputSingleUniqueTest = {
				{1,2,3,4,1,2,3},
				{10,10,20,20,30,40,40},
		};
		
		for (int i = 0; i < inputSingleUniqueTest.length; i++) {
			System.out.println("Unique: " + getXOR (inputSingleUniqueTest[i]));
		}

		int[][] inputDoubleUniqueTest = {
				{1,2,3,4,1,2,3,5},
				{10,10,20,20,30,40,40,50},
				{10,10,20,20,30,40,40,50,50,30,45,55,45,100,200,55},
		};
		
		for (int i = 0; i < inputDoubleUniqueTest.length; i++) {
			getDoubleUnique (inputDoubleUniqueTest[i]);
		}
		
//		System.out.println ("On bit in 6: " + getFirstOnBit (6));
//		System.out.println ("On bit in 8: " + getFirstOnBit (8));
//		System.out.println ("On bit in 9: " + getFirstOnBit (9));
	}

	static void getDoubleUnique (int[] input) {
		int[] tempArray = new int[input.length];

		int xor = getXOR (input);
		int maskBit = getFirstOnBit (xor);

		reorderArray (tempArray, input, maskBit, true);
		int firstUnique = getXOR (tempArray);

		reorderArray (tempArray, input, maskBit, false);
		int secondUnique = getXOR (tempArray);

		System.out.println("firstUnique: " + firstUnique + ", secondUnique: " + secondUnique);
	}

	/* Merge of reorderArray_0 & reorderArray_1
	 * keep function for cleaner */
	private static void reorderArray(int[] tempArray, int[] input, int mask, boolean firstHalf) {
		int s = 0;
		for (int i = 0; i < tempArray.length; i++) {
			tempArray[i] = 0;
		}
		for (int i = 0; i < input.length; i++) {
			if ( ((input[i] & mask) == 0 && firstHalf)
					|| ((input[i] & mask) != 0 && !firstHalf)) {
				tempArray[s] = input[i];
				s++;
			}
		}

		System.out.println ("reorderArray_0: ");
		for (int i = 0;i < tempArray.length ;i++) {
			System.out.print( tempArray[i] + ", ");
		}
		System.out.println ();
	}
	
	@SuppressWarnings("unused")
	private static void reorderArray_0(int[] tempArray, int[] input, int mask) {
		int s = 0;
		for (int i = 0; i < tempArray.length; i++) {
			tempArray[i] = 0;
		}
		for (int i = 0; i < input.length; i++) {
			if ((input[i] & mask) == 0) {
				tempArray[s] = input[i];
				s++;
			}
		}

		System.out.println ("reorderArray_0: ");
		for (int i = 0;i < tempArray.length ;i++) {
			System.out.print( tempArray[i] + ", ");
		}
		System.out.println ();
	}
	
	@SuppressWarnings("unused")
	private static void reorderArray_1(int[] tempArray, int[] input, int mask) {
		int s = 0;
		for (int i = 0; i < tempArray.length; i++) {
			tempArray[i] = 0;
		}
		for (int i = 0; i < input.length; i++) {
			if ((input[i] & mask) != 0) {
				tempArray[s] = input[i];
				s++;
			}
		}

		System.out.println ("reorderArray_1: ");
		for (int i = 0;i < tempArray.length ;i++) {
			System.out.print( tempArray[i] + ", ");
		}
		System.out.println ();
	}

	static int getFirstOnBit(int xor) {
		int mask = 1;
		if (xor == 0)
			return -1;
		while ( (xor & mask) == 0) {
			mask <<= 1;
		}
		return mask;
	}

	static int getXOR (int[] input, int s, int e) {
		int xor = input[s];
		for (int i = s+1; i < e; i++) {
			xor = input[i] ^ xor;
		}
		return xor;
	}
	
	static int getXOR (int[] input) {
		int xor = input[0];
		for (int i = 1; i < input.length; i++) {
			xor = input[i] ^ xor;
		}
		return xor;
	}
}
