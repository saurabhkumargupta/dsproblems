/**
 * 
 */
package com.code.saurabh.datastructure.heap;

/**
 * @author saurabh.gupta
 *
 */
public class HeapTest {

	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		maxHeapTest ();
		minHeapTest ();
		}
	
	static void maxHeapTest () {
		Heap heap = new MaxHeap (10);

		System.out.println("Max Heap");
		heap.add (20);
		heap.add (25);
		heap.add (30);
		heap.add (15);
		heap.add (35);
		heap.add (10);
		heap.add (50);
		heap.add (5);

		heap.printHeap();
		
		heap.delete();
		heap.printHeap();
	}
	
	static void minHeapTest () {
		Heap heap = new MinHeap (10);
		System.out.println("Min Heap");
		heap.add (20);
		heap.add (25);
		heap.add (30);
		heap.add (15);
		heap.add (35);
		heap.add (10);
		heap.add (50);
		heap.add (5);

		heap.printHeap();
		
		heap.delete();
		heap.printHeap();
	}
}
