/**
 * 
 */
package com.code.saurabh.datastructure.heap;

import com.code.saurabh.array.ArrayUtils;

/**
 * @author saurabh.gupta
 *
 */
public abstract class Heap {

	int heapSize;
	int maxSize;
	int [] heapArray = null;
	boolean maxHeap = true;
	
	public Heap (int size) {
		this.maxSize = size;
		this.heapSize = 0;
		heapArray = new int[maxSize];
	}
	public abstract void delete ();
	public abstract void add (int key);

	public void printHeap () {
		ArrayUtils.printArray(heapArray, 0 , heapSize-1);
	}

	boolean isLeaf (int index) {
		if (2*index + 1 >= heapSize) {
			return true;
		}
		return false;
	}
	
	boolean isSingleChild (int index) {
		int left = 2 * index + 1;
		int right = 2 * index + 2;
		if (left < heapSize && right >= heapSize)
			return true;

		return false;
	}

	int getRoot () {
		if (heapSize <= 0) {
			return -1;
		}
		return heapArray[0];
	}
	
	
	
	
//	/**
//	 * <pre>
//	 * Always delete from root element
//	 * 
//	 * 1. Swap root with last element of heap
//	 * 2. decrease heap size
//	 * 3. convert this tree into Heap, whichever subtree is not selected from swapping is already in max Heap
//	 * </pre>
//	 */
//	public void deleteFromMaxHeap () {
//		UtilClass.XorSwap(heapArray, 0, heapSize-1);
//		heapSize--;
//		shiftDownInBinaryHeap (0);
//	}
//	
//	void shiftDownInBinaryHeap(int index) {
//		if (isLeaf(index)) {
//			return;
//		}
//
//		int childToReplace = bigChild (index);
//		if (childToReplace == -1){
//			return;
//		}
//		if (heapArray[childToReplace] > heapArray[index]) {
//			UtilClass.XorSwap(heapArray, childToReplace, index);
//			shiftDownInBinaryHeap (childToReplace);
//		}
//	}

//	/**
//	 * <pre>
//	 * heapSize is one index greater than the max Index used in Heap
//	 * </pre>
//	 * @param key
//	 */
//	void addToMaxHeap (int key) {
//		heapArray[heapSize] = key;
//		heapSize++;
//		
//		int maxIndex = heapSize -1;
//		int parent = (maxIndex-1)/2;
//		while (parent >= 0) {
//			int childToReplace = bigChild (parent);
//			if (childToReplace != -1 && heapArray[parent] < heapArray[childToReplace])
//				UtilClass.XorSwap(heapArray, parent, childToReplace);
//			parent = (parent-1)/2;
//			if (parent == 0) {
//				childToReplace = bigChild (parent);
//				if (childToReplace != -1 && heapArray[parent] < heapArray[childToReplace])
//					UtilClass.XorSwap(heapArray, parent, childToReplace);
//				break;
//			}
//		}
//	}

//	/**
//	 * <pre>
//	 * </pre>
//	 * @return
//	 */
//	private int bigChild(int index) {
//		if (index >= heapSize
//				|| isLeaf (index)) {
//			return -1;
//		}
//		int left = 2 * index + 1;
//		int right = 2 * index + 2;
//
//		/*in heap, single child mean only left child*/
//		if (isSingleChild (index)) {
//			return left;
//		}
//		else {
//			if (heapArray[left] > heapArray[right])
//				return left;
//			else
//				return right;
//		}
//	}
}
