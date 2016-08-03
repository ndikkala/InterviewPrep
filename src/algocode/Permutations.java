package algocode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Permutations {

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

		String inputString = in.readLine();

		perm(inputString, 0);

		System.out.println(output);
	}

	ArrayList<String> output = new ArrayList<String>();

	ArrayList<String> temp = new ArrayList<String>();

	public void perm(String input, int i) {
		StringBuilder buffer1 = new StringBuilder("");
		if (i == 0) {
			buffer1.insert(0, input.charAt(0));
			output.add(buffer1.toString());
			//temp = (ArrayList) output.clone();
		}

		for (int j = 1; j < input.length(); j++) {
			// System.out.println("putting this in temp: "+ output);
			//temp = (ArrayList) output.clone();
			boolean replace = true;
			int outputSize = output.size();
			int index = 0;
			for (int n = 0; n < temp.size(); n++) {
				for (int m = 0; m <= j; m++) {
					buffer1.replace(0, buffer1.length(), temp.get(n));
					// System.out.println("buffer 1 length " + buffer1.length()
					// + " and m is "+m);
					buffer1.insert(m, input.charAt(j));
					if (index < outputSize && replace) {
						System.out.println("setting " + index
								+ " index , size is " + outputSize);
						output.set(index, buffer1.toString());

					} else {
						replace = false;
						System.out.println("adding new , " + index
								+ " is index, size is " + outputSize);
						output.add(buffer1.toString());
					}
					index++;

				}
			}

		}

	}

	public static void main(String[] args) throws Exception {

		Permutations per = new Permutations();
		per.process();
	}

}
