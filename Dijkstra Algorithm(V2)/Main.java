//Time compexity O(V2) and Space compexity O(V)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static int minDistance(int dist[], boolean visited[],int V){
        int min=Integer.MAX_VALUE;
        int min_idx=0;
        for(int i=0;i<V;i++){
            if(visited[i]==false && dist[i]<=min){
                min=dist[i];
                min_idx=i;
            }
        }
        return min_idx;

    }
    public static int[]dijkstra(int V,ArrayList<ArrayList<ArrayList<Integer>>>adj, int S){
        int [][]adj_mat=new int[V][V];
        for(int i=0;i<V;i++){
            for(int j=0;j<adj.get(i).size();j++){
                adj_mat[i][adj.get(i).get(j).get(0)]=adj.get(i).get(j).get(1);
            }
        }
        int dist[]=new int[V];
        boolean []visited=new boolean [V];
        for(int i=0;i<V;i++){
            dist[i]=Integer.MAX_VALUE;
            visited[i]=false;
        }
        dist[S]=0;
        for(int count=0;count<V-1;count++){
            int u=minDistance(dist,visited,V);
            visited[u]=true;
            for(int i=0;i<V;i++){
                if(!visited[i] && adj_mat[u][i]!=0 && dist[u]!=Integer.MAX_VALUE && dist[u]+adj_mat[u][i]<dist[i] )
                    dist[i]=dist[u]+adj_mat[u][i];
                
            }
        }
        return dist;
    }
    public static void main(String[] args) throws IOException {
        
        BufferedReader read =
        new BufferedReader(new InputStreamReader(System.in));
        String str[] = read.readLine().trim().split(" ");
        int V = Integer.parseInt(str[0]);
        int E = Integer.parseInt(str[1]);

        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
        for(int i=0;i<V;i++)
        {
            adj.add(new ArrayList<ArrayList<Integer>>());
        }
        
        int i=0;
        while (i++<E) {
            String S[] = read.readLine().trim().split(" ");
            int u = Integer.parseInt(S[0]);
            int v = Integer.parseInt(S[1]);
            int w = Integer.parseInt(S[2]);
            ArrayList<Integer> t1 = new ArrayList<Integer>();
            ArrayList<Integer> t2 = new ArrayList<Integer>();
            t1.add(v);
            t1.add(w);
            t2.add(u);
            t2.add(w);
            adj.get(u).add(t1);
            adj.get(v).add(t2);
        }
        
        int S = Integer.parseInt(read.readLine());
        
        int[] ptr = dijkstra(V, adj, S);
        
        for(i=0; i<V; i++)
            System.out.print(ptr[i] + " ");
        System.out.println();
    }

}


