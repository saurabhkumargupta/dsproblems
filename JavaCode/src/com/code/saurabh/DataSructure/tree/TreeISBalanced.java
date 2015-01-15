package com.code.saurabh.DataSructure.tree;

public class TreeISBalanced {

	static int isBalanced (TreeNode root) {
		if (root == null) {
			return 0;
		}
		int l = isBalanced(root.left);
		if (l == -1) {
			return -1;
		}
		int r = isBalanced(root.right);
		if (r == -1) {
			return -1;
		}
		if (Math.abs(l - r) > 1) {
			return -1;
		}
		return Math.max(l, r) + 1;
	}
	public static void main(String[] args) {
		Tree t = new Tree();
		t.createTree();
		balanceTest (t);

		int [] input = {100, 90, 80, 95, 93, 97, 200, 300, 400, 500, 350};
		t = new Tree();
		t.createTree(input);
		balanceTest(t);
		
		int [] input_1 = {10, 5, 4,3, 6, 15, 14};
		t = new Tree();
		t.createTree(input_1);
		balanceTest(t);
	}
	
	static void balanceTest (Tree t) {
		BTreePrinter.printNode(t.root);
		int r = isBalanced(t.root);
		if (r == -1) {
			System.out.println("Tree is not balanced");
		}
		else {
			System.out.println("Tree is balanced");
		}
	}

}
