import java.util.concurrent.ThreadLocalRandom;

// CLASS: ConnectGame
//
// Author: Jaspreet Singh,7846401
//
// REMARKS: The purpose of the class is to maintain the flow of the game by calling player class methods.
//
//-----------------------------------------


public class ConnectGame implements GameLogic {
    private int size;
    private HumanPlayer  human;
    private ComputerPlayer AI ;
    private int nextMove;
    private Status [][] board;
    int firstPlayer;
    Factory factory;
    boolean gameStarted;
    int nextTurn;
    Player play;
    public ConnectGame()
    {
        size = generateRandom(6,9);
        board = new Status[size][size];
        human = new HumanPlayer();
        AI  =new ComputerPlayer();

        human.setInfo(size,this);
        AI.setInfo(size,this);
        nextMove = -1;
        //factory = new Factory();
        firstPlayer=generateRandom(0,1);
        //play =factory.getplayer(firstPlayer,this,size);
        gameStarted=false;


    }
    /**
     * executeGame - a private helper method that starts the execution of the game upon call by main function
     */

    private void executeGame()
    {
        if(!gameStarted) {
            if (firstPlayer == 1)
            {
                gameStarted=true;
                nextTurn=0;
                AI.lastMove(nextMove);


            } else {
                gameStarted=true;
                nextTurn=1;
                human.lastMove(nextMove);
            }
        }
        else
        {
            if(nextTurn==1)     //AI's turn
            {
                nextTurn=0;

                    AI.lastMove(nextMove);

            }
            else {
                nextTurn = 1;
                    human.lastMove(nextMove);          //human's turn

            }
        }
    }
    /**
     * setAnswer - a private helper method that sets up the next move and executes the game further
     * @param col the column where the piece is dropped
     */

    @Override
    public void setAnswer(int col)
    {
        nextMove  =col;
        executeGame();
    }
    /**
     * generateRandom - a private helper method that finds a random number between the given range
     * @param max   the lower limit of the range
     * @param min   the upper limit of the range
     *  return     The random value in the given range
     */
    private int generateRandom(int min, int max)
    {

        int num = ThreadLocalRandom.current().nextInt(min,max+1);

        return num;
    }

}
