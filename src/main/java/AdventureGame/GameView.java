package AdventureGame;

import java.util.Scanner;

public class GameView {

    Scanner input = new Scanner(System.in);
    public String getNextMove() {

        System.out.println("Increase age (yes) / Increase health (no) / End game (end)");
        String line = input.nextLine();
        if (line.equals("yes") || line.equals("no") || line.equals("end")) return line;
        System.out.println("Try again! (yes/no/end)");
        getNextMove();
        return line;
    }

    public void start() {
        System.out.println("The game is starting!");
    }


    public void end() {
        System.out.println("Game Over!");
    }
}
