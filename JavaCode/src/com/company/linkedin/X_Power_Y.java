package com.company.linkedin;

import java.util.HashMap;
import java.util.Map;

import com.code.saurabh.util.UtilPart_2;

public class X_Power_Y {
	/*
	 * http://www.geeksforgeeks.org/write-a-c-program-to-calculate-powxn/
	 */
	static int power(int x, int y) // for non-negative y
	{
	    int temp;
	    if( y == 0)
	        return 1;
	    temp = power(x, y/2);
	    if (y%2 == 0)
	        return temp*temp;
	    else
	        return x*temp*temp;
	}
	/*
	 * http://www.geeksforgeeks.org/write-a-c-program-to-calculate-powxn/
	 */
	static double power(double x, int y)	// for negative y
	{
		double temp;
	    if( y == 0)
	       return 1;
	    temp = power(x, y/2);       
	    if (y%2 == 0)
	        return temp*temp;
	    else
	    {
	        if(y > 0)
	            return x*temp*temp;
	        else
	            return (temp*temp)/x;
	    }
	}

	/*
	 * 	1. If power 0, then return 1.0
	 *	2. if power negative, then get result for positive value of b and return 1/result.
	 *	3. initialize map with power = 1;
	 *	4. decrement power by 1
	 *	5. In each loop, first verify if remaining b (power to be calculated) is greater than power calculated till now, if b is greater
	 *		than power, then we simply double result and so power and reduce b by that power
	 *	6. At point when b is less than power, then we first check do we have remaining power in map, if so then simply use that value,
	 *		otherwise, get a value which is power of 2 and is closes less than b and use it
	 *	7. with each result, we populate map also, which we use in future.
	 *
	 *	Here, we never do linear processing, Its log(b)
	 */
	static double x_pow_y (double a, int b) {
		double result = 1.0;
		if (b == 0) {
			return 1.0;
		}
		
		boolean negative = false;
		if (b < 0) {
			negative = true;
			b = Math.abs(b);
		}
		Map<Integer, Double> m = new HashMap<Integer, Double> ();

		int power = 1;
		result = a;
		b -= power;
		m.put(power, result);

		while (b > 0) {
			if (b > power) {
				result *= result;
				b -= power;
				power *= 2;
			}
			else {
				int maxAvail = b;
				if (m.get(maxAvail) == null) {
					//if remaining power does not exist in map
					if (!powerOfTwo(b)) {
						maxAvail = b & b-1;
					}					
				}

				Double val = m.get(maxAvail);
				
				result *= val;
				power *= maxAvail;
				b -= maxAvail;
			}
			m.put(power, result);
		}

		if (negative == true) {
			return 1/result;
		}
		return result;
	}

	private static boolean powerOfTwo(int b) {
		if ((b & (b-1) ) == 0) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		int [][] input_2 = {
				{2, 1},
				{1, 1},
				{2, 2},
				{3, 4},
				{5, 3},
				{9, 2},
				{7, 3},
				{2, 4},
				{2, 5},
				{2, 0},
				{2, -1},
				{2, -2},
		};
		
		for (int i = 0; i < input_2.length; i++) {
			int a = input_2[i][0];
			int b = input_2[i][1];

			System.out.println(a+"^"+b +": " + x_pow_y (a,b) + ", Old: " + UtilPart_2.x_pow_y_logn (a, b) + ", power: " + power (a,b));
		}
		
		double a = 1.2;
		int b = 2;
		System.out.println(a+"^"+b +": " + x_pow_y (a,b));
		
		System.out.println("power: " + power(1.2, 2));
	}


}
