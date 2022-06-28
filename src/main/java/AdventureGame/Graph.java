package AdventureGame;

import java.util.ArrayList;

public class Graph {
    private int vertices;
    private ArrayList<Integer>[] adj;

    public Graph(int a){
        this.vertices = a;
        this.adj = new ArrayList[a];
    }

    public void addEdge(int u, int v){
        this.adj[u].add(v);
    }

    public void printGraph(){
        for (int i = 0; i < this.adj.length; i++){
            System.out.println(this.adj[i]);
        }
    }
}
