package AdventureGame;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;
import java.util.Random;

public class GameModel {

    Graph graph;
    Player player;
    Node currentNode;
    Random rand = new Random();
    Consequence[] positive,negative;
    Consequence currentConsequence;

    public static int nodeCount;
    public static int nodeStart;
    public static int winningIndex;

    public static int percentOfGoodChange = 95;

    public static int min = 5;
    public static int max = 10;

    public void start(){
        generateRandomGraph(rand.nextInt(max-min)+min);
        generatePlayer();
        generateGraph();
        generateNodeContents();
        generateModifications();
        this.currentNode = this.graph.getNode(nodeStart);
    }

    private void generateRandomGraph(int size){

        char[] liste = new char[size*size];

        for (int i = 0; i < size*size; i++){
            liste[i] = getaChar();
        }

        int p = rand.nextInt(size*size);
        int g = rand.nextInt(size*size);
        while(g == p){
            g = rand.nextInt(size*size);
        }
        liste[p] = 'P';
        liste[g] = 'G';

        String[] list = new String[size];

        for (int i = 0; i < list.length; i++){
            list[i] = "";
        }

        for (int i = 0; i < liste.length; i++){
            list[i % size] += liste[i];
        }

        writeListToFile(list);
    }

    private char getaChar() {
        int r = rand.nextInt(100);

        if (r > 25) return '#';
        return '@';
    }


    private void writeListToFile(String[] list) {
        try{
            PrintWriter writer = new PrintWriter("C:\\Users\\andre\\repos\\AdventureGame\\src\\main\\resources\\game.txt", "UTF-8");

            for (String line: list){
                writer.println(line);
            }
            writer.close();
        } catch (Exception e){
            System.out.println("Error while generating a random graph");
        }
    }

    private void generatePlayer() {
        this.player = new Player("Andreas");
    }

    private void generateGraph() {
        ArrayList<Character> list = getCharList();
        nodeCount = list.size();
        this.graph = new Graph(nodeCount);
        int i = 0;
        for (char c: list){
            if (c == 'P') nodeStart = i;
            if (c == 'G') winningIndex = i;
            generateNodeRelation(i);
            i++;
        }
    }

    private void generateNodeRelation(int i) {
        int sq = (int) Math.sqrt(nodeCount);
        if (i >= sq ) this.graph.addEdgeSingle(i,i-sq); //up
        if (i < sq*(sq-1)) this.graph.addEdgeSingle(i,i+sq); //down
        if ((i+1) % sq != 0 ) this.graph.addEdgeSingle(i,i+1); //right
        if (i % sq != 0 ) this.graph.addEdgeSingle(i,i-1); //left
    }

    private ArrayList<Character> getCharList() {
        ArrayList<Character> list = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\andre\\repos\\AdventureGame\\src\\main\\resources\\game.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()){
                String line = scanner.next();
                char[] chars = line.toCharArray();
                for (char c: chars){
                    list.add(c);
                }
            }
        } catch (Exception e){
        }
        return list;
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

    public boolean playerLost() {
        return this.player.getHealth() == 0;
    }

    public boolean playerWon(){
        return this.currentNode.getIndex() == winningIndex;
    }

    public boolean isGameOver() {
        return playerLost() || playerWon();
    }
}
