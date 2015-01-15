/**
 * 
 */
package com.code.saurabh.math;

import com.code.saurabh.array.ArrayUtils;

/**
 * Find the unique number that is present only once in array while all the others are present three times. 
 * Example: 2,3,5,1,2,2,5,3,5,3 
 * Answer : 1 as 2,3,5 are repeated three times 
 * Complexity should be better than O(nlogn)
 * 
 * http://www.careercup.com/question?id=5119850590502912
 * 
 * From site itself
 * The idea is to for every bit position count the number of 1's mod 3. 
 * The resulting number will be the answer.It is possible to do it in O(n) using bitwise operations
 * @author saurabh.gupta
 *
 */
public class OneUniqueRestThreeElement {

	/**
	 * <pre>
	 * This kind of return element which is present different number of times than recurringElementCount
	 * i.e. if all elements are present for 5 number of  times and there is a single element which is not present 5 number of times
	 * (either 1,2,3,4,6,7,8 etc) that element will be returned
	 * 
	 * If all elements are present equal number of times than it will return 0
	 * </pre>
	 * @param input
	 * @param recurringElementCount
	 * @return
	 */
	static int findUnique (int[] input, int recurringElementCount) {
		int result = 0;
		for (int bit = 0; bit < 32; bit++) {
			int mask = 1 << bit;
			int setCount = 0;
			for (int i = 0; i < input.length; i++) {
				if ((input[i] & mask) != 0) {
					setCount++;
					setCount = setCount % recurringElementCount;
				}
			}
			if (setCount > 0) {
				result = result | mask;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[][] input = {
				{2,3,5,1,2,2,5,3,5,3},
				{2,1,5,3,2,2,5,1,5,1},

				{2,3,5,1,2,2,5,3,5,3,2,3,5},
				{2,1,5,3,2,2,5,1,5,1,2,1,5},
				{2,1,5,3,2,2,5,1,5,1,2,1,5,3,3,3,3},
				{2,1,5,3,2,2,5,1,5,1,2,1,5,3,3,3},
		};
		int[] count = {3, 3, 4, 4, 4, 4};
		for (int i = 0; i < input.length; i++) {
			System.out.println("Input Array:");
			ArrayUtils.printArray(input[i]);
			System.out.println("Unique: " + findUnique (input[i], count[i]));
			System.out.println();
		}
	}
}
