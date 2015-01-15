/**
 * 
 */
package com.code.saurabh.datastructure.heap;

import com.code.saurabh.util.UtilClass;

/**
 * @author saurabh.gupta
 *
 */
public class MinHeap extends Heap{

	/**
	 * @param size
	 */
	MinHeap(int size) {
		super(size);
	}

	/* (non-Javadoc)
	 * @see com.code.saurabh.datastructure.heap.Heap#delete()
	 */
	@Override
	public void delete() {
		UtilClass.XorSwap(heapArray, 0, heapSize-1);
		heapSize--;
		shiftDownInHeap (0);
	}
	
	void shiftDownInHeap(int index) {
		if (isLeaf(index)) {
			return;
		}

		int childToReplace = smallChild (index);
		if (childToReplace == -1){
			return;
		}
		if (heapArray[childToReplace] < heapArray[index]) {
			UtilClass.XorSwap(heapArray, childToReplace, index);
			shiftDownInHeap (childToReplace);
		}
	}

	/* (non-Javadoc)
	 * @see com.code.saurabh.datastructure.heap.Heap#add(int)
	 */
	@Override
	public void add(int key) {
		heapArray[heapSize] = key;
		heapSize++;
		
		int maxIndex = heapSize -1;
		int parent = (maxIndex-1)/2;
		while (parent >= 0) {
			int childToReplace = smallChild (parent);
			if (childToReplace != -1 && heapArray[parent] > heapArray[childToReplace])
				UtilClass.XorSwap(heapArray, parent, childToReplace);
			parent = (parent-1)/2;
			if (parent == 0) {
				childToReplace = smallChild (parent);
				if (childToReplace != -1 && heapArray[parent] > heapArray[childToReplace])
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
	private int smallChild(int index) {
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
			if (heapArray[left] < heapArray[right])
				return left;
			else
				return right;
		}
	}

}
