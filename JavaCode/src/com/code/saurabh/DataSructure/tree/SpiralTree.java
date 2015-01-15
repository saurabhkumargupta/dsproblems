package com.code.saurabh.DataSructure.tree;

import com.code.saurabh.datastructure.queue.Queue;
import com.code.saurabh.datastructure.stack.Stack;
import com.code.saurabh.util.UtilClass;

/**
 * Given a binary tree, print in spiral order 
 * <pre>
            1 
       2           3
    4    5      6    7
 8   
  print - > 1, 2, 3, 7,6,5,4,8
  <pre>
 * 
 * @author saurabh.gupta
 *
 */
public class SpiralTree {

	private static class QueueBirDirection {
		private class QueueNode {
			Object key;
			QueueNode next;
			QueueNode prev;
			
			QueueNode (Object K) {
				key = K;
				next = null;
				prev = null;
			}
		}

		QueueNode front;
		QueueNode rear;
		public QueueBirDirection () {
			front = null;
			rear = null;
		}

		public QueueNode enqueue (Object O) {
			QueueNode node = new QueueNode(O);
			if (isEmpty ()) {
				front = rear = node;
			}
			else {
				node.prev = rear;
				rear.next = node;
				rear = node;
			}
			return rear;
		}
		

		public Object dequeue () {
			Object node = null;
			if (isEmpty ()) {
				return null;
			}
			node = front.key;
			front = front.next;

			if (front == null)
				rear = null;
			else
				front.prev = null;
			return node;
		}
		
		public Object readNext (QueueNode node) {
			QueueNode temp = node.prev;
			if (temp != null) {
				node.prev = temp.prev;
				if (node.prev == null) {
					front = node;
				}
				else
					node.prev.next = node;
				return temp.key;
			}
			else
				return null;
		}
		
		public boolean isEmpty () {
			if (front == null) {
				return true;
			}
			return false;
		}
		
		public boolean isEmpty (QueueNode node) {
			if (node.prev == null) {
				return true;
			}
			return false;
		}
	}

	public static void printSpiral (TreeNode root) {
		int level = 0;
		int levelCount = 0;
		TreeNode nullNode = new TreeNode (-1);
		
		QueueBirDirection queue = new QueueBirDirection ();
		queue.enqueue(root);
		SpiralTree.QueueBirDirection.QueueNode R = queue.enqueue(nullNode);
		System.out.println ("printSpiral");
		System.out.print(levelCount + " --> ");		
		while (!queue.isEmpty()) {
			TreeNode node = (TreeNode) queue.readNext(R);
			if (node == null) {
				level = (level == 0)?1:0;
				levelCount ++;
				
				//This will remove Null node from front
				queue.dequeue();
				R = queue.enqueue(nullNode);
				if (queue.isEmpty(R)) {
					break;
				}
				System.out.println ();
				System.out.print(levelCount + " --> ");

				continue;
			}
			System.out.print(node.key + ", ");
			if (level == 0) { //Even
				if (node.right != null)
					queue.enqueue(node.right);
				if (node.left != null)
					queue.enqueue(node.left);
			}
			else if (level == 1) { //ODD
				if (node.left != null)
					queue.enqueue(node.left);
				if (node.right != null)
					queue.enqueue(node.right);
			}
		}
		System.out.println ();
	}

	/**
	 * <pre>
	 * elements of each level is stored in separate stack
	 * each stack is stored in Queue
	 * 
	 * each stack is dequeued from Queue and processed until current stack is empty (current level is printed)
	 * During this stack processing, even and odd levels' children should be pushed to stack accordingly
	 * </pre>
	 * @param root
	 */
	public static void printSpiralStackQueue (TreeNode root) {
		Queue q = new Queue ();
		Stack s = new Stack ();
		int level = 0;

		System.out.println ();
		System.out.print("printSpiralStackQueue");
		s.push(root);
		q.enqueue(s);
		
		while (!q.isEmpty()) {
			Stack oldStack = (Stack)q.dequeue();
			Stack newStack = new Stack ();
			System.out.println ();
			while (!oldStack.isEmpty()) {
				TreeNode node = (TreeNode) oldStack.pop();
				System.out.print(node.key + ", ");
				if (UtilClass.isEven(level)) { //Even
					if (node.right != null)
						newStack.push(node.right);
					if (node.left != null)
						newStack.push(node.left);
				}
				else if (UtilClass.isOdd(level)) { //ODD
					if (node.left != null)
						newStack.push(node.left);
					if (node.right != null)
						newStack.push(node.right);
				}
			}
			level++;
			if (newStack.isEmpty()) {
				break;
			}
			q.enqueue(newStack);
		}
	}

	/**
	 * <pre>
	 * Like previous one.
	 * But Queue is not necessary, as at any point of time there is no more than one element (stack) in Queue.
	 * </pre>
	 * @param root
	 */
	public static void printSpiralStack (TreeNode root) {
		Stack oldStack = new Stack ();
		int level = 0;

		System.out.println ();
		System.out.print("printSpiralStack");
		oldStack.push(root);
		
		while (true) {
			Stack newStack = new Stack ();
			System.out.println ();
			while (!oldStack.isEmpty()) {
				TreeNode node = (TreeNode) oldStack.pop();
				System.out.print(node.key + ", ");
				if (UtilClass.isEven(level)) { //Even
					if (node.right != null)
						newStack.push(node.right);
					if (node.left != null)
						newStack.push(node.left);
				}
				else if (UtilClass.isOdd(level)) { //ODD
					if (node.left != null)
						newStack.push(node.left);
					if (node.right != null)
						newStack.push(node.right);
				}
			}
			level++;
			if (newStack.isEmpty()) {
				break;
			}
			oldStack = newStack;
		}
	}
	
	public static void main(String[] args) {
		SpiralTesting ();
	}
	private static void SpiralTesting() {
		int[][] input = {
				{50,40,90,35,30,45,84,89},
				{1,2,3,4},
				{9,8,7,6},
				{10,11,9,7,12,13,4, 8},
				{50,40,30,20,35,45,60,55,70,65,80},
				{100,50,200,25,75,150,300,10,40,60,90,125,175,250,400}
		};

		for (int i = 0; i < input.length; i++) {
			System.out.println();
			Tree t = new Tree ();
			for (int j = 0; j < input[i].length; j++) {
				t.addToBST(input[i][j]); 
			}
			printSpiral (t.root);
			printSpiralStackQueue (t.root);
			printSpiralStack (t.root);
			System.out.println ();
		}
	}
}
