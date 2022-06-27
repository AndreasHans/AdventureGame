package AdventureGame;

public class Player {

    private int age,health;
    private String name;

    public Player(String name){
        this.age = 0;
        this.health = 50;
        this.name = name;
    }


    public String toString(){
        return this.name + " age:" + this.age + " health:" + this.health;
    }


    public String getName() {
        return this.name;
    }

    public void increaseAge(){
        this.age++;
    }

    public void increaseHealth() {
        this.health++;
    }
}
