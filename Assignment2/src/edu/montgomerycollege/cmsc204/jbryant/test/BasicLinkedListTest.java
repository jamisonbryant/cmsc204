package edu.montgomerycollege.cmsc204.jbryant.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.montgomerycollege.cmsc204.jbryant.BasicLinkedList;

public class BasicLinkedListTest
{
    private BasicLinkedList<String> list;
    
    @Before
    public void setUp()
    {
        // Create list and add elements
        list = new BasicLinkedList<String>();
        list.addToEnd("apple");
        list.addToEnd("banana");
        list.addToEnd("orange");
    }
    
    @After
    public void tearDown()
    {
        list = null;
    }
}
