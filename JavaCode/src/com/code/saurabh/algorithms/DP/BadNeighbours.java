package com.code.saurabh.algorithms.DP;
/**
 * http://community.topcoder.com/stat?c=problem_statement&pm=2402&rd=5009
 * 
 * @author saurabh.gupta
 *
 */
public class BadNeighbours {
	class LookupStructure {
		int startIndex;
		int lastIndex;
		int totalsum;

		LookupStructure (int start, int last, int sum) {
			startIndex = start;
			lastIndex = last;
			totalsum = sum;
		}
	}

	boolean isValidNeighbour (int i, int j, int lastIndex) {

		if (j+1 == i) {
			return false;
		}
		if (j == 0 && i == lastIndex) {
			return false;
		}
		return true;
	}

	public int maxDonations (int [] donations) {
		int maxSoFar = 0;
		@SuppressWarnings("unused")
		int maxIndex = 0;

		LookupStructure[] LA = new LookupStructure [donations.length];
		initLookupArray (LA, donations);

		maxIndex = 0;
		maxSoFar = donations[0];

		for (int i = 1; i < donations.length; i++) {
			for (int j = 0; j < i; j++) {
				if (isValidNeighbour (i, j, donations.length -1) ) {

					/*Update current neighbor's donation with best possible donation using previous neighbors donations*/
					if ( (LA[j].totalsum + donations[i]) > LA[i].totalsum) {
						LA[i].startIndex = LA[j].startIndex;
						LA[i].lastIndex = j;
						LA[i].totalsum = LA[j].totalsum + donations[i];
					}

				}

				if ( (i == donations.length -1) && j != 0) {
					if (LA[i].startIndex == 0) {
						int sum = LA[i].totalsum - donations[0];
						if (sum > maxSoFar) {
							maxSoFar = sum;
							LA[i].totalsum = maxSoFar;
						}
						else
							LA[i].totalsum = sum;
					}
				}
			}
			if (LA[i].totalsum > maxSoFar) {
				maxSoFar = LA[i].totalsum;
				maxIndex = i;
			}
		}
		//printLookupArray (LA);
		return maxSoFar;
	}

	@SuppressWarnings("unused")
	private void printLookupArray(LookupStructure[] lA) {

		for (int i = 0; i < lA.length; i++) {
			System.out.println (lA[i].startIndex + ", " + lA[i].lastIndex + ", " + lA[i].totalsum);
		}
	}

	private void initLookupArray(LookupStructure[] lA, int[] donations) {
		for (int i = 0; i < donations.length; i++) {
			lA[i] = new LookupStructure (i, i, donations[i]);
		}
	}

	public static void main(String[] args) {
		int [][] input = {
				{ 10, 3, 2, 5, 7, 8 },
				{ 11, 15 },
				{ 7, 7, 7, 7, 7, 7, 7 },
				{ 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 },
				{ 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,  
					6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
					52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72 },
		};

		BadNeighbours testObject = new BadNeighbours ();
		for (int i = 0; i < input.length; i++) {
			System.out.println("max donations: " + testObject.maxDonations (input[i]));
			System.out.println();
		}
	}

}
