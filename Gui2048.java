//------------------------------------------------------------------//
// Gui2048.java                                                     //
//                                                                  //
// GUI Driver for 2048                                             //
//                                                                  //
// Author:  Ujjwal Gulecha		                             //
// Date:    11/09/16                                                //
//------------------------------------------------------------------//

/*
 * Name: Angelynne Pawaan
 * Login: cs8bwadr
 * Date:  March 6th, 2017
 * File:  Gui2048.java
 * Sources of Help: Textbook, tutors, piazza, discussion
 * 
 * Gui2048.java provides the GUI for the 2048 game based
 * on the methods and variables from Board.java
 * Gui2048.java contains the start method which provides
 * the graphical template for the game and sets up everything 
 * that is seen in the beginning. 
 * Gui2048 also overrides the handle method which takes care of
 * all the events and key presses within the game.
 * This program also contains the changeTile method
 * which is responsible for updating the GUI whenever
 * an event or key is pressed.   
 */


import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;
import java.io.*;


public class Gui2048 extends Application
{
    private String outputBoard; // The filename for where to save the Board
    private Board board; // The 2048 Game Board

    private static final int TILE_WIDTH = 106;

    private static final int TEXT_SIZE_LOW = 55; // Low value tiles (2,4,8,etc)
    private static final int TEXT_SIZE_MID = 45; // Mid value tiles 
                                                 //(128, 256, 512)
    private static final int TEXT_SIZE_HIGH = 35; // High value tiles 
                                                  //(1024, 2048, Higher)

    // Fill colors for each of the Tile values
    private static final Color COLOR_EMPTY = Color.rgb(238, 228, 218, 0.35);
    private static final Color COLOR_2 = Color.rgb(238, 228, 218);
    private static final Color COLOR_4 = Color.rgb(237, 224, 200);
    private static final Color COLOR_8 = Color.rgb(242, 177, 121);
    private static final Color COLOR_16 = Color.rgb(245, 149, 99);
    private static final Color COLOR_32 = Color.rgb(246, 124, 95);
    private static final Color COLOR_64 = Color.rgb(246, 94, 59);
    private static final Color COLOR_128 = Color.rgb(237, 207, 114);
    private static final Color COLOR_256 = Color.rgb(237, 204, 97);
    private static final Color COLOR_512 = Color.rgb(237, 200, 80);
    private static final Color COLOR_1024 = Color.rgb(237, 197, 63);
    private static final Color COLOR_2048 = Color.rgb(237, 194, 46);
    private static final Color COLOR_OTHER = Color.BLACK;
    private static final Color COLOR_GAME_OVER = Color.rgb(238, 228, 218, 0.73);

    private static final Color COLOR_VALUE_LIGHT = Color.rgb(249, 246, 242); 
                        // For tiles >= 8

    private static final Color COLOR_VALUE_DARK = Color.rgb(119, 110, 101); 
                       // For tiles < 8

    private GridPane pane;

    /** Add your own Instance Variabls here */
    public int[][] grid; // for setting up board
    // array list for rectangles to update later
    private static ArrayList<Rectangle> rectList = new ArrayList<Rectangle>();
    // array list for text to update later
    private static ArrayList<Text> textList = new ArrayList<Text>();

