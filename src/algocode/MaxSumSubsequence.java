package algocode;
/*
 * Compute the maximum sum possible with a subsequence in an array such that no two elements of the subsequence are adjacent.
 */
public class MaxSumSubsequence {
	public static void main(String[] args){
		int[] input =  {5,  5, 10, 40, 50, 35};
		System.out.println(compute(input));
	}
	/*
	 * Loop for all elements in arr[] and maintain two sums incl and excl where incl = Max sum including the previous element and excl = Max sum excluding the previous element.
	 * Max sum excluding the current element will be max(incl, excl) and max sum including the current element will be excl + current element (Note that only excl is considered because elements cannot be adjacent).
	 * At the end of the loop return max of incl and excl.
	 */
	public static int compute(int[] array){
		int inclusive = array[0];
		int exclusive = 0;
		int excl_new = 0;
		
		for(int i=1; i<array.length;i++){
			excl_new = Math.max(exclusive, inclusive);
			inclusive = exclusive + array[i];
			exclusive = excl_new;
		}
		
		return Math.max(inclusive, exclusive);
	}

}
