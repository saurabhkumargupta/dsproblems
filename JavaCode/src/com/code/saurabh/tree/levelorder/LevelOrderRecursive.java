package com.code.saurabh.tree.levelorder;


import sun.misc.Queue;

import com.code.saurabh.DataSructure.tree.BTreePrinter;
import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;

/*
 * http://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/
 */
public class LevelOrderRecursive {

	static void level (TreeNode t) throws InterruptedException {
		Queue q = new Queue ();
		q.enqueue(t);
		level (q);
	}
	
	static void level (Queue q) throws InterruptedException {
		if (q.isEmpty()) {
			return;
		}
		Queue nextqueue = new Queue ();
		System.out.println();
		while (!q.isEmpty()) {
			TreeNode node = (TreeNode)q.dequeue();
			System.out.print(node.key + ", ");

			if (node.left != null) {
				nextqueue.enqueue(node.left);
			}
			if (node.right != null) {
				nextqueue.enqueue(node.right);
			}
		}
		level (nextqueue);
	}
	public static void main(String[] args) throws InterruptedException {
		Tree t = new Tree ();
		t.createTree();
		BTreePrinter.printNode(t.root);
		level (t.root);
	}

}
