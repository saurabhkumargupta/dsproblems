package com.code.saurabh.DataSructure.tree;

import com.code.saurabh.util.IntPair;

public class TreeIsBSTOperation {

	public static class ResultClass {
		IntPair minMax;
		boolean isBST;
		
		ResultClass () {
			minMax = new IntPair ();
			isBST = true;
		}

		ResultClass (boolean status) {
			minMax = null;
			isBST = status;
		}

		ResultClass (int f, int s, boolean status) {
			minMax = new IntPair (f ,s);
			isBST = status;
		}
	}

	public static boolean isBst (Tree t) {
		if (t.root == null)
			return true;
		ResultClass result = isBST (t.root);
//		System.out.println("Method_2");
		boolean res = isBST (t.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
		if (res == true) {
			System.out.println("Tree is BST");
		}
		else {
			System.out.println("Tree is not BST");
		}
//		System.out.println("Method_1");
		return result.isBST;
	}

	/**
	 * <pre>
	 * In this function, we are passing max,min values in each subtree and if any root of subtree is violating this {max,min} value
	 * then it is not BST
	 * In current subtree:
	 * 	max that is possible for left  subtree is this current subtree's root, min is which is passing from above
	 *  min that is possible for right subtree is this current subtree's root, max is which is passing from above
	 * </pre>
	 */
	static Boolean isBST (TreeNode root, int min, int max) {
		//This check not required, as it is already been taken care in its caller, both in first call and in its recursive call
//		if (root == null) {
//			return true;
//		}
		if (root.key < min || root.key > max) {
			return false;
		}
		if (root.left != null && isBST (root.left, min, root.key) == false) {
			return false;
		}
		if (root.right != null && isBST (root.right, root.key, max) == false) {
			return false;
		}
		return true;
	}

	static ResultClass isBST (TreeNode root) {
		ResultClass result = null;
		if (root.leafNode()) {
			result =  new ResultClass (root.key, root.key, true);
			return result;
		}

		ResultClass left = null;
		ResultClass right = null;

		if (root.left != null) {
			left = isBST (root.left);
			if (left.isBST == false) {
				return left;
			}
		}
		if (root.right != null) {
			right = isBST (root.right);
			if (right.isBST == false) {
				return right;
			}
		}

		if (left == null && right != null) {
			boolean status = true;
			if (right.minMax.getFirst() < root.key)
				status = false;
			result = new ResultClass (root.key, right.minMax.getSecond(), status);
		}
		else if (left != null && right == null) {
			boolean status = true;
			if (left.minMax.getSecond() > root.key)
				status = false;
			result = new ResultClass (left.minMax.getFirst(), root.key, status);
		}
		else {
			boolean status = true;
			if (right.minMax.getFirst() < root.key || left.minMax.getSecond() > root.key)
				status = false;
			result = new ResultClass (left.minMax.getFirst(), right.minMax.getSecond(), status);
		}
		return result;
	}

	public static void main(String[] args) {

		isBSTTesting ();
	}

	private static void isBSTTesting() {		
		Tree t = new Tree ();
		TreeTraversal treeT = new TreeTraversal ();
		
		treeT.setTree(t);
		treeT.inorder();
		if (isBst (t)) {
			System.out.println("Tree is BST");
		}
		else {
			System.out.println("Tree is not BST");
		}
		System.out.println();
		
		t.addToBST(100);

		t.addToNotBST(40, 100, "left");
		t.addToNotBST(150, 100, "right");

		t.addToNotBST(30, 40, "left");
		t.addToNotBST(110, 40, "right");
		
		t.addToNotBST(45, 150, "left");
		t.addToNotBST(160, 150, "right");

		treeT.setTree(t);
		treeT.inorder();
		if (isBst (t)) {
			System.out.println("Tree is BST");
		}
		else {
			System.out.println("Tree is not BST");
		}
		System.out.println();
		
		t.root = null;
		t.addToBST(11);

		t.addToNotBST(9, 11, "left");
		t.addToNotBST(15, 11, "right");

		t.addToNotBST(3, 9, "left");
		t.addToNotBST(10, 9, "right");
		
		t.addToNotBST(6, 15, "left");
		t.addToNotBST(20, 15, "right");
		treeT.setTree(t);
		treeT.inorder();
		if (isBst (t)) {
			System.out.println("Tree is BST");
		}
		else {
			System.out.println("Tree is not BST");
		}
		System.out.println();

		t.root = null;		
		t.addToBST(50);

		t.addToNotBST(40, 50, "left");
		t.addToNotBST(60, 50, "right");

		t.addToNotBST(30, 40, "left");
		t.addToNotBST(20, 30, "left");
		
		t.addToNotBST(55, 60, "left");
		
		t.addToNotBST(66, 55, "right");
		treeT.setTree(t);
		treeT.inorder();

		if (isBst (t)) {
			System.out.println("Tree is BST");
		}
		else {
			System.out.println("Tree is not BST");
		}
		System.out.println();
		
		t.root = null;		
		t.addToBST(50);

		t.addToNotBST(40, 50, "left");
		t.addToNotBST(60, 50, "right");

		t.addToNotBST(100, 40, "left");
		t.addToNotBST(45, 40, "right");
		
		t.addToNotBST(55, 60, "left");
		
		t.addToNotBST(75, 60, "right");
		treeT.setTree(t);
		treeT.inorder();

		if (isBst (t)) {
			System.out.println("Tree is BST");
		}
		else {
			System.out.println("Tree is not BST");
		}
		System.out.println();

		t.root = null;		
		t.addToBST(10);

		t.addToNotBST(9, 10, "left");
		t.addToNotBST(15, 10, "right");

		t.addToNotBST(3, 9, "left");
		t.addToNotBST(6, 9, "right");
		
		t.addToNotBST(14, 15, "left");
		t.addToNotBST(20, 15, "right");

		treeT.setTree(t);
		treeT.inorder();

		if (isBst (t)) {
			System.out.println("Tree is BST");
		}
		else {
			System.out.println("Tree is not BST");
		}
		System.out.println();
		
		
		
		
		int[][] input = {
				{50,40,90,35,30,45,84,89},
				{1,2,3,4},
				{9,8,7,6},
				{10,11,9,7,12,13,4, 8},
				{50,40,30,20,35,45,60,55,70,65,80},
				{10}
		};

		for (int i = 0; i < input.length; i++) {
			System.out.println();
			t = new Tree ();
			for (int j = 0; j < input[i].length; j++) {
				t.addToBST(input[i][j]); 
			}
			
			treeT.setTree(t);
			treeT.inorder();
			if (isBst (t)) {
				System.out.println("Tree is BST");
			}
			else {
				System.out.println("Tree is not BST");
			}
		}
	}
}
