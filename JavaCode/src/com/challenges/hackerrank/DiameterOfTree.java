package com.challenges.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DiameterOfTree {
	
	static public class Pair {
		int first;
		int second;

		public Pair (int f, int s) {
			this.first = f;
			this.second = s;
		}

		public int getFirst () {
			return first;
		}

		public int getSecond () {
			return second;
		}
	}
	
	static public class TreeNode {
		public TreeNode left;
		public int key;
		public TreeNode right;
		
		public TreeNode (int k) {
			this.left = null;
			this.key = k;
			this.right = null;
		}

		public boolean leafNode() {
			if (this.left == null && this.right == null)
				return true;
			return false;
		}
		
		public boolean singleChild () {
			if (this.left == null
					|| this.right == null) {
				return true;
			}
			return false;
		}
	}
	
	static public class Tree {
		public TreeNode root;
		
		public Tree () {
			root = null;
		}

		public boolean isEmpty () {
			return root == null ? true : false; 
		}
		
		public void addToBST (int k) {
			if (this.isEmpty()) {
				this.root = new TreeNode (k);
				return;
			}
			addToBST (this.root, k);
		}
		
		private void addToBST (TreeNode r, int k) {
			if (k < r.key) {
				if (r.left == null)
					r.left = new TreeNode (k);
				else
					addToBST (r.left, k);
			}
			else if (k > r.key) {
				if (r.right == null)
					r.right = new TreeNode (k);
				else
					addToBST (r.right, k);
			}
		}
	}
	
	static void diaMeter (Tree t) {
		TreeNode root = t.root;
		if (root == null) {
			System.out.println("0");
			return;
		}
		Pair result = dia (root);
		System.out.println(result.getSecond());
	}
	
	static private Pair dia(TreeNode node) {
		if (node == null)
			return new Pair (0, 0);
		
		Pair left = dia (node.left);
		Pair right = dia (node.right);
		int maxDia = Math.max(left.getSecond(), right.getSecond());

		int curDia =  left.getFirst() + right.getFirst() + 1;
		if (curDia > maxDia) {
			maxDia = curDia;
		}
		int maxHeight = Math.max(left.getFirst(), right.getFirst()) + 1;
		
		return new Pair (maxHeight, maxDia);
	}
	
	static void treeDiameter(int[] values) {
		Tree t = new Tree ();
		for (int i = 0; i < values.length; i++) {
			t.addToBST(values[i]);
		}
		diaMeter(t);
    }
	
	public static void main(String[] args) {
		readInput ();
	}

	static void readInput () {
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
		try {
			int[] arr = inputArrayFromStdin(br, " ");
			treeDiameter (arr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static public int[] inputArrayFromStdin (BufferedReader br, String separator) throws IOException {
		String[] input = br.readLine().trim().split(separator);
		int[] arr = new int[input.length];
		int index = 0;
		for (String s : input) {
			if (!s.equals(" ") && !s.isEmpty()) {
				arr[index] = Integer.valueOf(s.trim());
				index++;
			}
		}
		int[] finalArray = new int[index];
		for (int i = 0; i < index; i++) {
			finalArray[i] = arr[i];
		}
		return finalArray;
	}
}
