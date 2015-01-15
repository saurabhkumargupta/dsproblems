package com.code.saurabh.bitwise;

public class NumberOfSetBits {

	static int diffBitsInTwoNumber (int x, int y) {
		//Different bits will be set
		int num = x ^ y;
		return setBitCount (num);
	}
	
	static int setBitCount (int x) {
		int count = 0;
		while (x != 0) {
			count++;
			x &= x-1;
		}
		return count;
	}
	
	//first bit from LSB side 
	static int firstSetBitRemoval (int x) {
		System.out.println("Binary: " + Integer.toBinaryString(x));
		System.out.println("Binary: " + Integer.toBinaryString(-x));
		int y = x & -x;
		y = x - y;
		return y;
	}
	public static void main(String[] args) {
		for (int i = 0; i < 8;i++) {
			System.out.println("Set bits in " + i + " is " + setBitCount(i));
			System.out.println("Different bits in " + i + " and " + (i+1) + " is " + diffBitsInTwoNumber(i, i+1));
			System.out.println("After first set bit remove: " + firstSetBitRemoval(i));
			System.out.println();
		}
	}

}
