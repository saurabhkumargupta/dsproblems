package com.code.saurabh.datastructure.tree.misc;

import com.code.saurabh.DataSructure.tree.BTreePrinter;
import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;

/*
 * from given preOrder to BST is straigh-forward
 * 
 * this is postorder is given, you have to create BST using it.
 */
public class PostOrderToBST {

	static class Traversal {
		int index;
		Traversal (int index) {
			this.index = index;
		}
	}
	static TreeNode postorderTOBST (int[] in, Traversal index) {
		TreeNode node = new TreeNode (in[index.index]);
		if (index.index == 0) {
			return node;
		}
		index.index--;
		if (in[index.index] > node.key) {
			node.right = postorderTOBST (in, index);
		}
		else {
			return node;
		}
		
		if (in[index.index] < node.key) {
			node.left = postorderTOBST (in, index);
		}
		return node;
	}
	public static void main(String[] args) {
		int [][] input = {
				{20, 34, 36, 35, 30, 45, 40, 55, 65, 80, 70, 60, 50},
				{30, 45, 40, 60, 50}
		};

		Tree t = new Tree ();
		Traversal index = new Traversal (0);
		for (int i = 0; i < input.length; i++) {
			index.index = input[i].length-1;
			t.root = postorderTOBST (input[i], index);
			BTreePrinter.printNode(t.root);
			System.out.println();
		}
	}

}
