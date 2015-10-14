/**
 * An error thrown when an empty truck is unloaded or a full truck is loaded
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class TruckLoadException extends Exception
{
    public TruckLoadException(String message)
    {
        super(message);
    }
}
