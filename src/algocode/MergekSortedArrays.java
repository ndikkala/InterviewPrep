package algocode;
import java.io.*;
import java.util.*;
/*
 * Merge k sorted lists with N total elements. Time complexity is O(Nlogk). Min heap is used to merge the elements.
 * Output array of size N. Min root of min heap is added to output.
 * Increment column for min element's cell.
 * Sample input: 
 * 20
 * 4
 * 2 3 4 8 9
 * 1 6 15 20 24
 * 11 18 19 21 22
 * 0 5 7 10 17
 * 
 */

public class MergekSortedArrays {
	
private static int[][] input =  new int[1024][1024];
private static int[] output = new int[1024];

public static void main(String[] args) throws Exception{
	
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	//Total number of elements
	int N = Integer.parseInt(in.readLine());
	output = new int[N];
	//Number of arrays
	int k = Integer.parseInt(in.readLine());
	int cols = N/k;
	input =  new int[k][cols];
	//Next k lines
	for(int i=0; i<k; i++){
		String[] array = in.readLine().split(" ");
		for(int j=0; j< array.length; j++){
			input[i][j] = Integer.parseInt(array[j]);
		}
	}
	MergekSortedArrays msk = new MergekSortedArrays();
	msk.mergekSortedArrays(k, cols);
	for(int o: output){
		System.out.println(o);
	}
	

	
}

public  void mergekSortedArrays(int k, int cols){
	PriorityQueue<Cell> pq = new PriorityQueue<Cell>();
	int index = 0;
	for(int i=0; i<k; i++){
		Cell c =  new Cell(i, index, input[i][index]);
		pq.add(c);
	}
	
	while(pq.peek()!=null){
		Cell min = pq.poll();
		output[index] = min.val;
		index++;
		int row = min.row;
		int col = min.col;
		if((col+1) <= (cols-1)){
			pq.add(new Cell(row, (col+1),input[row][col+1]));
		}
	}
}


class Cell implements Comparable<Cell> {
	int row;
	int col;
	int val;
	
	Cell(int row, int col, int val){
		this.row = row;
		this.col = col;
		this.val = val;
	}

	public int compareTo(Cell cell) {
		
		if(this.val<cell.val){
			return -1;
		}
		else if(this.val>cell.val){
			return 1;
		}
		return 0;
	}
	
}

}
