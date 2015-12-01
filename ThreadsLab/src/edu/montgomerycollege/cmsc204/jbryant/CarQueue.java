package edu.montgomerycollege.cmsc204.jbryant;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

/**
 * Car Queue class
 *
 * @author Jamison Bryant
 */
public class CarQueue
{
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;
    private ArrayDeque<Integer> queue;
    private Random random = new Random();

    public CarQueue()
    {
        // Initialize queue
        queue = new ArrayDeque<Integer>();

        // Add random directions to queue
        for (int i = 0; i < 5; i++) {
            queue.add(random.nextInt(4));
        }
    }

    public void addToQueue()
    {
        class Animation implements Runnable
        {
            @Override
            public void run()
            {
                while(true) {
                    try {
                        // Add random direction to queue
                        queue.add(random.nextInt(4));

                        // Tell thread to sleep
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Create animation object
        Animation a = new Animation();

        // Create and start thread
        Thread t = new Thread(a);
        t.start();
    }

    public int deleteQueue()
    {
        return queue.remove();
    }
}
