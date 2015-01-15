package com.company.problems;

/*
 * The idea is to start with 'd' cells first and mark minimum distance to all adjacent 'e' cells.
 * In the first iteration all the cells adjacent to all the 'd' will be marked and these are definitely at minimum distance from 'd'.
 * now start with first level of all 'e' marked in iteration 1 above and repeat the same steps. (This is kind of BFS)
 * I am visualizing this as 'd' in center of a circle and marking all adjacent 'e' on its circumference first and then start with the circumference and move outwards.
 * If any 'e' cell is reached again then we have already marked the minimum distance so ignore it.
 * 
 * This implementation provides linear time complexity and linear space complexity. 
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EtoDminDistance {

	//This class is used to store the index (row, col) of the cell while adding them to a queue.
	private class Index {
		int row;
		int col;

		public Index(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	//distance will save minimum distance of 'd' from every 'e'. 
	int[][] distance;
	char[][] input;

	public EtoDminDistance(char[][] inputMatrix) {
		distance = new int[inputMatrix.length][inputMatrix[0].length];
		for (int i = 0; i < distance.length; i++) {
			Arrays.fill(distance[i], -1);
		}
		this.input = inputMatrix;
	}

	private void updateAdjacent(int row, int col, List<Index> queue) {

		// updateRight
		if (((col + 1) < input[row].length) && (distance[row][col + 1] < 0)) {
			distance[row][col + 1] = distance[row][col] + 1;
			queue.add(new Index(row, col + 1)); // add to last
		}
		// updateDown
		if (((row + 1) < input.length) && (distance[row + 1][col] < 0)) {
			distance[row + 1][col] = distance[row][col] + 1;
			queue.add(new Index(row + 1, col));
		}
		// updateLeft
		if (((col - 1) >= 0) && (distance[row][col - 1] < 0)) {
			distance[row][col - 1] = distance[row][col] + 1;
			queue.add(new Index(row, col - 1));
		}
		// updateUp
		if (((row - 1) >= 0) && (distance[row - 1][col] < 0)) {
			distance[row - 1][col] = distance[row][col] + 1;
			queue.add(new Index(row - 1, col));
		}
	}

	public void findDistance() {
		//LinkedList as queue. Additions to last and retrievals to head.
		List<Index> queue = new LinkedList<>();
		for (int row = 0; row < input.length; row++) {
			for (int col = 0; col < input[row].length; col++) {
				if (input[row][col] == 'd') { // first look for all cells
												// adjacent to 'd' only. and
												// then iterate over them later.
					distance[row][col] = 0;
					updateAdjacent(row, col, queue);
				}
				if (input[row][col] == 'w')
					distance[row][col] = 0; // wall cells will not be updated.
											// They will always be 0 (visited).
			}
		}
		while (!queue.isEmpty()) {
			Index index = queue.remove(0); // Think of it like a circle or BFS. Marking all cells at one level first and then moving outwards.
			updateAdjacent(index.row, index.col, queue);
		}
	}

	public void print() {
		for (int i = 0; i < distance.length; i++) {
			System.out.println();
			for (int j = 0; j < distance[i].length; j++) {
				System.out.print(distance[i][j] + " ");
			}
		}
	}

	public static void main(String[] args) {
//		char[][] input = { 
//				{ 'd', 'e', 'd' }, 
//				{ 'e', 'e', 'e' },
//				{ 'e', 'w', 'd' } };
		
		char[][] input = { 
				{ 'd', 'w', 'w', 'e'}, 
				{ 'e', 'w', 'w', 'e'},
				{ 'e', 'e', 'e', 'e'},
				{ 'e', 'e', 'e', 'd'}
		};
		// char[][] input = { { 'e', 'w', 'd' }, { 'e', 'e', 'e' }};

		EtoDminDistance etoDminDistance = new EtoDminDistance(input);

		etoDminDistance.findDistance();
		etoDminDistance.print();
	}
}
