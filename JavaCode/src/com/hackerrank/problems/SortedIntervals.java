/**
 *
 * https://www.hackerrank.com/challenges/almost-sorted-interval
 * fails some test cases: Timeout
 * I have solved in O ( n^2 ) Time-complexity
 * Solution is in O (N * log (n) )
 */

package com.hackerrank.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author saurabh.gupta
 *
 */
public class SortedIntervals {

	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {

		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String input = null;
		@SuppressWarnings("unused")
		int totalElements = 0;

		try {
			input = br.readLine();
			totalElements = Integer.valueOf(input);
			
			input = br.readLine();
			String[] data = input.split(" ");
			
			int[] inputdata = new int[data.length];
			for (int i = 0; i < data.length; i++) {
				inputdata[i] = Integer.valueOf(data[i]);
			}

			int count = 0;

			int[] lookupArray = new int[inputdata.length];
			for (int i = 0; i < inputdata.length; i++) {
				int j = i+1;
				for (j = i +1; j < inputdata.length; j++) {
					if (inputdata[j] > inputdata[i]) {
						break;
					}
				}
				if (j < inputdata.length) {
					lookupArray[i] = j;
				}
				else
					lookupArray[i] = -1;
			}
			
//			for (int i = 0; i < inputdata.length; i++) {
//				int smallest = inputdata[i];
//				int largest = inputdata[i];
//				count ++;
//				for (int j = i+1; j < inputdata.length; j++) {
//					
//					if (inputdata[j] < smallest) {
//						break;
//					}
//					if (inputdata[j] > largest) {
//						largest = inputdata[j];
//						count ++;
//					}
//				}
//			}
			
			System.out.println("Algo 1");
			count = algo_1 (inputdata);
			System.out.print(count);
			
			System.out.println("Algo 2");
			count = algo_2 (inputdata);
			System.out.print(count);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * </pre>
	 * @param inputdata
	 * @return
	 */
	private static int algo_2(int[] inputdata) {

		return 0;
	}

	static int algo_1 (int[] inputdata) {
		int count = 0;
		for (int i = 0; i < inputdata.length; i++) {
			int smallest = inputdata[i];
			int largest = inputdata[i];
			count ++;
			for (int j = i+1; j < inputdata.length; j++) {
				
				if (inputdata[j] < smallest) {
					break;
				}
				if (inputdata[j] > largest) {
					largest = inputdata[j];
					count ++;
				}
			}
		}
		return count;
	}

}
