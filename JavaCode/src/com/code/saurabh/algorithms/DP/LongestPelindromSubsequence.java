package com.code.saurabh.algorithms.DP;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.code.saurabh.array.ArrayUtils;

/**
 * <pre>
 * @author saurabh.gupta
 * Subsequence
 * http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
 * 
 * Substring
 * http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
 * </pre>
 */
public class LongestPelindromSubsequence {

	static int max (int x, int y) { return (x > y)? x : y; }

	// Returns the length of the longest palindromic subsequence in seq
	static int lps(String seq, int i, int j)
	{
	   // Base Case 1: If there is only 1 character
	   if (i == j)
	     return 1;
	 
	   // Base Case 2: If there are only 2 characters and both are same
	   if (seq.charAt(i) == seq.charAt(j) && i + 1 == j)
	     return 2;
	 
	   // If the first and last characters match
	   if (seq.charAt(i) == seq.charAt(j))
	      return lps (seq, i+1, j-1) + 2;
	 
	   // If the first and last characters do not match
	   return max( lps(seq, i, j-1), lps(seq, i+1, j) );
	}
	
	static int lps_dp (String seq) {
		//table to store subproblems
		int [][] la = new int [seq.length()] [seq.length()];
		ArrayUtils.initArray(la, 0);
		
		//Array of size 1 is always pelindrom
		//base case
		for (int i = 0; i < la.length; i++) {
			la[i][i] = 1;
		}
		
		//Now, we are finding pelindrom in sub-array of size 2, then pelindrom in sub-array of size 3 and upto size n-1
		//Each time when sub-array size is incrementing, we are using sub-array of one size less to find out current sub-array
		// finding pelindrom in subarray of size 3, we will use subarray of size 2
		
		int len = seq.length();
		//pelindrom in subarray of size i can be find our using subarray of size i-1
		//start sub-array size with 2, and go upto complete array size
		for (int size = 2; size <= len; size++) {
			
			//start index of subarray
			for (int start = 0; start < len-size+1; start++) {
				
//				end index of subarray
				int end = start + size -1;
				if (seq.charAt(start) == seq.charAt(end)
						&& size == 2) {
					//for size 2, there will not be inbetween subarray
					// as both corners are equal, we will be move both corners and trying to find out in-between subarray which
					//does not exist. that is why this special case
					la[start][end] = 2;
				}
				else if (seq.charAt(start) == seq.charAt(end)) {
					//if corners of subarray is equal,
					//then size of pelindrom in this subarray array [start..end] is 
					// size of pelindrom in subarray [start+1 .. end-1] + 2
					// all subarray of size smaller than current subarray size is already calculated and stored in lookup array
					// we can use that stored value and update current subarray's longest pelindrom
					la[start][end] = la[start+1][end-1] + 2;
				}
				else {
					la[start][end] = max (la[start+1][end], la[start][end-1]);
				}
			}
		}
		
		printSequence (la, seq);
		return la[0][len-1];
	}
	
	/*
	 * Printing sequence:
	 * Using lookup table and sequence
	 * Check if corners of current i, j are equal, if so then this i, j are required indexes, store them
	 * else check from which location this value has been reached.
	 * Choosing next value in updating LA was choosing which one is maximum, here we are checking which index should be updated to
	 * reach corners which are equal
	 * 
	 * for "BBABCBCAB" this will be lookup tree
	 * 
	 *  1 2 2 3 3 5 5 5 7
	 *  0 1 1 3 3 3 3 5 7
	 *  0 0 1 1 1 3 3 5 5
	 *  0 0 0 1 1 3 3 3 5
	 *  0 0 0 0 1 1 3 3 3
	 *  0 0 0 0 0 1 1 1 3
	 *  0 0 0 0 0 0 1 1 1
	 *  0 0 0 0 0 0 0 1 1
	 *  0 0 0 0 0 0 0 0 1
	 * 
	 *  starting from (i, j)  (start: 0, 8) we move backward. 
	 *  if seq[i] seq[j] is equal then are we moving in table in diagonal, means i++ and j--
	 *  else we check which was max corners (out of {i++,j} or {i, j--} ) which was used to update i,j
	 *   
	 */
	static void printSequence (int[][] table, String seq) {
		boolean finish = false;
		List<Integer> startList = new ArrayList<Integer> ();
		List<Integer> endList = new ArrayList<Integer> ();

		boolean odd = (table[0][seq.length()-1] % 2 == 0) ? false: true;

		for (int i = 0; i < seq.length();) {
			for (int j = seq.length()-1; j>=0 && j >= i;) {
				if (seq.charAt(i) == seq.charAt(j)) {
					
					//need to check if we are in middle
					if (i == j) {
						//check whether this middle should be used or not, in case of even result it should not
						//like for string XABBAY
						if (odd)
							startList.add(i);
						finish = true;
						break;
					}
					startList.add(i);
					endList.add(j);
					i++;
					j--;
				}
				else {
					if (table[i][j] == table [i][j-1]) {
						j--;
					}
					else {
						i++;
					}
				}
			}
			if (finish) {
				break;
			}
		}

		System.out.println(startList.toString());
		System.out.println(endList.toString());
		Iterator<Integer> iter = startList.iterator();
		while (iter.hasNext()) {
			Integer index = iter.next();
			System.out.print(seq.charAt(index));
		}

		//index is lastindex+1, as previous call return value with index-1
		ListIterator <Integer> endIter = endList.listIterator(endList.size());
		while (endIter.previousIndex() != -1) {
			Integer index = endIter.previous();
			System.out.print(seq.charAt(index));
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String [] input = {
			"GEEKSFORGEEKS",//EEKEE
			"BBABCBCAB",//BABCBAB
			"XABBAY",
			"XAYZBBAT",
		};

		for (int i = 0; i < input.length; i++) {
			System.out.println("Input String: " + input [i]);
			System.out.println("The length of the LPS is " + lps(input[i], 0, input[i].length() - 1));
		    System.out.println("The length of the LPS_DP is " + lps_dp(input[i]));
		    System.out.println();
		}
	    
	}

}
