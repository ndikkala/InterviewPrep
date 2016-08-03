package algocode;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FindFibonacci {

	static BufferedReader in;
	
	long[]result = new long[1024];
	
	private long fib(int i, int n){
		/*
		 * Without using aux array
		 * if(i==0||i==1)
		 * a=1, b=1
		 * else
		 * a=b, b=res[i]
		 * 
		 * res[i] =a+b
		 */
		
		if(i<=n && i>1){
			result[i] = result[i-1] + result[i-2];
		}
		else if (i==1)
			result[i] = 1;
		else if(i==0)
			result[i] = 0;
		
		if(i<n)
			fib(i+1,n);
		
		return result[n];
		
	}
	
	public static void main(String[] args) throws Exception{
		
		in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		FindFibonacci fibi = new FindFibonacci();
		System.out.println(fibi.fib(0, n));
		
	}
}
