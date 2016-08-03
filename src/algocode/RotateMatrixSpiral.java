package algocode;

import java.io.*;

public class RotateMatrixSpiral {

	public static void main(String args[]) throws Exception {
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
		RotateMatrixSpiral r = new RotateMatrixSpiral();

		r.rotate(input, rows, noOfColumns);
	}

	public void rotate(int[][] input, int rows, int cols) {
		if (rows != cols) {
			System.out.print("ERROR");
		} else {
			int[][] output = new int[1024][1024];
			// top left corner indices for each concentric circle
			int concentrici = 0;
			int concentricj = 0;
			// ptrs used for loops to traverse right, bottom, left and top
			int ptri = 0;
			int ptrj = 0;
			// limits used in the loops
			int endi = rows - 1;
			int endj = cols - 1;

			int mid = (int) Math.ceil(rows / 2);

			while (!(concentrici == mid && concentricj == mid)) {
				// go right
				for (int a = ptrj; a < endj; a++) {
					int b=a+1;
					output[ptri][b] = input[ptri][a];
					ptrj = b;
				}
				// go down
				for (int a = ptri ; a < endi; a++) {
					int b = a + 1;
					output[b][ptrj] = input[a][ptrj];
					ptri = b;
				}
				// go left
				for (int a = ptrj; a > concentrici; a--) {
					int b = a - 1;
					output[ptri][b] = input[ptri][a];
					ptrj = b;
				}
				// go up
				for (int a = ptri; a > concentricj; a--) {
					int  b = a - 1;
					output[b][ptrj] = input[a][ptrj];
					ptri = b;
				}

				concentrici++;
				concentricj++;
				//after every concentric circle, we are one layer inside so end indices reduce by 1
				endi--;
				endj--;
				//reset ptrs for next inner circle
				ptri=concentrici;
				ptrj=concentricj;
			}
			
			if(rows%2!=0 && (concentrici == mid && concentricj == mid)){
				//keep the center element for odd square matrix
				output[concentrici][concentricj] = input[concentrici][concentricj];
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
