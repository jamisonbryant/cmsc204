package edu.montgomerycollege.cmsc204.jbryant.server;

import edu.montgomerycollege.cmsc204.jkartchner.Test;

import java.util.Arrays;

/**
 * Created by Jamison on 12/2/2015.
 */
public class CSTest extends Test
{
    private String[] questions;
    private String[] answers;

    public CSTest()
    {
        super();

        // Define questions and answers
        questions = new String[10];
        questions[0] = "";
        questions[1] = "Every node in a binary tree has _____ references.";
        questions[2] = "In a perfectly balanced binary tree, the height of the subtrees of the root are _____";
        questions[3] = "A node in a binary tree is called a(n) _____ if it has neither a left nor a right child.";
        questions[4] = "The number of nodes in the longest branch of the tree is called the _____ of the tree.";
        questions[5] = "Each item in a data set has a special member that uniquely identifies it called a(n) _____.";
        questions[6] = "In an ordered linked list the search algorithm is somewhat improved because the list is _____.";
        questions[7] = "A queue is a(n) _____ data structure.";
        questions[8] = "The ability to create new data types from existing data types is called _____.";
        questions[9] = "When the binding of methods takes place at execution time, it is considered _____.";
        questions[10] = "Which operator is used to determine if an object is of a particular class type?";

        answers = new String[10];
        answers[0] = "";
        answers[1] = "2";
        answers[2] = "equal";
        answers[3] = "leaf";
        answers[4] = "height";
        answers[5] = "key";
        answers[6] = "sorted";
        answers[7] = "FIFO";
        answers[8] = "inheritance";
        answers[9] = "dynamic";
        answers[10] = "instanceof";
    }

    public CSTest(String[] questions, String[] answers)
    {
        this.questions = questions;
        this.answers = answers;
    }

    @Override
    public String check(String answer)
    {
        return null;
    }

    @Override
    public String next()
    {
        return null;
    }

    @Override
    public double getPercentCorrect()
    {
        return 0;
    }
}
