package algocode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SubsetSum {

	static BufferedReader in;

	static StringTokenizer st;

	String next() {
		while (st != null && st.hasMoreTokens())
			try {
				return st.nextToken();
			} catch (Exception e) {

			}

		return null;
	}

	String nextLine() {
		try {
			return in.readLine();
		} catch (Exception e) {

		}
		return null;
	}

	void subsum(ArrayList<String> inList, int n, int sum) {
		// Each row is m=1 to n
		// Each column is k=1 to sum
		// output will be at dp[n][sum]
		
		/*
		 * f(m,k) represents a subset among the first m values in the list that sums to k
		 */
		
		String dp[][] = new String[n + 1][sum + 1];

		for (int m = 1; m <= n; m++) {
			for (int k = 1; k <= sum; k++) {
				//index is the index to traverse inList
				int index = m-1;
				if (m == 1) {
					if ((k - Integer.parseInt(inList.get(index))) == 0)
						dp[m][k] = inList.get(index);
					else
						dp[m][k] = "0";
				} else {
					// m is not 1

					// case 1: if f(m-1,k) exists
					if (dp[m - 1][k] != "0" && dp[m - 1][k] != null) {
						dp[m][k] = dp[m - 1][k];
					}

					else {
						// case 2: check for f(m-1,p) where p= k - mth value and
						// p is not less than 0
						int p = k - Integer.parseInt(inList.get(index));
						if(p==0) {
							// p=0, means mth element itself is k
							dp[m][k] = inList.get(index);
						}
						
						if (p < 0) {
							// impossible
							dp[m][k] = "0";
						} else if (p > 0) {
							// combine mth element with f(m-1,p) if f(m-1,p) exists
							if(dp[m - 1][p]!="0")
								dp[m][k] = dp[m - 1][p] + inList.get(index);
							else 
								dp[m][k] = "0";
						} 

					}
				}
			}
		}

		/*
		 * Print the array for debugging
		 * 
		for (int m = 1; m <= n; m++) {
			for (int k = 1; k <= sum; k++) {
				System.out.print(dp[m][k] + "  ");
			}
			System.out.print("\n");
		}
		*/

		System.out.println("Subset that totals to sum " + sum + " is "
				+ dp[n][sum]);

	}

	public static void main(String[] args) throws Exception {
		SubsetSum ss = new SubsetSum();
		in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		ArrayList<String> inList = new ArrayList<String>();
		for (String s : input) {
			if (s.length() > 0) {
				inList.add("" + Integer.parseInt(s));
			}
		}
		int sum = Integer.parseInt(in.readLine());
		int n = inList.size();
		ss.subsum(inList, n, sum);
	}

}
