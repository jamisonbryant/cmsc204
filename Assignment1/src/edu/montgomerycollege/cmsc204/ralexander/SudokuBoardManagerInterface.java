package edu.montgomerycollege.cmsc204.ralexander;
import java.io.File;
import java.io.FileNotFoundException;

import edu.montgomerycollege.cmsc204.jbryant.InputOutOfRangeException;
import edu.montgomerycollege.cmsc204.jbryant.ValueNotValidException;

/**
 * An interface to manage a Sudoku game.  
 * Allows the user to start a new game, enter (set) a value for a row,column combination, 
 * retrieve (get) a value for a row,column combination, return a string representation of the
 * sudoku board and display all valid values for an entry in the puzzle.
 * When entering a value, this class will check the validity of a value at the specified row,column combination,
 * based on the rules of Sudoku.
 * The class that implements this interface will use a Data Structure of a two-dimensional array of 
 * integers to represent the sudoku game.
 * In order for your public test and private test to be successful, you cannot change this interface!!!
 * @author Professor Myers
 *
 */
public interface SudokuBoardManagerInterface {
	
	/** Set a value (v) at the row (r) and column (c).  If the value is valid for this row and column, 
	 * the value will be placed at that location.  If the value is invalid for this row and column,
	 * the value will not be placed at that location.
	 * @param r row in the sudoku puzzle
	 * @param c column in the sudoku puzzle
	 * @param v value to place in the row,column
	 * @throws InputOutOfRangeException if the values for the row or column are invalid (> 9 or < 1)
	 * @throws ValueNotValidException if the value is invalid for this (row,column) entry in the puzzle
	 */
	public void setValueAt(int r, int c, int v) throws InputOutOfRangeException, ValueNotValidException;
	
	/** Get the value at the row (r) and column (c).  
	 * @param r row in the sudoku puzzle
	 * @param c column in the sudoku puzzle
	 * @throws InputOutOfRangeException if the values for the row or column are invalid (> 9 or < 1)
	 * @return the value for this row,column in the puzzle, returns 0 if value was not previously set
	 */	
	public int getValueAt(int r, int c) throws InputOutOfRangeException;
	
	/**
	 * Determines all possible values for this (row, column) entry in the puzzle, based on the
	 * rules of Sudoku
	 * @param r row in the sudoku puzzle
	 * @param c column in the sudoku puzzle
	 * @return an array of integers representing all possible values for this (row,column) entry 
	 * in the puzzle in numeric order, i.e. 3 6 9
	 * @throws InputOutOfRangeException if the values for the row or column are invalid (> 9 or < 1)
	 */
	public int[] displayPossibleValues(int r, int c) throws InputOutOfRangeException;
		
	/** Return string representation of the sudoku board in the following format:
	 *   0,0,2,0,8,0,0,0,1 (new line)
	 *   1,0,0,4,0,2,0,0,6 (new line)
	 *   . . .
	 *   
	 * @return string representation of the sudoku board
	 */
	public String toString();
	
	/**
	 * 
	 * @param gameFile the File with the representation of a new sudoku game.
	 * File will contain representation of the sudoku board in the following format:
	 *   0,0,2,0,8,0,0,0,1 (new line)
	 *   1,0,0,4,0,2,0,0,6 (new line)
	 *   . . .
	 * 9 rows separated with a new line.  Each row contains 9 values separated by a comma.
	 * A 0 represents an empty value
	 * 
	 */
	public void newGame(File gameFile);
}
