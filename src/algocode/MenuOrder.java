package algocode;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuOrder {
	static ArrayList<Integer> out =  new ArrayList<Integer>();
	static int sum = 1505;
 	public static void main(String[] args){
		
		int[] input = {212, 275, 130, 355, 480, 580};
		HashMap<Integer, String> menu =  new HashMap<Integer, String>();
		menu.put(212, "Mixed Salad");
		menu.put(275, "French Fries");
		menu.put(130, "Sandwich");
		menu.put(355, "Burger");
		menu.put(480, "Soup");
		menu.put(580, "Sampler Plate");
		
		ArrayList<Integer> output = new ArrayList<Integer>();
		getMenuOrder(input, sum, output);
		for(int o: out){
			System.out.println(menu.get(o));
		}
		
		
	}
	
 	public static void getMenuOrder(int[] input, int currentSum, ArrayList<Integer> output){
 		
 		if(currentSum==0){
 			//System.out.println(output.toString());
 			out = new ArrayList<Integer>();
 			for(int o:output){
 				out.add(o);
 			}
 			return;
 		}
 		
 		for(int j=0; j<input.length; j++){
 			if(input[j]<=currentSum){
 				//System.out.println("element "+input[j]);
 				currentSum -= input[j];
 				//System.out.println("updated sum "+currentSum);
 				output.add(input[j]);
 				getMenuOrder(input, currentSum, output);
 				output.remove(new Integer(input[j]));
 				currentSum += input[j];
 			}
 			
 		}
 		
 		return;
 	}
	
	
}
