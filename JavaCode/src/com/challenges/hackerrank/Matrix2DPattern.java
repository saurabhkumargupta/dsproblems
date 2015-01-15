/*
 * https://www.hackerrank.com/challenges/the-grid-search
 * 
 */
package com.challenges.hackerrank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author saurabh.gupta
 *
 */
public class Matrix2DPattern {
	public static class RollingHash {
		int size;
		long mod_value;
		int base;
		long hash;

		public RollingHash (int size, long mod, int base) {
			this.size = size;
			this.mod_value = mod;
			this.hash = 0;
			this.base = base;
		}
		
		public RollingHash (int size, int base) {
			this (size, 13, base);
		}

		public RollingHash (int size) {
			this (size, 13, 10);
		}

		long add (int C) {
			hash = mod(hash*base + Integer.valueOf(C), mod_value);
			return hash;
		}

		long createHash (int[] str, int s, int e) {
			for (int i = s; i < e; i++) {
				hash = add(str[i]);
			}
			return hash;
		}

		long createHash (int[] str) {
			return createHash (str, 0, str.length);
		}

		long createHash (String str) {
			for (int i = 0; i < str.length(); i++) {
				hash = add(str.charAt(i));
			}
			return hash;
		}

		long update (char remove, char add) {
			long E = mod(powerWithMode(base, size-1), mod_value);
			long D = mod(remove * E, mod_value);
			long C = hash - D;
			long B = mod(base * C, mod_value);
			long A = B + add;
			hash = mod(A, mod_value);
			return hash;
		}
		
		long powerWithMode (long x, long y) {
			long ret = 1;
			for (int i = 0; i < y; i++) {
				ret = ret * x;
				ret = mod (ret, mod_value);
			}
			return mod (ret, mod_value);
		}

		long mod (long l, long b) {
			return (l % b + b) % b;
		}
		
		long getHash () {
			return hash;
		}
	}

	static boolean equalArray (int[] s, int[] d, int d_s, int d_e) {
		if (s.length != (d_e-d_s)) {
			return false;
		}
		for (int i = d_s; i < d_e-d_s; i++) {
			if (s[i] != d[d_s+i]) {
				return false;
			}
		}
		return true;
	}
	
	static int[] createSubarray (int [] input, int s, int e) {
		int l = e -s;
		int [] newarray = new int[l];
		for (int i = 0; i < l; i++) {
			newarray[i] = input[i+s];
		}
		return newarray;
	}

	static int RabinKarpAlgo (int[] input, int i_s, int i_e, int input_row, int[] pattern, int pattern_row, boolean fixlength) {
		int IL = i_e - i_s;
		int PL = pattern.length;

		if (IL < PL) {
			return -1;
		}

		RollingHash rhp = patternRollingHash (pattern, pattern_row);
		RollingHash rht = inputRollingHash (input, i_s, PL, input_row);
		long hp = rhp.getHash();
		long ht = rht.getHash();
		if (hp == ht) {
			if (equalArray (pattern, input, i_s, i_s+PL)) {
				return i_s;
			}
		}
		
		if (fixlength) {
			return -1;
		}
		for (int i = i_s+1; i <= (IL-PL); i++) {
			ht = rht.update((char)(input[i-1]), (char)(input[i+PL-1]));
			Point oldkey = new Point (input_row, i-1);
			inputHash.remove(oldkey);
			Point newkey = new Point (input_row, i);
			inputHash.put(newkey, rht);
			if (hp == ht) {
				if (equalArray(pattern,input,i, i+PL)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	private static RollingHash inputRollingHash(int[] input, int i_s, int length, int input_row) {
		Point p = new Point(input_row, i_s);
		if (inputHash.containsKey(p)) {
			return inputHash.get(p);
		}

		if (i_s != 0) {
			Point lastp = new Point(input_row, i_s-1);
			if (inputHash.containsKey(lastp)) {
				RollingHash rh = inputHash.get(lastp);
				rh.update((char)(input[i_s-1]), (char) (input[i_s+length-1]));
				return rh;
			}
		}
		RollingHash pat = new RollingHash (length, 1007L, 257);
		pat.createHash(input, i_s, i_s+length);
		inputHash.put(p, pat);
		return pat;
	}

	private static RollingHash patternRollingHash(int[] pattern, int row) {
		Point p = new Point(row, 0);
		if (patternHash.containsKey(p)) {
			return patternHash.get(p);
		}
		else {
			System.out.println("Something wrong");
		}
		RollingHash pat = new RollingHash (pattern.length, 1007L, 257);
		pat.createHash(pattern);
		return pat;
	}

	static class Point {
		Integer x;
		Integer y;
		Point (Integer i, Integer j) {
			this.x = i;
			this.y = j;
		}

		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}

	static Map <Point, RollingHash> patternHash = new HashMap<Point, RollingHash> ();
	static Map <Point, RollingHash> inputHash = new HashMap<Point, RollingHash> ();

	private static void preparePatternHashMap(int[][] pattern) {
		for (int i = 0; i < pattern.length; i++) {
			RollingHash pat = new RollingHash (pattern[0].length, 1007L, 257);
			pat.createHash(pattern[i]);
			patternHash.put(new Point(i, 0), pat);
		}
	}

	static boolean match (int[][] input, int[][] pattern) {
		
		patternHash.clear();
		inputHash.clear();
		preparePatternHashMap (pattern);
		
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				int index = RabinKarpAlgo (input[i], j, input[i].length, i, pattern[0], 0, false);
				if (index != -1) {
					boolean ret = processRemainigPattern (input, pattern, i+1, index);
					if (ret == true) {
						return true;
					}
					j = index;
				}
			}
		}
		return false;
	}
	
	static boolean match_1 (int[][] input, int[][] pattern) {

		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				boolean ret = localMatch (input, pattern, i, j);
				if (ret == true) {
					return true;
				}
			}
		}
		return false;
	}
	
