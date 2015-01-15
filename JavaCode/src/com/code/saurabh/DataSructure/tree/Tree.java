package com.code.saurabh.DataSructure.tree;

import java.util.LinkedList;
import java.util.List;

import com.code.saurabh.array.ArrayUtils;
import com.code.saurabh.util.Pair;

/**
 * Operations:
 * isEmpty ()
 * addToNotBst ()
 * isMirror ()
 * deleteNode ()
 * addToBst ()
 * inorder ()
 * preorder ()
 * postorder ()
 * inOrderToArray ()
 * toArray ()
 * NodeSumBST ()
 * createTree () : create BST from given array
 * @author saurabh.gupta
 *
 */
public class Tree {
	public TreeNode root;
	
	public Tree () {
		root = null;
	}

	public boolean isEmpty () {
		return root == null ? true : false; 
	}

	public void addToNotBST (int k, int parent, String rightOrLeft) {
		if (this.isEmpty()) {
			this.root = new TreeNode (k);
			return;
		}
		addToNotBST (this.root, k, parent, rightOrLeft);
	}
	
	boolean isMirror (TreeNode t) {
		return isMirror (root, t);
	}


	//level count
	public int height (TreeNode root) {
		if (root == null) {
			 return 0;
		}
		return Math.max(height(root.left), height(root.right) ) +1;
	}

	/**
	 * <pre>
	 * </pre>
	 * @param root2
	 * @param t
	 * @return
	 */
	private boolean isMirror(TreeNode first, TreeNode second) {
		//Null tree are always same
		if (first == null && second == null) {
			return true;
		}
		if (first.key != second.key) {
			return false;
		}
		if (isMirror (first.left, second.left) == false)
			return false;
		if (isMirror (first.right, second.right) == false)
			return false;
		return true;
	}

	/**
	 * <pre>
	 * Function returns tree node whose key value is v
	 * 1. first checks current root, if its key matches required key
	 * 2. then it looks all into left subtree for given v
	 * 3. then it looks all into right subtree for given v
	 * </pre>
	 * @param t
	 * @param v
	 * @return
	 */
	private static TreeNode getNode (TreeNode t, int v) {
		if (t != null) {
			if (t.key == v)
				return t;
			TreeNode left = getNode (t.left, v);
			if (left != null) {
				return left;
			}
			TreeNode right = getNode (t.right, v);
			if (right != null) {
				return right;
			}
		}
		return null;
	}
	
	/**
	 * <pre>
	 * Node to be deleted is
	 * 1. Root node, when root is leaf node
	 * 2. Root node, when root is single child node
	 * 3. Root node, when root has two children
	 * 
	 * Non-root cases
	 * 4. some node which is leaf node and left to parent
	 * 5. some node which is leaf node and right to parent
	 * 6. some node which is single child node and left to parent
	 * 7. some node which is single child node and right to parent
	 * 8. some node which has two children and left to parent
	 * 9. some node which has two children and right to parent
	 * </pre>
	 * @param key
	 */
	public void deleleNode (int key) {
		if (root == null) {
			return;
		}
		
		//pair of parent and child, where child.key == key. child is the node to be deleted
		//parent node of child also needs to be updated
		Pair<TreeNode, TreeNode> searchPair = searchParentChild(root, key);

		TreeNode nodeToDeleted = searchPair.getSecond();
		TreeNode parentOfNodeToDeleted = searchPair.getFirst();
		
		//A. if nodetodelete is either leafNode or have one child only
		if (nodeToDeleted.leafNode() || nodeToDeleted.singleChild()) {
			if (parentOfNodeToDeleted == null) {
				//CASE 1 and CASE 2
				//In case root is node which needs to be deleted
				if (nodeToDeleted.left != null) {
					root = nodeToDeleted.left;
				}
				else {
					root = nodeToDeleted.right;
				}
			}
			else {
				//In case node to be deleted (which is either leafNode or singleChild node) is not root node
				//CASE 4, 5, 6, 7
				if (nodeToDeleted.left != null) {
					if (parentOfNodeToDeleted.left != null && parentOfNodeToDeleted.left.key == nodeToDeleted.key) {
						parentOfNodeToDeleted.left = nodeToDeleted.left;
					}
					else {
						parentOfNodeToDeleted.right = nodeToDeleted.left;
					}
				}
				else {
					if (parentOfNodeToDeleted.left != null && parentOfNodeToDeleted.left.key == nodeToDeleted.key) {
						parentOfNodeToDeleted.left = nodeToDeleted.right;
					}
					else {
						parentOfNodeToDeleted.right = nodeToDeleted.right;
					}
				}
			}
			return;
		}

		//B. if nodetodelete have both children
		TreeNode nodeWhichWillReplaceNodeToBeDeleted = getSmallestChildAndUpdateParentOfThatChild (nodeToDeleted);
		nodeWhichWillReplaceNodeToBeDeleted.left = nodeToDeleted.left;
		nodeWhichWillReplaceNodeToBeDeleted.right = nodeToDeleted.right;
		
		if (parentOfNodeToDeleted == null) {
			//CASE 3
			//this is the case where root node is the one which will be deleted
			root = nodeWhichWillReplaceNodeToBeDeleted;
		}
		//Node to be deleted is left of parent
		else if (parentOfNodeToDeleted.left != null && parentOfNodeToDeleted.left.key == key) {
			//CASE 8
			parentOfNodeToDeleted.left = nodeWhichWillReplaceNodeToBeDeleted;
		}
		//Node to be deleted is right of parent
		else if (parentOfNodeToDeleted.right != null && parentOfNodeToDeleted.right.key == key) {
			//CASE 9
			parentOfNodeToDeleted.right = nodeWhichWillReplaceNodeToBeDeleted;
		}
	}
	
