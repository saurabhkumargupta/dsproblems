/**
 * 
 */
package com.code.saurabh.util;

import java.util.Iterator;
import java.util.List;

/**
 * @author saurabh.gupta
 *
 */
public class LinkedListUtil {
	
	public static void printLinkedList (List<String> list) {
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			String str = iter.next();
			System.out.print (str + ", ") ;
		}
		System.out.println ();
		System.out.println ();
	}
}
