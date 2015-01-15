package com.company.linkedin;
/*
 * Given two (dictionary) words as Strings, determine if they are isomorphic. Two words are called isomorphic if the letters in one word can be remapped to get the second word. Remapping a letter means replacing all occurrences of it with another letter while the ordering of the letters remains unchanged. No two letters may map to the same letter, but a letter may map to itself. 
Example:
	i.   Given "foo", "app"; returns true, we can map 'f' -> 'a' and 'o' -> 'p'
	ii.  Given "bar", "foo"; returns false, we can't map both 'a' and 'r' to 'o'
	iii. Given "turtle", "tletur"; returns true, we can map 't' -> 't', 'u' -> 'l', 'r' -> 'e', 'l' -> 'u', 'e' -'r'
	iv. Given "ab", "ca"; returns true, we can map 'a' -> 'c', 'b
 */
public class Isomorphic {

	static boolean isomorphic (String w1, String w2) {
		if (w1.length() != w2.length()) {
			return false;
		}
		
		int index = 0;
		char[] temp = new char [26];
		for (int c = 0; c< temp.length; c++) {
			temp[c] = '$';
		}
		while (index < w1.length()) {
			char f = w1.charAt(index);
			char s = w2.charAt(index);
			if (temp[s-'a'] == '$') {
				temp[s-'a'] = f;
			}
			else if (temp[s-'a'] != f) {
				return false;
			}
			index++;
		}
		return true;
	}
	public static void main(String[] args) {
		String[][] input = {
				{"foo", "app"},
				{"bar", "foo"},
				{"turtle", "tletur"},
				{"ab", "ca"}
		};
		
		for (int i = 0; i < input.length; i++) {
			String w1 = input[i][0];
			String w2 = input[i][1];
			System.out.println("Result of " + w1 + " and " + w2 + ": " + isomorphic (w1, w2));
		}
	}

}
