package algocode;

import java.io.*;

/*
 * Test Cases:
3
1 1
.
6 4
....
..#.
.#..
....
.##.
....
10 10
..........
.##...##..
..##.#....
.#........
.#####..#.
.......#..
.....#.#..
..##.###..
..........
..........
*/

class IslandsTailRecursion {
	 char[][] input;
	 boolean[][] visited;
	 //	Number of islands initialized to 0 for every test case;
	 int count = 0;
	 boolean allHashes =  true;
	 //There is only one output for all test cases, so make it static
	 static int[] output ;

	/*
	 * Given a 2-d matrix that represents water (.) or land (#), determine the
	 * number of islands. Island is a contigous set of connected lands.
	 * Contigous is defined as modular difference of 1 in x or y or both x and y
	 * coordinates. So, an element can have atmost 8 neighbours.
	 */
	public static void main(String[] args) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			int t = Integer.parseInt(in.readLine());
			if (t > 20 || t < 1)
				return;
			output = new int[t];
			// for every test case
			for (int i = 0; i < t; i++) {
				
				IslandsTailRecursion itr = new IslandsTailRecursion();
				//number of rows and cols
				String[] s = in.readLine().split(" ");
				int n = Integer.parseInt(s[0]);
				int m = Integer.parseInt(s[1]);
				if (n < 1 || n > 201 || m < 1 || m > 201)
					return;
				
				itr.make(n,m,in);
				output[i] = itr.process(n,m);
			}
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					System.out));
			for (int i = 0; i < output.length; i++) {
				int result = output[i];
				out.write(Integer.toString(result));
				out.write("\n");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			System.exit(0);
		}
	}
	
	void make(int n, int m, BufferedReader in){
		this.input = new char[n][m];
		this.visited = new boolean[n][m];
		//this.allHashes = true;
		//Initialize the count of islands for every test case
		count = 0;
		//Read the next n lines as n rows
		for (int j = 0; j < n; j++) {
			try{
			//input[j] = in.readLine().trim().toCharArray();
			char[] chars = in.readLine().toCharArray();
			//Read all the chars in a line as cols
			for (int k = 0; k < m; k++) {
				if (chars[k] != '#' && allHashes) {
					allHashes = false;
				}
				this.input[j][k] = chars[k];
				this.visited[j][k] = false;
			}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	int process(int n, int m){
		//if (this.allHashes) {
		//count = 1;
	    //} else {
		//For every unvisited element in matrix, look for lands
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				if (this.input == null) {
					count = 0;
					break;
				}
				// Process only valid indices and non-border indices
				if (isValid(j, k, n, m)) {
					// If a land is encountered, search for all
					// connected
					// lands
					if ((this.input[j][k]) == '#') {
						count++;
						this.visited[j][k] = true;
						System.out.println("main r" + (j)+ "c"+ (k));
						searchAll(j, k, n, m);

					}
					// If water, mark visited
					else {
						this.visited[j][k] = true;
					}
				}
			}
		}
		
	//}
		return count;
	}
	
	void searchAll(int j, int k, int n, int m){
		search1(j, k, n, m);
		search2(j, k, n, m);
		search3(j, k, n, m);
		search4(j, k, n, m);
		search5(j, k, n, m);
		search6(j, k, n, m);
		search7(j, k, n, m);
		search8(j, k, n, m);
	}

	 boolean search1(int row1, int col1, int n, int m) {
		return aux1(row1,col1,n,m);
	}
	 boolean search2(int row2, int col2, int n, int m) {
			return aux2(row2,col2,n,m);
		}
	 boolean search3(int row3, int col3, int n, int m) {
			return aux3(row3,col3,n,m);
		}
	 boolean search4(int row4, int col4, int n, int m) {
			return aux4(row4,col4,n,m);
		}
	 boolean search5(int row5, int col5, int n, int m) {
			return aux5(row5,col5,n,m);
		}
	 boolean search6(int row6, int col6, int n, int m) {
			return aux6(row6,col6,n,m);
		}
	 boolean search7(int row7, int col7, int n, int m) {
			return aux7(row7,col7,n,m);
		}
	 boolean search8(int row8, int col8, int n, int m) {
			return aux8(row8,col8,n,m);
		}
	 boolean aux1(int r1, int c1, int n, int m){
		 if (isValid(r1 - 1, c1, n, m)) {
			 this.visited[r1 - 1][c1] = true;
			 if ((this.input[r1 - 1][c1]) == '#') {
				 System.out.println("1 r" + (r1 - 1)+ "c"+ (c1));
				return aux1(r1 - 1, c1, n, m);
			 }
			}
			return true;
	 }
	 
	 boolean aux2(int r2, int c2, int n, int m){
		 if (isValid(r2, c2 - 1, n, m)) {
			 this.visited[r2][c2 - 1] = true;
				
				if (this.input[r2][c2 - 1] == '#') {
					System.out.println("2 r" + (r2)+ "c"+ (c2-1));
					return aux2(r2, c2 - 1, n, m);
				}
			}
			return true;
	 }
	 boolean aux3(int r3, int c3, int n, int m){
		  if (isValid(r3 - 1, c3 - 1, n, m)) {
			  this.visited[r3 - 1][c3 - 1] = true;
				
				if (this.input[r3 - 1][c3 - 1] == '#') {
					System.out.println("3 r" + (r3-1)+ "c"+ (c3-1));
					return aux3(r3 - 1, c3 - 1, n, m);
				}
			}
			return true;
	 }
	 boolean aux4(int r4, int c4, int n, int m){
		if (isValid(r4 + 1, c4, n, m)) {
			this.visited[r4 + 1][c4] = true;
				
				if (this.input[r4 + 1][c4] == '#') {
					System.out.println("4 r" + (r4+1)+ "c"+ (c4));
					return aux4(r4 + 1, c4, n, m);
				}
			}
			return true;
	 }
	 boolean aux5(int r5, int c5, int n, int m){
		 if (isValid(r5, c5 + 1, n, m)) {
			 this.visited[r5][c5 + 1] = true;
				
				if (this.input[r5][c5 + 1] == '#') {
					System.out.println("5 r" + (r5)+ "c"+ (c5+1));
					return aux5(r5, c5 + 1, n, m);
				}
			}
			return true;
	 }
	 boolean aux6(int r6, int c6, int n, int m){
		 if (isValid(r6 + 1, c6 + 1, n, m)) {
			 this.visited[r6 + 1][c6 + 1] = true;
				if (this.input[r6 + 1][c6 + 1] == '#') {
					System.out.println("6 r" + (r6+1)+ "c"+ (c6+1));
					return aux6(r6 + 1, c6 + 1, n, m);
				}
			}
			return true;
	 }
	 boolean aux7(int r7, int c7, int n, int m){
		 
		 if (isValid(r7 - 1, c7 + 1, n, m)) {
			 this.visited[r7 - 1][c7 + 1]  = true;
				
				if (this.input[r7 - 1][c7 + 1] == '#') {
					System.out.println("7 r" + (r7-1)+ "c"+ (c7+1));
					return aux7(r7 - 1, c7 + 1, n, m);
				}
				
			}
			return true;
	 }
	 boolean aux8(int r8, int c8, int n, int m){
		if (isValid(r8 + 1, c8 - 1, n, m)) {
			this.visited[r8 + 1][c8 - 1]  = true;
				if (this.input[r8 + 1][c8 - 1] == '#') {
					System.out.println("8 r" + (r8+1)+ "c"+ (c8-1));
					return aux8(r8 + 1, c8 - 1, n, m);
				}
				
			}
			return true;
	 }
			
	 

	 boolean isValid(int row, int col, int n, int m) {
		// Invalid index or Border index, dont process it, its always water
		if (row <= 0 || col <= 0 || row >= (n - 1)|| col >= (m - 1)) {
			return false;
		}
		if(this.visited[row][col]){
			return false;
		}
		
		return true;
	}

}

