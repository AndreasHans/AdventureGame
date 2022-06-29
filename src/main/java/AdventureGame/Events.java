package AdventureGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

public class Events {

    private ArrayList<Function> all;
    private ArrayList<Function> bad;
    private ArrayList<Function> good;
    private static Random rand = new Random();

    public Events(){
        all = new ArrayList<>();
        bad = new ArrayList<>();
        good = new ArrayList<>();
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

        this.all.add(decreaseHP);
        this.all.add(increaseAge);
        this.all.add(increaseHP);

        bad.add(kill);
        bad.add(decreaseHP);

        good.add(increaseAge);
        good.add(increaseHP);
    }

    public Function getRandomEvent(){
        int r = rand.nextInt(this.all.size());
        return this.all.get(r);
    }

    public Function getBadEvent(){
        int r = rand.nextInt(this.bad.size());
        return this.bad.get(r);
    }

    public Function getGoodEvent(){
        int r = rand.nextInt(this.good.size());
        return this.good.get(r);
    }

    public Function getNoEvent() {
        return (p) -> null;
    }
}
