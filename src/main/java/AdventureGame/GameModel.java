package AdventureGame;


import java.util.ArrayList;
import java.util.function.Function;
import java.util.Random;

public class GameModel {

    Player player;
    Question currentQuestion;
    Random rand = new Random();
    Consequens[] consequences;
    Consequens chosenConsequence;
    public void start() {
        this.player = new Player("Andreas");

        generateConsequences();
    }

    private void generateConsequences() {
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

        String messageDecreaseHP = "You lost a HP!";
        String messageIncreaseHP = "You gained a HP!";
        String messageIncreaseAge = "You survived another year!";
        String messageKill = "You died!";

        Consequens c1 = new Consequens(decreaseHP,messageDecreaseHP);
        Consequens c2 = new Consequens(increaseAge,messageIncreaseAge);
        Consequens c3 = new Consequens(increaseHP,messageIncreaseHP);
        Consequens c4 = new Consequens(kill,messageKill);

        ArrayList<Consequens> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);

        this.consequences =  list.toArray(new Consequens[0]);
    }


    public void end() {

    }
    public String getStatus() {
        return this.player.toString();
    }

    public void handleYes(Question q) {
        chosenConsequence = q.getYesAnswer();
        Function answer = chosenConsequence.getFunction();
        answer.apply(this.player);
    }

    public void handleNo(Question q) {
        chosenConsequence = q.getNoAnswer();
        Function answer = chosenConsequence.getFunction();
        answer.apply(this.player);
    }
    public Question getNextQuestion() {

        String q = "test question (yes/no/end)";

        int r1 = rand.nextInt(consequences.length);
        int r2 = rand.nextInt(consequences.length);
        Consequens a1 = consequences[r1];
        Consequens a2 = consequences[r2];

        currentQuestion = new Question(q,a1,a2);
        return currentQuestion;
    }

    public boolean playerIsDead() {
        return this.player.getHealth() == 0;
    }

    public String getResultPromt() {
        return chosenConsequence.getMessage();
    }
}
