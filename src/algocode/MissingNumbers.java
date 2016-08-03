package algocode;

import java.io.*;
import java.util.*;
/*
 * A type of search algo solution
 * Missing Numbers from Hackerrank
 */

public class MissingNumbers {

    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        HashMap<Integer, Integer> a = new HashMap<Integer, Integer>();
        String[] A = in.readLine().split(" ");
        for(int i=0; i<n; i++){
            int num = Integer.parseInt(A[i]);
            if(a.containsKey(num)){
                int val = a.get(num);
                val++;
                a.put(num, val);
            }
            else{
                a.put(num, 1);
            }
        }
        int m = Integer.parseInt(in.readLine());
        HashMap<Integer, Integer> b = new HashMap<Integer, Integer>();
        String[] B = in.readLine().split(" ");
        for(int i=0; i<m; i++){
            int num = Integer.parseInt(B[i]);
            if(b.containsKey(num)){
                int val = b.get(num);
                val++;
                b.put(num, val);
            }
            else{
                b.put(num, 1);
            }
        }
       PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
       for (Map.Entry<Integer, Integer> entry : b.entrySet()) {
    	   //System.out.println("iterating thru perm map");
           int key =  entry.getKey();
           if(a.containsKey(key)){
        	   //System.out.println("found key in orig map= "+key);
               int aval = a.get(key);
               int bval = b.get(key);
               //System.out.println("freq in a = "+aval);
               //System.out.println("freq in b = "+bval);
               if(bval%aval !=0){
                   //missing
            	  //System.out.println("adding "+key);
                   pq.add(key);
               }
           }
           else{
               //missing, not there in orig list at all
        	   //System.out.println("adding "+key);
               
        	  pq.add(key);
           }
        }
      
       while(!pq.isEmpty()){
    	   System.out.print(pq.poll()+ " ");
       }
    }
}
