package com.code.saurabh.DS.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.code.saurabh.DataSructure.tree.TreeNode;

public class CompressTrie {

	private static class TrieNode {
		String currentString;
		Map<Character, TrieNode> childs;
		boolean isLeaf;
		int count;
		TreeNode refNode;
	}
	
	private TrieNode root = null;

	CompressTrie () {
		root = new TrieNode ();
		root.currentString = "";
		root.childs = new HashMap <Character, TrieNode> ();
		root.isLeaf = false;
		root.count = 0;//this will be leaf node
		root.refNode = null;
	}
	void insert (String searchWord) {
		insert (root, searchWord);
	}
	/*
	 *                 (Root)
	 *    Nokia        Samsung       LG
	 *            TV        Mobile
	 *            
	 *   New words: SamsungMobile, SamsungMobilePhone, SamsungMob, SamsungMobSearch 
	 */
	private void insert (TrieNode root, String searchWord) {
		TrieNode node = root.childs.get(searchWord.charAt(0));
		if (node == null) {
			TrieNode N = new TrieNode ();
			N.currentString = searchWord;
			N.childs = new HashMap <Character, TrieNode> ();
			N.isLeaf = true;
			N.count = 1;//this will be leaf node
			N.refNode = null;
			
			root.childs.put(searchWord.charAt(0), N);
		}
		else {
			//some common exist
			String common = StringUtils.getCommonPrefix(new String [] {node.currentString, searchWord});
			if (!common.isEmpty()) {
				if (common.equals(searchWord) && common.equals(node.currentString)) {
					//Actual Search Word : SamsungMobile
					//Node               : Mobile
					//common             : Mobile 
					//SearchWord         : Mobile

					//Word already exist, increment counter
					node.count +=1;
				}
				//Actual Search Word : SamsungMobilePhone
				//Node               : Mobile
				//common             : Mobile 
				//SearchWord         : MobilePhone
				else if (common.length() == node.currentString.length()) {
					String remainingSearchWord = searchWord.substring(common.length()); 
					insert (node, remainingSearchWord);
				}
				//Actual Search Word : SamsungMob
				//Node               : Mobile
				//common             : Mob
				//SearchWord         : Mob
				else if (common.equals(searchWord) && !common.equals(node.currentString)) {
					TrieNode N = new TrieNode ();
					N.currentString = common;
					N.childs = new HashMap <Character, TrieNode> ();
					N.isLeaf = true;
					N.count = 1;//this will be leaf node
					N.refNode = null;

					root.childs.put(common.charAt(0), N);

					//break node
					String remainingOldWord = node.currentString.substring(common.length());
					node.currentString = remainingOldWord;
					N.childs.put(remainingOldWord.charAt(0), node);
				}
				//Actual Search Word : SamsungMobSearch
				//Node               : Mobile
				//common             : Mob
				//SearchWord         : MobSearch
				else {
					TrieNode N = new TrieNode ();
					N.currentString = common;
					N.childs = new HashMap <Character, TrieNode> ();
					N.isLeaf = false;
					N.count = 0;//this will be leaf node
					N.refNode = null;

					root.childs.put(common.charAt(0), N);

					//break node
					String remainingOldWord = node.currentString.substring(common.length());
					node.currentString = remainingOldWord;
					N.childs.put(remainingOldWord.charAt(0), node);

					String remainingNewWord = searchWord.substring(common.length());
					insert (N, remainingNewWord);
				}
			}
		}
	}
	
	void printTrie () {
		printTrie (root, "");
	}
	private void printTrie (TrieNode node, String output) {
		Set<Character> keys = node.childs.keySet();
		output = output + node.currentString;
		if (node.isLeaf && !output.isEmpty()) {
			System.out.println(output + ", count: " + node.count);
		}
		for (char t:keys) {
			TrieNode n = node.childs.get(t);			
			printTrie (n, output);
		}
	}
	
	public static void main(String[] args) {
		CompressTrie trie = new CompressTrie ();
		trie.insert("samsung");
		trie.insert("nokia");
		trie.insert("lg");
		trie.insert("samsung");
		trie.insert("samsungMobile");
		trie.insert("samsungMob");
		trie.insert("samsungMobSearch");
		trie.insert("samsungMobilePhone");
		trie.insert("samsungMobilePhone");
		trie.insert("samsonite");
		trie.printTrie();
	}

}
