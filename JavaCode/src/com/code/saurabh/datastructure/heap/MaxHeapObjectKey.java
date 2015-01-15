/**
 * 
 */
package com.code.saurabh.datastructure.heap;

import com.code.saurabh.util.Pair;

/**
 * @author saurabh.gupta
 *
 */
public class MaxHeapObjectKey {

	int heapSize;
	int maxSize;
	Pair<Long, Integer> [] heapArray = null;
	/**
	 * @param size
	 */
	@SuppressWarnings("unchecked")
	public MaxHeapObjectKey(int size) {
		this.maxSize = size;
		this.heapSize = 0;
		heapArray = new Pair [maxSize] ;
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
	public Pair<Long, Integer> delete () {
		Pair<Long, Integer> obj = getRoot ();
		if (obj.getFirst() == -1L) {
			return obj;
		}
		heapArray[0] = heapArray[heapSize-1];
		heapSize--;
		shiftDownInHeap (0);
		return obj;
	}
	
	boolean isLeaf (int index) {
		if (2*index + 1 >= heapSize) {
			return true;
		}
		return false;
	}

	void shiftDownInHeap(int index) {
		if (isLeaf(index)) {
			return;
		}

		int childToReplace = bigChild (index);
		if (childToReplace == -1){
			return;
		}

		if (heapArray[childToReplace].getFirst() > heapArray[index].getFirst()) {
			Pair<Long, Integer> t = heapArray[childToReplace];
			heapArray[childToReplace] = heapArray[index];
			heapArray[index] = t;
			shiftDownInHeap (childToReplace);
		}
	}

	/**
	 * <pre>
	 * heapSize is one index greater than the max Index used in Heap
	 * </pre>
	 * @param key
	 */
	public void add (Pair<Long, Integer> key) {
		heapArray[heapSize] = key;
		heapSize++;
		
		int maxIndex = heapSize -1;
		int parent = (maxIndex-1)/2;
		while (parent >= 0) {
			int childToReplace = bigChild (parent);
			if (childToReplace != -1 && heapArray[parent].getFirst() < heapArray[childToReplace].getFirst()) {
				Pair<Long, Integer> t = heapArray[childToReplace];
				heapArray[childToReplace] = heapArray[parent];
				heapArray[parent] = t;
			}
			parent = (parent-1)/2;
			if (parent == 0) {
				childToReplace = bigChild (parent);
				if (childToReplace != -1 && heapArray[parent].getFirst() < heapArray[childToReplace].getFirst()) {
					Pair<Long, Integer> t = heapArray[childToReplace];
					heapArray[childToReplace] = heapArray[parent];
					heapArray[parent] = t;
				}
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
			if (heapArray[left].getFirst() > heapArray[right].getFirst())
				return left;
			else
				return right;
		}
	}
	
	boolean isSingleChild (int index) {
		int left = 2 * index + 1;
		int right = 2 * index + 2;
		if (left < heapSize && right >= heapSize)
			return true;

		return false;
	}
	
	public Pair<Long, Integer> getRoot () {
		if (heapSize <= 0) {
			return new Pair<Long, Integer> (-1L, -1);
		}
		return heapArray[0];
	}
	
	public static void main(String[] args) {
		MaxHeapObjectKey test = new MaxHeapObjectKey (100);
		test.add(new Pair<Long, Integer> (System.nanoTime(), 1));
		test.add(new Pair<Long, Integer> (System.nanoTime(), 2));
		test.add(new Pair<Long, Integer> (System.nanoTime(), 3));
		test.add(new Pair<Long, Integer> (System.nanoTime(), 4));
		test.add(new Pair<Long, Integer> (System.nanoTime(), 5));
		test.add(new Pair<Long, Integer> (System.nanoTime(), 6));
		
//		for (int i = 0; i < test.heapSize; i++) {
//			System.out.println(test.heapArray[i].getSecond());
//		}
		while (test.getRoot().getSecond() != 1) {
			System.out.println(test.delete().getSecond());
		}
		//TODO: need to check its requirment
		System.out.println(test.delete().getSecond());

	}
}