package model;

public class Player {
    protected int id;
    protected String name;
    protected int position;


    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.position = 0;
    }

    public int updatexPosition(int position) {
        this.position = position;
        return this.position;
    }
    public int getCurrentPosition() {
        return position;
    }
}
