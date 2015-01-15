package com.code.saurabh.DataSructure.tree;

import com.code.saurabh.datastructure.queue.Queue;
import com.code.saurabh.datastructure.stack.Stack;
import com.code.saurabh.util.UtilClass;

public class LevelOrderTreeTraversal extends TreeTraversal {

	LevelOrderTreeTraversal (Tree t) {
		super(t);
	}

	/**
	 * <pre>
	 * In this level order traversal, we are maintaining two queues.
	 * first is current queue which is being processed (current level queue)
	 * Second is nextLevelQueue, in which we are inserting children of current level queue
	 * When currentLevel Queue is empty, then currentLevelQueue is replaced with nextLevelQueue.
	 * but if nextLevelQueue is empty, then we break loop
	 * 
	 * No Marker elements are used
	 * </pre>
	 */
	public void levelorderQueueOnly () {
		Queue currentLevelQueue = new Queue ();
		currentLevelQueue.enqueue(tree.root);
		
		System.out.println("levelorderQueueOnly");
		while (true) {
			Queue nextLevelQueue = new Queue ();
			System.out.println();
			while (!currentLevelQueue.isEmpty()) {
				TreeNode node = (TreeNode) currentLevelQueue.dequeue();
				System.out.print(node.key + ", ");
				if (node.left != null)
					nextLevelQueue.enqueue(node.left);
				if (node.right != null)
					nextLevelQueue.enqueue(node.right);
			}
			if (nextLevelQueue.isEmpty()) {
				break;
			}
			currentLevelQueue = nextLevelQueue;
		}
	}
	/**
	 * <pre>
	 * In level order traversal, all nodes of tree at each level is inserted in the queue. 
	 * And at the end of each level, there is marker also inserted in the queue which is used to identify level change
	 * 1) Insert root element
	 * 2) insert marker element
	 * 3) loop until queue is empty
	 * if next element of queue is marker element and queue is empty now, this is end
	 * if next element is marker but still queue is not empty, then add this marker element to the queue.
	 * </pre>
	 */
	public void levelorder () {
		Queue q = new Queue ();
		int level = 0;
		System.out.println();
		System.out.println("Level Order Traversal");
		
		if (tree.isEmpty()) {
			System.out.println("Tree is Empty");
			return;
		}
		
		TreeNode nullNode = new TreeNode (-1);

		q.enqueue(tree.root);
		q.enqueue(nullNode);

		System.out.println();
		System.out.print(level + " : ");
		while (!q.isEmpty()) {
			TreeNode node = (TreeNode) q.dequeue();
			if (node.key == -1) {
				if (q.isEmpty()) {
					break;
				}
				level++;
				System.out.println();
				System.out.print(level + " : ");
				q.enqueue(nullNode);
				continue;
			}
			
			System.out.print(node.key + ", ");
			if (node.left != null)
				q.enqueue(node.left);
			if (node.right != null)
				q.enqueue(node.right);
		}
	}

	/**
	 * <pre>
	 * Just like spiral one.
	 * All children of current level (ODD) are inserted into new Stack first left child, then right child
	 * All children of current level (EVEN) are inserted into new Stack first right child, then left child
	 * While parsing current level, print only first element of stack
	 * </pre>
	 * @param root
	 */
	public void levelorderAlternateCornersStackOnly () {
		Stack oldStack = new Stack ();
		int level = 0;

		System.out.println ();
		System.out.print("levelorderAlternateCornersStackOnly");
		oldStack.push(tree.root);

		while (true) {
			Stack newStack = new Stack ();
			System.out.println ();
			
			boolean isFirst = true;
			while (!oldStack.isEmpty()) {
				TreeNode node = (TreeNode) oldStack.pop();
				if (isFirst) {
					System.out.print(node.key + ", ");
					isFirst = false;
				}
				if (UtilClass.isEven(level)) { //Even
					if (node.right != null)
						newStack.push(node.right);
					if (node.left != null)
						newStack.push(node.left);
				}
				else if (UtilClass.isOdd(level)) { //ODD
					if (node.left != null)
						newStack.push(node.left);
					if (node.right != null)
						newStack.push(node.right);
				}
			}
			level++;
			if (newStack.isEmpty()) {
				break;
			}
			oldStack = newStack;
		}
	}
	
