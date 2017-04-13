//------------------------------------------------------------------//
// Board.java                                                       //
//                                                                  //
// Class used to represent a 2048 game board                        //
//                                                                  //
// Author:  Ujjwal Gulecha, Alan Kuo	                            //
// Date:    01/22/17                                                //
//------------------------------------------------------------------//

/*
 * Name: Angelynne Pawaan
 * Login: cs8bwadr
 * Date:  February 6th, 2017
 * File:  Board.java
 * Board.java contains Board contructors
 * for a new board and loads a saved board. 
 * It also contains the saveBoard method which allows the player
 * to save his/her current board/game/score/etc to an outputfile.
 * Contains the addRandomTile method which generates a new
 * tile after each move in game.
 * Contains flip method which flips the current board
 * into a variety of directions depending on the user's input
 */


import java.util.*;
import java.io.*;

public class Board {
    public final int NUM_START_TILES = 2;
    public final int TWO_PROBABILITY = 90;
    public int GRID_SIZE;


    private final Random random;
    private int[][] grid;
    private int score;

    // TODO PSA3
    // Constructs a fresh board with random tiles
    public Board(Random random, int boardSize) 
    {   
	// initiating all instance variables
	this.random = random;
        this.GRID_SIZE = boardSize;
	grid = new int[GRID_SIZE][GRID_SIZE];
	this.score = 0;
	// adding two new random tiles to fresh board
	addRandomTile();
	addRandomTile();	
    }

    // Construct a board based off of an input file
    public Board(Random random, String inputBoard) throws IOException 
    {
	// initiating instance variables
	this.random = random;
	File file = new File(inputBoard);
	Scanner input = new Scanner(file);
	ArrayList<Integer> fileElements = new ArrayList<Integer>();
	ArrayList<Integer> gridVal = new ArrayList<Integer>();
	// taking input from file to store in GRID_SIZE and score member variables
	int index = 0;
	// reading input from file and loading it to board
	while (index < 2)
	{
	 fileElements.add(input.nextInt());
	 index++;
	}

	this.GRID_SIZE = fileElements.get(0);
	this.score = fileElements.get(1);
	int gridInd = 0;
	grid = new int[GRID_SIZE][GRID_SIZE];

	while (input.hasNextInt() == true)
	{
	 gridVal.add(input.nextInt());
	}

	for (int row = 0; row < GRID_SIZE; row++)
        {
         for (int col = 0; col < GRID_SIZE; col++)
         {
	  grid[row][col] = gridVal.get(gridInd);
	  gridInd++;
         }
	}
    }

    // Saves the current board to a file
    public void saveBoard(String outputBoard) throws IOException 
    {
     PrintWriter writer = new PrintWriter(outputBoard);
     writer.println(this.GRID_SIZE);
     writer.println(this.score);
     // saving each element in file through grid format
     for (int row = 0; row < GRID_SIZE; row++)
     {
       for (int col = 0; col < GRID_SIZE; col++)
      {
       writer.print(grid[row][col] + " ");
      }
      writer.println();
     }
     writer.close();
    }

    // Adds a random tile (of value 2 or 4) to a
    // random empty space on the board
    public void addRandomTile() 
    {
     int count = 0;
     // counting all empty slots
     for (int row = 0; row < GRID_SIZE; row++)
     {
      for (int col = 0; col < GRID_SIZE; col++)
      {
       if (grid[row][col] == 0) count++;       
      }
     }
     if (count == 0) return; // if there are no empty spots, return or gameover
     
     int location = random.nextInt(count);
     int value = random.nextInt(100);
     int emptySlots = 0;

     // finds location'nth position of empty slot and places new tile there
     for (int row = 0; row < GRID_SIZE; row++)
     {
      for (int col = 0; col < GRID_SIZE; col++)
      {
       if (grid[row][col] == 0)
       {
	emptySlots++;
	if ((emptySlots - 1) == location)
	{
	 // if in correct position, place new tile based on probability
	 if (value < TWO_PROBABILITY) grid[row][col] = 2;
	 else grid[row][col] = 4;
	}
       }	       
      }
     } 
    }

