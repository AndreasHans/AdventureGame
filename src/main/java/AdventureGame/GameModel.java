package AdventureGame;

public class GameModel {

    Player player;
    public void start() {
        this.player = new Player("Andreas");
    }

    public void end() {


    }

    public String getStatus() {
        return this.player.toString();
    }

    public void increasePlayerAge() {
        this.player.increaseAge();
    }

    public void increasePlayerHealth() {
        this.player.increaseHealth();
    }
}
