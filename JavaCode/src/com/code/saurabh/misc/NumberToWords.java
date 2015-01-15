package com.code.saurabh.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/*
 * Write a program to convert a integer to its form in a given language .
 * For example 112345 Could be one hundred twelve thousand three hundred forty five or One lac twelve thousand three hundred forty five.
 * 
 * 
 * This is done two ways: 
 * first using string format which is quite slow
 * then using integer format which works fast and best
 */
public class NumberToWords {
	static Map<String, String> numMap = new HashMap<String, String> ();
	static Map<Integer, String> numIntMap = new HashMap<Integer, String> ();
	static Map<String, String> nextUnitMap = new HashMap<String, String> ();
	
	static void populateMap () {
		numMap.put("0", "");
		numMap.put("00", "");
		numMap.put("01", "one");
		numMap.put("1", "one");
		numMap.put("02", "two");
		numMap.put("2", "two");
		numMap.put("03", "three");
		numMap.put("3", "three");
		numMap.put("04", "four");
		numMap.put("4", "four");
		numMap.put("05", "five");
		numMap.put("5", "five");
		numMap.put("06", "six");
		numMap.put("6", "six");
		numMap.put("07", "seven");
		numMap.put("7", "seven");
		numMap.put("08", "eight");
		numMap.put("8", "eight");
		numMap.put("09", "nine");
		numMap.put("9", "nine");
		numMap.put("10", "ten");
		numMap.put("10", "ten");
		numMap.put("11", "eleven");
		numMap.put("12", "twelve");	
		numMap.put("13", "thirteen");
		numMap.put("14", "fourteen");
		numMap.put("15", "fifteen");
		numMap.put("16", "sixteen");
		numMap.put("17", "seventeen");
		numMap.put("18", "eightteen");
		numMap.put("19", "nineteen");
		numMap.put("20", "twenty");
		numMap.put("30", "thirty");
		numMap.put("40", "fourty");
		numMap.put("50", "fifty");
		numMap.put("60", "sixty");
		numMap.put("70", "seventy");
		numMap.put("80", "eightty");
		numMap.put("90", "ninety");
		
		nextUnitMap.put("", "hundred");
		nextUnitMap.put("hundred", "thousand");
		nextUnitMap.put("thousand", "Lac");
		nextUnitMap.put("Lac", "crore");
		
		numIntMap.put(0, "");
		numIntMap.put(1, "one");
		numIntMap.put(2, "two");
		numIntMap.put(3, "three");
		numIntMap.put(4, "four");
		numIntMap.put(5, "five");
		numIntMap.put(6, "six");
		numIntMap.put(7, "seven");
		numIntMap.put(8, "eight");
		numIntMap.put(9, "nine");
		numIntMap.put(10, "ten");
		numIntMap.put(11, "eleven");
		numIntMap.put(12, "twelve");	
		numIntMap.put(13, "thirteen");
		numIntMap.put(14, "fourteen");
		numIntMap.put(15, "fifteen");
		numIntMap.put(16, "sixteen");
		numIntMap.put(17, "seventeen");
		numIntMap.put(18, "eightteen");
		numIntMap.put(19, "nineteen");
		numIntMap.put(20, "twenty");
		numIntMap.put(30, "thirty");
		numIntMap.put(40, "fourty");
		numIntMap.put(50, "fifty");
		numIntMap.put(60, "sixty");
		numIntMap.put(70, "seventy");
		numIntMap.put(80, "eightty");
		numIntMap.put(90, "ninety");
	}

	static String getNumber (String num) {
		String result = numMap.get(num);
		if (result != null) {
			return result;
		}
		result = "";
		String n1 = "";
		String n2 = "";
		if (num.length() > 0) {
			n1 = String.valueOf(num.charAt(0));
		}
		if (num.length() > 1) {
			n2 = String.valueOf(num.charAt(1));
			n1 = n1 + "0";
		}
		

		if (!n1.isEmpty()) {
			result = numMap.get(n1);
		}
		if (!n2.isEmpty()) {
			result = result + " " + numMap.get(n2);
		}		
		return result;
	}
	static void intToWords (String n) {
		if (n.length() == 0) {
			System.out.println("<-Empty String->");
			return;
		}
		String result = null;
		if (n.length() <= 2) {
			result = numMap.get(n);
		}
		else {
			StringBuilder sb = new StringBuilder();
			intToWords (n, sb, "");
			result = sb.toString();
		}
		System.out.println(n + " -> " + result);
	}

