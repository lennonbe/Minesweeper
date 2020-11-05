/**
 * This class is a subclass of GameSquare, inheriting its methods.
 * This class provides a representation of a single game square for the bombsweeper game.
 */
public class BombSquare extends GameSquare
{
    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb, rightClickFlag, beenCleared;                            // True if this squre contains a bomb. False otherwise.
    private int count = 0;
    private BombSquare temp;

    private static boolean gameOver;            //static variable shared amongst all squares, allows to implement game over without accessing other classes
	public static final int MINE_PROBABILITY = 10;

    /**
     * Constructor for the BombSquare object.
     * Creates a BombSquare which can be placed on the GameBoard.
     * Also defines a beenCleared variable as false, since instantiated squares have not yet been cleared.
     * @param x the x co-ordinate of this square on the game board.
     * @param y the y co-ordinate of this square on the game board.
     * @param board the filename of an image to display on this square.
     */
	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png");

        this.board = board;
        this.hasBomb = (((int) (Math.random() * MINE_PROBABILITY)) == 0);
        this.beenCleared = false;
    }
    
    /**
     * Method which recognizes a use of the mouse left click.
     * This method is used for clearing the squares and it essentially displays what the square
     * represents. This is either a clear space, in which case there are no bombs around it or a number, which is the number
     * of bombs around this square or a bomb, which means this square had a bomb and the game is over.
     */
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

    /**
     * This allows the user to place a flag on squares which havent been cleared yet. This is 
     * an in game mechanism to help the player keep track of squares with a high chance of 
     * having a bomb.
     */
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

    /**
     * This method sets the count method on a square.
     * The count variable is a representation of form int of the number of bombs around a certain square.
     * This method aquires the number of bombs around a square by checking all the squares around it for a hasBomb flag. 
     * Every time this hasBomb flag is true it increments a count variable which is representative of the number of bombs around a square.
     */
    public void setCount()
    {
        for(int i = -1; i < 2; i++)
        {
            for(int j = -1; j < 2; j++)
            {
                if(this.getXLocation() + i < 0 || this.getXLocation() + i > 29 || this.getYLocation() + j < 0 || this.getYLocation() + j > 29)
                {
                    //do nothing, as this square does not exist, it is out of bounds
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

    /**
     * This method sets the squares image based on their count and hasBomb variable.
     * It is used recursivelly as the minesweeper rules imply that if a square has no bombs all squares around it must be cleared as well. 
     * Due to this this method clears all squares around it and iterates throught the squares around it to ensure all squares which have a 
     * count of 0 are cleared, it achieves this by using a temp variable of type BombSquare.
     * Once the square is revealed the beenCleared flag on the square is set to true.
     * Ensures stackoverflow doesnt occur by checking the beenCleared variable, which as the 
     * name implies is a flag for showing if a square has been cleared.
     */
    public void clearSquare()
    {
        this.setCount();

        for(int i = -1; i < 2; i++)
        {
            for(int j = -1; j < 2; j++)
            {
                if(this.getXLocation() + i < 0 || this.getXLocation() + i > 29 || this.getYLocation() + j < 0 || this.getYLocation() + j > 29)
                {
                    //do nothing, as this square does not exist, it is out of bounds
                }
                else
                {
                    temp = (BombSquare) this.board.getSquareAt(this.getXLocation() + i, this.getYLocation() + j);

                    if(!this.temp.beenCleared && !this.temp.hasBomb)
                    {
                        this.beenCleared = true;
                        
                        if(this.count == 0)
                        {
                            this.temp.clearSquare();
                        }

                        this.setImage("images/" + String.valueOf(this.count) + ".png");
                    }
                }
            }
        }
    }
}