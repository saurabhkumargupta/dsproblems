/**
 * 
 */
package com.code.saurabh.datastructure.queue;

/**
 * @author saurabh.gupta
 * * WRONG IMPLEMENTAAATION
 */
public class MaxInWindow {

	static public void maxInWindow (int [] input, int k) {
		MinMaxQueue<Integer> queue = new MinMaxQueue<Integer>();

		for (int i = 0; i < k; i++) {
			queue.enqueue(input[i]);
		}

		System.out.print(queue.getMax() + ", ");
		
		for (int i = k; i < input.length; i++) {
			queue.dequeue();
			queue.enqueue(input[i]);
			System.out.print(queue.getMax() + ", ");
		}
	}

	public static void main(String[] args) {
		int [][] input = {
//				{8, 5, 10, 7, 9, 4, 15, 12, 90, 13},
//				{7, 9, 15, 11, 10, 3, 19, 2, 1, 14},
				{7, 9, 15, 11, 10, 3, 2, 1, 14},
				{10, 19, 11, 5, 6},
				{10, 9, 8, 7, 6, 5, 4},
		};
		
		int k = 4;
		for (int i = 0; i < input.length; i++) {
			maxInWindow(input[i], k);
			System.out.println();
		}
	}

}
