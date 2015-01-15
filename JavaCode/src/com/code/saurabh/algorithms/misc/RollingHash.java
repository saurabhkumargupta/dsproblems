/**
 * 
 */
package com.code.saurabh.algorithms.misc;


/**
 *  <pre>
 *  <a href="http://courses.csail.mit.edu/6.006/spring11/rec/rec06.pdf/">
 *  http://courses.csail.mit.edu/6.006/spring11/rec/rec06.pdf
 *  </a>
 *  
 *  General Rolling Hash function for string s size M and base B and modulo Q: 
 *  h = ( s[0] * B ^ (M-1) + s[1] * B ^ (M-2) + s[2] * B ^ (M-3) + ... s[M-1] * B ^ (M-M) ) % Q
 *  
 *  Rolling Hash Function
 *  Base: 10
 *  Input String: 435623
 *  Substring : 4356 (Size of substring for which Rolling hash function has to be calculated : 4)
 *  mod : 13
 *  H1 = (4 * 10^3 + 3 * 10^2 + 5 * 10^1 + 6 * 10^0 ) % 13
 *  
 *  Next Hash function using value of previous hash function (next digit is 2)
 *  H2 = (10 * (H1 - 4 * 10^3) + 2 ) % 13
 *  
 *  Problems that can be solved using Rolling Hash
 *  1. RabinKarp Algo for seaching a pattern in a given string
 *  2. if two long strings of length n, S and T, share a common substring of length L. (http://courses.csail.mit.edu/6.006/spring11/rec/rec06.pdf)
 *  3. Given two strings A and B, compute their longest common substring.
 * </pre>
 * @author saurabh.gupta
 *
 */
public class RollingHash {

	/**
	 * number of characters used for rolling 
	 */
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

	/**
	 * <pre>
	 * equivalent to:
	 * H1 = (4 * 10^3 + 3 * 10^2 + 5 * 10^1 + 6 * 10^0 ) % 13
	 * 
	 * Here we are using rule of Modular arithmetic of Addition
	 * Addition Rule: (A + B) % C === ( (A%C) + (B%C) )%C
	 * 
	 * http://stackoverflow.com/questions/711770/fast-implementation-of-rolling-hash
	 * unsigned hash(const string& s)
		{
    		unsigned ret = 0;
    		for (int i = 0; i < s.size(); i++)
    		{
    			ret *= PRIME_BASE; //shift over by one
    			ret += s[i]; //add the current char
    			ret %= PRIME_MOD; //don't overflow
    		}
    		return ret;
		}
	 *
	 * http://www.infoarena.ro/blog/rolling-hash 
	 * Good explanation of things
	 * (Other solvable problems using Rolling hash)
	 * </pre>
	 * @param C
	 * @return
	 */
	long add (int C) {
		hash = mod(hash*base + Integer.valueOf(C), mod_value);
		return hash;
	}
	
	/**
	 * <pre>
	 * create rolling hash from input string
	 * </pre>
	 * @param str
	 * @return
	 */
	long createHash (String str) {
		for (int i = 0; i < str.length(); i++) {
			hash = add(str.charAt(i));
		}
		return hash;
	}

	/**
	 * <pre>
	 * remove first character from hash and add new character in hash. And this is called Rolling Hash
	 * 
	 * So many Mods taken to avoid overflow
	 * H[i+1] =  (base * (H[i] - (olddigit * (base^(patternsize-1) % M) ) %M) + newDigit ) % M
	 * 
	 * trimmed
	 * H[i+1] =  (base * (H[i] - old * base^n-1) + newdigit) % M
	 *        ==  ( (A * (B - C*D) + E) ) % M
	 * E = base^n-1
	 * D = oldKey * E
	 * C = oldHash - D
	 * B = base * C
	 * A = B + newKey
	 * 
	 * H[i+1] = A % M
	 * 
	 * http://www.infoarena.ro/blog/rolling-hash
	 * 
	 * All three are same but simpler is used to avoid complexity of readability and to avoid overflow
	 * 
	 * Modulo properties explained here 
	 * http://www.artofproblemsolving.com/Wiki/index.php/Modular_arithmetic/Introduction
	 * </pre>
	 * @param remove the character which will be removed for calculating new hash
	 * @param add the character which will be added for calculating new hash
	 * @return
	 */
	long update (char remove, char add) {
		//Approach 1 (breakdown simpler)
		long E = mod(powerWithMode(base, size-1), mod_value);
		long D = mod(remove * E, mod_value);
		long C = hash - D;
		long B = mod(base * C, mod_value);
		long A = B + add;
		hash = mod(A, mod_value);
		
		//Approach 2 (single operation, may overflow)
//		hash = mod ((base * (hash - remove * UtilPart_2.x_pow_y (base, size-1) ) + add), mod_value);

		//Approach 3 (breakdown simple)
		//This can be used after one calculation
//		long baseToPower = mod(powerWithMode(base, size-1), mod_value);
//		long removefirstCharacter = hash - mod(remove * baseToPower, mod_value);
//		long addNewCharacter = mod(base * removefirstCharacter, mod_value) + add;
//		long newHash = mod (addNewCharacter, mod_value);
//		hash = newHash;

		return hash;
	}
	
