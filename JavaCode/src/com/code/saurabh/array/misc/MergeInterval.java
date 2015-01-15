package com.code.saurabh.array.misc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.code.saurabh.util.IntPair;

/*
 * 
 * 
 * Original Questions
 * 	http://www.programcreek.com/2012/12/leetcode-merge-intervals/
	http://www.geeksforgeeks.org/merging-intervals/


Similar Question:
http://www.careercup.com/question?id=12696710
You are given n variable length sets with each set like set1: [s1.....e1], set2:[s2.....e2] with the condition that the sets overlap
 (i.e. if you represent them on number line, they intersect). Now you have to remove the minimum number of sets from here so that the
  remaining sets are disjoint. 

For example you have set S1, S2, S3 with S1 and S3 disjoint and S2 overlapping both S1 and S3 then we remove S2 to get the answer.
 */
public class MergeInterval {

	static class PairComparator implements Comparator<IntPair> {
		public int compare(IntPair i1, IntPair i2) {
			return i1.getFirst() - i2.getFirst();
		}
	}
	
	static void mergeInterval (List<IntPair> intervals) {
		Collections.sort(intervals, new PairComparator());

		Iterator<IntPair> iter = intervals.iterator();
		IntPair PrevInterval = iter.next();
		while (iter.hasNext()) {
			IntPair CurrInterval = iter.next();
			if (PrevInterval.getSecond() >= CurrInterval.getFirst()) { //Remove = to restrict it with only overlapping ones Testcase 4
				if (PrevInterval.getSecond() < CurrInterval.getSecond()) {
							PrevInterval.setSecond(CurrInterval.getSecond());
				}
			}
			else {
				System.out.print("{" + PrevInterval.getFirst() + "," + PrevInterval.getSecond() + "}, ");
				PrevInterval = CurrInterval;
			}
		}
		System.out.print("{" + PrevInterval.getFirst() + "," + PrevInterval.getSecond() + "}, ");
	}
	
	public static void main(String[] args) {
		IntPair [][] input = {
				{new IntPair(1,3), new IntPair(2,6), new IntPair(8,10), new IntPair(15,18)},
				{new IntPair(1,3), new IntPair(2,4), new IntPair(5,7), new IntPair(6,8)},
				{new IntPair(6,8), new IntPair(1,9), new IntPair(2,4), new IntPair(4,7) },
				{new IntPair(6,8), new IntPair(1,3), new IntPair(2,4), new IntPair(4,7) },
				{new IntPair(1,3), new IntPair(7,9), new IntPair(4,6), new IntPair(10,13)},
		};
		
		for (int i = 0; i < input.length; i++) {
			mergeInterval (Arrays.asList(input[i]));
			System.out.println();
		}
	}

}
