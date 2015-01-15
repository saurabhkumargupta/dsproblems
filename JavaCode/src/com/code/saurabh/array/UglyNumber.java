/**
 * 
 */
package com.code.saurabh.array;

import java.util.Arrays;

/**
 * @author saurabh.gupta
 *
 */
public class UglyNumber {

	static void uglyNumber (int n) {
		if (n == 1) {
			System.out.println("1");
		}
		
		int [] ugly = new int [n];
		Arrays.fill(ugly, 0);
		ugly[0] = 1;
		
		int next_multiple_2 = 2;
		int next_multiple_3 = 3;
		int next_multiple_5 = 5;
		
		int next_index_2 = 0;
		int next_index_3 = 0;
		int next_index_5 = 0;
		
		for (int i = 1; i < n; i++) {
			int next_ugly = Math.min(next_multiple_2, Math.min(next_multiple_3, next_multiple_5));
			ugly[i] = next_ugly;

			if (next_ugly == next_multiple_2) {
				next_index_2++;
				next_multiple_2 = ugly[next_index_2] * 2;
			}
			if (next_ugly == next_multiple_3) {
				next_index_3++;
				next_multiple_3 = ugly[next_index_3] * 3;
			}
			if (next_ugly == next_multiple_5) {
				next_index_5++;
				next_multiple_5 = ugly[next_index_5] * 5;
			}
		}
		ArrayUtils.printArray(ugly);
	}
	public static void main(String[] args) {
		uglyNumber (20);
		
	}

}
