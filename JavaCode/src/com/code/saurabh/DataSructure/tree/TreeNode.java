package com.code.saurabh.DataSructure.tree;

public class TreeNode {
	public TreeNode left;
	public int key;
	public TreeNode right;
	
	public TreeNode (int k) {
		this.left = null;
		this.key = k;
		this.right = null;
	}

	public boolean leafNode() {
		if (this.left == null && this.right == null)
			return true;
		return false;
	}
	
	public boolean singleChild () {
		if (this.left == null
				|| this.right == null) {
			return true;
		}
		return false;
	}

	public String toString () {
		StringBuilder sb = new StringBuilder ();
		sb.append(key);
		return sb.toString();
	}
}
