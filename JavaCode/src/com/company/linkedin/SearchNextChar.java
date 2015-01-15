package com.company.linkedin;

/*
 * Return the smallest character that is strictly larger than the search character, 
 * @param sortedStr : sorted list of letters, sorted in ascending order.
 * @param c : character for which we are searching.
 * Given the following inputs we expect the corresponding output:
 * ['c', 'f', 'j', 'p', 'v'], 'a' => 'c'
 * ['c', 'f', 'j', 'p', 'v'], 'c' => 'f'
 * ['c', 'f', 'j', 'p', 'v'], 'k' => 'p'
 * ['c', 'f', 'j', 'p', 'v'], 'z' => 'c' // The wrap around case
 * ['c', 'f', 'k'], 'f' => 'k'
 * ['c', 'f', 'k'], 'c' => 'f'
 * ['c', 'f', 'k'], 'd' => 'f'
 */
public class SearchNextChar {
	static char searchChar (char[] input, char search) {
		int left = 0;
		int right = input.length -1;

		//If character matches then next character has to be returned
		while (left <= right) {
			if (left == right) {
				//in case of single character, if current character is less than or equal to search character, then it would be next 
				//character. And if it is last character then 0th character should be returned
				if (input[left] <= search) {
					return input[(left+1)%input.length];
				}
				//if current character is bigger than search character and this is single character left in arrray to search, then
				// this is the result character.
				if (input[left] > search) {
					return input[left];
				}
			}

			if (search > input[right]) {
				return input[(right+1)%input.length];
			}
			int mid = left + (right-left)/2;

			if (input[mid] == search) {
				return input[(mid+1)%input.length];
			}

			//this is possible when size is 2, and as mid is not equal to search key
			if (mid == left) {
				if (search < input[mid]) {
					return input[mid];
				}
				else {
					return input[(mid+1)%input.length];
				}
			}
			if (search < input[mid]) {
				right = mid-1;
			}
			else {
				left = mid+1;
			}
		}
		return '$';
	}

	static char searchChar_1 (char[] input, char search) {
		int left = 0;
		int right = input.length -1;

		//If character matches then next character has to be returned
		while (left <= right) {
			if (search > input[right]) {
				return input[(right+1)%input.length];
			}

			//size 1
			if (left == right) {
				//in case of single character, if current character is less than or equal to search character, then it would be next 
				//character. And if it is last character then 0th character should be returned
				if (input[left] <= search) {
					return input[(left+1)%input.length];
				}
				//if current character is bigger than search character and this is single character left in arrray to search, then
				// this is the result character.
				if (input[left] > search) {
					return input[left];
				}
			}

			//size 2
			if (left +1 == right) {
				if (search < input[left]) {
					return input[left];
				}
				if (search >= input[left]) {
					return input[(left+1)%input.length];
				}
			}

			int mid = left + (right-left)/2;

			if (input[mid] == search) {
				return input[(mid+1)%input.length];
			}

			if (search < input[mid]) {
				right = mid-1;
			}
			else {
				left = mid+1;
			}
		}
		return '$';
	}

	//From senthil
	//Modified with low==high, to work for last case
	public static char findChar(char[] input , char toSearch){
		int low = 0, high = input.length - 1;
		char res = input[0];
		while(low <= high){
			if (low == high) {
				if (toSearch < input[low]) {
					return input[low];
				}
			}
			int mid = (low + high)/2;
			if(input[mid] == toSearch){
				//return next character
				if((mid + 1 ) <= (input.length - 1))
					return input[mid +1];
				else
					return res; // default character.
			}else if (input[mid] < toSearch){
				low = mid + 1;
			}
			else{
				high = mid - 1;
				res = input[mid];
			}
		}
		return res;
	}

	public static void main(String[] args) {
		char [][] input = {
				{'c', 'f', 'j', 'p', 'v'},
				{'c', 'f', 'j', 'p', 'v'},
				{'c', 'f', 'j', 'p', 'v'},
				{'c', 'f', 'j', 'p', 'v'},
				{'c', 'f', 'k'},
				{'c', 'f', 'k'},
				{'c', 'f', 'k'},
				{'c', 'f', 'k'},
		};

		char [][] match = {
				{'a', 'c'}, // search character and result character
				{'c', 'f'},
				{'k', 'p'},
				{'z', 'c'},
				{'f', 'k'},
				{'c', 'f'},
				{'d', 'f'},
				{'g', 'k'}
		};

		for (int i = 0; i < input.length; i++) {
			char result = findChar (input[i], match[i][0]);
			//			char result = searchChar (input[i], match[i][0]);
			//			char result = searchChar_1 (input[i], match[i][0]);
			if (result == match[i][1]) {
				System.out.println("Correct: " + result);
			}
			else {
				System.out.println("failed: " + result);
			}
		}

		//		 ['c', 'f', 'j', 'p', 'v'], 'a' => 'c'
		// ['c', 'f', 'j', 'p', 'v'], 'c' => 'f'
		// ['c', 'f', 'j', 'p', 'v'], 'k' => 'p'
		//['c', 'f', 'j', 'p', 'v'], 'z' => 'c' // The wrap around case
		//['c', 'f', 'k'], 'f' => 'k'
		//['c', 'f', 'k'], 'c' => 'f'
		//['c', 'f', 'k'], 'd' => 'f'
	}

}
