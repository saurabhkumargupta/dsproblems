package com.code.saurabh.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Length of the largest subarray with contiguous elements
 * Given an array of distinct integers, find length of the longest subarray which contains numbers that can be arranged in a continuous sequence.

Examples:

Input:  arr[] = {10, 12, 11};
Output: Length of the longest contiguous subarray is 3

Input:  arr[] = {14, 12, 11, 20};
Output: Length of the longest contiguous subarray is 2

Input:  arr[] = {1, 56, 58, 57, 90, 92, 94, 93, 91, 45};
Output: Length of the longest contiguous subarray is 5

 * @author saurabh.gupta
 *
 */
public class LargestContinousSubarry {

	/*
	 * 1. Store all values in map
	 * 2. now, parse array and search for next continous element in hashMap, if it exist and keep on looking next until no next exist
	 * 3. at same time, for each key, update how many next elements including self exist in hash
	 * 4. if at some point, next exist in hash and its value is greater than 1, it means all its next has been parsed, return count
	 * 
	 */
	static void subArray (int [] input) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer> ();
		for (int i = 0; i < input.length; i++) {
			map.put (input[i], 1);
		}
		
		int max = 0;
		for (int i = 0; i < input.length; i++) {
			int count = getCount (map, input[i]);
			if (count > max) {
				max = count;
			}
		}
		System.out.println("Max: " + max);
	}

	static private int getCount(Map<Integer, Integer> map, int i) {
		if (map.get(i) > 1 || map.get(i+1) == null) {
			return map.get(i);
		}

		Integer cur = map.get(i);
		map.put(i, cur+getCount(map, i+1));
		return map.get(i);
	}
	public static void main(String[] args) {
		int [] [] input = {
				{8, 12, 10, 9, 11, 7},
				{1, 56, 58, 57, 90, 92, 94, 93, 91, 45},
				{10, 12, 11},
				{14, 12, 11, 20}
		};
		for (int i =0; i < input.length; i++) {
			subArray (input[i]);
		}
	}

}
