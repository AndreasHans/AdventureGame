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

    public void decreaseHealth(){
        this.health--;
    }

    public void killPlayer(){
        this.health = 0;
    }

    public void increaseHealth() {
        this.health++;
    }

    public int getHealth() {
        return this.health;
    }
}