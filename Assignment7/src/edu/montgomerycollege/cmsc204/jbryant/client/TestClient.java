package edu.montgomerycollege.cmsc204.jbryant.client;

import edu.montgomerycollege.cmsc204.jbryant.Application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by Jamison on 12/2/2015.
 */
public class TestClient
{
    public static void main(String[] args)
    {
        try {
            // Initialize client and I/O streams
            Socket client = new Socket("127.0.0.1", Application.getPort());
            Scanner input = new Scanner(client.getInputStream());
            PrintWriter output = new PrintWriter(client.getOutputStream());
            System.out.println("[" + Application.getTimestamp() + "] Initializing client " + client.hashCode());

            // Send HELLO command to server
            System.out.println("[" + Application.getTimestamp() + "] Sent HELLO command to server");
            output.println("HELLO");
            output.flush();

            // Capture and process server response
            String response = input.nextLine();
            System.out.println("[" + Application.getTimestamp() + "] Received response from server: " + response);

            // Shut down client and connection
            System.out.println("[" + Application.getTimestamp() + "] Shutting down client " + client.hashCode());
            input.close();
            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
