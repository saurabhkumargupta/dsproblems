package com.hackerrank.problems;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RemoveSubstring {

	int getMaxRounds (String input, String subString) {
		if (input.length() <= 0 || subString.length() <= 0) {
			return 0;
		}
		int max = 0;
		Set<Integer> indexes = getAllIndex (input, subString);
		if (indexes.size() > 0) {
			Iterator<Integer> iter = indexes.iterator();
			while (iter.hasNext()) {
				Integer index = iter.next();
				String newString = getNewString (input, subString, index.intValue());
				int count = getMaxRounds (newString, subString);
				if (count > max) {
					max = count;
				}
			}
			//one level recursion
			max += 1;
		}
		return max;
	}

	private String getNewString(String input, String subString, int intValue) {
		StringBuilder sb = new StringBuilder ();
		sb.append(input.substring(0, intValue));
		sb.append(input.substring(intValue + subString.length()));
		return sb.toString();
	}

	private Set<Integer> getAllIndex(String input, String subString) {
		Set<Integer> points = new HashSet<Integer> ();
		int i = 0;
		while (true) {
			i = input.indexOf(subString, i);
			if (i >= 0)
				points.add(i);
			else
				break;
			i = i + 1;
		}
		return points;
	}
	public static void main(String[] args) {

		RemoveSubstring test = new RemoveSubstring ();
		//test.localTesting ();
		test.multipleRun ();
	}

	private void multipleRun() {
		System.out.println(getMaxRounds("aababa", "aba"));
		System.out.println(getMaxRounds("aabb", "ab"));
		System.out.println(getMaxRounds("aabcbdc", "abc"));
		System.out.println(getMaxRounds("aabbdc", "abc"));
		System.out.println(getMaxRounds("", "abc"));
		System.out.println(getMaxRounds("aabcbdc", ""));
		System.out.println(getMaxRounds("", ""));
		
		/*Testcases from rajat*/
		System.out.println(getMaxRounds("bababb", "bab"));
		System.out.println(getMaxRounds("bbbbbb", "b"));
		System.out.println(getMaxRounds("Bbbbbb", "b"));
		
	}

	@SuppressWarnings("unused")
	private void localTesting() {
		System.out.println(": " + getNewString("aababa", "aba", 1));
		System.out.println(": " + getNewString("aababa", "aba", 3));
		System.out.println(": " + getNewString("ababa", "aba", 0));
		
		Set<Integer> result = getAllIndex("aababa", "aba");
		Iterator<Integer> iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(":" + iter.next().intValue());
		}
	}

}
