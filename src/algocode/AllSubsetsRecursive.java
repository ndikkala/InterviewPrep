package algocode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AllSubsetsRecursive {

	static StringTokenizer st;

	static BufferedReader in;

	static BufferedWriter out;

	String[] data;

	String S;

	String next() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(in.readLine());
			} catch (Exception e) {
			}
		}
		return st.nextToken();
	}

	String nextLine() {
		try {
			return in.readLine();
		} catch (Exception e) {
		}
		return null;
	}

	int nextInt() {
		return Integer.parseInt(next());
	}

	public void process() throws Exception {
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		in = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<String> list = new ArrayList<String>();

		String[] allIntString = in.readLine().split(" ");
		for (int j = 0; j < allIntString.length; j++) {
			if (allIntString[j].length() > 0) {
				list.add(""+ Integer.parseInt(allIntString[j]));
			}
		}
		
		subset(list, 0);
		
		System.out.println(output);
		

	}
	
	ArrayList<String> output = new ArrayList<String>();
	
	public void subset(ArrayList<String> l, int i){
		int currentSize=0;
		int pointer = 0;
		if(i==0)
			{
			 output.add("");
			 output.add(""+l.get(i));
			 pointer = pointer+2;
			}
		for (int j = 1; j<l.size(); j++)
		{ 
			currentSize = output.size();
			for(int m=0; m < currentSize; m++){
				output.add(pointer,output.get(m)+l.get(j));
				pointer++;
			}
			
		}
	}
	

	public static void main(String[] args) throws Exception {

		AllSubsetsRecursive asr = new AllSubsetsRecursive();
		asr.process();
	}

}
