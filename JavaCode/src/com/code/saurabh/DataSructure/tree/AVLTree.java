/**
 * http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 * TODO: deletion
 */
package com.code.saurabh.DataSructure.tree;

/**
 * @author saurabh.gupta
 *
 */
public class AVLTree {

	
	class AVLTreeNode {

		AVLTreeNode left;
		int key;
		int height;
		int size; // total node in subtree including subtree root
		AVLTreeNode right;
		
		AVLTreeNode (int k) {
			this.left = null;
			this.key = k;
			this.height = 1;
			this.size = 1;
			this.right = null;
		}

		@SuppressWarnings("unused")
		public boolean leafNode() {
			if (this.left == null && this.right == null)
				return true;
			return false;
		}
		
		@SuppressWarnings("unused")
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
		
		public void printChildren () {
			System.out.println("Parent: " + key + ", Size of this subtree: " + size);
			if (left != null)
				System.out.println("left: " + left.key);
			else
				System.out.println("left: null");
			if (right != null)
				System.out.println("right: " + right.key);
			else
				System.out.println("right: null");
		}

		/**
		 * @return the size
		 */
		public int getSize() {
			return size;
		}

		/**
		 * @param size the size to set
		 */
		public void setSize(int size) {
			this.size = size;
		}

		/**
		 * @return the height
		 */
		@SuppressWarnings("unused")
		public int getHeight() {
			return height;
		}
		/**
		 * @param height the height to set
		 */
		@SuppressWarnings("unused")
		public void setHeight(int height) {
			this.height = height;
		}
	}

	AVLTreeNode root = null;
	
	void addToAVL (int k) {
		if (root == null) {
			root = new AVLTreeNode (k);
		}
		else {
			root = addToAVL (root, k);
		}
	}
	
	AVLTreeNode addToAVL (AVLTreeNode r, int k) {
		if (k < r.key) {
			if (r.left == null) {
				r.left = new AVLTreeNode (k);
			}
			else {
				r.left = addToAVL (r.left, k);
			}
		}
		else if (k > r.key) {
			if (r.right == null) {
				r.right = new AVLTreeNode (k);
			}
			else {
				r.right = addToAVL (r.right, k);
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

	/**
	 * <pre>
	 * </pre>
	 * @param r
	 */
	protected void updateSize(AVLTreeNode r) {
		if (r == null) {
			return;
		}

		int left = 0;
		int right = 0;
		//size of left subtree
		if (r.left != null) {
			left = r.left.size;
		}
		//size of right subtree
		if (r.right != null) {
			right = r.right.size;
		}
		//size of tree
		r.size = left + right + 1;
	}

	/**
	 * <pre>
	 * new element : 4
	 * 
                 75 
          10            100
       6     11      90
     5   8
   4 
 
 change to  (right rotation)
 Tree become unbalanced @ root 10, use right rotation, which will make
  1. 6 root of that subtree
  2. old root of that subtree (10) will become right child of new root of that subtree (6)
  3. new root's old right child (8) become, new left child of old root (10) 
  4. old root's parents left child is updated with new child

                 75 
          6            100
       5     10      90
     4      8  11

	 * </pre>
	 * @param r
	 */
	protected AVLTreeNode rightRotate(AVLTreeNode oldroot) {
		//Many (redundant) variables are used but they make semantic easy to understand
		AVLTreeNode leftofoldroot = oldroot.left;
		AVLTreeNode newRoot = leftofoldroot;
		AVLTreeNode newRootrightchild = newRoot.right;
		
		newRoot.right = oldroot;
		oldroot.left = newRootrightchild;
		updateHeight (oldroot);
		updateSize (oldroot);
//		updateHeight (newRoot); 
		return newRoot;
	}

	/**
	/**
	 * <pre>
	 * new element : 40
	 * 
                 10 
          5             20
       4     7       15      30
                          25    35
                                  40 
 
 change to  (right rotation)
 Tree become unbalanced @ root 20, use left rotation, which will make
  1. 30 root of that subtree
  2. old root of that subtree (20) will become left child of new root of that subtree (30)
  3. new root's old left child (25) become, new right child of old root (20) 
  4. old root's parents right child is updated with new child

                 10 
          5             30
       4     7       20      35
                   15   25      40

	 * </pre>
	 */
	protected AVLTreeNode leftRotate(AVLTreeNode oldroot) {
		//Many (redundant) variables are used but they make semantic easy to understand
		AVLTreeNode rightofoldroot = oldroot.right;
		AVLTreeNode newRoot = rightofoldroot;
		AVLTreeNode newRootoldleftchild = newRoot.left;

		newRoot.left = oldroot;
		oldroot.right = newRootoldleftchild;
		updateHeight (oldroot);
		updateSize (oldroot);
//		updateHeight (newRoot); 
		return newRoot;
	}

	int heightBalance (AVLTreeNode r) {
		int left = 0;
		int right = 0;
		//Height of left subtree
		if (r.left != null) {
			left = r.left.height;
		}
		//Height of right subtree
		if (r.right != null) {
			right = r.right.height;
		}

		return (left - right);
	}
	/**
	 * <pre>
	 * </pre>
	 * @param r
	 */
	protected void updateHeight(AVLTreeNode r) {
		if (r == null) {
			return;
		}

		int left = 0;
		int right = 0;
		//Height of left subtree
		if (r.left != null) {
			left = r.left.height;
		}
		//Height of right subtree
		if (r.right != null) {
			right = r.right.height;
		}
		//height of tree
		r.height = Math.max (left, right) + 1;
	}

	void childOfSubtreeRootedAt (AVLTreeNode r, int k) {
		if (r != null) {
			if (r.key == k) {
				r.printChildren ();
				return;
			}
			childOfSubtreeRootedAt (r.left, k);
			childOfSubtreeRootedAt (r.right, k);
		}
	}
	int getTreeHeight () {
		if (root == null) {
			return 0;
		}

		int left = 0;
		int right = 0;
		//Height of left subtree
		if (root.left != null) {
			left = root.left.height;
		}
		//Height of right subtree
		if (root.right != null) {
			right = root.right.height;
		}
		//height of tree
		return Math.max (left, right) + 1;
	}
	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		AVLTree tree = new AVLTree ();
//		int [] input = {50, 10, 75, 6, 11};
//		int [] input = {50, 10, 75, 6, 11, 4}; //for right-right
//		int [] input = {50, 10, 75, 6, 11, 12}; //for left-right

//		int [] input = {50, 10, 75, 55, 85};
//		int [] input = {50, 10, 75, 55, 85, 90}; //for left-left
		int [] input = {50, 10, 75, 55, 85, 60}; //for right-left

		for (int i = 0; i < input.length; i++) {
			System.out.println("Tree Height before: " + input[i] + " is:  " + tree.getTreeHeight());
			tree.addToAVL(input[i]);
			System.out.println("Tree Height after: " + input[i] + " is:  " + tree.getTreeHeight());
		}

		tree.allChilds (tree.root);
		System.out.println();
		tree.inorder(tree.root);
	}

	/**
	 * <pre>
	 * </pre>
	 * @param root2
	 */
	protected void allChilds(AVLTreeNode r) {
		if (r == null)
			return;
		System.out.println();
		r.printChildren ();
		allChilds (r.left);
		allChilds (r.right);
	}

	private void inorder(AVLTreeNode r) {
		if (r == null)
			return;
		inorder (r.left);
		System.out.print (r.key + ", ");
		inorder (r.right);	
	}
}
