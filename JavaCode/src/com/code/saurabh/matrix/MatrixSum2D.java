package com.code.saurabh.matrix;

import java.util.Random;

/**
 * <pre>
 *  COPIED
 * Sum of Matrix
 * http://www.careercup.com/question?id=5154229209530368
 * 
 * A 2D matrix is given to you. Now user will give 2 positions (x1,y1) and (x2,y2), 
	which is basically the upper left and lower right coordinate of a rectangle formed within the matrix. 
	You have to print sum of all the elements within the area of rectangle in O(1) running time. 
	Now you can do any pre computation with the matrix. 
	But when it is done you should answer all your queries in constant time. 
	
	Example : consider this 2D matrix 
	1 3 5 1 8 
	8 3 5 3 7 
	6 3 9 6 0 
	
	Now consider 2 points given by user (0, 2) and (2, 4). 
	Your solution should print: 44. 
	i.e., the enclosed area is 
	5 1 8 
	5 3 7 
	9 6 0
	
	Solution
	In order to compute the sum of all the elements within the area of rectangle in O(1) running time we can preprocess the 
	Matrix to have in each element of the Matrix the sum of all the elements between (0,0) and the lowerRight corner. 
	Then we can use this preprocessed Matrix to get the sum for all the elements between upperLeft and lowerRight by getting the
	sum at lower right, minus the sum at the element on the bottom left-1 of the rectangle (that will subtract all the elements on
	the left os the Rectangle), minus the sum at the element on the top-1 right (that will subtract all the elements above the 
	rectangle, and then we have to readd the elements that are both on the left and above the reactangle, because we have subtracted 
	them 2 times, so we add the sum at the top-1 left-1 corner. In this way we can compute the sum of all the elements in a rectangle
	inside a matrix we just 3 operations for any dimension. 

So there are 2 steps: 
1. Preprocess the matrix substituting the elements with the sum of all previous elements (like a running sum) 
2. Get the sum in a rectangle by getting the sum at lowerRight minus the sum at position bottom left -1 (elements on the left), minus the sum at position top-1right, plus the sum at position top-1 left-1. 

The complexity is O(n*m) to preprocess the matrix as we need to process each element (n and m are the dimensions of the matrix), and O(1) to get the sum after preprocessing. 
 </pre>
 
 
 COPIED
 */
public class MatrixSum2D {
	static class Vertex {
		int i;
		int j;
		public Vertex(int i, int j) {
			this.i=i;
			this.j=j;
		}
		public String toString() {
			return "("+this.i+","+this.j+")";
		}
	}
	
	public static int[][] preprocessSumMatrix(int[][] m) {
		int[][] summedMatrix = new int[m.length][m[0].length];
		for(int i=0;i<m.length;i++) {
			for(int j=0;j<m[i].length;j++) {
				summedMatrix[i][j] = m[i][j];
				if(j-1>=0) {
					summedMatrix[i][j] = summedMatrix[i][j] + summedMatrix[i][j-1];
				}
				if(i-1>=0) {
					summedMatrix[i][j] = summedMatrix[i][j] + summedMatrix[i-1][j];
				}
				if(i-1>=0 && j-1>=0) {
					summedMatrix[i][j] = summedMatrix[i][j] - summedMatrix[i-1][j-1];
				}
			}
		}
		return summedMatrix;
	}
	public static int sum(int[][] m, Vertex upperLeft, Vertex lowerRight) {
		int sum = 0;
		if(m==null) return sum;
		if(upperLeft.i>lowerRight.i || upperLeft.j>lowerRight.j) {
			System.out.println("ERROR: Input Vertices not Correct, check the coordinates.");
			return sum;
		}
		if(upperLeft.i<0 || upperLeft.i>=m.length || upperLeft.j<0 || upperLeft.j>=m[0].length) {
			System.out.println("ERROR: Input Vertex UpperLeft out of Bounds.");
			return sum;
		}
		if(lowerRight.i<0 || lowerRight.i>=m.length || lowerRight.j<0 || lowerRight.j>=m[0].length) {
			System.out.println("ERROR: Input Vertex LowerRight out of Bounds.");
			return sum;
		}
		sum = m[lowerRight.i][lowerRight.j];
		if(upperLeft.i-1>=0) {
			sum = sum - m[upperLeft.i-1][lowerRight.j];
		}
		if(upperLeft.j-1>=0) {
			sum = sum - m[lowerRight.i][upperLeft.j-1];
		}
		if(upperLeft.i-1>=0 && upperLeft.j-1>=0) {
			sum = sum + m[upperLeft.i-1][upperLeft.j-1];
		}
		return sum;
	}
	public static int[][] genMatrix(int n, int m) {
		if(n<=0 || m<=0) {
			System.out.println("ERROR: Non-positive values to create Matrix");
			return null;
		}
		Random r = new Random();
		int[][] matrix = new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				matrix[i][j] = r.nextInt(10);
			}
		}
		return matrix;
	}
	public static void printMatrix(int[][] m) {
		if(m==null) {
			System.out.println("ERROR: Null Matrix");
			return;
		}
		for(int i=0;i<m.length;i++) {
			for(int j=0;j<m[i].length-1;j++) {
				System.out.print(m[i][j]+",");
			}
			System.out.println(m[i][m[i].length-1]);
		}
	}
	public static void main(String[] args) {
		int[][] matrix = genMatrix(3,4);
		System.out.println("Original Matrix:");
		printMatrix(matrix);
		int[][] summedMatrix = preprocessSumMatrix(matrix);
		System.out.println("Summed Matrix:");
		printMatrix(summedMatrix);
		Vertex upperLeft = new Vertex(0,1);
		Vertex lowerRight = new Vertex(2,2);
		int sum = sum(summedMatrix,upperLeft,lowerRight);
		System.out.println("The sum in the rectangle betweeen\nUpperLeft corner "
				+upperLeft+"\nand\nLowerRight corner "+lowerRight+"\nis: "+sum);
	}

}
