/**
 * 
 */
package com.code.saurabh.DataSructure.tree;

import com.code.saurabh.datastructure.stack.Stack;
import com.code.saurabh.util.Pair;

/**
 * @author saurabh.gupta
 *
 */
public class BSTtoDLL {

	static Pair<TreeNode,TreeNode> Recursion (TreeNode root) {
		Pair<TreeNode, TreeNode> result = new Pair<TreeNode, TreeNode> (null, null);
		if (root == null) {
			return result;
		}
		Pair<TreeNode,TreeNode> L = Recursion (root.left);
		Pair<TreeNode,TreeNode> R = Recursion (root.right);
		result = merge (L, R, root);
		return result;
	}

	private static Pair<TreeNode, TreeNode> merge(Pair<TreeNode, TreeNode> L, Pair<TreeNode, TreeNode> R, TreeNode root) {
		TreeNode l = L.getSecond();
		TreeNode r = R.getFirst();
		if (l == null) {
			l = root;
		}
		else {
			l.right = root;
			root.left = l;
		}
		
		if (r == null) {
			r = root;
		}
		else {
			r.left = root;
			root.right = r;
		}
		Pair<TreeNode, TreeNode> result = new Pair<TreeNode, TreeNode> (null, null);
		if (L.getFirst() == null) {
			result.setFirst(root);
		}
		else
			result.setFirst(L.getFirst());
		
		if (R.getSecond() == null) {
			result.setSecond(root);
		}
		else
			result.setSecond(R.getSecond());

		return result;
	}
	static TreeNode bstToDll (TreeNode node) {
		TreeNode newRoot = null;
		TreeNode end = null;
		Stack s = new Stack ();
		while (true) {
			while (node != null) {
				s.push(node);
				node = node.left;
			}
			if (s.isEmpty()) {
				break;
			}
			node = (TreeNode) s.pop();
			if (newRoot == null) {
				newRoot = node;
			}
			node.left = end;
			if (end != null) {
				end.right = node;
			}
			end = node;
			node = node.right;
		}
		return newRoot;
	}
	
	static void printDLL (TreeNode node) {
		System.out.println ("DLL: from left to right");
		TreeNode rightend = null;
		while (node != null) {
			System.out.print(node.key +", ");
			rightend = node;
			node = node.right;
		}
		System.out.println ();
		
		System.out.println ("DLL: from right to left");
		node = rightend;
		while (node != null) {
			System.out.print(node.key +", ");
			node = node.left;
		}
		System.out.println ();
	}
	public static void main(String[] args) {
		int [] [] input = {
				{10, 5, 1, 7, 20, 15, 25, 12, 11, 14, 13},
				{10, 5, 8, 2, 1, 15, 20, 17, 30, 16, 18, 19},
				{10, 5, 8, 2, 1},
				{10,5,4,3,15,14,16}
		};

		for (int i = 0; i < input.length; i++) {
			System.out.println("Iteration");
			Tree t = new Tree ();
			t.createTree(input[i]);
			t.toArray();
			TreeNode dllRoot = bstToDll(t.root);
			printDLL (dllRoot);
			System.out.println();

			System.out.println("Recursion");
			Tree t2 = new Tree ();
			t2.createTree(input[i]);
			t2.toArray();
			Pair<TreeNode, TreeNode> dll = Recursion(t2.root);
			printDLL (dll.getFirst());
			System.out.println();
		}
	}
}
