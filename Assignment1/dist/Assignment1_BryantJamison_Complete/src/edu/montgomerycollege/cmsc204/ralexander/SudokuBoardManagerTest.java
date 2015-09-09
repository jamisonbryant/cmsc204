package edu.montgomerycollege.cmsc204.ralexander;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;

import edu.montgomerycollege.cmsc204.jbryant.InputOutOfRangeException;
import edu.montgomerycollege.cmsc204.jbryant.SudokuBoardManager;
import edu.montgomerycollege.cmsc204.jbryant.ValueNotValidException;

public class SudokuBoardManagerTest
{
    private SudokuBoardManagerInterface myBoard;
    private File newFile;
    private PrintWriter output;

    @Before
    public void setUp()
    {
        newFile = new File("sudokuTest");
        
        try {
            output = new PrintWriter(newFile);
            output.println("8,0,0,3,0,9,0,0,5");
            output.println("0,0,0,0,2,0,0,0,0");
            output.println("5,0,0,6,0,8,0,0,3");
            output.println("0,7,5,9,0,3,4,6,0");
            output.println("0,0,1,0,0,0,7,0,0");
            output.println("0,3,8,7,0,4,2,5,0");
            output.println("6,0,0,4,0,1,0,0,2");
            output.println("0,0,0,0,9,0,0,0,0");
            output.println("3,0,0,5,0,7,0,0,4");
            output.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        myBoard = new SudokuBoardManager();
        myBoard.newGame(newFile.getAbsoluteFile());
    }

    @Test
    public void testSetValueAt()
    {
        try {
            assertEquals(myBoard.getValueAt(2, 2), 0);
            myBoard.setValueAt(2, 2, 4);
            assertEquals(myBoard.getValueAt(2, 2), 4);
            myBoard.setValueAt(2, 2, 8);
            fail("This statement should have thrown a ValueNotValidException");
        } catch (InputOutOfRangeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("This statement should have thrown a ValueNotValidException");
        } catch (ValueNotValidException e) {
            // TODO Auto-generated catch block
            System.out.println("This is an invalid value");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("This statement should have thrown a ValueNotValidException");
        }

        try {
            myBoard.setValueAt(2, 8, 10);
            fail("This statement should have thrown a InputOutOfRangeException");
        } catch (InputOutOfRangeException e) {
            System.out.println("This is an invalid row");
            // TODO Auto-generated catch block
        } catch (ValueNotValidException e) {
            // TODO Auto-generated catch block
            fail("This statement should have thrown a InputOutOfRangeException");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("This statement should have thrown a InputOutOfRangeException");
        }
    }

    @Test
    public void testSetValueAtSTUDENT()
    {
        fail("Student test not implemented yet");
    }

    @Test
    public void testGetValueAt()
    {
        try {
            assertEquals(myBoard.getValueAt(1, 1), 8);
            assertEquals(myBoard.getValueAt(7, 7), 0);
            myBoard.getValueAt(5, 10);
            fail("This statement should have thrown an InputOutOfRangeException");
        } catch (InputOutOfRangeException e) {
            // TODO Auto-generated catch block
            System.out.println("This is an invalid column value");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("This statement should have thrown an InputOutOfRangeException");
        }
    }

    @Test
    public void testToString()
    {
        String boardString, resultString;
        boardString = myBoard.toString();
        resultString = boardString.substring(0, 17);
        assertEquals(resultString, "8,0,0,3,0,9,0,0,5");
        resultString = boardString.substring(72, 89);
        assertEquals(resultString, "0,0,1,0,0,0,7,0,0");
    }

    @Test
    public void testDisplayPossibleValues()
    {
        int[] results;
        try {
            results = myBoard.displayPossibleValues(2, 2);
            assertEquals(results[0], 1);
            assertEquals(results[1], 4);
            assertEquals(results[2], 6);
            assertEquals(results[3], 9);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("This statement should not have thrown an Exception");
        }

        try {
            results = myBoard.displayPossibleValues(8, 8);
            assertEquals(results[0], 1);
            assertEquals(results[1], 3);
            assertEquals(results[2], 7);
            assertEquals(results[3], 8);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("This statement should not have thrown an Exception");
        }
    }

    @Test
    public void testDisplayPossibleValuesSTUDENT()
    {
        fail("Student test not implemented yet");
    }

}
