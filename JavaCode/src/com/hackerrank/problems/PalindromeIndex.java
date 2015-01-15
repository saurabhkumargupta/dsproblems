/**
 * 
 */
package com.hackerrank.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author saurabh.gupta
 *
 */
public class PalindromeIndex {

	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		try {
			int testcasecount = Integer.valueOf(br.readLine().trim()).intValue();
			for (int i = 0 ; i < testcasecount; i++) {
				String nextString = br.readLine().trim();
				checkPalindromString (nextString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void checkPalindromString (String nextString) {
		int startIndex = 0;
		int endIndex = nextString.length()-1;
		boolean gotit = false;
		int doubt  = -1;
		int startdoubt = -1;
		int enddoubt = -1;
		while (startIndex < endIndex) {
			if (nextString.charAt(startIndex) == nextString.charAt(endIndex)) {
				startIndex++;
				endIndex--;
			}
			else {
				if (gotit == false) {
					gotit = true;
					doubt = startIndex;
					enddoubt = endIndex;
					startIndex++;
//					if (nextString.charAt(startIndex+1) == nextString.charAt(endIndex)) {
//						System.out.println(startIndex);
//						return;
//					}
//					else if (nextString.charAt(startIndex) == nextString.charAt(endIndex-1)) {
//						System.out.println(endIndex);
//						return;
//					}
				}
				else {
					doubt = enddoubt;
					break;
				}
			}
		}
		System.out.println(doubt);
	}
}
