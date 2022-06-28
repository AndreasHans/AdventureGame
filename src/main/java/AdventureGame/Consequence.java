package AdventureGame;

import java.util.function.Function;

public class Consequence {

    private Function func;
    private String message;

    public Consequence(Function f, String s){
        this.func = f;
        this.message = s;
    }

    public Function getFunction(){
        return this.func;
    }

    public String getMessage(){
        return this.message;
    }

}
