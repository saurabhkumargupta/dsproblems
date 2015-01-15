package com.code.saurabh.datastructure.tree.misc;

import java.util.ArrayList;
import java.util.List;

import sun.misc.Queue;

/*
 * http://ideone.com/K604Ay
 * 
 * given a pre and post order kindof a traversal (2 arrays) create an n-ary treee out of it with struct of the form : 
	struct node { 
	int data; 
	struct node *child[MAX]; 
	int child_num; 
}
 */
public class PrePostToNaryTree {

	static class NaryTreeNode {
		int count;
		int key;
		List<NaryTreeNode> childs;

		public NaryTreeNode(int k) {
			this.key = k;
			childs = new ArrayList<NaryTreeNode> ();
		}
	}
	static class Traversal {
		int index;
		Traversal() {
			index = 0;
		}
		void incr () {
			index++;
		}
		int getIndex () {
			return index;
		}
	}
	
	/*
	 * 1. Current subtree root is current elements of pre array.
	 * 2. Get next element from pre array
	 * 3. search this element in post array, (this post array is sub-array of given post array)
	 * 		i. in this post array,this element should lie in left of current root's.For that purpose,e(end index of post array) of post array in this
	 * 			search function is index of current root.
	 * 		ii. if element is not found, return -1
	 * 4. If post array doesnt have next key in its left of current root location then this root is returned.
	 * 5. make return root of subtree as next child of current root
	 * 6. child count in incremented for this current root.
	 */
	static NaryTreeNode buildNaryTree (Traversal Index, int[] pre, int[] post, int s, int e) {
		NaryTreeNode root = new NaryTreeNode (pre[Index.index]);
		Index.incr();
		if (Index.index >= post.length) {
			return root;
		}
		
		while (true) {
			if (Index.index >= post.length) {
				return root;
			}
			int nextkey = pre[Index.index];
			int index = postSearch (post, s, e, nextkey);
			if (index == -1) {
				return root;
			}
			root.childs.add(buildNaryTree(Index, pre, post, s, index));
			root.count++;
		}
	}
	
	static private int postSearch(int[] post, int s, int e, int nextkey) {
		for (int i = s; i <= e; i++) {
			if (post[i] == nextkey) {
				return i;
			}
		}
		return -1;
	}
	
	static void printTree (NaryTreeNode root) throws InterruptedException {
		Queue q = new Queue ();
		q.enqueue(root);
		while (!q.isEmpty()) {
			Queue nextQueue = new Queue();
			while (!q.isEmpty()) {
				NaryTreeNode node = (NaryTreeNode)q.dequeue();
				System.out.println("Childs of node: " + node.key + ", total " + node.count);
				for (int i = 0; i < node.count; i++) {
					NaryTreeNode child = node.childs.get(i);
					System.out.print(child.key + ", ");
					nextQueue.enqueue(child);
				}
				System.out.println();
			}
			q = nextQueue;
		}
	}
	
	static void printTreeLevel (NaryTreeNode root) throws InterruptedException {
		Queue q = new Queue ();
		q.enqueue(root);
		while (!q.isEmpty()) {
			Queue nextQueue = new Queue();
			while (!q.isEmpty()) {
				NaryTreeNode node = (NaryTreeNode)q.dequeue();
				System.out.print(node.key + ", ");
				for (int i = 0; i < node.count; i++) {
					NaryTreeNode child = node.childs.get(i);
					nextQueue.enqueue(child);
				}
			}
			System.out.println();
			q = nextQueue;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int [][][] input = {
				{
					{10, 5, 2, 1, 4, 3},
					{2, 5,  1, 4, 3, 10}
				},
				{
					{10, 5, 2, 4, 1, 3},
					{2, 5,  1, 4, 3, 10}					
				},
				{
					{50, 40, 30, 45, 60},
					{30, 45, 40, 60, 50}
				},
				{
					{50, 40, 30, 20, 35, 34, 36, 45, 60, 55, 70, 65, 80},
					{20, 34, 36, 35, 30, 45, 40, 55, 65, 80, 70, 60, 50}
				},
				{
					{10, 11, 16, 17, 12, 18, 13, 19, 20, 22, 21, 23, 14, 24, 15, 25, 26, 30, 27, 31, 32, 28, 29, 33},
					{16, 17, 11, 18, 12, 22, 20, 23, 21, 19, 13, 24, 14, 30, 26, 32, 31, 27, 28, 33, 29, 25, 15, 10},
				}
		};
		for (int i = 0; i < input.length; i++) {
			System.out.println("Next Tree");
			Traversal t = new Traversal ();
			NaryTreeNode root = buildNaryTree (t, input[i][0], input[i][1], 0, input[i][1].length-1);
			printTree (root);
			printTreeLevel (root);
		}
	}

}
