package com.code.saurabh.algorithms.DP;

import com.code.saurabh.array.ArrayUtils;

public class MaxProductSubArray {

	/*
		http://leetcodesolution.blogspot.in/2014/09/leetcode-maximum-product-subarray.html 
	*/
	static void maxProduct(int A[]) {
		int n = A.length;
        int maxProd = Integer.MIN_VALUE;
        int prod = 1;
        for(int i=0; i <n; i++)
        {
            prod = prod * A[i];
            maxProd = Math.max(maxProd, prod);
            if (A[i] == 0)
                prod = 1;
        }
        
        prod = 1;
        for(int i=n-1; i >=0; i--)
        {
            prod = prod * A[i];
            maxProd = Math.max(maxProd, prod);
            if (A[i] == 0)
                prod = 1;
        }
        System.out.println("maxProduct: " + maxProd);
    }
	
	static void maxProd (int [] input) {
		int max = Integer.MIN_VALUE;
		int start = -1;
		int end = -1;
		int [] [] temp = new int[input.length][input.length];
		ArrayUtils.initArray(temp);
		
		for (int i = 0; i < input.length; i++) {
			temp[i][i] = input[i];
			if (max < temp[i][i]) {
				max = temp[i][i];
				start = i;
				end = i;
			}
		}
		
		for (int size = 2; size < input.length; size++) {
			for (int i = 0; i < input.length - (size -1); i++) {
				int j = i + (size -1);
				temp [i][j] = temp[i][j-1] * input[j];
				if (max < temp[i][j]) {
					max = temp[i][j];
					start = i;
					end = j;
				}
			}
		}
		
		System.out.println("maxProd: "+max);
//		ArrayUtils.printArray(input, start, end);
	}

	public static void main(String[] args) {
		int [][] input = {
				{7, -1, -20, -10, 0},
				{7, -1, -20, -10, 0, 15, 20},
				{-7, -1, -20, -10, 0, 15, 20},
				{1, -2, -3, 0, 7, -8, -2},
				{0, 0, -20, 0},
				{0, 0, 0},
				{-2, -3, 0, -2, -40},
				{-1, -3, -10, 0, 60},
				{6, -3, -10, 0, 2},
		};
		
		for (int i = 0; i < input.length; i++) {
			maxProd (input[i]);
			maxProduct(input[i]);
		}
	}

}
