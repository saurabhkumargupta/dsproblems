package com.code.saurabh.problem.subsets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PowerSet {

	static Set<HashSet<Integer>> powerSet = new HashSet<HashSet<Integer>> ();
	static int recursionCount = 0;
/** powerSetUsingBitWiseOperation () : Just print
 * and
 * powerSetUsingBitWiseOperationSet ( ) : Store in set and print
 * works for N<31, since 231 is not an "int" anymore, it's too big. We could use "long long" (that's "long" in Java) variables, because
 * they offer more bits. But think about it. The loop runs for 2N iterations and with N=31 this will be more than 2 billion times. 
 * That will time out anyway, even if you do nothing inside the loop. Conclusion: The N<31 constraint for this method is irrelevant, 
 * for higher limits we need a totally different approach anyway.
 * @param n
 */
	public static void powerSetUsingBitWiseOperation (int[] n) {
		for (int i = 0; i < (1<<n.length) ;i ++) {
			System.out.println();
			System.out.print ("{");
			for (int bit = 0; bit < n.length; bit++) {
				if ((i & (1 << bit)) == 0) {
					System.out.print(" ,");
				}
				else {
					System.out.print(n[bit] + ",");
				}
			}
			System.out.print("}");
		}
	}

	public static void powerSetUsingBitWiseOperationSet (int[] n) {
		
		Set<Set<Integer>> sets = new HashSet<Set<Integer>> ();
		int count = 0;
		for (int i = 0; i < (1<<n.length) ;i ++) {
			System.out.println();
			
			Set<Integer> set = new HashSet<Integer> ();
			for (int bit = 0; bit < n.length; bit++) {
				if ((i & (1 << bit)) != 0) {
					set.add(n[bit]);
				}
			}
			sets.add(set);
			System.out.print("{");
			Iterator<Integer> iter = set.iterator();
			while (iter.hasNext()) {
				Integer v = iter.next();
				System.out.print(v + ",");
			}
			System.out.print("}");
			count++;
		}
		System.out.println();
		System.out.println("Total Count: " + count);
	}

	/**
	 * With recursion, at each element, we decide whether we have to include that element or not.
	 * And this result two sets at each point, one with having that element and another with excluding that element.
	 * And these set sets further dive into recursion.
	 * @param i
	 * @param input
	 * @param currentSet
	 */
	public static void powerSetUsingRecusion (int i, int[] input, HashSet<Integer> currentSet) {
		if (i >= input.length) {
			recursionCount++;
			powerSet.add(currentSet);
			return;
		}
		HashSet<Integer> newSet = new HashSet<Integer> ();
		newSet.addAll(currentSet);
		newSet.add(input[i]);
		powerSetUsingRecusion (i+1, input, currentSet);
		powerSetUsingRecusion (i+1, input, newSet);
	}

	/**
	 * <pre>
	 * At each new element, we create a subset with all previous subsets.
	 * 
	 * At each ith element, we have already stored all possible subsets which can be created using i-1 elements.
	 * This new ith element will add itself with all previous sets and this will create a new set of sets. And this is added to 
	 * powerset which will be used in next iteration.
	 * </pre>
	 * @param input
	 */
	public static void powerSetDP (int[] input) {
		HashSet<Integer> emptySet = new HashSet<Integer> ();
		powerSet.add(emptySet);
		for (int i = 0; i < input.length; i++) {
			addNewElementInAllPreviousSubset (input[i]);			
		}
		//System.out.println("Total Sets are: " + powerSet.size());
	}

	private static void addNewElementInAllPreviousSubset(int v) {
		Iterator<?> outerIterator = powerSet.iterator();
		Set<HashSet<Integer>> tempSet = new HashSet<HashSet<Integer>> ();
		while (outerIterator.hasNext()) {
			@SuppressWarnings("unchecked")
			HashSet<Integer> innerSet = (HashSet<Integer>)outerIterator.next();
			HashSet<Integer> newSet = new HashSet<Integer> ();
			newSet.addAll(innerSet);
			newSet.add(v);
			tempSet.add(newSet);
		}
		powerSet.addAll(tempSet);
	}

	public static void main(String[] args) {
//		int [] input = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		int [] input = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};

		//powerSetUsingBitWiseOperationSet (input);
		//powerSetUsingBitWiseOperation (input);

		long lStartTime = System.nanoTime();
		//start with empty set
		powerSetUsingRecusion (0, input, new HashSet<Integer> ());

		//powerSetDP (input);
		long lEndTime = System.nanoTime(); 
		long difference = lEndTime - lStartTime;
		System.out.println("Elapsed milliseconds: " + difference/1000000);
		System.out.println("Total Sets are: " + powerSet.size());
		//printPowerSet ();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private static void printPowerSet() {
		Iterator<?> outerIterator = powerSet.iterator();
		System.out.println("Total Sets are: " + powerSet.size() + ", recursionCount: "+ recursionCount);
		while (outerIterator.hasNext()) {
			HashSet<Integer> innerSet = (HashSet<Integer>)outerIterator.next();
			Iterator<?> innerIterator = innerSet.iterator();
			System.out.print("{");
			while (innerIterator.hasNext()) {
				System.out.print((Integer)innerIterator.next() + ",");
			}
			System.out.print("}");
			System.out.println();
		}
	}

}
