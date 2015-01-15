/**
 * 
 */
package com.code.saurabh.datastructure.tree.misc;

import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;
import com.code.saurabh.util.IntPair;

/**
 * http://www.geeksforgeeks.org/find-closest-leaf-binary-tree/
 * Given a Binary Tree and a key 'k', find distance of the closest leaf from 'k'.
 * 
 * @author saurabh.gupta
 *
 */
public class ClosestLeaf {

	static IntPair closeLeaf (TreeNode root, int key) {
		if (root == null) {
			return new IntPair(-1, -1);
		}
		
		IntPair left = null;
		IntPair right = null;
		if (root.left != null) {
			left = closeLeaf (root.left, key);
		}
		if (root.right != null) {
			right = closeLeaf (root.right, key);
		}
		if (root.key == key) {
			int D = minFunc (left, right);
			return new IntPair (D, 1);
		}
		
		if (left != null && left.getFirst() != -1) {
			if (right != null && (left.getSecond() + right.getSecond() < left.getFirst())) {
				left.setFirst(left.getSecond() + right.getSecond());
			}
			left.setSecond(left.getSecond()+1);
			return left;
		}
		else if (right != null && right.getFirst() != -1) {
			if (left != null && (right.getSecond() + left.getSecond() < right.getFirst())) {
				right.setFirst(right.getSecond() + left.getSecond());
			}
			right.setSecond(right.getSecond()+1);
			return right;
		}
		int D = minFunc (left, right);
		return new IntPair(-1, D+1);
	}
	
	static int minFunc (IntPair l, IntPair r) {
		if (l == null && r == null) {
			return 0;
		}
		else if (l == null && r != null) {
			return r.getSecond();
		}
		else if (l != null && r == null) {
			return l.getSecond();
		}
		else { //if (l != null && r!= null) {
			return Math.min(l.getSecond(), r.getSecond());
		}
	}

	public static void main(String[] args) {
		Tree t = new Tree ();
//		int input [] = {10, 5, 50, 60, 70, 80, 25, 40, 41,42,43,44,39,38,37,36,15,16,17,18,19,14,13,12,11};
		int input [] = {10, 50, 60, 70, 80, 25, 40, 41,42,43,44,39,38,37,36,15,16,17,18,19,14,13,12,11};
		t.createTree(input);
		for (int i = 0; i < input.length; i++) {
			System.out.println("Closest leaf to " + input[i] + ": " + closeLeaf (t.root, input[i]).getFirst());	
		}
	}

}
