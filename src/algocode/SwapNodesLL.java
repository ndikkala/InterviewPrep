package algocode;

public class SwapNodesLL {
	
	public static void main(String[] args) throws Exception{
		SwapNodesLL s =  new SwapNodesLL();
		Node head =  s.new Node(1);
		Node second =  s.new Node(2);
		head.next =  second;
		Node third =  s.new Node(3);
		second.next =  third;
		Node fourth =  s.new Node(4);
		third.next =  fourth;
		Node fifth =  s.new Node(5);
		fourth.next =  fifth;
		printList(head);
		head = swapNodes(head, 2, 3);
		printList(head);
		head = swapNodes(head, 1, 2);
		printList(head);
		head = swapNodes(head, 3, 5);
		printList(head);
		head = swapNodes(head, 4, 1);
		printList(head);
		head = swapNodes(head, 1, 5);
		printList(head);
		head = swapNodes(head, 3, 2);
		printList(head);
	}
	
	public static void printList(Node head){
		Node curr=  head;
		while(curr!=null){
			if(curr.next==null){
				System.out.print(curr.data);
			}
			else{
				System.out.print(curr.data + "->");
			}
			curr =  curr.next;
		}
		System.out.println();
	}
	public static Node swapNodes(Node head, int x, int y){
		
		Node prevX = null;
		Node prevY = null;
		Node nextX = null;
		Node nextY = null;
		Node current =  head;
		Node X = null;
		Node Y = null;
		if(x==y){
			return head;
		}
		while(current!=null){
			if(current.data == x){
				X = current;
				nextX = current.next;
				break;
			}
			prevX = current;
			current =  current.next;
		}
		
		current = head;
		while(current!=null){
			if(current.data == y){
				Y =  current;
				nextY = current.next;
				break;
			}
			prevY = current;
			current =  current.next;
		}
		
		if(X==null || Y==null){
			return head;
		}
		
		if(head==X){
			//x is the head
			Y.next = nextX;
			prevY.next = X;
			X.next = nextY;
			head = Y;
			return head;
		}
		else if(head==Y){
			//y is the head
			Y.next = nextX;
			prevX.next = Y;
			X.next = nextY;
			head = X;
			return head;
		}
		
		if((nextX == null || nextY ==null)){
			//One of x or y is tail
			prevX.next = Y;
			Y.next = nextX;
			prevY.next = X;
			X.next = nextY;
			return head;
		}
		else if (nextX.data == y) {
			// x,y are adjacent and x comes before y
			prevX.next = Y;
			Y.next = X;
			X.next = nextY;
		} else if (nextY.data == x) {
			// x,y are adjacent and y comes before x
			prevY.next = X;
			X.next = Y;
			Y.next = nextX;
		}
		else{
			//x,y are not adjacent
			prevX.next = Y;
			Y.next = nextX;
			prevY.next = X;
			X.next = nextY;
		}
		
		
		return head;
	}

	
	
	class Node{
		int data;
		Node next;
		Node(int data){
			this.data =  data;
			this.next = null;
		}
	}
}
