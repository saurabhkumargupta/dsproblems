package com.code.saurabh.DataSructure.tree;

import java.util.ArrayList;
import java.util.List;

//Not done
// Given a sorted integer array, how many Binary Search trees can be formed from it? (Wallmart)
public class SortedArrayToBSTCount {

	public static void main(String[] args) {
		int [][] input = {
				{1, 5, 7, 9} //14 unique BST	
		};
		
		for (int i = 0; i < input.length; i++) {
			System.out.println("Count " + totalTree (input[i]));
		}
	}

	private static int totalTree(int[] input) {
		List<ArrayList<Integer>> allInput = SimilarTrees.getAllPerm (input);
		int[][] result = SimilarTrees.ListToArray(allInput);
		
		int bstcount = 0;
		for (int i = 0; i < result.length; i++) {
			Tree t = new Tree();
			t.createTree(result[i]);
			if (TreeIsBSTOperation.isBst(t)) {
				bstcount++;
			}
//			BTreePrinter.printNode(t.root);
		}
		return bstcount;
	}

}
