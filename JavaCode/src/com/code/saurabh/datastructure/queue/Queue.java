package com.code.saurabh.datastructure.queue;

public class Queue{
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

	public static void main(String[] args) {
		Queue queue = new Queue();
		QueueTest (queue);
	}

	private static void QueueTest(Queue queue) {
		queue.enqueue(10);
		System.out.println(": " + queue.dequeue());
		System.out.println(": " + queue.dequeue());
		System.out.println(": " + queue.dequeue());
		
		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);
		queue.enqueue(40);
		queue.enqueue(50);
		System.out.println(": " + queue.dequeue());
		System.out.println(": " + queue.dequeue());
		queue.enqueue(60);
		System.out.println(": " + queue.dequeue());
		System.out.println(": " + queue.dequeue());
		System.out.println(": " + queue.dequeue());
	}

}
