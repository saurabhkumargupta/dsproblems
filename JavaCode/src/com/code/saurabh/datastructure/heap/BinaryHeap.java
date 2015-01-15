package com.code.saurabh.datastructure.heap;

import com.code.saurabh.array.ArrayUtils;
import com.code.saurabh.util.UtilClass;

public class BinaryHeap {

	/**
	 * <pre>
	 * 1. Start from last parent in array. ( index = array.length / 2)
	 * 2. Convert this subtree into (max) heap
	 * 3. move to next index (previous index, as it is decreasing order) in array,
	 *    and now convert this subtree into Heap
	 * </pre>
	 * @param input
	 */
	static void convertToHeap (int[] input) {
		for (int i = input.length/2; i >= 0; i--) {
			shiftDownInBinaryHeap (input, i);
		}
	}

	private static void shiftDownInBinaryHeap(int[] input, int i) {
		if (isLeafNode (input, i)) {
			return;
		}
		int bigChildIndex = getBigChildIndex (input, i);
		if (bigChildIndex == -1){
			return;
		}
		if (input[bigChildIndex] > input[i]) {
			UtilClass.XorSwap(input, bigChildIndex, i);
			shiftDownInBinaryHeap (input, bigChildIndex);
		}
	}

	private static int getBigChildIndex(int[] input, int i) {
		int l_child_index = 2 * i +1;
		int r_child_index = 2 * i +2;
		
		if (l_child_index < input.length
				&& r_child_index < input.length) {
			if (input[l_child_index] > input[r_child_index]) {
				return l_child_index;
			}
			else
				return r_child_index;
		}
		if (l_child_index < input.length) {
			return l_child_index;
		}
		else if (r_child_index < input.length) {
			return r_child_index;
		}
		return -1;
	}

	private static boolean isLeafNode(int[] input, int i) {
		if ( (2*i + 1) > input.length){
			return true;
		}
		return false;
	}

	public static boolean isHeap (int [] input) {
		for (int i = 0; i < input.length/2; i++) {
			int k = getBigChildIndex (input, i);
			if (k != -1 && input[k] > input[i])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		convertToHeapTest ();
	}

	private static void convertToHeapTest() {
		int [] [] input = {
				{10,11,15,18,9,20},
				{1,2,3,4,5,6,7,8,9,10,11},
				{20,15,10,5,11,8},
				{20,15,10,5,11,8,21},
		};

		for (int i = 0; i < input.length; i++) {
			System.out.println();
			System.out.println("Before Heap Conversion");
			ArrayUtils.printArray(input[i]);
			if (!isHeap (input[i])) {
				convertToHeap (input[i]);
				System.out.println("After Heap Conversion");
				ArrayUtils.printArray(input[i]);
			}
			else {
				System.out.println("Index: " + i + ", is already Heap");
			}
		}
	}

}
