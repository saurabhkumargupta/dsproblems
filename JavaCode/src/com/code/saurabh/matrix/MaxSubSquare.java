package com.code.saurabh.matrix;

/*
 * http://www.geeksforgeeks.org/given-matrix-o-x-find-largest-subsquare-surrounded-x/
 * Given a matrix where every element is either 'O' or 'X', find the largest subsquare surrounded by 'X'.
 * 
 * copied
 * 
 */
public class MaxSubSquare {

	
	// Returns size of maximum size subsquare matrix
	// surrounded by 'X'
	static int findSubSquare(int mat[][])
	{
	    int max = 1; // Initialize result
	 
	    int N = mat.length;
	    // Initialize the left-top value in hor[][] and ver[][]
	    int hor[][] = new int[N][N];
	    int ver[][] = new int[N][N];
	    hor[0][0] = ver[0][0] = (mat[0][0] == 'X') ? 1 : 0;
	 
	    // Fill values in hor[][] and ver[][]
	    for (int i=0; i<N; i++)
	    {
	        for (int j=0; j<N; j++)
	        {
	            if (mat[i][j] == 'O')
	                ver[i][j] = hor[i][j] = 0;
	            else
	            {
	                hor[i][j] = (j==0)? 1: hor[i][j-1] + 1;
	                ver[i][j] = (i==0)? 1: ver[i-1][j] + 1;
	            }
	        }
	    }
	 
	    // Start from the rightmost-bottommost corner element and find
	    // the largest ssubsquare with the help of hor[][] and ver[][]
	    for (int i = 0; i < N; i++)
	    {
	        for (int j = 0; j < N; j++)
	        {
	            // Find smaller of values in hor[][] and ver[][]
	            // A Square can only be made by taking smaller
	            // value
	            int small = Math.min(hor[i][j], ver[i][j]);
	 
	            // At this point, we are sure that there is a right
	            // vertical line and bottom horizontal line of length
	            // at least 'small'.
	 
	            // We found a bigger square if following conditions
	            // are met:
	            // 1)If side of square is greater than max.
	            // 2)There is a left vertical line of length >= 'small'
	            // 3)There is a top horizontal line of length >= 'small'
	            while (small > max)
	            {
	                if (ver[i][j-small+1] >= small &&
	                    hor[i-small+1][j] >= small)
	                {
	                    max = small;
	                }
	                small--;
	            }
	        }
	    }
	    return max;
	}
	public static void main(String[] args) {
	    int mat[][] =  { {'X', 'O', 'X', 'X', 'X', 'X'},
	                     {'X', 'O', 'X', 'X', 'O', 'X'},
	                     {'X', 'X', 'X', 'O', 'O', 'X'},
	                     {'O', 'X', 'X', 'X', 'X', 'X'},
	                     {'X', 'X', 'X', 'O', 'X', 'O'},
	                     {'O', 'O', 'X', 'O', 'O', 'O'},
	                    };

//		int mat[][] = { {'X', 'O', 'X', 'X', 'X'},
//			            {'X', 'X', 'X', 'X', 'X'},
//			            {'X', 'X', 'O', 'X', 'O'},
//			            {'X', 'X', 'X', 'X', 'X'},
//			            {'X', 'X', 'X', 'O', 'O'},
//           			};
        
	    System.out.println(findSubSquare(mat));
	}
}
