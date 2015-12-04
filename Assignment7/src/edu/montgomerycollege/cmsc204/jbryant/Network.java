package edu.montgomerycollege.cmsc204.jbryant;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Jamison on 12/2/2015.
 */
public class Network
{
    public static int getPort() { return 8890; }
    public static String getTimestamp() { return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); }
}
