/**
 * 
 */
package com.code.saurabh.datastructure.linklist;

/**
 * @author saurabh.gupta
 *
 */
public class DoublyLinkedList {
	
	protected LinkedListNode head;
	protected LinkedListNode tail;
	protected int size;
	
	public int getSize () {
		return size;
	}
	public DoublyLinkedList () {
		head = tail = null;
	}

	public LinkedListNode getHead () {
		return head;
	}

	public LinkedListNode getTail () {
		return tail;
	}

	public void add (Object key) {
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
	}

	public Object remove (Object key) {
		LinkedListNode node = findNode(key);
		if (node != null) {
			if (node.prev != null) {
				node.prev.next = node.next;
			}
			if (node.next != null) {
				node.next.prev = node.prev;
			}
			node.next = null;
			node.prev = null;
			size--;
			return node.key;
		}
		return null;
	}

	//removes element which was inserted first
	public Object removeLast () {
		LinkedListNode node = getHead();
		if (node != null) {
			head = node.next;
			if (node.next != null) {
				node.next.prev = null;
			}
			size--;
			return node.key;
		}
		return null;
	}
	private LinkedListNode findNode (Object key) {
		LinkedListNode node = head;
		while (node != null) {
			if (node.key.equals(key)) {
				return node;
			}
			node = node.next;
		}
		return node;
	}
	
	public void printReverse () {
		LinkedListNode node = tail;
		while (node != null) {
			System.out.print(node.key + ", ");
			node = node.prev;
		}
		System.out.println();
	}

	public void print () {
		LinkedListNode node = head;
		while (node != null) {
			System.out.print(node.key + ", ");
			node = node.next;
		}
//		System.out.println();
	}
	
	public String toString () {
		LinkedListNode node = head;
		StringBuilder sb = new StringBuilder();
		while (node != null) {
			sb.append(node.key).append(", ");
			node = node.next;
		}
		sb.replace(sb.lastIndexOf(","), sb.length(), "");
		return sb.toString();
	}

	public static void main(String[] args) {
		DoublyLinkedList dll = new DoublyLinkedList ();
		Integer val = new Integer(50);
		dll.add(10);
		dll.add(val);
		dll.add(30);
		dll.add(20);
		dll.add(70);
		dll.print();
		System.out.println (dll.remove(val));
		dll.print();
		System.out.println(dll.toString());
		System.out.println();
		dll.printReverse();
		
		System.out.println();
		Object value = dll.removeLast();
		while (value != null) {
			System.out.print( (int) value + ", ");
			value = dll.removeLast();
		}
		System.out.print( value + ", ");
		value = dll.removeLast();
		System.out.print( value + ", ");
	}

}
