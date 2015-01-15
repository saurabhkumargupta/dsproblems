/**
 * 
 */
package com.code.saurabh.datastructure.linklist;



/**
 * @author saurabh.gupta
 *
 */
public class ReverseLinklist {

	static DoublyLinkedList linklist = new DoublyLinkedList();
	
	static void updateDLLTail (DoublyLinkedList list) {
		//THIS FOLLOWING OPERATION DOES NOT HAVE ANYTHING TO DO WITH REVERSE LOGIC.
		//THIS IS DONE TO MAINTAIN PROPER TAIL POINTER IN LINKLIST
		//AS WE ARE USING DLL AS UNDERLYING LL, AND THIS REVERSE LOGIC DOES MOT MODIFY TAIL POINTER, WHICH CAUSE ISSUES IN ADDING
		//NEW ELEMENTS IN LINKLIST.
		//AS ADD FUNCTION OF DLL, USES THIS TAIL POINTER TO ADDITION, IF WE WERE NOT USING THIS TAIL POINTER, EVEN IN THAT CASE
		//THERE WONT BE ANY PROBLEM
		LinkedListNode node = list.head;
		LinkedListNode prev = null;
		while (node != null) {
			prev = node;
			node = node.next;
		}
		list.tail = prev;
	}

	static void createLinkList (int [] input) {
		for (int i = 0; i < input.length; i++) {
			linklist.add(input[i]);
		}
	}
	static LinkedListNode reverse_k (LinkedListNode head, int k) {
		LinkedListNode prev = null;
		LinkedListNode cur = head;
		LinkedListNode next = head;
		
		while (k > 0 && cur != null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
			k--;
		}
		head.next = cur;
		head = prev;
		return head;
	}

	/*
	 * <pre>
	 * 1. Reverse first k nodes, and get new head of list
	 * 2. Get head of remaining list (Size - k) as oldhead.next will now be pointing to start of remaining list
	 * 3. recusrively call this function with this newHead
	 * 4. To build complete list, merge oldhead of old list and newhead of remaining list.
	 * 		oldhead.next was pointing to previous head of newlist, and it should point to newHead of remaining list.
	 * 		So oldhead.next = newHeadOfRemainingList
	 * 		and this makes complete list. This is done recursively for all sub-lists 
	 * </pre>
	 */
	static LinkedListNode rec_reverse_k (LinkedListNode oldhead, int k) {
		if (oldhead == null) {
			return null;
		}
		LinkedListNode newhead = reverse_k (oldhead, k);
		LinkedListNode nextListHead = oldhead.next;
		LinkedListNode nextListNewHead = rec_reverse_k (nextListHead, k);
		oldhead.next = nextListNewHead;
		return newhead;
	}

	static void reverse_linklist (DoublyLinkedList list, int k) {
		if (k <= 0) {
			return;
		}
		LinkedListNode newList = rec_reverse_k (linklist.head, k);
		list.head = newList;
		updateDLLTail (list);
		return;
	}
	
	public static void main(String[] args) {
		int [][] input = {
				{1,2,3,4,5,6,7,8},
				{1,2,3,4,5,6,7,8,9},
				{1,2,3,4,5,6,7,8,9,10},
		};

		for (int i = 0; i < input.length; i++) {
			System.out.println();
			System.out.println("Input: ");
			createLinkList (input[i]);
			linklist.print();
			reverse_linklist(linklist, 2);
			System.out.println();
			linklist.print();
		}
		
//		for (int i = 0; i < input.length; i++) {
//			System.out.println();
//			System.out.println("Input: ");
//			createLinkList (input[i]);
//			linklist.print();
////			LinkedListNode newList = reverse_k (linklist.head, 6);
//			LinkedListNode newList = rec_reverse_k(linklist.head, 2);
////			LinkedListNode newList = rec_reverse_k(linklist.head, 0);
//			linklist.head = newList;
//			System.out.println();
//			linklist.print();
//		}
//		
//		LinkedListNode newList = rec_reverse_k(linklist.head, linklist.getSize());
//		linklist.head = newList;
//		System.out.println();
//		linklist.print();
	}

}
