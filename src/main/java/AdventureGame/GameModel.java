package AdventureGame;


import java.util.function.Function;
import java.util.Random;

public class GameModel {

    Player player;
    Question currentQuestion;
    Random rand = new Random();
    public void start() {
        this.player = new Player("Andreas");
    }

    public void end() {

    }
    public String getStatus() {
        return this.player.toString();
    }

    public void handleYes(Question q) {
        Function yesAnswer = q.getYesAnswer();
        yesAnswer.apply(this.player);
    }

    public void handleNo(Question q) {
        Function noAnswer = q.getNoAnswer();
        noAnswer.apply(this.player);
    }
    public Question getNextQuestion() {

        String q = "test question (yes/no/end)";

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

        Function[] ans = {
                increaseAge,increaseAge,increaseAge,
                decreaseHP,decreaseHP,decreaseHP,
                increaseHP,increaseHP,increaseHP,
                kill
        };
        int r1 = rand.nextInt(ans.length);
        int r2 = rand.nextInt(ans.length);
        Function a1 = ans[r1];
        Function a2 = ans[r2];

        currentQuestion = new Question(q,a1,a2);
        return currentQuestion;
    }

    public boolean playerIsDead() {
        return this.player.getHealth() == 0;
    }
}
