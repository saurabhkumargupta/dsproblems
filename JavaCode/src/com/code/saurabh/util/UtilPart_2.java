/**
 * 
 */
package com.code.saurabh.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author saurabh.gupta
 *
 */
public class UtilPart_2 {

	public static double x_pow_y (int x, int y) {
		int ret = 1;
		boolean negative = (y < 0)?true:false;
		for (int i = 1; i <= Math.abs(y); i++) {
			ret = ret * x;
		}
		
		if (negative) {
			return 1/(double)ret;
		}
		return ret;
	}
	
	public static int x_pow_y_rec (int x, int y) {
		if (y == 1)
			return x;
		return x * x_pow_y_rec (x ,y-1);
	}
	
	
	static public double x_pow_y_logn (int a, int b) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer> ();
		map.put(1, a);
		map.put(0, 1);
		boolean negative = (b < 0)?true:false;
		int result = x_pow_y_logn (a, Math.abs(b), 1, map);
		if (negative) {
			return 1/(double)result;
		}
		return result;
	}
	/*
	 * This will take log(n) complxity
	 * Idea is:
	 * keep calculated values in hashMap and check how many values are remained to calculate
	 * if remaining value or any near calculated value exists then re-use it and update counter accordingly
	 * 
	 * Initialise map with counter: 1
	 * Map<CalculatedCounter, CalculatedValue> = <1, a> (a : whose power has to be calculated)
	 * Map<CalculatedCounter, CalculatedValue> = <2, 4>
	 */
	static public int x_pow_y_logn (int val, int total, int calculated, Map<Integer, Integer> map) {
		int remaining = total-calculated;
		int subResult = map.get(calculated);
		if (remaining <= 0) {
			return map.get(total);
		}
		int reusedval = 0;
		if (map.containsKey(remaining)) {
			reusedval = map.get(remaining);
			calculated = calculated + remaining;
		}
		else if (remaining > calculated && map.containsKey(calculated)) {
			reusedval = map.get(calculated);
			calculated = calculated + calculated;
		}
		else if (remaining == 0){
			reusedval = val;
			calculated = calculated + 1;
		}
		else {
			int lower_2_power_val = (remaining % 2 == 0) ?remaining: (remaining & (remaining-1));
			if (map.containsKey(lower_2_power_val)) {
				reusedval = map.get(lower_2_power_val);
				calculated = calculated + lower_2_power_val;
			}
		}
		map.put(calculated, reusedval * subResult);
		return x_pow_y_logn (val, total, calculated, map);
	}
	
	/**
	 * <pre>
	 * This extra edition is for considering mod of negative
	 * http://mathforum.org/library/drmath/view/52343.html
	 * </pre>
	 * @param l
	 * @param b
	 * @return
	 */
	public static long mod (long l, int b) {
		return (l % b + b) % b;
	}
	
	public static int minOfThree (int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}
	
	public static int maxOfThree (int a, int b, int c) {
		return Math.max(a, Math.max(b, c));
	}


	public static int IntegerDigitCount(int x) {
		int count = 1;
//		if (x <= 0)
//			return 0;
		while (x / 10 > 0)
		{
			count ++;
			x = x/10;
		}
		return count;
	}

	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
//		int [][] input = 
//			{
//				{1, 2, 3},
//				{1, 3, 2},
//				{2, 1, 3},
//				{2, 3, 1},
//				{3, 1, 2},
//				{3, 2, 1},
//			};
//		
//		for (int i = 0; i < input.length; i++) {
//			System.out.print("Mininum of " + input[i][0] +  " , "+ input[i][1] + " , "+ input[i][2] + " : ");
//			System.out.print(minOfThree (input[i][0], input[i][1], input[i][2]));
//			
//			System.out.println();
//			System.out.print("Maximum of " + input[i][0] +  " , "+ input[i][1] + " , "+ input[i][2] + " : ");
//			System.out.print(maxOfThree (input[i][0], input[i][1], input[i][2]));
//			System.out.println();
//			System.out.println();
//		}
//
		int [][] input_2 = {
				{2, 1},
				{1, 1},
				{2, 2},
				{3, 4},
				{5, 3},
				{9, 2},
				{7, 3},
				{2, 4},
				{2, 5},
				{2, 0},
				{2, -1},
				{2, -2}
		};
		
		for (int i = 0; i < input_2.length; i++) {
			int a = input_2[i][0];
			int b = input_2[i][1];
			
//			System.out.println(a+"^"+b +": " + x_pow_y (a,b) + ", x_pow_y_rec: " + x_pow_y_rec(a, b) + ", logn: " + 
//					x_pow_y_logn (a, b));
			System.out.println(a+"^"+b +": " + x_pow_y (a,b) + ", logn: " + x_pow_y_logn (a, b));
		}
//		
//		System.out.println("Size: " + IntegerDigitCount (2567));
//		System.out.println("Size: " + IntegerDigitCount (2));
	}

}
