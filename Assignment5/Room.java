
import javax.swing.JPanel;
/**
 * Write a description of class Room here.
 *
 * @author (your name)
 * @version (0.1)
 */
public class Room extends JPanel
{
    private int number;
    private MHashTable<String, Player>;
    
    /**
     * Constructor for objects of class Room
     */
    public Room()
    {
        number = 0;
    }

    /**
     * Constructor for objects of class Room
     * @param num - int room number
     */
    public Room(int num)
    {
        number = num;
    }
    
    /**
     * Gets the room number
     * @return - this room's assigned number
     */
    public int getNumber() {
        return number;
    }
    
    /**
     * Sets the room number
     * @param n - The desired room number
     */
    protected void setNumber(int n) {
        number = n;
    }
    
    
    
}
