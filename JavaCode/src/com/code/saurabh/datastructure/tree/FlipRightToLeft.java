package com.code.saurabh.datastructure.tree;

import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;

/*
 * http://www.careercup.com/question?id=6266917077647360
 * Given a binary tree where all the right nodes are leaf nodes, flip it upside down and turn it into a tree with left leaf nodes. 

Keep in mind: ALL RIGHT NODES IN ORIGINAL TREE ARE LEAF NODE.


/* for example, turn these:
 *
 *        1                 1
 *       / \                 / \
 *      2   3            2   3
 *     / \
 *    4   5
 *   / \
 *  6   7
 *
 * into these:
 *
 *        1               1
 *       /               /
 *      2---3         2---3
 *     /
 *    4---5
 *   /
 *  6---7
 *
 * where 6 is the new root node for the left tree, and 2 for the right tree.
 * oriented correctly:
 *
 *     6                   2
 *    / \                   / \
 *   7   4              3   1
 *        / \
 *       5   2
 *            / \
 *          3   1
 *
 */
public class FlipRightToLeft {

	static TreeNode flip (TreeNode newroot, TreeNode newleft, TreeNode newright) {
		TreeNode left = newroot.left;
		TreeNode right = newroot.right;
		
		newroot.left = newleft;
		newroot.right = newright;
		
		if (left == null) {
			return newroot;
		}
		return flip (left, right, newroot);
	}

	public static void main(String[] args) {
		Tree t = new Tree ();
		t.addToBST(1);
		
		t.addToNotBST(2, 1, "left");
		t.addToNotBST(3, 1, "right");
		
		t.addToNotBST(4, 2, "left");
		t.addToNotBST(5, 2, "right");

		t.addToNotBST(6, 4, "left");
		t.addToNotBST(7, 4, "right");
		
		t.inorder();
		System.out.println();
		
		TreeNode newRoot = flip (t.root.left, t.root.right, t.root);
		t.root.left = null;
		t.root.right = null;
		t.root = newRoot;
		t.inorder();
	}

}
