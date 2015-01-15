/**
 * 
 */
package com.code.saurabh.misc;

/**
 * @author saurabh.gupta
 * You have 2 array of numbers say X and Y. Let x be any possible
element of first array and y be any possible element of the second
one. You have to return the minimum possible value |x-y|.

For example

X = [1,2,7,9]
Y = [5,12,13]

This should return 2, i.e. |7-5|
 */
public class MindiffInTwoArray {

	static int minDiffUsingMergeSortedArrayTech (int[] f, int[] s) {
		int i = 0;
		int j = 0;
		int mindiff = Integer.MAX_VALUE;
		while (i < f.length && j < s.length) {
			int diff = Math.abs(f[i] - s[j]);
			if (mindiff > diff) {
				mindiff = diff;
			}
			if (f[i] < s[j])
				i++;
			else
				j++;
		}
		return mindiff;
	}
	static int getMinDiff (int v, int[] arr) {
		int min = Math.abs(v - arr[0]);;
		for (int i = 0; i < arr.length; i++) {
			int diff = Math.abs(v - arr[i]);
			if (min > diff) {
				min = diff;
			}
		}
		return min;
	}
	static int minDiff (int [] a1, 
			int [] a2) {
		int mindiff = -1;
		//this is initial difference between first elements of two arrays
		/**
		 * f = {6,7,8,9}
		 * s = {1,2,3,4,5,6},
		 * 
		 * f = {6,7,8,9}
		 * s = {1,2,3,4},
		 * 
		 * For these type of cases, we first have to find the min diff between 1st element of first array
		 * and all elements of 2nd array
		 */
		mindiff = getMinDiff (a1[0], a2);
//		mindiff = Math.abs(a1[0] - a2[0]);
		for (int i = 1; i < a1.length; i++) {
			//value searched in a2 array should be greater than this value, not equal to this value
			int minvalue = a1[i] - mindiff;
			//value searched in a2 array should be less than this value, not equal to this value
			int maxvalue = a1[i] + mindiff;
			
			//This function will return index from a2 array for value which is greater than minvalue but less
			//than a1[i].In case no value, -1 is returned
			int mink = greaterthanandlessthan (a2, minvalue, a1[i]);
			//This function will return index from a2 array for value which is less than maxvalue but greater
			//than a1[i]. In case no value, -1 is returned
			//int maxk = lessthanandgreaterthan (a2, maxvalue, a1[i]);
			int maxk = greaterthanandlessthan (a2, a1[i], maxvalue);
			
			//index from a2 array which is closer to a1[i] value
			int finalk = -1;
			if (maxk == -1)
				finalk = mink;
			else if (mink == -1)
				finalk = maxk;
			else {
				//whichever index is closer to a1[i] will returned to finalk
				finalk = ( Math.abs(a1[i] - a2[mink]) < Math.abs(a2[maxk] - a1[i]) ) ? mink : maxk; 
			}

			//If index available in a2 array. then check if new diff is lesser than previous diff
			//If so, then update mindiff with new value else old mindiff remain unchanged
			if (finalk != -1) {
				int newdiff = Math.abs(a1[i] - a2[finalk]);
				if (mindiff > newdiff)
					mindiff = newdiff;
			}
		}

		return mindiff;
	}
	/**
	 * <pre>
	 * return index from given array for value which is greater than minvalue
	 * and smaller than K
	 * value which is closest to K is returned
	 * minvalue = 2, K = 8
	 * 1,2,3,5,7,8,11
	 * return value should be 7 ( not 8, not 3 )
	 * 
	 * 1,2,3,8,11,13,14,15,16
	 * 
	 * 1,2,3,4,11,13,14,15,16
	 * </pre>
	 * @param arr Given array
	 * @param minvalue
	 * @param K
	 * @return
	 */
	private static int greaterthanandlessthan(int[] arr, int minvalue, int K) {
		int s_index = 0;
		int e_index = arr.length - 1;
		int r_index = -1;
		while (e_index >= s_index) {
			int m_index = s_index + (e_index - s_index)/2;
			if (K < arr[s_index]) {
				return -1;
			}
			if (K > arr[e_index]) {
				//as we need lower value than K, but greater than minvalue.
				//As greatest index is lower than K, which is meeting first criteria 
				//we will check if its satisfying second criteria. If so then this is the resultant value
				if (minvalue < arr[e_index]) {
					r_index = e_index;
					break;
				}
			}
			
			//In this function, we are not looking for equal values
			//but lesser than value, so we will check just lower index if it satisfies next condition of
			// minvalue than this is the resulting index, break the loop and return this index.
			if (K == arr[m_index]) {
				if (arr[m_index -1] > minvalue) {
					r_index = m_index -1;
				}
				break;
			}
			else if (arr[m_index] > K
					&& arr[m_index-1] < K) {
				if (arr[m_index -1] > minvalue) {
					r_index = m_index -1;
				}
				break;
			}
			else if (K < arr[m_index]) {
				e_index = m_index-1;
			}
			else if (K > arr[m_index]) {
				s_index = m_index+1;
			}
		}
		return r_index;
	}

	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int [][] arr = {
				{1,2,3,5,7,8,11},
				{1,2,3,8,11,13,14,15,16},
				{1,2,3,4,11,13,14,15,16},
				{4,5,6,7,8},
				{1, 7, 11, 12, 18, 19}
		};

		int [][] minmaxarr = {
				{2,8},
				{1,11},
				{15,17},
				{1,2},
				{1,2},
				{2, 30}
		};
//		for (int i = 0; i < minmaxarr.length; i++) {
//			greaterthanandlessthantest (arr, minmaxarr[i][0], minmaxarr[i][1]);
//		}
		
		minDiffTest ();
	}
	
	/**
	 * <pre>
	 * </pre>
	 */
	private static void minDiffTest() {
		int[][] firstarray = {
				{15, 16, 17},
				{1,2,7,9},
				{4,5,11,20},
				{6,7,8,9},
				{6,7,8,9},
				{16,17,18,19},
				{1, 5, 9},
		};
		int[][] secondarray = {
				{1, 7, 11, 12, 18, 19},
				{5,12,13},
				{7,13,21},
				{1,2,3,4},
				{1,2,3,4,5,6},
				{1,2,3,4,5,16},
				{6, 7, 8}
		};
		
		for (int i = 0; i < firstarray.length; i++) {
			int minDifference = minDiff (firstarray[i], secondarray[i]);
			System.out.println("result : " + minDifference);
			minDifference = minDiffUsingMergeSortedArrayTech (firstarray[i], secondarray[i]);
			System.out.println("result : " + minDifference);
			System.out.println();
		}
	}
	@SuppressWarnings("unused")
	private static void greaterthanandlessthantest (int[][] arr, int minvalue, int maxvalue) {
		System.out.println ("minvalue : " + minvalue + ", maxvalue :" + maxvalue);
		for (int i = 0; i < arr.length; i++) {
			int index = greaterthanandlessthan (arr[i], minvalue, maxvalue);
			if (index == -1) {
				System.out.println("for array index: " + i + " no result");
			}
			else
				System.out.println("Result value: " + arr[i][index]);
		}
		System.out.println ();
	}

}
