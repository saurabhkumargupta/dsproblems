/**
 * 
 */
package com.code.saurabh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.code.saurabh.array.ArrayUtils;

/**
 * @author saurabh.gupta
 *
 */
public class StandardInputReader {

	/**
	 * <pre>
	 * Do not consider extra spaces
	 * </pre>
	 * @param args
	 */

	static public int[] inputArrayFromStdin (BufferedReader br) throws IOException {
		return inputArrayFromStdin (br, " ");
	}
	
	static public int[] inputArrayFromStdin (BufferedReader br, String separator) throws IOException {
		String[] input = br.readLine().trim().split(separator);
		int[] arr = new int[input.length];
		int index = 0;
		for (String s : input) {
			if (!s.equals(" ") && !s.isEmpty()) {
				arr[index] = Integer.valueOf(s.trim());
				index++;
			}
		}
		int[] finalArray = new int[index];
		for (int i = 0; i < index; i++) {
			finalArray[i] = arr[i];
		}
		return finalArray;
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		try {
			ArrayUtils.printArray(inputArrayFromStdin (br, ","));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
