/**
 * 
 */
package com.code.saurabh.datastructure.tree.misc;

import com.code.saurabh.DataSructure.tree.BTreePrinter;
import com.code.saurabh.DataSructure.tree.Tree;
import com.code.saurabh.DataSructure.tree.TreeNode;

/**
 * @author saurabh.gupta
 *http://www.geeksforgeeks.org/serialize-deserialize-binary-tree/
 * Storing preorder traversal of tree
 * Null node is denoted: /
 * Internal node is denoted by: <node>'
 * Leaf node: <node>
 */
public class BinaryTreeSerialiseDeserialise {
	
	/**
	 * SerializedTree: 100',90',80,95',93,97,200',/,300',/,400',350',325,375,500,
	 */
	static void serialize (TreeNode root, StringBuilder serialiseTree) {
		//Adding null node to serialized string. null node is denoted by char '/' 
		if (root == null) {
			serialiseTree.append("/").append(",");
		}
		//Adding lead node to serialized string. Leaf node is not appended by any character
		else if (root.leafNode()) {
			//Leaf Node
			serialiseTree.append(root.key).append(",");
		}
		else {
			//Adding internal node to serialzed string. serialized string is appended by character "'"
			//Internal Node
			serialiseTree.append(root.key).append("'").append(",");
			serialize (root.left, serialiseTree);
			serialize (root.right, serialiseTree);
		}
		return;
	}

	//res is storing serialized string's current index which needs to be parsed
//	static TreeNode deserialize (String serialiseTree, InnerClass res) {
//		TreeNode root = null;
//		
//		//Serialized string iterator
//		int currentIndex = res.getIndex();
//		String node = getNode (serialiseTree, currentIndex);
//
//		//if serialized character is '/', then it is null node of tree
//		if (node.equals("/")) {
//			//null node
//			res.incrIndex(2); // 1 for "/" and 1 for ","
//			return null;
//		}
//		else {
//			boolean leaf = false;
//			//If de-serialized sub-string doesn't contain "'" character, then its a leaf node
//			if (!node.contains("'")) {
//				//leaf Node
//				leaf = true;
//			}
//			//get key value from node. Internal node will be { <node>' }, this "'" has to be removed in case of internal node to get key value
//			//otherwise if its a leaf node, then it is key itself
//			int k = node.contains("'") ? Integer.valueOf(node.substring(0, node.length()-1)) : Integer.valueOf(node);
//
//			root = new TreeNode (k);
//			res.incrIndex(node.length() +1);//1 for skipping ','
//
//			if (!leaf) {
//				root.left = deserialize (serialiseTree, res); 
//				root.right = deserialize (serialiseTree, res);
//			}
//		}
//		return root;
//	}

	//res is storing serialized string's current index which needs to be parsed
	static TreeNode deserialize (String serialiseTree, InnerClass res) {
		TreeNode root = null;
		
		//Serialized string iterator
		int currentIndex = res.getIndex();
		String node = getNode (serialiseTree, currentIndex);

		//if serialized character is '/', then it is null node of tree
		if (node.equals("/")) {
			//null node
			res.incrIndex(2); // 1 for "/" and 1 for ","
			return null;
		}
		else {
			boolean leaf = false;
			//If de-serialized sub-string doesn't contain "'" character, then its a leaf node
			if (!node.contains("'")) {
				//leaf Node
				leaf = true;
			}
			//get key value from node. Internal node will be { <node>' }, this "'" has to be removed in case of internal node to get key value
			//otherwise if its a leaf node, then it is key itself
			int k = 0;
			if (leaf == true) {
				k = Integer.valueOf(node);
			}
			else {
				k = Integer.valueOf(node.substring(0, node.length()-1));
			}
			root = new TreeNode (k);
			res.incrIndex(node.length() +1);//1 for skipping ','

			if (!leaf) {
				root.left = deserialize (serialiseTree, res); 
				root.right = deserialize (serialiseTree, res);
			}
		}
		return root;
	}
	
	/*
	 * Getting next node from serialized string.
	 * Node from currentIndex in serialized string to next (comma)","
	 * SerializedTree: 100',90',80,95',93,97,200',/,300',/,400',350',325,375,500,
	 * 
	 * If current remaining string is: 200',/,300',/,400',350',325,375,500,
	 * then nextNode will return 200'
	 * 
	 * If current remaining string is: /,300',/,400',350',325,375,500,
	 * then nextNode will return / (this denotes empty node)
	 */
	private static String getNode(String serialiseTree, int currentIndex) {
		String node = serialiseTree.substring(currentIndex, serialiseTree.indexOf(',', currentIndex));
		return node;
	}

	static class InnerClass {
		Integer index;
		InnerClass (int i) {
			index = new Integer (i);
		}
		
		public void incrIndex (int count) {
			index += count;
		}
		
		public int getIndex () {
			return index;
		}
	}
	public static void main(String[] args) {
		Tree t = new Tree ();
		t.createTree();
		BTreePrinter.printNode(t.root);
		StringBuilder serialiseTree = new StringBuilder ();
		serialize (t.root,serialiseTree);
		String result = serialiseTree.toString();
		System.out.println("SerializedTree: " + result);
		
		InnerClass index = new InnerClass (0);
		TreeNode newTree = deserialize (result, index);
		BTreePrinter.printNode(newTree);

		serialiseTree = new StringBuilder ();
		serialize (newTree,serialiseTree);
		result = serialiseTree.toString();
		System.out.println("New SerializedTree: " + result);
	}
}
