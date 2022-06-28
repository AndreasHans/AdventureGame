package AdventureGame;


import java.util.ArrayList;
import java.util.function.Function;
import java.util.Random;

public class GameModel {

    Player player;
    Question currentQuestion;
    Random rand = new Random();
    Consequens[] consequences;
    String[] questions;
    Consequens chosenConsequence;
    public void start() {
        this.player = new Player("Andreas");

        generateQuestions();
        generateConsequences();
    }

    private void generateQuestions() {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            list.add("Question " + i);
        }
        questions = list.toArray(new String[0]);
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

        Consequens c1 = new Consequens(increaseHP,messageIncreaseHP);
        Consequens c2 = new Consequens(decreaseHP,messageDecreaseHP);
        Consequens c3 = new Consequens(increaseAge,messageIncreaseAge);
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

        int r = rand.nextInt(questions.length);
        String q = questions[r] + " (yes/no/end)";

        Consequens a1 = getNextConsequence();
        Consequens a2 = getNextConsequence();


        currentQuestion = new Question(q,a1,a2);
        return currentQuestion;
    }

    public Consequens getNextConsequence(){
        int[] p = {25,25,45,5}; //inc, dec, age, kill
        int r = rand.nextInt(100);
        System.out.println(r);

        int i = 0;
        r-= p[i];
        while (r >= 0){
            i++;
            r-= p[i];
        }
        System.out.println(i);
        return consequences[i];
    }

    public boolean playerIsDead() {
        return this.player.getHealth() == 0;
    }

    public String getResultPrompt() {
        return chosenConsequence.getMessage();
    }
}
