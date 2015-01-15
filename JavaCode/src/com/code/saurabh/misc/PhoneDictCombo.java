/**
 * 
 */
package com.code.saurabh.misc;

import com.code.saurabh.util.UtilPart_2;

/**
 * @author saurabh.gupta
 *
 */
public class PhoneDictCombo {

	static int count = 0;
	static String[] dict = {
		"ABC",
		"DEF",
		"GHI",
		"JKL",
		"MNO",
		"PQR",
		"STU",
		"VWX",
//		"YZ"
	};
	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	
	static void f (int index, String out, int[] input) {
		if (index >= input.length) {
			count++;
//			System.out.println(out);
			return;
		}
		
		String temp = dict[input[index]-1];
		for (int j = 0; j < temp.length(); j++) {
			f (index+1, out+temp.charAt(j), input);
		}
	}
	public static void main(String[] args) {
		int [] input = {1,2,3,4,5,6,7};
//		int [] input = {1,2};
		f (0, "", input);
		System.out.println ("Total Combinations:" + ", Expected: " + UtilPart_2.x_pow_y(3, input.length) + ", Generated: " + count);
	}
}
