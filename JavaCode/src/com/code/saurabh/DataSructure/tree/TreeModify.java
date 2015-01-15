/**
 * 
 * http://www.careercup.com/question?id=5485057749811200
 * Given a binary tree and a range as min and max. 
Modify the tree such that number formed by traversing tree from root to leaf all fall in given range. 

let say range is 125 - 136 

             1 
         2       3  
       4   5   6   7 
when you read tree from root to leaf: it will be 124, 125, 136, 137
then values which lies in the range are 135, 136 so, modify tree to:

           1 
       2	   3 
 (null)	 5   6   (null) 
 */
package com.code.saurabh.DataSructure.tree;

import com.code.saurabh.datastructure.tree.misc.InorderPreorderToBinaryTree;
import com.code.saurabh.util.Pair;

/**
 * @author saurabh.gupta
 *
 */
public class TreeModify {

	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		TestCase_1 ();
		TestCase_2 ();
//		TestCase_3 ();
//		TestCase_4 ();
	}

	private static void TestCase_3() {
		Tree t = new Tree ();
		int[][][] input = {
				{
					{7, 8, 9},
					{8, 7, 9}	
				},
				{
					{7, 8, 0, 9, 8},
					{8, 7, 9, 0, 8}
				}
		};
		
		for (int i = 0; i < input.length; i++) {
			System.out.println("Another Tree");
			int[] in  = input[i][0];
			int[] pre = input[i][1];
			t.root = InorderPreorderToBinaryTree.buildBT (in, pre);
			BTreePrinter.printNode(t.root);
			
//			Pair<Integer, Integer> minMax = new Pair<Integer, Integer> (186, 200);
			Pair<Integer, Integer> minMax = new Pair<Integer, Integer> (88, 891);
			int M = multipier (minMax.getFirst());
			if (func (t.root, M, 0, minMax) == false)
				t.root = null;
			System.out.println("Tree After Trimming");
			BTreePrinter.printNode(t.root);
		}
	}

	static boolean func (TreeNode root, int M, int num, Pair<Integer,Integer> minMax) {
		if (root == null) {
			return true;
		}
		int newNum = num * 10 + root.key;
		if (checkRange(newNum, minMax, M) == false) {
			return false;
		}
		if (func (root.left, M/10, newNum, minMax) == false)
			root.left = null;
		if (func (root.right, M/10, newNum, minMax) == false)
			root.right = null;
		return true;
	}
	
	static boolean checkRange (int N, Pair<Integer,Integer> minMax, int M) {
		int min, max;
		if (M <= 0) {
			min = minMax.getFirst();
			max = minMax.getSecond();
//			return false;
		}
		else {
			min = minMax.getFirst()  / M;
			max = minMax.getSecond()  / M;
		}
		if (N < min
				|| N > max) {
			return false;
		}
		return true;
	}

	static int multipier (int num) {
		int orig = num;
		int multipier = 1;
		while (num/10 > 0) {
			multipier = multipier * 10;;
			num = num/10;
		}
		System.out.println("Multiplier for Num: " + orig + " is : " + multipier);
		return multipier;
	}

	static void printRootToLeaf (TreeNode root, int num) {
		if (root == null) {
			return;
		}
		int newNum = num * 10 + root.key;
		if (root.leafNode()) {
			System.out.println(newNum);
			return;
		}
		printRootToLeaf (root.left, newNum);
		printRootToLeaf (root.right, newNum);
	}
	
	static void TestCase_2 () {
		Tree t = new Tree ();
		System.out.println();
		
		t.addToBST(1);

		t.addToNotBST(9, 1, "left");
		t.addToNotBST(8, 1, "right");

		t.addToNotBST(1, 9, "left");
		t.addToNotBST(9, 9, "right");
		
		t.addToNotBST(7, 8, "left");
		t.addToNotBST(5, 8, "right");
		t.inorder();

		System.out.println();
		BTreePrinter.printNode(t.root);
		Pair<Integer, Integer> minMax = new Pair<Integer, Integer> (186, 200);
		int M = multipier (minMax.getFirst());
		func (t.root, M, 0, minMax);
		t.inorder();

		System.out.println();
		printRootToLeaf (t.root, 0);
		BTreePrinter.printNode(t.root);
	}

	static void TestCase_1 () {
		Tree t = new Tree ();
		System.out.println();
		
		t.addToBST(1);

		t.addToNotBST(2, 1, "left");
		t.addToNotBST(3, 1, "right");

		t.addToNotBST(4, 2, "left");
		t.addToNotBST(5, 2, "right");
		
		t.addToNotBST(8, 4, "left");
		t.addToNotBST(9, 4, "right");
		
		t.addToNotBST(8, 5, "left");
		t.addToNotBST(9, 5, "right");
		
		t.addToNotBST(6, 3, "left");
		t.addToNotBST(7, 3, "right");
		
		t.addToNotBST(8, 6, "left");
		t.addToNotBST(9, 6, "right");
		
		t.addToNotBST(8, 7, "left");
		t.addToNotBST(9, 7, "right");

		t.inorder();

		System.out.println();
		
		BTreePrinter.printNode(t.root);
		Pair<Integer, Integer> minMax = new Pair<Integer, Integer> (125, 136);
		int M = multipier (minMax.getFirst());
		if (func (t.root, M, 0, minMax) == false )
			t.root = null;
		t.inorder();

		System.out.println();
		printRootToLeaf (t.root, 0);
		BTreePrinter.printNode(t.root);
	}
	
	static void TestCase_4 () {
		Tree t = new Tree ();
		System.out.println();
		
		t.addToBST(8);
		t.addToBST(7);
		t.addToBST(9);

		t.inorder();

		System.out.println();
		BTreePrinter.printNode(t.root);
		Pair<Integer, Integer> minMax = new Pair<Integer, Integer> (125, 136);
		int M = multipier (minMax.getFirst());
		if (func (t.root, M, 0, minMax) == false)
			t.root = null;
		t.inorder();

		System.out.println();
		printRootToLeaf (t.root, 0);
	}
}
