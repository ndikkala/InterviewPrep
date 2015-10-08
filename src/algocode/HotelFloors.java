package algocode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class HotelFloors {
	 char[][] input;
	 boolean[][] visited;
	 //	Number of Rooms initialized to 0 for every test case;
	 int rooms = 0;
	 int persons = 0;
	 //There is only one output for all test cases, so make it static
	 static double[] output ;
	 	/*
		 * Given a 2-d matrix that represents free space (-) or wall (#) or (occupied space), determine the
		 * number of rooms. Room is a contigous set of connected free spaces or occupied spaces.
		 * Contigous is defined as modular difference of 1 in x or y but NOT both x and y
		 * coordinates, thus leaving the diagonal neighbours. So, a cell can have atmost 4 neighbours.
		 */
		public static void main(String[] args) {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						System.in));
				int t = Integer.parseInt(in.readLine());
				if (t > 10 || t < 1)
					return;
				output = new double[t];
				// for every test case
				for (int i = 0; i < t; i++) {
					HotelFloors hf = new HotelFloors();
					//number of rows and cols
					String[] s = in.readLine().split(" ");
					int n = Integer.parseInt(s[0]);
					int m = Integer.parseInt(s[1]);
					if (n < 1 || n > 101 || m < 1 || m > 101)
						return;
					hf.prepare(n,m,in);
					output[i] = hf.process(n,m);
				}
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						System.out));
				for (int i = 0; i < output.length; i++) {
					double result = output[i];
					System.out.printf("%.2f", result);
					System.out.println();
					//out.write(Double.toString(result));
					//out.write("\n");
				}
				out.flush();
				out.close();
			}
			catch (Exception e) {
				System.exit(0);
			}
		}
		
		void prepare(int noOfRows, int noOfCols, BufferedReader input){
			//Initialize everything for each test case
			this.input = new char[noOfRows][noOfCols];
			this.visited = new boolean[noOfRows][noOfCols];
			rooms = 0;
			persons = 0;
			//Read the next n lines as n rows
			for (int j = 0; j < noOfRows; j++) {
				try{
					this.input[j] = input.readLine().trim().toCharArray();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		double process(int rows, int cols){
			//For every unvisited cell in matrix, look for spaces - free/occupied
			for (int j = 0; j < rows; j++) {
				for (int k = 0; k < cols; k++) {
					if (this.input == null) {
						rooms = 0;
						persons = 0;
						break;
					}
					// Process only valid indices indices
					if (validCheck(j, k, rows, cols)) {
						// If a space is encountered, search for all connected spaces and keep track of occupied ones
						if (((this.input[j][k] == '-') || (this.input[j][k] == '*')) && !(this.visited[j][k])) {
							findRooms(j, k, rows, cols);
							rooms++;

						}
						// If wall, mark visited
						else {
							this.visited[j][k] = true;
						}
					}
				}
			}
			double result = (double)persons/(double)rooms;
			
			return result;
			
		}
		
		void findRooms(int row, int col, int rows, int cols){
			this.visited[row][col] = true;
			if(this.input[row][col] == '*')
				persons++;
			//Up
			if (validCheck(row - 1, col, rows, cols)) {
				if (((this.input[row - 1][col] == '-') || (this.input[row - 1][col] == '*')) && !(this.visited[row - 1][col])) {
					findRooms(row - 1, col, rows, cols);
				}
			}
			//Left
			if (validCheck(row , col-1, rows, cols)) {
				if (((this.input[row][col-1] == '-') || (this.input[row][col-1] == '*')) && !(this.visited[row][col-1])) {
					findRooms(row, col-1, rows, cols);
				}
			}
			//Down
			if (validCheck(row + 1, col, rows, cols)) {
				if (((this.input[row + 1][col] == '-') || (this.input[row + 1][col] == '*')) && !(this.visited[row + 1][col])) {
					findRooms(row + 1, col, rows, cols);
				}
			}
			//Right
			if (validCheck(row, col+1, rows, cols)) {
				if (((this.input[row][col+1] == '-') || (this.input[row][col+1] == '*')) && !(this.visited[row][col+1])) {
					findRooms(row, col+1, rows, cols);
				}
			}
		}
		
		boolean validCheck(int row, int col, int n, int m){
			//Invalid index, dont process it
			if (row < 0 || col < 0 || row > (n - 1)|| col > (m - 1)) {
				return false;
			}
			return true;
		}
		
		public static double round(double value, int places) {
		    if (places < 0) throw new IllegalArgumentException();

		    BigDecimal bd = new BigDecimal(value);
		    bd = bd.setScale(places, RoundingMode.HALF_UP);
		    return bd.doubleValue();
		}
}
