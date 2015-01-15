package com.code.saurabh.datastructure.tree;

import java.util.Stack;

import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;

public class InorderSuccessorPredecessor {

	static void inorderSuccessorPredecessor (TreeNode root, int key) {
		Stack<TreeNode> s = new Stack<TreeNode> ();
		TreeNode node = root;
		boolean iskeyfirst = false;//to denote that key is first of inorder to print predecessor
		boolean prevFound = false;
		TreeNode prev = null;
		TreeNode succ = null;
		boolean keyFound = false;
		
		while (true) {
			while (node != null) {
				s.add(node);
				node = node.left;
			}
			if (s.isEmpty()) {
				break;
			}
			node = s.pop();
			if (keyFound == true) {
				succ = node;
				break;
			}
			if (node.key == key) {
				keyFound = true;//this is required for successor
				if (prev == null) {
					iskeyfirst = true;//for predecessor
				}
				prevFound = true;
			}
			if (prev == null || prevFound == false) {
				prev = node;
			}
			node = node.right;
		}

		if (keyFound == true) {
			if (succ != null) {
				System.out.println("Successor of key: " + key + " " + succ.key);
			}
			else {
				System.out.println("No Successor of key: " + key);
			}

			if (prev != null && iskeyfirst == false) {
				System.out.println("Predeccessor of key: " + key + " " + prev.key);
			}
			else {
				System.out.println("No Predeccessor of key: " + key);
			}
		}
		else {
			System.out.println("Key Not Found");
		}
	}

	static void inorderSuccessorPredecessor_sep (TreeNode root, int key) {
		inorderSuccessor (root, key);
		inorderPredecessor (root, key);
	}

	private static void inorderPredecessor(TreeNode root, int key) {
		Stack<TreeNode> s = new Stack<TreeNode> ();
		TreeNode prev = null;
		TreeNode node = root;
		while (true) {
			while (node != null) {
				s.add(node);
				node = node.left;
			}
			if (s.isEmpty()) {
				break;
			}
			node = s.pop();
			if (node.key == key) {
				break;
			}
			prev = node;
			node = node.right;
		}

		//All these so many conditions for if key is not found. We can simply use flag also
		if (prev != null && node!= null && node.key == key) {
			System.out.println("Predeccessor of key: " + key + " " + prev.key);
		}
		else {
			System.out.println("No Predeccessor of key: " + key);
		}
	}

	private static void inorderSuccessor(TreeNode root, int key) {
		Stack<TreeNode> s = new Stack<TreeNode> ();
		TreeNode succ = null;
		boolean keyFound = false;
		TreeNode node = root;
		while (true) {
			while (node != null) {
				s.add(node);
				node = node.left;
			}
			if (s.isEmpty()) {
				break;
			}
			node = s.pop();
			if (keyFound == true) {
				succ = node;
				break;
			}
			if (node.key == key) {
				keyFound = true;
			}
			node = node.right;
		}
		if (succ != null) {
			System.out.println("Successor of key: " + key + " " + succ.key);
		}
		else {
			System.out.println("No Successor of key: " + key);
		}
	}

	public static void main(String[] args) {
		Tree t = new Tree ();
		t.createTree();
		t.printTree();
		Integer[] input = t.toArray();
		for (int i = 0; i < input.length; i++) {
			inorderSuccessorPredecessor (t.root, input[i]);
			inorderSuccessorPredecessor_sep (t.root, input[i]);
			System.out.println();
		}
		
		int key = 120;
		inorderSuccessorPredecessor (t.root, key);
		inorderSuccessorPredecessor_sep (t.root, key);
	}

}
