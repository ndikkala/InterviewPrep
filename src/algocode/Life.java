package algocode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Life {
	static ArrayList<Integer> output = new ArrayList<Integer>();
	public static void main(String[] args) {
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			int m = 0;
			while(m!= 42){
				m = Integer.parseInt(in.readLine());
				if(m!=42)
					output.add(m);
			}
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					System.out));
			for (int i = 0; i < output.size(); i++) {
				int result = output.get(i);
				out.write(Integer.toString(result));
				out.write("\n");
			}
			out.flush();
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
