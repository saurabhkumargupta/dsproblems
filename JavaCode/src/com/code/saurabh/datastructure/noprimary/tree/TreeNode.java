package com.code.saurabh.datastructure.noprimary.tree;

public class TreeNode {

	TreeNode left;
	int data;
	TreeNode right;

	TreeNode (int value) {
		this.left = null;
		this.data = value;
		this.right = null;
	}

	void insert (int value) {
		if (value < data) {
			if (left == null) {
				left = new TreeNode (value);
			}
			else {
				left.insert (value);
			}
		}
		else if (value > data) {
			if (right == null) {
				right = new TreeNode (value);
			}
			else {
				right.insert (value);
			}
		}
	}
	
	public String toString () {
		StringBuilder sb = new StringBuilder ();
		sb.append(data);
		return sb.toString();
	}

	public boolean leafNode() {
		if (this.left == null && this.right == null)
			return true;
		return false;
	}
}
