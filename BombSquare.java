public class BombSquare extends GameSquare
{
    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb, rightClickFlag, beenCleared;                            // True if this squre contains a bomb. False otherwise.
    private int count = 0;
    private BombSquare temp;

    private static boolean gameOver;
	public static final int MINE_PROBABILITY = 10;

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png");

        this.board = board;
        this.hasBomb = (((int) (Math.random() * MINE_PROBABILITY)) == 0);
        this.beenCleared = false;
    }
    
    public void leftClicked()
    {
        if(this.hasBomb && !gameOver)
        {
            this.setImage("images/bomb.png");
            this.beenCleared = true;
            gameOver = true;
        }
        else if(!this.hasBomb && !this.beenCleared && !this.rightClickFlag && !gameOver)
        {
            this.clearSquare();
        }
    }

    public void rightClicked()
    {
        if(rightClickFlag && !gameOver)
        {
            this.setImage("images/blank.png");
            rightClickFlag = false;
        }
        else if(!rightClickFlag && !this.beenCleared && !gameOver)
        {
            this.setImage("images/flag.png");
            rightClickFlag = true;
        }
    }

    public void setCount()
    {
        for(int i = -1; i < 2; i++)
        {
            for(int j = -1; j < 2; j++)
            {
                if(this.getXLocation() + i < 0 || this.getXLocation() + i > 29 || this.getYLocation() + j < 0 || this.getYLocation() + j > 29)
                {
                    //do nothing, as this square does not exist
                }
                else
                {
                    temp = (BombSquare) board.getSquareAt(this.getXLocation() + i, this.getYLocation() + j);
                    if(temp.hasBomb)
                    {
                        this.count++;
                    }
                }
            }
        }
    }

    public void clearSquare()
    {
        this.setCount();

        for(int i = -1; i < 2; i++)
        {
            for(int j = -1; j < 2; j++)
            {
                if(this.getXLocation() + i < 0 || this.getXLocation() + i > 29 || this.getYLocation() + j < 0 || this.getYLocation() + j > 29)
                {
                    //do nothing, as this square does not exist
                }
                else
                {
                    temp = (BombSquare) this.board.getSquareAt(this.getXLocation() + i, this.getYLocation() + j);

                    if(!temp.beenCleared && !this.temp.hasBomb)
                    {
                        this.beenCleared = true;
                        
                        if(this.count == 0)
                        {
                            temp.clearSquare();
                        }

                        this.setImage("images/" + String.valueOf(this.count) + ".png");
                    }
                }
            }
        }
    }
}