package com.code.saurabh.DataSructure.tree;

import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;
import com.code.saurabh.util.Pair;

/*
 * http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
 * LCA_boolean & LCA are same.
 * Just Type different one is used with Boolean and another with Integer
 */
public class LowestCommonAncestor {

	/*
	 * At every node, take left result and right result
	 * if both have returned true, then this node is LCA return <true, current node>
	 * if either of left and right is true and current node is one of the keys then return <true, current node>
	 * if either of left and right is true and current node is not one of the keys, then return true but not current node as LCA <true, Null>
	 * if either of left and right is true and second element of pair (in either of left and right) is not null, then return that result
	 * if none of left and right is true, then return <false, Null>
	 */
	static Pair<Integer, TreeNode> LCA (TreeNode root, int n1, int n2) {
		if (root == null) {
			return new Pair <Integer, TreeNode> (0, null);
		}
		Pair<Integer, TreeNode> left = LCA (root.left, n1, n2);
		Pair<Integer, TreeNode> right = LCA (root.right, n1, n2);

		if (left.getFirst() == 1 && right.getFirst() == 1) {
			return new Pair <Integer, TreeNode> (1, root);
		}
		else if (left.getFirst() == 1 || right.getFirst() == 1) {
			if (root.key == n1 || root.key == n2) {
				return new Pair <Integer, TreeNode> (1, root);
			}
			else if (left.getFirst() == 1 && left.getSecond() != null) {
				return new Pair <Integer, TreeNode> (1, left.getSecond());
			}
			else if (right.getFirst() == 1 && right.getSecond() != null) {
				return new Pair <Integer, TreeNode> (1, right.getSecond());
			}
			else
				return new Pair <Integer, TreeNode> (1, null);
		}
		else if (n1 == n2 && root.key == n1) {
			return new Pair <Integer, TreeNode> (1, root);
		}
		else if (root.key == n1 || root.key == n2) {
			return new Pair <Integer, TreeNode> (1, null);
		}
		else
			return new Pair <Integer, TreeNode> (0, null);
	}

	/*
	 * At every node, take left result and right result
	 * if both have returned true, then this node is LCA return <true, current node>
	 * if either of left and right is true and current node is one of the keys then return <true, current node>
	 * if either of left and right is true and current node is not one of the keys, then return true but not current node as LCA <true, Null>
	 * if either of left and right is true and second element of pair (in either of left and right) is not null, then return that result
	 * if none of left and right is true, then return <false, Null>
	 */
	static Pair<Boolean, TreeNode> LCA_boolean (TreeNode root, int n1, int n2) {
		if (root == null) {
			return new Pair <Boolean, TreeNode> (false, null);
		}
		Pair<Boolean, TreeNode> left = LCA_boolean (root.left, n1, n2);
		Pair<Boolean, TreeNode> right = LCA_boolean (root.right, n1, n2);

		if (left.getFirst() == true && right.getFirst() == true) {
			return new Pair <Boolean, TreeNode> (true, root);
		}
		else if (left.getFirst() == true || right.getFirst() == true) {
			if (root.key == n1 || root.key == n2) {
				return new Pair <Boolean, TreeNode> (true, root);
			}
			else if (left.getFirst() == true && left.getSecond() != null) {
				return new Pair <Boolean, TreeNode> (true, left.getSecond());
			}
			else if (right.getFirst() == true && right.getSecond() != null) {
				return new Pair <Boolean, TreeNode> (true, right.getSecond());
			}
			else
				return new Pair <Boolean, TreeNode> (true, null);
		}
		else if (n1 == n2 && root.key == n1) {
			return new Pair <Boolean, TreeNode> (true, root);
		}
		else if (root.key == n1 || root.key == n2) {
			return new Pair <Boolean, TreeNode> (true, null);
		}
		else
			return new Pair <Boolean, TreeNode> (false, null);
	}

	public static void main(String[] args) {
		Tree t = new Tree ();
		t.addToBST(1);
		
		t.addToNotBST(2, 1, "left");
		t.addToNotBST(3, 1, "right");
		
		t.addToNotBST(4, 2, "left");
		t.addToNotBST(5, 2, "right");

		t.addToNotBST(6, 3, "left");
		t.addToNotBST(7, 3, "right");
		
		t.inorder();
		System.out.println();
		
		int [] [] input = {
				{4, 5},
				{4, 6},
				{3, 4},
				{2, 4},
				{4, 4}
		};
		
		BTreePrinter.printNode(t.root);
		for (int i = 0; i < input.length; i++) {
//			Pair <Integer, TreeNode> result = LCA (t.root, input[i][0], input[i][1]);
			Pair <Boolean, TreeNode> result = LCA_boolean(t.root, input[i][0], input[i][1]);
			if (result.getSecond() != null) {
				System.out.println("Result: " + result.getSecond().key);
			}
		}
	}

}
