package AdventureGame;


import java.util.ArrayList;

public class GameModel {
    Player player;
    Node currentNode;
    Terrain terrain;
    private int size = 5;

    public void start(){
        terrain = new Terrain();
        terrain.generateRandom(size);
        generatePlayer();
        this.currentNode = this.terrain.getGraph().getNode(terrain.getStartIndex());
    }
    private void generatePlayer() {
        this.player = new Player("Andreas");
    }

    public void selectNode(int i) {
        this.currentNode = this.terrain.graph.getNode(i);
    }

    public Node getCurrentNode(){
        return this.currentNode;
    }

    public void doNodeEvent() {
        this.currentNode.getEvent().apply(this.player);
    }

    public String getStatus() {
        return this.player.toString();
    }

    public String getEventMessage() {
        String message =
                "You went to " + currentNode.getHint() + "\n" +
                "and " + currentNode.getEventMessage() + ".\n" +
                currentNode.getResultMessage();
        return message;
    }

    public boolean playerLost() {
        return this.player.getHealth() == 0;
    }

    public boolean playerWon(){
        return  this.terrain.isAtGoal(this.currentNode.getIndex());
    }

    public boolean isGameOver() {
        return playerLost() || playerWon();
    }

    public Node[] getPossibilities() {
        int index = this.currentNode.getIndex();
        int[] indexes = this.terrain.getGraph().getNeighboursOf(index);

        ArrayList<Node> list = new ArrayList<>();

        for (int i: indexes){
            Node n = this.terrain.getGraph().getNode(i);
            list.add(n);
        }
        return list.toArray(new Node[0]);
    }
}
