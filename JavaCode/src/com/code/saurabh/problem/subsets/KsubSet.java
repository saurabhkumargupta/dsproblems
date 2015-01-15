package com.code.saurabh.problem.subsets;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.code.saurabh.util.LinkedListUtil;

/**
 * <pre>
 * From a given String , create a subset of size K
 * These sets will be in lexicographical order
 * </pre>
 * @author saurabh.gupta
 *
 */
public class KsubSet {

	/**
	 * 
	 * <pre>
	 * To get List in lexicographical order, we have to start from 0th index.
	 *  
	 * 
	 * loop through array starting from ith index
	 * for each i in loop 
	 * 	call this function recursively with start index i+1 and required substring size K-1
	 * 	append ith character at start of each string returned in previous recursive call.
	 * 	this will get another List of String which is saved and will be retuned from this recursive call. 
	 * </pre>
	 * @param input given String
	 * @param i start index of given String
	 * @param k size of subset to be created
	 * @return List of strings of size k
	 */
	static List<String> k_subset (String input, int currentIndex, int subsetsize) {
		List<String> result = new LinkedList <String> ();
		if (subsetsize <= 0) {
			//This empty string is added in intermediate result because it will make easy to create size 1 substring
			//getNewList function will simply append new character in null string to create size 1 substring
			result.add("");
			return result;
		}
		
		for (; currentIndex <= (input.length() - subsetsize); currentIndex++) {
			List<String> subresult = new LinkedList<String> ();
			
			//Current Index is fixed, and recursively this function is called with next index and 
			// subsetsize -1.
			//Which basically means creating all string in lexicographical oder, starting from index+1 and 
			//size of subsetsize -1.
			subresult = k_subset (input, currentIndex+1, subsetsize-1);
			
			//Current Index is appended at beginning of all strings in sub-result.
			//This creates another linkedList of sub-result which is returned.
			subresult = getNewList (input.charAt(currentIndex), subresult);
			result.addAll(subresult);
		}
		return result;
	}

	/**
	 * <pre>
	 * Function iterates all string and add this new character at the start of each string.
	 * These new strings are now part of result which will be returned.
	 * i.e.: if input linkedList is:
	 * 	 {bc ->  bd ->  be} and char 'a', then result LinkedList will be:
	 * 	{abc -> abd -> abe}
	 * </pre>
	 * @param newMember character to be inserted in each string
	 * @param subresult LinkedList of Strings
	 * @return new LinkedList of Strings
	 */
	static private List<String> getNewList(char newMember, List<String> subresult) {
		List<String> result = new LinkedList <String> ();
		Iterator<String> iter = subresult.iterator();
		while (iter.hasNext()) {
			String str = iter.next();
			String newString = newMember + str;
			result.add(newString);
		}
		
		return result;
	}
	public static void main(String[] args) {
		
		String[][] input = {
				{"abc", "1"},
				{"abc", "2"},
				{"abc", "3"},
				{"abcd", "4"},
				{"abcd", "2"},
				{"abcd", "3"},
				{"ABCDEFGH", "3"},
		};

		List<String> result = null;
		for (int i = 0; i < input.length; i++) {
			String str = input[i][0];
			int k = Integer.valueOf(input[i][1]);

			result = k_subset (str, 0, k);
			
			System.out.println ("Input: " + str + ", k = " + k + ", result Size: " + result.size());
			LinkedListUtil.printLinkedList (result);
		}
		
		powerSet (input[0][0]);
		powerSet (input[2][0]);
	}


	/**
	 * <pre>
	 * </pre>
	 * @param string
	 */
	private static void powerSet(String string) {
		List<String> result = new LinkedList<String> ();
		for (int i = 0; i <= string.length(); i++) {
			List<String> subresult = k_subset (string, 0, i);
			result.addAll(subresult);
		}
		System.out.println ("Power Set for : " + string);
		LinkedListUtil.printLinkedList (result);
	}

}
