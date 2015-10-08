package algocode;
import java.io.*;
/*
 * Compute the minimum number of operations required to convert string a to string b. Minimum edit distance between two strings.
 * 
 */

public class EditDistance {

	int[] output;
	int count;
	int [][] d;
	public static void main(String[] args){
		try{
		BufferedReader in =  new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
		EditDistance ed =  new EditDistance();
		ed.initialize(t);
		for(int i=0; i<t; i++){
				String a = in.readLine();
				String b = in.readLine();
				ed.output[i] = ed.process(a, b);
		}
		for(int value: ed.output){
			System.out.println(value);
		}
		}
	
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	void initialize(int t){;
		output =  new int[t];
	}
	
	void initMatrix(int a, int b){
		int rows =  a+1;
		int cols = b+1;
		d = new int[rows][cols];
		int val = 0;
		for(int i=0; i<cols; i++){
			d[0][i] = val;
			val++;
		}
		val = 0;
		for(int j=0; j<rows; j++){
			d[j][0] = val;
			val++;
		}
	}
	
	int minimum(int x, int y, int z){
		if((x<y && x<z )||(x<y && x==z ) ){
			return x;
		}
		if((y<x && y<z) || (y<x && y==z )){
			return y;
		}
		if((z<y && z<x) || (z<y && z==x)){
			return z;
		}
		else{
			//all three equal
			return x;
		}
	}
	
	int process(String a, String b){
		//Compute the min edit distance to transform string a to string b
		int n = a.length();
		int m = b.length();
		initMatrix(m, n);
		char[] A = a.toCharArray();
		char[] B = b.toCharArray();
		//row = string b, col = string a
		for(int i=1; i<m+1;i++){
			for(int j=1; j<n+1;j++){
				if(A[j-1] == B[i-1]){
					//If current char in both strings are equal, take the diagonal value
					d[i][j] = d[i-1][j-1];
				}
				else{
					d[i][j] = minimum(d[i-1][j], d[i][j-1], d[i-1][j-1]) +1;
				}
			}
		}
		return d[m][n];
	}
	
}
