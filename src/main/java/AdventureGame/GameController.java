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


        Question q = model.getNextQuestion();
        view.showQuestion(q);
        String answer = view.getAnswer();



        if  (answer.equals("yes")){
            model.handleYes(q);
        }

        if  (answer.equals("no")){
            model.handleNo(q);
        }

        String result = model.getResultPromt();
        String status = model.getStatus();

        view.showResults(result,status);

        if (model.playerIsDead() || answer.equals("end")){
            end();
            return;
        }

        cycle();
    }

    public void end(){
        model.end();
        view.end();
    }



}
