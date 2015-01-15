/**
 * 
 */
package com.code.saurabh.algorithms.misc;

/**
 * @author saurabh.gupta
 *
 */
public class RabinKarp {

	static void RabinKarpAlgo (String input, String pattern) {
		int IL = input.length();
		int PL = pattern.length();

		if (IL < PL) {
			return;
		}
		
		RollingHash rhp = new RollingHash (PL, 1007L, 257);
		RollingHash rht = new RollingHash (PL, 1007L, 257);
		long hp = rhp.createHash(pattern);
		long ht = rht.createHash(input.substring(0, PL));
		if (hp == ht) {
			/* There are chances that these two substring are equal
			 * but its not necessary. So all characters need to be compared
			 * We are using String library function here but it will take O ( M ) time, M is size of pattern
			 */
			if (pattern.equals(input.subSequence(0, PL))) {
				System.out.println("Pattern Matched at index: " + 0);
			}
		}
		
		for (int i = 1; i <= (IL-PL); i++) {
			ht = rht.update(input.charAt(i-1), input.charAt(i+PL-1));
			if (hp == ht) {
				/* There are chances that these two substring are equal
				 * but its not necessary. So all characters need to be compared
				 * We are using String library function here but it will take O ( M ) time, M is size of pattern
				 */
				if (pattern.equals(input.subSequence(i, i+PL))) {
					System.out.println("Pattern Matched at index: " + i);
				}
			}
		}
	}
	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		String [][] input = {
				{"zabcdedfgh", "cde"},
				{"abcdedfgh", "cde"},
				{"abcdabcd", "abcd"},
				{"123456", "345"},
				{"123456", "123"},
				{"123123", "123"},
				{"456789123123456789678234456789567456789", "456789"},
				{"3249753424234837", "53424"},
		};
		
		for (int i = 0; i < input.length; i++) {
			System.out.println ("Input: " + input[i][0] + ", Pattern: " + input[i][1]);
			RabinKarpAlgo (input[i][0], input[i][1]);
			System.out.println ();
		}
	}

}
