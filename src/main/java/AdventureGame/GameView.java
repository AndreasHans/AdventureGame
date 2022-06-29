package AdventureGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameView {

    Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("The game is starting!");
    }

    public void showHitMessage(Node node){
        System.out.println("You are at " + node.getTitle());
        System.out.println(node.getHitMessage());
    }

    public int getValidUserInput(Node[] pos) {
        String inp = "";
        int index;
        ArrayList<String> validOptions = new ArrayList<>();

        Map<String,Integer> inpToIndex = new HashMap<>();

        for (Node n: pos){
            validOptions.add(n.getTypeThis());
            inpToIndex.put(n.getTypeThis(),n.getIndex());
        }

        Boolean isValid = false;
        while (!isValid) {
            inp = input.nextLine();
            isValid = isNeighbour(inp,validOptions);
            if (inp.equals("-1")){
                return -1;
            }
        }

        index = inpToIndex.get(inp);
        return index;
    }

    private boolean isNeighbour(String inp, ArrayList<String> list) {
        for (String e: list){
            if (e.equals(inp)) return true;
        }
        System.out.println("This is not a valid input!");
        return false;
    }

    public void showResults(String result, String status) {
        System.out.println(result);
        System.out.println(status);
        System.out.println("------------------------");
    }

    public void showWin() {
        System.out.println("YOU WIN!");
        System.out.println("You made it to the end!");
        System.out.println("Game Over");
    }
    public void showLost() {
        System.out.println("YOU LOST!");
        System.out.println("Game Over");
    }

    public void showPossibilities(Node[] possibilities) {
        System.out.println("You have the following options:");

        for (Node n: possibilities){
            System.out.println("Go to " + n.getHint() + " by typing '" + n.getTypeThis() + "'");
        }

    }
}
