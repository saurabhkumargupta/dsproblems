package com.challenges.hackerrank;

/*
 * https://www.hackerrank.com/challenges/rooted-tree/editorial
 * NOT DONE
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RootedTree {

	static public class Pair <T1, T2> {
		T1 first;
		T2 second;

		public Pair (T1 f, T2 s) {
			this.first = f;
			this.second = s;
		}

		public Pair() {
		}

		public void setFirst (T1 f) {
			this.first = f;
		}

		public T1 getFirst () {
			return first;
		}

		public void setSecond (T2 s) {
			this.second = s;
		}
		
		public T2 getSecond () {
			return second;
		}
		
		public Pair<T1, T2> getPair () {
			return this;
		}

		public void assign(Pair<T1, T2> p) {
			this.first = p.first;
			this.second = p.second;
		}

		public String toString () {
			StringBuilder sb = new StringBuilder ();
			sb.append(first);
			sb.append(",");
			sb.append(second);
			return sb.toString();
		}
	}
	static public class Queue{
		public class QueueNode {
			Object key;
			QueueNode next;
			
			QueueNode (Object K) {
				key = K;
				next = null;
			}
			
			public QueueNode getNext () {
				return next;
			}
			
			public Object getKey () {
				return key;
			}
		}

		protected QueueNode front;
		QueueNode rear;
		int size;
		String name;

		public Queue (String name) {
			front = null;
			rear = null;
			size = 0;
			this.name = name;
		}

		public Queue () {
			front = null;
			rear = null;
			size = 0;
		}

		public void enqueue (Object O) {
			QueueNode node = new QueueNode(O);
			if (isEmpty ()) {
				front = rear = node;
			}
			else {
				rear.next = node;
				rear = node;
			}
			size++;
		}

		public Object dequeue () {
			Object node = null;
			if (isEmpty ()) {
				return null;
			}
			node = front.key;
			front = front.next;
			if (front == null)
				rear = null;
			size--;
			return node;
		}
		
		public boolean isEmpty () {
			if (front == null) {
				return true;
			}
			return false;
		}
	}
	
	static public class LinkedListNode {
		Integer key;
		Integer value;
		LinkedListNode next;
		LinkedListNode prev;
		
		LinkedListNode (Integer k) {
			key = k;
			value = 0;
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

		public void add (Integer key) {
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

	static long mode = (long) Math.pow(10, 9) + 7;
	public static void main(String[] args) {
		readInput ();
	}

	static void readInput () {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String file = "/Users/saurabh.gupta/Documents/Personal/Interview/Round_2_preparation_material/JavaCode/Inputs/rooteTree_input11.txt";
		BufferedReader br = null;
		try {
			br = new BufferedReader (new FileReader (file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			String[] line = br.readLine().trim().split(" ");
			int nodes = Integer.valueOf(line[0]);
			int queries =  Integer.valueOf(line[1]);
			int root = Integer.valueOf(line[2]);
			
			Map<Integer, DoublyLinkedList> tree = new HashMap<Integer, DoublyLinkedList> ();
			for (int i = 0; i < nodes-1; i++) {
				line = br.readLine().trim().split(" ");
				int x =  Integer.valueOf(line[0]);
				int y =  Integer.valueOf(line[1]);
				DoublyLinkedList list = null;
				if (tree.containsKey(x)) {
					list = tree.get(x);
				}
				else {
					list = new DoublyLinkedList();
					list.add(x);
					tree.put(x, list);
				}
				list.add(y);

				if (tree.containsKey(y)) {
					list = tree.get(y);
				}
				else {
					list = new DoublyLinkedList();
					list.add(y);
					tree.put(y, list);
				}
				list.add(x);
			}
			
			for (int i = 0; i < queries; i++) {
				line = br.readLine().trim().split(" ");
				String op =  line[0];
				
				if (op.equals("U")) {
					int node =  Integer.valueOf(line[1]);
					int V =  Integer.valueOf(line[2]);
					int K =  Integer.valueOf(line[3]);
					doUpdate (tree, node, V, K);
				}
				else if (op.equals("Q")) {
					int A =  Integer.valueOf(line[1]);
					int B =  Integer.valueOf(line[2]);
					Set<Integer> V = new HashSet<Integer> ();
					System.out.println(processReport (tree, A, B, V));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static int processReport(Map<Integer, DoublyLinkedList> tree, int A, int B, Set<Integer> V) {
		if (A == B) {
			return tree.get(A).head.value;
		}
//		if (V.contains(A)) {
//			return -1;
//		}
//		V.add(A);
		LinkedListNode node = tree.get(A).head;
		node = node.next;
		while (node != null) {
			int v = processReport (tree, node.key, B, V);
			if (v != -1) {
				return tree.get(A).head.value + v;
			}
			node = node.next;
		}
		return -1;
	}
//	private static long processReport(Map<Integer, Pair<Integer,DoublyLinkedList> > tree, int A, int B) {
//		if (A == B) {
//			int value = tree.get(A).getSecond().head.value;
//			return value % mode;
//		}
//
//		int value = tree.get(B).getSecond().head.value;
//		int parent = tree.get(B).getFirst();
//		while (true) {
//			value += tree.get(parent).getSecond().head.value;
//			if (parent == A) {
//				break;
//			}
//			parent = tree.get(parent).getFirst();
//		}
//		return value % mode;
//	}
//
//	private static void doUpdate(Map<Integer, Pair<Integer,DoublyLinkedList>> tree, int n, int v, int k) {
//		Queue q = new Queue ();
//		q.enqueue(n);
//		int d = 0;
//		
//		while (true) {
//			Queue innerQueue = new Queue ();
//			while (!q.isEmpty()) {
//				int r = (int)q.dequeue();
//				LinkedListNode node = tree.get(r).getSecond().head;
//				node.value += v + d*k;
//				enqueueAll (node.next, innerQueue);				
//			}
//			if (innerQueue.isEmpty()) {
//				break;
//			}
//			q = innerQueue;
//			d++;
//		}
//	}

	private static void doUpdate(Map<Integer, DoublyLinkedList> tree, int n, int v, int k) {
		Queue q = new Queue ();
		q.enqueue(n);
		int d = 0;
		
		while (true) {
			Queue innerQueue = new Queue ();
			while (!q.isEmpty()) {
				int r = (int)q.dequeue();
				LinkedListNode node = tree.get(r).head;
				node.value += v + d*k;
				enqueueAll (node.next, innerQueue);				
			}
			if (innerQueue.isEmpty()) {
				break;
			}
			q = innerQueue;
			d++;
		}
	}

	private static void enqueueAll(LinkedListNode node, Queue innerQueue) {
		while (node != null) {
			innerQueue.enqueue(node.key);
			node = node.next;
		}
	}
}
