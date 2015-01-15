/*
 *
 * http://stackoverflow.com/questions/8031939/finding-maximum-for-every-window-of-size-k-in-an-array
 * {2, 5, 6} , {9, 8, -2} , {1, 0, 3}

(2, 5, 6}, {9, 9, 9 }
(6, 6, 6}, {9, 8 -2 }

    ---------
            -
    ------
    
    
public class MaxInKSubsetArray {

    int[] arr;
    public MaxInKSubsetArray(int[] arr) {
        this.arr = arr;
    }
    
    public void print(int k){
        int[] maxFromLeft = new int[arr.length];
        int[] maxFromRight = new int[arr.length];
        int max = 0;
        
        for(int i=0;i<arr.length;i++){
            if(arr[i] > max){
                max = arr[i];
            }
            maxFromLeft[i] = max;
            if((i+1) % k == 0){
                max=0;
            }
        }
        max=0;
        for(int i=arr.length-1;i>=0;i--){
            if(arr[i] > max){
                max = arr[i];
            }
            maxFromRight[i] = max;
            if((i) % k == 0){
                max=0;
            }
        }
        
        for(int i=0;i<arr.length-k+1;i++){
            System.out.print(Math.max(maxFromRight[i],maxFromLeft[i+k-1]) + " ");
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {1, -2, 5, 6, 0, 9, 8, -1, 2, 0};
        MaxInKSubsetArray m = new MaxInKSubsetArray(arr);
        m.print(3);
    }
    
}
 * 
 */
package com.code.saurabh.array;

/**
 * @author saurabh.gupta
 *
 */
public class SlidingWindowProblem {

	/**
	 * <pre>
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
