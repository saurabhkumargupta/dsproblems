package com.code.saurabh.array;

import java.util.HashMap;
import java.util.Map;

import com.code.saurabh.util.Pair;

public class StartHashSubSequence {

	private class Alpha {
		char alpha;
		int count;
		int s_index;
		
		Alpha (char c) {
			this.alpha = c;
			this.count = 0;
			this.s_index = -1;
		}
		
		public int getIndex () {
			return s_index;
		}
		
		public int getCount () {
			return count;
		}
		
		boolean equal (char c) {
			return this.alpha == c;
		}
		
		void incr () {
			this.count++;
		}
		
		void setIndex (int i) {
			this.s_index = i;
		}
		
		void reset () {
			this.s_index = -1;
			this.count = 0;
		}
	}
	
	public void parseArray (char [] array) {
		Alpha star = new Alpha ('*');
		Alpha hash = new Alpha ('#');
		Pair<Integer, Integer> max = new Pair <Integer, Integer> (0,-1);
		
		if (array.length <= 0) {
			return;
		}
		
		int i = 0;
		if (star.equal(array[0])) {
			star.setIndex(0);
			while (i < array.length && star.equal(array[i])) {
				star.incr();
				i++;
			}
		}
		else if (hash.equal(array[0])) {
			hash.setIndex(0);
			while (i < array.length && hash.equal(array[i])) {
				hash.incr();
				i++;
			}
		}
		
		for (; i < array.length;) {
			if (star.equal(array[i])) {
				star.reset();
				star.setIndex(i);
				while (i < array.length && star.equal(array[i])) {
					star.incr();
					i++;
				}
			}
			else if (hash.equal(array[i])) {
				hash.reset();
				hash.setIndex(i);
				while (i < array.length && hash.equal(array[i])) {
					hash.incr();
					i++;
				}
			}
			
			Pair<Integer, Integer> ret = getMaxArray (star, hash);
			if (max.getFirst() < ret.getFirst()) {
				max.assign (ret);
			}
		}
		
		if (max.getSecond() != -1) {
			System.out.println ("Start Index: " + max.getSecond() + ", Size: " + max.getFirst());
			for (int j = max.getSecond(); j < max.getSecond() + max.getFirst(); j++) {
				System.out.print (array[j]);
			}
		}
	}

	private static Pair<Integer, Integer> getMaxArray(Alpha star, Alpha hash) {
		int size = 0;
		int s_index = -1;
		if (star.getCount() < hash.getCount()) {
			size = star.getCount() * 2;
			if (star.getIndex() < hash.getIndex()) {
				s_index = star.getIndex();
			}
			else {
				s_index = hash.getIndex() + (hash.getCount() - star.getCount());
			}
		}
		else {
			size = hash.getCount() * 2;
			if (hash.getIndex() < star.getIndex()) {
				s_index = hash.getIndex();
			}
			else
			{
				s_index = star.getIndex() + (star.getCount() - hash.getCount());
			}
		}
		return new Pair<Integer, Integer> (size, s_index);
	}

	/**
	 * <pre>
	 * 1) Convert given string into a Array of integer when * is replaced by -1 and # is replaced by +1
	 * 2) Then this array is updated : arr[i] = arr[i] + arr[i-1]. Means each element represent the sum of array till that index
	 * 3) Parse this array, and create a map of {arraySum, index}
	 * So when arraySum appears again in the array it means there is subSequence, here check if this is the largest subsequence 
	 * or not.
	 * 
	 *  sum ZERO is handled specially as 0 is also part of sum and at very starting of array, sum is 0
	 *  </pre>
	 * @param str
	 */
	private static void printMaxSubSequence (String str) {
		int[] arr = new int[str.length()];
		int i = 0;
		
		for (i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '*')
				arr[i] = -1;
			else
				arr[i] = 1;
		}

		for (i = 1; i < arr.length; i++) {
			arr[i] = arr[i] + arr[i-1];
		}
		
		Map<Integer, Integer> map = new HashMap <Integer, Integer> ();
		map.put(0, -1);
		int maxLen = 1;
		int sIndex = 0;
		int eIndex = 0;
		for (i = 0; i < arr.length; i++) {
			Integer t = map.get(arr[i]);
			if (t != null) {
				if (i -t > maxLen) {
					maxLen = i - t;
					sIndex = t + 1;
					eIndex = i;
				}
			}
			else
				map.put(arr[i], i);
		}

		System.out.println("Max SubSequence");
		ArrayUtils.printArray(str, sIndex, eIndex);
	}
	public static void main(String[] args) {
		//char [] array = {'*', '#', '#', '*', '*', '*', '#', '#', '#'};
		//char [] array = {'*','#','*','#','*','#','#','#','*','*','*','*'};
		//char [] array;
		//String input = "*#*#*###****";
		//char [] array = {'*', '#', '*', '*', '*', '#', '#', '#'};
		char [] array = {'*', '#', '*', '#', '*', '#', '*', '#'};
//		array = input.toCharArray();
		StartHashSubSequence pa = new StartHashSubSequence ();
		pa.parseArray (array);
		
		String [] input = {
			"*##***###",
			"*#*#*#*#",
			"*#**###*",
			"**###*"
		};
		
		System.out.println();
		for (int i = 0; i < input.length; i++) {
			printMaxSubSequence (input[i]);
		}
	}

}
