package com.code.saurabh.DataSructure.tree;

import com.code.saurabh.util.IntPair;

public class MaxSumSubTree {
	Tree tree;

	public MaxSumSubTree () {
		tree = null;
	}
	
	public MaxSumSubTree (Tree t) {
		this.tree = t;
	}

	public void setTree (Tree t) {
		this.tree = t;
	}

	public IntPair postOrderModified (TreeNode root) {
		if (root != null) {
			IntPair left = postOrderModified (root.left);
			IntPair right = postOrderModified (root.right);
			
			if (root.leafNode()) {
				return new IntPair (root.key, 0);
			}
			if (root.singleChild()) {
				return handleSingleChild (left, right, root);
			}
			else{
				return handleBothChild (left, right, root);
			}
		}
		return null;
	}

	private IntPair handleBothChild(IntPair left, IntPair right, TreeNode root) {
		int subtreesum = left.getFirst() + right.getFirst() + root.key;
		int left_path = left.getFirst() + root.key;
		int right_path = right.getFirst() + root.key;
		int max_path = (left_path > right_path) ? left_path : right_path;
		int bigger_subtree = (left.getSecond() > right.getSecond()) ? left.getSecond() : right.getSecond();
		int max_subtree = (subtreesum > bigger_subtree) ? subtreesum : bigger_subtree;
		return new IntPair (max_path, max_subtree);
	}

	private IntPair handleSingleChild(IntPair left, IntPair right, TreeNode root) {
		if (left == null) {
			return new IntPair (right.getFirst() + root.key, 0);
		}
		else if (right == null) {
			return new IntPair (left.getFirst() + root.key, 0);
		}
		return null;
	}

	public static void main(String[] args) {
		Tree t = PrepareNotBstTree ();
		MaxSumSubTree mss = new MaxSumSubTree (t);
		IntPair result = mss.postOrderModified(t.root);
		System.out.println("max Sum of Subtree: " + result.getSecond());
		
		t = PrepareNotBstTree_2 ();
		mss.setTree(t);
		result = mss.postOrderModified(t.root);
		System.out.println("max Sum of Subtree: " + result.getSecond());
	}

	private static Tree PrepareNotBstTree() {
		Tree tree = null;
		tree = new Tree ();

		System.out.println();		
		System.out.println("Non BST Tree");

		tree.addToBST(10);

		tree.addToNotBST(-20, 10, "left");
		tree.addToNotBST(-30, 10, "right");

		tree.addToNotBST(11, -20, "left");
		tree.addToNotBST(50, -20, "right");

		tree.addToNotBST(21, 11, "right");

		tree.addToNotBST(5, -30, "left");
		tree.addToNotBST(6, -30, "right");
		
		tree.addToNotBST(51, 5, "left");

		tree.addToNotBST(52, 6, "right");	
		
		tree.inorder();
		System.out.println();
		return tree;
	}
	
	private static Tree PrepareNotBstTree_2() {
		Tree tree = null;
		tree = new Tree ();

		System.out.println();		
		System.out.println("Non BST Tree");

		tree.addToBST(10);

		tree.addToNotBST(20, 10, "left");
		tree.addToNotBST(-20, 10, "right");

		tree.addToNotBST(11, 20, "left");
		tree.addToNotBST(7, 20, "right");

		tree.addToNotBST(21, 11, "right");
		
		tree.addToNotBST(5, 21, "left");
		
		tree.addToNotBST(15, 7, "right");

		tree.addToNotBST(12, 15, "left");

		tree.addToNotBST(-20, 10, "right");

		tree.addToNotBST(6, -20, "right");

		tree.addToNotBST(50, 6, "left");

		tree.inorder();
		System.out.println();
		return tree;
	}
}
