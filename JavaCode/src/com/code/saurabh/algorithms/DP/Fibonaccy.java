package com.code.saurabh.algorithms.DP;

//file://localhost/Users/saurabh.gupta/Documents/Personal/Interview/Round_1_preparation_material/DS/Dynamic_Programming/Dynamic%20Programming%20%20%20Set%201%20(Overlapping%20Subproblems%20Property)%20%20%20GeeksforGeeks.html
//http://en.wikipedia.org/wiki/Dynamic_programming

//This kind of problem has overlapping subproblems as same subproblem is being solved by other subproblem

/** Overlapping subproblems can be solved in two ways:
 * 1) Memoization (top Down)
 * 2) Tabulation (Bottom up)
 * */
public class Fibonaccy {

	//Exponential
	//nth fibonaccy number
	//Using Recursion only
	static int fibonaccyBruteForce (int n) {
		if(n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return fibonaccyBruteForce (n-1) + fibonaccyBruteForce (n-2);
	}

	static Integer [] topDownArray = null;
	static int fibonaccyTopDown (int n) {		
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		if ( topDownArray == null) {
			topDownArray = new Integer [n+1];
			initArray (topDownArray);
		}

		if (topDownArray[n] == 0) {
			topDownArray[n] = fibonaccyTopDown(n-1) + fibonaccyTopDown(n-2);
		}
		return topDownArray[n];
	}

	//Iterative with storing in-between entries
	static int fibonaccyIterative (int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		int first = 0;
		int second = 1;
		int result = 0;
		for (int i = 2; i <= n; i++) {
			result = first + second;
			first = second;
			second = result;
		}
		return result;
	}
	
	//Bottom up approach, storing all values in table entries
	static int fibonaccyBottomUp (int n) {
		if (n == 0)
			return 0;
		int[] array = new int[n+1];
		array[0] = 0;
		array[1] = 1;
		for (int i = 2; i <= n; i++) {
			array[i] = array[i-1] + array[i-2];
		}
		return array[n];
	}

	private static void initArray(Integer[] topDownArray2) {
		for (int i = 0; i < topDownArray2.length; i++) {
			topDownArray2[i] = 0;
		}
	}
	public static void main(String[] args) {
		int [] input = {0,1,2,3,4,5,6,7};
		for (int i = 0; i < input.length; i++) {
			System.out.println("BruteForce:         (" + input[i] + "): ->" + fibonaccyBruteForce (input[i]));
			System.out.println("fibonaccyTopDown:   (" + input[i] + "): ->" + fibonaccyTopDown (input[i]));
			System.out.println("fibonaccyIterative: (" + input[i] + "): ->" + fibonaccyIterative (input[i]));
			System.out.println("fibonaccyBottomUp:  (" + input[i] + "): ->" + fibonaccyBottomUp (input[i]));
			topDownArray = null;
		}
	}

}
