/**
 * 
 */
package com.code.saurabh.datastructure.stack;

import com.code.saurabh.datastructure.heap.MaxHeapObjectKey;
import com.code.saurabh.util.Pair;

/**
 * @author saurabh.gupta
 *
 */
public class StackUsingMaxHeap {

	MaxHeapObjectKey maxHeap = new MaxHeapObjectKey(100);	

	public void heapStackPush (int v) {
		long curTime = System.nanoTime();
		Pair<Long, Integer> key = new Pair<Long, Integer> (curTime, v);
		maxHeap.add(key);
	}
	
	public int heapStackPop () {
		Pair<Long, Integer> key = maxHeap.delete();
		return key.getSecond();
	}
	
	public boolean isHeapStackEmpty () {
		Pair<Long, Integer> key = maxHeap.getRoot();
		if (key.getFirst() == -1L) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		StackUsingMaxHeap test = new StackUsingMaxHeap ();
		test.heapStackPush(11);
		test.heapStackPush(2);
		test.heapStackPush(31);
		test.heapStackPush(45);
		test.heapStackPush(10);
		test.heapStackPush(9);

		while (!test.isHeapStackEmpty ()) {
			System.out.println(test.heapStackPop());
		}
	}

}
