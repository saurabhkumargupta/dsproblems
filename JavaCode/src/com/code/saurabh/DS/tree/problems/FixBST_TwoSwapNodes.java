package com.code.saurabh.DS.tree.problems;

import java.util.Stack;

import com.code.saurabh.DataSructure.tree.BTreePrinter;
import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;
import com.code.saurabh.datastructure.tree.misc.InorderPreorderToBinaryTree;

public class FixBST_TwoSwapNodes {

	static void fixBST (TreeNode root) {
		Stack<TreeNode> inorder = new Stack<TreeNode> ();
		TreeNode first = null;
		TreeNode last  = null;
		TreeNode prev = null;
		
		TreeNode node = root;
		while (true) {
			while (node != null) {
				inorder.add(node);
				node = node.left;
			}

			if (inorder.isEmpty()) {
				break;
			}

			node = inorder.pop();
			if (prev == null) {
				prev = node;
			}
			if (prev.key > node.key) {
				if (first == null) {
					first = prev;
					last = node;
				}
				else {
					last = node;
				}
			}
			prev = node;
			node = node.right;
		}
		int t = first.key;
		first.key = last.key;
		last.key = t;
	}
	
	static void inorderI (TreeNode root) {
		Stack<TreeNode> inorder = new Stack<TreeNode> ();
		if (root == null) {
			System.out.println("Emtyr tree");
			return;
		}

		System.out.println();
		TreeNode node = root;
		while (true) {
			while (node != null) {
				inorder.add(node);
				node = node.left;
			}
			if (inorder.isEmpty()) {
				return;
			}
			node = inorder.pop();
			System.out.print(node.key + ", ");
			node = node.right;
		}
	}
	public static void main(String[] args) {
		Tree t = new Tree();
//		t.createTree();
//		BTreePrinter.printNode(t.root);
//		inorderI(t.root);
		
//		t = new Tree ();
		
		int[][][] input = {
				{
					{5, 6, 9, 8, 7, 10},
					{10, 5, 9, 6, 8, 7}	
				},
				{
					{8, 6, 7, 5, 10}, //IN
					{10, 8, 7, 6, 5} // pre
				},
				{
					{2, 5, 8, 10, 20},
					{10, 5, 2, 20, 8}
				},
				{
					{1, 10, 3, 6, 7, 2, 12},
					{6, 10, 1, 3, 2, 7, 12}
				},
				{
					{14, 13, 11, 15, 17},
					{14, 15, 13, 11, 17}
				}
		};
		
		for (int i = 0; i < input.length; i++) {
			int[] in  = input[i][0];
			int[] pre = input[i][1];
			t.root = InorderPreorderToBinaryTree.buildBT (in, pre);
			System.out.println("Before fix:");
			BTreePrinter.printNode(t.root);
			System.out.println();
			System.out.println("After Fix:");
			fixBST(t.root);
			BTreePrinter.printNode(t.root);
		}
	}

}
