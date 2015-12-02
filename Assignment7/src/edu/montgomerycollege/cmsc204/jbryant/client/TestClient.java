package edu.montgomerycollege.cmsc204.jbryant.client;

import edu.montgomerycollege.cmsc204.jbryant.Application;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
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
        boolean inputValid = false;

        // Get test taker's name
        String name = null;

        while (!inputValid) {
            name = (String) JOptionPane.showInputDialog(null, "Enter your first and last name:", "Assignment 7",
                    JOptionPane.QUESTION_MESSAGE);

            if (name != null) {
                if (!name.isEmpty()) {
                    inputValid = true;
                }
            }
        }

        // Build test taking window
        JFrame testWindow = new JFrame("Assignment 7");
        testWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        testWindow.setSize(new Dimension(700, 300));
        testWindow.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Test taker: " + name);
        nameLabel.setForeground(Color.BLUE);
        namePanel.add(nameLabel);

        JPanel questionPanel = new JPanel();
        JLabel questionLabel = new JLabel("Question");
        questionPanel.add(questionLabel);

        JPanel answerPanel = new JPanel();
        JLabel answerLabel = new JLabel("Answer");
        answerPanel.add(answerLabel);

        JTextArea questionField = new JTextArea();
        questionField.setEditable(false);
        questionField.setPreferredSize(new Dimension(500, 50));

        JTextField answerField = new JTextField();
        answerField.setPreferredSize(new Dimension(500, 25));

        JPanel buttonPanel = new JPanel();
        JButton checkButton = new JButton("Check");
        JButton nextButton = new JButton("Next");
        JButton percentButton = new JButton("Percent Correct");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(checkButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(percentButton);
        buttonPanel.add(exitButton);

        mainPanel.add(namePanel, BorderLayout.SOUTH);
        mainPanel.add(questionPanel, BorderLayout.SOUTH);
        mainPanel.add(questionField, BorderLayout.SOUTH);
        mainPanel.add(answerPanel, BorderLayout.SOUTH);
        mainPanel.add(answerField, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        testWindow.setContentPane(mainPanel);
        testWindow.setVisible(true);

//        try {
//            // Initialize client and I/O streams
//            Socket client = new Socket("127.0.0.1", Application.getPort());
//            Scanner input = new Scanner(client.getInputStream());
//            PrintWriter output = new PrintWriter(client.getOutputStream());
//            System.out.println("[" + Application.getTimestamp() + "] Initializing client " + client.hashCode());
//
//            // Send HELLO command to server
//            System.out.println("[" + Application.getTimestamp() + "] Sent HELLO command to server");
//            output.println("HELLO");
//            output.flush();
//
//            // Capture and process server response
//            String response = input.nextLine();
//            System.out.println("[" + Application.getTimestamp() + "] Received response from server: " + response);
//
//            // Shut down client and connection
//            System.out.println("[" + Application.getTimestamp() + "] Shutting down client " + client.hashCode());
//            input.close();
//            client.close();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
