package com.code.saurabh.array.misc;

import com.code.saurabh.util.Pair;

//NOT WORKING FOR ALL
public class RearrangeString {

	static Pair<Integer, Integer> arrage (char[] str, int s, int e) {
		if (e-s == 2) {
			return new Pair<Integer, Integer> (s, e-1);
		}

		int mid = s + (e-s)/2;

		Pair<Integer, Integer> left = arrage (str, s, mid);
		Pair<Integer, Integer> right = arrage (str, mid, e);
		
		convert (str, left.getSecond(), right.getFirst());
		return new Pair<Integer, Integer> (left.getFirst(), right.getFirst());
	}
	
	static void convert (char[] str, int first, int second) {
		int size =  second-first;
		while (size > 0) {
			char t = str[first];
			str[first] = str[second];
			str[second] = t;
			first++;
			second++;
			size--;
		}
	}
	public static void main(String[] args) {
		char[]  input = {'a', '1', 'b', '2', 'c', '3', 'd', '4', 'e', '5', 'f', '6', 'g', '7', 'h', '8', 
				'a', '1', 'b', '2', 'c', '3', 'd', '4', 'e', '5', 'f', '6', 'g', '7', 'h', '8'};
//		char[]  input = {'a', '1', 'b', '2', 'c', '3', 'd', '4'};
		System.out.println();
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i]);
		}
		arrage (input, 0, input.length);
		System.out.println();
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i]);
		}
	}

}
