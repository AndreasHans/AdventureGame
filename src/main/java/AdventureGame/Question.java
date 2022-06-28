package AdventureGame;


import java.util.function.Function;

public class Question {

    private String question;
    private Function yesAnswer;
    private Function noAnswer;

    public Question(String q, Function a1,Function a2){
        this.question = q;
        this.yesAnswer = a1;
        this.noAnswer = a2;
    }

    public String getQuestion() {
        return this.question;
    }

    public Function getYesAnswer(){
        return this.yesAnswer;
    }

    public Function getNoAnswer(){
        return this.noAnswer;
    }





}
