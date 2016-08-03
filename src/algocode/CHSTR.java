package algocode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

class CHSTR {

	private static final int MOD = 1000000007;

	private static int temp[];
	
	private static int mod(int x)
	{
		
	   int result = x % MOD;
	   if (result < 0)
	   {
	       result += MOD;
	   }
	   return result;
	}
	
	private int factorial(int N) {
		if (N == 0 || N == 1)
			return 1;
		else {
			if (temp[N] != 0)
				return temp[N] ;
			else
				return temp[N] = mod((N * factorial(N - 1)));

		}
	}

	public ArrayList<String> getSubStrings(String input) {
		int length = input.length();
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < length; i++) {
			for (int j = 1; j <= length - i; j++) {
				list.add(input.substring(i, i + j));
			}
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
		ArrayList<Integer> output = new ArrayList<Integer>();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		OutputWriter out = new OutputWriter(System.out);
		int noOfTestCases = Integer.parseInt(in.readLine());
		try {
			for (int t = 0; t < noOfTestCases; t++) {
				String[] secondLine = in.readLine().split(" ");
				//int N = Integer.parseInt(secondLine[0]);
				int Q = Integer.parseInt(secondLine[1]);
				int betterQ = mod(Q);
				temp = new int[betterQ];
				String inputString = in.readLine();
				// String[] input = inputString.split("(?!^)");
				ArrayList<Integer> question = new ArrayList<Integer>();
				for (int q = 0; q < Q; q++) {
					question.add(Integer.parseInt(in.readLine()));
				}
				ArrayList<String> listOfSubStrings = new ArrayList<String>();
				CHSTR cs = new CHSTR();
				long start =  System.currentTimeMillis();
				listOfSubStrings = cs.getSubStrings(inputString);
				long end =  System.currentTimeMillis();
				long elapsed = end - start;
				System.out.println("Time substrings method took: "+elapsed);
				cs.computeOutput(listOfSubStrings, question, output);
			}
			for (int i = 0; i < output.size(); i++) {
				out.printLine(output.get(i));
			}

			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void computeOutput(ArrayList<String> list,
			ArrayList<Integer> question, ArrayList<Integer> output) {
		HashMap<String, String> hm = new HashMap<String, String>();
		long start =  System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			String currentString = list.get(i);
			if (hm.containsKey(currentString)) {
				String currentVal = hm.get(currentString);
				hm.put(currentString, currentVal + "," + i);
			} else {
				hm.put(currentString, "" + i);
			}
		}
		long end =  System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Time making hashmap took: "+elapsed);
		for (int q = 0; q < question.size(); q++) {
			int total = 0;
			Iterator<?> it = hm.entrySet().iterator();
			long start1 =  System.currentTimeMillis();
			while (it.hasNext()) {
				@SuppressWarnings("unchecked")
				Map.Entry<String,String> pair = (Map.Entry<String, String>) it.next();
				String currentString = pair.getKey().toString();
				String currentVal = hm.get(currentString);
				String[] separateVals = currentVal.split(",");
				int noOfVals = mod(separateVals.length);
				int k = mod(question.get(q));
				int totWithinKey = 1;
				if (noOfVals >= k) {
					// When there are n different values and n >= k, then we have
					// nCk possible combinations
					int n = noOfVals;
					int diff = mod(n - k);
					int fact = 1;
					int half = mod(n / 2);
					if (k >= half) {
						// k! is bigger, nCk = (n.n-1.n-2...k)/(n-k)!
						while (n > k) {
							totWithinKey = mod(totWithinKey * (n));
							n--;
						}
						fact = factorial(diff);
						totWithinKey = mod(totWithinKey / fact);
					} else if (k <= half) {
						// n-k! is bigger, nCk = (n.n-1.n-2...(n-k+1)/(k)!
						while (n > (diff)) {
							totWithinKey = mod(totWithinKey * (n));
							n--;
						}
						fact = factorial(k);
						totWithinKey = mod(totWithinKey / fact);
						
					}
					total = mod(total + totWithinKey);
				}

			}
			long end1 =  System.currentTimeMillis();
			long elapsed1 = end1 - start1;
			System.out.println("Time taken for processing o/p for each q: "+elapsed1);
			output.add((Integer) (mod(total)));

		}
	}
}

class OutputWriter {
	private final PrintWriter writer;

	public OutputWriter(OutputStream stream) {
		this.writer = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(stream)));
	}

	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}

	public void print(Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0)
				writer.print(' ');
			writer.print(objects[i]);
		}
	}

	public void printLine(Object... objects) {
		print(objects);
		writer.println();
	}

	public void flush() {
		writer.flush();
	}

	public void close() {
		writer.close();
	}
}
