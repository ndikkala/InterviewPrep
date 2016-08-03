package algocode;
import java.io.*;

public class WDTBAM {
	/*
	 * Problem Code: WDTBAM
	 */

		static final int MAXSIZE = 500;
		static long[] output = new long[MAXSIZE];
		public static void main(String[] args) throws Exception{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			int t = Integer.parseInt(in.readLine());//no of test cases
			output = new long[t];
			int index = 0;
			while(t>0){
				int n = Integer.parseInt(in.readLine()); //no. of questions
				char[] correctAns = in.readLine().toCharArray();
				char[] chefAns = in.readLine().toCharArray();
				String[] winningStr = in.readLine().split(" ");
				long[] winnings = new long[n+1];
				int count = 0;
				for(int i=0; i<n; i++){
					if(correctAns[i] == chefAns[i]){
						count++;
					}
				}
				
				for(int j=0; j<(n+1); j++){
					winnings[j] = Long.parseLong(winningStr[j]);
				}
				if(count == n){
					//all answers are correct, so anything less than n correct answers is not possible
					output[index] = winnings[count];
				}
				else{
				
				for(int k=0; k<=count;k++){
					if(winnings[k] > winnings[count]){
						winnings[count] = winnings[k];
					}
				}
				}
				output[index] = winnings[count];
				t--;
				index++;
			}
			
			for(long w: output){
				System.out.println(w);
			}
		}
	
}
