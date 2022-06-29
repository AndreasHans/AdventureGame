package AdventureGame;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Terrain {
    Random rand = new Random();
    Graph graph;
    private static int nodeCount, nodeStart, winningIndex;
    NodeTypes nodeTypes;
    public Terrain(){
        nodeTypes = new NodeTypes();
    }

    private char getaChar() {
        int p = 100;
        int safe = 50;
        int event = 25;
        int trap = 10;
        int good = 15;

        int r = rand.nextInt(100);
        if (r > p - safe) return '#';
        if (r > p - safe - event) return '@';
        if (r > p - safe - event - trap) return 'D';
        if (r > p - safe - event - trap - good) return 'B';
        return '?';
    }

    public void generateNode(int i, char c){
        Node n;
        switch (c){
            case 'P':
                nodeStart = i;
                n = nodeTypes.createStartNode(i);
                break;
            case 'G':
                winningIndex = i;
                n = nodeTypes.createGoalNode(i);
                break;
            case '#':
                n = nodeTypes.createSafeNode(i);
                break;
            case 'D':
                n = nodeTypes.createTrapNode(i);
                break;
            case 'B':
                n = nodeTypes.createGoodNode(i);
                break;
            default:
                n = nodeTypes.createEventNode(i);
                break;
        }
        this.graph.setNode(n,i);
    }

    public void generateRandom(int size) {
        this.graph = new Graph(size*size);
        nodeCount = size*size;
        generateRandomGraph(size);
        generateGraph();
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

    private void writeListToFile(String[] list) {
        try{
            PrintWriter writer = new PrintWriter("C:\\Users\\andre\\repos\\AdventureGame\\src\\main\\resources\\game.txt", "UTF-8");

            for (String line: list){
                writer.println(line);
            }
            writer.close();
        } catch (Exception e){
            System.out.println("Error while generating random terrain");
        }
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

    public Graph getGraph(){
        return this.graph;
    }

    private void generateNodeRelation(int i) {
        int sq = (int) Math.sqrt(nodeCount);
        if (i >= sq ) this.graph.addEdgeSingle(i,i-sq); //up
        if (i < sq*(sq-1)) this.graph.addEdgeSingle(i,i+sq); //down
        if ((i+1) % sq != 0 ) this.graph.addEdgeSingle(i,i+1); //right
        if (i % sq != 0 ) this.graph.addEdgeSingle(i,i-1); //left
    }

    public boolean isAtGoal(int p){
        return p == this.winningIndex;
    }

    public int getStartIndex(){
        return this.nodeStart;
    }
}
