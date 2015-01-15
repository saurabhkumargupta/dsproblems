/**
 * 
 */
package com.code.saurabh.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author saurabh.gupta
 *
 */
public class SumOFThree {

	static public class Pair {
		private int i;
		private int j;
		Pair (int i, int j) {
			this.i = i;
			this.j = j;
		}
		public int getJ() {
			return j;
		}
		
		public int getI() {
			return i;
		}
		
	}
	static Map<Integer, Pair> squareMap = new HashMap <Integer, Pair> (); 
	static void updateHash (int[] input) {
		for (int i =0; i < input.length; i++) {
			for (int j = i+1; j < input.length; j++) {
				int sum = input[i] + input[j];
				squareMap.put(sum, new Pair (i, j));
			}
		}
	}
	
	static void searchTriplet (int[] input, int sum) {
		updateHash(input);
		for (int i = 0; i <= input.length; i++) {
			if (i == input.length) {
				System.out.println("Sum " + sum + " not found:");
				break;
			}
			int reqval = sum-input[i];
			if (squareMap.containsKey(reqval)) {
				Pair val = squareMap.get(reqval);
				if (i != val.getJ() && i != val.getI()) {
					System.out.println(input[val.getI()] + ", " + input[val.getJ()] + ", " + input[i]);
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] input = {4, 6, 8, 9, 10, 15, 2, 3};
		searchTriplet (input, 9);
		searchTriplet (input, 34);
		searchTriplet (input, 22);
		searchTriplet (input, 27);
		searchTriplet (input, 26);
		searchTriplet (input, 11);
		searchTriplet (input, 10);
		
	}

}
