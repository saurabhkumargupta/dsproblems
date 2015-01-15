package com.code.saurabh.util;

public class IntPair extends Pair<Integer, Integer> {

	public IntPair (int f, int s) {
		super (f,s);
	}

	public IntPair() {
		super();
	}

	public static void main(String[] args) {

		IntPair pair = new IntPair(1,2);
		System.out.println("First: " + pair.getFirst() + ", Second: " + pair.getSecond() + ", String: " + pair.toString());
		pair.setFirst(5);
		pair.setSecond(7);
		System.out.println("First: " + pair.getFirst() + ", Second: " + pair.getSecond() + ", String: " + pair.toString());
	}

}
