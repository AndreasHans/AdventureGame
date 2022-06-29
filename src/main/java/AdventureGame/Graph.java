package AdventureGame;

import java.util.ArrayList;

public class Graph {
    private Node[] nodes;
    private ArrayList<Integer>[] adj;

    public Graph(int amount){
        this.nodes = new Node[amount];
        this.adj = new ArrayList[amount];

        for (int i = 0; i < amount; i++) {
            this.nodes[i] = new Node();
            this.adj[i] = new ArrayList<>();
        }
    }

    public void addEdgeSingle(int u, int v){
        this.adj[u].add(v);
    }
    public void setNode(Node node, int index){
        this.nodes[index] = node;
    }

    public Node getNode(int index){
        return this.nodes[index];
    }

    public int[] getNeighboursOf(int index){
        return this.adj[index].stream().mapToInt(i -> i).toArray();
    }

    public void printGraph(){
        for (int i = 0; i < this.adj.length; i++){
            System.out.println(this.adj[i]);
        }
    }
}
