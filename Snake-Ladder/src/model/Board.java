package model;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    protected Dice dice;
     protected Cell[][] board;
     protected int snake;
     protected int ladder;
     List<Player> players;
     protected int currentPlayerturn;

    public Board(int row, int col, int snake, int ladder, Dice dice) {
        this.dice = dice;
        this.board = new Cell[row][col];
        this.snake = snake;
        this.ladder = ladder;
        initBoard(row, col);
        addSnake(snake);
        addLadder(ladder);
        currentPlayerturn = 0;
    }
    public void joinPlayer(List<Player> players) {
        this.players = players;
        for (Player player : players) {
            System.out.println("players: has been added to board"+player.name);
        }
    }
    private void initBoard(int row, int col) {
        int value = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.board[i][j] = new Cell(value++, i, j);
            }
        }
        System.out.println("board has been initialized"+"row: "+row+"col: "+col );
    }

    private void addSnake(int snake) {
        while (snake > 0) {
            // start postion
            int x1 = ThreadLocalRandom.current().nextInt(0, board.length * board[0].length + 1);
            int y1 = ThreadLocalRandom.current().nextInt(0, board.length * board[0].length + 1);

            //end position
            int x2 = ThreadLocalRandom.current().nextInt(0, x1);
            int y2 = ThreadLocalRandom.current().nextInt(0, y1);
            if (this.board[x1][y1].isJumpCheck()) {
                continue;
            }
            // startPoint > endPoint
            Jump jump = new Jump(new int[]{x1, y1}, new int[]{x2, y2});
            this.board[x1][y1].jumpSet(jump);
            System.out.println("snake has been added to board"+"x1: "+x1+"y1: "+y1+"x2: "+x2+"y2: "+y2);
            snake--;
        }

    }
    private void addLadder(int ladder) {
        while (ladder > 0) {
            // start postion
            int x1 = ThreadLocalRandom.current().nextInt(0, board.length * board[0].length + 1);
            int y1 = ThreadLocalRandom.current().nextInt(0, board.length * board[0].length + 1);

            //end position
            int x2 = ThreadLocalRandom.current().nextInt(x1+1, board.length * board[0].length + 1);
            int y2 = ThreadLocalRandom.current().nextInt(y1+1, board.length * board[0].length + 1);
            if (this.board[x1][y1].isJumpCheck()) {
                continue;
            }
            // startPoint < endPoint
            Jump jump = new Jump(new int[]{x2, y2}, new int[]{x1, y1});
            this.board[x1][y1].jumpSet(jump);
            System.out.println("ladder has been added to board"+"x1: "+x1+"y1: "+y1+"x2: "+x2+"y2: "+y2);
            ladder--;
        }
    }


    public int roleDice() {
        int diceNumber = 0;
        while (true) {
            diceNumber += dice.roleDice();
            if(diceNumber != 6){
                break;
            }
        }
        System.out.println("diceNumber: "+diceNumber);
        this.MovePlayer(players.get(currentPlayerturn),diceNumber)
        currentPlayerturn = (currentPlayerturn + 1) % players.size();
        return diceNumber;
    }

    public void MovePlayer(Player player, int diceNumber){

        int currentCellNum = player.getCurrentPosition(); // Get the current cell number
        int newCellNum = currentCellNum + diceNumber;     // Calculate the new position

        int boardSize = board.length * board[0].length; // Total cells in the board

        // If the new position is out of bounds, player stays in the current position
        if (newCellNum > boardSize) {
            System.out.println(player.getName() + " rolls " + diceNumber + " Winner !! Winner ");
            return;
        }

        // Translate 1D position into 2D indices for the board
        int row = (newCellNum - 1) / board[0].length;
        int col = (newCellNum - 1) % board[0].length;

        player.setCurrentPosition(newCellNum); // Update player's current position
        System.out.println(player.getName() + " moves to " + newCellNum);

        // Check if the current cell has a Snake or Ladder
        if (board[row][col].isJumpCheck()) {
            Jump jump = board[row][col].isJump; // Get the Jump (Snake or Ladder) for this cell
            int[] jumpEnd = jump.endPosition;  // End position of the jump
            int finalCellNum = (jumpEnd[0] * board[0].length) + jumpEnd[1] + 1;

            player.setCurrentPosition(finalCellNum); // Update player's position to the end of the jump
            System.out.println(player.getName() + " encounters a jump and moves to " + finalCellNum);
        }


    }


}