    // Flip the board horizontally or vertically,
    // Rotate the board by 90 degrees clockwise or 90 degrees counter-clockwise.
    public void flip(int change) 
    {
     int temp = 0; // to store old element before swapping values
     
     // flip method for horizontal flip
     if (change == 1)
     {
      for (int row = 0; row < GRID_SIZE; row++)
      {
       for (int col = 0; col < GRID_SIZE / 2; col++)
       {
	temp = grid[row][col];
	grid[row][col] = grid[row][GRID_SIZE - 1 - col];
	grid[row][GRID_SIZE - 1 - col] = temp;		
       }     
      }
     }
     
     // method for vertical flip
     if (change == 2)
     {
      for (int row = 0; row < GRID_SIZE / 2; row++)
      {
       for (int col = 0; col < GRID_SIZE; col++)
       {
        temp = grid[row][col];
	grid[row][col] = grid[GRID_SIZE - 1 - row][col];
	grid[GRID_SIZE - 1 - row][col] = temp;		
       }     
      }
     }

     // method for clockwise rotation flip
     int saveVal = 0;
     int gridInd = 0;
     ArrayList <Integer> gridVal = new ArrayList<Integer>();
     if (change == 3)
     {
      // first saving values into an arraylist in clockwise order
      for (int col = 0; col < GRID_SIZE; col++)
      {
       for (int row = 0; row < GRID_SIZE; row++)
       {
        saveVal = grid[GRID_SIZE - 1 - row][col];
        gridVal.add(saveVal);	
       }     
      }
      // taking values from arraylist and printing them out
      for (int row = 0; row < GRID_SIZE; row++)
      {
       for (int col = 0; col < GRID_SIZE; col++)
       {
        grid[row][col] = gridVal.get(gridInd);
	gridInd++;
       }
      }
     }
  
     // method for counterclockwise rotation flip
     int saveVal2 = 0;
     int gridInd2 = 0;
     ArrayList <Integer> gridVal2 = new ArrayList<Integer>();
     if (change == 4)
     {
      // first saving values into an arraylist in counter clockwise order
	     
      for (int col = GRID_SIZE - 1; col > -1; col--)
      {
       for (int row = 0; row < GRID_SIZE; row++)
       {
        saveVal2 = grid[row][col];
        gridVal2.add(saveVal2);	
       }     
      }
      // taking values from arraylist and printing them out
   
      for (int row = 0; row < GRID_SIZE; row++)
      {
       for (int col = 0; col < GRID_SIZE; col++)
       {
        grid[row][col] = gridVal2.get(gridInd2);
	gridInd2++;
       }
      }
     }
    }

    //Complete this method ONLY if you want to attempt at getting the extra credit
    //Returns true if the file to be read is in the correct format, else return
    //false
    public static boolean isInputFileCorrectFormat(String inputFile) {
        //The try and catch block are used to handle any exceptions
        //Do not worry about the details, just write all your conditions inside the
        //try block
        try {
            //write your code to check for all conditions and return true if it satisfies
            //all conditions else return false
            return true;
        } catch (Exception e) {
            return false;
        }
    }
 
    // checks to see if the desired move can be performed
    public boolean canMove(Direction direction)
    {
        if (direction.equals(Direction.LEFT) == true)
	{
	   if (canMoveLeft() == true) return true;   
	}

	if (direction.equals(Direction.RIGHT) == true)
	{
	   if (canMoveRight() == true) return true;   
	}	

	if (direction.equals(Direction.UP) == true)
	{
	   if (canMoveUp() == true) return true;   
	}	

	if (direction.equals(Direction.DOWN))
	{
	   if (canMoveDown() == true) return true;   
	}	
     return false;
    }
    
    //helper methods to check if each desired direction can be performed    
    private boolean canMoveLeft() 
    {
	for (int row = 0; row < GRID_SIZE; row++)
        {
	 for (int col = 1; col < GRID_SIZE; col++)
	 {
	    if (grid[row][col - 1] == 0 || grid[row][col - 1] == grid[row][col])
	    {
	       return true;
	    }
	 }
	}
     return false;	
    }

    private boolean canMoveRight() 
    {
	for (int row = 0; row < GRID_SIZE; row++)
        {
	 for (int col = 0; col < GRID_SIZE - 1; col++)
	 {
	    if (grid[row][col + 1] == 0 || grid[row][col + 1] == grid[row][col])
	    {
	       return true;
	    }
	 }
	}
     return false;
    }