	/**
	 * <pre>
	 * In this we have used mod in calculation as We are avoiding number to be overflow.
	 * Also, we are only interested in mod of final number. which can be achieved using modular property
	 * Multiplication Rule: (A * B) % C === (  (A%C) * (B%C))%C
	 * 
	 * ref: http://www.artofproblemsolving.com/Wiki/index.php/Modular_arithmetic/Introduction
	 * </pre>
	 * @param x
	 * @param y
	 * @return
	 */
	long powerWithMode (long x, long y) {
		long ret = 1;
		for (int i = 0; i < y; i++) {
			ret = ret * x;
			ret = mod (ret, mod_value);
		}
		return mod (ret, mod_value);
	}

	/**
	 * <pre>
	 * This extra edition is for considering mod of negative
	 * http://mathforum.org/library/drmath/view/52343.html
	 * 
	 * i.e: 
	 * 14 % 3 ==> 2
	 * 14 % 3 ==> (14 % 3 + 3 ) % 3 ==> ( 2 + 3 ) % 3 ==> 5 % 3 ==> 2
	 * 
	 * -5 % 3 ==> (-5%3 + 3) % 3 ==> (-2 + 3)%3 ==> 1 % 3 ==> 1
	 * 
	 * -6, -3, 0,   3,  6
	 * x1, x2, x3, x4, x5
	 * modulo is difference between: given number(N=5) - multiple of 3 which is smaller than given number (3)
	 * In case of positive N =5, multiple of 3 which is smaller than 5 is 3, so mod = 5 -3 = 2
	 * 
	 * In case of negative N =-5, multiple of 3 which is smaller than -5 is -6, so mod = -5 - (-6) = -5 + 6 = 1
	 * 
	 * </pre>
	 * @param l
	 * @param b
	 * @return
	 */
	long mod (long l, long b) {
		return (l % b + b) % b;
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

	public static void main(String[] args) {
		String input = "4014268212059555086782053180799274502740601270974827572945652903516313066599789442271"
				+ "20782597769822742040169888399266383398656436418402043009010547623";
		
//		String input   = "40601270974827572945652903516313066599789442271";
		String pattern = "0601270974827572945652903516313066599789442271";
		int [] inputarray = new int [input.length()];
		int [] patternarray = new int[pattern.length()];
		
		for (int i = 0; i < input.length(); i++) {
			inputarray[i] = Integer.valueOf(input.charAt(i));
		}
		for (int i = 0; i < pattern.length(); i++) {
			patternarray[i] = Integer.valueOf(pattern.charAt(i));
		}
		
		RollingHash patterhash = new RollingHash(pattern.length(), 1007L, 257);
		RollingHash inputhash = new RollingHash(pattern.length(), 1007L, 257);
		
		long phash = patterhash.createHash(patternarray);
		long ihash = inputhash.createHash(inputarray, 0, pattern.length());

		int PL = pattern.length();
		if (phash == ihash) {
			if (equalArray (patternarray, inputarray, 0, PL)) {
				System.out.println("Similar At Index:" + 0);
				System.out.println("String: " + input.substring(0, PL));
			}
		}
		for (int i = 1; i <= (inputarray.length - patternarray.length); i++) {
//			if (i == 39) {
//				System.out.println();
//			}
			ihash = inputhash.update((char)(inputarray[i-1]), (char)(inputarray[i+PL-1]));
			if (ihash == phash) {
				if (equalArray (patternarray, inputarray, i, i+PL)) {
					System.out.println("Similar At Index:" + i);
					System.out.println("String: " + input.substring(i, i+PL));
				}
			}
		}
		
	}
}
