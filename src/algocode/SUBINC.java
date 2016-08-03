package algocode;
import java.io.*;
import java.util.*;
/*
 * Problem Code: SUBINC
 */
public class SUBINC{
	static int MAXSIZE = 100000;
	static long count = 0;
	static long [] input = new long[MAXSIZE];
	static long[] output = new long[5];
	
	public static void main(String[] args) throws Exception{
		//SUBINC solution = new SUBINC();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());//no of test cases
		output = new long[t];
		int index=0;
		while(t>0){
			int n = Integer.parseInt(in.readLine()); //size of array
			String[] inputStr = in.readLine().split(" ");
			input = new long[n];
			count = 0;
			for(int i =0; i<n; i++){
				input[i] = Long.parseLong(inputStr[i]); //For every test case, global array input is initialized
			}
			output[index] = countSubarraysFast();
			t--;
			index++;
		}
		
		for(long o: output){
			System.out.println(o);
		}
	}
	
	static long countSubarraysFast(){
		//This method doesnt compute the subarrays, it only counts
		long prev = 0;
		
		for(int j=0; j<input.length; j++){
			if(j==0){
				prev = 1; // for the first element added
				count += prev;
			}
			else{
				if(input[j] >= input[j-1]){
					prev += 1; //no. of new subarrays formed
					count += prev;
				}
				else{
					prev=1; //when current element is less than prev element, then make prev as 1
					count += prev;//only current element is the new subarray
				}
			}
		}
		
		return count;
	}
	
	static long countSubarrays(){
		Queue<ArrayList<Long>> subarrays = new LinkedList<ArrayList<Long>> ();
		ArrayList<Long> previousSubArray = new ArrayList<Long>();
		int length =  input.length;
		int size = previousSubArray.size();
		int noOfPreviousSubArrays =0;
		for(int j=0; j< length; j++){
			if(j==0){
				//base case, add the first element subarray to queue
				previousSubArray.add(input[j]);
				//Push the first subarray to queue
				//System.out.println("base case pushing "+input[j]);
				subarrays.add(previousSubArray);
				count++;
			}
			else{
				noOfPreviousSubArrays = subarrays.size();
				//Repeat the operation for no. of times as the number of subarrays existing in the queue
				while(noOfPreviousSubArrays>0){
					//Get the first element from queue
					previousSubArray = subarrays.poll();
					size = previousSubArray.size();
					//If current element is greater or equal to last element in the polled subarray, update the subarray to include current element
					if(input[j] >= previousSubArray.get(size-1)){
						previousSubArray.add(input[j]);
						//Push the updated subarray to queue
						//System.out.println("current element pushing "+input[j]);
						subarrays.add(previousSubArray);
						count++;
					}
					if(noOfPreviousSubArrays==1){
						//when we come to last subarray processing, add the current element subarray
						previousSubArray.add(input[j]);
						//System.out.println("current element pushing "+input[j]);
						subarrays.add(previousSubArray);
						count++;
					}
					noOfPreviousSubArrays -- ;
				}
			}
		}
		
		return count;
	}
}