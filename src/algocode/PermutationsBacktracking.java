package algocode;
import java.io.*;
import java.util.*;


public class PermutationsBacktracking {
	private static String input = "";
	private static ArrayList<String> result =  new ArrayList<String>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader in  = new BufferedReader (new InputStreamReader(System.in));
		input =  in.readLine();
		int length = input.length();
		permuteBT(input, 0, length);
		System.out.println(result);
	}
	
	public static void permuteBT(String string, int start, int end){
		int current = 0;
		char[] x = string.toCharArray();
		char temp = ' ';
		if(start == (end-1)){
			if(!result.contains(string)){
				result.add(string);
			}
		}
		else{
			for(current=start; current<end; current++){
				// Swap
				temp = x[start];
				x[start] = x[current];
				x[current] = temp;
				String str =  String.valueOf(x);
				permuteBT(str, start + 1, end);

				temp = x[start];
				x[start] = x[current];
				x[current] = temp;
			}
		}
	}

}
