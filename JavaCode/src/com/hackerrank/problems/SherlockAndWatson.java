/**
 * 
 */
package com.hackerrank.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

import com.code.saurabh.util.StandardInputReader;
import com.code.saurabh.util.UtilPart_2;

/**
 * @author saurabh.gupta
 *
 */
public class SherlockAndWatson {

	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
//		SherlockAndWatsonTest ();
//		SherlockAndSquareTest ();
		StrangeNumbers ();
	}

	static void SherlockAndWatsonTest () {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		int[] arr= null;
		try {
			String[] inputs = br.readLine().trim().split(" ");
			int size = Integer.valueOf(inputs[0]);
			int rotation = Integer.valueOf(inputs[1]);
			int testcases = Integer.valueOf(inputs[2]);
			
			arr = StandardInputReader.inputArrayFromStdin (br);
			for (int i = 0; i < testcases; i++) {
				int x = Integer.valueOf(br.readLine().trim());
				int index = f (rotation, x, size);
				System.out.println(arr[index]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static int f (int K, int x, int size) {
		K = K % size;
		x = x - K;
		if (x < 0) {
			x = x + size;
		}
		return x;
	}

	static void SherlockAndSquareTest () {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		int testCases;
		try {
			testCases = Integer.valueOf(br.readLine().trim());
			for (int i = 0; i < testCases; i++) {
				int next = Integer.valueOf(br.readLine().trim());
				if (next == 0) {
					System.out.println(4);
				}
				else {
					long prime = (long) (Math.pow(10, 9) + 7);
					long v = (long) ((Math.pow(2, next))%prime);
					System.out.println(v + 4);
				}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	static HashSet<Integer> strange = new HashSet <Integer> ();
	static void StrangeNumbers () {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		int testCases;
		try {
			testCases = Integer.valueOf(br.readLine().trim());
			for (int i = 0; i < testCases; i++) {
				int total = 0;
				int[] arr = StandardInputReader.inputArrayFromStdin (br);
				for (int v = arr[0]; v <= arr[1]; v++) {
					if (isStrange (v)) {
//						System.out.println(v);
						total++;
					}
				}
				strange.clear();
				System.out.println(total);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean isStrange (int x) {
		int digitCount = UtilPart_2.IntegerDigitCount (x);
		boolean ret = true;
		if (digitCount == 1) {
			return true;
		}
		if ( (x % digitCount) == 0 )
			ret = isStrange (x/digitCount);
		else
			ret = false;
		return ret;
//		if (strange.contains(x)) {
//			return true;
//		}
//		int digitCount = UtilPart_2.IntegerDigitCount (x);
//		if (digitCount == 1) {
//			strange.add(x);
//			return true;
//		}
//		else {
//			if (x % digitCount == 0) {
//				boolean ret = isStrange (x/digitCount);
//				if (ret == true) {
//					strange.add(x);
//				}
//				return ret;
//			}
//			else {
//				return false;
//			}
//		}
	}

}