	public void levelorderAlternateCornersQueueOnly () {
		Queue currentLevelQueue = new Queue ();
		currentLevelQueue.enqueue(tree.root);
		int level = 0;
		System.out.println("levelorderAlternateCornersQueueOnly");
		while (true) {
			TreeNode node = null;
			Queue nextLevelQueue = new Queue ();
			System.out.println();

			boolean levelPrinted = false;
			while (!currentLevelQueue.isEmpty()) {
				node = (TreeNode) currentLevelQueue.dequeue();
				
				//For ODD levels,, only first element of the queue should be printed
				if (UtilClass.isOdd(level) && levelPrinted == false) {
					levelPrinted = true;
					System.out.print(node.key + ", ");
				}
				if (node.left != null)
					nextLevelQueue.enqueue(node.left);
				if (node.right != null)
					nextLevelQueue.enqueue(node.right);
			}

			//For even levels, last element of currentLevelQueue should be printed
			if (node != null && UtilClass.isEven(level)) {
				System.out.print(node.key + ", ");
			}
			if (nextLevelQueue.isEmpty()) {
				break;
			}
			currentLevelQueue = nextLevelQueue;
			level++;
		}
	}

	/**
	 * <pre>
	 * Given a binary tree, print in spiral order 
	 * 
               1
             /   \
            2      3
           / \    / \
          4   5  6   7
         / \
        8   9
       /     \
      10      11
  
  print - > 1, 2, 7, 8, 11
  
  1) Normal level order traversal. each level elements are inserted into queue with markers to denote level change
  2) At each level, keep first and last element of each label
  3) when there is label change, print first element for odd level and last element for even level. And reset these counters
  before moving to next level.
  
	 * </pre>
	 */
	public void levelorder_alternate_corners () {
		Queue q = new Queue ();
		int level = 0;
		System.out.println();
		System.out.println("Level Order Traversal alternate corners");
		
		if (tree.isEmpty()) {
			System.out.println("Tree is Empty");
			return;
		}
		
		TreeNode nullNode = new TreeNode (-1);

		q.enqueue(tree.root);
		q.enqueue(nullNode);

		int firstelement_of_level = Integer.MAX_VALUE;
		int lastelement_of_level = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			TreeNode node = (TreeNode) q.dequeue();
			if (node.key == -1) {

				//Level change, print for previous level
				if (level%2 == 0) {
					System.out.println(level + " : "  +lastelement_of_level);
				}
				else if (level%2 != 0) {
					System.out.println(level + " : "  +firstelement_of_level);
				}

				if (q.isEmpty()) {
					break;
				}
				level++;
				q.enqueue(nullNode);
				firstelement_of_level = Integer.MAX_VALUE;
				lastelement_of_level = Integer.MAX_VALUE;
				continue;
			}
			
			if (firstelement_of_level == Integer.MAX_VALUE)
				firstelement_of_level = node.key;
			lastelement_of_level = node.key;

			if (node.left != null)
				q.enqueue(node.left);
			if (node.right != null)
				q.enqueue(node.right);
		}
	}
	
	/**
	 * <pre>
	 * This extended queue class provide a method which return next element in queue from starting.
	 * Each call modifies next return;
	 * 
	 * i.e. if Queue insert in order: 10 (front) ,6,4,8,3 (rear)
	 * then 
	 * nextMember will return
	 * in 1st call: 10,
	 * in next call : 6
	 * in next call : 4
	 * so on
	 * But it does not modify queue itself
	 * 
	 * also provides reset method which will reset iterator to front
	 * </pre>
	 * @author saurabh.gupta
	 *
	 */
	class QueueIterator extends Queue {
		QueueNode next = null;

		public Object nextMember () {
			if (next == null) {
				next = front;
			}
			else {
				next = next.getNext();
				if (next == null)
					return null;
			}
			return next.getKey();
		}
		
		public void resetIterator () {
			next = null;
		}
	}

	/**
	 * <pre>
	 * All elements are inserted in the Queue while traversing Whole tree
	 * Once all elements of tree are in queue, then we start printing elements 
	 * (Alternative: in place of Queue, this can be done using linkedList, as we have to maintain all elements
	 *         1
             /   \
            2      3
           / \    / \
          4   5  6   7
         / \
        8   9
       /     \
      10      11
  
      link-list will be:  1 -> M -> 2 -> 3 -> M -> 4 ->5 -> 6 -> 7 -> M -> 8 -> 9 -> M -> 10 -> 11
	 * Queue's implementation is also a special type of link-list
	 * )
	 * 1) In this level order traversal, all elements are inserted into
	 * queue along with marker elements at level change.
	 * 2) When all elements are inserted in queue, then print queue.
	 * 3) While printing, at each marker there is level change, which should be printed in next line
	 * </pre>
	 */
	public void levelorder_part_2 () {
		QueueIterator q = new QueueIterator ();
		
		if (tree.isEmpty()) {
			System.out.println("Tree is Empty");
			return;
		}
		
		TreeNode nullNode = new TreeNode (-1);
		q.enqueue(tree.root);
		q.enqueue(nullNode);

		TreeNode node =  (TreeNode) q.nextMember();
		while (node != null) {
			if (node.key == -1) {
				node =  (TreeNode) q.nextMember();
				if (node == null) {
					break;
				}
				q.enqueue(nullNode);
				continue;
			}
			
			if (node.left != null)
				q.enqueue(node.left);
			if (node.right != null)
				q.enqueue(node.right);
			node =  (TreeNode) q.nextMember();
		}
		
		System.out.println();
		System.out.println("Level Order Traversal Part _2");
		int level = 0;

		System.out.println();
		System.out.print(level + " : ");
		while (!q.isEmpty()) {
			node = (TreeNode) q.dequeue();
			if (node.key == -1) {
				if (q.isEmpty())
					break;
				level++;
				System.out.println();
				System.out.print(level + " : ");
				continue;
			}
			System.out.print(node.key + ", ");
		}
		System.out.println();
	}
	
	/**
	 *  * Given a binary tree, print in spiral order 
 * <pre>
               1
             /   \
            2      3
           / \    / \
          4   5  6   7
         / \
        8   9
       /     \
      10      11
  print - > 1, 2, 7, 8, 11
  
      1) continuing 'levelorder_part_2' logic, all level elements along with markers are added to the queue
      2) At time of printing, we keep counter of how many elements has been printed for this label (always 1)
       and also keep label counter. 
      3) When there is label change (market found) label counter is incremented and total printed elements counter reset to 0.  
      4) for odd level, we have to print first element of the level, so we check if number of elements printed for this odd level
      is 0, then print it else skip.
      5) for even levels, we have to print last element of the level. For this, at each even level we check if next queue element
      is marker, if yes then print this element 
  <pre>
	 */
	public void levelorder_alternate_corners_part_2 () {
		QueueIterator q = new QueueIterator ();
		
		if (tree.isEmpty()) {
			System.out.println("Tree is Empty");
			return;
		}
		
		TreeNode nullNode = new TreeNode (-1);
		q.enqueue(tree.root);
		q.enqueue(nullNode);

		TreeNode node =  (TreeNode) q.nextMember();
		while (node != null) {
			if (node.key == -1) {
				node =  (TreeNode) q.nextMember();
				if (node == null) {
					break;
				}
				q.enqueue(nullNode);
				continue;
			}
			
			if (node.left != null)
				q.enqueue(node.left);
			if (node.right != null)
				q.enqueue(node.right);
			node =  (TreeNode) q.nextMember();
		}
		
		System.out.println();
		System.out.println("Level Order Traversal ALternate cornerrs part 2");
		int level = 0;

		System.out.println();
		System.out.print(level + " : ");
		int count = 0;
		while (!q.isEmpty()) {
			node = (TreeNode) q.dequeue();
			if (node.key == -1) {
				if (q.isEmpty())
					break;
				level++;
				count = 0;
				System.out.println();
				System.out.print(level + " : ");
				continue;
			}
			TreeNode nextMember = (TreeNode) q.nextMember();
			if ( level % 2 == 0 
					&& ( nextMember == null || nextMember.key == -1 )) {
				System.out.print(node.key + ", ");
			}
			else if (level % 2 != 0 && count == 0) {
				System.out.print(node.key + ", ");
				count ++;
			}
			q.resetIterator();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		TraversalTesting ();
	}

	private static void TraversalTesting() {
		int[][] input = {
				{50,40,90,35,30,45,84,89},
				{1,2,3,4},
				{9,8,7,6},
				{10,11,9,7,12,13,4, 8},
				{50,40,30,20,35,45,60,55,70,65,80},
				{100,50,200,25,75,150,300,10,40,60,90,125,175,250,400}
		};

		LevelOrderTreeTraversal tree = null;
		for (int i = 0; i < input.length; i++) {
			System.out.println();
			Tree t = new Tree ();
			for (int j = 0; j < input[i].length; j++) {
				t.addToBST(input[i][j]); 
			}
			tree = new LevelOrderTreeTraversal (t);
			
			tree.levelorder();
			tree.levelorder_part_2();
			tree.levelorderQueueOnly();
			tree.levelorder_alternate_corners();
			tree.levelorder_alternate_corners_part_2();
			tree.levelorderAlternateCornersQueueOnly();
			tree.levelorderAlternateCornersStackOnly();
		}
	}

}
