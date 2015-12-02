package edu.montgomerycollege.cmsc204.jkartchner;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *  Executes Tests commands from a socket.
 *  The run method will set up the Scanner and the PrintWriter
 *  and then will call executeCommands();
 */
public abstract class TestCommandProcessor implements Runnable
{
	protected Socket s;
	protected Scanner in;
	protected PrintWriter out;
	protected Test account;

	/**
	 * 
	 * @param aSocket socket being used for connection
	 * @param aTest the test being used
	 */
	public TestCommandProcessor(Socket aSocket, Test aTest)
	{
		s = aSocket;
		account = aTest;
	}
	
	/**
	 * Executes all commands until the end of input.
	 * The commands will be an input string from the client 
	 * in one of the following formats:
	 * 	�	"CHECK <answer>"
	 *		�	"NEXT"
	 *		�	"PERCENT"
	 *		�	"QUIT"
	 *
	 *	CHECK will place a String on the output stream returned from aTest.check()
	 *	NEXT will place a String on the output stream returned from aTest.next()
	 *	PERCENT will place a double on the output stream returned from aTest.getPercentCorrect()
	 *	QUIT - return
	 * @throws IOException
	 */
	public abstract void executeCommands() throws IOException;

}
