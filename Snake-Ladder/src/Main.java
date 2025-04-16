import model.Board;
import model.Dice;
import model.Player;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        Dice dice = new Dice(1);
        Board board = new Board(10,10,4,4,dice);

        Player player1 = new Player(1,"Player1");
        Player player2 = new Player(2,"Player2");
        board.joinPlayer(List.of(player1,player2));



    }
}