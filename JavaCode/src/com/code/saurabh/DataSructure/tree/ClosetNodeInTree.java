/**
 * 
 */
package com.code.saurabh.DataSructure.tree;

/**
 * @author saurabh.gupta
 *
 */
public class ClosetNodeInTree {
	/* Given a BST and a target value, find the node in the given BST that is closest to the input value. 

    50
40      60
30  45  55  70

Target - 61

	 */

	static TreeNode tailClosestTarget (TreeNode node, int key, TreeNode targetNode) {
		if (node == null) {
			return targetNode;
		}

		if (node.key == key) {
			return node;
		}

		if (targetNode == null) {
			targetNode = node;
		}

		int currdiff = Math.abs (node.key - key);
		int olddiff = Math.abs (targetNode.key - key);
		if (currdiff < olddiff) {
			targetNode = node;
		}

		if (key > node.key) {
			return tailClosestTarget (node.right, key, targetNode);
		}
		else { // if (key < node.key)
			return tailClosestTarget (node.left, key, targetNode);
		}
	}


	static TreeNode headClosestTarget (TreeNode node, int key) {
		if (node == null) {
			return null;
		}

		TreeNode closeNode = null;
		if (node.key == key) {
			return node;
		}
		else if (key > node.key) {
			closeNode = headClosestTarget (node.right, key);
		}
		else if (key < node.key) {
			closeNode = headClosestTarget (node.left, key);
		}

		if (closeNode != null) {
			int ret_diff = Math.abs (closeNode.key - key);
			int cur_diff = Math.abs (node.key - key);
			if (cur_diff < ret_diff) {
				return node;
			}
			else {
				return closeNode;
			}
		}
		return node;
	}


	static TreeNode closestTargetInterative (TreeNode root, int key) {
		TreeNode node = root;
		int oldDiff = Integer.MAX_VALUE;
		TreeNode resultNode = root;

		while (node != null) {
			if (key == node.key) {
				resultNode = node;
				break;
			}

			int currDiff = Math.abs (node.key - key);
			if (currDiff < oldDiff) {
				oldDiff = currDiff;
				resultNode = node;
			}

			if (key > node.key) {
				node = node.right;
			}
			else {
				node = node.left;
			}
		}
		return resultNode;
	}
	public static void main(String[] args) {
		Tree t = new Tree ();
		int input [] = {50, 40, 30, 45, 60, 55, 70};
		t.createTree(input);
		int [] check = {61, 46, 30, 100, 1};
		
		for (int i = 0; i < check.length; i++) {
			int key = check[i];
			System.out.println();
			System.out.println("Closest to key: " + key);
			TreeNode result = tailClosestTarget(t.root, key, null);
			System.out.println("tail result: " + result.key);

			result = headClosestTarget(t.root, key);
			System.out.println("head result: " + result.key);
			
			result = closestTargetInterative(t.root, key);
			System.out.println("iter result: " + result.key);			
		}
	}

}
