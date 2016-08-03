package algocode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.LinkedList;
//import java.util.Queue;

public class List {
	public static void main(String[] args) throws Exception {
		List list = new List();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		Node head = null;
		Node current = null;
		for (String s : input) {
			int value = Integer.parseInt(s);
			Node n = new Node(value);
			if (head == null) {
				head = n;
				current = head;
			} else {
				current.next = n;
				current = current.next;
			}

		}

		//int k = Integer.parseInt(in.readLine());

		System.out.print("Given List is ");
		System.out.println(list.toString(head));

		Node rev = list.reverse(head);
		System.out.println("Reverse: " + list.toString(rev));

		//Node revk = list.reverseByK(head, k);
		//System.out.println("Reverse by k: " + list.toString(revk));
		
		//Node rotk = list.rotateByK(head, k);
		//System.out.println("Rotate by k: " + list.toString(rotk));
		
		//Node segeo = list.segEvenOdd(head);
		//System.out.println("Seg even and odd: " + list.toString(segeo));

	}
	
	Node segEvenOdd(Node curr){
		//Walk through entire list, store the last ptr.
		//Walk through entire list again and every time odd value is encountered, change it's previous node's next to odd node's next
		//and make last node's next to odd node. Make the odd node the new last.
		Node last = null;
		Node head = curr;
		int size = 0;
		while(curr!=null){
			last = curr;
			curr=curr.next;
			size++;
		}
		curr = head; 
		Node next = null;
		Node prev = null;
		int c = 0;
		while(curr!=null && size>0){
			//System.out.println("Evaluating node "+ curr.data);
			next = curr.next; 
			//System.out.println("Next is "+ next.data);
			if(curr.data%2!=0){
				//Odd node
				if(prev!=null)
				{
					if(head==prev){
						head.next = next;
					}
					prev.next = next;
					
				}
				//System.out.println("Odd node ");
				last.next = curr; 
				//System.out.println("next of last is "+ last.next.data);
				last = curr;
				last.next = null;
				//System.out.println("last is "+ last.data);
				curr = next; 
				//System.out.println("curr is "+ curr.data);
				
			}
			else{
				if(c==0){
					//First even node is the head
					head = curr;
					System.out.println("Even: new head "+ head.data);
				}
				c++;
				prev = curr;
				curr = next; 
				//System.out.println("Even: curr is "+ curr.data);
			}
			size --;
		}
		
		return head;
	}
	
	Node rotateByK(Node curr, int k){
		//Get hold of kth node, k+1th node and last node
		//1. Next of kth node becomes null
		//2. Next of last node becomes head
		//3. k+1th node becomes the new head
		int count=1;
		Node head = curr;
		Node kNode=null;
		Node kPlus1Node = null;
		Node last = null;
		while(curr!=null ){
			last = curr;
			curr = curr.next;
			count++;
			if(count==k){
				kNode = curr;
				kPlus1Node = curr.next;
			}
			
		}
		
		
		kNode.next = null;
		last.next = head;
		head = kPlus1Node;
		
		return head;
		
	}

	Node reverseByK(Node head, int k) {
		Node prev = null;
		Node next = null;
		Node curr = head;
		int counter = 0;
		while (counter < k && curr!=null) {
				next = curr.next;
				// This reverses the link
				curr.next = prev;
				// Move prev one to right
				prev = curr;
				// Move curr one to right
				curr = next;
				counter++;
		}
		
		//Next is now pointing to k+1th node, recursively call reverse on the next k nodes
		if(next!=null){
			head.next = reverseByK(next, k);
			 
		}
		return prev;
	
	}

	Node reverse(Node curr) {
		Node prev = null;
		Node next;
		while (curr != null) {
			next = curr.next;
			// This reverses the link
			curr.next = prev;
			// Move prev one to right
			prev = curr;
			// Move curr one to right
			curr = next;
		}

		return prev;
	}
	

	
	public String toString(Node curr) {
		String s = "";
		String append = "->";
		while (curr != null) {
			s += curr.data;
			if (curr.next != null)
				s += append;
			curr = curr.next;
			
		}

		return s;
	}

}



class Node {
	Node next;

	int data;

	Node(int data) {
		this.data = data;

	}

}
