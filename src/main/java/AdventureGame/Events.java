package AdventureGame;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.*;

public class Events {

    private ArrayList<Function> list;
    private static Random rand = new Random();

    public Events(){
        list = new ArrayList<>();
        generateEvents();
    }

    private void generateEvents(){
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
        this.list.add(decreaseHP);
        this.list.add(increaseAge);
        this.list.add(increaseHP);
        //this.events.add(kill);
    }

    public Function getRandomEvent(){
        int r = rand.nextInt(this.list.size());
        return this.list.get(r);
    }


    public Function getNoEvent() {
        return (p) -> null;
    }
}
