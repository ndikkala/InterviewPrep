package algocode;
import java.io.*;
import java.util.*;


public class TestDelete {
    
    // There is only output for all test cases, so make it static
	static ArrayList<Integer> output;

	// There is one input for each test case, make it global but not static.
	// This array will be re-initialized for every test case
	int[][] m;

	boolean[] visited;

	int[][] distance;
    int noOfVertices;



   public static void main(String[] args) throws Exception {
		
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			int t = Integer.parseInt(in.readLine());
			if (t > 10 || t < 1)
				return;
			for (int i = 0; i < t; i++) {
				TestDelete sp = new TestDelete();
				String[] sizes = in.readLine().split(" ");
                sp.noOfVertices = Integer.parseInt(sizes[0]);
                int noOfEdges = Integer.parseInt(sizes[1]);
				sp.initializeAdjMatrix(sp.noOfVertices);
				for (int n = 0; n < noOfEdges; n++) {
					String[] integers = in.readLine().split(" ");
					int row = Integer.parseInt(integers[0]);
					int col = Integer.parseInt(integers[1]);
                    int wt = Integer.parseInt(integers[2]);
					sp.m[row-1][col-1] = wt;
					sp.m[col-1][row-1] = wt;
				}
				/*
				 * 
				
				System.out.println("Matrix initialized");
				for(int row=0; row<sp.m.length; row++){
					for(int col=0; col<sp.m[0].length; col++){
						System.out.print(sp.m[row][col]+ " ");
					}
					System.out.println("");
				}
				*/
				 
				int source = Integer.parseInt(in.readLine());
				sp.initializeVisited(sp.noOfVertices);
                for(int v=0; v<sp.noOfVertices; v++){
                    if(v!=source-1){
                    	sp.findShortestPathCost(source-1, v);
                    }
                    
                }
			}
			System.out.println("final output size : " + output.size());

			for (int i : output) {
				System.out.print(i+" ");
			}
	}
    
    void initializeAdjMatrix(int num) {
		m = new int[num][num];
		visited = new boolean[num];
		distance = new int[num][num];
		output = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			visited[i] = false;
			for (int j = 0; j < num; j++) {
				m[i][j] = -1;
				if (i == j) {
					distance[i][j] = 0;
				} else {
					distance[i][j] =Integer.MAX_VALUE;
				}
			}
		}
	}
    
    
    
	void initializeVisited(int num) {
		visited = new boolean[num];
		for (int i = 0; i < num; i++) {
			visited[i] = false;
		}
	}
    
    void findShortestPathCost(int source, int dest) {
		//printMatrix(distance);
		PriorityQueue<GraphNode> pq = new PriorityQueue<GraphNode>();
		if (!visited[source]) {
			pq.add(new GraphNode(source, distance[source][source]));
		}
		while (pq.peek() != null) {
			GraphNode gn = pq.poll();
			int currentNode = gn.indexOfNode;
			visited[currentNode] = true;
			//System.out.println("current node is " + currentNode);
			//System.out.println("After removing size of pq "+pq.size());
			ArrayList<Integer> neighbours = getNeighbours(currentNode);
			//System.out.println("neighbours of " + currentNode + " are " +
			//neighbours);
			if (neighbours.size() > 0) {
				for (Integer neighbour : neighbours) {

					int compare = distance[source][currentNode]
							+ m[currentNode][neighbour];

					if (compare < distance[source][neighbour]) {
						distance[source][neighbour] = compare;
						// printMatrix(distance);
						if (!visited[neighbour]) {
							// System.out.println("adding to pq: " + neighbour);
							pq.add(new GraphNode(neighbour,
									distance[currentNode][neighbour]));
						}
					}

				}
			}
		}
		int leastCostToDestination = distance[source][dest];
		if(leastCostToDestination == Integer.MAX_VALUE)
			leastCostToDestination = -1;
		System.out.println("Least cost from source to dest : " + leastCostToDestination);
		System.out.println("output size so far : " + output.size());
		output.add(leastCostToDestination);
	}
    
    
    ArrayList<Integer> getNeighbours(int node) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int col = 0; col < noOfVertices; col++) {
			if (m[node][col] > 0 && !visited[col]) {
				list.add(col);
			}
		}

		return list;
	}

	class GraphNode implements Comparable<GraphNode> {
		int tentDistance;

		int indexOfNode;

		GraphNode(int indexOfNode, int tentDistance) {
			this.indexOfNode = indexOfNode;
			this.tentDistance = tentDistance;
		}

		public int compareTo(GraphNode gn) {
			if (this.tentDistance < gn.tentDistance) {
				return -1;
			}
			if (this.tentDistance > gn.tentDistance) {
				return 1;
			}
			return 0;
		}

	}
	
	void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

    
}