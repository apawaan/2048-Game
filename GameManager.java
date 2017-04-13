//------------------------------------------------------------------//
// GameManager.java                                                 //
//                                                                  //
// Game Manager for 2048                                            //
//                                                                  //
// Author:  Ujjwal Gulecha, Alan Kuo	                            //
// Date:    01/25/17                                                //
//------------------------------------------------------------------//


/*
 * Name: Angelynne Pawaan
 * Login: cs8bwadr
 * Date:  February 7, 2017
 * File:  GameManager.java
 * Sources of Help: Textbook, tutors, piazza, discussion
 * GameManager.java contains GameManager contructors
 * for a new game and loads a saved game. 
 * It also contains the play method that generates the game.
 * 
 */

import java.util.*;
import java.io.*;

public class GameManager {
    // Instance variables
    private Board board; // The actual 2048 board
    private String outputFileName; // File to save the board to when exiting

    // TODO PSA3
    // GameManager Constructor
    // Generate new game
    public GameManager(String outputBoard, int boardSize, Random random) {
        System.out.println("Generating a New Board");
	board = new Board(random, boardSize);
	outputFileName = outputBoard;
    }

    // TODO PSA3
    // GameManager Constructor
    // Load a saved game
    public GameManager(String inputBoard, String outputBoard, Random random) throws IOException {
        System.out.println("Loading Board from " + inputBoard);
	board = new Board(random, inputBoard);
        outputFileName = outputBoard;
    }

    // TODO PSA3
    // Main play loop
    // Takes in input from the user to specify moves to execute
    // valid moves are:
    //      k - Move up
    //      j - Move Down
    //      h - Move Left
    //      l - Move Right
    //      q - Quit and Save Board
    //
    //  If an invalid command is received then print the controls
    //  to remind the user of the valid moves.
    //
    //  Once the player decides to quit or the game is over,
    //  save the game board to a file based on the outputFileName
    //  string that was set in the constructor and then return
    //
    //  If the game is over print "Game Over!" to the terminal
    public void play() throws IOException {
	System.out.println("Please enter a command: ");
	Scanner input = new Scanner(System.in);
	String command = input.next();
	// each condition checks input value and executes
	// specified move
	//
	// if game is still running 
        if (board.isGameOver() == false)
	{
	// if quit is called or if the game is over
	if ((command).equals("q") == true || board.isGameOver() == true)
	{
	 board.saveBoard(outputFileName);
	 if (board.isGameOver() == true) System.out.println("Game Over!");
	 
	}

	else if ((command).equals("e") == true)
	{
	 board.expand();
	 System.out.println(board);
	 play();
	}
	
	// what happens when k is called
	else if ((command).equals("k") == true)
	{
	 board.move(Direction.UP);
	 board.addRandomTile();
	 System.out.println(board);
	 play();
	}
	// what happens when j is called
	else if ((command).equals("j") == true)
	{
	 board.move(Direction.DOWN);
	 board.addRandomTile();
	 System.out.println(board);
	 play();
	}
	// what happens when h is called
	else if ((command).equals("h") == true)
	{
	 board.move(Direction.LEFT);
	 board.addRandomTile();
	 System.out.println(board);
	 play();
	}
	// what happens if l is called
	else if ((command).equals("l") == true)
	{
	 board.move(Direction.RIGHT);
	 board.addRandomTile();
	 System.out.println(board);
	 play();
	}
	// if input is not a valid command, print controls again
	else
	{
	 printControls();
	 play();
	}	 
      }
   }

    // Print the Controls for the Game
    private void printControls() {
        System.out.println("  Controls:");
        System.out.println("    k - Move Up");
        System.out.println("    j - Move Down");
        System.out.println("    h - Move Left");
        System.out.println("    l - Move Right");
        System.out.println("    q - Quit and Save Board");
        System.out.println();
    }
}
