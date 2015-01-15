/**
 * 
 */
package com.code.saurabh.array.misc;

import com.code.saurabh.array.ArrayUtils;

/**
 * @author saurabh.gupta
 *
 * http://www.careercup.com/question?id=5754245519245312
 *given a range of number 1 through N, where N >=3. you have to take an array of length 2N and place each number ( from the range 1 to N) twice. such a that the distance between two indexes of a number is equal to the number. example 

N=3 

( 3, 1, 2, 1, 3, 2 ) 

DOES NOT WORK FOR ALL Ns
 */
public class DuplicateArrayRange {
	static boolean f (int k, int n, int[] arr) {
		if (k == 0) {
			return true;
		}
		for (int i = 0; i < 2 * n; i++) {
			if (arr[i] == 0 && (i + k + 1) < (2 * n) && arr[i+k+1] == 0) {
				arr[i] = k;
				arr[i+k+1] = k;
				boolean ret = f (k-1, n, arr);
				if (ret == false) {
					arr[i] = 0;
					arr[i+k+1] = 0;	
				}
				else
					return ret;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int N = 4;
		int [] arr = new int[ 2 * N];
		ArrayUtils.initArray(arr);
		ArrayUtils.printArray(arr);
		f (N, N, arr);
		ArrayUtils.printArray(arr);
	}

}