    @Override
    // start method sets up all the tiles and graphics of the game in the
    // the beginnning by creating a pane and scene
    // and then using a for loop to create the tiles
    // based on the size of the given grid/board
    public void start(Stage primaryStage)
    {
        // Process Arguments and Initialize the Game Board
        processArgs(getParameters().getRaw().toArray(new String[0]));
	grid = board.getGrid();

        // Create the pane that will hold all of the visual objects
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setStyle("-fx-background-color: rgb(187, 173, 160)");
        // Set the spacing between the Tiles
        pane.setHgap(15); 
        pane.setVgap(15);

        /** Add your Code for the GUI Here */
	// creating scene and title
	Scene scene = new Scene(pane);
	primaryStage.setTitle("Gui2048");

	// adding text for the title
	Text titleGame = new Text();
	titleGame.setText("2048");
	titleGame.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
	titleGame.setFill(Color.BLACK);	
	
	// adding title to pane
	pane.add(titleGame, 1, 0);

	// adding text for the score
	Text score = new Text();
	score.setText("Score: 0");
	score.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
	score.setFill(Color.BLACK);	
	
	// adding score to pane
	pane.add(score, 3, 0);

	// for loop creates the grid tiles based on the size
	// of the grid and adds all to board
	for (int row = 0; row < board.GRID_SIZE; row++)
	{
	 for (int col = 0; col < board.GRID_SIZE; col++)
	 {
	  // setting tiles to color and 
	  Rectangle rect = new Rectangle();
	  rect.setWidth(TILE_WIDTH);
	  rect.setHeight(TILE_WIDTH);
 	  Text text = new Text();
	  rect.setFill(COLOR_EMPTY);
	  text.setFill(Color.WHITE);
	  
	  // Depending on the value from the board's grid,
	  // place the tile at that location with specific
	  // color and value
	  
          if (grid[row][col] == 2)
	  { 
	    rect.setFill(COLOR_2);
	    text.setText("2");
	    text.setFont(Font.font("Times New Roman", FontWeight.BOLD,
				    TEXT_SIZE_LOW));
	  }
	  
	   if (grid[row][col] == 4)
	  { 
	    rect.setFill(COLOR_4);
	    text.setText("4");
	    text.setFont(Font.font("Times New Roman", FontWeight.BOLD,
				    TEXT_SIZE_LOW));
	  }

	  if (grid[row][col] == 8)
	  { 
	    rect.setFill(COLOR_8);
	    text.setText("8");
	    text.setFont(Font.font("Times New Roman", FontWeight.BOLD,
				    TEXT_SIZE_LOW));
	  }

	  if (grid[row][col] == 16)
	  { 
	    rect.setFill(COLOR_16);
	    text.setText("16");
	    text.setFont(Font.font("Times New Roman", FontWeight.BOLD,
				    TEXT_SIZE_LOW));
	  }
   	  
	  if (grid[row][col] == 32)
	  { 
	    rect.setFill(COLOR_32);
	    text.setText("32");
	    text.setFont(Font.font("Times New Roman", FontWeight.BOLD,
				    TEXT_SIZE_LOW));
	  }

	  if (grid[row][col] == 64)
	  { 
	    rect.setFill(COLOR_64);
	    text.setText("64");
	    text.setFont(Font.font("Times New Roman", FontWeight.BOLD,
				    TEXT_SIZE_LOW));
	  }	  
	  
	  // aligning text to the middle
	  GridPane.setHalignment(text, HPos.CENTER); 

	  // adding rectangles made and text made to corresponding
	  // array list
	  rectList.add(rect);
	  textList.add(text);
	  // adding rectangles and text to row,col location
	  pane.add(rect,col,row+1);
	  pane.add(text,col,row+1);
	 }
	}

    // Event Handler for when the key is presssed,
    // based on the key that the user pressed
   
    scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
    // overriding handle method checks for what key
    // is pressed by the user and then calls
    // the board.move(DIRECTION) method
    // and then updates the value of grid 
    // and calls the changeTile method to update the
    // tiles
    @Override
    public void handle(KeyEvent e) {
	    
      if (e.getCode() == KeyCode.UP) {
	board.move(Direction.UP);
	board.addRandomTile();//adding random tile
	System.out.println("Moving UP");
       	score.setText("Score: " + board.getScore()); //updating score
	grid = board.getGrid(); // updating grid
	changeTile(grid); // using new grid in change tile method	 
      } 
      if (e.getCode() == KeyCode.DOWN) {
	board.move(Direction.DOWN);
	board.addRandomTile(); // adding random tile	
	System.out.println("Moving DOWN");
       	score.setText("Score: " + board.getScore());//updating score
	grid = board.getGrid();//updating grid
	changeTile(grid); // using new grid in change tile method
      } 
      if (e.getCode() == KeyCode.LEFT) {
	board.move(Direction.LEFT);
	board.addRandomTile(); // adding random tile
	System.out.println("Moving LEFT");
      	score.setText("Score: " + board.getScore()); //updating score
	grid = board.getGrid();//updating grid	
	changeTile(grid);// using new grid in change tile method
	
      } 
      if (e.getCode() == KeyCode.RIGHT) {
	board.move(Direction.RIGHT);
	board.addRandomTile();	//adding random tile
	System.out.println("Moving RIGHT");
	score.setText("Score: " + board.getScore()); // updating score
	grid = board.getGrid();// updating grid
	changeTile(grid); // using new grid grid in change tile method
      }

      // if user presses s to save the board, call saveBoard method
      if (e.getCode() == KeyCode.S)
      {
      	System.out.println("Saving Board To 'Board' file");
	
	// try catch block in case exception is thrown
	try 
	{
	 board.saveBoard("Board");
      
	}
        catch (Exception c)
        {
	 System.out.println("Exception thrown");            
	}

      }

      // if the game is over, create a new pane and add
      // a transparent rectangle and indicate that the game is over
      // also makes sure that the user cannot press any more keys
      if (board.isGameOver() == true)
      {
	scene.setOnKeyPressed(null);// no more input form user		      
	GridPane goPane = new GridPane();
	goPane.setAlignment(Pos.CENTER);
	Rectangle rectangle = new Rectangle();
	rectangle.setFill(COLOR_GAME_OVER);
	rectangle.setWidth(500);
	rectangle.setHeight(800);
	// binding rectangle to pane
	goPane.hgapProperty().bind(rectangle.widthProperty()); 

	// displaying game over text
	Text gameOver = new Text();
	gameOver.setText("     Game Over");
	gameOver.setFont(Font.font("Times New Roman", FontWeight.BOLD, TEXT_SIZE_LOW));
	gameOver.setFill(COLOR_VALUE_DARK);

        goPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	
	// adding rectangle and text to pane
	goPane.getChildren().add(rectangle);
	goPane.getChildren().add(gameOver);
	scene.setRoot(goPane);
      }

    }
    });

    // setting scene and showing
    primaryStage.setScene(scene);
    primaryStage.show();
      
    }

    
    /* Add your own Instance Methods Here */
 
    // changeTile method will take in a newGrid
    // of the board after a move and then
    // uses the new grid to manipulate each
    // tile based on the values of the newGrid
    // This method accesses the values through the
    // array list
    public static void changeTile(int[][] newGrid) {
    // loop through newGrid  
    // compare value of newGrid to whatever 2048 number
    // if 2
    // get rectangle/ text at index and set to properties
    
	  for (int row = 0; row < newGrid.length; row++)
	  {
           for (int col = 0; col < newGrid[row].length; col++)
	   {
	    // calculates index for arraylist based on current
	    // row/col location
	    int arrayListIndex = col + (row * newGrid.length);
	    
	    if (newGrid[row][col] == 0) 
	     {
	     (rectList.get(arrayListIndex)).setFill(COLOR_EMPTY);
	     (textList.get(arrayListIndex)).setText("");

	    }
 	    if (newGrid[row][col] == 2)
	    {
	     (rectList.get(arrayListIndex)).setFill(COLOR_2);
	     (textList.get(arrayListIndex)).setText("2");
	     (textList.get(arrayListIndex)).setFont(Font.font("Times New Roman", FontWeight.BOLD, TEXT_SIZE_LOW));
	    }
	    if (newGrid[row][col] == 4)
	    {
	     (rectList.get(arrayListIndex)).setFill(COLOR_4);
	     (textList.get(arrayListIndex)).setText("4");
	     (textList.get(arrayListIndex)).setFont(Font.font("Times New Roman", FontWeight.BOLD, TEXT_SIZE_LOW));
	    }

	    if (newGrid[row][col] == 8)
	    {
	     (rectList.get(arrayListIndex)).setFill(COLOR_8);
	     (textList.get(arrayListIndex)).setText("8");
	     (textList.get(arrayListIndex)).setFont(Font.font("Times New Roman", FontWeight.BOLD, TEXT_SIZE_LOW));
	    }

	    if (newGrid[row][col] == 16)
	    {
	     (rectList.get(arrayListIndex)).setFill(COLOR_16);
	     (textList.get(arrayListIndex)).setText("16");
	     (textList.get(arrayListIndex)).setFont(Font.font("Times New Roman", FontWeight.BOLD, TEXT_SIZE_LOW));
	    }
	    if (newGrid[row][col] == 32)
	    {
	     (rectList.get(col + (row * newGrid.length))).setFill(COLOR_32);
	     (textList.get(col + (row * newGrid.length))).setText("32");
	     (textList.get(col + (row * newGrid.length))).setFont(Font.font("Times New Roman", FontWeight.BOLD, TEXT_SIZE_LOW));
	    }

	    if (newGrid[row][col] == 64)
	    {
	     (rectList.get(col + (row * newGrid.length))).setFill(COLOR_64);
	     (textList.get(col + (row * newGrid.length))).setText("64");
	     (textList.get(col + (row * newGrid.length))).setFont(Font.font("Times New Roman", FontWeight.BOLD, TEXT_SIZE_LOW));
	    }

	    if (newGrid[row][col] == 128)
	    {
	     (rectList.get(col + (row * newGrid.length))).setFill(COLOR_128);
	     (textList.get(col + (row * newGrid.length))).setText("128");
	     (textList.get(col + (row * newGrid.length))).setFont(Font.font("Times New Roman", FontWeight.BOLD, TEXT_SIZE_MID));
	    }

	    if (newGrid[row][col] == 256)
	    {
	     (rectList.get(col + (row * newGrid.length))).setFill(COLOR_256);
	     (textList.get(col + (row * newGrid.length))).setText("256");
	     (textList.get(col + (row * newGrid.length))).setFont(Font.font("Times New Roman", FontWeight.BOLD, TEXT_SIZE_MID));
	    }
 
	    if (newGrid[row][col] == 512)
	    { 
	     (rectList.get(col + (row * newGrid.length))).setFill(COLOR_512);
	     (textList.get(col + (row * newGrid.length))).setText("512");
	     (textList.get(col + (row * newGrid.length))).setFont(Font.font("Times New Roman", FontWeight.BOLD, TEXT_SIZE_MID));
	    }
   	  
	    if (newGrid[row][col] == 1024)
	    { 
	     (rectList.get(col + (row * newGrid.length))).setFill(COLOR_1024);
	     (textList.get(col + (row * newGrid.length))).setText("1024");
	     (textList.get(col + (row * newGrid.length))).setFont(Font.font("Times New Roman", FontWeight.BOLD, TEXT_SIZE_HIGH));
	    }

	    if (newGrid[row][col] == 2048)
	    { 
	     (rectList.get(col + (row * newGrid.length))).setFill(COLOR_2048);
	     (textList.get(col + (row * newGrid.length))).setText("2048");
	     (textList.get(col + (row * newGrid.length))).setFont(Font.font("Times New Roman", FontWeight.BOLD, TEXT_SIZE_HIGH));
	    }
 	    
	    if (newGrid[row][col] > 2048)
	    { 
	     (rectList.get(col + (row * newGrid.length))).setFill(Color.BLACK);
	     (textList.get(col + (row * newGrid.length))).setText("" + newGrid[row][col]);
	     (textList.get(col + (row * newGrid.length))).setFont(Font.font("Times New Roman", FontWeight.BOLD, TEXT_SIZE_HIGH));
	    }		    
	   }
	  } 
    }

    /** DO NOT EDIT BELOW */

    // The method used to process the command line arguments
    private void processArgs(String[] args)
    {
        String inputBoard = null;   // The filename for where to load the Board
        int boardSize = 0;          // The Size of the Board

        // Arguments must come in pairs
        if((args.length % 2) != 0)
        {
            printUsage();
            System.exit(-1);
        }

        // Process all the arguments 
        for(int i = 0; i < args.length; i += 2)
        {
            if(args[i].equals("-i"))
            {   // We are processing the argument that specifies
                // the input file to be used to set the board
                inputBoard = args[i + 1];
            }
            else if(args[i].equals("-o"))
            {   // We are processing the argument that specifies
                // the output file to be used to save the board
                outputBoard = args[i + 1];
            }
            else if(args[i].equals("-s"))
            {   // We are processing the argument that specifies
                // the size of the Board
                boardSize = Integer.parseInt(args[i + 1]);
            }
            else
            {   // Incorrect Argument 
                printUsage();
                System.exit(-1);
            }
        }

        // Set the default output file if none specified
        if(outputBoard == null)
            outputBoard = "2048.board";
        // Set the default Board size if none specified or less than 2
        if(boardSize < 2)
            boardSize = 4;

        // Initialize the Game Board
        try{
            if(inputBoard != null)
                board = new Board(new Random(), boardSize);
            else
                board = new Board(new Random(), boardSize);
        }
        catch (Exception e)
        {
            System.out.println(e.getClass().getName() + 
                               " was thrown while creating a " +
                               "Board from file " + inputBoard);
            System.out.println("Either your Board(String, Random) " +
                               "Constructor is broken or the file isn't " +
                               "formated correctly");
            System.exit(-1);
        }
    }

    // Print the Usage Message 
    private static void printUsage()
    {
        System.out.println("Gui2048");
        System.out.println("Usage:  Gui2048 [-i|o file ...]");
        System.out.println();
        System.out.println("  Command line arguments come in pairs of the "+ 
                           "form: <command> <argument>");
        System.out.println();
        System.out.println("  -i [file]  -> Specifies a 2048 board that " + 
                           "should be loaded");
        System.out.println();
        System.out.println("  -o [file]  -> Specifies a file that should be " + 
                           "used to save the 2048 board");
        System.out.println("                If none specified then the " + 
                           "default \"2048.board\" file will be used");  
        System.out.println("  -s [size]  -> Specifies the size of the 2048" + 
                           "board if an input file hasn't been"); 
        System.out.println("                specified.  If both -s and -i" + 
                           "are used, then the size of the board"); 
        System.out.println("                will be determined by the input" +
                           " file. The default size is 4.");
    }

} 


 

