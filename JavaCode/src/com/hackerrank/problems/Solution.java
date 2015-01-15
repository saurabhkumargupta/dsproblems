/**
 * 
 */
package com.hackerrank.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.code.saurabh.array.ArrayUtils;

/**
 * @author saurabh.gupta
 *
 */
public class Solution {

	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int max = 0;
		try {
			String[] firstline = br.readLine().split(" ");
			int N = Integer.valueOf(firstline[0]);
			int M = Integer.valueOf(firstline[1]);
			
			int[] testList = new int [N + 1];
			initArray(testList, 0);
			String input = null;
			int linecount = 0;
			while ( (input = br.readLine()) != null) {
				
				String[] nextLine = input.split(" ");
				int[] nextM = new int[nextLine.length];
				for (int i = 0; i < nextLine.length; i++){
					nextM[i] = Integer.valueOf(nextLine[i]);
				}
				
				int start = nextM[0];
				int end = nextM[1];
				int K = nextM[2];
				for (int i = start; i <= end; i++) {
					testList[i] += K;
					if (max < testList[i]) {
						max = testList[i];
					}
				}
				linecount ++;
				if (linecount >= M)
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(max);
	}

	public static void initArray (int[] input, int initvalue) {
		for (int i = 0; i < input.length; i++) {
			input[i] = initvalue;
		}
	}

}
