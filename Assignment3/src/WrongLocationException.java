/**
 * An error thrown when a truck is loaded or unloaded in the incorrect location
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class WrongLocationException extends Exception
{
    public WrongLocationException(String message)
    {
        super(message);
    }
}
