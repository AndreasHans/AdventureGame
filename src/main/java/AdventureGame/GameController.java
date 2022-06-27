package AdventureGame;



public class GameController {
    GameModel model;
    GameView view;

    public GameController(){
        this.model = new GameModel();
        this.view = new GameView();
    }

    public void start(){
        model.start();
        view.start();
        cycle();
        model.end();
        view.end();
    }

    private void cycle() {

        String move = view.getNextMove();

        if (move.equals("end")){
            return;
        }

        if  (move.equals("yes")){
            model.increasePlayerAge();
        }

        if  (move.equals("no")){
            model.increasePlayerHealth();
        }

        String status = model.getStatus();
        System.out.println(status);

        cycle();
    }


}
