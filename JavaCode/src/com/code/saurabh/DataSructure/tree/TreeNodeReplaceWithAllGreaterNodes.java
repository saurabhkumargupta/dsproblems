package com.code.saurabh.DataSructure.tree;

import com.code.saurabh.array.ArrayUtils;
import com.code.saurabh.datastructure.stack.Stack;

/**
 * Replace each node with the sum of all greater nodes in a given BST
 * @author saurabh.gupta
 *
 */
public class TreeNodeReplaceWithAllGreaterNodes {

	//Simple
	static int recursion (TreeNode root, int sum) {
		if (root == null)
			return sum;
		
		sum = recursion (root.right, sum);
		sum += root.key;
		if (sum != root.key)
			root.key = sum - root.key;
		sum = recursion (root.left, sum);
		return sum;
	}

	static int replaceNodeWithAllGreaterNodes (TreeNode root, int sum) {
		if (root != null) {
			int right = replaceNodeWithAllGreaterNodes (root.right, sum);
			int left = replaceNodeWithAllGreaterNodes (root.left, right+root.key);
			if (right != 0) {
				root.key = right;
			}
			if (left != 0) {
				return left;
			}
			else {
				return root.key;
			}
		}
		return sum;
	}

	static void sumWithIteration (TreeNode root) {
		TreeNode node = root;
		Stack s = new Stack ();

		int sum = 0;
		while (true) {
			while (node != null) {
				s.push(node);
				node = node.right;
			}

			if (s.isEmpty()) {
				break;
			}

			node = (TreeNode) s.pop();
			int key = node.key;
			if (sum != 0) {
				node.key = sum;
			}
			sum = sum+key;
			node = node.left;
		}
	}

	public static void main(String[] args) {
		int [] [] input = {
				{10, 5, 1, 7, 20, 15, 25, 12, 11, 14, 13},
				{10, 5, 8, 2, 1, 15, 20, 17, 30, 16, 18, 19},
				{10, 5, 8, 2, 1},
		};
		
		for (int i = 0; i < input.length; i++) {
			Tree t = new Tree ();

			System.out.println("RECURSION");
			t.createTree(input[i]);
//			t.toArray();
			replaceNodeWithAllGreaterNodes (t.root, 0);
//			t.toArray();
			t.convertToMirror();
			t.toArray();
			System.out.println();

			System.out.println("ITERATION");
			Tree t2 = new Tree ();
			t2.createTree(input[i]);
//			t2.toArray();
			sumWithIteration (t2.root);
//			t2.toArray();
			t2.convertToMirror();
			t2.toArray();
			System.out.println();
			
			System.out.println("NEW RECURSION");
			Tree t3 = new Tree ();
			t3.createTree(input[i]);
//			t3.toArray();
			recursion (t3.root, 0);
//			t3.toArray();
			t3.convertToMirror();
			t3.toArray();
			System.out.println();
			System.out.println();


		}
	}

}
