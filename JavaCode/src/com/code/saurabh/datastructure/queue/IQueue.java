/**
 * 
 */
package com.code.saurabh.datastructure.queue;

/**
 * @author saurabh.gupta
 *
 */
public interface IQueue <T> {
	public boolean isEmpty ();
	public void enqueue (T key);
	public T dequeue ();
}