    private boolean canMoveUp() 
    {
	for (int row = 1; row < GRID_SIZE; row++)
        {
	 for (int col = 0; col < GRID_SIZE; col++)
	 {
	    if (grid[row - 1][col] == 0 || grid[row - 1][col] == grid[row][col])
	    {
	       return true;
	    }
	 }
	}
     return false;
    }

    
    private boolean canMoveDown() 
    {
	for (int row = 0; row < GRID_SIZE - 1; row++)
        {
	 for (int col = 0; col < GRID_SIZE; col++)
	 {
	    if (grid[row + 1][col] == 0 || grid[row + 1][col] == grid[row][col])
	    {
	       return true;
	    }
	 }
	}
    return false;
    }

    // Performs a move Operation to perform move desired by user
    public boolean move(Direction direction) 
    {
	if (canMove(direction) == true)
	{
	  if (direction.equals(Direction.LEFT) == true)
	  {
	   moveLeft();
	   return true;
	  }
	  
	  if (direction.equals(Direction.RIGHT) == true)
	  {
	   moveRight();
	   return true;
	  }

	  if (direction.equals(Direction.UP) == true)
	  {
	   moveUp();
	   return true;
	  }

	  if (direction.equals(Direction.DOWN) == true)
	  {
	   moveDown();
	   return true;
	  }
	}
     return false;
    }
    // move helper methods that are specific to each direction
    private void moveLeft()
    {   
     // shifts board first
     for (int shiftCount = 0; shiftCount < GRID_SIZE; shiftCount++)
     {     
      int temp = 0;

      for (int row = 0; row < GRID_SIZE; row++)
      {
       for (int col = 1; col < GRID_SIZE; col++)
       { 
        if (grid[row][col - 1] == 0)
        {
	 grid[row][col - 1] = temp;
	 grid[row][col - 1] = grid[row][col];
	 grid[row][col] = temp;
        }
       }
      }
     }
	    
     // combine board
     int count;
     for (int row = 0; row < GRID_SIZE; row++)
     {
      for (int col = 1; col < GRID_SIZE; col++)
      {
       count = 0;
       if (count == 0)
       {
       if (grid[row][col - 1] == grid[row][col])
       {
        grid[row][col - 1] = (grid[row][col]*2);
	grid[row][col] = 0;
        score += grid[row][col - 1];	
       }
       count = 1;
       }
      }
     }

     // shift again to make sure all tiles are at desired area
     for (int shiftCount = 0; shiftCount < GRID_SIZE; shiftCount++)
     {     
      int temp = 0;

      for (int row = 0; row < GRID_SIZE; row++)
      {
       for (int col = 1; col < GRID_SIZE; col++)
       { 
        if (grid[row][col - 1] == 0)
        {
	 grid[row][col - 1] = temp;
	 grid[row][col - 1] = grid[row][col];
	 grid[row][col] = temp;
        }
       }
      }
     }
    }

    private void moveRight()
    {   
     // shifts board first
     for (int shiftCount = 0; shiftCount < GRID_SIZE; shiftCount++)
     {     
     int temp = 0;
      for (int row = 0; row < GRID_SIZE; row++)
      {
       for (int col = GRID_SIZE - 2; col > -1; col--)
       { 
        if (grid[row][col + 1] == 0)
        {
	 grid[row][col + 1] = temp;
	 grid[row][col + 1] = grid[row][col];
	 grid[row][col] = temp;
        }
        }
      }
     }
      // combine board
     int count;
     for (int row = 0; row < GRID_SIZE; row++)
     {
      for (int col = GRID_SIZE - 2; col > -1; col--)
      {
       count = 0;
       if (count == 0)
       {
        if (grid[row][col + 1] == grid[row][col])
        {
        grid[row][col + 1] = (grid[row][col]*2);
	grid[row][col] = 0;
        score += grid[row][col + 1];
        }
        count = 1;
       }
      }
     }
     
     // shift again to make sure all tiles are at desired area     
     for (int shiftCount = 0; shiftCount < GRID_SIZE; shiftCount++)
     {     
     int temp = 0;
      for (int row = 0; row < GRID_SIZE; row++)
      {
       for (int col = GRID_SIZE - 2; col > -1; col--)
       { 
        if (grid[row][col + 1] == 0)
        {
	 grid[row][col + 1] = temp;
	 grid[row][col + 1] = grid[row][col];
	 grid[row][col] = temp;
        }
        }
      }
     }
    }

