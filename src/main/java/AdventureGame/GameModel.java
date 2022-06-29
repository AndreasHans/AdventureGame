package AdventureGame;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Function;

public class GameModel {
    Graph graph;
    Player player;
    Node currentNode;
    Random rand = new Random();
    ArrayList<Function> events;

    public static int nodeCount,nodeStart,winningIndex;
    public static int min = 5, max = 10;

    public void start(){
        generateRandomGraph(rand.nextInt(max-min)+min);
        generateEvents();
        generatePlayer();
        generateGraph();

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
            generateNode(i,c);
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


    private void generateEvents(){

        this.events = new ArrayList<>();

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
        this.events.add(decreaseHP);
        this.events.add(increaseAge);
        this.events.add(increaseHP);
        //this.events.add(kill);
    }

    private Function getRandomEvent(){
        int r = rand.nextInt(this.events.size());
        return this.events.get(r);
    }

    public void generateNode(int i, char c){

        if (c == 'P') nodeStart = i;
        if (c == 'G') winningIndex = i;
        String title = "title " + i;
        String message = "hit message " + i;
        String eventMessage = "eventMessage " + i;
        String hint = "hint " + i;
        String result = "result " + i;
        String typeThis = "type" +i;

        Function event = getRandomEvent();
        Node n = new Node(i,title,message,eventMessage,hint,result,typeThis,event);
        this.graph.setNode(n,i);
    }

    public void selectNode(int i) {
        this.currentNode = this.graph.getNode(i);
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
                "You " + currentNode.getResultMessage();
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

    public Node[] getPossibilities() {
        int index = this.currentNode.getIndex();
        int[] indexes = this.graph.getNeighboursOf(index);

        ArrayList<Node> list = new ArrayList<>();

        for (int i: indexes){
            Node n = this.graph.getNode(i);
            list.add(n);
        }
        return list.toArray(new Node[0]);
    }
}
