package AdventureGame;


import java.io.File;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.Random;
import java.util.Scanner;

public class GameModel {

    Player player;
    Question currentQuestion;
    Random rand = new Random();
    Consequens[] consequences;
    String[] questions;
    Consequens chosenConsequence;
    int[] probabilities;
    public void start(){
        this.player = new Player("Andreas");

        generateQuestions("graph.txt");
        generateConsequences();
        setProbabilities(25,25,45,5);
    }

    void setProbabilities(int inc, int dec, int age, int kill) {
        probabilities = new int[]{inc, dec, age, kill};
    }

    void generateQuestions(String url){
        ArrayList<String> list = new ArrayList<>();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(url).getFile());
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()){
                list.add(scanner.nextLine());
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            for (int i = 0; i < 10; i++){
                list.add("Backup " + i);
            }
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
        int r = rand.nextInt(100);

        int i = 0;
        r-= probabilities[i];
        while (r >= 0){
            i++;
            r-= probabilities[i];
        }
        return consequences[i];
    }

    public boolean playerIsDead() {
        return this.player.getHealth() == 0;
    }

    public String getResultPrompt() {
        return chosenConsequence.getMessage();
    }

    public boolean playerHasAged() {
        return this.player.getAge() % 5 == 0;
    }
}
