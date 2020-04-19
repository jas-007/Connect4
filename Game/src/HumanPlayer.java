// CLASS: HumanPlayer
//
// Author: Jaspreet Singh, 7846401
//
// REMARKS: The class basically gets the input from User and sets up communication with UI.
//
//-----------------------------------------

import java.util.Arrays;

public class HumanPlayer implements Human, Player {

    private Status[][] board;
    private SwingGUI ui;
    private Library library;
    private int nextMove;
    GameLogic logic;

    public HumanPlayer() {
        ui = new SwingGUI();
        library  =new Library();
        nextMove =-1;
    }

    /**
     * lastMove - called to indicate the last move of the opponent. See the
     * assignment for more detail.
     * @param lastCol - column where the opponent played.
     */

    @Override
    public void lastMove(int lastCol) {

        ui.lastMove(lastCol);
        //call setAnswer of Logic with next move
    }


    /**
     * gameOver - called when the game is over. See assignment
     * for more details
     * @param winner - who won the game or NEITHER if the game is a draw.
     */



    @Override
    public void gameOver(Status winner) {
        ui.gameOver(winner);
    }
    /**
     * setInfo - called before any other action. Sets the Human and size
     * of the board. See assignment for more details.
     * @param gl - pointer to the human that is using this UI
     * @param size - size of the board.
     */
    @Override
    public void setInfo(int size, GameLogic gl) {

        board = new Status[size][size];
        logic = gl;
        ui.setInfo(this, size);
        for (Status[] s : board) {
            Arrays.fill(s, Status.NEITHER);
        }

    }
    /**
     * setAnswer - a private helper method that sets up the next move and passes information to the game logic
     * @param col the column where the piece is dropped
     */
    @Override
    public void setAnswer(int col) {
        nextMove = col;
        Status winner=library.checkWin(board);
        if(winner!=Status.NEITHER)             //checking if the game is over
            gameOver(winner);
        else
        logic.setAnswer(col);
    }


    /**
     * drop - a private helper method that finds the position of a marker
     * when it is dropped in a column.
     *
     * @param col the column where the piece is dropped
     * @return the row where the piece lands
     */
    private int drop(int col) {

        int posn = 0;
        while (posn < board.length && board[posn][col] == Status.NEITHER) {
            posn++;
        }
        return posn - 1;
    }



}