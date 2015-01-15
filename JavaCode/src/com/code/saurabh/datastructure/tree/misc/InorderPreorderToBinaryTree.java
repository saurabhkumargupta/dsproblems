package com.code.saurabh.datastructure.tree.misc;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

import com.code.saurabh.DataSructure.tree.BTreePrinter;
import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;

/*
 * Possible and not-possible combinations
 * http://www.geeksforgeeks.org/if-you-are-given-two-traversal-sequences-can-you-construct-the-binary-tree/
 * 
 * 
 * 
 * http://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
 * Construct Tree from given Inorder and Preorder traversals
 */
public class InorderPreorderToBinaryTree {

	static class Traversal {
		int[] input;
		int index;
		Traversal (int[] input) {
			this.input = input;
			index = 0;
		}
	}

	/*
	 * Given preorder and inorder as array and it will return root of any binary tree
	 */
	static public TreeNode buildBT (int[] inorder, int[] preorder) {
		Traversal pre = new Traversal (preorder);
		return buildBT (inorder, pre);
	}
	
	static TreeNode buildBT (int[] inorder, Traversal preorder) {
		if (preorder.index >= preorder.input.length || inorder.length <= 0) {
			return null;
		}
		int key = preorder.input[preorder.index];
		TreeNode node = new TreeNode(key);
		preorder.index++;
		int inorderIndex = ArrayUtils.indexOf(inorder, key);
		if (inorderIndex >= 0) {
			node.left = buildBT (Arrays.copyOfRange(inorder, 0, inorderIndex), preorder);
			node.right = buildBT (Arrays.copyOfRange(inorder, inorderIndex+1, inorder.length), preorder);
		}
		return node;
	}
	public static void main(String[] args) {
		Tree t = new Tree ();
		
		int[][][] input = {
				{
					{20, 30, 35, 40, 45, 50, 55, 60, 65, 70, 80},
					{50, 40, 30, 20, 35, 45, 60, 55, 70, 65, 80}	
				},
				{
					{40, 30, 20, 10},
					{10, 20, 30, 40}
				},
				{
					{5, 6, 9, 8, 7, 10},
					{10, 5, 9, 6, 8, 7}	
				},
		};
		
		for (int i = 0; i < input.length; i++) {
			int[] in  = input[i][0];
			int[] pre = input[i][1];
			t.root = buildBT (in, pre);
			BTreePrinter.printNode(t.root);
			System.out.println();
		}
	}

}
