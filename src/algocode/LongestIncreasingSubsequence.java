package algocode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
/*
 * Given a list of numbers, find the longest increasing subsequence
 * Subsequence: Non-contiguous subset of given set, which maintains order but not continuity.
 * Substring: Subset of given set of chars, which maintains order and continuity.
 * 1 2 4 8 3 4 10 200 23 5 9 10 12
 * Final output: [1, 2, 3, 4, 5, 9, 10, 12]
 * Pure DP solution gives a 2^n run time, so its not effective.
 * We compute f(m) by using f(m-1), f(m-2)...f(0). Largest of these is the answer, not f(n) unlike typical DP soln.
 * f(m) is computed in such a way that m is always considered in f(m), if m < the last element of largest of previous f(m-1)... f(0) - its added to that set
 * otherwise, f(m) = {m}.
 * 5,100,101,6,7,8 -> f(1) = 5, f(2) = 5, 100, f(3) = 5,100,101 , f(4) = 5,6, f(7) = 5,6,7 , f(8) = 5,6,7,8 - here f(8) is largest and its the ans.
 * 5,100,101,6,4,3 -> f(1) = 5, f(2) = 5, 100, f(3) = 5,100,101, f(4) = 5,6, f(7) = 4 , f(8) = 3 - here f(3) is the largest and its the ans.
 * Simpler version of this algo in Tushar Roy's utube vid..O(N^2) using one temporary array that tracks the count of lis at each element
 * Another algo: O(NlogN) using binary search, uses two temporary arrays, one of which stores min. of last value of lis of particular length
 * and second array stores the index value which the current index follows in the lis
 */
public class LongestIncreasingSubsequence {

	public void computelis(String[] input, ArrayList<ArrayList<String>> output,
			int m, int max, int index) {
		if (m < input.length) {
			int counter = m;

			// To check with f(m-1), f(m-2) and so on, but if mth element suits
			// the longest sequence so far, concatenate with that
			while (counter >= 0) {
				if (counter == 0) {
					//Just add the first element for f(0)
					if (output.size() == 0) {
						ArrayList<String> s = new ArrayList<String>();
						s.add(input[m]);
						output.add(counter, s);
					}

				} else if (Integer.parseInt(output.get(index).get(
						output.get(index).size() - 1)) < Integer
						.parseInt(input[m])) {
					// longest of previous outputs
					ArrayList<String> s1 = new ArrayList<String>(output
							.get(index));
					s1.add(s1.size(), input[m]);
					output.add(m, s1);
					break;
				}

				else {
					// longest of previous outputs didn't work, now check the
					// remaining one by one going from higher m to lower m
					int lastindex = output.get(counter - 1).size();

					if (Integer.parseInt(output.get(counter - 1).get(
							lastindex - 1)) < Integer.parseInt(input[m])) {
						// if last element of f(m-1) is lesser than mth element,
						// add mth element to the sequence
						ArrayList<String> s2 = new ArrayList<String>(output
								.get(counter - 1));
						s2.add(s2.size(), input[m]);
						output.add(m, s2);
						break;
					}
				}

				counter--;
			}

			if (m == output.size()) {
				// previous elements did not get added, so add the mth element
				// alone
				ArrayList<String> s = new ArrayList<String>();
				s.add(input[m]);
				output.add(m, s);

			}

			if (max < output.get(m).size()) {
				max = output.get(m).size();
				index = m;
			}
		}

		if (m < input.length) {
			computelis(input, output, m + 1, max, index);
		} else if (m == input.length) {
			System.out.println("Final output: " + output.get(index));
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		lis.computelis(input, output, 0, 0, 0);

	}
}
