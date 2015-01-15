package com.code.saurabh.algorithms.DP;

/**
 * 
 * @author saurabh.gupta
 *
 * http://www.geeksforgeeks.org/longest-common-substring/
 */
public class LongestCommonSubString {

	/*
	 * 	Time Complexity: O(m*n)
		Auxiliary Space: O(m*n)
	 */
	/* Returns length of longest common substring of X[0..m-1] and Y[0..n-1] */
	static int LCSubStr(String X, String Y)
	{
		int m = X.length();
		int n = Y.length();
		// Create a table to store lengths of longest common suffixes of
		// substrings.   Notethat LCSuff[i][j] contains length of longest
		// common suffix of X[0..i-1] and Y[0..j-1]. The first row and
		// first column entries have no logical meaning, they are used only
		// for simplicity of program
		int LCSuff[][] = new int [m+1][n+1];
		int result = 0;  // To store length of the longest common substring

		/* Following steps build LCSuff[m+1][n+1] in bottom up fashion. */
		for (int i=0; i<=m; i++)
		{
			for (int j=0; j<=n; j++)
			{
				if (i == 0 || j == 0)
					LCSuff[i][j] = 0;

				else if (X.charAt(i-1) == Y.charAt(j-1))
				{
					LCSuff[i][j] = LCSuff[i-1][j-1] + 1;
					result = Math.max(result, LCSuff[i][j]);
				}
				else LCSuff[i][j] = 0;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String [] [] input = {
				{"OldSite:GeeksforGeeks.org", "NewSite:GeeksQuiz.com"}	
		};
		
		for (int i = 0; i < input.length; i++) {
			System.out.println("Input: " + input[i][0] + ", " + input[i][1]);
			System.out.println("LCS: " + LCSubStr (input[i][0], input[i][1]));
		}
	}

}
