package algocode;
import java.util.*;
/*
 * O(n) time and O(1) aux space
 */
class MoveZeroesToEnd{
  public static void main(String[] args){
    int[] test1 = {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0};
    System.out.println(Arrays.toString(answer(test1)));
  }
  
  public static int[] answer(int[] input){
    int left = 0;
    int right = 0;
    
    for(int i: input){
       //System.out.println("current element "+ i);
       //System.out.println("LEFT PTR "+left);
       //System.out.println("RIGHT PTR "+right);
      if(left<right && input[right]!=0){
        int temp = input[left];
        input[left] = input[right];
        input[right] = temp;
        left++;
        right++;
       // System.out.println("sWAPPING");
       // System.out.println("AFTER SWAP LEFT PTR "+left);
       // System.out.println("AFTER SWAP RIGHT PTR "+right);
        
      }
      else if(left<right && input[right]==0){
        right++;
      }
      else if(left==right){
      if(i!=0){
        left++;
        right++;
      }
      else{
        right++;
      }
      }
    }
    
    return input;
    
  } 
    
    
}