    private void moveUp()
    { 
     // shifts board first   
     for (int shiftCount = 0; shiftCount < GRID_SIZE; shiftCount++)
     {     
      int temp = 0;

      for (int row = 1; row < GRID_SIZE; row++)
      {
       for (int col = 0; col < GRID_SIZE; col++)
       {
        if (grid[row - 1][col] == 0)
        {
	 grid[row - 1][col] = temp;
	 grid[row - 1][col] = grid[row][col];
	 grid[row][col] = temp;
        }
       }
      }
     }
     
     // combine board
     int count;
     for (int row = 1; row < GRID_SIZE; row++)
     {
      for (int col = 0; col < GRID_SIZE; col++)
      {
       count = 0;
       if (count == 0)
       {
        if (grid[row - 1][col] == grid[row][col])
        {
         grid[row - 1][col] = (grid[row][col]*2);
	 grid[row][col] = 0;
         score += grid[row - 1][col];
        }
       count = 1;
       }
      }
     }
    
     // shift again to make sure all tiles are at desired area     
     for (int shiftCount = 0; shiftCount < GRID_SIZE; shiftCount++)
     {     
      int temp = 0;

      for (int row = 1; row < GRID_SIZE; row++)
      {
       for (int col = 0; col < GRID_SIZE; col++)
       {
        if (grid[row - 1][col] == 0)
        {
	 grid[row - 1][col] = temp;
	 grid[row - 1][col] = grid[row][col];
	 grid[row][col] = temp;
        }
       }
      }
     }
    }

    private void moveDown()
    { 
     // shifts board first
     for (int shiftCount = 0; shiftCount < GRID_SIZE; shiftCount++)
     {     
      int temp = 0;

      for (int row = GRID_SIZE - 2; row > -1; row--)
      {
       for (int col = 0; col < GRID_SIZE; col++)
       {
        if (grid[row + 1][col] == 0)
        {
	 grid[row + 1][col] = temp;
	 grid[row + 1][col] = grid[row][col];
	 grid[row][col] = temp;
        }
       }
      }
     }
     
     // combine board
     int count;
     for (int row = GRID_SIZE - 2; row > -1; row--)
     {
      for (int col = 0; col < GRID_SIZE; col++)
      {
       count = 0;
       if (count == 0)
       {
        if (grid[row + 1][col] == grid[row][col])
        {
         grid[row + 1][col] = (grid[row][col]*2);
	 grid[row][col] = 0;
	 score += grid[row + 1][col];
        }
       count = 1;
       }
      }
     }
     
     // shift again to make sure all tiles are at desired area    
     for (int shiftCount = 0; shiftCount < GRID_SIZE; shiftCount++)
     {     
      int temp = 0;

      for (int row = GRID_SIZE - 2; row > -1; row--)
      {
       for (int col = 0; col < GRID_SIZE; col++)
       {
        if (grid[row + 1][col] == 0)
        {
	 grid[row + 1][col] = temp;
	 grid[row + 1][col] = grid[row][col];
	 grid[row][col] = temp;
        }
       }
      }
     }
    }

    // Check to see if we have a game over
    public boolean isGameOver() 
    {
     // checks each can move direction if board has any valid moves left 
     if (canMoveLeft() == false &&
	 canMoveRight() == false &&
	 canMoveDown() == false &&
	 canMoveUp() == false)
     {
         System.out.println("Game Over! Try again.");
	 return true;
     }
 
    return false;
    }


    // expand method
    public void expand()
    {
     int[][] newGrid; // copy of new board
     newGrid = new int[GRID_SIZE + 1][GRID_SIZE + 1];
     for (int row = 0; row < this.GRID_SIZE; row++)
     {
      for (int col = 0; col < this.GRID_SIZE; col++)
      {
       newGrid[row][col] = grid[row][col];
      }
     }
     grid = newGrid;
     GRID_SIZE = GRID_SIZE + 1;
    }




    // Return the reference to the 2048 Grid
    public int[][] getGrid() {
        return grid;
    }

    // Return the score
    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        outputString.append(String.format("Score: %d\n", score));
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++)
                outputString.append(grid[row][column] == 0 ? "    -" :
                        String.format("%5d", grid[row][column]));

            outputString.append("\n");
        }
        return outputString.toString();
    }
}
