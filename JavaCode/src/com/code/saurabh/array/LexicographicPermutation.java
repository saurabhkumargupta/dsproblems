/**
 * 
 */
package com.code.saurabh.array;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.code.saurabh.util.LinkedListUtil;

/**
 * @author saurabh.gupta
 *
 */
public class LexicographicPermutation {

	static List<String> lexico_per (String input) {
		List <String> result = new LinkedList<String> ();
		if (input.length() == 0) {
			result.add("");
			return result;
		}
		
		for (int i = 0; i < input.length(); i++) {
			String newString = input.substring(0,i) + input.substring(i+1, input.length());
			List<String> subresult = lexico_per (newString);
			subresult = addCharToStartOfAll (input.charAt(i), subresult);
			result.addAll(subresult);
		}
		return result;
	}
	/**
	 * <pre>
	 * </pre>
	 * @param charAt
	 * @param subresult
	 * @return
	 */
	static private List<String> addCharToStartOfAll(char charAt, List<String> subresult) {
		List<String> result = new LinkedList<String> ();
		Iterator<String> iter = subresult.iterator();
		while (iter.hasNext()) {
			String str = iter.next();
			String newString = charAt + str;
			result.add(newString);
		}
		return result;
	}
	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		String[] input = {
				"abc",
				"abcd",
				"ABCDE",
				};

		List<String> result = null;
		for (int i = 0; i < input.length; i++) {
			String str = input[i];
			result = lexico_per (str);
			
			System.out.println ("Input: " + str + ", result Size: " + result.size());
			LinkedListUtil.printLinkedList (result);
		}
	}

}
