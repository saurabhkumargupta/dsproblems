package com.code.saurabh.DataSructure.tree;

import com.code.saurabh.datastructure.stack.Stack;

public class TreeTraversal {

	Tree tree;

	TreeTraversal (Tree t) {
		tree = t;
	}
	
	TreeTraversal () {
		
	}
	
	void setTree (Tree t) {
		tree = t;
	}

	void inorderIterative () {
		TreeNode localroot = tree.root;
		Stack stack = new Stack ();
		System.out.println("Inorder Iterative");
		while (true) {
			/*Put all left nodes until null*/
			while (localroot != null) {
				stack.push(localroot);
				localroot = localroot.left;
			}

			if (stack.isEmpty()) {
				System.out.println();
				return;
			}

			/*get top node. print it and mark pointer to right which if null will get next correct inorder node*/
			Object node = stack.pop();
			TreeNode treenode = (TreeNode)node;
			System.out.print(treenode.key + ", ");
			localroot = treenode.right;
		}
	}

	void preorderIterative () {
		TreeNode localroot = tree.root;
		Stack stack = new Stack ();
		System.out.println("preorder Iterative");
		stack.push(localroot);
		while (!stack.isEmpty()) {
			Object node = stack.pop();
			TreeNode treenode = (TreeNode)node;
			System.out.print(treenode.key + ", ");

			if (treenode.right != null)
				stack.push(treenode.right);			

			if (treenode.left != null)
				stack.push(treenode.left);
		}
		System.out.println();
	}
	
	void postorderIterative () {
		System.out.println("postorder Iterative");

		TreeNode localroot = tree.root;
		TreeNode nullNode = null;
		Stack stack = new Stack ();
		stack.push(localroot);
		
		while (!stack.isEmpty()) {
			Object node = stack.pop();
			TreeNode treenode = (TreeNode)node;
			
			if (treenode == null) {
				node = stack.pop();
				treenode = (TreeNode)node;
				System.out.print(treenode.key + ", ");
			}
			else if (treenode.left == null && treenode.right == null) {
				System.out.print(treenode.key + ", ");				
			}
			else {
				stack.push(treenode);
				stack.push(nullNode);

				if (treenode.right != null)
					stack.push(treenode.right);			

				if (treenode.left != null)
					stack.push(treenode.left);
			}
		}
	}
	
	void inorder () {
		if (tree.isEmpty()) {
			System.out.println("Tree is Empty");
			return;
		}
		System.out.println("Inorder Recursive");
		inorder (tree.root);
		System.out.println();
	}

	private void inorder(TreeNode r) {
		if (r == null)
			return;
		inorder (r.left);
		System.out.print (r.key + ", ");
		inorder (r.right);	
	}

	void preorder () {
		if (tree.isEmpty()) {
			System.out.println("Tree is Empty");
			return;
		}
		System.out.println("Preorder Recursive");
		preorder (tree.root);
		System.out.println();
	}

	private void preorder(TreeNode r) {
		if (r == null)
			return;
		System.out.print (r.key + ", ");
		preorder (r.left);
		preorder (r.right);	
	}

	void postorder () {
		if (tree.isEmpty()) {
			System.out.println("Tree is Empty");
			return;
		}
		System.out.println("Postorder Recursive");
		postorder (tree.root);
		System.out.println();
	}

	private void postorder(TreeNode r) {
		if (r == null)
			return;
		postorder (r.left);
		postorder (r.right);
		System.out.print (r.key + ", ");	
	}

	public static void main(String[] args) {
		TraversalTesting ();
	}

	private static void TraversalTesting() {
		int[][] input = {
				{50,40,90,35,30,45,84,89},
				{1,2,3,4},
				{9,8,7,6},
				{10,11,9,7,12,13,4, 8},
				{50,40,30,20,35,45,60,55,70,65,80}
		};

		TreeTraversal tree = null;
		for (int i = 0; i < input.length; i++) {
			System.out.println();
			Tree t = new Tree ();
			for (int j = 0; j < input[i].length; j++) {
				t.addToBST(input[i][j]); 
			}
			tree = new TreeTraversal (t);
			tree.inorder();
			tree.inorderIterative();
			tree.preorder();
			tree.preorderIterative();
			tree.postorder();
			tree.postorderIterative();
//			tree.levelorder();
//			tree.levelorder_part_2();
//			tree.levelorder_alternate_corners();
//			tree.levelorder_alternate_corners_part_2();
		}
	}
}
