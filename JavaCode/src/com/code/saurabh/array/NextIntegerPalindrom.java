/**
 * 
 */
package com.code.saurabh.array;

import com.code.saurabh.util.UtilClass;

/**
 * @author saurabh.gupta
 * LIMEROAD (Rajat)
 * 1. Given an integer. Find next higher palindrome of that number.
for ex. if given number is 14 then next higher palindrome is 22.
 * 
 * 1. We have to find next bigger palindrom number. So whatever is input, we are incrementing it by 1. so that now we have to find out
 * 	next palindrom including this new number, if this new number is palindrom then it will be answer.
 * 
 * 1. We are creating another array from input array. This new array is mirror of first-half of input array. This is again just to
 * 	check if this can give us next bigger palindrom.
 * 2. If previous step doesnt give us result then we are starting from middle of input array.
 * 3. increment middle values and if increment causes into carry, then increment side digits also and do it until carry is off.
 * 
 * 1. increment input by 1.
 * 2. check it for palindrom
 * 3. create mirror of firt-half-of-input arrya
 * 4. check is its bigger than original, if yes then its the result
 * 5. find middle of array, start, end indexes.
 * 6. increment middle indx values, if it overflows, keep carry and increment side digits
 * 7. if side digits oveflow, then increment those until overflow doesnt stop.
 */
public class NextIntegerPalindrom {
	
	static void stringToArray (String input, int[] arr) {
		for (int i = 0; i < input.length(); i++) {
			arr[i] = input.charAt(i) - '0';
		}
	}

	static boolean isPalindrom (String s) {
		if (s.length() <= 1 ) {
			return true;
		}
		if (s.charAt(0) != s.charAt(s.length()-1)) {
			return false;
		}
		return isPalindrom(s.substring(1, s.length()-1));
	}

	static void nextPalindromBruteForce (int input) {
		if (input < 10) {
//			System.out.println("Next palindrom of :" + input + " is " + x);
			System.out.println("Brute Force " + 11);
			return;
		}
		int x = input;
		while (true) {
			x++;
			String val = String.valueOf(x);
			if (isPalindrom(val)) {
//				System.out.println("Next palindrom of :" + input + " is " + x);
				System.out.println("Brute Force " + x);
				return;
			}
		}
	}

	static void resultFunction (int input) {
		input = input+1;
		String stringValue = String.valueOf(input);
		if (input < 10) {
//			System.out.println("Next palindrom of :" + input + " is " + x);
			System.out.print("Next Approach:" + 11);
			return;
		}
		nextPalindrom(stringValue);
	}
	
	static void nextPalindrom (String input) {
		System.out.print ("Next Approach: ");
		int inputlength = input.length();
		int [] inputArray = new int[inputlength];
		stringToArray (input, inputArray);
		int start, end;
		if (UtilClass.isEven(inputlength)) {
			start = inputlength/2 -1;
			end = start+1;
		}
		else {
			start = inputlength/2;
			end = inputlength/2;
		}

		int[] val = new int [inputlength];
		mirrorArray (inputArray, val);
	
		if (compareArray (val, inputArray) == true) {
			printArray(val);
			return;
		}

		boolean carry = false;
		if (end-start == 0) {
			carry = size_1 (val, start, end);
		} else if (end-start == 1) {
			carry = size_2 (val, start, end);
		}
		start--;
		end++;
		while (start >= 0 && end < val.length) {
			if (carry == true) {
				val [start]++;
				if (val[start] == 10) {
					val[start] = 0;
					carry = true;
				}
				else {
					carry = false;
				}
			}
			val[end] = val[start];
			end++;
			start--;
		}
		printArray(val);
		return;
	}


	private static boolean size_2(int[] val, int start, int end) {
		if (val[start] <= val[end]) {
			val[start]++;
		}
		boolean carry = false;
		if (val[start] == 10) {
			val[start] = 0;
			carry = true;
		}
		val[end] = val[start];
		return carry;
	}

	private static boolean size_1(int[] val, int start, int end) {
		val[start]++;
		boolean carry = false;
		if (val[start] == 10) {
			val[start] = 0;
			carry = true;
		}
		return carry;
	}

	private static boolean compareArray(int[] val, int[] inputArray) {
		for (int i = 0; i < val.length; i++) {
			if (val[i] < inputArray[i]) {
				return false;
			}
		}
		return true;
	}

	private static void mirrorArray(int[] inputArray, int[] val) {
		int inputlength = inputArray.length;
		int start, end;
		if (UtilClass.isEven(inputlength)) {
			end = inputlength/2;
		}
		else {
			end = inputlength/2+1;
		}
		start = 0;
		while (start < end) {
			val[start] = inputArray[start];
			start++;
		}

		if (UtilClass.isEven(inputlength)) {
			start = inputlength/2-1;
			end = start+1;
		}
		else {
			start = inputlength/2;
			end = inputlength/2;
		}
		while (end < val.length) {
			val[end] = inputArray[start];
			end++;
			start--;
		}
	}
	
	public static void printArray (int[] input) {
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i]);
		}
	}

	public static void main(String[] args) {
		int [] Intinput = {	14, 4, 99, 9, 1234, 1356, 1996, 2133, 941879783, 808, 1992, 19992, 9999, 9998, 9997};
		
		for (int i = 0; i < Intinput.length; i++) {
			System.out.println("input: " + Intinput[i]);
			resultFunction (Intinput[i]);
			System.out.println();
			nextPalindromBruteForce (Intinput[i]);		
			System.out.println();
			System.out.println();
		}
//		AllTest ();
	}

	private static void AllTest() {
		String [] input = {
				"abcddcba",
				"abba",
				"abbc",
				"abcba"
		};
		
		for (int i = 0; i < input.length; i++) {
			System.out.println(isPalindrom (input[i]));			
		}

		int [] Intinput = {	14, 4, 99, 9, 1234, 1356};
		
		for (int i = 0; i < Intinput.length; i++) {
			nextPalindromBruteForce (Intinput[i]);			
		}
		
		String testString = "123456789";
		int [] testArray = new int [testString.length()];
		stringToArray (testString, testArray);
		ArrayUtils.printArray(testArray);
		int [] mirror = new int [testArray.length];
		mirrorArray (testArray, mirror);
		ArrayUtils.printArray(mirror);		
	}
}