	static void intToWords (String num, StringBuilder sb, String unit) {
		String str = null;
		if (num.length() == 0) {
			return;
		}
		if (unit.equals("hundred")) {//only one last character
			str = num.substring(num.length()-1, num.length());
		}
		else if (num.length() == 2 || num.length() == 1) {
			str = num;
		}
		else {
			str = num.substring(num.length()-2, num.length());
		}

		String nextUnit = nextUnitMap.get(unit);
		if (nextUnit == null && num.length() > 2) {
			sb.delete(0, sb.length());
			sb.append ("Not supported after unit ").append(unit);
			return;
		}

		String curDigit = getNumber(str);
		if (!curDigit.isEmpty()) 
			sb.insert(0, curDigit + " " + unit + " ");
		if (num.length() == 1) {
			return;
		}
		if (unit.equals("hundred"))
			intToWords (num.substring(0, num.length()-1), sb, nextUnit);
		else
			intToWords (num.substring(0, num.length()-2), sb, nextUnit);
	}

	
	static void usingInt (int number) {
		int v = number / 10;
		String result = null;
		if (number == 0) {
			result = "zero";
		}
		else if (v == 0) {
			result = numIntMap.get (number%10);
		}
		else {
			StringBuilder sb = new StringBuilder();
			usingInt (number, sb, "");
			result = sb.toString();
		}
		System.out.println(number + " -> " + result);
	}
	
	static String handleThreeDigits (int number) {
		String temp = "";
		if (number < 100) {
			temp = handleTwoDigits (number);
		}
		else {
			int hundredthDigit = number / 100;
			temp = handleTwoDigits (number%100);//to handle two digits
			temp = numIntMap.get (hundredthDigit) + " hundred " + temp;
		}
		return temp;
	}

	static String handleTwoDigits (int number) {
		String temp = null;
		int firstTwoDigits = number % 100;
		if (firstTwoDigits == 0) {
			temp = "";
		}
		else if (firstTwoDigits < 20) {
			temp = numIntMap.get (firstTwoDigits);
		}
		else {
			int firstdigit = firstTwoDigits % 10;
			int seconddigit = (firstTwoDigits / 10) * 10; //converting 89 to 80
			temp = numIntMap.get (seconddigit) + " " + numIntMap.get (firstdigit);
		}
		return temp;
	}
	static void usingInt (int number, StringBuilder result, String currentUnit) {
		if (number == 0) {
			return;
		}
		if (currentUnit == null || number < 0) {
			result.delete(0, result.length());
			result.append("This number is not supported");
			return;
		}
		if (currentUnit.isEmpty()) {
			//get first three digits
			int firstThreeDigits = number % 1000;
			result.append(handleThreeDigits (firstThreeDigits));
			usingInt (number/1000, result, "thousand");
		}
		else {
			int twoDigits = number %100;
			String temp = handleTwoDigits (twoDigits);
			String nextUnit = nextUnitMap.get (currentUnit);
			temp = temp + " " + currentUnit + " ";
			result.insert(0, temp);
			usingInt (number/100, result, nextUnit);
		}
	}
	
	public static void main(String[] args) {
		populateMap ();
//		String [] input = {
//				"11112", 
//				"112", 
//				"11012",
//				"1012", 
//				"11111111", 
//				"101010", 
//				"1000", 
//				"10", 
//				"100", 
//				"1100",
//				"9999"
//		};
//		for (int i = 0; i < input.length; i++) {
//			intToWords (input[i]);
//		}
		
//		usingInt (0);
//		usingInt (11112);
//		usingInt (112);
//		usingInt (11012);
//		usingInt (1012);
//		usingInt (11111111);
//		usingInt (101010);
//		usingInt (1000);
//		usingInt (10);
//		usingInt (100);
//		usingInt (1100);
//		usingInt (9999);
		
//		for (int i = 0; i < 1000; i++) {

		//Using int is faster and working fine
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
//			int v = r.nextInt(10000001);
			int v = r.nextInt();
//			System.out.println("input:" + v);
			usingInt (v);
		}
	}

}
