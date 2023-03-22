
import java.util.*;
import java.io.*;
import java.lang.*;

public class Main{
	static BufferedReader br;
	static PrintWriter ot;
    public static void main(String args[]) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		ot = new PrintWriter(System.out);
		int t = Integer.parseInt(br.readLine().trim());
		while(t-- > 0){
			String s[] = br.readLine().trim().split(" ");
			int V = Integer.parseInt(s[0]);
			int E = Integer.parseInt(s[1]);
			int edges[][] = new int[E][3];
			for(int i = 0; i < E; i++){
				s = br.readLine().trim().split(" ");
				edges[i][0] = Integer.parseInt(s[0]);
				edges[i][1] = Integer.parseInt(s[1]);
				edges[i][2] = Integer.parseInt(s[2]);
			}
			ot.println(new Solution().spanningTree(V, E, edges));
		}
		ot.close();
	}
}

class Solution{
    // By Using Kruskal's Algorithm (Union Find by Rank and Path Comperssion)
   // TC :->  O(E(LogE+v)) -> O(ElogV)
// SC :-> O(E+V)
    public static int findParent(int x,int[] parent){
	    int tmp = x;
	    while(parent[x]!=x){
	        x = parent[x];
	    }
	    parent[tmp] = x; //Path Compression
	    return x;
	}
	public static int spanningTree(int V, int E, int edges[][]){
	     int minCost = 0;
	   int[] parent = new int[V+1];
	   int[] rank = new int[V+1];
	   for(int i = 0;i<=V;i++){
	       parent[i] = i;
	   }
	   PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[2]-b[2]);
	   for(int i[] : edges){
	       pq.offer(i);
	   }
	   while(!pq.isEmpty()){
	       int[] curr = pq.poll();
	       int p1 = findParent(curr[0],parent);
	       int p2 = findParent(curr[1],parent);
	       if(p1!=p2){
	           if(rank[p1]<rank[p2]){
	               parent[p1] = p2;
	           }else if(rank[p1]>rank[p2]){
	               parent[p2] = p1;
	           }else{
	               parent[p1] = p2;
	               rank[p1]+=1;
	           }
	           minCost += curr[2]; 
	       }
	   }
	   return minCost;
	}
	
	
}