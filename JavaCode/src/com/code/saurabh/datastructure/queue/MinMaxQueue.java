/**
 * 
 */
package com.code.saurabh.datastructure.queue;

import com.code.saurabh.datastructure.stack.MinMaxStack;

/**
 * @author saurabh.gupta
 * WRONG IMPLEMENTAAATION
 */
public class MinMaxQueue <T> implements IQueue <T> {
	private MinMaxStack<T> addStack;
	private MinMaxStack<T> removeStack;
	T max;

	MinMaxQueue () {
		addStack = new MinMaxStack<T>();
		removeStack = new MinMaxStack<T>();
		max = null;
	}

	@Override
	//When both stacks are empty then, Queue is empty
	public boolean isEmpty() {
		if (removeStack.isEmpty() && addStack.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public void enqueue(T key) {
		addStack.push(key);
		if (max == null || (Integer)max < (Integer)addStack.getMax()) {
			max = addStack.getMax();
		}
	}

	private void addToRemoveStack () {
		while (!addStack.isEmpty()) {
			removeStack.push(addStack.pop());
		}
	}
	
	private void removeToAddStack () {
		while (!removeStack.isEmpty()) {
			addStack.push(removeStack.pop());
			max = addStack.getMax();
		}
	}

	@Override
	public T dequeue() {
		if (removeStack.isEmpty()) {
			addToRemoveStack();
		}
		
		T ret = removeStack.pop();
		if (max == ret) {
			removeToAddStack();
			max = addStack.getMax();
		}
		return ret;
	}
	
	public T getMax () {
		return max;
	}

	public static void main(String[] args) {
		MinMaxQueue<Integer> mmQ = new MinMaxQueue<Integer> ();
		
		int [][] input = {
				{8, 5, 10, 7, 9, 4, 15, 12, 90, 13},
				{7, 9, 15, 11, 10, 3, 19, 2, 1, 14},
				{10, 19, 11, 5, 6},
		};

		for (int i = 0; i < 2; i++) {
			System.out.println ();
			System.out.println ();
			for (int j = 0; j < input[i].length; j++) {
				mmQ.enqueue(input[i][j]);
			}

			while (!mmQ.isEmpty()) {
				//System.out.println ("Key: "  + mmQ.dequeue() + ", Max: " + mmQ.getMax() + ", Min: " + mmQ.getMin());
				System.out.println ("Max: " + mmQ.getMax() + ", Key: "  + mmQ.dequeue());
			}
		}
		
		System.out.println ();
		System.out.println ();

		mmQ.enqueue(input[2][0]);
		mmQ.enqueue(input[2][1]);
		mmQ.enqueue(input[2][2]);

		if (!mmQ.isEmpty()) {
			System.out.println ("Max: " + mmQ.getMax() + ", Key: "  + mmQ.dequeue());
		}
		if (!mmQ.isEmpty()) {
			System.out.println ("Max: " + mmQ.getMax() + ", Key: "  + mmQ.dequeue());
		}
		
		if (!mmQ.isEmpty()) {
			System.out.println ("Max: " + mmQ.getMax());
		}
		
		mmQ.enqueue(input[2][3]);
		mmQ.enqueue(input[2][4]);

		if (!mmQ.isEmpty()) {
			System.out.println ("Max: " + mmQ.getMax() + ", Key: "  + mmQ.dequeue());
		}
		if (!mmQ.isEmpty()) {
			System.out.println ("Max: " + mmQ.getMax() + ", Key: "  + mmQ.dequeue());
		}
		if (!mmQ.isEmpty()) {
			System.out.println ("Max: " + mmQ.getMax() + ", Key: "  + mmQ.dequeue());
		}
	}
}
