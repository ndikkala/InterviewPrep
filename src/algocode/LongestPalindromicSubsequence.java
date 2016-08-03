package algocode;

import java.io.*;
/*
 * Given a string, return the maximum possible product of lengths of two non-overlapping pallindromic subsequences
 */

public class LongestPalindromicSubsequence {

	int[][] dp = new int[3001][3001];

	public static void main(String[] args) throws Exception {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = in.readLine();
		LongestPalindromicSubsequence sol = new LongestPalindromicSubsequence();
		System.out.print(sol.lps(s));
	}

	void initializeMatrix(int N) {
		dp = new int[N + 1][N + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) {
					dp[i][j] = 1;
				} else {
					dp[i][j] = 0;
				}
			}
		}
	}

	int max(int a, int b) {
		if (a > b)
			return a;
		else if (b > a)
			return b;
		else
			return a;
	}

	int lps(String inputStr) {
		char[] input = inputStr.toCharArray();
		initializeMatrix(inputStr.length());
		int N = inputStr.length();
		int diff = 1;
		int j = 0;
		while (diff != N) {
			for (int i = 0; i < N - 1; i++) {
				j = i + diff;
				//System.out.println("i = " + i + " j = " + j);
				// System.out.println("input[i]= "+input[i] + " input[j] = "+
				// input[j]);
				if(j<N){
				if (input[i] == input[j]) {
					// Diagonally left bottom cell
					dp[i][j] = dp[i + 1][j - 1] + 2;
				} else {
					// Max of left and bottom cells
					dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
				}
				}
			}
			diff++;
		}
		
		/*
		 * dp[0][N-1] has the length of longest pallindromic subsequence
		 */

		/*
		 * Print the array for debugging
		 *
		for (int m = 0; m <= N; m++) {
			for (int k = 0; k <= N; k++) {
				System.out.print(dp[m][k] + "  ");
			}
			System.out.print("\n");
		}
		*/

		int max = 1;
		for (int k = 1; k < N; k++) {
			if (k + 1 < N - 1) {
				if ((dp[0][k] * dp[k + 1][N - 1]) > max) {
					max = dp[0][k] * dp[k + 1][N - 1];
				}
			}
		}

		return max;
	}
}