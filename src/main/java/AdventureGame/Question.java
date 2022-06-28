package AdventureGame;


import java.util.function.Function;

public class Question {

    private String question;
    private Consequens yesAnswer;
    private Consequens noAnswer;

    public Question(String q, Consequens a1,Consequens a2){
        this.question = q;
        this.yesAnswer = a1;
        this.noAnswer = a2;
    }

    public String getQuestion() {
        return this.question;
    }

    public Consequens getYesAnswer(){
        return this.yesAnswer;
    }

    public Consequens getNoAnswer(){
        return this.noAnswer;
    }





}
