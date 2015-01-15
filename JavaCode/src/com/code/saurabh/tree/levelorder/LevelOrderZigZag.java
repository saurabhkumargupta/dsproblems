package com.code.saurabh.tree.levelorder;

/*
 * 
 * http://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/
 * 
 */
import java.util.Stack;

import com.code.saurabh.DataSructure.tree.BTreePrinter;
import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;
/*
 *                             100                                                               
                              / \                               
               				90   200
       						/\     \
                          80 95    300               
                             / \     \       
                            93 97    400       
                                     / \   
                                   350  500   
                                   / \     
                                 325 375 
 */
public class LevelOrderZigZag {

	/*
	 * 100 
		90 200 
		300 95 80 
		93 97 400 
		500 350 
		325 375
	 */
	static void zigzag (TreeNode root) {
		if (root == null) {
			return;
		}
		
		Stack<TreeNode> evenstack = new Stack<TreeNode> ();
		Stack<TreeNode> oddstack  = new Stack<TreeNode> ();
		evenstack.add(root);
		while (!evenstack.isEmpty() || !oddstack.isEmpty()) {
			System.out.println();
			while (!evenstack.isEmpty()) {
				TreeNode node = (TreeNode) evenstack.pop();
				System.out.print(node.key + " ");
				if (node.right != null) {
					oddstack.add(node.right);
				}
				if (node.left != null) {
					oddstack.add(node.left);
				}
			}
			System.out.println();
			while (!oddstack.isEmpty()) {
				TreeNode node = (TreeNode) oddstack.pop();
				System.out.print(node.key + " ");
				if (node.left != null) {
					evenstack.add(node.left);
				}
				if (node.right != null) {
					evenstack.add(node.right);
				}
			}
		}
	}
	/*
	 * Output of above tree is: 375 325 350 500 400 97 93 80 95 300 200 90 100

		Oupput for this tree below
			    1
			 2      3
			4    5   6
		  7   8

	    Output:8 7  4 5 6  3 2  1  
	*/
	static void zigzagreverse (TreeNode root) {
		if (root == null) {
			return;
		}
		
		Stack<TreeNode> evenstack = new Stack<TreeNode> ();
		Stack<TreeNode> oddstack  = new Stack<TreeNode> ();
		Stack<TreeNode> printstack  = new Stack<TreeNode> ();
		evenstack.add(root);
		while (!evenstack.isEmpty() || !oddstack.isEmpty()) {
			while (!evenstack.isEmpty()) {
				TreeNode node = (TreeNode) evenstack.pop();
				printstack.add(node);
				if (node.right != null) {
					oddstack.add(node.right);
				}
				if (node.left != null) {
					oddstack.add(node.left);
				}
			}
			while (!oddstack.isEmpty()) {
				TreeNode node = (TreeNode) oddstack.pop();
				printstack.add(node);
				if (node.left != null) {
					evenstack.add(node.left);
				}
				if (node.right != null) {
					evenstack.add(node.right);
				}
			}
		}
		System.out.println();
		while (!printstack.isEmpty()) {
			TreeNode node = printstack.pop();
			System.out.print(node.key + " ");
		}
	}
	
	/*
	 * http://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/ 
	*/
	static void levelRecZigZag (Tree t) {
		int h = t.height(t.root);
		boolean leftToRight = false;
		for (int i = 1; i <= h; i++) {
			printLevel (t.root, i, leftToRight);
			leftToRight = leftToRight == true? false :true;
			System.out.println();
		}
	}

	private static void printLevel(TreeNode root, int level, boolean leftToRight) {
		if(root == null)
	        return;
	    if(level == 1)
	        System.out.print(root.key + " ");
	    else if (level > 1)
	    {
	        if(leftToRight)
	        {
	        	printLevel(root.left, level-1, leftToRight);
	        	printLevel(root.right, level-1, leftToRight);
	        }
	        else
	        {
	        	printLevel(root.right, level-1, leftToRight);
	        	printLevel(root.left, level-1, leftToRight);
	        }
	    }		
	}
	
	public static void main(String[] args) {
		Tree t = new Tree ();
		t.createTree();
		BTreePrinter.printNode(t.root);
		zigzag (t.root);
		zigzagreverse (t.root);
		System.out.println();
		System.out.println("Level Recusive way");
		levelRecZigZag (t);
	}

}
