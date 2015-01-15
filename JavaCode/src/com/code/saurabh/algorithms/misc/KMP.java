package com.code.saurabh.algorithms.misc;

import java.util.HashSet;
import java.util.Set;

public class KMP {

	public static int[] getPatternArray (String pattern) {
		int [] patternArray = new int[pattern.length()];
		int matchindex = 0;
		
		for (int i = 1; i < pattern.length(); i++) {
			if (pattern.charAt(i) == pattern.charAt(matchindex)) {
				matchindex += 1;
				patternArray[i] = matchindex;
			}
			else {
				matchindex = 0;
			}
		}

		System.out.println("Pattern Array for Pattern: " + pattern);
		for (int i = 0; i < patternArray.length; i++) {
			System.out.print(patternArray[i] + ", ");
		}
		System.out.println();
		return patternArray;
	}
	
	public static Integer[] getAllIndexKMPAlgorithm (String text, String pattern) {
		
		if (pattern.length() == 0 || text.length() == 0) {
			return new Integer[0];
		}
			int [] patternArray = getPatternArray (pattern);
			int patternIndex = 0;
			Set<Integer> indexes = new HashSet <Integer> ();
			int i = 0;

			for (i = 0; i < text.length();) {
				
				// If current pattern index matches then increment pattern and text index
				// Also check, if complete patter is matched, if yes then add that index
				// Reset pattern Index to the point till it has already matched as pattern is repeated in pattern
				if (text.charAt(i) == pattern.charAt(patternIndex)) {
					patternIndex++;
					i++;
					if (pattern.length() == patternIndex) {
						indexes.add(i - patternIndex);
						patternIndex = patternArray[patternIndex-1];
					}
				}
				//If pattern does not match then move back till pattern has already match as subpattern until either 
				//it starts matching with text or its values comes to 0.
				else {
					if (patternIndex == 0) {
						i++;
					}
					else {
						patternIndex = patternArray[patternIndex-1];
					}
				}
			}

			return indexes.toArray(new Integer[0]);
	}

	public static Integer[] getAllIndexBruteForce (String text, String pattern) {
		Set<Integer> indexes = new HashSet<Integer> ();
		for (int i = 0; i < text.length(); i++) {
			int idx = text.indexOf(pattern, i);
			if (idx >= 0) {
				indexes.add(idx);
			}
			else {
				break;
			}
		}
		return indexes.toArray(new Integer[0]);
	}

	public static void main(String[] args) {
		String [][] input = {
				{"AAABAAABBAAABAAABA", "AAABAAABA"},
				{"CAAABAAABBAAABAAAB", "AAABAAABA"},
				{"aababa", "aba"},
				{"aabb", "ab"},
				{"aabcbdc", "abc"},
				{"aabbdc", "abc"},
				{"", "abc"},
				{"aabcbdc", ""},
				{"", ""},
				{"bababb", "bab"},
				{"bbbbbb", "b"},
				{"Bbbbbb", "b"},
		};
			for (int i = 0; i < input.length; i++) {
				Integer [] result = getAllIndexBruteForce (input[i][0], input[i][1]);
				System.out.println("BruteForce");
				printResult (result);
				
				Integer[] resultKMP = getAllIndexKMPAlgorithm (input[i][0], input[i][1]);
				System.out.println("KMP");
				printResult (resultKMP);

				System.out.println();
			}
			
			//getPatternArray ("AAABAAABA");
	}

	private static void printResult(Integer[] result) {
		if (result.length <= 0) {
			System.out.print("-1");
			return;
		}
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + ", ");
		}
		System.out.println();
	}

}
