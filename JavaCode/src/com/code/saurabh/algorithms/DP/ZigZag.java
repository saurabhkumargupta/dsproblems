package com.code.saurabh.algorithms.DP;
/**
 * http://community.topcoder.com/stat?c=problem_statement&pm=1259&rd=4493
 * <p>
 * This is done using Dynamic Programming <br>
 * 
 * First property (overlapping subproblem) : <br>
 *  subproblem: can be divided into sub-problem of lesser size sequence. (i.e. for array size: 1, for array size : 2, ... upto array size: N ) <br>
 *  overlapping: each next subproblem include lower subproblem as solution. which makes is overlapping problem <br><br>
 *  
 * Second property (optimal substructure) : at each state, maximum sub-sequence will be optimal till that state and thus will be part of over-all solution.<br>
 * Also, lower state is independent of higher state (updating higher state does not modify lower state)<br> <br>
 * 
 * { In tabulation (bottom-up approach) we solve each subproblem. but in case of lookup (top-down) we solve only required subproblems } <br><br>
 * 
 * 1) For every next stage, we are using result of old stages. (tabulation) (bottom up)<br>
 * 2) for each index, we are examining all lower indexes. Current state can be part of either increasing sequence or decreasing sequence.<br>
 * so for each index (state), two results are stored:<br>
 * 	i)  0th if current index is taking ZigZag in up direction<br>  
 *  ii) 1st if current index is taking ZigZag in down direction<br>
 *  At each index and for each direction ,we are maintaining count of zigzag size and last index of sequence(to trace back)<br>
 * 3) while examining next state, we checks from all initial states from which this state can be reached. and if that lower state + current state make bigger sub-sequence, we update
 * array.<br>
 * 
 * </p>
 * @author saurabh.gupta
 * 
 */

public class ZigZag {
	public static class InnerStructure {
		int count = 0;
		int lastindex = 0;
		public InnerStructure (int c, int i) {
			count = c;
			lastindex = i;
		}
	}

	InnerStructure [][] LA = null;
	private void initialiseLookupArray(int size) {
		LA = new InnerStructure [size][2];
		
		for (int i = 0; i < size; i++) {
			LA[i][0] = new InnerStructure (1,i);
			LA[i][1] = new InnerStructure (1,i);
			// 0th index of 2 D array will denote UP array
			LA[i][0].count = 1;
			LA[i][0].lastindex = i;

			// 1st index of 2 D array will denote Down array
			LA[i][1].count = 1;
			LA[i][1].lastindex = i;
		}
	}

	public int zigzagsequence (int [] input) {
		initialiseLookupArray (input.length);

//		System.out.println ("Before Processing");
//		printLA ();
		for (int i = 1; i < input.length; i++) {
			for (int j = 0; j < i; j++) {
				if (input[j] > input[i]) {
					updateDownArray (i, j);
				}
				else if (input[j] < input[i]) {
					updateUpArray (i, j);
				}
			}
		}

//		System.out.println ("After Processing");
//		printLA ();
		int count = getMaxSequenceCount (input);
		return count;
	}

	private int getMaxSequenceCount(int[] input) {
		int max = 0;
		int maxIndex = 0;
		int lastDirection = 0; //0 UP, 1 down
		for (int i = LA.length -1; i >= 0; i--) {
			if (LA[i][0].count > max) {
				max = LA[i][0].count;
				maxIndex = i;
				lastDirection = 0;
			}
			if (LA[i][1].count > max) {
				max = LA[i][1].count;
				maxIndex = i;
				lastDirection = 1;
			}
		}
		printSequence (input, maxIndex, lastDirection);
		//System.out.println();
		printSequenceRecursive (input, maxIndex, lastDirection);
		System.out.println();
		return max;
	}

	@SuppressWarnings("unused")
	private void printLA() {
		for (int i = 0; i < LA.length; i++) {
			System.out.println(i + " --> {" + LA[i][0].count + " : " + LA[i][0].lastindex + " }," + " {" + LA[i][1].count + " : " + LA[i][1].lastindex + " },");
		}
	}
	
	private void printSequenceRecursive(int[] input, int maxIndex, int lastDirection) {
		if ( maxIndex == LA[maxIndex][lastDirection].lastindex ) {
			System.out.print(input[maxIndex] + ", ");
			return;
		}
		int oldMaxIndex = maxIndex;
		maxIndex = LA[maxIndex][lastDirection].lastindex;
		lastDirection = (lastDirection == 0) ? 1 : 0;
		printSequenceRecursive (input, maxIndex, lastDirection);
		System.out.print(input[oldMaxIndex] + ", ");
	}

	private void printSequence(int[] input, int maxIndex, int lastDirection) {
		for (int i = maxIndex; ;) {
			System.out.print(input[i] + ", ");
			if ( i == LA[i][lastDirection].lastindex )
				break;
			i = LA[i][lastDirection].lastindex;
			lastDirection = (lastDirection == 0) ? 1 : 0;
		}
		System.out.println ();
	}

	private void updateDownArray(int i, int j) {
		if ( (LA[j][0].count+1) > (LA[i][1].count)) {
			LA[i][1].count = LA[j][0].count+1;
			LA[i][1].lastindex = j;
		}
	}

	private void updateUpArray(int i, int j) {
		if ( (LA[j][1].count+1) > (LA[i][0].count)) {
			LA[i][0].count = LA[j][1].count+1;
			LA[i][0].lastindex = j;
		}
	}

	public static void main(String[] args) {
		int [][] input = {
				{10,8,5,6,5,9,8},
				{ 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 },
				{ 44 },
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 },
				{ 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 },
				{ 374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 
					600, 947, 978, 46, 100, 953, 670, 862, 568, 188, 
					67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 
					477, 245, 413, 109, 659, 401, 483, 308, 609, 120, 
					249, 22, 176, 279, 23, 22, 617, 462, 459, 244 },
		};
		
		ZigZag testObject = new ZigZag ();
		for (int i = 0; i < input.length; i++) {
			System.out.println("max subsequence size: " + testObject.zigzagsequence (input[i]));
			System.out.println();
		}
	}

}
