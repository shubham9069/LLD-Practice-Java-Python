package model;
import java.util.concurrent.ThreadLocalRandom;


public class Dice {
    private final int count;


    public Dice(int count) {
        this.count = count;
    }
    public int roleDice() {
        return count * ThreadLocalRandom.current().nextInt(1, 6 + 1);
    }
}
