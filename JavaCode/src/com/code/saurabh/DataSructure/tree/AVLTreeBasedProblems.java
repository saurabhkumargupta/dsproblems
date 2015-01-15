/**
 * 
 * Count smaller elements on right side: http://www.geeksforgeeks.org/count-smaller-elements-on-right-side/
 * 
 */
package com.code.saurabh.DataSructure.tree;


/**
 * @author saurabh.gupta
 *
 */
public class AVLTreeBasedProblems extends AVLTree {
	
	private class Result {
		int count = 0;
		Result () {
		}

		void setResultCount (int c) {
			count = c;
		}

		int getResultCount () {
			return count;
		}
	}

	void addToAVLAugmented (int k, Result result) {
		if (root == null) {
			root = new AVLTreeNode (k);
		}
		else {
			root = addToAVLAugmented (root, k, result);
		}
	}
	
	AVLTreeNode addToAVLAugmented (AVLTreeNode r, int k, Result result) {
		if (k < r.key) {
			if (r.left == null) {
				r.left = new AVLTreeNode (k);
			}
			else {
				r.left = addToAVLAugmented (r.left, k, result);
			}
		}
		else if (k > r.key) {
			int leftchild = 0;
			if (r.left != null)
				leftchild = r.left.size;
			result.setResultCount(leftchild+1);

			if (r.right == null) {
				r.right = new AVLTreeNode (k);
			}
			else {
				r.right = addToAVLAugmented (r.right, k, result);
			}
		}
		
		updateHeight (r);
		updateSize (r);
		
		//left subtree is bigger
		if (heightBalance(r) > 1) {
			if (k < r.left.key) {
				//Left-Left case
				r = rightRotate (r);
			}
			else {
				//Left-Right case
				r.left = leftRotate(r.left);
				r = rightRotate (r);
			}
		}
		//right subtree is unbalanced
		else if (heightBalance(r) < -1) {
			if (k > r.right.key) {
				//right-right case
				r = leftRotate (r);
			}
			else {
				//right-Left case
				r.right = rightRotate(r.right);
				r = leftRotate (r);
			}
		}
		updateHeight (r);
		updateSize (r);
		return r;
	}

	static void countSmallerElementsOnRightSide (int[] input) {
		AVLTreeBasedProblems tree = new AVLTreeBasedProblems ();
		Result result = tree.new Result ();
		for (int i = input.length-1; i >= 0; i--) {
			tree.addToAVLAugmented(input[i], result);

//			System.out.println("Tree After " + input[i]);
//			tree.allChilds(tree.root);
			System.out.println("total Smaller value to right of " + input[i] + " is :" + result.getResultCount());
			result.setResultCount(0);
		}
	}
	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		int [] input = {4, 8, 1, 3, 15, 9, 25, 6 ,10, 75};
		countSmallerElementsOnRightSide (input);
	}

}
