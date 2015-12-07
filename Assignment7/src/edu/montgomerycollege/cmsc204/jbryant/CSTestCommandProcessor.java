package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jkartchner.Test;
import edu.montgomerycollege.cmsc204.jkartchner.TestCommandProcessor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Jamison on 12/2/2015.
 */
public class CSTestCommandProcessor extends TestCommandProcessor
{
    private Socket socket;
    private Test test;

    /**
     * @param socket socket being used for connection
     * @param test   the test being used
     */
    public CSTestCommandProcessor(Socket socket, Test test)
    {
        super(socket, test);
        this.socket = socket;
        this.test = test;
    }

    @Override
    public void executeCommands() throws IOException
    {

    }

    @Override
    public void run()
    {
        Socket client;
        Scanner input;
        PrintWriter output;
        String response = null;

        try {
            // Connect to server
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream());

            if (input != null && input.hasNextLine()) {
                String command = input.nextLine();

                // Validate command
                if (command.equals("NEXT") || command.contains("CHECK:") || command.equals("GRADE")) {
                    // Send response to client
                    if (command.equals("NEXT")) {
                        output.println(test.next());
                        output.flush();
                    } else if (command.equals("CHECK")) {
//                        // This isn't handled...
//                        output.println(test.check(answer));
//                        output.flush();
                    } else if (command.equals("GRADE")) {
                        output.println(test.getPercentCorrect());
                        output.flush();
                    } else if (command.equals("QUIT")) {
                        return;
                    }

                    // Disconnect from server
                    input.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
