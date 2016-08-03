//square and multiply algorithm - very commonly used in cryptography

package algocode;

import java.io.*;
import java.util.*;

public class MPower {
	static long[] output ;
	public static void main(String[] args) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			int t = Integer.parseInt(in.readLine());
			if (t > 10 || t < 1)
				return;
			output = new long[t];
			//for every test case
			for (int i = 0; i < t; i++) {
				MPower mp = new MPower();
				String[] s = in.readLine().split(" ");
				long x = Long.parseLong(s[0]);
				//long y = Long.parseLong(s[1]);
				String y = s[1];
				long n = Long.parseLong(s[2]);
				long result = mp.powerWithBase10(x, y, n);
				output[i] = result;
			}
			
			for (int i = 0; i < output.length; i++) {
				System.out.print(output[i]);
				System.out.println();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 /*long power(long x, long y, long n){
		String bin = convertToReverseBinary(y);
		
		long result = 1;
		long temp = mod(x,n);
		//bin = reverse(bin);
		char[] binDigits = bin.toCharArray();
		for(char c: binDigits){
			if(c == '0'){
				//System.out.println("hit1");
				temp = mod(temp * temp,n);
			}
			if(c== '1'){
				//System.out.println("hit2");
				result = mod((mod(result,n)) * (mod(temp,n)), n);
				temp = mod(temp * temp,n);
			}
		}
		result =  mod(result,n);
		return result;
		
	}*/
	
	long powerWithBase10(long x, String y, long n){
		/*2^586 = 2^ (500+80+6) = 2^500* 2^80 * 2^6 = 2^6 * (2^10)^8 * (2^100)^5*/
		long result = 1;
		long temp = mod(x,n);
		char[] binDigits = reverse(y).toCharArray();
		for(char c: binDigits){
			int d = Character.getNumericValue(c);
			//System.out.println("result: "+result+" temp: "+temp);
			//result = result*temp^d;
			//temp = temp^10;
			result = mod((long)((mod(result,n)) * (raiseToPower(mod(temp,n), d, n))), n);
			temp = mod((long)(raiseToPower(mod(temp,n), 10,n)),n);
		}
		//System.out.println("result: "+result+" temp: "+temp);
		result =  mod(result,n);
		return result;
	}
	 
	 long powerWithBase2(long x, String y, long n){
			String bin = convertToStringBinary(y);
			
			long result = 1;
			long temp = mod(x,n);
			//bin = reverse(bin);
			char[] binDigits = bin.toCharArray();
			for(char c: binDigits){
				if(c == '0'){
					//System.out.println("hit1");
					temp = mod(temp * temp,n);
				}
				if(c== '1'){
					//System.out.println("hit2");
					result = mod((mod(result,n)) * (mod(temp,n)), n);
					temp = mod(temp * temp,n);
				}
			}
			result =  mod(result,n);
			return result;
			
		}
	
	String convertToReverseBinary(long power){
		long remainder=0;
		long quotient=power;
		String binary= "";
		while(quotient>1){
			remainder = quotient%2;
			binary += remainder;
			quotient = quotient/2;
			
		}
		if(quotient == 1){
			remainder = 1;
			binary += remainder;
		}
		return binary;
	}
	
	String reverse(String input) {
		char[] chars = input.toCharArray();
		Stack<Character> s = new Stack<Character>();
		String reverse = "";
		for (char c : chars) {
			s.push(c);
		}
		while (!s.empty()) {
			reverse = reverse + s.pop();
		}
		return reverse;
	}

	String convertToStringBinary(String power){
		char[] allDigits = power.toCharArray();
		int noOfDigits = allDigits.length;
		String binary= "";
		char[] quotient = new char[noOfDigits];
		int sum =0;
		for(char c:allDigits){
			sum += Character.getNumericValue(c);
		}
		//boolean isConcatenate = false;
		while(sum>0){
			//System.out.println("all done count "+ allDoneCount + " no of digits "+noOfDigits);
			int i =0;
			long remainder = 0;
			//System.out.println("alldigits " + new String(allDigits));
		for(char c:allDigits){
			long current;
			String s = remainder+Character.toString(c);
			current = Integer.parseInt(s);
			
			remainder = mod(current,2);
			//System.out.println("rem is "+ remainder );
			int q = (int) current/2;
			//System.out.println("q is "+ q );
			
			
			quotient[i] = Character.forDigit(q, 10);
			//System.out.println("q[i] " + quotient[i]);
			//System.out.println("ad[i] " + allDigits[i]);
			sum += quotient[i]-allDigits[i];
			allDigits[i] = quotient[i];
			i++;
		}
		//int j=0;
		/*for(char q: quotient){
			allDigits[j] = q;
			j++;
		}*/
		binary += remainder;
		}
		return binary;
	}
	
	long mod(long x, long n)
	{
		
	   long result = x % n;
	   if (result < 0)
	   {
	       result += n;
	   }
	   return result;
	}
	
	
	long raiseToPower(long x, long y, long n)
	{
		long result = 1;
		for(long i=0; i<y;i++ ){
			result *= x;
			result = mod(result, n);
		}
		return mod(result, n);
	  
	}
}
