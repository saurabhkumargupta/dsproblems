/**
 * 
 */
package com.code.saurabh.algorithms.DP;

import com.code.saurabh.array.ArrayUtils;

/**
 * @author saurabh.gupta
 * <pre>
 *  http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 *  
 *	1) Consider the input strings "AGGTAB" and "GXTXAYB". Last characters match for the strings. So length of LCS can be written as:
	L("AGGTAB", "GXTXAYB") = 1 + L("AGGTA", "GXTXAY")

	2) Consider the input strings "ABCDGH" and "AEDFHR". Last characters do not match for the strings. So length of LCS can be written as:
	L("ABCDGH", "AEDFHR") = MAX ( L("ABCDG", "AEDFHR"), L("ABCDGH", "AEDFH") )
	
	If last characters of both sequences match (or X[m-1] == Y[n-1]) then
		L(X[0..m-1], Y[0..n-1]) = 1 + L(X[0..m-2], Y[0..n-2])

	If last characters of both sequences do not match (or X[m-1] != Y[n-1]) then
		L(X[0..m-1], Y[0..n-1]) = MAX ( L(X[0..m-2], Y[0..n-1]), L(X[0..m-1], Y[0..n-2])

 * </pre>
 */
public class LargestCommonSubsequence {

	static int LCS (String a, String b) {
		if (a.length() == 0
				|| b.length() == 0) {
			return 0;
		}
		if (a.charAt(a.length()-1) == b.charAt(b.length()-1)) {
			return 1 + LCS (a.substring(0, a.length()-1),
					b.substring(0, b.length()-1));
		}
		else {
			return Math.max(LCS (a.substring(0, a.length()-1), b.substring(0, b.length()))
					, LCS (a.substring(0, a.length()), b.substring(0, b.length()-1)));
		}
	}
	
	/* Returns length of LCS for X[0..m-1], Y[0..n-1] */
	static int lcs_DP( String X, String Y)
	{
		int m = X.length();
		int n = Y.length();
		int L[][] = new int [m+1][n+1];
		int i, j;

		/* Following steps build L[m+1][n+1] in bottom up fashion. Note 
	      that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
		for (i=0; i<=m; i++)
		{
			for (j=0; j<=n; j++)
			{
				if (i == 0 || j == 0)
					L[i][j] = 0;

				else if (X.charAt(i-1) == Y.charAt(j-1))
					L[i][j] = L[i-1][j-1] + 1;

				else
					L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
			}
		}

//		ArrayUtils.printArray(L);
		/* L[m][n] contains length of LCS for X[0..n-1] and Y[0..m-1] */
		return L[m][n];
	}
	
	/* Returns length of LCS for X[0..m-1], Y[0..n-1] 
	 * 
	 * In this variation, we need to do special handling in case of 0th index
	 * but in case (as done in lcs_DP above) we take 1 extra size of matrix, then it is done very gracefully
	 * 
	 * 
	 * */
	static int lcs_DP_mine( String X, String Y)
	{
		int m = X.length();
		int n = Y.length();
		int L[][] = new int [m][n];
		int i, j;

		ArrayUtils.initArray(L);
		/* Following steps build L[m+1][n+1] in bottom up fashion. Note 
	      that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
		for (i=0; i<m; i++)
		{
			for (j=0; j<n; j++)
			{
				if (i == 0 || j == 0) {
					if (X.charAt(i) == Y.charAt(j)) {
						L[i][j] = 1;
					}
					else {
						L[i][j] = Math.max(L[0][j], L[i][0]);
					}
				}

				else if (X.charAt(i) == Y.charAt(j))
					L[i][j] = L[i-1][j-1] + 1;

				else
					L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
			}
		}

//		ArrayUtils.printArray(L);
		/* L[m][n] contains length of LCS for X[0..n-1] and Y[0..m-1] */
		return L[m-1][n-1];
	}

	public static void main(String[] args) {
		String [] [] input = 
			{
				{"AGGTAB","GXTXAYB"}, // GTAB
				{"ABCDGH", "AEDFHR"}, // ADH
				{"AB", "AB"}
			};
	
		for (int i = 0; i < input.length; i++) {
			System.out.println("Input: " + input[i][0] + ", " + input[i][1]);
			System.out.println("recursion: " + LCS (input[i][0], input[i][1]));
			System.out.println("DP: " + lcs_DP (input[i][0], input[i][1]));
			System.out.println("DP_Mine: " + lcs_DP_mine (input[i][0], input[i][1]));
			System.out.println();
		}

	}
}
