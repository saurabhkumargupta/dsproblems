package com.code.saurabh.datastructure.noprimary.tree;

public class Tree {
	TreeNode root;
	Tree () {
		this.root = null;
	}

	void addNode (int value) {
		if (root == null){
			root = new TreeNode (value);
		}
		else {
			root.insert (value);
		}
	}

	private void inorderHelper (TreeNode node) {
		if (node == null)
			return;

		inorderHelper (node.left);
		System.out.print ("" + node.data + ", ");
		inorderHelper (node.right);
	}
	
	void inorder () {
		if (root == null){
			System.out.println ("Empty Tree");
			return;
		}
		System.out.println ("Inorder Output");
		inorderHelper (root);
		System.out.println ();
	}

	private void preorderHelper (TreeNode node) {
		if (node == null)
			return;

		System.out.print ("" + node.data + ", ");		
		preorderHelper (node.left);
		preorderHelper (node.right);
	}
	
	void preorder () {
		if (root == null){
			System.out.println ("Empty Tree");
			return;
		}
		System.out.println ("preorder Output");
		preorderHelper (root);
		System.out.println ();
	}

	private void postorderHelper (TreeNode node) {
		if (node == null)
			return;
	
		postorderHelper (node.left);
		postorderHelper (node.right);
		System.out.print ("" + node.data + ", ");	
	}
	
	void postorder () {
		if (root == null){
			System.out.println ("Empty Tree");
			return;
		}
		System.out.println ("Postorder Output");
		postorderHelper (root);
		System.out.println ();
	}

	public static boolean isEmpty (TreeNode root) {
		if (root == null)
			return true;
		else
			return false;
	}

	public static void main (String[] args) {
		Tree tree = null;
		tree = new Tree ();

		tree.inorder();
		tree.addNode(40);
		tree.addNode(30);
		tree.addNode(50);
		tree.addNode(45);
		tree.addNode(55);
		tree.addNode(25);
		tree.addNode(35);
		tree.inorder();
		tree.preorder();
		tree.postorder();
	}
}
