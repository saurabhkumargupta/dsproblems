/**
 * 
 */
package com.code.saurabh.array.misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * http://www.careercup.com/question?id=5761544004567040
 * Given an arraylist of N integers, 
	(1) find a non-empty subset whose sum is a multiple of N. 
	(2) find a non-empty subset whose sum is a multiple of 2N. 
		Compare the solutions of the two questions.

COpied Solution
 * @author saurabh.gupta
 *
 */
public class SubsetSumOfMultipleProblem {

	static List<Integer> findSubset(int[] numbers, int M) {
		int N = numbers.length;
		HashMap<Integer, LinkedList<Integer>> subsets = new HashMap<Integer, LinkedList<Integer>>();
		subsets.put(0, new LinkedList<Integer>());
		for(int n : numbers) {
			HashSet<Integer> keys = new HashSet<Integer>(subsets.keySet());
			for(int i : keys) {
				int sum = (i+n) % M;
				if(sum == 0) {
					subsets.get(i).add(n);
					return subsets.get(i);
				}
				if(subsets.containsKey(sum))
					continue;
				LinkedList<Integer> list = (LinkedList<Integer>)subsets.get(i).clone();
				list.add(n);
				subsets.put(sum, list);
			}
		}
		return null;
	}

	public static void main(String[] args) {
//		int len = args.length;
//		int[] numbers = new int[len];
//		for(int i=0; i<len; i++) {
//			numbers[i] = Integer.parseInt(args[i]);
//		}
		int [] numbers = {7,4,2,11};
		List<Integer> subset = findSubset(numbers, 12);
		if(subset == null) {
			System.out.println("None found");
		} else {
			for(int n : subset)
				System.out.print(n + " ");
		}
	}

}
