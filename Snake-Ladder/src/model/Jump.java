package model;

public class Jump {
    protected  int[] startPosition = new int[2];
    protected int[] endPosition = new int[2];


    public Jump(int[] startPosition, int[] endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

}
