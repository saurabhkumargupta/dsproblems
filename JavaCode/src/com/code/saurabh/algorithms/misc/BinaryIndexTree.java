package com.code.saurabh.algorithms.misc;
/*
 * NEED TO UNDErSTNand details
 * http://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/
 * http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=binaryIndexedTrees
 */
public class BinaryIndexTree {
	// Returns sum of arr[0..index]. This function assumes
	// that the array is preprocessed and partial sums of
	// array elements are stored in BITree[].
	static int getSum(int BITree[], int n, int index)
	{
	    int sum = 0; // Iniialize result
	 
	    // index in BITree[] is 1 more than the index in arr[]
	    index = index + 1;
	 
	    // Traverse ancestors of BITree[index]
	    while (index>0)
	    {
	        // Add current element of BITree to sum
	        sum += BITree[index];
	 
	        // Move index to parent node
	        index -= index & (-index);
	    }
	    return sum;
	}
	 
	// Updates a node in Binary Index Tree (BITree) at given index
	// in BITree.  The given value 'val' is added to BITree[i] and 
	// all of its ancestors in tree.
	static void updateBIT(int []BITree, int n, int index, int val)
	{
	    // index in BITree[] is 1 more than the index in arr[]
	    index = index + 1;
	 
	    // Traverse all ancestors and add 'val'
	    while (index <= n)
	    {
	       // Add 'val' to current node of BI Tree
	       BITree[index] += val;
	 
	       // Update index to that of parent
	       index += index & (-index);
	    }
	}
	 
	// Constructs and returns a Binary Indexed Tree for given
	// array of size n.
	static int [] constructBITree(int arr[], int n)
	{
	    // Create and initialize BITree[] as 0
	    int []BITree = new int[n+1];
	    for (int i=1; i<=n; i++)
	        BITree[i] = 0;
	 
	    // Store the actual values in BITree[] using update()
	    for (int i=0; i<n; i++)
	        updateBIT(BITree, n, i, arr[i]);
	 
	    // Uncomment below lines to see contents of BITree[]
	    //for (int i=1; i<=n; i++)
	    //      cout << BITree[i] << " ";
	 
	    return BITree;
	}
	 
	 
	// Driver program to test above functions
	public static void main(String[] args) {
	    int freq[] = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
	    int n = freq.length;
	    int [] BITree = constructBITree(freq, n);
	    System.out.println("Sum of elements in arr[0..5] is " + getSum(BITree, n, 5));
	 
	    // Let use test the update operation
	    freq[3] += 6;
	    updateBIT(BITree, n, 3, 6); //Update BIT for above change in arr[]
	 
	    System.out.println("\nSum of elements in arr[0..5] after update is " + getSum(BITree, n, 5));
	}

}
