package AdventureGame;

import java.util.function.Function;

public class NodeTypes {

    private Events events;

    public NodeTypes(){
        this.events = new Events();
    }

    public Node createSafeNode(int i){
        String title = "a safe field.";
        String message = "Here you're safe!";
        String eventMessage = "there is no event";
        String hint = "a very safe field";
        String resultMessage = "Nothing happened";
        String typeThis = "safe"+i;
        Function event = events.getNoEvent();
        return new Node(i,title,message,eventMessage,hint,resultMessage,typeThis, event);
    }

    public Node createStartNode(int i){
        String title = "a starting field.";
        String message = "Here starts the adventure!";
        String eventMessage = "there is no event";
        String hint = "the starting field";
        String resultMessage = "Nothing happened";
        String typeThis = "start"+i;
        Function event = events.getNoEvent();
        return new Node(i,title,message,eventMessage,hint,resultMessage,typeThis, event);
    }

    public Node createGoalNode(int i){
        String title = "a goal field.";
        String message = "Here ends the adventure!";
        String eventMessage = "there is no event";
        String hint = "the ending field";
        String resultMessage = "Nothing happened";
        String typeThis = "goal"+i;
        Function event = events.getNoEvent();
        return new Node(i,title,message,eventMessage,hint,resultMessage,typeThis, event);
    }

    public Node createEventNode(int i){
        String title = "an event field.";
        String message = "Here an event happened as you came!";
        String eventMessage = "there is an event";
        String hint = "a cool event field";
        String resultMessage = "A random event happened";
        String typeThis = "event"+i;
        Function event = events.getRandomEvent();
        return new Node(i,title,message,eventMessage,hint,resultMessage,typeThis, event);
    }

    public Node createTrapNode(int i) {
        String title = "a trap field.";
        String message = "Here you might die!";
        String eventMessage = "you went to a death trap!";
        String hint = "looks like a trap!";
        String resultMessage = "You fell into a trap";
        String typeThis = "trap"+i;
        Function event = events.getBadEvent();
        return new Node(i,title,message,eventMessage,hint,resultMessage,typeThis, event);
    }

    public Node createGoodNode(int i) {
        String title = "a good field.";
        String message = "Here you feel something good!";
        String eventMessage = "you went to a good field!";
        String hint = "might be a cookie!";
        String resultMessage = "You feel better now";
        String typeThis = "cookie"+i;
        Function event = events.getGoodEvent();
        return new Node(i,title,message,eventMessage,hint,resultMessage,typeThis, event);
    }

}
