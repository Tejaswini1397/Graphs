import java.util.*;
import java.io.*;
import java.lang.*;
class Solution{
static class Pair{
    int node;
    int weight;
    public Pair(int node, int weight){
        this.node=node;
        this.weight=weight;
    }
}

int spanningTree(int V, int E, int edges[][]){
List<List<Pair>>graph=new ArrayList<>();
for(int i=0;i<V;i++){
    graph.add(new ArrayList<Pair>());
}
for(int []edge:edges){
    graph.get(edge[0]).add(new Pair(edge[1],edge[2]));
    graph.get(edge[1]).add(new Pair(edge[0],edge[2]));
}
PriorityQueue<Pair>que=new PriorityQueue<>((a,b)->{
    if(a.weight==b.weight){
        return a.node-b.node;
    }
        return a.weight-b.weight;
    
});
boolean visited[]=new boolean[V];
que.add(new Pair(0,0));
int sum=0;
while(que.size()>0){
    Pair cp=que.remove();
    int node=cp.node;
    int wt=cp.weight;
    if(visited[node])continue;
    sum+=wt;
    visited[node]=true;
    for(Pair p:graph.get(node)){
        int nbr=p.node;
        int cost=p.weight;
        if(!visited[nbr]){
            que.add(new Pair(nbr,cost));
        }
    }
}
return sum;
}
}



   