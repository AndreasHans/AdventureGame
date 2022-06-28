package AdventureGame;

public class Node {
    private final String title;
    private final String message;
    private final int index;

    public Node(int index, String title, String message){
        this.title = title;
        this.message = message;
        this.index = index;
    }

    public String getTitle(){
        return this.title;
    }

    public int getIndex(){
        return this.index;
    }

    public String getMessage() {
        return this.message;
    }
}
