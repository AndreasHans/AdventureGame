package AdventureGame;

public class GameApplication {

    Player player;
    GameController controller;


    public void start(){
        this.player = new Player("Andreas");
        this.controller = new GameController();
        controller.start();
    }















}
