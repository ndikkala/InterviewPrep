package algocode;

import java.io.*;

/*
 * Given a 2-d matrix of integers, find the sub-rectangle which gives maximum sum. 
 * Sub-Rectangle is contiguous cells of the elements.
 * Time Complexity: O(col*col*row)
 * Space Complexity: O(row)
 */
public class KadaneMaxSubRectangle {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int rows = Integer.parseInt(in.readLine());
		int cols = Integer.parseInt(in.readLine());
		int[][] input = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			String[] eachrow = in.readLine().split(" ");
			int j = 0;
			for (String s : eachrow) {
				input[i][j] = Integer.parseInt(s);
				j++;
			}
		}
		int[] output = findMaximumSum(input);
		for(int out: output){
			System.out.println(out);
		}
	}
	
	public static int[] findMaximumSum(int[][] input){
		int left = 0;
		int right = 0;
		//Array to keep counting max sum for every col with left and right iterating from left-most area.
		//For ex., left->0, right->0 to cols-1, then left ->1 , right-> 1 to cols-1 and so on
		int[] rowArray = new int[input.length];
		int rows = input.length;
		int cols = input[0].length;
		int globalMax = 0;
		int currentMax = 0;
		//Coordinates of the rectangle
		int leftMax = 0;
		int rightMax = 0;
		int upMax = 0;
		int downMax = 0;
		
		for(left=0; left<=(cols-1); left++){
			//Re-initialize the array after every iteration of left to 0
			for(int k=0; k < rows; k++){
                rowArray[k] = 0;
            }
			for(right=left; right<cols; right++){
				for(int i=0; i<rows;i++){
					//Compute cumulative sum for every iteration of right and update it in rowArray for each row.
					rowArray[i] += input[i][right];
				}
				//Returns an int array of max sum, start and end indices
				int[] subarray = findMaximumSum(rowArray);
				currentMax =  subarray[0];
				
				if(currentMax>globalMax){
					globalMax = currentMax;
					//Keep track of the 4 coordinates of the sub-rectangle which has the maximum sum so far
					leftMax = left;
					rightMax =  right;
					upMax = subarray[1];
					downMax = subarray[2];
				}
				
			}
		}
		
		
		return new int[] {globalMax, leftMax, rightMax, upMax, downMax};
	}
	
	public static int[] findMaximumSum(int[] input){
		int globalMaximum= 0;
		int currentMaximum = 0;
		int maxStart = -1;
        int maxEnd = -1;
        int currentStart = 0;
		if(input.length>0){
			globalMaximum= input[0];
			currentMaximum = input[0];
		}
		else{
			//Error: input is empty
			return null;
		}
		
		for(int i=1; i<input.length; i++){
			int max = Math.max(input[i], (input[i]+currentMaximum));
			//Keep track of the index from where subarray is being considered
			if(input[i]>(input[i]+currentMaximum)){
				currentStart=i;
			}
			currentMaximum = max;
			if(max > globalMaximum){
				//Keep track of the start and end indices of the subarray that gave the maximum sum				
				maxStart = currentStart;
	            maxEnd = i;
				globalMaximum = max;
			}
		}
		
		return new int[]{globalMaximum, maxStart, maxEnd};
	}

}
