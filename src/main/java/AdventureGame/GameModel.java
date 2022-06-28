package AdventureGame;


import java.util.ArrayList;
import java.util.function.Function;
import java.util.Random;

public class GameModel {

    Graph graph;
    Player player;
    Node currentNode;
    Random rand = new Random();
    Consequence[] positive;
    Consequence[] negative;
    Consequence currentConsequence;

    public static int nodeCount = 3;
    public static int nodeStart = 0;

    public static int percentOfGoodChange = 95;



    public void start(){
        this.player = new Player("Andreas");

        this.graph = new Graph(nodeCount);
        generateNodeRelations();
        generateNodeContents();
        generateModifications();
        this.currentNode = this.graph.getNode(nodeStart);
    }

    private void generateModifications() {
        Function<Player,Player> decreaseHP = (p) -> {
            p.decreaseHealth();
            return null;
        };

        Function<Player,Player> increaseHP = (p) -> {
            p.increaseHealth();
            return null;
        };

        Function<Player,Player> increaseAge = (p) -> {
            p.increaseAge();
            return null;
        };

        Function<Player,Player> kill = (p) -> {
            p.killPlayer();
            return null;
        };

        String messageDecreaseHP = "you lost a HP!";
        String messageIncreaseHP = "you gained a HP!";
        String messageIncreaseAge = "you survived another year!";
        String messageKill = "you died!";

        Consequence c1 = new Consequence(increaseHP,messageIncreaseHP);
        Consequence c2 = new Consequence(decreaseHP,messageDecreaseHP);
        Consequence c3 = new Consequence(increaseAge,messageIncreaseAge);
        Consequence c4 = new Consequence(kill,messageKill);

        ArrayList<Consequence> pos = new ArrayList<>();
        ArrayList<Consequence> neg = new ArrayList<>();

        pos.add(c1);
        neg.add(c2);
        pos.add(c3);
        neg.add(c4);

        this.positive = pos.toArray(new Consequence[0]);
        this.negative = neg.toArray(new Consequence[0]);
    }

    public void generateNodeRelations(){
        this.graph.addEdge(0,1);
        this.graph.addEdge(0,2);
        //this.graph.addEdge(1,2);
        this.graph.addEdge(1,0);
        //this.graph.addEdge(2,1);
        this.graph.addEdge(2,0);
        //this.graph.printGraph();
    }

    public void generateNodeContents(){
        for (int i = 0; i < nodeCount; i++){
            Node n = new Node(i,"title " + i,"message " + i);
            this.graph.setNode(n,i);
        }
    }

    public void selectNode(int i) {
        this.currentNode = this.graph.getNode(i);
    }

    public Node getCurrentNode(){
        return this.currentNode;
    }

    public int[] getPossibilities() {
        int index = this.currentNode.getIndex();
        return this.graph.getNeighboursOf(index);
    }

    public void doNodeEvent() {
        int val = rand.nextInt(100);
        if (val < percentOfGoodChange) doPositiveChange();
        if (val > percentOfGoodChange) doNegativeChange();
    }

    private void doNegativeChange() {
        int r = rand.nextInt(negative.length);
        this.currentConsequence = negative[r];
        this.currentConsequence.getFunction().apply(this.player);
    }

    private void doPositiveChange() {
        int r = rand.nextInt(positive.length);
        this.currentConsequence = positive[r];
        this.currentConsequence.getFunction().apply(this.player);
    }

    public String getStatus() {
        return this.player.toString();
    }

    public String getResultMessage() {

        String message =
                "You went to node " + currentNode.getIndex() + "\n" +
                "and " + this.currentConsequence.getMessage();
        return message;
    }

    public void end() {

    }

    public boolean playerIsDead() {
        return this.player.getHealth() == 0;
    }

}
