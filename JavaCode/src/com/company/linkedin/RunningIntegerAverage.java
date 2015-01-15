package com.company.linkedin;

import java.util.HashMap;
import java.util.Map;

import com.code.saurabh.datastructure.linklist.DoublyLinkedList;

/*
 * You have bunch of numbers coming in, and a given a window size, how would you save numbers 
 * so that you can return number if present in window and provide average for current window.
 * 
 * Storing in HashMap and DLL,
 * This can be optimised for removing un-necessary entries from hashMap
 */
public class RunningIntegerAverage {

	Map<Integer, Integer> m = new HashMap<Integer, Integer> ();
	int W = 5;
	int T = 0;
	int A = 0;
	DoublyLinkedList dll = new DoublyLinkedList ();

	double searchAverage (int N) {
		int oldIndex = -1;
		T++;
		if (m.containsKey(N)) {
			oldIndex = m.get(N);
		}
		m.put(N, T);
		dll.add(N);

		if (oldIndex != -1 && (T-oldIndex) < W)
			System.out.println("Number: " + N + " exist in current window at position " + oldIndex);
		if (T <= W) {
			A = A + N;
			return (double)A/T;
		}
		else {
			int P = (int) dll.removeLast();
			A = (A - P) + N;
			return (double)A/W;
		}
	}
	public static void main(String[] args) {
		RunningIntegerAverage ria = new RunningIntegerAverage ();
		int [] input = {10,5,4,11,25,15,4,5,15,10,1,4,6};
		for (int i = 0; i < input.length; i++) {
			System.out.println("Average After " + input[i] + " : " + ria.searchAverage(input[i]));
		}
	}

}
