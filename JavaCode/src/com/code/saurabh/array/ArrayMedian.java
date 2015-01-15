package com.code.saurabh.array;

/*
 * http://www.geeksforgeeks.org/median-of-two-sorted-arrays/
 * 
 * Method - 3 (Copied)
 */
public class ArrayMedian {

	/* This function returns median of ar1[] and ar2[].
	   Assumptions in this function:
	   Both ar1[] and ar2[] are sorted arrays
	   Both have n elements */
	static float getMedian(int ar1[], int ar2[], int n)
	{
		// If all elements of array 1 are smaller then
		   // median is average of last element of ar1 and
		   // first element of ar2
		   if (ar1[n-1] < ar2[0])
		     return (ar1[n-1]+ar2[0])/2;

		   // If all elements of array 1 are smaller then
		   // median is average of first element of ar1 and
		   // last element of ar2
		   if (ar2[n-1] < ar1[0])
		     return (ar2[n-1]+ar1[0])/2;

		   return getMedianRec(ar1, ar2, 0, n-1, n);
	}

	/* A recursive function to get the median of ar1[] and ar2[]
	   using binary search */
	static float getMedianRec(int ar1[], int ar2[], int left, int right, int n)
	{
	    int i, j;

	    /* We have reached at the end (left or right) of ar1[] */
	    if (left > right)
	        return getMedianRec(ar2, ar1, 0, n-1, n);

	    i = (left + right)/2;
	    j = n - i - 1;  /* Index of ar2[] */

	    /* Recursion terminates here.*/
	    if (ar1[i] > ar2[j] && (j == n-1 || ar1[i] <= ar2[j+1]))
	    {
	        /* ar1[i] is decided as median 2, now select the median 1
	           (element just before ar1[i] in merged array) to get the
	           average of both*/
	        if (i == 0 || ar2[j] > ar1[i-1])
	            return (float)(ar1[i] + ar2[j])/2;
	        else
	            return (float)(ar1[i] + ar1[i-1])/2;
	    }

	    /*Search in left half of ar1[]*/
	    else if (ar1[i] > ar2[j] && j != n-1 && ar1[i] > ar2[j+1])
	        return getMedianRec(ar1, ar2, left, i-1, n);

	    /*Search in right half of ar1[]*/
	    else /* ar1[i] is smaller than both ar2[j] and ar2[j+1]*/
	        return getMedianRec(ar1, ar2, i+1, right, n);
	}

	public static void main(String[] args) {
//		   int ar1[] = {1, 12, 15, 26, 38};
//		   int ar2[] = {2, 13, 17, 30, 45};
 
		int ar1 [] = {4, 8, 11, 15, 17, 19, 21};
		int ar2 [] = {3, 5, 6,  10, 12, 14, 16};
		
		   //doesnt work for unequal size
		   System.out.println("Median is " + getMedian(ar1, ar2, ar1.length));
	}

}
