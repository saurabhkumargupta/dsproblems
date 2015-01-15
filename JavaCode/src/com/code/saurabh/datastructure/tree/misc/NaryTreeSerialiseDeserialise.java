package com.code.saurabh.datastructure.tree.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.code.saurabh.datastructure.queue.Queue;

/*
 * N-ary tree
 * Serialize-deserialize n-ary tree
 */
public class NaryTreeSerialiseDeserialise {

	static class NaryTreeNode {
		char key;
		List<NaryTreeNode> childs;
		NaryTreeNode (char k) {
			this.key = k;
		}
	}
	NaryTreeNode root = null;

	void add (char k, String childs) {
		
		NaryTreeNode node = getNode (k);
		if (node == null) {
			node = new NaryTreeNode (k);
			node.key = k;
		}

		node.childs = new ArrayList<NaryTreeNode> ();

		for (int i = 0; i < childs.length(); i++) {
			node.childs.add(new NaryTreeNode(childs.charAt(i)));
		}

		if (root == null) {
			root = node;
		}
	}

	private NaryTreeNode getNode(char k) {
		Queue q = new Queue ();
		q.enqueue(root);

		while (!q.isEmpty()) {
			NaryTreeNode node = (NaryTreeNode) q.dequeue();
			
			Queue q2 = new Queue ();
			while (node != null) {
				if (node.key == k) {
					return node;
				}
				if (node.childs != null) {
					List<NaryTreeNode> childs = node.childs;
					Iterator<NaryTreeNode> iter = childs.iterator();
					while (iter.hasNext()) {
						NaryTreeNode child = iter.next();
						q2.enqueue(child);
					}
				}
				node = (NaryTreeNode) q.dequeue();
			}
			q = q2;
		}
		return null;
	}

	private NaryTreeNode levelorder() {
		Queue q = new Queue ();
		q.enqueue(root);

		while (!q.isEmpty()) {
			NaryTreeNode node = (NaryTreeNode) q.dequeue();
			System.out.println();
			Queue q2 = new Queue ();
			while (node != null) {
				System.out.print(node.key + ", ");
				if (node.childs != null) {
					List<NaryTreeNode> childs = node.childs;
					Iterator<NaryTreeNode> iter = childs.iterator();
					while (iter.hasNext()) {
						NaryTreeNode child = iter.next();
						q2.enqueue(child);
					}
				}
				node = (NaryTreeNode) q.dequeue();
			}
			q = q2;
		}
		return null;
	}

	/**
	 * Doing level order traversal.
	 * Storign tree as Parent:<List of Child>, next Parent: <List of childs>
	 * Serialized String: A:BCD,B:EF,D:GHIJ,
	 */
	String serialize () {
		Queue q = new Queue ();
		q.enqueue(root);
		StringBuilder sb = new StringBuilder ();

		while (!q.isEmpty()) {
			NaryTreeNode node = (NaryTreeNode) q.dequeue();
			System.out.println();
			Queue q2 = new Queue ();

			while (node != null) {
				if (node.childs != null) {
					sb.append( node.key );
					sb.append (":");
					List<NaryTreeNode> childs = node.childs;
					Iterator<NaryTreeNode> iter = childs.iterator();
					while (iter.hasNext()) {
						NaryTreeNode child = iter.next();
						q2.enqueue(child);
						sb.append(child.key);
					}
					sb.append (",");
				}
				node = (NaryTreeNode) q.dequeue();
			}
			q = q2;
		}

		if (sb.length() == 0) {
			sb.append(root.key);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		testcase_1 ();
//		testcase_2 ();
	}

	@SuppressWarnings("unused")
	private static void testcase_2 () {
		NaryTreeSerialiseDeserialise tree = new NaryTreeSerialiseDeserialise ();
		String nodes = "A";
		tree.add('A', "");
		tree.levelorder();
		tree.parents ("A");
		String serializedTree = tree.serialize();
		System.out.println("Serialized String: " + serializedTree);
		
		System.out.println("\nAfter Deserilization:");
		tree.deserialize (serializedTree, nodes);
	}

	private static void testcase_1 () {
		NaryTreeSerialiseDeserialise tree = new NaryTreeSerialiseDeserialise ();
		tree.add('A', "BCD");
		tree.add('B', "EF");
		tree.add('D', "GHIJ");
		tree.levelorder();
		
		String nodes = "ABCDEFGHIJ";
		tree.parents (nodes);
		String serializedTree = tree.serialize();
		System.out.println("Serialized String: " + serializedTree);
		tree.deserialize (serializedTree, nodes);	
	}

	private void deserialize(String serializedTree, String nodes) {
		NaryTreeSerialiseDeserialise tree = new NaryTreeSerialiseDeserialise ();
		int index = 0;
		while (index < serializedTree.length()) {
			char root = serializedTree.charAt(index);
			index++;// passing root
			index++; //skipping :
			String childs = serializedTree.substring (index, serializedTree.indexOf(',', index));
			index += childs.length();
			index++; //skipping ,
			System.out.println("root:" + root + ", childs: " + childs);
			tree.add(root, childs);
		}
		tree.levelorder();
		tree.parents (nodes);	
	}

	private void parents (String nodes) {
		System.out.println();
		for (int i = 0; i < nodes.length(); i++) {
			char key = nodes.charAt(i);
			System.out.println("Parent of " + key + " : " + parent (key));			
		}
	}

	private char parent(char k) {
		Queue q = new Queue ();
		q.enqueue(root);

		while (!q.isEmpty()) {
			NaryTreeNode node = (NaryTreeNode) q.dequeue();
			
			Queue q2 = new Queue ();
			while (node != null) {

				if (node.childs != null) {
					List<NaryTreeNode> childs = node.childs;
					Iterator<NaryTreeNode> iter = childs.iterator();
					while (iter.hasNext()) {
						NaryTreeNode child = iter.next();
						if (child.key == k) {
							return node.key;
						}
						q2.enqueue(child);
					}
				}
				node = (NaryTreeNode) q.dequeue();
			}
			q = q2;
		}
		return (char) -1;
	}

}
