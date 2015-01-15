package com.code.saurabh.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 * Just like powerSetDP, we are creating a new sets of string with every new element
 * 
 * But in powerSet, we use all previous created sets and this new element is added to all that old sets.
 * 
 * But in this permutation, this new element is added only to one set. And this set has elements whose string length is 1 less than current input size.
 * if current element index is 3, then input length till this index will be 4. So permutation calculation will use subset whose elements size is 3.
 * Thus, in permutation calculation, all old sets can be discarded as they are not required.
 * </pre>
 * @author saurabh.gupta
 *
 */
public class AllPermutationOfString {

	/**
	 * 
	 * <pre>
	 * At every ith index, we are using all subsets created upto (i-1)th index.
	 * this ith index element generates a new set of string with each of previous subsets
	 * example:
	 * a,b,c (0,1,2 indexes)
	 * at index 2, there will available sets
	 * size : sets of given size
	 *   1  :  {a} {b}
	 *   2  :  {a,b} {b,a}
	 * 
	 * at index 2, we will use all elements of set whose size is 2. here it will be {a,b} {b,a}
	 * This new element c, will be inserted at each position of {a,b} and then each position of {b,a}
	 * this will create: {{c,a,b} {a,c,b} {a,b,c}, {c,b,a} {b,c,a} {b,a,c}}
	 * 
	 * </pre>
	 * @param input
	 * @return
	 */
	public static Map<Integer, Set<String>> getPerm_1 (String input) {
		Map<Integer, Set<String>> result = new HashMap <Integer, Set<String>> ();
		
		for (int i = 0; i < input.length(); i++) {
			Integer size = i;
			Set<String> subResult = result.get(size);
			Set<String> newResult = new HashSet<String> ();
			
			if (subResult != null) {
				Iterator<String> iter = subResult.iterator();
				Set<String> localResult = null;
				while (iter.hasNext()) {
					String str = iter.next();
					localResult = generateNewSet (str, input.charAt(i));
					newResult.addAll(localResult);
				}
			}
			else {
				String str = "" + input.charAt(i);
				newResult.add(str);
			}
			result.put(size+1, newResult);
		}
		return result;
	}

	/**
	 * 
	 * <pre>
	 * same as before, just using from back-side
	 * </pre>
	 * @param input
	 * @return
	 */
	public static Map<Integer, Set<String>> getPerm (String input) {
		Map<Integer, Set<String>> result = new HashMap <Integer, Set<String>> ();
		
		for (int i = input.length()-1; i >= 0; i--) {
			Integer size = input.length() - i;
			Set<String> subResult = result.get(size-1);
			Set<String> newResult = new HashSet<String> ();
			
			if (subResult != null) {
				Iterator<String> iter = subResult.iterator();
				Set<String> localResult = null;
				while (iter.hasNext()) {
					String str = iter.next();
					localResult = generateNewSet (str, input.charAt(i));
					newResult.addAll(localResult);
				}
			}
			else {
				String str = "" + input.charAt(i);
				newResult.add(str);
			}
			result.put(size, newResult);
		}
		return result;
	}

	/**
	 * <pre>
	 * newMember is inserted at each location of given string. And each new string is inserted into result set.
	 * e -> bc -> ebc (at start)
	 * e -> bc -> bec (in middle)
	 * e -> bc -> bce (at end)
	 * 
	 * d -> abc -> dabc (at start)
	 * d -> abc -> adbc (all middle positions)
	 * d -> abc -> abdc (all middle positions)
	 * d -> abc -> adcd (end) 
	 * 
	 * So total input.length + 1 strings can be created
	 * A = length -1 place in-between of given string
	 * B = 1 place at start
	 * C = 1 place at end
	 * total : A + B +C = (length -1) + 1 + 1 = length + 1
	 * 
	 * </pre>
	 * @param input A input string is given like "bc"
	 * @param newMember: a new character (say 'e' ) is given which needs to be inserted at each place of given string
	 * @return set of all new String (here exa: ebc,bec,bce)
	 */
	private static Set<String> generateNewSet(String input, char newMember) {

		Set<String> result = new HashSet<String> ();
		String newString = null;
		for (int i = 0; i < input.length(); i++) {
			if (i == 0) {
				newString = newMember + input;
			}
			else {
				newString = input.substring(0, i) + newMember + input.substring(i, input.length());
			}
			result.add(newString);
		}
		newString = input + newMember;
		result.add(newString);
		return result;
	}
	public static void main(String[] args) {
		testCases ();
	}

	private static void testCases() {

		Map<Integer, Set<String>> result = null;
		String [] input = {
				"abcd",
				"abc",
				"a",
				"ab",
				"",
		};
		
		for (int i = 0; i < input.length; i++) {
			result = getPerm_1 (input[i]);
			printResult (input[i], result);
		}		
	}

	private static void printResult(String input, Map<Integer, Set<String>> result) {
		Iterator<Integer> keys = result.keySet().iterator();

		System.out.println("Input: " + input);
		while (keys.hasNext()) {
			int size = keys.next();
			Set<String> values = result.get(size);
			System.out.println("Size: " + size + ", Count: " + values.size());
			for (String str: values) {
				System.out.print(str + ", ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
}
