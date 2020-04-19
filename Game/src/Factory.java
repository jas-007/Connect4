
public class Factory
{
    private Player human;
    private Player AI;
    public Factory()
    {
        human = new HumanPlayer();
        AI = new ComputerPlayer();
    }
    public Player getplayer(int playerType,GameLogic logic,int size)
    {
        Player p = null;
        if(playerType==0) //client chose human
        {

            human.setInfo(size,logic);
            p= human;
        }
        else if(playerType == 1) //client chose computer
        {
            AI.setInfo(size,logic);
            p = AI;
        }
        return p;

    }
}
