package com.code.saurabh.array.misc;

import java.util.Arrays;
/*
 * Given an array of numbers, give me a wave.
       Input: a0 a1 a2 .. an-1
       Mathematical expression for wave: ai>=aj<=ak>=al<=am for all i, j,k,l .. m belong to [0,n-1]
       
 * http://ideone.com/SKOn9b
 */
public class ArrayWaveAlternaterINcreasing {

	static void wave (int [] input) {
		Arrays.sort(input);
		System.out.println();
		System.out.println();
		System.out.print(input[1] + ">=" + input[0]);
		for (int i = 2; i < input.length; i+=2) {
			if (i == input.length-1) {
				System.out.print("<=" + input[i]);
			}
			else {
				System.out.print("<=" + input[i+1] + ">=" + input[i]);
			}
		}
	}
	public static void main(String[] args) {
		int [][] input = {
				{7, 9, 5, 4, 8, 6},
				{7, 9, 5, 4, 8},
				{11, 1, 6, 3, 5, 4}
		};
		for (int i = 0; i < input.length; i++) {
			wave(input[i]);
		}
	}

}
