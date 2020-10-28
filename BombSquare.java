public class BombSquare extends GameSquare
{
    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb, rightClickFlag, beenSet, beenCleared;                            // True if this squre contains a bomb. False otherwise.
    private int count = 0;
    private BombSquare temp, surroundingBombsquare;
    //private BombSquare [] surroundingSquares;
    //private int surrCount = 0;

	public static final int MINE_PROBABILITY = 10;

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png");

        this.board = board;
        this.hasBomb = (((int) (Math.random() * MINE_PROBABILITY)) == 0);
        this.beenSet = false;
        this.beenCleared = false;
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
                    
                    surroundingBombsquare = (BombSquare) board.getSquareAt(this.getXLocation() + i, this.getYLocation() + j);
                    if(surroundingBombsquare.hasBomb)
                    {
                        this.count++;
                    }
                }
            }
        }
    }
    
    public void leftClicked()
    {
        if(this.hasBomb)
        {
            this.setImage("images/bomb.png");
            this.beenCleared = true;
        }
        else if(!this.hasBomb && !this.beenCleared && !this.rightClickFlag)
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
            if(!this.beenCleared)
            {
                this.setImage("images/flag.png");
                rightClickFlag = true;
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
                    surroundingBombsquare = (BombSquare) this.board.getSquareAt(this.getXLocation() + i, this.getYLocation() + j);

                    if(!surroundingBombsquare.beenCleared && !this.surroundingBombsquare.hasBomb)
                    {
                        this.beenCleared = true;
                        
                        if(this.count == 0)
                        {
                            surroundingBombsquare.clearSquare();
                        }

                        this.setImage("images/" + String.valueOf(this.count) + ".png");
                        
                    }
                }
                
            }
        
        }
    }

}