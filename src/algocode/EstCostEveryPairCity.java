package algocode;
//import java.io.*;
import java.util.*;
//import java.text.*;
//import java.math.*;
///import java.util.regex.*;

/*
 * Hackerrank contest: Super Highways
 */
public class EstCostEveryPairCity {
    static long modulus = (long) (Math.pow(10,9))+9;
    long maxN = (long)Math.pow(10, 15);
    static HashMap<String, Long> memoize =  new HashMap<String, Long>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int a0 = 0; a0 < T; a0++){
            long N = in.nextLong();
            long K = in.nextLong();
            long C = in.nextLong();
            String key = N + "_" + K+ "_" + C;
            long estimatedCost = 0;
            if(!memoize.containsKey(key)){
                long totalCost = mod(computeCost(N,K,C), modulus);
                estimatedCost =  mod(totalCost, modulus);
                memoize.put(key, estimatedCost);
            }
            else{
                estimatedCost = memoize.get(key);
            }
            
            System.out.println(estimatedCost);
        }
        in.close();
    }
    
    public static long computeCost(long N, long K, long C){
        long noOfTimesDifferenceOccurs = N-1;
        long difference = 1;
        long total = 0;
        long currentCost = 0;
        
        while((noOfTimesDifferenceOccurs >=1) || (difference<N) ){
            currentCost =  mod((((difference * K)+C)*noOfTimesDifferenceOccurs), modulus);
            total += mod(currentCost, modulus);
            currentCost = 0;
            noOfTimesDifferenceOccurs--;
            difference++;
        }
        
        return total;
        
    }
    
    
    public static long mod(long x, long n)
	{
		
	   long result = x % n;
	   if (result < 0)
	   {
	       result += n;
	   }
	   return result;
	}
}
