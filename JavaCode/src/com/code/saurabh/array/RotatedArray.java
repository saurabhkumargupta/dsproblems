package com.code.saurabh.array;

import com.code.saurabh.util.UtilClass;

public class RotatedArray {
	
	static int searchInRotatedArray (int[] input, int key) {
		int s = 0;
		int e = input.length -1;
		while (s <= e) {
			if (s == e) {
				if (input[s] == key) {
					return s;
				}
				else
					return -1;
			}
			int mid = s + (e-s)/2;
			if (input[mid] == key) {
				return mid;
			}

			//mid will be equal to s,only when there is 2 elements left in search and so if mid+1 is not key, then key doesnt exist
			//as mid is checked already, and size is 2, then mid+1 should be checked only
			if (mid == s) {
				if (input[mid+1] == key) {
					return mid+1;
				}
				else {
					return -1;
				}
			}
			//This check is not required, as in case of size 1 it is handled at start and in case of size 2, mid will always be =s
//			if (mid == e) {
//				if (input[mid-1] == key) {
//					return mid-1;
//				}
//				else {
//					return -1;
//				}
//			}
			
			//If complete subarray is sorted then do binary search
			if (input[s] < input[e] && key > input[s] && key < input[e]) {
				return UtilClass.binarySearch(input, key, s, e);
			}
			//If 2nd half subarray is sorted then do binary search
			if (input[mid] < input[e]) {
				//if element doesnt lie in range it means, we have to search other half of array, (here 1st half)
				if (key > input[mid] && key < input[e]) {
					return UtilClass.binarySearch(input, key, mid, e);
				}
				else {
					e = mid-1;
				}
			}
			//If 1st half subarray is sorted then do binary search
			else if (input[s] < input[mid]) {
				//if element doesnt lie in range it means, we have to search other half of array, (here 2nd half)
				if (key > input[s] && key < input[mid]) {
					return UtilClass.binarySearch(input, key, s, mid);
				}
				else {
					s = mid+1;
				}
			}
		}
		return -1;
	}
	
	/**
	 * this is working correctly for all cases
	 */
	static int getTip_1 (int[] input, int s, int e) {
		while (s < e) {
			//get middle element
			int mid =  s + (e-s)/2;
			if (mid == 0) {
				//this will only be possible when s = 0 and e = 1, it means either array size is 2 or start element is 1st as
				// we had previosly checked if 0th element is starting point then array is not rotated and this has been verified earlier
				//if middle element is 0th index, then check for 0th and 1st element
				if (input[mid] > input[mid+1]) {
					return mid+1;
				}
			}
			//check for last element to be starting point
			else if (mid == input.length -1) {
				if (input[mid-1] > input[mid]) {
					return mid;
				}
			}
			else {
				//otherwise current mid is starting point
				if (input[mid-1] > input[mid]) {
					return mid;
				}
				else if (input[mid] > input[mid+1]) {
					return mid+1;
				}
				//if start poiunt has not been figured out then devide search array
				//Check if mid to e is increasing, if so then starting point would in 1st array half else in 2nd array half
				if (input[mid] < input[e]) {
					e = mid-1;
				}
				else {
					s = mid+1;
				}
			}
		}
		return -1;
	}

	public static int getStartPoint_1  (int [] input) {
		//This checks whether array is of size 1 or if array is rotated or not, by checking corner elements
		if (!isRotatedArray (input)) {
			return 0;
		}
//		if (input.length == 2 && isRotatedArray (input)) {
//			return 1;
//		}
		return getTip_1 (input, 0, input.length -1);
	}

	/*
	 * Not working correctly for few cases.
	 * getStartPoint_1 & getTip_1 is correct code.
	 */
	public static int getStartPoint  (int [] input) {
		
		if (!isRotatedArray (input)) {
			return 0;
		}
		if (input.length == 2 && isRotatedArray (input)) {
			return 1;
		}
		return getTip (input, 0, input.length -1);
	}

	private static int getTip(int[] input, int s, int e) {
		int mid = s + (e-s)/2;

		if (isStartPoint (input, mid-1)) {
			return mid-1;
		}
		
		if (isStartPoint (input, mid)) {
			return mid;
		}
		
		if (isStartPoint (input, mid+1)) {
			return mid+1;
		}
		
		if (isUPDirection (input, mid)) {
			return getTip (input, mid+1, e);
		}
		else {
			return getTip (input, s, mid-1);
		}
	}

	private static boolean isUPDirection(int[] input, int i) {
		if (i == 0) {
			if (input [i] < input[i+1]) {
				return true;
			}
		}
		else if (i == input.length-1) {
			if (input [i-1] < input[i]) {
				return true;
			}
		}
		else if (input[i-1] < input[i] 
				&& input [i] < input[i+1]) {
			return true;
		}
		return false;
	}

