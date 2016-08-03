package algocode;

import java.io.*;
import java.util.*;

public class PrimeGenerator {
	ArrayList<String> output = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
		PrimeGenerator pg = new PrimeGenerator();
		while(t>0){
			String[] numbers = in.readLine().split(" ");
			pg.generatePrimes(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
			t--;
		}
		
		for(String s: pg.output){
			if(s.equals("\n"))
				System.out.print(s);
			else
				System.out.println(s);
		}
	}
	
	void generatePrimes(int a, int b){
		for(int i=a;i<=b; i++){
			if(checkIfPrime(i)){
				if(i>1)
				{
					output.add(Integer.toString(i));
				}
			}
		}
		output.add("\n");
	}

	boolean checkIfPrime(int input){
		double sqrtOfFactor = Math.ceil(Math.sqrt(input));
		int numOfSubFactors = 0;
		if(input ==1 || input == 2)
			{return true;}
		for (int k = 2; k <= sqrtOfFactor; k++) {
			if (input% k == 0) {
				numOfSubFactors += 1;
				break;
			}
		}
		if(numOfSubFactors ==  0)
			return true; 
		else return false;
		
	}
}
