package com.company.hike;

import java.util.Arrays;

public class NextNumberWithSameDigits {

	static boolean sameDigits (int a, int b) {
		char[] aStr = String.valueOf(a).toCharArray();
		char[] bStr = String.valueOf(b).toCharArray();
		Arrays.sort(aStr);
		Arrays.sort(bStr);

		for (int i = 0; i < aStr.length; i++) {
			if (aStr[i] != bStr[i]) {
				return false;
			}
		}
		return true;
	}
	
	static boolean equalNumberOfDigits (int a, int b) {
		char[] aStr = String.valueOf(a).toCharArray();
		char[] bStr = String.valueOf(b).toCharArray();
		if (aStr.length != bStr.length) {
			return false;
		}
		return true;
	}

	static int nextBig (int n) {
		int next = n;
		while (equalNumberOfDigits (n, next)) {
			next++;
			if (sameDigits (n, next)) {
				return next;
			}
		}
		return -1;
	}
	
	static int nextBigWhat (int a) {
		char[] aStr = String.valueOf(a).toCharArray();
		
		int firstSmallfromlast = -1;
		for (int i = aStr.length-1; i >=1; i--) {
			if (aStr[i-1] < aStr[i]) {
				firstSmallfromlast = i-1;
				break;
			}
		}
		
		if (firstSmallfromlast == -1) {
			return -1;
		}
		int biggerThanFirstSmall = -1;
		for (int i = aStr.length-1; i >=1; i--) {
			if (aStr[i] > aStr[firstSmallfromlast]) {
				biggerThanFirstSmall = i;
				break;
			}
		}

		if (biggerThanFirstSmall == -1) {
			System.out.println("Something wrong in code");
			return -1;
		}
		char t = aStr[biggerThanFirstSmall];
		aStr[biggerThanFirstSmall] = aStr[firstSmallfromlast];
		aStr[firstSmallfromlast] = t;
		
		Arrays.sort(aStr, firstSmallfromlast+1, aStr.length);
		String result = String.valueOf(aStr);
		int num = Integer.valueOf(result);
		return num;
	}
	
	public static void main(String[] args) {
		int [] next = { 3256, 3265, 432, 1432, 5432, 1111, 1112, 165425, 1321, 3421, 3654321, 1233654321};
		for (int i = 0; i < next.length; i++) {
			int a = next[i];
			System.out.println("Next of " + a + " : " + nextBig (a) + ", nextBigWhat: " + nextBigWhat(a));
			System.out.println();
		}

//		int [][] input = {
//				{123, 321},
//				{1001, 1100},
//				{123,124},
//				{19095, 10987},
//				{19095, 10995},
//				{3256, 3257},
//				{3265, 3266},
//				{432, 433}
//		};
//		
//		for (int i = 0; i < input.length; i++) {
//			int a = input[i][0];
//			int b = input[i][1];
//			System.out.println(a + ", " + b + " : " + sameDigits (a, b));
//		}
	}

}
