package algocode;
import java.io.*;
import java.util.*;

/*
 *Mistakes:
 *1. Erase the current char and mark its visited false once all 8 recursive calls have finished. What this means is you did not find a word down this path
 *and you want to take one step back and try other directions without this letter. Path with the current letter and paths without the current letter.
 *
 *2. Marking return for each of the 8 search recursive calls was computing only first direction and once it returned false function returns without exploring the rest of 7 directions.
 *
 *3. If a particular stack call returned true..make all its parent stack recursive calls return true because word was found down that path.
 *So, if found a word, not only does the current call return true, but even the 8 recursive calls if true, will return true.
 */

class Boggle {
   static char boggle[][]   = {{'G','I','Z'},
                       {'U','E','K'},
                       {'Q','S','E'}};
    static boolean [][] visited =  new boolean[boggle.length][boggle.length];
  public static void main(String[] args) {
    String dictionary[] = {"GEEKS", "FOR", "QUIZ", "GO", "GIZ"};
   
    for(String s: dictionary){
      if(isWord(s)){
        System.out.println(s);
      }
      
    }

  }
 
  
  public static boolean isWord(String str){
      
      for(int i=0; i<boggle.length;i++){
        for(int j=0; j<boggle[i].length; j++){
          visited =  new boolean[boggle.length][boggle.length];
          if(!visited[i][j]){
             if(search(i,j, str, "")){
               return true;
             }
          }
          
          
        }
        
      }
        
        return false;
  }
         
         
  public static boolean search(int row, int col, String str, String strSoFar){
        visited[row][col] = true;
        strSoFar += boggle[row][col];
   // System.out.println("String so far "+strSoFar);
     //System.out.println("Actual string "+str);
        if(strSoFar.equals(str)){
          System.out.println("hit");
          return true;
        }
        
        if((row+1)<boggle.length && (row+1)>=0 && (col +1)< boggle.length && (col+1) >=0){
           if(!visited[row+1][col+1]){
               if(search(row+1, col+1, str, strSoFar)){
                 return true;
               }
           }
         }
        if((row+1)<boggle.length && (row+1)>=0 && col < boggle.length && col >=0){
          if(!visited[row+1][col]){
             if(search(row+1, col, str, strSoFar)){
               return true;
             }
          }
         }
        
       if((row-1)<boggle.length && (row-1)>=0 && col < boggle.length && col >=0){
         if(!visited[row-1][col]){
              if(search(row-1, col, str, strSoFar)){
                return true;
              }
         }
       }
         
         if(  (row-1)<boggle.length && (row-1)>=0 && (col -1)< boggle.length && (col-1) >=0){
           if(!visited[row-1][col-1]){
             if(search(row-1, col-1, str, strSoFar)){
               return true;
             }
           }
         }
        if((row)<boggle.length && (row)>=0 && (col+1)< boggle.length && (col+1) >=0){
          if(!visited[row][col+1]){
           if(search(row, col+1, str, strSoFar)){
             return true;
           }
          }
        }
        if( (row)<boggle.length && (row)>=0 && (col -1)< boggle.length && (col-1) >=0){
          if(!visited[row][col-1]){
            if(search(row, col-1, str, strSoFar))
            {
              return true;
            }
          }
        }
        if( (row+1)<boggle.length && (row+1)>=0 && (col -1)< boggle.length && (col-1) >=0){
          if(!visited[row+1][col-1]){
             if(search(row+1, col-1, str, strSoFar)){
               return true;
             }
          }
        }
        if( (row-1)<boggle.length && (row-1)>=0 && (col +1)< boggle.length && (col+1) >=0){
          if(!visited[row-1][col+1]){
           if( search(row-1, col+1, str, strSoFar)){
             return true;
             
           }
          }
        }
      
    strSoFar= strSoFar.substring(0, strSoFar.length()-1);
    visited[row][col] = false;
        
        return false;
  }
  
}

