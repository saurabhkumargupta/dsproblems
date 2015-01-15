package com.code.saurabh.matrix;


/*
 * Given a 2D array, print it in spiral form. 
 * http://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/
 * http://leetcode.com/2010/05/printing-matrix-in-spiral-order.html
 * 
 * Code is copied from this side and Solution 1 (non-recursive)
 * http://www.programcreek.com/2013/01/leetcode-spiral-matrix-java/
 */
public class MatrixSpiral {

	static void printSpiral (int [][] matrix) {
		 
        if(matrix == null || matrix.length == 0) 
        	return ;
		int m = matrix.length;
        int n = matrix[0].length;
 
        int x=0; 
        int y=0;
 
        while(m>0 && n>0){
 
            //if one row left, no circle can be formed
            if(m==1){
                for(int i=0; i<n; i++){
                    System.out.print(matrix[x][y++] + ", ");
                }
                break;
            }
            else if(n==1){ //if one column left, no circle can be formed
                for(int i=0; i<m; i++){
                	System.out.print(matrix[x++][y] + ", ");
                }
                break;
            }
 
            //below, process a circle
 
            //top - move right
            for(int i=0;i<n-1;i++){
            	System.out.print(matrix[x][y++] + ", ");
            }
 
            //right - move down
            for(int i=0;i<m-1;i++){
            	System.out.print(matrix[x++][y] + ", ");
            }
 
            //bottom - move left
            for(int i=0;i<n-1;i++){
            	System.out.print(matrix[x][y--] + ", ");
            }
 
            //left - move up
            for(int i=0;i<m-1;i++){
            	System.out.print(matrix[x--][y] + ", ");
            }
 
            x++;
            y++;
            m=m-2;
            n=n-2;
        }
	}

	public static void main(String[] args) {
		int [][][] input = {
				{
					{1,  2,  3,  4,  5},
					{6,  7,  8,  9,  10},
					{11, 12, 13, 14, 15},
					{16, 17, 18, 19, 20},
					{21, 22, 23, 24, 25}
				},
				{
					{1,  2,  3,  4},
					{14, 15, 16, 5},
					{13, 20, 17, 6},
					{12, 19, 18, 7},
					{11, 10, 9,  8},
				},
				{
					{1,  2,  3,  4,  5},
					{14, 15, 16, 17, 6},
					{13, 20, 19, 18, 7},
					{12, 11, 10, 9,  8}
				},
				{
					{1}
				},
				{
					{1, 2}
				},
				{
					{1},
					{2}
				},
				{
					{1, 2},
					{3, 4}
				},
				{
					{1},
					{2},
					{3}
				}

		};
		for (int i = 0; i < input.length; i++) {
			printSpiral (input[i]);
			System.out.println();
		}

	}

}
