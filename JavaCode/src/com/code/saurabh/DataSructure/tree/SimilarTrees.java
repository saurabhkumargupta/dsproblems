/**
 * 
 */
package com.code.saurabh.DataSructure.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author saurabh.gupta
 *
 */
public class SimilarTrees extends Tree {

	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		SimilarTrees tree = new SimilarTrees ();
		
		int [] [] inputs = {
				{10, 5, 3, 25},
				{10, 5, 3, 25, 15},
				{10, 5, 3, 25, 15, 30},
				{10, 5, 3, 25, 15, 30, 8},
				{10, 5, 3, 25, 15, 30, 8, 9},
		};

		for (int i = 0; i < inputs.length; i++) {
			runInAFunction(inputs[i]);
		}

	}

	static void runInAFunction (int [] input) {
		List<ArrayList<Integer>> allInput = getAllPerm (input);
		int[][] result = ListToArray(allInput);
//		ArrayUtils.printArray(result);
		System.out.println("Total similar tree: " + createMultipleTree (input, result));
	}
	/**
	 * <pre>
	 * </pre>
	 * @param input
	 * @param result
	 */
	private static int createMultipleTree(int[] input, int[][] result) {
		Tree original = new Tree ();
		original.createTree (input);
//		System.out.println("Original Tree");
//		ArrayUtils.printArray(input);
		int sameTree = 0;
		
		for (int i = 0; i < result.length; i++) {
			Tree newTree = new Tree ();
			newTree.createTree(result[i]);
			if (original.isMirror(newTree.root)) {
//				System.out.println("Same Tree");
				sameTree++;
//				ArrayUtils.printArray(result[i]);
			}
			else {
//				System.out.println("Different Tree");
//				ArrayUtils.printArray(result[i]);
			}
		}
		System.out.println();
		return sameTree;
	}

	/**
	 * <pre>
	 * </pre>
	 * @param allInput
	 */
	@Deprecated
	private static Integer [][] printList(List<ArrayList<Integer>> allInput) {
		Iterator<?> iter = allInput.iterator();
		Integer [][] twoDArray = new Integer [allInput.size()] [];
		int index = 0;
		while (iter.hasNext()) {
			ArrayList<Integer> localList = (ArrayList<Integer>) iter.next();
			Integer[] oneDArray = localList.toArray(new Integer[localList.size()]);
			twoDArray[index] = oneDArray;
			index++;
		}
		return twoDArray;
	}
	
	public static int [][] ListToArray(List<ArrayList<Integer>> allInput) {
		Iterator<?> iter = allInput.iterator();
		int [][] twoDArray = new int [allInput.size()] [];
		int index = 0;
		while (iter.hasNext()) {
			ArrayList<Integer> localList = (ArrayList<Integer>) iter.next();
			int[] oneDArray = org.apache.commons.lang3.ArrayUtils.toPrimitive(localList.toArray(new Integer[localList.size()]));
			twoDArray[index] = oneDArray;
			index++;
		}
		return twoDArray;
	}

	/**
	 * <pre>
	 * </pre>
	 * @param input
	 * @return
	 */
	public static List<ArrayList<Integer>> getAllPerm(int[] input) {
		List<ArrayList<Integer>> powerResult = new ArrayList<ArrayList<Integer>> ();

		ArrayList<Integer> tempResult = new ArrayList<Integer> ();
		tempResult.add(input[0]);
		powerResult.add(tempResult);
		
		for (int i = 1; i < input.length; i++) {
			int val = input[i];
			List<ArrayList<Integer>> toberemovedset = new ArrayList<ArrayList<Integer>> ();
			List<ArrayList<Integer>> tobeaddedset = new ArrayList<ArrayList<Integer>> ();
			
			Iterator<?> iter = powerResult.iterator();
			while (iter.hasNext()) {
				ArrayList<Integer> local = (ArrayList<Integer>) iter.next();
				toberemovedset.add(local);
				tobeaddedset.addAll(generateBigger (local, val));
			}
			powerResult.addAll(tobeaddedset);
			powerResult.removeAll(toberemovedset);
		}
		return powerResult;
	}

	/**
	 * <pre>
	 * </pre>
	 * @param local
	 * @param powerResult
	 */
	private static List<ArrayList<Integer>> generateBigger(ArrayList<Integer> local, int val) {
		List<ArrayList<Integer>> localPowerResult = new ArrayList<ArrayList<Integer>> ();
		for (int i = 0; i <= local.size(); i++) {
			ArrayList<Integer> temp = new ArrayList<Integer> ();
			temp.addAll(local);
			temp.add(i, val);
			localPowerResult.add(temp);
		}
		return localPowerResult;
	}

}