	/*if number before ith index is greater than ith index value than ith index is starting point*/
	private static boolean isStartPoint(int[] input, int i) {
		if (i == 0) {
			if (input[input.length -1] > input[0]) {
				return true;
			}
		}
		else {
			if (input[i-1] > input[i]){
				return true;
			}
		}
		return false;
	}

	/*Elements should be unique*/
	public static boolean isRotatedArray(int[] input) {
		/*Input array of size 1 is not rotated*/
		if (input.length <= 1) {
			return false;
		}
		if (input[0] > input[input.length -1]) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		getStartPointTest ();
		//finElementInRotatedArrayTest ();
	}

	@SuppressWarnings("unused")
	private static void finElementInRotatedArrayTest() {
		int[][] input = {
				{2,1},
				{1,2},
				{1},
				{1,2,3},
				{2,3,1},
				{3,1,2},
				{3,8,9,10,1,2},
				{7,8,9,10,11,12,1},
				{7,8,9,10,11,12},
				{12,7,8,9,10,11},
				{7,8,9,10,11,12,1,2,3,4,5,6},
		};
		
		for (int i = 0; i < input.length; i++) {
			System.out.println("Array:");
			ArrayUtils.printArray(input[i]);
			int start = getStartPoint (input[i]);
			int result = -1;
			int searchKey = 2;
			if (start == 0) {
				result = UtilClass.binarySearch(input[i], searchKey, 0, input[i].length-1);
			}
			else {
				result = UtilClass.binarySearch(input[i], searchKey, 0, start-1);
				if (result == -1) {
					result = UtilClass.binarySearch(input[i], searchKey, start, input[i].length-1);
				}
			}
			System.out.println("searchKey: " + searchKey + " is at index: " + result);
			System.out.println();
		}
	}

	public static boolean isRotatedArray_1(int[] input, int s, int e) {
		/*Input array of size 1 is not rotated*/
		if ((e-s+1) <= 1) {
			return false;
		}
		if (input[s] > input[e]) {
			return true;
		}
		return false;
	}
	
	private static void getStartPointTest() {
		int[][] input = {
				{2,1},
				{1,2},
				{1},
				{1,2,3},
				{2,3,1},
				{3,1,2},
				{3,8,9,10,1,2},
				{7,8,9,10,11,12,1},
				{7,8,9,10,11,12},
				{12,7,8,9,10,11},
				{7,8,9,10,11,12,1,2,3,4,5,6},
				{9,1,5,6,7},
				{14,15,1,10,11,12,13},
				{100,200,300,400,500,600,700,800,900,990,999,10,20,30},
				{10,11,1,2,3,4,5,6,7,8,9},
				{1000,2000,10,20,30,300,400,500,600,700,800,900,990,999}
		};
		
		for (int i = 0; i < input.length; i++) {
			System.out.println("Array:");
			ArrayUtils.printArray(input[i]);
//			System.out.println("Start Point Index: " + getStartPoint (input[i]));
			System.out.println("Start Point Index: " + getStartPoint_1 (input[i]));
			System.out.println();
		}
		
		int[][] searchinput = {
				{10},
				{20,10},
				{10,20},
				{11},
				{11,12},
				{10,20,30, 100,200,300,400,500,600,700,800,900,990,999},
				{20,30, 100,200,300,400,500,600,700,800,900,990,999,10},
				{30,100,200,300,400,500,600,700,800,900,990,999,10,20},
				{100,200,300,400,500,600,700,800,900,990,999,10,20,30},
				{200,300,400,500,600,700,800,900,990,999,10,20,30,100},
				{300,400,500,600,700,800,900,990,999,10,20,30,100,200},
				{400,500,600,700,800,900,990,999,10,20,30,100,200,300},
				{500,600,700,800,900,990,999,10,20,30,100,200,300,400},
				{600,700,800,900,990,999,10,20,30,100,200,300,400,500},
				{700,800,900,990,999,10,20,30,100,200,300,400,500,600},
				{800,900,990,999,10,20,30,100,200,300,400,500,600,700},
				{900,990,999,10,20,30,100,200,300,400,500,600,700,800},
				{990,999,10,20,30,100,200,300,400,500,600,700,800,900},
				{999,10,20,30,100,200,300,400,500,600,700,800,900,990},
				{10,20,30,100,200,300,400,500,600,700,800,900,990,999},
		};

		for (int i = 0; i < searchinput.length; i++) {
			System.out.println("Array:");
			ArrayUtils.printArray(searchinput[i]);
			System.out.println("Key Index: " + searchInRotatedArray (searchinput[i], 10));
			System.out.println();
		}
	}
}
