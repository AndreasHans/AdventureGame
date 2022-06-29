package AdventureGame;

import java.util.function.Function;
//bridge breaks after using it
//a field that heals the player
//a field that kills the player
//a training camp
//field to eat
//village
//armour
//raiding a village

public class Node {
    private final String title,hitMessage;
    private final String hint,typeThis;
    private final String eventMessage,resultMessage;
    private final Function event;
    private final int index;


    public Node(){
        this.title = null;
        this.hint = null;
        this.hitMessage = null;
        this.resultMessage = null;
        this.eventMessage = null;
        this.event = null;
        this.index = -1;
        this.typeThis = null;
    }

    public Node(int index, String title, String message, String eventMessage, String hint, String resultMessage, String typeThis, Function event){
        this.title = title;
        this.hitMessage = message;
        this.index = index;
        this.eventMessage = eventMessage;
        this.event = event;
        this.hint = hint;
        this.resultMessage = resultMessage;
        this.typeThis = typeThis;
    }

    public String getResultMessage(){
        return this.resultMessage;
    }
    public String getHint(){
        return this.hint;
    }

    public Function getEvent(){
        return this.event;
    }

    public String getTitle(){
        return this.title;
    }

    public int getIndex(){
        return this.index;
    }

    public String getHitMessage() {
        return this.hitMessage;
    }

    public String getEventMessage() {
        return this.eventMessage;
    }

    public String getTypeThis() {
        return this.typeThis;
    }
}
