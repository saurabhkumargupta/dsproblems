package com.code.saurabh.datastructure.tree.misc;

import com.code.saurabh.DataSructure.tree.BTreePrinter;
import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;

/*
 * Given preorder and postorder
 * And given tree is full binary tree: either each node has 2 child or 0 child
 * 
 * PrePostToNaryTree.java, in this this either 2 or 0 child constraint is not there. (there is not left or right child but 0, 1, 2 or n childs) 
 */
public class PrePostTraversalToTree {

	static class Traversal {
		int[] input;
		int s;
		int e;
		Traversal (int[] input, int s, int e) {
			this.input = input;
			this.s = s;
			this.e = e;
		}
	}

	static TreeNode f (Traversal pre, Traversal post) {
		int key = pre.input[pre.s];
		TreeNode root = new TreeNode (key);
		if (pre.s == pre.e) {
			return root;
		}
		int postIndex = searchInPost (key, post);
		if (postIndex == -1) {
			System.out.println("Somethis wrong 1 ");
			return null;
		}
		if (postIndex == post.s) {
			return root;
		}
		
		int leftrootindex = pre.s +1;
		int tempIndex = searchInPost (pre.input[leftrootindex], post);
		if (tempIndex == -1) {
			System.out.println("Somethis wrong 2");
			return null;
		}
		int rightrootindex = leftrootindex + (tempIndex - post.s) + 1;
		
		Traversal nPre = new Traversal (pre.input, pre.s, pre.e);
		Traversal nPost = new Traversal (post.input, post.s, post.e);
		
		nPre.s = leftrootindex;
		nPre.e = rightrootindex -1;
		
		nPost.s = post.s;
		nPost.e = tempIndex;
		root.left = f (nPre, nPost);

		nPre.s = rightrootindex;
		nPre.e = pre.e;
		
		nPost.s = tempIndex+1;
		nPost.e = post.e -1;
		root.right = f (nPre, nPost);
		return root;
	}

	static private int searchInPost(int key, Traversal post) {
		int s = post.s;
		int e = post.e;
		for (int i = s; i <= e; i++) {
			if (post.input[i] == key) {
				return i;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
//		Tree t = new Tree ();
//		
////		int[] preI =  {50, 40, 30, 45, 60};
////		int[] postI = {30, 45, 40, 60, 50};
//
//		int[] preI =  {50, 40, 30, 20, 35, 34, 36, 45, 60, 55, 70, 65, 80};
//		int[] postI = {20, 34, 36, 35, 30, 45, 40, 55, 65, 80, 70, 60, 50};
//		
//		Traversal pre = new Traversal (preI, 0, preI.length-1);
//		Traversal post = new Traversal (postI, 0, postI.length -1);
//		
//		t.root = f (pre, post);
//		BTreePrinter.printNode(t.root);
//		
		testCase ();
	}
	
	static private void testCase () {
		int [][][] input = {
				{
					{50, 40, 30, 45, 60},
					{30, 45, 40, 60, 50}
				},
				{
					{50, 40, 30, 20, 35, 34, 36, 45, 60, 55, 70, 65, 80},
					{20, 34, 36, 35, 30, 45, 40, 55, 65, 80, 70, 60, 50}
				}
		};
		
		Tree t = new Tree ();
		for (int i = 0; i < input.length; i++) {
			Traversal pre = new Traversal (input[i][0], 0, input[i][0].length-1);
			Traversal post = new Traversal (input[i][1], 0, input[i][1].length -1);
			
			t.root = f (pre, post);
			BTreePrinter.printNode(t.root);
			System.out.println();
		}
	}

}
