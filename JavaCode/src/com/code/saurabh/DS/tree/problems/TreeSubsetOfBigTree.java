package com.code.saurabh.DS.tree.problems;

import com.code.saurabh.DataSructure.tree.BTreePrinter;
import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;

public class TreeSubsetOfBigTree {

	static TreeNode findNode (TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		if (root.key == key) {
			return root;
		}
		TreeNode left = findNode (root.left, key);
		if (left != null) {
			return left;
		}
		TreeNode right = findNode (root.right, key);
		if (right != null) {
			return right;
		}
		return null;
	}
	
	static boolean subset (TreeNode t1, TreeNode t2) {
		TreeNode node = findNode(t1, t2.key);
		if (node == null) {
			return false;
		}
		return subsetrec (node, t2);
	}
	//t1 big tree, t2 small tree
	static boolean subsetrec (TreeNode t1, TreeNode t2) {
		if (t2 == null) {
			return true;
		}
		if(t1 == null || t1.key != t2.key) {
			return false;
		}
		return subsetrec(t1.left, t2.left) && subsetrec (t1.right, t2.right);
	}
	public static void main(String[] args) {
		Tree t1 = new Tree ();
		t1.createTree();
//		BTreePrinter.printNode(t1.root);
		
		int[][] input = {
				{100, 90, 80, 95, 93, 97, 200, 300, 400, 500, 350, 325, 375},
				{400, 500, 350, 325, 375},
				{90, 80, 95},
				{90},
				{90, 80, 70}
		};

		for (int i = 0; i < input.length; i++) {
			Tree t2 = new Tree ();
			t2.createTree(input[i]);
//			BTreePrinter.printNode(t2.root);
			System.out.println(subset (t1.root, t2.root));
		}
	}

}
