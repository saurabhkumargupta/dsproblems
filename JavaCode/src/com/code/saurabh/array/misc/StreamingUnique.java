package com.code.saurabh.array.misc;

import com.code.saurabh.datastructure.linklist.DoublyLinkedList;
import com.code.saurabh.datastructure.linklist.LinkedListNode;

public class StreamingUnique {

	static class DoublyLinkedListLocal extends DoublyLinkedList {
		public LinkedListNode addNew (char key) {
			LinkedListNode node = new LinkedListNode (key);
			if (head == null) {
				head = node;
			}
			else {
				tail.next = node;
				node.prev = tail;
			}
			tail = node;
			size++;
			return node;
		}
		
		void removeNode (LinkedListNode node) {
			if (node == null) {
				return;
			}
			if (head == node) {
				head = head.next;
				if (head != null)
					head.prev = null;
			}
			else {
				if (node.prev != null) {
					node.prev.next = node.next;
				}
				if (node.next != null) {
					node.next.prev = node.prev;
				}
				node.next = null;
				node.prev = null;
			}
			size--;
		}
	}
	static void f (char[] input) {
		LinkedListNode[] arr = new LinkedListNode [256];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = null;
		}
		
		DoublyLinkedListLocal dll = new DoublyLinkedListLocal();
		LinkedListNode dummy = new LinkedListNode (null);
		
		for (int i = 0; i < input.length; i++) {
			char c = input[i];
			if (arr[c] == null) {
				arr[c] = dll.addNew(c);
			}
			else {
				dll.removeNode(arr[c]);
				arr[c] = dummy;
			}
			if (dll.getHead() != null) {
				System.out.println("Unique after: " + input[i] + " is: " + dll.getHead().getKey());				
			}
			else {
				System.out.println("No Unique after: " + input[i]);
			}
		}
	}
	public static void main(String[] args) {
		String in = "abcdefaabcdef";
		char[] input = in.toCharArray();
		f (input);
	}

}
