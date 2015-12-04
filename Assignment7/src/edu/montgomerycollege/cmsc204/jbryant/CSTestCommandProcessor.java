package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jkartchner.Test;
import edu.montgomerycollege.cmsc204.jkartchner.TestCommandProcessor;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Jamison on 12/2/2015.
 */
public class CSTestCommandProcessor extends TestCommandProcessor
{
    /**
     * @param socket socket being used for connection
     * @param test   the test being used
     */
    public CSTestCommandProcessor(Socket socket, Test test)
    {
        super(socket, test);
    }

    @Override
    public void executeCommands() throws IOException
    {

    }

    @Override
    public void run()
    {

    }
}
