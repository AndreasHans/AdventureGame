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
        view.showNodeMessage(currentNode);

        //show node possibilities
        int[] pos = model.getPossibilities();
        view.showPossibilities(pos);

        //wait for valid user input
        int inp = view.getValidUserInput(pos);

        //handle user input
        if (inp == -1){
            end();
            return;
        }

        //go the user selected node
        model.selectNode(inp);

        //node event happens
        model.doNodeEvent();

        //show what happened
        String result = model.getResultMessage();
        String status = model.getStatus();
        view.showResults(result,status);

        //check game over
        if (model.playerIsDead()){
            end();
            return;
        }

        //repeat
        cycle();
    }
    public void end(){
        model.end();
        view.end();
    }

}
