package com.code.saurabh.DataSructure.tree;

import com.code.saurabh.datastructure.stack.Stack;
import com.code.saurabh.util.IntPair;


/**
 * Find any two elements in BST such that sum of those two nodes = K
 * 
 * @author saurabh.gupta
 *
 */
public class BSTNodePair {

	static IntPair getPair (Tree root, int K) {
		IntPair result = new IntPair ();
		Stack incr = new Stack ();
		Stack decr = new Stack ();
		int leftkey = nextIncreasing (incr, root);
		int rightkey = nextDecreasing (decr, root);

		while (leftkey < rightkey) {
			int sum = leftkey + rightkey;
			if (sum == K) {
				result.setFirst(leftkey);
				result.setSecond(rightkey);
				break;
			}
			else if (sum < K) {
				leftkey = nextIncreasing (incr, root);
			}
			else {
				rightkey = nextDecreasing (decr, root);
			}
		}
		return result;
	}

	static private int nextIncreasing(Stack incr, Tree root) {	
		TreeNode node = (TreeNode) incr.pop();
		if (node == null) {
			node = root.root;
		}
		else {
			node = node.right;
		}
		while (node != null) {
			incr.push(node);
			node = node.left;
		}
		node = (TreeNode) incr.peek ();
		return node.key;
	}

	static private int nextDecreasing(Stack decr, Tree root) {

		TreeNode node = (TreeNode) decr.pop();
		if (node == null) {
			node = root.root;
		}
		else {
			node = node.left;
		}
		while (node != null) {
			decr.push(node);
			node = node.right;
		}
		node = (TreeNode) decr.peek ();
		return node.key;
	}

//	static private int nextDecreasing(Stack decr, Tree root) {
//		
//		TreeNode node = (TreeNode) decr.pop();
//		if (node == null) {
//			node = updateDecreasingStack (decr, root.root);
//		}
//		else {
//			node = updateDecreasingStack (decr, node.left);
//		}
//		return node.key;
//	}
//	static private TreeNode updateDecreasingStack(Stack decr, TreeNode node) {
//		while (node != null) {
//			decr.push(node);
//			node = node.right;
//		}
//		return (TreeNode) decr.peek ();
//	}


//	static private int nextIncreasing(Stack incr, Tree root) {
//		
//		TreeNode node = (TreeNode) incr.pop();
//		if (node == null) {
//			node = updateIncreasingStack (incr, root.root);
//		}
//		else {
//			node = updateIncreasingStack (incr, node.right);
//		}
//		return node.key;
//	}
//	static private TreeNode updateIncreasingStack(Stack incr, TreeNode node) {
//		while (node != null) {
//			incr.push(node);
//			node = node.left;
//		}
//		return (TreeNode) incr.peek ();
//	}

	public static void main(String[] args) {
		int[] input = {50, 30, 20, 25, 23, 40, 70, 60, 80};
		Tree tree = new Tree ();
		tree.createTree(input);
		TreeTraversal ttrvl = new TreeTraversal(tree);
		ttrvl.inorder();
//		ttrvl.preorder ();
		int[] K = {100, 110, 50, 30, 130};
		for (int k: K) {
			IntPair result = getPair (tree, k);
			System.out.println("Sum: " + k);;
			result.print();
			System.out.println();
		}
	}

}
