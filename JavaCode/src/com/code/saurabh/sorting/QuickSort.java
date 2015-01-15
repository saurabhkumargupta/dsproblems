package com.code.saurabh.sorting;

import com.code.saurabh.array.ArrayUtils;
import com.code.saurabh.util.UtilClass;

/*
 * http://geeksquiz.com/quick-sort/
 * http://www.geeksforgeeks.org/when-does-the-worst-case-of-quicksort-occur/
 * http://geeksquiz.com/quick-sort/
 * http://www.geeksforgeeks.org/iterative-quick-sort/
 * 
 */
public class QuickSort {

	static int partition (int arr[], int l, int h)
	{
	    int x = arr[h];
	    int i = (l - 1);

	    for (int j = l; j <= h- 1; j++)
	    {
	        if (arr[j] <= x)
	        {
	            i++;
	            UtilClass.swap(arr, i, j);
	        }
	    }
	    UtilClass.swap(arr, i+1, h);
	    return (i + 1);
	}

	/* A[] --> Array to be sorted, l  --> Starting index, h  --> Ending index */
	static void quickSort(int A[], int l, int h)
	{
	    if (l < h)
	    {        
	        int p = partition(A, l, h); /* Partitioning index */
	        quickSort(A, l, p - 1);  
	        quickSort(A, p + 1, h);
	    }
	}

	public static void main(String[] args) {
		int [][] input = {
				{4, 3, 5, 2, 1, 3, 2, 3},
				{4,3,2,1}
		};
		
		for (int i =0; i < input.length; i++) {
			System.out.println("Input");
			ArrayUtils.printArray(input[i]);
			quickSort (input[i], 0, input[i].length-1);
			System.out.println("Output");
			ArrayUtils.printArray(input[i]);
			System.out.println();
		}
	}

}
