package com.code.saurabh.matrix;

import com.code.saurabh.util.IntPair;

public class Matrix {

	int row_count;
	int col_count;
	int[][] data;
	
	public Matrix (int[][] input) {
		row_count = input.length;
		col_count = input[0].length;
		
		data = new int[row_count][col_count];
		for (int i = 0; i < row_count; i++) {
			for (int j = 0; j < col_count; j++) {
				data[i][j] = input[i][j];
			}
		}
	}
	
	public int getValue (IntPair point) {
		return data[point.getFirst()][point.getSecond()];
	}

	public void setValue (IntPair point, int value) {
		data[point.getFirst()][point.getSecond()] = value;
	}

	public Matrix (int r, int c) {
		this(r, c, 0);
	}

	public Matrix (int r, int c, int initialValue) {
		this.row_count = r;
		this.col_count = c;
		
		data = new int[row_count][col_count];
		for (int i = 0; i < row_count; i++) {
			for (int j = 0; j < col_count; j++) {
				data[i][j] = -1;
			}
		}
	}
	
	public int getRowsCount () {
		return this.row_count;
	}

	public int getColsCount () {
		return this.col_count;
	}

	public boolean rightPointExist (IntPair point) {
		if (point.getSecond()+1 >= this.col_count)
			return false;
		return true;
	}
	
	public boolean downPointExist (IntPair point) {
		if (point.getFirst()+1 >= this.row_count)
			return false;
		return true;
	}

	public boolean leftPointExist (IntPair point) {
		if (point.getSecond() -1 < 0)
			return false;
		return true;
	}
	
	public boolean upPointExist (IntPair point) {
		if (point.getFirst()-1 < 0)
			return false;
		return true;
	}

	public boolean isValidPoint (IntPair point) {
		if (point.getFirst() >= this.row_count) {
			return false;
		}

		if (point.getSecond() >= this.col_count) {
			return false;
		}
		return true;
	}

	public IntPair getRightPoint (IntPair point) {
		IntPair result = new IntPair (point.getFirst(), point.getSecond() +1);
		return result;
	}

	public IntPair getDownPoint (IntPair point) {
		IntPair result = new IntPair (point.getFirst()+1, point.getSecond());
		return result;
	}
	
	public static void main(String[] args) {

		int input [][] = {
				{4,2,9},
				{8,5,11},
				{1,2,3},
		};

		Matrix mat = new Matrix (input);
//		Matrix mat = new Matrix (3,3);
		
		IntPair point = new IntPair (0,0);

		for (int i = 0; i <= mat.row_count; i++) {
			for (int j = 0; j <= mat.col_count; j++) {
				point.setFirst(i);
				point.setSecond(j);
				mat.pointAround(point);				
			}
		}
	}
	
	void pointAround (IntPair point) {
		System.out.println("Point: " + point);
		System.out.println( " isValidPoint    : " + isValidPoint (point));
		if (isValidPoint (point)) {
			System.out.println( " leftPointExist  : " + leftPointExist(point));
			System.out.println( " rightPointExist : " + rightPointExist(point));
			System.out.println( " upPointExist    : " + upPointExist(point));
			System.out.println( " downPointExist  : " + downPointExist(point));
		}
		System.out.println();
	}

}
