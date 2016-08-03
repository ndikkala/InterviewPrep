package algocode;
import java.io.*;
import java.util.*;
/*
 * Given an undirected weighted graph, find its minimum spanning tree using Prim's algo
 */

public class FindMinSpanningTree {

	public static void main (String[]args) throws Exception{
		
		//Read input graph in the form adjacency matrix
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int[][] adj = new int[n][n];
		boolean[][] visited = new boolean[n][n];
		FindMinSpanningTree mst = new FindMinSpanningTree();
		for(int i=0; i< n; i++){
			String row = in.readLine();
			String[] eachCell = row.split(" ");
			for(int j=0; j<n; j++){
				adj[i][j] = Integer.parseInt(eachCell[j]);
			}
		}
		ArrayList<String> output = new ArrayList<String>();
		for(int k=0; k< n; k++){
			int[] minEdge = mst.getMinimum(adj[k], k, visited);
			int row = minEdge[1];
			int col = minEdge[2];
			if(!visited[row][col]){
				output.add("Weight of edge "+minEdge[0]+" Row "+ minEdge[1]+ "Col "+minEdge[2]);
				visited[row][col] = true;
				visited[col][row] = true;
			}
		}
		
		for(String s: output){
			System.out.println(s);
		}
		
		
	}
	
	int[] getMinimum(int[] input, int row, boolean[][] visited){
		//Row, col and weight
		int[] cell = new int[]{0,0,0};
		
		for(int j=0; j<input.length; j++){
			
			if((input[j]< cell[0] && input[j]>0) || (cell[0] == 0 && input[j]> 0)){
				cell[0] = input[j];
				cell[1] = row;
				cell[2] = j;
			}
			
		}
		
		return cell;
		
	}
}
