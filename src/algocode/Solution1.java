package algocode;

import java.io.*;

public class Solution1 {
	public static void main(String args[]) throws Exception {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int rows = Integer.parseInt(in.readLine());
		int[][] input = new int[501][501];
		int noOfColumns = 0;
		for (int i = 0; i < rows; i++) {
			String[] columns = in.readLine().split(" ");
			noOfColumns = columns.length;
			for (int j = 0; j < columns.length; j++) {
				input[i][j] = Integer.parseInt(columns[j]);
			}
		}
		Solution1 s = new Solution1();

		s.rotate(input, rows, noOfColumns);
	}

	public void rotate(int[][] input, int rows, int cols) {
		int[][] output = new int[501][501];
		int n = rows; // for non-error case, rows=cols
		int endcol = n;
		int endrow = n;

		if (rows != cols) {
			System.out.print("ERROR");
		}

		else {
			// this while loops is for each concentric circle
			int m = 0; // rowcounter
			int k = 0; // columncounter

			while (m < endrow && k < endcol) {
				// mid element dont move it
				if (n%2!=0 && (m == ((n + 1) / 2 - 1) && k == ((n + 1) / 2 - 1))) {
					System.out.println("0: mid-output ");
					// n is odd and we r at the center
					output[m][k] = input[m][k];
				}
				// keeping row constant, and traversing through all columns in
				// that row
				for (int a = k; a < endcol; ++a) {
					System.out.println("1: output ");
					int b = a + 1;
					if ((n%2==0) ||((n%2!=0) && (!(m == ((n + 1) / 2 - 1) && k == ((n + 1) / 2 - 1))))) {
						if (b < endcol) {
							output[m][b] = input[m][a];
						} else {
							output[m + 1][a] = input[m][a];
						}
					} else {
						System.out.println("1: mid output m "+m+" k "+k);
						output[m][k] = input[m][k];
					}
					// System.out.println("1: output of "+m+ "," +(a+1)+ " is
					// input of "+m+ ","+ (a));
					// output[m][a+1] = input[m][a];
					// System.out.println(input[m][a]);
				}
				m++;
				// keeping column constant and traversing through all rows in
				// that column
				for (int a = m; a < endrow; ++a) {
					System.out.println("2: output ");
					int b = a + 1;
					if ((n%2==0) ||((n%2!=0) && (!(m == ((n + 1) / 2 - 1) && k == ((n + 1) / 2 - 1))))) {
						if (b < endrow)
							output[b][endcol - 1] = input[a][endcol - 1];
						else
							output[a][endcol - 2] = input[a][endcol - 1];
						// System.out.println("2: output of "+a+ ","
						// +(endcol-1)+ "
						// is input of "+(a-1)+ ","+ (endcol-1));
						// output[a][endcol-1] = input[a-1][endcol-1];
						// System.out.println(input[a][endcol-1]);
					} else {
						output[m][k] = input[m][k];
					}
				}
				endcol--;
				if (m < endrow) {
					for (int a = endcol - 1; a >= k; --a) {
						System.out.println("3: output ");
						int b = a - 1;
						if ((n%2==0) ||((n%2!=0) && (!(m == ((n + 1) / 2 - 1) && k == ((n + 1) / 2 - 1))))) {
							if (b >= k)
								output[endrow - 1][b] = input[endrow - 1][a];
							else
								output[endrow - 2][a] = input[endrow - 1][a];
							// System.out.println("3: output of "+(endrow-1)+
							// ","
							// +a+ " is input of "+(endrow-1)+ ","+ (a+1));
							// output[endrow-1][a] = input[endrow-1][a+1];
							// System.out.println(input[endrow-1][a]);
						} else {
							output[m][k] = input[m][k];
						}
					}
					endrow--;
				}
				if (k < endcol) {
					for (int a = endrow - 1; a > m; --a) {
						System.out.println("4: output ");
						int b = a - 1;
						if ((n%2==0) ||((n%2!=0) && (!(m == ((n + 1) / 2 - 1) && k == ((n + 1) / 2 - 1))))) {
							if (b >= m)
								output[b][k] = input[a][k];
							else
								output[a][k + 1] = input[a][k];

							// System.out.println("4: output of "+a+ "," +k+ "
							// is
							// input of "+(a+1)+ ","+ (k));
							// output[a][k] = input[a+1][k];
							// System.out.println(input[a][k]);
						} else {
							output[m][k] = input[m][k];
						}
					}
					k++;
				}

			}

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					System.out.print(output[i][j] + "  ");
				}
				System.out.print("\n");
			}

		}

	}
}