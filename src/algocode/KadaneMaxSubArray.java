package algocode;

import java.io.*;
/*
 * Given an array of integers, find the subarray which gives maximum sum. 
 * Subarray is continous sequence of the elements.
 * Time Complexity: O(n)
 */
public class KadaneMaxSubArray {
	
	public static void main(String[] args) throws Exception{
		BufferedReader in =  new BufferedReader(new InputStreamReader(System.in));
		String[] array = in.readLine().split(" ");
		int i=0;
		int[] input =  new int[array.length];
		for(String s: array){
			input[i] = Integer.parseInt(s);
			i++;
		}
		
		System.out.println(findMaximumSum(input));
		
	}
	
	public static int findMaximumSum(int[] input){
		int globalMaximum= 0;
		int currentMaximum = 0;
		if(input.length>0){
			globalMaximum= input[0];
			currentMaximum = input[0];
		}
		else{
			//Error: input is empty
			return 0;
		}
		
		for(int i=1; i<input.length; i++){
			int max = Math.max(input[i], (input[i]+currentMaximum));
			currentMaximum = max;
			if(max > globalMaximum){
				globalMaximum = max;
			}
		}
		
		return globalMaximum;
	}

}
