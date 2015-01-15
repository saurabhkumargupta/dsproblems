package com.code.saurabh.DS.tree.problems;

import com.code.saurabh.DataSructure.tree.BTreePrinter;
import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;

public class TwoNodeDistanceInTree {

	static class Result {
		boolean found;
		int count;
		TreeNode lca;
		Result (boolean s, int c, TreeNode node) {
			this.found = s;
			this.count = c;
			this.lca = node;
		}
	}
	
	static Result distance (TreeNode root, int n1, int n2) {
		if (root == null) {
			return new Result (false, 0, null);
		}
		Result left = distance (root.left, n1, n2);
		Result right = distance (root.right, n1, n2);
		
		if (left.lca != null || right.lca != null) {
			return left.lca != null ? left : right;
		}

		if (left.found == true && right.found == true) {
			return new Result (true, left.count + right.count, root);
		}
		if (left.found == true) {
			if (root.key == n1 || root.key == n2) {
				return new Result (true, left.count, root);
			}
			return new Result (true, left.count + 1, null);
		}
		if (right.found == true) {
			if (root.key == n1 || root.key == n2) {
				return new Result (true, right.count, root);
			}
			return new Result (true, right.count + 1, null);
		}
		if (root.key == n1 && root.key == n2) {
			return new Result (true, 0, root);
		}
		if (root.key == n1 || root.key == n2) {
			return new Result (true, 1, null);
		}
		return new Result (false, 0, null);
	}
	public static void main(String[] args) {
		Tree t = new Tree ();
		int [] input = {100, 90, 80, 95, 93, 97, 200, 300, 400, 500, 350, 325, 375};
		t.createTree(input);
		BTreePrinter.printNode(t.root);
		for (int i = 0; i < input.length -1;i++) {
			for (int j = 0; j < input.length; j++) {
				System.out.println("Distance between: " + input[i] + " and " + input[j] + " : " + distance (t.root, input[i], input[j]).count);
			}
		}
//		System.out.println("Distance: " + distance (t.root, 100, 375).count);
//		System.out.println("Distance: " + distance (t.root, 97, 375).count);
//		System.out.println("Distance: " + distance (t.root, 97, 80).count);
//		System.out.println("Distance: " + distance (t.root, 80, 80).count);
	}

}
