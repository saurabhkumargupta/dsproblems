package com.code.saurabh.datastructure.noprimary.tree;

import com.code.saurabh.util.Pair;

public class TreeDiameter {
	
	public static void main (String[] args) {
		TestCase (50,40,30,20,35,45,44,43,42,47,49,60);
		TestCase (50,40,30,20);
		TestCase (50,40,60);
		TestCase (50,40,30,20,35,45,44,43,42,41,47,49,60);
		TestCase (50,40,30,20,35,45,44,43,42,47,49,60,70,80,90,65,64,63,62,61,85,84,83);
		TestCase (50,60,70,80,90,65,64,63,62,61,85,84,83);
		TestCase (100,20,10,5,50,60,70,80,55,45,40,25,35,30,29,120,130);
	}

	private static void TestCase(Integer...data) {
		System.out.println ("New TestCase");
		Tree tree = null;
		tree = new Tree ();
		for (Integer val: data)
			tree.addNode(val);
		tree.inorder();
		tree.preorder();
		
		Pair<Integer, Integer> result = null;
		result = getD (tree.root);
		System.out.println ("New Diameter: " + result.getFirst());
	
	}

	/* first = diameter
	 * second = maxsubtreelength*/
	private static Pair<Integer, Integer> getD(TreeNode root) {

		Pair<Integer, Integer> result = new Pair <Integer, Integer> (0,0);
		if (root == null || Tree.isEmpty(root)) {
			return result;
		}
		if (root.leafNode ()) {
			result.setFirst(1);
			result.setSecond(1);
			return result;
		}
		Pair<Integer, Integer> left = getD (root.left);
		Pair<Integer, Integer> right = getD (root.right);
		
		int new_dia = left.getSecond() + right.getSecond() + 1;
		int big_tree = (left.getSecond() > right.getSecond())?left.getSecond():right.getSecond();
		int big_dia = left.getFirst() > right.getFirst() ? left.getFirst():right.getFirst();

		if (new_dia > big_dia) {
			big_dia = new_dia;
		}
		result.setFirst(big_dia);
		result.setSecond(big_tree+1);
		return result;
	}

}
