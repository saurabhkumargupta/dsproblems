/**
 * 
 */
package com.company.hike;

import java.util.HashMap;
import java.util.Map;

import com.code.saurabh.util.Pair;
import com.company.hike.HikeLRU.SimpleList.ListNode;
import com.sun.jmx.snmp.ThreadContext;

/**
 * @author saurabh.gupta
 *
 */
public class HikeLRU {

	public class SimpleList {
		ListNode head = null;
		ListNode tail = null;
		int count = 0;

		public class ListNode {
			Object key;
			ListNode next;
			ListNode prev;
			
			ListNode (Object key) {
				this.key = key;
				this.next = null;
				this.prev = null;
			}
			
			public Object getKey () {
				return key;
			}
		}
		
		public SimpleList () {
		}
		
		public ListNode add (Object key) {
			ListNode node = new ListNode (key);
			node.next = null;
			node.prev = null;

			if (head == null) {
				head = node;
				tail = node;
			}
			else {
				add (node);
			}
			count++;
			return node;
		}
		
		public void add (ListNode node) {
			node.next = head;
			node.prev = null;
			head = node;
			if (node.next != null)
				node.next.prev = node;
		}

		public void remove (ListNode node) {
			if (node != null) {
				if (node.prev != null)
					node.prev.next = node.next;
				if (node.next != null)
					node.next.prev = node.prev;
			}
		}
		
		public void removeAndAddToFront (ListNode node) {
			remove (node);
			node.next = null;
			node.prev = null;
			add(node);
		}
		
		public ListNode removeLast () {
			ListNode node = tail;
			if (tail != null && tail.prev != null) {
				tail = tail.prev;
				tail.next = null;
			}
			return node;
		}
		
		public void dumpList () {
			ListNode node = head;
			while (node != null) {
				System.out.print(node.key + ", ");
				node = node.next;
			}
		}
	}

	final static int MAX_CACHE_SIZE = 100;
	int cachesize = 0;
	//this *2 is used to avoid re-hashing or we can set loading factor as 1 in place of default .75
	Map<String, Pair<Object, ListNode> > keyValue = new HashMap <String, Pair<Object,ListNode> > (MAX_CACHE_SIZE*2);
	SimpleList list = new SimpleList ();
	
	synchronized  public void add (String key, String value) {
		if (cachesize == MAX_CACHE_SIZE) {
			handleCacheFull ();
		}
		
		ListNode node = search (key);
		if (node == null) {
			ListNode listnode = list.add(value);
			keyValue.put(key, new Pair<Object, ListNode> (key, listnode));
			cachesize++;
		}
		else {
			node.key = value;
		}
	}

	private void handleCacheFull() {
		ListNode node = list.removeLast ();
		keyValue.remove(node.getKey());
	}

	synchronized  public void delete (String key) {
		if (keyValue.containsKey(key)) {
			Pair<Object, ListNode> pair = keyValue.get(key);
			ListNode node = pair.getSecond();

			//remove from map
			keyValue.remove(key);
			//remove from list
			list.remove(node);
			cachesize--;
		}
	}

	synchronized  public ListNode search (String key) {
		if (keyValue.containsKey(key)) {
			ListNode node = keyValue.get(key).getSecond();
			list.removeAndAddToFront(node);			
			return node;
		}
		return null;
	}
	
	public void dumpCacheList () {
		System.out.println("List Dump: ");
		list.dumpList();
		System.out.println();
	}

	public static class SampleRun extends Thread {
		HikeLRU lru = null;

		public SampleRun (HikeLRU lru) {
			this.lru = lru;
		}

		public void run () {
			for (int i = 0; i < 10; i++) {
				lru.add(String.valueOf(i), "Thread-Id: " + Thread.currentThread().getId() + " ->" + String.valueOf(i));
			}
			
//			for (int i = 0; i < 1; i++) {
//				lru.delete(String.valueOf(i));
//			}

			for (int i = 0; i < 10; i++) {
				String str = (String) lru.search(String.valueOf(i)).key;
				System.out.println("Thread-" + Thread.currentThread().getId()+ " : " + str);
			}
		}
	}

	public static void main(String[] args) {
		HikeLRU lru = new HikeLRU ();
		Thread t1 = new SampleRun (lru);
		Thread t2 = new SampleRun (lru);
		t1.start();
		t2.start();
		lru.dumpCacheList ();
	}
}
