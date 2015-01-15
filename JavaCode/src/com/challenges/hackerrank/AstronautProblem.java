package com.challenges.hackerrank;
/*
 * https://www.hackerrank.com/challenges/journey-to-the-moon/submissions/code/10739554
 * DID NOT COMPLETED
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AstronautProblem {

	static public class LinkedListNode {
		Object key;
		LinkedListNode next;
		LinkedListNode prev;
		
		LinkedListNode (Object k) {
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

		public LinkedListNode getPrev() {
			return prev;
		}
	}
	
	static public class DoublyLinkedList {
		
		LinkedListNode head;
		LinkedListNode tail;
		int size;
		
		public int getSize () {
			return size;
		}

		public DoublyLinkedList () {
			head = tail = null;
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
	}
	
	public static void main(String[] args) {
		readInput();
	}

//	static class Country {
//		int count;
//	}
	static void readInput () {
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
		
//		String file = "/Users/saurabh.gupta/Documents/Personal/Interview/Round_2_preparation_material/JavaCode/Inputs/astrnaut_input09.txt";
//		BufferedReader br = null;
//		try {
//			br = new BufferedReader (new FileReader (file));
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
		
		try {
			String[] line = br.readLine().trim().split(" ");
			int n = Integer.valueOf(line[0]);
			int l = Integer.valueOf(line[1]);
			
			Map<Integer, DoublyLinkedList> map = new HashMap<Integer, DoublyLinkedList> ();
			for (int i = 0; i < l; i++) {
				line = br.readLine().trim().split(" ");
				int n1 = Integer.valueOf(line[0]);
				int n2 = Integer.valueOf(line[1]);
				if (map.containsKey(n1)) {
					DoublyLinkedList c1 = map.get(n1);
					if (map.containsKey(n2)) {
						DoublyLinkedList c2 = map.get(n2);
						if (c1.equals(c2)) {
							//n1 & n2 pointing to same country
							continue;
						}
						else {
							//n1 & n2 are pointing to different country, moving c2 's data to c1 & marking n2 to n1's node
							mergeSmallAndMove (c1, c2, map);
						}
					}
					else {
						//n1 & n2 are from same country
						c1.add(n2);
						map.put(n2, c1);
					}
				}
				else if (map.containsKey(n2)) {
					DoublyLinkedList c2 = map.get(n2);
					if (map.containsKey(n1)) {
						DoublyLinkedList c1 = map.get(n1);
						if (c1.equals(c2)) {
							//n1 & n2 pointing to same country
							continue;
						}
						else {
							//n1 & n2 are pointing to different country, moving c1 's data to c2 & marking n1 to n2's node
							mergeSmallAndMove (c1, c2, map);
						}
					}
					else {
						//n1 & n2 are from same country
						c2.add(n1);
						map.put(n1, c2);
					}
				}
				else {
					//n1 & n2 doesnt exit. adding to map
					DoublyLinkedList c = new DoublyLinkedList();
					c.add(n1);
					c.add(n2);
					map.put(n1, c);
					map.put(n2, c);
				}
			}
			
			int total = 1;

			int totalCountries = 0;
			Set<DoublyLinkedList> visited = new HashSet<DoublyLinkedList> ();
			Iterator<Integer> iterator = map.keySet().iterator();
			List<Integer> countries = new ArrayList<Integer> ();
			while (iterator.hasNext()) {
				Integer k = iterator.next();
				DoublyLinkedList dll = map.get(k);
				if (visited.contains(dll)) {
					continue;
				}
				visited.add(dll);
				System.out.println("Size of Countriy: " + (totalCountries+1) + " is " + dll.size);
				countries.add(dll.size);
				totalCountries++;
			}

			
			if (totalCountries == 1) {
				System.out.println(0);
				return;
			}
			total = 0;
			Integer[] arr = countries.toArray(new Integer[0]);
			for (int i = 0; i < arr.length-1; i++) {
				for (int j = i+1; j < arr.length; j++) {
					total = total + arr[i] * arr[j];
				}
			}
			System.out.println(total);
			System.out.println("Countries: " + totalCountries);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void mergeSmallAndMove(DoublyLinkedList c1, DoublyLinkedList c2, Map<Integer, DoublyLinkedList> map) {
		c1.tail.next = c2.head;
		c2.head.prev = c1.tail;
		c1.tail = c2.tail;
		c1.size += c2.size;
		LinkedListNode node = c2.head;
		while (node != null) {
			map.put((Integer)node.key, c1);
			node = node.next;
		}

//		if (c2.size < c1.size) {
//			//merging c2 to c1
//			c1.tail.next = c2.head;
//			c2.head.prev = c1.tail;
//			c1.tail = c2.tail;
//			c1.size += c2.size;
//			LinkedListNode node = c2.head;
//			while (node != null) {
//				map.put((Integer)node.key, c1);
//				node = node.next;
//			}
//		}
//		else {
//			//merging c1 to c2
//			c2.tail.next = c1.head;
//			c1.head.prev = c2.tail;
//			c2.tail = c1.tail;
//			c2.size += c1.size;
//			LinkedListNode node = c1.head;
//			while (node != null) {
//				map.put((Integer)node.key, c2);
//				node = node.next;
//			}
//		}
	}
}
