package model;

public class Cell {
    protected int number;
    protected  int[] position = new int[2];
    protected Jump isJump = null;


    public Cell(int number, int rowNum, int colNum) {
        this.number = number;
        this.position[0] = rowNum;
        this.position[1] = colNum;

    }
    public int getCellNumber() {
        return number;
    }

    public void jumpSet(Jump isJump) {
        this.isJump = isJump;
    }
    public boolean isJumpCheck() {
        return isJump != null;
    }

}
