package algocode;
import java.util.*;
//import java.io.*;
public class TopViewBT {
	
	 class Node {
	       int data;
	       Node left;
	       Node right;
	       Node(int data){
	    	   this.data = data;
	       }
	       
	       Node(){
	    	   
	       }
	 }
	 
	 //Simple traversing of left and then right using extra list to keep the order
	 void top_view(Node root)
	 {
	     ArrayList<Integer> tv1 = new ArrayList<Integer>();
	     Node n1 = new Node();
	     n1 = root;
	     while(n1!= null)
	         {
	             tv1.add(n1.data);
	              n1 = n1.left;
	         }
	     n1 = root;
	     Collections.reverse(tv1);
	     while(n1.right!= null)
	         {
	              n1 = n1.right;
	             tv1.add(n1.data);
	         }
	     for(int i=0;i<tv1.size();i++)
	         {
	     System.out.print(tv1.get(i)+" ");
	     }
	 }
	
	 
	 //Recursive solution of traversing left and then right
	 void top_viewRecur(Node root)
	 {

	     if(root != null) {

	         top_view(root.left, true);

	         System.out.print(root.data + " ");

	         top_view(root.right, false);

	     }

	 }

	 void top_view(Node node, boolean goLeft) {

	     if(node != null) {
	         if(goLeft) {
	             top_view(node.left, goLeft);
	             System.out.print(node.data + " ");
	         } else {
	             System.out.print(node.data + " ");
	             top_view(node.right, goLeft);
	         }
	     } 

	 }

	   void LevelOrder(Node root)
	    {
	      if(root == null) return; 
	      Queue<Node> q =  new LinkedList<Node>();
	      q.add(root);
	      while(!q.isEmpty()) {
	          Node focus =  q.remove();
	          System.out.print(focus.data+" ");
	          if(focus.left !=null)
	            q.add(focus.left);
	          if(focus.right != null)
	            q.add(focus.right);
	      }
	      
	    }
	   
	   public static void main(String[] args) throws Exception{
		   TopViewBT tv = new TopViewBT();
		   Node root = tv.new Node(3);
		   root.left = tv.new Node(5);
		   root.right = tv.new Node(2);
		   root.left.left = tv.new Node(1);
		   root.left.right = tv.new Node(4);
		   root.right.left = tv.new Node(6);
		   
		   tv.LevelOrder(root);
		   
		   
	   }

}
