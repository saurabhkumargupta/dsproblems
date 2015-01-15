/**
 * 
 */
package com.code.saurabh.datastructure.stack;

/**
 * @author saurabh.gupta
 * Simplest and famous one: Design stack for top(),push(),pop() and getMin()/getMax() operations in O(1) time complexity.
 */
public class MinMaxStack<T> {
	private StackNode<T> top;
	private int size;

	static private class StackNode <T> {
		T key;
		T max;
		T min;
		StackNode<T> next;
		public StackNode(T key, T max, T min, StackNode<T> next) {
			super();
			this.key = key;
			this.max = max;
			this.min = min;
			this.next = next;
		}
	}
	
	public MinMaxStack () {
		top = null;
		size = 0;
	}
	
	public void push (T key) {
		T min;
		T max;
		if (top == null) {
			max = key;
			min = key;
		} else {
			min = top.min;
			max = top.max;
		}
			
		if (key instanceof Integer) {
			if ((Integer)key > (Integer) max) {
				max = key;				
			}
			if ((Integer)key < (Integer) min) {
				min = key;				
			}
		}
		StackNode<T> node = new StackNode<T> (key, max, min, top);
		top = node;
		size++;
	}
	
	public Object peek () {
		return top.key;
	}

	public int getSize () {
		return size;
	}

	public T pop () {
		if (isEmpty()) {
			return null;
		}
		StackNode<T> node = top;
		top = top.next;
		size--;
		return node.key;
	}
	
	public T getMin () {
		if (isEmpty()) {
			return null;
		}
		return top.min;
	}

	public T getMax () {
		if (isEmpty()) {
			return null;
		}
		return top.max;
	}
	
	public boolean isEmpty () {
		if (top == null)
			return true;
		return false;
	}

	public static void main(String[] args) {
		int [][] input = {
				{8, 5, 10, 7, 9, 4, 15, 12, 90, 13},
				{7, 9, 15, 11, 10, 3, 19, 2, 1, 14}
		};

		MinMaxStack<Integer> mmstack = new MinMaxStack<Integer>();
		for (int i = 0; i < input.length; i++) {
			System.out.println ();
			System.out.println ();
			for (int j = 0; j < input[i].length; j++) {
				mmstack.push(input[i][j]);
			}
	
			while (!mmstack.isEmpty()){
				System.out.print("Top: " + (Integer)mmstack.peek() + ", Max: " + (Integer)mmstack.getMax()
						+ ", Min: " + (Integer)mmstack.getMin());
				mmstack.pop();
				System.out.println ();
			}
		}
	}

}
