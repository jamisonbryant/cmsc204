package edu.montgomerycollege.cmsc204.jbryant.server;

import edu.montgomerycollege.cmsc204.jbryant.Network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Jamison on 12/2/2015.
 */
public class TestServer
{
    public static void main(String[] args)
    {
        CSTest test = new CSTest();

        try {
            // Initialize port and start listening
            ServerSocket server = new ServerSocket(Network.getPort());
            System.out.println("[" + Network.getTimestamp() + "] Listening on port " + Network.getPort());

            while (true) {
                // Accept client connection if present
                Socket client = server.accept();
                System.out.println("[" + Network.getTimestamp() + "] Connected to client " + client.hashCode());

                // Get data from client streams
                Scanner input = new Scanner(client.getInputStream());
                PrintWriter output = new PrintWriter(client.getOutputStream());

                if (input.hasNextLine()) {
                    String[] parts = input.nextLine().split(":");
                    String command = parts[0];
                    String param = (parts.length == 2 ? parts[1] : null);

                    String response = null;
                    System.out.println("[" + Network.getTimestamp() + "] Received command " + command);

                    // Process received command
                    switch (command) {
                        case "NEXT":
                            response = test.next();
                            break;

                        case "CHECK":
                            response = test.check(param);
                            break;

                        case "GRADE":
                            response = Double.toString(test.getPercentCorrect());
                            break;

                        default:
                            System.err.println("[" + Network.getTimestamp() + "] Invalid client command: " + command);
                            break;
                    }

                    output.println(response);
                    output.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
