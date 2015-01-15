/**
 * 
 */
package com.code.saurabh.datastructure.linklist;

/**
 * @author saurabh.gupta
 *
 */
public class LinkedListNode {
	Object key;
	public LinkedListNode next;
	public LinkedListNode prev;
	
	public LinkedListNode (Object k) {
		key = k;
		next = null;
		prev = null;
	}

	public Object getKey() {
		return key;
	}

	public LinkedListNode getNext() {
		return next;
	}

	/**
	 * <pre>
	 * </pre>
	 * @return
	 */
	public LinkedListNode getPrev() {
		return prev;
	}
}
