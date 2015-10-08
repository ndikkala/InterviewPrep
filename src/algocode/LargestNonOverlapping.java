package algocode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * This method returns largest set of non-overlapping intervals from the given input. 
 * Run time is O(nlogn). 
 * The input has intervals with start and end value.
 * Both the start and end values are sorted as separate numbers on the number line using a sort algo like mergesort which is O(nlogn), while sorting the start and
 * end point pairs are tracked by using notations like s1,s2,e1,e2. Then the sorted list is looped through once and counter variable is computed at every step.
 * Counter increases if a start pt is encountered and decreases when an end pt is encountered. The condition used for adding intervals to the output is, if counter
 * has decreased, get the corresponding start pt and check if its less than the end pt of the previously added interval to the output, if so, add it.
 */

public class LargestNonOverlapping {
	
	private ArrayList<Integer> input1;
    private int size;
 

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//input is given as each interval separated by space and each pt within an interval separated by comma
		String[] inputStr = in.readLine().split(" ");
		ArrayList<Integer> input = new ArrayList<Integer>();
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		//Get the values of different pts on the number line from the input
		for(int i=0;i<inputStr.length;i++){
			String[] str =  inputStr[i].split(",");
			int start =  Integer.parseInt(str[0]);
			int end =  Integer.parseInt(str[1]);
			input.add(start);
			input.add(end);
			hm.put(end,start);
			
		}
		
		ArrayList<int[]> output = new ArrayList<int[]>();
		
		LargestNonOverlapping lno = new LargestNonOverlapping();
		
		lno.computelno(input, hm, output);
		//System.out.println("hm "+hm);
		System.out.print("output is ");
		for(int i = 0; i<output.size();i++){
		System.out.print(Arrays.toString(output.get(i)));
		}
		
		
	}
	
	public void computelno(ArrayList<Integer> input, Map hm,ArrayList<int[]> output ) {
		this.input1 = input;
		this.size = input.size();
		doMergeSort(0, size - 1);
		//System.out.println("Sorted input: " + input1);
		int counter = 0;
		for(int k=0; k<input1.size();k++){
			int currentElement = input1.get(k);
			//System.out.println(" Current element getting processed: "+ currentElement);
			if(hm.containsKey(currentElement)){
				//if current pt is a key, then its an end pt
				//System.out.println(" Current element is an end pt ");
				counter--;
				int corrElement = Integer.parseInt(hm.get(currentElement).toString());
				int [] interval = new int[2];
				interval[0] = corrElement;
				interval[1] = currentElement;
				//System.out.println(" Interval "+Arrays.toString(interval));
				int n = output.size();
				if(n!=0){
						int[] lastInterval = output.get(n-1);
						if((currentElement == corrElement && corrElement == lastInterval[1]) || (lastInterval[1] == lastInterval[0] &&corrElement == lastInterval[1])){
							//edge case: intervals are overlapping, don't add. ex., 1,2 and 2,2 or 2,2 2,3
						}
						else if(corrElement >= lastInterval[1]){
							//System.out.println(" Adding interval to op "+Arrays.toString(interval));
							output.add(interval);
						}
				}
				else{
					//System.out.println(" Adding interval to op "+Arrays.toString(interval));
					output.add(interval );
				}
    			
    			
				
			}
			else if(hm.containsValue(currentElement)){
				//if current pt is a value then its a start pt
				//System.out.println(" Current element is a start pt ");
				counter++;
			}
		}
		
		
	}
	
	private void doMergeSort(int lowerIndex, int higherIndex) {
        
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the arraylist
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the arraylist
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }
	
	 
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
    	//Split the input into left and right
    	ArrayList<Integer> left = new ArrayList<Integer>();
    	ArrayList<Integer> right = new ArrayList<Integer>();
    	for(int i = lowerIndex; i<=middle; i++){
    		left.add(input1.get(i));
    	}
    	for(int i = middle+1; i<=higherIndex; i++){
    		right.add(input1.get(i));
    	}
    	
    	int l=0;
    	int r=0;
    	//Now move the ptrs l and m for the left and right and compare and form sorted input, put it in input1
    	for(int j=lowerIndex;j<=higherIndex;j++){
    		if(l<left.size() && !(r<right.size())){
    			//right is done and only left elements are remaining
    			try{
    				input1.set(j, left.get(l));
    			}
    			catch ( IndexOutOfBoundsException e ) {
    				input1.add(j,left.get(l));
    			}
    			//System.out.println("Temp index "+j+ " value of left " +left.get(l)+ " temp " + input1);
    			l++;
    		}
    		else if(r<right.size() && !(l<left.size())){
    			//left is done and only right elements are remaining
    			try{
    				input1.set(j, right.get(r));
    			}
    			catch ( IndexOutOfBoundsException e ) {
    				input1.add(j,right.get(r));
    			}
    			//System.out.println("Temp index "+j+ " value of right " +right.get(r)+ " temp " + input1);
				r++;
    		}
    		else if (r<right.size() && l<left.size()){
    			//both left and right are remaining
    			//System.out.println("j "+j );
    			//System.out.println("l "+l );
    			//System.out.println("r "+r );
    			//System.out.println("left "+left );
    			//System.out.println("right "+right );
    			
    			if(left.get(l) < right.get(r))
    			{
    				try{
    					input1.set(j, left.get(l));
        			}
    				catch ( IndexOutOfBoundsException e ) {
    					input1.add(j,left.get(l));
        			}
    				//System.out.println("Temp index "+j+ " value of left " +left.get(l)+ " temp " + input1);
    				l++;
    				
    			}
    			else if(right.get(r) < left.get(l)){
    				try{
    					input1.set(j, right.get(r));
        			}
    				catch ( IndexOutOfBoundsException e ) {
    					input1.add(j,right.get(r));
        			}
    				//System.out.println("Temp index "+j+ " value of right " +right.get(r)+ " temp " + input1);
    				r++;
    				
    			}
    			
    		}
    		
    	}
 
    }

}
