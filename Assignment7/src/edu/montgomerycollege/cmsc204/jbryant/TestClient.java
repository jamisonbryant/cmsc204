package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jbryant.Network;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Jamison on 12/2/2015.
 */
public class TestClient
{
    public static void main(String[] args)
    {
//        // Get test taker's name
//        String name = null;
//        boolean inputValid = false;
//
//        while (!inputValid) {
//            name = (String) JOptionPane.showInputDialog(null, "Enter your first and last name:", "Assignment 7",
//                    JOptionPane.QUESTION_MESSAGE);
//
//            if (name != null) {
//                if (!name.isEmpty()) {
//                    inputValid = true;
//                }
//            }
//        }

        String name = "John Doe";

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
        JLabel questionLabel = new JLabel("Question 1");
        questionPanel.add(questionLabel);

        JPanel answerPanel = new JPanel();
        JLabel answerLabel = new JLabel("Answer");
        answerPanel.add(answerLabel);

        JTextArea questionField = new JTextArea();
        questionField.setEditable(false);
        questionField.setPreferredSize(new Dimension(500, 50));

        JTextField answerField = new JTextField();
        answerField.setPreferredSize(new Dimension(500, 15));

        JButton checkButton = new JButton("Check");
        checkButton.addActionListener(e -> {
            // Check if answer field is empty
            if (!answerField.getText().isEmpty()) {
                // Send question to server to check
                String response = send("CHECK:" + answerField.getText());

                switch (response) {
                    case "Correct":
                        JOptionPane.showMessageDialog(null, "Your answer is correct!",
                                "Check Answer", JOptionPane.INFORMATION_MESSAGE);
                        break;

                    case "Incorrect":
                        JOptionPane.showMessageDialog(null, "Sorry, that answer is incorrect.",
                                "Check Answer", JOptionPane.INFORMATION_MESSAGE);
                        break;

                    default:
                        System.err.println("[" + Network.getTimestamp() + "] Invalid server response: " + response);
                        break;
                }
            } else {
                // Display error message
                JOptionPane.showMessageDialog(null, "You must enter an answer in order for it to be checked",
                        "Answer Required", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            // Check if answer field is empty
            if (!answerField.getText().isEmpty()) {
                // Get next question from server
                questionField.setText(send("NEXT"));
                answerField.setText("");

                // Update question label
                int questionNumber = Integer.parseInt(questionLabel.getText().replaceAll("[\\D]", ""));
                questionLabel.setText("Question " + ++questionNumber);

                if (questionNumber == 10) {
                    nextButton.setEnabled(false);
                }
            } else {
                // Display error message
                JOptionPane.showMessageDialog(null, "You must answer the question before going to the next one",
                        "Answer Required", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton gradeButton = new JButton("Grade");
        gradeButton.addActionListener(e -> {
            // Get grade from server
            Double grade = Double.parseDouble(send("GRADE"));
            String text = "Your grade is currently " + grade + "%";

            // Display information message
            JOptionPane.showMessageDialog(null, text, "Current Grade", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0) );

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(checkButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(gradeButton);
        buttonPanel.add(exitButton);

        mainPanel.add(namePanel, BorderLayout.SOUTH);
        mainPanel.add(questionPanel, BorderLayout.SOUTH);
        mainPanel.add(questionField, BorderLayout.SOUTH);
        mainPanel.add(answerPanel, BorderLayout.SOUTH);
        mainPanel.add(answerField, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        testWindow.setContentPane(mainPanel);
        testWindow.setVisible(true);

        // Get first question
        questionField.setText(send("NEXT"));
    }

    private static String send(String command)
    {
        Socket client;
        Scanner input;
        PrintWriter output;
        String response = null;

        try {
            // Connect to server
            client = new Socket("127.0.0.1", Network.getPort());
            input = new Scanner(client.getInputStream());
            output = new PrintWriter(client.getOutputStream());
            System.out.println("[" + Network.getTimestamp() + "] Initializing client " + client.hashCode());

            // Validate command
            if (command.equals("NEXT") || command.contains("CHECK:") || command.equals("GRADE")) {
                // Send command to server
                System.out.println("[" + Network.getTimestamp() + "] Sending command " + command + " to server");
                output.println(command);
                output.flush();

                // Get response from server
                response = input.nextLine();
                System.out.println("[" + Network.getTimestamp() + "] Received response from server: " + response);

                // Disconnect from server
                System.out.println("[" + Network.getTimestamp() + "] Shutting down client " + client.hashCode());
                input.close();
                client.close();
            } else {
                System.err.println("[" + Network.getTimestamp() + "] " + command + " is not a valid command");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return response;
    }
}
