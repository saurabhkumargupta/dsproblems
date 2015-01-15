/**
 * find all subsets of a given array whose sum is equal to given sum
 * and print the subsets also
 */
package com.code.saurabh.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author saurabh.gupta
 *
 */
public class SubSetSumProblem {

	static Map<HashSet<Integer>, Integer> resultSet = new HashMap<HashSet<Integer>, Integer> ();
	static void makeAllSubset (int[] A, int S) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] <= S) {
				updateResultSet (A[i], S);
			}
		}
	}
	/**
	 * <pre>
	 * </pre>
	 * @param i
	 * @param s
	 */
	private static void updateResultSet(int key, int S) {
		Map<HashSet<Integer>, Integer> tempMap = new HashMap<HashSet<Integer>, Integer> ();
		HashSet<Integer> singleKey = new HashSet<Integer> ();
		singleKey.add(key);
		tempMap.put(singleKey, key);
		
		Set<Map.Entry<HashSet<Integer>, Integer>> entryset = resultSet.entrySet();
		Iterator<?> iter = entryset.iterator();
		while (iter.hasNext()) {
			Map.Entry<HashSet<Integer>, Integer> keyval = (Map.Entry<HashSet<Integer>, Integer>) iter.next();
			HashSet<Integer> tempset = new HashSet<Integer> ();
			HashSet<Integer> keys = keyval.getKey();
			Integer val = keyval.getValue();
			if (val + key <= S) {
				tempset.addAll(keys);
				tempset.add(key);
				tempMap.put(tempset, val + key);
			}
		}
		resultSet.putAll(tempMap);
	}

	static void printResultSet (int S) {
		Set<Map.Entry<HashSet<Integer>, Integer>> entryset = resultSet.entrySet();
		Iterator<?> iter = entryset.iterator();
		while (iter.hasNext()) {
			Map.Entry<HashSet<Integer>, Integer> keyval = (Map.Entry<HashSet<Integer>, Integer>) iter.next();
			HashSet<Integer> keys = keyval.getKey();
			Integer val = keyval.getValue();
			if (val == S) {
				printSet (keys);
			}
		}
	}
	/**
	 * <pre>
	 * </pre>
	 * @param keys
	 */
	private static void printSet(HashSet<Integer> keys) {
		Iterator<Integer> iter = keys.iterator();
		while (iter.hasNext()) {
			System.out.print(iter.next() + ", ");
		}
		System.out.println ();
	}
	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		int Sum = 10;
		int[] input = {11, 5 ,8, 10, 1, 3 ,19, 2, 7};
		makeAllSubset (input, Sum);
		printResultSet (Sum);
	}

}
