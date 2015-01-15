package com.challenges.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * https://www.hackerrank.com/challenges/almost-sorted
 */
public class AlmostSorted {

	public static void main(String[] args) {
		readInput();
	}

	static void readInput () {
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
		try {
			String size = br.readLine();
			int[] arr = inputArrayFromStdin(br, " ");
			if (isSorted (arr)) {
				System.out.println("yes");
				
			}
			int firstmaxindex = 0;
			for (int i = 1; i < arr.length; i++) {
				if (arr[i-1] < arr[i]) {
					firstmaxindex = i;
				}
				else {
					break;
				}
			}

			int lastminindex = firstmaxindex+1;
			for (int j = lastminindex+1; j < arr.length; j++) {
				if (arr[j] < arr[j-1]) {
					lastminindex = j;
				}
			}
			
//			if (lastminindex >= arr.length) {
//				System.out.println("Something wrong");
//				return;
//			}
			swap(arr, firstmaxindex, lastminindex);
			
			if (isSorted (arr)) {
				System.out.println("yes");
				System.out.println("swap " + (firstmaxindex+1) + " " + (lastminindex+1));
				return;
			}
			swap(arr, firstmaxindex, lastminindex);
			if (meetingOtherCondition(arr, firstmaxindex, lastminindex)) {
				System.out.println("yes");
				System.out.println("reverse " + (firstmaxindex+1) + " " + (lastminindex+1));
			}
			else {
				System.out.println("no");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	static public void swap (int [] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	static private boolean meetingOtherCondition(int[] arr, int firstmaxindex, int lastminindex) {
		if (lastminindex == 0) {
			return false;
		}
		if (firstmaxindex != 0 && arr[firstmaxindex-1] > arr[lastminindex]) {
			return false;
		}
		for (int i = lastminindex-1; i >= firstmaxindex; i--) {
			if (arr[i] < arr[i+1]) {
				return false;
			}
		}
		if (lastminindex != arr.length && arr[firstmaxindex] > arr[lastminindex+1]) {
			return false;
		}
		return true;
	}

	static private boolean isSorted(int[] arr) {
		for (int i = 1; i <  arr.length; i++) {
			if (arr[i] < arr[i-1]) {
				return false;
			}
		}
		return true;
	}
}
