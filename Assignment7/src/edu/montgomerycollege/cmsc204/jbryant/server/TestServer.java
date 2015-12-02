package edu.montgomerycollege.cmsc204.jbryant.server;

import edu.montgomerycollege.cmsc204.jbryant.Application;

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
        try {
            // Initialize port and start listening
            ServerSocket server = new ServerSocket(Application.getPort());
            System.out.println("[" + Application.getTimestamp() + "] Listening on port " + Application.getPort());

            while (true) {
                // Accept client connection if present
                Socket client = server.accept();
                System.out.println("[" + Application.getTimestamp() + "] Connected to client " + client.hashCode());

                // Get data from client streams
                Scanner input = new Scanner(client.getInputStream());
                PrintWriter output = new PrintWriter(client.getOutputStream());
                String command = input.nextLine();

                // Validate and process command
                if (command.equals("HELLO")) {
                    System.out.println("[" + Application.getTimestamp() + "] Received HELLO command");
                    output.println("Hello World");
                    output.flush();
                } else {
                    System.out.println("[" + Application.getTimestamp() + "] Received unknown command: " + command);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
