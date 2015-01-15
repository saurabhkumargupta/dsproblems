package com.code.saurabh.DS.graph.problems;

import java.util.HashSet;
import java.util.Set;


/*
 * COPIED (http://www.geeksforgeeks.org/forums/topic/word-ladder/)
 * Modified to work
 */
public class WordLadder {
	int minStep = Integer.MAX_VALUE;

	public int ladderLength( String start, String end, Set<String> dict ) {
		Set<String> visited = new HashSet<String>();
		minStep = Integer.MAX_VALUE;
		DFS( start, end, 0, visited, dict );
		if( minStep == Integer.MAX_VALUE ) 
			return -1;
		return minStep;
	}

	public void DFS( String start, String end, int step, Set<String> visited, Set<String> dict ) {
		if( start.equals(end) ) {
			minStep = Math.min( step, minStep );
			return;
		}
		char[] startArray = start.toCharArray();
		for( int i = 0; i < startArray.length; i++ ) {
			char original = startArray[i];
			for( char c = 'a'; c <= 'z'; c++ ) {
				if( c == original )
					continue;
				else {
					startArray[i] = c;
					String tmpStr = new String( startArray );
					if( dict.contains( tmpStr ) && !visited.contains( tmpStr ) ) {
						visited.add( tmpStr );
						DFS( tmpStr, end, step + 1, visited, dict );
						visited.remove( tmpStr );
					}
				}
			}
			startArray[i] = original;
		}
	}

	public static void main(String[] args) {
//		String [] input = {"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le",
//				"av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr", "sq"};
		String [] input = { "GOD", "GID","DID", "DIG", "DOG"};
		
		Set<String> dict = new HashSet<String> ();
		for (String str: input) {
			dict.add(str.toLowerCase());			
		}

		WordLadder wl = new WordLadder ();
//		System.out.println("Distance: " + wl.ladderLength("qa", "sq", dict));
		System.out.println("Distance: " + wl.ladderLength("GOD".toLowerCase(), "DOG".toLowerCase(), dict));
		System.out.println("Distance: " + wl.ladderLength("GOD".toLowerCase(), "GID".toLowerCase(), dict));
		System.out.println("Distance: " + wl.ladderLength("GOD".toLowerCase(), "GOD".toLowerCase(), dict));
		System.out.println("Distance: " + wl.ladderLength("GOD".toLowerCase(), "DDD".toLowerCase(), dict));
	}

}
