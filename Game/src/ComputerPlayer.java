import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

// CLASS: ComputerPlayer
//
// Author: Jaspreet Singh, 7846401
//
// REMARKS: The class basically implements the behaviour of computer while playing the game.
//
//-----------------------------------------


public class ComputerPlayer implements Player {
    private Status[][] board;
    private Library library;
    GameLogic logic;

//------------------------------------------------------
    // ComputerPlayer()
    //
    // PURPOSE:    Its the constructor which initializes instance variables of class
    // PARAMETERS: No parameters passed
    //
    // Returns: No return value
    //------------------------------------------------------
    public ComputerPlayer()
    {
            library = new Library();
    }

    //------------------------------------------------------
    // lastMove()
    //
    // PURPOSE:    It is called to tell a player the previous move and decide their next move.
    // PARAMETERS:
    // @param :It is the column of last move made by the opponent.
    // Returns: No return Value
    //------------------------------------------------------
    @Override
    public void lastMove(int lastCol) {

        Position newPosn = new Position();
        Position availablePositions[];

        if (lastCol != -1) {

            int lastPosn = drop(lastCol);
            board[lastPosn][lastCol] = Status.ONE; // this is HumanPlayer's move
            availablePositions=getPossiblePositions();

            System.out.println("BOARD");
            printBoard();

            newPosn = playingStrategy(Status.TWO,availablePositions);  //Defensive move
            if (newPosn.getRow()==0&& newPosn.getColumn()==0)
            {
                newPosn = playingStrategy(Status.ONE,availablePositions);
                if(newPosn.getRow()==0&& newPosn.getColumn()==0){                     // now select position randomly
                newPosn = availablePositions[ThreadLocalRandom.current().nextInt(0,availablePositions.length)];
                board[newPosn.getRow()][newPosn.getColumn()]=Status.TWO;
                    Status winner=library.checkWin(board);
                    if(winner!=Status.NEITHER)
                        gameOver(winner);
                    else
                logic.setAnswer(newPosn.getColumn());
            }
            else{
                board[newPosn.getRow()][newPosn.getColumn()]=Status.TWO;
                    Status winner=library.checkWin(board);
                    if(winner!=Status.NEITHER)
                        gameOver(winner);
                    else
                    logic.setAnswer(newPosn.getColumn());
            }
            }
            else
            {
                board[newPosn.getRow()][newPosn.getColumn()]=Status.TWO;
                Status winner=library.checkWin(board);
                if(winner!=Status.NEITHER)
                    gameOver(winner);
                else
                logic.setAnswer(newPosn.getColumn());
            }

        }
        else
        {
            int lastPosn = drop(lastCol);
            if(lastPosn!=-1)
            board[lastPosn][lastCol] = Status.ONE; // this is HumanPlayer's move
            availablePositions=getPossiblePositions();  //getting available positions for next move
            int x= ThreadLocalRandom.current().nextInt(0,availablePositions.length-1);
             lastPosn = drop(availablePositions[x].getColumn());
            board[lastPosn][availablePositions[x].getColumn()] = Status.TWO;//we need to choose random col from available
                                                                                                    // positions
            Status winner=library.checkWin(board);
            if(winner!=Status.NEITHER)
                gameOver(winner);

            logic.setAnswer(availablePositions[x].getColumn());

        }
    }
    /**
     * rowToString - private helper method to print a single
     * row of the board
     * @param s - an array from the board to be printed.
     * @return - String representation of the board.
     */
    private String rowToString(Status[] s) {

        String out = "";
        for (Status curr : s) {
            switch (curr) {
                case ONE:
                    out += "O";
                    break;
                case TWO:
                    out += "X";
                    break;
                case NEITHER:
                    out += ".";
                    break;
            }
        }
        return out;
    }

    /**
     * printBoard - private helper method to print the board.
     */
    private void printBoard() {

        for (Status[] s : board) {
            System.out.println(rowToString(s));
        }
    }

    /**
     * playingStrategy - private helper method to determine the next move of the AI player
     *
     * @param s - an array from the board to be printed.
     * @param availablePositions - an array of position where a possible move can be made
     * @return - position of the next move
     */
    private Position playingStrategy(Status s,Position[] availablePositions) {
        Position nextMove = new Position();
        Status opponent=Status.ONE;
        if(s==Status.ONE)
            opponent=Status.TWO;

       outer: for (int cause = 0; cause < availablePositions.length; cause++) {
            int bool = -1;
            for (int i = 0; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                   int row=availablePositions[cause].getRow() + i;;
                   int col=availablePositions[cause].getColumn() + j;
                   int counter=0;
                    if ((row>=0&&row<board.length)&&(col>=0&&col<board.length)) {
                        if (board[row][col] == s) // checking the corresponding positions
                        {
                            bool = 0;
                        }
                        else if(board[row][col] == opponent) {
                            bool=1;
                            counter++;
                        }
                    }
                    for (int k = 0; k < 2; k++) {
                         row= row + i;
                        col= col + j;
                        if ((row>=0&&row<board.length)&&(col>=0&&col<board.length)) {
                            if (board[row][col] == s )
                            {
                                bool = 0;
                            }
                            else if(board[row][col] == opponent) {
                                bool=1;
                                counter++;
                            }
                        }
                    }

                    if(counter==3&&((row>=0&&row<board.length)&&(col>=0&&col<board.length))) { //bool!=1
                        nextMove.setRow(availablePositions[cause].getRow());
                        nextMove.setColumn(availablePositions[cause].getColumn());
                        break outer;
                    }
                }

            }
        }

        return nextMove;
    }

    /**
     * getPossiblePositions - private helper method to determine all the possible moves possible.
     * @return - An array of positions where moves can be made
     */

    private Position[] getPossiblePositions() {
        Position dropPoints[] = new Position[board.length];

        for(int i=0;i<dropPoints.length;i++){
            dropPoints[i]=new Position();
        }

        for (int i = 0; i < board.length; i++) {
            int lastPosn = drop(i);
            dropPoints[i].setRow(lastPosn);
            dropPoints[i].setColumn(i);

        }
        return dropPoints;
    }
    /**
     * drop - a private helper method that finds the position of a marker
     * when it is dropped in a column.
     * @param col the column where the piece is dropped
     * @return the row where the piece lands
     */
    private int drop(int col) {

        if(col!=-1) {
            int posn = 0;
            while (posn < board.length && board[posn][col] == Status.NEITHER) {
                posn++;
            }
            return posn - 1;
        }
        return -1;

    }

    /**
     * gameOver - called when the game is over. See assignment
     * for more details
     * @param winner - who won the game or NEITHER if the game is a draw.
     */
    @Override
    public void gameOver(Status winner)
    {
        System.out.println("Game over!");
        if (winner == Status.NEITHER) {
            System.out.println("Game is a draw.");
        } else if (winner == Status.ONE) {
            System.out.println("You win.");
        } else {
            System.out.println("Computer wins.");
        }

    }
    /**
     * setInfo - called before any other action. Sets the GameLogic and size
     * of the board.
     * @param gl - pointer to the GameLogic that is using this class
     * @param size - size of the board.
     */
    @Override
    public void setInfo(int size, GameLogic gl) {
        board = new Status[size][size];
        logic = gl;
        for (Status[] s : board) {
            Arrays.fill(s, Status.NEITHER);
        }
    }

}


