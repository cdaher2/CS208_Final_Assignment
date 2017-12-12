import javax.swing.JPanel;
import java.util.Date;
/**
 * Room.
 *
 * @author (Catherine Denis)
 * @version (0.1)
 */
public class Room extends JPanel
{
    private int hash;
    private int timestamp = (int)new Date().getTime();
    private int number;
    //private MHashTable<Player> hTable;
    
    /**
     * Constructor for objects of class Room
     */
    public Room()
    {
        number = 0;
        this.makeHash();
    }

    /**
     * Constructor for objects of class Room
     * @param num - int room number
     */
    public Room(int num)
    {
        number = num;
        this.makeHash();
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
    
    /**
     * Creates this room's hashcode
     */
    private void makeHash() {
        hash = 1;
        for (int i = 0; i < number + 9; i++) {
            hash = hash + (timestamp * i);
        }
        hash = Math.abs(hash);
    }
    
    /**
     * Returns the room's hashcode
     * @return hash - hashcode calculated
     */
    @Override
    public int hashCode() {
        return hash;
    }
    
    /**
     * Returns whether or not this room is the same as another room
     * @return - boolean value true or false
     */
    public boolean equals(Room room) {
        if(room instanceof Room){
            if(this.number == room.number
                &&this.hash == room.hash)
                { return true; }
        }
        return false;
    }
}