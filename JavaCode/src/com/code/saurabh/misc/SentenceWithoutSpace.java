/**
 * 
 * There is a sentence that your friend knows, but while giving it to you, he lost all the spaces.
 * You have a dictionary with you. How would you reconstruct the original sentence using it?
 */
package com.code.saurabh.misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author saurabh.gupta
 *
 */
public class SentenceWithoutSpace {

	/*
	 * This method using Dynamic Programming to store result of already processed substrings
	 */
	static String getSentence_1 (String input, Set<String> dict, Map<String, String> tempMap){
	    if (dict.contains(input)) {
	        return input;
	    }

	    if (tempMap.containsKey(input)) {
	        return tempMap.get(input);
	    }
	    
	    if (input.isEmpty()) {
	        return "";
	    }
	    
	    String output = "";
	    
	    for (int i = 1; i < input.length(); i++) {
	        String temp = input.substring (0, i);
	        if (dict.contains (temp)) {
	            String str = getSentence_1 (input.substring(i), dict, tempMap);
	            tempMap.put(input.substring(i), str);
	            if (!str.isEmpty()) {
	                output = temp + " " + str;
	                break;
	            }
	        }
	    }
	    
	    return output;
	}
	
	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	
	static String getSentence (Set<String> dict, String search, String output) {
		if (dict.contains(search)) {
			return search;
		}
		if (search.isEmpty()) {
			return "";
		}
		
		for (int i = 1; i < search.length(); i++) {
			String substring = search.substring(0, i);
			if (dict.contains(substring)) {
				String str = getSentence(dict , search.substring(i), "");
				if (!str.isEmpty()) {
					output = substring + " " + str;
					break;
				}
			}
		}

		return output;
	}
	public static void main(String[] args) {
		Set<String> dictionary = new HashSet<String> ();
		dictionary.add("abc");
		dictionary.add("efg");
		dictionary.add("de");
		dictionary.add("def");
		dictionary.add("fgh");
		dictionary.add("gh");
		
		String search = "abcdefgh";
		System.out.println("output: -> " + SentenceWithoutSpace.getSentence(dictionary, search, ""));

		Map<String, String> temp = new HashMap<String, String> ();
		dictionary.add("a");
		dictionary.add("aa");
		dictionary.add("aaa");
		dictionary.add("aaaa");
		search = "aaaaaaaaa";
		System.out.println("output: -> " + SentenceWithoutSpace.getSentence_1(search, dictionary, temp));
	}

}
