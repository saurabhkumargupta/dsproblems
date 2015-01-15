package com.code.saurabh.array.misc;

import com.code.saurabh.array.ArrayUtils;
/*
 * http://www.careercup.com/question?id=13630670
 * 
 * Given array of numbers, with 0 also, you have to replace each element of array with multiplication of all elements except current element
 * Input
1, 2, 3, 
6, 3, 2, 
Input
1, 2, 3, 0, 
0, 0, 0, 6, 
Input
1, 2, 3, 0, 5, 
0, 0, 0, 30, 0,

Do it without using division operator
 */
public class ProductArray {

	static void product_with_division (int[] arr) {
		int mul = 1;
		int zero_count = 0;
		int zero_index = -1;
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				zero_count += 1;
				zero_index = i;
			}
			else
				mul *= arr[i];
		}
		
		if (zero_count > 1) {
			for (int i = 0; i < arr.length; i++) {
				arr[i] = 0;
			}
		}
		else if (zero_count == 1) {
			for (int i = 0; i < arr.length; i++) {
				arr[i] = 0;
			}
			arr[zero_index] = mul;
		}
		else {
			for (int i = 0; i < arr.length; i++) {
				arr[i] = mul/arr[i];
			}
		}
	}
	
	//Doesnt work with 0s, just have to do priliminary checks done in above method
	//O (n) space and O(n) complexity
	static void product_without_division (int [] arr) {
		int mul = 1;
		int [] result = new int[arr.length];
		ArrayUtils.initArray(result);
		for (int i = 0; i < arr.length; i++) {
			result[i] = mul;
			mul = mul * arr[i];
		}
		mul = 1;
		for (int i = arr.length -1; i >= 0; i--) {
			result[i] = result[i] * mul;
			mul =  mul * arr[i];
		}
		
		ArrayUtils.printArray(result);
	}
	public static void main(String[] args) {
		int [][] input = {
				{1,2,3},
				{1,2,3,0},
				{1,2,3,0,5},
				{10, 3, 5, 6, 2},
				{7, -3, -1, 2, -40, 0, 3, 6}
		};
		
		for (int i = 0; i < input.length; i++) {
			System.out.println("Input");
			ArrayUtils.printArray(input[i]);
			product_without_division (input[i]);
//			System.out.println("Output");
//			ArrayUtils.printArray(input[i]);
		}
	}

}