	/**
	 * <pre>
	 * Function finds, smallest element in right subtree of given node
	 * and corresponding parent is also updated
	 * </pre>
	 * @return child which will replace node to be deleted
	 */
	TreeNode getSmallestChildAndUpdateParentOfThatChild (TreeNode root) {
		//pair of parent and child
		Pair<TreeNode, TreeNode> smallestInRight = smallestInRightSubtree (root.right);
		TreeNode parent = smallestInRight.getFirst();
		TreeNode child = smallestInRight.getSecond();
		if (root.right.equals(child)) {
			//if root of right subtree of current node which is to be deleted, is smallest.
			root.right = child.right;
		}
		else {
			//update parent pointer of smallest node in right subtree
			//As in this case, child node wont have left child
			parent.left = child.right;
		}
		return child;
	}
	/**
	 * <pre>
	 * </pre>
	 * @param right
	 * @return Pair of <parent,child> node. Parent node is null, in case root is smallest in right subtree
	 */
	private Pair<TreeNode, TreeNode> smallestInRightSubtree(TreeNode root) {
		TreeNode parent = null;
		while (root.left != null) {
			parent = root;
			root = root.left;
		}
		return new Pair<TreeNode, TreeNode> (parent, root);
	}

	/**
	 * <pre>
	 * Function searches tree for a node where node.key == key,
	 *  then it return pair of parent of that node and node
	 * </pre>
	 * @param root2 
	 * @param key
	 * @return returns a pair of parent,child where child.key == key
	 * In case root.key = key, then parent is null and child is root.
	 */
	private static Pair<TreeNode, TreeNode> searchParentChild(TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		//this check will be applicable for first call only
		if (root.key == key) {
			return new Pair<TreeNode, TreeNode> (null, root);
		}
		if (root.left != null && root.left.key == key) {
			return new Pair<TreeNode, TreeNode> (root, root.left);
		}
		if (root.right != null && root.right.key == key) {
			return new Pair<TreeNode, TreeNode> (root, root.right);
		}

		Pair<TreeNode, TreeNode> parentChildNode = searchParentChild (root.left, key);
		if (parentChildNode != null)
			return parentChildNode;
		parentChildNode = searchParentChild (root.right, key);
		if (parentChildNode != null)
			return parentChildNode;
		return null;
	}

	/**
	 * <pre>
	 * Insert k as right/left child of 'parent' 
	 * 1. Get parent node (getNode)
	 * 2. add k as left or right child, if 'rightOrLeft' is not-empty
	 * 3. otherwise, add new key as normal BST operation.
	 * </pre>
	 * @param r root node
	 * @param k key
	 * @param parent parent key whose child new key should be
	 * @param rightOrLeft decides whether given k should be inserted into left child of parent of right child of parent
	 */
	private void addToNotBST (TreeNode r, int k, int parent, String rightOrLeft) {

		TreeNode node = getNode (r, parent);
		if (rightOrLeft == "left") {
			node.left = new TreeNode (k);
		}
		else if (rightOrLeft == "right") {
			node.right = new TreeNode (k);
		}
		else {
			if ( k < r.key) {
				if (r.left == null)
					r.left = new TreeNode (k);
				else
					addToNotBST (r.left, k, parent, rightOrLeft);
			}
			else if ( k > r.key) {
				if (r.right == null)
					r.right = new TreeNode (k);
				else
					addToNotBST (r.right, k, parent, rightOrLeft);
			}
		}
	}

	public void addToBST (int k) {
		if (this.isEmpty()) {
			this.root = new TreeNode (k);
			return;
		}
		addToBST (this.root, k);
	}
	
