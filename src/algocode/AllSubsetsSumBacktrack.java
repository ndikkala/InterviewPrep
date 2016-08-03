package algocode;
import java.io.*;
import java.util.*;
/*
 * 
 */
public class AllSubsetsSumBacktrack {
	
	static int n=0;
	static int m=0;
	static int[] X =  new int[1024];
	static int[] input =  new int[1024];
	static ArrayList<String> outputX =  new ArrayList<String>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputString = in.readLine().split(" ");
		m = Integer.parseInt(in.readLine());
		n = inputString.length;
		X =  new int[n];
		input = new int[n];
		int i=0;
		for(String s: inputString){
			input[i++] = Integer.parseInt(s);
		}
		
		//Recurses from k=0 to n-1
		recursiveSubsetSum(0,0);
		
		for(String s: outputX){
			System.out.println(s);
			char[] bitArray = s.toCharArray();
			int j=0;
			for(char c: bitArray){
				if(c=='1'){
					System.out.print(input[j]+" ");
				}
				j++;
			}
			System.out.println();
			
		}
	}
	
	public static void recursiveSubsetSum(int S, int k){
		//Try one branch of the tree
		X[k] = 1;
		
		if(S+input[k] == m){
			//Add the vector X to output, found one solution
			StringBuilder sb = new StringBuilder();
			for(int bit: X){
				sb.append(bit);
			}
			outputX.add(sb.toString());
		}
		else if(((k+1)< n) && (S+input[k]<=m)){
			//A solution is still possible, so continue along this branch
			recursiveSubsetSum(S+input[k], k+1);
		}
		
		//Try the other branch
		X[k] = 0;
		if(((k+1)< n) && (S+input[k+1]<=m)){
			recursiveSubsetSum(S, k+1);
		}
		
	}
}
