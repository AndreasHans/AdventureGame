package AdventureGame;

import java.util.Scanner;

public class GameView {

    Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("The game is starting!");
    }

    public void end() {
        System.out.println("Game Over!");
    }

    public void showNodeMessage(Node node){
        System.out.println("currently at node " + node.getTitle());
        System.out.println("The message is " + node.getMessage());
    }

    public void showPossibilities(int[] pos) {

        System.out.println("You have the following options:");

        for (int e: pos){
            System.out.println("go to " + e + " (type " + e + ")" );
        }
    }

    public int getValidUserInput(int[] pos) {
        int val = -2;

        Boolean isValid = false;
        while (!isValid) {

            if (val == -1) break;

            if (!input.hasNextInt()){
                input.nextLine();
            } else {
                val = input.nextInt();
            }

            isValid = isNeighbour(pos,val);
        }

        return val;
    }

    private boolean isNeighbour(int[] pos, int val) {
        for (int e: pos){
            if (e == val) return true;
        }
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
}
