/**
 * 
 */
package com.code.saurabh.math;

/**
 * You have an array of integers(size N), such that each integer is present an odd number of time, except 3 of them(which are present even number of times). Find the three numbers. 

	Only XOR based solution was permitted. 
	Time Complexity: O(N) 
	Space Complexity: O(1) 
	
	Sample Input: 
	{1,6,4,1,4,5,8,8,4,6,8,8,9,7,9,5,9} 
	Sample Output: 
	1 6 8

	http://www.careercup.com/question?id=5707243546738688
	
	alog copied from site

 * @author saurabh.gupta
 *
 */
public class ThreeEvenRestOddTimes {
	static void getEvenDuplicates(int[] number)
	{
		
		int len = number.length;
		int tracker=0;

		for(int i=0; i< len; ++i)
		{		
			int shifted = 1<< number[i];
			tracker ^= shifted;
		}
		
		System.out.println(tracker);
		for(int i=0; i< len; ++i)
		{
			int shifted = 1<< number[i];
			if((shifted & tracker) ==  0)
			{
				tracker ^=shifted; // to avoid repeated printing of the number
				System.out.println(number[i] + ", ");
			}
		}
	}

	public static void main(String[] args) {
//		int number[]={1,6,4,1,4,5,8,8,4,6,8,8,9,7,9,5,9,5};
		int number[] = {1, 1};
		getEvenDuplicates(number);
	}

}
