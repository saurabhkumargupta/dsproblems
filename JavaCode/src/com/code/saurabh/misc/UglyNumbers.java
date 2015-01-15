/**
 * http://www.geeksforgeeks.org/ugly-numbers/
 * Ugly numbers are numbers whose only prime factors are 2, 3 or 5. The sequence
 * 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15,
 * shows the first 11 ugly numbers. By convention, 1 is included.
 * Write a program to find and print the 150-th ugly number.
 */
package com.code.saurabh.misc;

import com.code.saurabh.util.UtilPart_2;

/**
 * @author saurabh.gupta
 *
 */
public class UglyNumbers {

	static int uglyNumbers (int N) {
		int [] ugly = new int[N];
		ugly[0] = 1;
		int index_of_next_multiple_of_2 = 0;
		int index_of_next_multiple_of_3 = 0;
		int index_of_next_multiple_of_5 = 0;
		
		int next_multiple_of_2 = ugly[0] * 2;
		int next_multiple_of_3 = ugly[0] * 3;
		int next_multiple_of_5 = ugly[0] * 5;
		
		for (int i = 1; i < N; i++) {
			ugly[i] = UtilPart_2.minOfThree(next_multiple_of_2, next_multiple_of_3, next_multiple_of_5);
			if (ugly[i] == next_multiple_of_2) {
				index_of_next_multiple_of_2++;
				next_multiple_of_2 = ugly[index_of_next_multiple_of_2] * 2;
			}
			if (ugly[i] == next_multiple_of_3) {
				index_of_next_multiple_of_3++;
				next_multiple_of_3 = ugly[index_of_next_multiple_of_3] * 3;
			}
			if (ugly[i] == next_multiple_of_5) {
				index_of_next_multiple_of_5++;
				next_multiple_of_5 = ugly[index_of_next_multiple_of_5] * 5;
			}
		}
		return ugly[N-1];
	}
	
	public static void main(String[] args) {
		for (int i = 150; i <= 150; i++) {
			System.out.print(i + "-th ugly number: " + uglyNumbers(i));
			System.out.println();
		}
	}

}