	static boolean localMatch (int[][] input, int[][] pattern, int x, int y) {
		for (int i = 0; i < pattern.length; i++, x++) {
			if (x >= input.length) {
				return false;
			}
			int inputcol = y;
			for (int j = 0; j < pattern[0].length; j++, inputcol++) {
				if (inputcol >= input[0].length) {
					return false;
				}
				if (input[x][inputcol] == pattern[i][j]) {
					continue;
				}
				else
					return false;
			}
		}
		return true;
	}

	static boolean processRemainigPattern(int[][] input, int[][] pattern, int inputRowCount, int j) {
		if (inputRowCount >= input.length) {
			return false;
		}

		for (int patternrow = 1; patternrow < pattern.length; patternrow++) {
			int index = RabinKarpAlgo (input[inputRowCount], j, input[inputRowCount].length, inputRowCount, pattern[patternrow], patternrow, true);
			if (index == -1) {
				return false;
			}
			inputRowCount++;
		}
		return true;
	}
	
	public static void main(String[] args) {
		readInput ();
	}
	
	static void readInput () {
//		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
//		String file = "/Users/saurabh.gupta/Documents/Personal/Interview/Round_2_preparation_material/JavaCode/Inputs/GridInput";
		String file = "/Users/saurabh.gupta/Documents/Personal/Interview/Round_2_preparation_material/JavaCode/Inputs/input04.txt";
		BufferedReader br = null;
		try {
			br = new BufferedReader (new FileReader (file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		int[][] input = null;
		try {
			Integer TC = Integer.valueOf(br.readLine().trim());
			for (int t = 0; t < TC; t++) {
				long startime = System.currentTimeMillis();
				String[] InputRC = br.readLine().trim().split(" ");
				int R = Integer.valueOf(InputRC[0]);
				int C = Integer.valueOf(InputRC[1]);

				input = inputArrayFromStdin (br, R , C);

				String[] PatternRC = br.readLine().trim().split(" ");
				int r = Integer.valueOf(PatternRC[0]);
				int c = Integer.valueOf(PatternRC[1]);

				int [][] pattern = inputArrayFromStdin (br, r, c);
//				boolean ret = match (input, pattern); // Ferzi test cases
				boolean ret = match_1 (input, pattern);
				if (ret == true) {
					System.out.println("YES");
				}
				else{
					System.out.println("NO");
				}
				long endtime = System.currentTimeMillis();
				System.out.println("Running time: " + (endtime-startime));
			}
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	static public int[][] inputArrayFromStdin (BufferedReader br, int row, int col) throws IOException {
		return inputArrayFromStdin (br, "", row, col);
	}
	
	static public int[][] inputArrayFromStdin (BufferedReader br, String separator, int row, int col) throws IOException {
		int[][] arr = new int[row][col];
		
		for (int r = 0; r < row; r++) {
			String[] input = br.readLine().trim().split(separator);
			int index = 0;
			for (int i = 0; i < input.length && index < col; i++) {
				String s = input[i];
				if (!s.equals(" ") && !s.isEmpty()) {
					arr[r][index] = Integer.valueOf(s.trim()) + '0';
					index++;
				}
			}
		}
		return arr;
	}
}
