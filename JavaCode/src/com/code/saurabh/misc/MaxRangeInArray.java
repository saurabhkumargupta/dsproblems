/**
 * 
 */
package com.code.saurabh.misc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.code.saurabh.util.Pair;

/**
 * @author saurabh.gupta
 * 
 * In FlipKart 
 * Given an array of numbers, not sorted
 * WAF to return the length of the longest seq. of continuous numbers that you can form from it
 * Example:
 * 3,6,2,7,1,5,8,13,12,11
 * 
 * output is : 4 (5, 6, 7, 8)
 */
public class MaxRangeInArray {

	static int f (int [] input) {
		int max = 0;
		int maxKey = 0;
		Map<Integer, Pair<Integer, Integer>> runningMap = new HashMap<Integer, Pair<Integer, Integer>> ();
		for (int i = 0; i < input.length; i++) {
			Integer v = new Integer (input[i]);
			Pair<Integer, Integer> pair = new Pair<Integer, Integer> (v, v);
			runningMap.put(v, pair);
			int L = v - 1;
			int U = v + 1;
			int L_L = -1;
			int U_U = -1;
			if (runningMap.containsKey(L)) {
				L_L = runningMap.get(L).getFirst();
			}
			else
				L_L = v;
			if (runningMap.containsKey(U)) {
				U_U = runningMap.get(U).getSecond();
			}
			else
				U_U = v;
			pair = runningMap.get(L_L);
			pair.setSecond(U_U);

			pair = runningMap.get(U_U);
			pair.setFirst(L_L);
		}
		
		Set<Integer> keys = runningMap.keySet();	
		Iterator<Integer> iter = keys.iterator();
		while (iter.hasNext()) {
			Integer key = iter.next();
			int range = runningMap.get(key).getSecond() - runningMap.get(key).getFirst() + 1;
			if (max < range) {
				maxKey = key;
				max = range;
			}
		}
		
		printMap (runningMap);
		int lower = runningMap.get(maxKey).getFirst();
		for (int i = 0; i < max; i++) {
			System.out.print(lower + ", ");
			lower++;
		}
		return max;
	}
	
	static void printMap (Map<Integer, Pair<Integer, Integer>> runningMap) {
		Set<Integer> keys = runningMap.keySet();	
		Iterator<Integer> iter = keys.iterator();
		while (iter.hasNext()) {
			Integer key = iter.next();
			System.out.println ("Key: " + key + ", < " + runningMap.get(key).getFirst() + 
					"," + runningMap.get(key).getSecond()+ ">");
		}
	}
	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		int [] [] input = {
				{3, 6, 2, 7, 1, 5, 8, 13, 12, 11},
				{3, 6, 2, 7, 1, 5, 8, 13, 12, 11, 4},
				{3, 6, 2, 7, 1, 5, 8, 4},
		};
		for (int i = 0; i < input.length; i++) {
			System.out.print("Size: " + f (input[i]));
			System.out.println();
			System.out.println();
		}
	}

}
