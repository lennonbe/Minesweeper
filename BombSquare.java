public class BombSquare extends GameSquare
{
    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb, rightClickFlag;                            // True if this squre contains a bomb. False otherwise.
    private int count;

	public static final int MINE_PROBABILITY = 10;

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png");

        this.board = board;
        this.hasBomb = (((int) (Math.random() * MINE_PROBABILITY)) == 0);
    }  
    
    public void leftClicked()
    {
        /*
        Sweeps for mines using recursion.
        All squares should have a value regarding the no. of mines adjacent to them.
        */

        if(this.hasBomb)
        {
            this.setImage("images/bomb.png");
        }
        else
        {
            this.clearSquare();
        }
    }

    public void rightClicked()
    {
        if(rightClickFlag)
        {
            this.setImage("images/blank.png");
            rightClickFlag = false;
        }
        else
        {
            this.setImage("images/flag.png");
            rightClickFlag = true;
        }
    }

    public void clearSquare()
    {
        board.getSquareAt(this.getXLocation() - 1, this.getYLocation()).setImage("images/0.png");
    }
}
