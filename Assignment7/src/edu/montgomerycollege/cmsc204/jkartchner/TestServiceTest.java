package edu.montgomerycollege.cmsc204.jkartchner;

import static org.junit.Assert.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import edu.montgomerycollege.cmsc204.jbryant.CSTest;
import edu.montgomerycollege.cmsc204.jbryant.CSTestCommandProcessor;
import edu.montgomerycollege.cmsc204.jkartchner.TestCommandProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests the TestServer
 * 
 *
 * @author Professor Myers
 * @version Fall 2013
 * 
 */
public class TestServiceTest {
	/** The server socket which is used to get sockets from */
	private static ServerSocket server;
	/** Number of accounts to make */
	private static final int NUM_TESTS = 5;
	/** Thread in which the TestCommandProcessor is running on */
	private Thread serverThread;
	/** The instance of TestCommandProcessor to test with*/
	private TestCommandProcessor commandProcessor;
	/** The client socket used to connect to the TestServer */
	private Socket client;
	/** Scanner to get values from the TestServer */
	private Scanner in;
	/** Output to communicate to TestServer with */
	private PrintWriter out;

	@Before
	public void setUp() throws Exception {
		client = new Socket("127.0.0.1",9680);
//		for (int i = 0; i<5; i++)
//		{
		commandProcessor = new CSTestCommandProcessor(server.accept(), new CSTest());
		serverThread = new Thread(commandProcessor);
		serverThread.start();
		
		out = new PrintWriter(client.getOutputStream());
		in = new Scanner(client.getInputStream());
//		}
	}
	
	@BeforeClass
	public static void startServer() {
		try {
			server = new ServerSocket(9680);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void endServer() throws IOException {
		server.close();
	}
	
	@After
	public void tearDown() throws Exception {
		out.write("QUIT\n");
		client.shutdownOutput();
		client.shutdownInput();
		out.close();
		in.close();
		client.close();
		client = null;
//		test = null;
	}

/*	@Test(timeout=1000)
	public final void testInvalid() throws IOException {
		for(int i = 0; i < 100; i++) {
			String random = UUID.randomUUID().toString();
			out.write(random + "\n");
			out.flush();
			assertEquals("UNRECOGNIZED " + random, in.nextLine());
		}
	}
	*/
	@Test(timeout=1000)
	public final void testTestAccess() {
			out.write("NEXT"+"\n");
			out.flush();
			assertEquals("Every node in a binary tree has ____ references.",in.nextLine());
	}
	
}
