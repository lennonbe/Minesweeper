public class BombSquare extends GameSquare
{
    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb, rightClickFlag, beenSet;                            // True if this squre contains a bomb. False otherwise.
    private int count = 0;
    private BombSquare temp;

	public static final int MINE_PROBABILITY = 10;

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png");

        this.board = board;
        this.hasBomb = (((int) (Math.random() * MINE_PROBABILITY)) == 0);
        this.beenSet = false;

        //this.setCount();
    }

    public boolean hasBomb()
    {
        return hasBomb;
    }
    
    public void setCount()
    {
        if(!beenSet)
        {
            beenSet = true;
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

                        if(temp.hasBomb())
                        {
                            this.count++;
                        }

                        System.out.println("Square" + this.getXLocation() + " " + this.getYLocation() + "has count = " + this.count);
                    }
                }
            }
        }
    }
    
    public void leftClicked()
    {
        /*
        Sweeps for mines using recursion.
        All squares should have a value regarding the no. of mines adjacent to them.
        */

        this.setCount();

        if(this.hasBomb)
        {
            this.setImage("images/bomb.png");
        }
        else
        {
            this.clearSquare();
            /*if(this.count == 0)
            {

            }*/
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
        if(this.count == 0)
        {
            /*this.setImage("images/0.png");
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
                        temp.clearSquare();

                    }
                }
            }*/
        }
        else
        {
            if(this.count == 1)
            {
                this.setImage("images/1.png");
            }
            else if(this.count == 2)
            {
                this.setImage("images/2.png");
            }
            else if(this.count == 3)
            {
                this.setImage("images/3.png");
            }
            else if(this.count == 4)
            {
                this.setImage("images/4.png");
            }
            else if(this.count == 5)
            {
                this.setImage("images/5.png");
            }
            else if(this.count == 6)
            {
                this.setImage("images/6.png");
            }
            else if(this.count == 7)
            {
                this.setImage("images/7.png");
            }
            else if(this.count == 8)
            {
                this.setImage("images/8.png");
            }
        }
    }
    
}
