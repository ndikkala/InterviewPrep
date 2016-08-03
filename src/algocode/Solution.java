package algocode;
import java.io.*;
import java.util.*;
/*
 * Write a function that determines if there are duplicates in a 2 dimensional array within k indices. 
 * First row is no. of rows, then the matrix in next line
 * and then k value in the next line. Within k indices is the distance, 
 * sum of diffs of indices between the duplicate values should be less than or equal to k
 */

public class Solution {
public static void main(String args[] ) throws Exception {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int rows = Integer.parseInt(in.readLine());
    int [][]input = new int[501][501];
    int noOfColumns = 0;
    for(int i=0; i< rows; i++){
        String []columns = in.readLine().split(" ");
        noOfColumns = columns.length;
        for(int j=0; j< columns.length; j++){
            input[i][j] = Integer.parseInt(columns[j]);
        }
    }
    int k = Integer.parseInt(in.readLine());
    Solution s = new Solution();
    if(s.check(input,k, rows, noOfColumns))
        System.out.print("YES");
    else
        System.out.print("NO");
}
   
    public boolean check(int [][]input, int k, int rows, int noOfColumns){
       HashMap<Integer,int[]> map = new HashMap <Integer,int[]>();
        
        for(int i =0; i<rows; i++){
        for (int j = 0; j<noOfColumns; j++){
             
        if(map.containsKey(input[i][j]))
             {
            	 int indices[] = map.get(input[i][j]);
            	 int diffRows = Math.abs(indices[0]-i);
                 int diffCols = Math.abs(indices[1]-j);
                 int sum =  diffRows + diffCols;
                // System.out.println("Sum "+sum+" k "+k);
                 
                 if(sum<=k){
                	 return true;
                 }
             }
             
             map.put(input[i][j], new int[] {i,j});
             
        }
        
        
        }
        return false;
    }
    
}