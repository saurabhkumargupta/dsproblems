/**
 * 
 */
package com.code.saurabh.DataSructure.tree;

import com.code.saurabh.util.Pair;

/**
 * @author saurabh.gupta
 *
 */
public class TreeDiameter {

	static void diaMeter (Tree t) {
		TreeNode root = t.root;
		if (root == null) {
			System.out.println("Dia: 0");
			return;
		}
		Pair<Integer, Integer> result = dia (root);
		System.out.println("Dia: " + result.getSecond());
	}

	// <maxHeight in subtree, Max diameter found>
	/*
	 * From each subtree root, return current maxHeight including current node and current max diameter
	 */
	static private Pair<Integer, Integer> dia(TreeNode node) {
		if (node == null)
			return new Pair<Integer, Integer> (0, 0);
		
		Pair<Integer, Integer> left = dia (node.left);
		Pair<Integer, Integer> right = dia (node.right);
		int maxDia = Math.max(left.getSecond(), right.getSecond());

		int curDia =  left.getFirst() + right.getFirst() + 1;
		if (curDia > maxDia) {
			maxDia = curDia;
		}
		int maxHeight = Math.max(left.getFirst(), right.getFirst()) + 1;
		
		return new Pair<Integer, Integer> (maxHeight, maxDia);
	}

	public static void main(String[] args) {
		Tree t = new Tree ();
		t.createTree();
		t.inorder();
		diaMeter (t);
		int [] input = {10, 7, 8, 9, 6, 5, 4, 2, 3};
		t = new Tree ();
		t.createTree(input);
		t.inorder();
		diaMeter (t);
		
		int [] input_2 = {10, 15, 16, 7, 8, 9, 6, 5, 4, 2, 3};
		t = new Tree ();
		t.createTree(input_2);
		t.inorder();
		diaMeter (t);
	}
}
