// CLASS: Library
//
// Author: Jaspreet Singh, 7846401
//
// REMARKS: The class provides shared access to methods in other classes to avoid duplication
//
//-----------------------------------------

public class Library
{
    /**
     * checkWin - Helper method to check if one of the player won
     *
     * @param board - The board where we look for a winner
     * return   the player with appropriate status is returned
     */
    public  Status  checkWin(Status[][]board)
    {
         int height= board.length;
         int width = board.length;

        for (int row = 0; row < height; row++) { // iterating rows from bottom to top
            for (int col = 0; col < width; col++) { // iterating columns from left to right
                Status  player =  board[row][col];
                if (player ==Status.NEITHER)
                    continue;

                if (col + 3 < width &&
                        player == board[row][col+1] &&
                        player == board[row][col+2] && //check right
                        player == board[row][col+3])
                    return player;
                if (col - 3 > width &&
                        player == board[row][col-2] &&
                        player == board[row][col-1] && //check left
                        player == board[row][col-3])
                    return player;
                if (row+3 < height) {
                    if (player == board[row+1][col] &&
                            player == board[row+2][col] && //check up
                            player == board[row+3][col])
                        return   player;

                    if (col-3 >= 0 &&
                            player == board[row+1][col-1] && // check up and left
                            player == board[row+2][col-2] &&
                            player == board[row+3][col-3])
                        return   player;

                    if (col+3 < width &&
                            player == board[row+1][col+1] && // check up and right
                            player == board[row+2][col+2] &&
                            player == board[row+3][col+3])
                        return   player;

                }
                else if(row-3>height)
                {
                    if (player == board[row-1][col] && // check down
                            player == board[row-2][col] &&
                            player == board[row-3][col])
                        return   player;

                    if (col+3 < width &&
                            player == board[row-1][col-1] && // check down and right
                            player == board[row-2][col-2] &&
                            player == board[row-3][col-3])
                        return   player;

                    if (col-3 >= 0 &&
                            player == board[row-1][col+1] && // check down and left
                            player == board[row-2][col+2] &&
                            player == board[row-3][col+3])
                        return   player;

                }
            }
        }
        return  Status.NEITHER; //winner not found
    }


}
