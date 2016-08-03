package algocode;

import java.io.*;
import java.util.*;

public class MergeOverlappingIntervals {
	
	class Interval implements Comparable<Interval>{
		int start;
		int end;
		
		Interval(int start, int end){
			this.start = start;
			this.end = end;
		}
		
		int getEnd(int start){
			return this.end;
		}

		public int compareTo(Interval o) {
			// TODO Auto-generated method stub
			if(this.start< o.start)
				return -1;
			else if(this.start > o.start)
				return 1;
			else
				return 0;
		}
	}

	public static void main(String[] args) throws Exception{
		MergeOverlappingIntervals moi = new MergeOverlappingIntervals();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input =  in.readLine();
		String[] intervalStr = input.split(" ");
		int[] start = new int[intervalStr.length];
		int i=0;
		
		PriorityQueue<Interval> pq = new PriorityQueue<Interval>();
		for(String interval: intervalStr){
			String[] points = interval.split(",");
			start[i] = Integer.parseInt(points[0]);
			Interval value = moi.new Interval(start[i],Integer.parseInt(points[1]));
			//Intervals get inserted in sorted manner by sorting the start property of interval object
			pq.add(value);
			i++;
		}
		//Not using mergesort, using heapsort with the help of priority queue
		//moi.mergeSort(start, 0, start.length-1);
		
		Stack<Interval> output =  new Stack<Interval>();
		//Priority queue is not sorted by start property but the front element in the queue is always the minimum element, which is the minimum start value
		while(pq.peek()!=null){
			Interval current = pq.poll();
			if(output.isEmpty()){
				output.push(current);
			}
			else{
				int stackTopEnd = output.peek().end;
				//If current interval's start is greater than the end of stack's top, then its not overlapping so push it
				if(current.start > stackTopEnd){
					output.push(current);
				}
				//If current interval's start is less than or equal to end of stack's top, then its overlapping, merge it so that max of
				//Current's end and stack's top's end is made the new end and replace this as the new top of the stack.
				else if(current.start <= stackTopEnd){
					Interval modifiedTop = output.pop();
					modifiedTop.end = Math.max(current.end, stackTopEnd);
					output.push(modifiedTop);
				}
			}
		}
		
		for(Interval o: output){
			System.out.println("{"+o.start + ","+o.end+"}");
		}
		
	}
	
    void  mergeSort(int[] input, int low, int high){
		
		if(low<high){
			int mid = (high+low)/2;
			mergeSort(input, low, mid);
			mergeSort(input, mid+1, high);
			merge(input, low , mid+1, high);
		}
		
		
	}
	
	
	void merge(int[] input, int leftBegin, int rightBegin, int rightEnd){
		
		int leftEnd = rightBegin-1;
		int num = rightEnd - leftBegin+1;
		int[] temp = new int[num];
		int index = 0;
		
		while(leftBegin<=leftEnd && rightBegin<=rightEnd){
			if((input[leftBegin] < input[rightBegin])){
				temp[index++] = input[leftBegin++];
			}
			else {
				temp[index++] = input[rightBegin++];
			}
		}
		
		while(leftBegin<=leftEnd){
			temp[index++] = input[leftBegin++];
		}
		
		while(rightBegin<=rightEnd){
			temp[index++] = input[rightBegin++];
		}
		
		for(int i=num-1; i>=0; i--, rightEnd--){
			input[rightEnd] = temp[i];
		}
	
	}
}
