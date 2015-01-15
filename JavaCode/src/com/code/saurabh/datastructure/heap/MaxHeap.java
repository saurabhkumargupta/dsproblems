/**
 * 
 */
package com.code.saurabh.datastructure.heap;

import com.code.saurabh.util.UtilClass;

/**
 * @author saurabh.gupta
 *
 */
public class MaxHeap extends Heap {

	/**
	 * @param size
	 */
	public MaxHeap(int size) {
		super(size);
	}

	/**
	 * <pre>
	 * Always delete from root element
	 * 
	 * 1. Swap root with last element of heap
	 * 2. decrease heap size
	 * 3. convert this tree into Heap, whichever subtree is not selected from swapping is already in max Heap
	 * </pre>
	 */
	@Override
	public void delete () {
		UtilClass.XorSwap(heapArray, 0, heapSize-1);
		heapSize--;
		shiftDownInHeap (0);
	}
	
	void shiftDownInHeap(int index) {
		if (isLeaf(index)) {
			return;
		}

		int childToReplace = bigChild (index);
		if (childToReplace == -1){
			return;
		}
		if (heapArray[childToReplace] > heapArray[index]) {
			UtilClass.XorSwap(heapArray, childToReplace, index);
			shiftDownInHeap (childToReplace);
		}
	}

	/**
	 * <pre>
	 * heapSize is one index greater than the max Index used in Heap
	 * </pre>
	 * @param key
	 */
	@Override
	public void add (int key) {
		heapArray[heapSize] = key;
		heapSize++;
		
		int maxIndex = heapSize -1;
		int parent = (maxIndex-1)/2;
		while (parent >= 0) {
			int childToReplace = bigChild (parent);
			if (childToReplace != -1 && heapArray[parent] < heapArray[childToReplace])
				UtilClass.XorSwap(heapArray, parent, childToReplace);
			parent = (parent-1)/2;
			if (parent == 0) {
				childToReplace = bigChild (parent);
				if (childToReplace != -1 && heapArray[parent] < heapArray[childToReplace])
					UtilClass.XorSwap(heapArray, parent, childToReplace);
				break;
			}
		}
	}

	/**
	 * <pre>
	 * </pre>
	 * @return
	 */
	private int bigChild(int index) {
		if (index >= heapSize
				|| isLeaf (index)) {
			return -1;
		}
		int left = 2 * index + 1;
		int right = 2 * index + 2;

		/*in heap, single child mean only left child*/
		if (isSingleChild (index)) {
			return left;
		}
		else {
			if (heapArray[left] > heapArray[right])
				return left;
			else
				return right;
		}
	}
	
}
