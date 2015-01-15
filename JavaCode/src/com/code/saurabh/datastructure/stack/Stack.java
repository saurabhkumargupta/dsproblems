package com.code.saurabh.datastructure.stack;


public class Stack {
	protected StackNode top;
	private int size;

	public class StackNode {
		Object key;
		StackNode next;
		
		StackNode (Object k, StackNode N) {
			key = k;
			next = N;			
		}
		
		public StackNode getNext () {
			return next;
		}
		
		public Object getKey () {
			return key;
		}
	};

	public Stack () {
		top = null;
		size = 0;
	}
	
	public void push (Object O) {
		StackNode node = new StackNode (O, top);
		top = node;
		size++;
	}
	
	public Object peek () {
		return top.key;
	}

	public int getSize () {
		return size;
	}

	public Object pop () {
		if (isEmpty()) {
			return null;
		}
		StackNode node = top;
		top = top.next;
		size--;
		return node.key;
	}
	
	public boolean isEmpty () {
		if (top == null)
			return true;
		return false;
	}

	public static void main (String[] args) {
		Stack stack = new Stack ();
		
		stackTest (stack);
	}

	private static void stackTest(Stack stack) {
		Object node = null;
		node = stack.pop();
		if (node == null) {
			System.out.println("Stack is Empty");
		}
		else
			System.out.println(" " + node);
		
		stack.push(15);
		node = stack.pop();
		if (node == null) {
			System.out.println("Stack is Empty");
		}
		else
			System.out.println(" " + node);
		
		int input [] = {10,45,23,55};
		for (int i = 0; i < input.length; i++) {
			stack.push(input[i]);
		}

		System.out.println("Peek");
		System.out.print(stack.peek() + ",");

		System.out.println();		
		System.out.println("Pop");
		while ((node = stack.pop()) != null) {
			System.out.print("," + node);
		}
		System.out.println("");
		stack.push(20);
		stack.push(30);
		stack.push(40);
		stack.push(50);
		System.out.println("" +stack.pop() );
		System.out.println("" +stack.pop() );
		System.out.println("" +stack.pop() );
		stack.push(60);
		stack.push(70);
		System.out.println("" +stack.pop() );
	}
}
