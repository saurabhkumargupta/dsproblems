/**
 * 
 */
package com.code.saurabh.strings;

import com.code.saurabh.array.ArrayUtils;

/**
 * @author saurabh.gupta
 * http://leetcode.com/2010/11/finding-minimum-window-in-s-which.html
 * Given a set T of characters and a string S, find the minimum window in S which will contain all the characters in T in complexity O(n).

	eg,
		S = "ADOBECODEBANC"
		T = "ABC"

	Minimum window is "BANC".
	
	1. maintaining two arrays: 
		i.  needToFind : stores required number of characters from T
		ii. hasFound: stored, found many characters of T has been found till now.
	2. min: stores current min
	3. start, end maintaining range of S which stored current min (end-start denotes min window)
	4. i, j (j - i) is current window
	
	1. Each character in S is looked into needToFind array, if this is not required then skip this element, otherwisr
		we take this character, increment hasFound[char] count.
	2. After incrementing we check, we this new character is excessive character than required, if yes then we are 
		changing current window's start pointer, otherwise if new character is not excessive it means it is required
		for T. we increment totalCount of characters.
	3. Whenever we have found all required character, then we start looking for min window.
		Current window is compared with min window. and update accordingly.
 *
 */
public class MinSubstringWindow {
	
	static void finMinWindow (String S, String T) {
		int[] needToFind = new int[256];
		ArrayUtils.initArray(needToFind);
		
		for (int i = 0; i < T.length(); i++) {
			needToFind[T.charAt(i)] ++;
		}

		int[] hasFound = new int[256];
		ArrayUtils.initArray(hasFound);
		
		int start = 0;
		int end = 0;
		int count = 0;
		int min = S.length() + 1;
		for (int j = 0, i = 0; j < S.length(); j++) {
			char X = S.charAt(j);

			//skipping not required characters.
			if (needToFind[X] == 0) {
				continue;
			}

			//This is required characters. update it.
			hasFound[X]++;

			if (hasFound[X] > needToFind[X]) {
				i = updateStartIndex (i, needToFind, hasFound, S);
			}
			else {
				count++;
				end = j;
			}
			
			//== is sufficient
//			if (count >= T.length()) {
			if (count == T.length()) {
				//find min window
				if (min > (j-i+1)) {
					min = j-i+1;
					start = i;
					end = j;
				}
			}
		}
		if (count != T.length()) {
			System.out.println("No result available");
		}
		else
			System.out.println("Size: " + min + ", range: " + S.substring(start, end+1));
	}

	private static int updateStartIndex(int startIndex, int[] needToFind, int[] hasFound, String s) {
		char startChar = s.charAt(startIndex);
		while (needToFind[startChar] == 0 
				|| needToFind[startChar] < hasFound[startChar]) {
			if (needToFind[startChar] < hasFound[startChar]) {
				hasFound[startChar]--;
			}
			startIndex++;
			startChar = s.charAt(startIndex);
		}
		return startIndex;
	}

	public static void main(String[] args) {
		finMinWindow ("ADOBECODEBANC", "ABC");
		finMinWindow ("acbbaca", "aba");
		finMinWindow ("xacbbaca", "aba");
		finMinWindow ("coobdafceeaxab", "abc");
		finMinWindow ("cobdafceeaxab", "abc");
		finMinWindow ("cobdafceeaxab", "abcy");
		finMinWindow("thisisateststring", "tist");
		finMinWindow("abcdefshuhijamklhukmshnub", "shubham");
	}

}