	private void addToBST (TreeNode r, int k) {

		if (k < r.key) {
			if (r.left == null)
				r.left = new TreeNode (k);
			else
				addToBST (r.left, k);
		}
		else if (k > r.key) {
			if (r.right == null)
				r.right = new TreeNode (k);
			else
				addToBST (r.right, k);
		}
		else {
			System.out.println("Duplicate Key: " + k);
		}
	}

	public void inorder () {
		if (this.isEmpty()) {
			System.out.println("Tree is Empty");
			return;
		}
		System.out.println("Inorder Recursive");
		inorder (this.root);
		System.out.println();
	}

	private void inorder(TreeNode r) {
		if (r == null)
			return;

		inorder (r.left);
		System.out.print (r.key + ", ");
		inorder (r.right);	
	}

	public void inorderSuccessor (int i) { }
	public void inorderPredecessor (int i) { }
	public boolean isBST () { return false;}

	public Integer[] inorderToArray () {
		if (this.isEmpty()) {
			System.out.println("Tree is Empty");
			return null;
		}
		List<Integer> result = new LinkedList<Integer> ();
		inorderToArray (this.root, result);
		return result.toArray(new Integer[result.size()]);
	}

	private void inorderToArray (TreeNode r, List<Integer> result) {
		if (r == null)
			return;

		inorderToArray (r.left, result);
		result.add(r.key);
		inorderToArray (r.right, result);	
	}
	
	public Integer[] toArray () {
		System.out.println("Tree To Array");
		Integer[] treeArray = inorderToArray ();

		for (int i = 0; i < treeArray.length; i++) {
			System.out.print(treeArray[i] + ", ");
		}
		System.out.println();
		return treeArray;
	}
	

	/**
	 * This function converts given tree to its mirror tree
	 * @param t
	 */
	void convertToMirror () {
		convertToMirror (this.root);
	}

	private TreeNode convertToMirror(TreeNode root) {
		if (root != null) {
			TreeNode left = convertToMirror (root.left);
			TreeNode right = convertToMirror (root.right);
			root.left = right;
			root.right = left;
		}
		return root;
	}

	public static void main(String[] args) {
		Tree tree = null;
		tree = new Tree ();
		
		tree.inorder();
		tree.addToBST(50);
		tree.addToBST(40);
		tree.addToBST(90);
		tree.addToBST(35);
		tree.addToBST(30);
		tree.addToBST(45);
		tree.addToBST(85);
		tree.addToBST(89);
		
		tree.inorder();
		NodeSumInBST (tree);
		PrepareNotBstTree ();
		System.out.println("Deleteion testing");
		testDeletion ();
	}

	/**
	 * <pre>
	 * </pre>
	 */
	private static void testDeletion() {
		int [] input = {50, 40, 60,30,45, 55, 70};
//		int [] input = {50, 40, 30, 45};
		Tree tree = null;
//		Tree tree = new Tree ();
//		tree.createTree(input);
		for (int i = 0; i < input.length; i++) {
			tree = new Tree ();
			tree.createTree(input);
			
			System.out.println("Tree Before deletion: --------------------------------- " + input[i]);
			tree.inorder();
			tree.deleleNode(input[i]);
			System.out.println("Tree After deletion:  --------------------------------- " + input[i]);
			tree.inorder();
			System.out.println();
		}
	}

	private static void PrepareNotBstTree() {
		Tree tree = null;
		tree = new Tree ();

		System.out.println();		
		System.out.println("Non BST Tree");
		tree.addToNotBST(10, -1, null);
		tree.addToNotBST(20, 10, "left");
		tree.addToNotBST(5, 20, "right");
		tree.addToNotBST(1, 10, "right");
		tree.addToNotBST(3, 1, "right");
		tree.inorder();
		System.out.println();
	}

	private static void NodeSumInBST (Tree tree) {
/*		Given a sum, how will you identify if 2 nodes are equal to that sum exist in BST */
		Pair<Integer, Integer> result = ArrayUtils.getNodePairsOfSumFromSortedArray (tree.toArray(), 125);

		if (result == null)
			System.out.println("No Result");
		else
			System.out.println("Result : " + result.getFirst() + ": " + result.getSecond());
	}

	/**
	 * <pre>
	 * </pre>
	 * @param input
	 */
	public void createTree(int[] input) {
		for (int i = 0; i < input.length; i++) {
			addToBST(input[i]);
		}
	}
	
	public void createTree () {
		int [] input = {100, 90, 80, 95, 93, 97, 200, 300, 400, 500, 350, 325, 375};
		createTree (input);
	}
	
	public void printTree () {
		BTreePrinter.printNode(root);
	}
}
