package algocode;
import java.io.*;
import java.util.*;
/*
 * Problem Code: JUMP
 */
public class JUMP{
	static final int MAXN = 3*10^5+1;
	static final double INFINITY = Double.POSITIVE_INFINITY;
	static int n = MAXN;
	static long[] P = new long[MAXN];
	static long[] A = new long[MAXN];
	static long[] H = new long[MAXN];
	static boolean[] visited =  new boolean[MAXN];
	static double[] tentDistance = new double[MAXN];
	static HashMap<Long, ArrayList<Long>> tm = new HashMap<Long, ArrayList<Long>>();
	static HashMap<Integer, long[]> mapPandH = new HashMap<Integer, long[]>();
	static HashMap<Long, Integer> mapPandI = new HashMap<Long, Integer>();
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		P = new long[n];
		A = new long[n];
		H = new long[n];
		tentDistance = new double[n];
		visited = new boolean[n];
		String[] pValues = in.readLine().split(" ");
		String[] aValues = in.readLine().split(" ");
		String[] hValues = in.readLine().split(" ");
		for(int i=0; i<n; i++){
			P[i] = Integer.parseInt(pValues[i]);
			A[i] = Integer.parseInt(aValues[i]);
			H[i] = Integer.parseInt(hValues[i]);
			visited[i] = false;
			tentDistance[i]= INFINITY;
			mapPandH.put(i, new long[]{H[i], A[i], P[i]});
			mapPandI.put(P[i], i);
		}
		makeTreeMap();
		
		System.out.println((long)findShortestPathCost(0,n-1));
		
	}
	
	static void makeTreeMap(){
		long key= 0;
		for(long p: P){
			//For every P value, check if its less than existing keys, and also add it as a new key.
			if(!tm.containsKey(p)){
				ArrayList<Long> values = new ArrayList<Long>();
				tm.put(p, values);
				for(Map.Entry<Long,ArrayList<Long>> entry : tm.entrySet()){
					key =entry.getKey();
					if(p> key){
						//If current P is less than existing key, add it to the list of values of the current existing key
						values = entry.getValue();
						values.add(p);
						tm.put(key, values);
					}
				}
			}
		}
		
	}
	
	static double findShortestPathCost(int source, int dest){
		long fromH = 0;
		long toH = 0;
		long a1 = 0;
		long a2 = 0; 
		long currentP = 0;
		int i2 = 0;
		PriorityQueue<GraphNode1> pq =new PriorityQueue<GraphNode1>(); 
		long[] mappedVals = new long[n];
		if (!visited[source]) {
			tentDistance[source] = 0;
			pq.add(new GraphNode1(source, 0));
		}
		
		while (pq.peek() != null) {
			GraphNode1 gn = pq.poll();
			int currentNode = gn.indexOfNode;
			visited[currentNode] = true;
			mappedVals = mapPandH.get(currentNode);
			fromH = mappedVals[0];//get corresponding H from index
			a1 = mappedVals[1];//get corresponding A from index
			currentP = mappedVals[2];//get corresponding P from index
			// System.out.println("current node is " + currentNode);
			// System.out.println("After removing size of pq "+pq.size());
			ArrayList<Long> neighbours = tm.get(currentP);
			// System.out.println("neighbours of " + currentNode + " are " +
			// neighbours);
			
			if (neighbours.size() > 0) {
				for (Long neighbour : neighbours) {
					i2 = mapPandI.get(neighbour);
					mappedVals = mapPandH.get(i2);
					toH = mappedVals[0];//get corresponding H from P
					a2 = mappedVals[1];//get corresponding A from P
					long compare = (long) (tentDistance[currentNode]+Math.pow((toH-fromH), 2) + a1 + a2);
					//System.out.println("neighbour "+neighbour+" tent(curr) "+ tentDistance[currentNode]+ " toH "+toH+ " fromH "+fromH+ " cook(curr) "+a1+ " cook(neigh) "+a2+ " tent(neigh) "+ tentDistance[i2] + " compare "+compare);
					if (compare < tentDistance[i2]) {
						tentDistance[i2] = compare;
						// printMatrix(distance);
						if (!visited[i2]) {
							// System.out.println("adding to pq: " + neighbour);
							pq.add(new GraphNode1(i2, tentDistance[i2]));
						}
					}
 
				}
			}
		}
		return tentDistance[dest];
	}
	
	
	static class GraphNode1 implements Comparable<GraphNode1> {
		double tentDistance;
		int indexOfNode;
 
		GraphNode1(int indexOfNode, double tentDistance) {
			this.indexOfNode = indexOfNode;
			this.tentDistance = tentDistance;
			
		}
 
		public int compareTo(GraphNode1 gn) {
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
 