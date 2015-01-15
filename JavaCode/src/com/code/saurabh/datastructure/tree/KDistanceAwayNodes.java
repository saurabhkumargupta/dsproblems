package com.code.saurabh.datastructure.tree;

import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;

public class KDistanceAwayNodes {

	static void printNodesAtKDistanceDown (TreeNode node, int d) {
		if (node == null) {
			return;
		}
		if (d == 0) {
			System.out.print(node.key + ", ");
			return;
		}
		printNodesAtKDistanceDown (node.left, d-1);
		printNodesAtKDistanceDown (node.right, d-1);
	}
	
	static int printNodesAtKDistance (TreeNode node, int key, int d) {
		if (node == null) {
			return -1;
		}
		if (node.key == key) {
			printNodesAtKDistanceDown (node.left, d-1);
			printNodesAtKDistanceDown (node.right, d-1);
			return 1;
		}
		int left = printNodesAtKDistance (node.left, key, d);
		if (left == d) {
			System.out.print(node.key + ", ");
			return -1;
		}
		if (left != -1) {
			printNodesAtKDistanceDown (node.right, d-left-1);
			return left+1;
		}
		
		int right = printNodesAtKDistance (node.right, key, d);
		if (right == d) {
			System.out.print(node.key + ", ");
			return -1;
		}
		if (right != -1) {
			printNodesAtKDistanceDown (node.left, d-right-1);
			return right+1;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Tree t = new Tree ();
		t.createTree();
		t.printTree();
		Integer[] input = t.toArray();
		for (int i = 0; i < input.length; i++) {
			System.out.print(" target: " + input[i] + " -> ");
			printNodesAtKDistance (t.root, input[i].intValue(), 3);
			System.out.println();
		}
	}

}
