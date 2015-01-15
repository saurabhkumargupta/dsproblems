/**
 * 
 */
package com.code.saurabh.algorithms.DP;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.code.saurabh.array.ArrayUtils;

/**
 * @author saurabh.gupta
 *
 */
public class SubsetSumProblem {
	static int count = 0;
	static List<LinkedList<Integer>> superset = new LinkedList<LinkedList<Integer>>  ();

	/**
	 * 
	 * <pre>
	 * For every element, either it will be added to the list or not, and will call recursively.
	 * It will be added to the list if it is satisfying condition of sum.
	 * 
	 * At each call, 
	 * 	if we are including current element, then current element is subtracted from required sum and index incremented. And current
	 * 	element is added to the potential list.
	 *
	 * 	if we are not including current element, then original required sum is passed to next call, but index incremented. and current
	 * 	element is not added to potential list.
	 * </pre>
	 * @param input
	 * @param list
	 * @param sum
	 * @param currentIndex
	 */
	public static void subsetSum (int[] input, LinkedList<Integer> list, int sum, int currentIndex) {
		count++;
		if (sum == 0) {
 			superset.add(list);
			return;
		}
		if (currentIndex >= input.length){
			return;
		}

		if (input[currentIndex] <= sum) {
			/* If current value is lesser than required sum then include this element into current subset
			 * */
			LinkedList <Integer> newList = new LinkedList<Integer> ();
			newList.addAll(list);
			newList.add(input[currentIndex]);
			subsetSum (input, newList, sum - input[currentIndex], currentIndex+1);
		}
		/* create a subset excluding this element from current subset
		 * */
		subsetSum (input, list, sum, currentIndex+1);
	}
	public static void main(String[] args) {
		int [][] input = {
				{4,6,8,1,3,11,9},
				{3, 34, 4, 12, 5, 2},
				{10, 7, 5, 18, 12, 20, 15},
		};
		
		int [] sum = {11, 9, 35};

		for (int i = 0; i < input.length; i++) {
			superset.clear();
			subsetsumtest (input[i], sum[i]);
			System.out.println();
			System.out.println();
		}
	}

	public static void subsetsumtest (int [] input, int sum) {
		System.out.println("sum: " + sum);
		System.out.print("Input Array : ");
		ArrayUtils.printArray(input);
		System.out.println("Result :");
		subsetSum (input, new LinkedList<Integer> (), sum, 0);
		printSuperset ();
	}
	/**
	 * <pre>
	 * </pre>
	 */
	private static void printSuperset() {
		Iterator <LinkedList<Integer>> outerIter = superset.iterator();
		while (outerIter.hasNext()) {
			LinkedList<Integer> innerList = outerIter.next();
			Iterator<Integer> innerIter = innerList.iterator();
			while (innerIter.hasNext()) {
				Integer v = innerIter.next();
				System.out.print(v + ", ");
			}
			System.out.println();
		}
		System.out.println("Count: " + count);
	}

}
