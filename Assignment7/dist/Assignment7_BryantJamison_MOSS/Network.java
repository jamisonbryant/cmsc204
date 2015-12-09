package edu.montgomerycollege.cmsc204.jbryant;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Contains constants and convenience methods for the application.
 *
 * @author Jamison Bryant <jbryan46@montgomerycollege.edu> for R. Alexander's CMSC 204 M/W 1:00PM-2:40PM
 */
public class Network
{
    /**
     * Returns the port number used for application communication
     *
     * @return Port number
     */
    public static int getPort() { return 9680; }

    /**
     * Returns the current timestamp, which is used in console debug messages.
     *
     * @return Current timestamp
     */
    public static String getTimestamp() { return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); }
}
