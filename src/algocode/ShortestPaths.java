package algocode;

import java.io.*;
import java.util.*;
/*
 * Given a list of cities and their neighbors as an adjacency matrix, find shortest paths between multiple cities.
 * Computation of multiple shortest paths is needed, like between A and F, between B and C, etc.
 * So we store the distances between every two nodes as we visit and compute in a distance matrix, where distance[row][column] represents
 * shortest cost of distance between city of index 'row' and city of index 'column'.
 * We have a boolean visited 1-d array for each city
 * We use a hash map to store the city names as values with city index as the key.
 * Dijkstra's algorithm is used to find the paths. For each path asked, we check if the path distance is already updated in distance matrix,
 * so that we don't compute it again. Else, we call the method and compute. 
 * We use graph node class to represent a city's properties, which are index and tentative distance from another node, 
 * i.e., distance cell with row/column = that city's index
 */
public class ShortestPaths {
	// There is only output for all test cases, so make it static
	static ArrayList<Integer> output;

	// There is one input for each test case, make it global but not static.
	// This array will be re-initialized for every test case
	int[][] m;

	boolean[] visited;

	int[][] distance;

	int noOfCities;

	public static void main(String[] args) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			int t = Integer.parseInt(in.readLine());
			if (t > 10 || t < 1)
				return;
			for (int i = 0; i < t; i++) {
				ShortestPaths sp = new ShortestPaths();
				sp.noOfCities = Integer.parseInt(in.readLine());
				sp.initializeAdjMatrix(sp.noOfCities);
				HashMap<String, Integer> hm = new HashMap<String, Integer>();
				for (int n = 0; n < sp.noOfCities; n++) {
					String cityName = in.readLine();
					hm.put(cityName, n);
					int noOfNeighbours = Integer.parseInt(in.readLine());
					for (int nb = 0; nb < noOfNeighbours; nb++) {
						String[] integers = in.readLine().split(" ");
						int neighbourIndex = Integer.parseInt(integers[0]);
						int neighbourCost = Integer.parseInt(integers[1]);
						sp.m[n][neighbourIndex - 1] = neighbourCost;
					}
				}
				int noOfPaths = Integer.parseInt(in.readLine());
				for (int np = 0; np < noOfPaths; np++) {
					String[] cities = in.readLine().split(" ");
					int source = hm.get(cities[0]);
					int dest = hm.get(cities[1]);
					if (sp.distance[source][dest] != 200005) {
						output.add(sp.distance[source][dest]);
					} else {
						sp.initializeVisited(sp.noOfCities);
						sp.findShortestPathCost(source, dest);
					}
				}
				if (t > 1) {
					// End of test case marked by empty line
					in.readLine();
				}
			}

			for (int i : output) {
				System.out.println(i);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
				m[i][j] = 0;
				if (i == j) {
					distance[i][j] = 0;//same source and dest
				} else {
					distance[i][j] = 200005; //distance not yet updated, so assign a high number
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

	void initializeDistance(int num) {
		distance = new int[num][num];
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (i == j) {
					distance[i][j] = 0;
				} else {
					distance[i][j] = 200005;
				}
			}
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

	void findShortestPathCost(int source, int dest) {
		// printMatrix(distance);
		PriorityQueue<GraphNode> pq = new PriorityQueue<GraphNode>();
		if (!visited[source]) {
			pq.add(new GraphNode(source, distance[source][source]));
		}
		while (pq.peek() != null) {
			GraphNode gn = pq.poll();
			int currentNode = gn.indexOfNode;
			visited[currentNode] = true;
			// System.out.println("current node is " + currentNode);
			// System.out.println("After removing size of pq "+pq.size());
			ArrayList<Integer> neighbours = getNeighbours(currentNode);
			// System.out.println("neighbours of " + currentNode + " are " +
			// neighbours);
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
		// System.out.println("Least cost from source to dest : " +
		// leastCostToDestination);
		output.add(leastCostToDestination);
	}

	ArrayList<Integer> getNeighbours(int node) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int col = 0; col < noOfCities; col++) {
			if (m[node][col] > 0 && !visited[col]) {
				list.add(col);
			}
		}

		return list;
	}

	public class GraphNode implements Comparable<GraphNode> {
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

}
