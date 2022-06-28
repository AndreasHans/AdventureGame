package AdventureGame;

import java.util.Scanner;

public class GameView {

    Scanner input = new Scanner(System.in);
    public String getAnswer() {
        String line = input.nextLine();
        if (line.equals("yes") || line.equals("no") || line.equals("end")) return line;
        System.out.println("Try again! (yes/no/end)");
        getAnswer();
        return line;
    }

    public void start() {
        System.out.println("The game is starting!");
    }


    public void end() {
        System.out.println("Game Over!");
    }

    public void showQuestion(Question q) {
        System.out.println(q.getQuestion());
    }

    public void showResults(String result, String status) {
        System.out.println(result);
        System.out.println(status);
        System.out.println();
    }
}
