/**
 * 
 */
package com.company.problems;

import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;
import com.code.saurabh.datastructure.linklist.DoublyLinkedList;
import com.code.saurabh.datastructure.linklist.LinkedListNode;
import com.code.saurabh.datastructure.stack.Stack;
import com.code.saurabh.datastructure.stack.Stack.StackNode;
import com.code.saurabh.util.UtilClass;

/**
 * @author saurabh.gupta
 *
 */
//public class Interview {
//        1
//     2      3
//   4      5   6
// 7   8
//
//8 7  4 5 6  3 2  1
//
//
//
//printFunction (TreeNode root) {
//   Stack out = new Stack ();
//   Stack in = new Stack ();
//   
//   Stack finalOut = new Stack (); 
//   
//   in.add (root);
//   out.add (in);
//   int level = 0;
//   finalOut.add (in);
//   
//   while (1) {
//       Stack temp = out.pop ();
//       in = new Stack ();
//       
//       while (!temp.isEmpty ()) {
//          TreeNode node = temp.pop ();
//          if (node != null) {
//              if (level % 2 == 0) {
//                  in.add (node.left);
//                  in.add (node.right);
//              }
//              else {
//                  in.add (node.right);
//                  in.add (node.left);
//              }
//          }
//       }
//       if (!in.isEmpty ()) {
//           out.add (in);
//           finalOut.add (in);
//       }
//
//       if (!out.isEmpty ()) {
//           break;
//       }
//       level++;
//   }
//   
//   while (!finalOut.isEmpty ()) {
//       in = finalOut.pop ();
//       while (!in.isEmpty ()) {
//           node = in.pop ();
//           print (node);
//       }
//   }
//   
//   
//}

/**
 *  We have to create list of elements at each level and insert that list into stack
 *  This list of elements can be created using 
 *  	DLL,
 *  	SLL, or 
 *  	simply using stack itself (with getNext() function which gives traversal functionality to stack). Implemented below 
 * @author saurabh.gupta
 *
 */
public class MoveInSync {

	public static void treePrintUsingDLL (TreeNode root) {
		Stack outer = new Stack ();
		DoublyLinkedList dll = new DoublyLinkedList ();
		
		dll.add(root);
		outer.push(dll);
		
		int level = 0;
		while (true) {
			DoublyLinkedList currentLevelElement = (DoublyLinkedList) outer.peek();
			LinkedListNode iter = currentLevelElement.getTail();

			DoublyLinkedList newLevelElement = new DoublyLinkedList ();

			while (iter != null) {
				if (UtilClass.isOdd(level)) {					
					TreeNode node = (TreeNode) iter.getKey();
					if (node.left != null) { 
						newLevelElement.add(node.left);
					}
					if (node.right != null) { 
						newLevelElement.add(node.right);
					}
				}
				else {
					TreeNode node = (TreeNode) iter.getKey();
					if (node.right != null) { 
						newLevelElement.add(node.right);
					}
					if (node.left != null) { 
						newLevelElement.add(node.left);
					}
				}
				iter = iter.getPrev();
			}
			if (newLevelElement.getHead() == null) {
				break;				
			}
			outer.push (newLevelElement);
			level++;
		}
		
		while (!outer.isEmpty()) {
			DoublyLinkedList currentLevelElement = (DoublyLinkedList) outer.pop();
			currentLevelElement.print();
		}
	}
	
	public static class LocalStack extends Stack {

		StackNode next = null;
		public StackNode getNext () {
			if (next == null) {
				next = top;
			}
			else {
				next = next.getNext();
			}
			return next;
		}
	}

	/**
	 * <pre>
	 * Approach is:
	 * At each level (starting from root considering it level 0), insert all elements in a stack
	 * Insert this stack into another stack
	 * 
	 * peek outer stack and traverse all element in inner stack (non popping, writing kind of iterator for this).
	 * 
	 * Get another new stack and add child of this level in following order: 
	 * 	if current level is even: insert left, then right
	 * 	if current level is odd: insert right, then left 
	 *	Now, insert this new stack on outer stack
	 *
	 * this will have elements in desired order
	 * </pre>
	 * @param root
	 */
	static void printSmallFunction (TreeNode root) {
		Stack out = new LocalStack ();
		Stack in = new LocalStack ();

		in.push(root);
		out.push (in);

		int level = 0;

		while (true) {
			LocalStack temp = (LocalStack) out.peek ();
			in = new LocalStack ();

			StackNode snode = null;
			while ((snode = (StackNode) temp.getNext()) != null) {
				TreeNode node = (TreeNode) snode.getKey();
				if (node != null) {
					if (level % 2 == 0) {
						if (node.left != null)
							in.push (node.left);
						if (node.right != null)
							in.push (node.right);
					}
					else {
						if (node.right != null)
							in.push (node.right);
						if (node.left != null)
							in.push (node.left);
					}
				}
				else
					break;
			}

			if (!in.isEmpty ()) {
				out.push (in);
			}
			else {
				break;
			}
			level++;
		}

		while (!out.isEmpty ()) {
			in = (Stack) out.pop ();
			while (!in.isEmpty ()) {
				TreeNode node = (TreeNode) in.pop ();
				System.out.print(node.key + ", ");
			}
		}
	}

	static void printFunction (TreeNode root) {
		Stack out = new LocalStack ();
		Stack in = new LocalStack ();
		Stack finalOut = new LocalStack (); 

		in.push(root);
		out.push (in);
		finalOut.push (in);

		int level = 0;

		while (true) {
			LocalStack temp = (LocalStack) out.pop ();
			in = new LocalStack ();

			while (true) {
				StackNode snode = (StackNode) temp.getNext();
				if (snode == null) {
					break;
				}
				TreeNode node = (TreeNode) snode.getKey();
				if (node != null) {
					if (level % 2 == 0) {
						if (node.left != null)
							in.push (node.left);
						if (node.right != null)
							in.push (node.right);
					}
					else {
						if (node.right != null)
							in.push (node.right);
						if (node.left != null)
							in.push (node.left);
					}
				}
				else
					break;
			}
			if (!in.isEmpty ()) {
				out.push (in);
				finalOut.push (in);
			}

			if (out.isEmpty ()) {
				break;
			}
			level++;
		}

		while (!finalOut.isEmpty ()) {
			in = (Stack) finalOut.pop ();
			while (!in.isEmpty ()) {
				TreeNode node = (TreeNode) in.pop ();
				System.out.print(node.key + ", ");
			}
		}
	}

	public static void main (String[] args) {
		Tree t = new Tree ();
		int [][] input =  {
				{10, 8, 15, 20, 11, 9, 7, 6},
				{10, 8, 13, 4, 3, 5, 11, 14},
				{100, 50, 30, 20, 35, 55, 200, 150, 110, 300, 250},
		};
		
		for (int i = 0; i < input.length; i++) {
			t = new Tree ();
			t.createTree(input[i]);
			t.inorder();

			System.out.println("printFunction: ");
			printFunction (t.root);
			System.out.println();
			
			System.out.println("printSmallFunction: ");
			printSmallFunction (t.root);
			System.out.println();

			System.out.println("treePrintUsingDLL: ");
			treePrintUsingDLL (t.root);
			System.out.println();
			
			System.out.println();
			System.out.println();
		}
	}
}
