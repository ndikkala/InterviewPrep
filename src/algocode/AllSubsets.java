package algocode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AllSubsets {

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

	long nextLong() {
		return Long.parseLong(next());
	}

	int sum = 0;

	public void process() throws Exception {
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		in = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<Integer> list = new ArrayList<Integer>();

		String[] allIntString = in.readLine().split(" ");
		sum = Integer.parseInt(in.readLine());
		for (int j = 0; j < allIntString.length; j++) {
			if (allIntString[j].length() > 0) {
				list.add(Integer.parseInt(allIntString[j])); // read all
				// integers to
				// an int array
			}
		}
		ArrayList<String> output = new ArrayList<String>();
		ArrayList<String> outputSum = new ArrayList<String>();
		subset(output, list, 0, 0);
		System.out.println(output);
		for (int k = 0; k < output.size(); k++) {
			int currentSum = 0;
			if (output.get(k) != "") {
				String[] a = output.get(k).split(",");
				for (int p = 0; p < a.length; p++) {
					if (a[p] != "" && a[p].length()>0)
						{
						currentSum += Integer.parseInt(a[p]);
						}
				}
				if (currentSum == sum) {
					outputSum.add(output.get(k));
				}

			}
		}
		System.out.println(outputSum);
		/*
		 * int counter = 0; while (counter < list.size()) { for (int k = 0; k <
		 * list.size(); k++) { System.out.println(""); System.out.print("{" +
		 * list.get(k) + "}"); // this is the set of n // numbers if (k ==
		 * list.size() - 1) counter++; for (int l = k + 1; l < list.size(); l++) {
		 * if (l == list.size() - 1) counter++; System.out.println("");
		 * System.out.print("{" + list.get(k) + "," + list.get(l) + "}"); for
		 * (int m = l + 1; m < list.size(); m++){ if (m == list.size() - 1)
		 * counter++; System.out.println(""); System.out.print("{" + list.get(k) +
		 * "," + list.get(l) + "," + list.get(m) + "}"); for (int n = m + 1; n <
		 * list.size(); n++){ if (n == list.size() - 1) counter++;
		 * System.out.println(""); System.out.print("{" + list.get(k) + "," +
		 * list.get(l) + "," + list.get(m) + "," + list.get(n) + "}"); } } } } }
		 */
	}

	int currentSize = 0;

	public void subset(ArrayList<String> output, ArrayList<Integer> l, int i,
			int pointer) {
		if (i == 0) {
			output.add("");
			output.add("," + l.get(i));
			pointer = pointer + 2;
		} else if (i > 0 && i < l.size()) {
			currentSize = output.size();
			for (int m = 0; m < currentSize; m++) {
				output.add(pointer, output.get(m) + "," + l.get(i));
				pointer++;
			}
		} else
			return;

		subset(output, l, i + 1, pointer);

	}

	public static void main(String[] args) throws Exception {

		AllSubsets as = new AllSubsets();
		as.process();
	}

}
