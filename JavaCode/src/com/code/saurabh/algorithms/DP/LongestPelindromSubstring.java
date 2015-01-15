package com.code.saurabh.algorithms.DP;

import com.code.saurabh.util.Pair;

/**
 * 
 * @author saurabh.gupta
 * <pre>
 * 1st and 2nd approach
 * Link: http://leetcode.com/2011/11/longest-palindromic-substring-part-i.html
 * 
 * 
 * O(N) solution (Manacher's Algorithm)
 * Link: http://leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
 * <Did not go thorugh code, just basic logic>
 * 
 * here exists a sixth solution to this problem - Using suffix trees.
 * However, it is not as efficient as this one (run time O(N log N) and more overhead for building suffix trees) 
 * </pre>
 */
public class LongestPelindromSubstring {

	/*
	 *  run time complexity of O(N2) and uses O(N2) space to store the table.
	 *  FIRST APPROACH
	 */
	static String longestPalindromeDP(String s) {
		int n = s.length();
		int longestBegin = 0;
		int maxLen = 1;
		boolean table[][] = new boolean [s.length()][s.length()];
		
		//Initialise table with false
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < s.length(); j++) {
				table[i][j] = false;
			}
		}

		//Size 1 string is always palindrom
		for (int i = 0; i < n; i++) {
			table[i][i] = true;
		}

		//Size 2 string, if palindrom
		for (int i = 0; i < n-1; i++) {
			if (s.charAt(i) == s.charAt(i+1)) {
				table[i][i+1] = true;
				longestBegin = i;
				maxLen = 2;
			}
		}

		//Now size 3 and more size string to check for palindrom
		for (int len = 3; len <= n; len++) {
			for (int i = 0; i < n-len+1; i++) {
				int j = i+len-1;
				if (s.charAt(i) == s.charAt(j) && table[i+1][j-1]) {
					table[i][j] = true;
					longestBegin = i;
					maxLen = len;
				}
			}
		}
		return s.substring(longestBegin, longestBegin + maxLen);
	}
	
	//Original, 
	//expandAroundCenter modified from this function
	static String expandAroundCenter_original(String s, int c1, int c2) {
		int l = c1, r = c2;
		int n = s.length();
		while (l >= 0 && r <= n-1 && s.charAt(l) == s.charAt(r)) {
			l--;
			r++;
		}
		return s.substring(l+1, (l+1) + (r-l-1));
	}

	static String expandAroundCenter(String s, int l, int r) {
		while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			l--;
			r++;
		}
		return s.substring(l+1, r);
	}

	/*
	 * O (N^2) TIME AND O(1) SPACE
	 * SECOND APPROACH
	 */		 
	static String longestPalindromeSimple(String s) {
		int n = s.length();
		if (n == 0) return "";
		String longest = s.substring(0, 1);  // a single char itself is a palindrome
		for (int i = 0; i < n-1; i++) {
			String p1 = expandAroundCenter(s, i, i);
			if (p1.length() > longest.length())
				longest = p1;

			String p2 = expandAroundCenter(s, i, i+1);
			if (p2.length() > longest.length())
				longest = p2;
		}
		return longest;
	}
		
		
	public static void main(String[] args) {
		String [] input = {
			"abacdgfdcaba",
			"caba",
			"forgeeksskeegfor",
			"abcd"
		};
		
		for (int i = 0; i < input.length; i++) {
			System.out.println("Input string: " + input[i]);
			System.out.println("Longest palindrom (1st Approach): " + longestPalindromeDP (input[i]));
			System.out.println("Longest palindrom (2nd Approach): " + longestPalindromeSimple (input[i]));
			System.out.println("Longest palindrom (3nd Approach): " + longestPalindromeSimple (input[i].toCharArray()));
			System.out.println();
		}
	}
	
	static String longestPalindromeSimple (char[] input) {
		String result = null;
		
		Pair<Integer, Integer> res = new Pair<Integer, Integer> (-1, 0);
		for (int i = 0; i <= input.length -1; i++) {
			Pair<Integer, Integer> f = find (input, i, i);
			Pair<Integer, Integer> s = find (input, i, i+1);
			if (f.getSecond() > res.getSecond()) {
				res = f;
			}
			if (s.getSecond() > res.getSecond()) {
				res = s;
			}
		}
		result = String.copyValueOf(input, res.getFirst(), res.getSecond());
		return result;
	}

	private static Pair<Integer, Integer> find(char[] input, int i, int j) {
		while (i >=0 && j < input.length && input[i] == input[j]) {
			i--;
			j++;
		}
		return new Pair<Integer, Integer> (i+1, j-1-i); //start index, size
	}

}
