package algocode;
import java.io.*;
//import java.util.Arrays;

/*
 * Given a string of size n and a pattern of size m, find if the string contains pattern. The dp algo which has 2d matrix gives a O(mn) solution.
 * Knuth-Morris-Pratt algo gives O(m+n) algo, by building suffix-prefix memoization for the pattern in a separate array
 * And then uses, those values to compute matching substring in the main string by iterating it once.
 */

public class KMPPatternMatching {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = in.readLine();
		String pattern = in.readLine();
		if(KMPSearch(input.toCharArray(), pattern.toCharArray())){
			System.out.println("Input contains pattern");
		}
		else{
			System.out.println("Input does not contain pattern");
		}
		
		
	}
	
	public static boolean KMPSearch(char[] input, char[] pattern){
		//Time complexity: O(m+n)
		int[] patternSuffix = buildPatternSuffixPrefixArray(pattern);
		int i=0;
		int j=0;
		
		while(i<input.length && j< pattern.length){
			//Match of chars, move both indices to right and compare next char in both strings
			if(input[i]== pattern[j]){
				i++;
				j++;
			}
			else{
				//Chars did not match at j>0, compare from the point where there is a matching prefix
				if(j!=0){
					j=patternSuffix[j-1];
				}
				//Chars did not match at j=0, move i to right and compare start of pattern with next char of input
				else{
					i++;
				}
			}
		}
		
		//If by the end of above loop, j did not move to the left and is at the end of the pattern string, we found a complete match
		if(j==pattern.length){
			return true;
		}
		
		return false;
	}
	
	public static int[] buildPatternSuffixPrefixArray(char[] pattern){
		//Array maintains size of suffix which is same as prefix
		//time complexity: O(n), space complexity: O(n)
		int i=0;
		int j=0;
		int[] temp = new int[pattern.length];
		//Base case
		if(i==0 && j==0){
			temp[i] = j;
			i++;
		}
		//i never moves to the left, it moves only to the right, 
		//so i reaching the end of string marks the end of loop
		for(i=1; i<pattern.length;){
			//Match found, keep moving both i and j to the right and update the count of suffix in the array 
			//as we found a matching suffix to a prefix of that size
			if (pattern[j] == pattern[i]) {
					temp[i] = j + 1;
					i++;
					j++;
			}
			else{
				//Mismatch occurred when j>0, move j to the point after the matched prefix as the part of string till prefix is already matched.
				if(j!=0){
					j=temp[j-1];
				}
				//Mismatch occurred when j is at the start, compare the next char
				else{
					temp[i]=0;
					i++;
				}
			}
			
		}
			
		//System.out.println(Arrays.toString(temp));
		return temp;
	}
}
