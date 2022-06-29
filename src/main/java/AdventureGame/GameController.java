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
    }

    private void cycle() {

        //current node
        Node currentNode = model.getCurrentNode();

        //show node message
        view.showHitMessage(currentNode);

        //show node possibilities
        Node[] possibilities = model.getPossibilities();
        view.showPossibilities(possibilities);

        //wait for valid user input
        int inp = view.getValidUserInput(possibilities);

        //handle user input
        if (inp == -1){
            view.showLost();
            return;
        }

        //go the user selected node
        model.selectNode(inp);

        //node event happens
        model.doNodeEvent();

        //show what happened
        String result = model.getEventMessage();
        String status = model.getStatus();
        view.showResults(result,status);

        //check game over
        if (model.isGameOver()){
            GameOver();
            return;
        }

        //repeat
        cycle();
    }

    public void GameOver(){
        if (model.playerWon()){
            view.showWin();
        } else {
            view.showLost();
        }
    }

}
